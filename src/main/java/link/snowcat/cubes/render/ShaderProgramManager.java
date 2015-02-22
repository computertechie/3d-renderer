package link.snowcat.cubes.render;

import com.google.gson.Gson;
import link.snowcat.cubes.Cubes;
import link.snowcat.cubes.generated.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: Pepper
 * Date: 5/25/13
 * Time: 8:36 PM
 * Project: Cubes
 */


public class ShaderProgramManager {
    private static ShaderProgramManager instance = new ShaderProgramManager();
    private Map<String, ShaderProgram> nameProgramMap;
    private String currentProgram="";
    private final String programAssetDir = "/assets/json/shader_programs/";
    private final Gson shaderGson = new Gson();

    private ShaderProgramManager(){
        nameProgramMap = new HashMap<String, ShaderProgram>();
    }

    public static ShaderProgramManager getInstance(){
        return instance;
    }

    public void loadAndBindProgram(String programName) {
        if(nameProgramMap.containsKey(programName)){
            return;
        }

        registerProgram(shaderGson.fromJson(new InputStreamReader(Cubes.class.getResourceAsStream(programAssetDir + programName + ".json")), ShaderProgram.class));
        bindProgram(programName);
    }

    public void bindProgram(String name){
        if(currentProgram.equals(name)){
            return;
        }
        else{
            currentProgram = name;
            GL20.glUseProgram(nameProgramMap.get(name).getProgramID());
        }
    }

    public ShaderProgram getShaderProgram(String name){
        return nameProgramMap.get(name);
    }

    private void registerProgram(ShaderProgram program){
        if(nameProgramMap.containsKey(program.getRenderProcessName()))
            return;

        int vertexShaderID = loadShader(program.getVertexShaderFile(), GL20.GL_VERTEX_SHADER);
        int fragmentShaderID = loadShader(program.getFragmentShaderFile(), GL20.GL_FRAGMENT_SHADER);
        int programID = GL20.glCreateProgram();

        int mrtLocation = 0;
        for(String mrtAttrib : program.getFragmentMRTs()){
            GL30.glBindFragDataLocation(programID, mrtLocation, mrtAttrib);
            mrtLocation++;
        }

        if(fragmentShaderID > -1 && vertexShaderID > -1 && createProgram(programID, vertexShaderID, fragmentShaderID)){
            program.setProgramID(programID);
            if(!program.getVertexAttributes().isEmpty()) {
                program.setVertexAttributes(getVertexAttributeLocations(program.getVertexAttributes(), programID));
            }

            if(!program.getUniformAttributes().isEmpty()) {
                program.setUniformAttributes(getUniformAttributeLocations(program.getUniformAttributes().keySet(), programID));
            }

            program.setProjectionMatrixLocation(GL20.glGetUniformLocation(programID, "projectionMatrix"));
            program.setViewMatrixLocation(GL20.glGetUniformLocation(programID, "viewMatrix"));
            program.setModelMatrixLocation(GL20.glGetUniformLocation(programID, "modelMatrix"));

            nameProgramMap.put(program.getRenderProcessName(), program);
        }
    }

    private List getVertexAttributeLocations(List<Attribute> attributes, int programID){
        for (Attribute attribute : attributes){
            attribute.setAttributeLocation(GL20.glGetAttribLocation(programID, attribute.getAttributeName()));
        }

        return attributes;
    }

    private Map getUniformAttributeLocations(Set<String> attributes, int programID){
        Map<String, Integer> attributeLocations = new HashMap<String, Integer>();
        for(String attribute : attributes){
            attributeLocations.put(attribute, GL20.glGetUniformLocation(programID, attribute));
        }

        return attributeLocations;
    }

    private static int loadShader(String filename, int type) {
        return loadShader(ShaderProgramManager.class.getResource(filename), type);
    }

    private static int loadShader(URL filename, int type) {
        StringBuilder shaderSource = new StringBuilder();
        int shaderID = -1;
        try
        {
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(filename.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Could not read file.");
            e.printStackTrace();
            System.exit(-1);
        }

        shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);

        int error = GL11.glGetError();

        if(error != 0){
            System.err.println("some gl error " + error);
        }

        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) != GL11.GL_TRUE)
            System.err.println(GL20.glGetShaderInfoLog(shaderID, 1024));
        if (GL11.glGetError() != 0) {
            System.err.println("shader error");
        }
        return shaderID;
    }

    private static boolean createProgram(int programID, int vertexShader, int fragmentShader) {
        GL20.glAttachShader(programID, vertexShader);
        GL20.glAttachShader(programID, fragmentShader);
        GL20.glLinkProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetProgramInfoLog(programID, 1024));
            throw new RuntimeException("Link failed");
        }

        return true;
    }
}
