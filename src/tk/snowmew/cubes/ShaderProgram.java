package tk.snowmew.cubes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Pepper
 * Date: 3/30/13
 * Time: 7:12 PM
 * Project: tk.snowmew.cubes.Cubes
 */

public class ShaderProgram {
    private int vertexShaderID;
    private int fragmentShaderID;
    private int programID;
    private int projectionMatrixLocation, viewMatrixLocation, modelMatrixLocation, directionalLightPositionLocation, directionalLightColorLocation, directionalLightIntensityLocation;
    private Map<String,Integer> vertexAttributes = new HashMap<String, Integer>(), uniformAttributes = new HashMap<String, Integer>();

    public ShaderProgram(String vertFile, String fragFile, String[] vertAtts, String[] uniforms){
        vertexShaderID = loadShader(vertFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        createProgram(programID, vertexShaderID, fragmentShaderID);
        getUniformLocs(uniforms);
        getVertAttLocs(vertAtts);
        getMatrixLocs();
        getDirLightLocs();
    }

    public ShaderProgram(String vertFile, String fragFile, String...vertAtts){
        vertexShaderID = loadShader(vertFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        createProgram(programID, vertexShaderID, fragmentShaderID);
        getVertAttLocs(vertAtts);
        getMatrixLocs();
        getDirLightLocs();
    }

    private void getDirLightLocs(){
        directionalLightColorLocation = GL20.glGetUniformLocation(programID, "dirLight.color");
        directionalLightIntensityLocation = GL20.glGetUniformLocation(programID, "dirLight.intensity");
        directionalLightPositionLocation = GL20.glGetUniformLocation(programID,  "dirLight.position");
    }

    public int getProgramID() {
        return programID;
    }

    public int getProjectionMatrixLocation() {
        return projectionMatrixLocation;
    }

    public int getViewMatrixLocation() {
        return viewMatrixLocation;
    }

    public int getModelMatrixLocation() {
        return modelMatrixLocation;
    }

    public int getDirectionalLightPositionLocation(){
        return directionalLightPositionLocation;
    }

    public int getDirectionalLightColorLocation(){
        return directionalLightColorLocation;
    }

    public int getDirectionalLightIntensityLocation(){
        return directionalLightIntensityLocation;
    }

    private void getVertAttLocs(String[] atts){
        for (int i = 0; i<atts.length;i++){
            vertexAttributes.put(atts[i],GL20.glGetAttribLocation(programID, atts[i]));
        }
    }

    private void getMatrixLocs(){
        projectionMatrixLocation = GL20.glGetUniformLocation(programID, "projectionMatrix");
        viewMatrixLocation = GL20.glGetUniformLocation(programID, "viewMatrix");
        modelMatrixLocation = GL20.glGetUniformLocation(programID, "modelMatrix");
    }

    private void getUniformLocs(String[] atts){
        for(int i = 0; i<atts.length; i++){
            uniformAttributes.put(atts[i],GL20.glGetUniformLocation(programID, atts[i]));
        }
    }

    public static int loadShader(String filename, int type) {
        StringBuilder shaderSource = new StringBuilder();
        int shaderID = 0;
        try
        {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filename));
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

    public static void createProgram(int programID, int vertexShader, int fragmentShader) {
        GL20.glAttachShader(programID, vertexShader);
        GL20.glAttachShader(programID, fragmentShader);
        GL20.glLinkProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetProgramInfoLog(programID, 1024));
            throw new RuntimeException("Link failed");
        }

        GL20.glValidateProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE)
            throw new RuntimeException("Validate failed");
    }

    public int getAttribLocation(String attrib){
        return vertexAttributes.get(attrib);
    }

    public int getUniformLocation(String uniform){
        return uniformAttributes.get(uniform);
    }
}

