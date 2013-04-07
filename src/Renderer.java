import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public class Renderer{
    private static Renderer instance = new Renderer();
    private int currentProgram = -1;
    Matrix4f projectionMatrix = new Matrix4f();
    int projUniformLoc;
    int viewUniformLoc;
    static int posAttributeLoc;
    CameraQuat camera;

    private Renderer(){
        createProjectionMatrix();
    }

    public void setCamera(CameraQuat cam) {
        camera = cam;
    }

    public void createProjectionMatrix(){
        projectionMatrix = new Matrix4f();
        float fieldOfView = 60.0F;
        float aspectRatio = Cubes.WIDTH/Cubes.HEIGHT;
        float near_plane = 0.1F;
        float far_plane = 100.0F;

        float y_scale = coTangent(degreesToRadians(fieldOfView / 2.0F));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = far_plane - near_plane;

        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = (-((far_plane + near_plane) / frustum_length));
        projectionMatrix.m23 = -1.0F;
        projectionMatrix.m32 = (-(2.0F * near_plane * far_plane / frustum_length));
        projectionMatrix.m33 = 0.0F;
    }

    public static Renderer getInstance(){
        return instance;
    }

    public static int loadShader(String filename, int type) {
        StringBuilder shaderSource = new StringBuilder();
        int shaderID = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
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
        GL20.glBindAttribLocation(programID, 0, "position");
        GL20.glLinkProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetProgramInfoLog(programID, 1024));
            throw new RuntimeException("Link failed");
        }

        GL20.glValidateProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE)
            throw new RuntimeException("Validate failed");
    }

    public void render(Model model) {
        if (currentProgram != model.programID)
            useProgram(model.programID);
        bufferUniforms();
        model.bufferUniforms();
        camera.bufferUniforms(viewUniformLoc);
        model.render();
    }

    public float coTangent(float tan){
        return (float)(1.0D / Math.tan(tan));
    }

    public void bufferUniforms() {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        projectionMatrix.store(buffer);
        buffer.flip();
        GL20.glUniformMatrix4(projUniformLoc, false, buffer);
    }

    public void useProgram(int programID) {
        currentProgram = programID;
        GL20.glUseProgram(currentProgram);
        projUniformLoc = GL20.glGetUniformLocation(programID, "projectionMatrix");
        viewUniformLoc = GL20.glGetUniformLocation(programID, "viewMatrix");
        posAttributeLoc = GL20.glGetAttribLocation(programID, "position");
    }

    public static float degreesToRadians(float degrees) {
        return (float)(Math.PI/180) * degrees;
    }
}