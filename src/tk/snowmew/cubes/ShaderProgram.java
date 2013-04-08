package tk.snowmew.cubes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * User: Pepper
 * Date: 3/30/13
 * Time: 7:12 PM
 * Project: tk.snowmew.cubes.Cubes
 */
public class ShaderProgram {
    private int vertexShaderID, fragmentShaderID, programID;

    private int projectionMatrixLocation, viewMatrixLocation, modelMatrixLocation;

    private Map<String,Integer> vertexAttributes, uniformAttributes;
    public ShaderProgram(String vertFile, String fragFile, String[] vertAtts, String[] uniforms){
        vertexShaderID = loadShader(vertFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        createProgram(programID, vertexShaderID, fragmentShaderID);
        getUniformLocs(uniforms);
        getVertAttLocs(vertAtts);
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

    public void getVertAttLocs(String[] atts){
        for (int i = 0; i<atts.length;i++){
            vertexAttributes.put(atts[i],GL20.glGetAttribLocation(programID, atts[i]));
        }
    }

    public void getUniformLocs(String[] atts){
        for(int i = 0; i<atts.length; i++){
            uniformAttributes.put(atts[i],GL20.glGetUniformLocation(programID, atts[i]));
        }
        projectionMatrixLocation = GL20.glGetUniformLocation(programID, "projectionMatrix");
        viewMatrixLocation = GL20.glGetUniformLocation(programID, "viewMatrix");
        modelMatrixLocation = GL20.glGetUniformLocation(programID, "modelMatrix");
    }

    public static int loadShader(String filename, int type) {
        StringBuilder shaderSource = new StringBuilder();
        int shaderID = 0;
        try
        {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filename));
//            reader = new BufferedReader(new InputStreamReader(instance.getClass().getClassLoader().getResourceAsStream(filename)));
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

