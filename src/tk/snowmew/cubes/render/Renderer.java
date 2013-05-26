package tk.snowmew.cubes.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import tk.snowmew.cubes.Cubes;

import java.nio.FloatBuffer;

public class Renderer{
    private static Renderer instance = new Renderer();
    private int currentProgram = -1, projUniformLoc = -1;
    Matrix4f projectionMatrix = new Matrix4f();
    Camera camera;
    TextureManager textureManager;
    Cubes cubeInstance;

    private Renderer(){
//        createProjectionMatrix();
    }

    public void setCubeInstance(Cubes cube){
        cubeInstance = cube;
    }

    public void setTextureManager(TextureManager manager){
        textureManager = manager;
    }

    public void setCamera(Camera cam) {
        camera = cam;
    }

    public  void createProjectionMatrix(){
        projectionMatrix = new Matrix4f();
        float fieldOfView = 60.0F;
        float aspectRatio = cubeInstance.getWidth()/cubeInstance.getHeight();
        float near_plane = 0.1F;
        float far_plane = 1000.0F;

        float y_scale = coTangent(degreesToRadians(fieldOfView / 2.0F));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = far_plane - near_plane;

        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2.0F * near_plane * far_plane )/ frustum_length);
        projectionMatrix.m33 = 0;
    }

    public static Renderer getInstance(){
        return instance;
    }

    public void render(Model model) {
        if (currentProgram != model.getShaderProgram().getProgramID())
            useProgram(model.getShaderProgram().getProgramID());
        projUniformLoc = model.getShaderProgram().getProjectionMatrixLocation();
        model.bufferUniforms();
        bufferUniforms();
        bufferDirLight(model);
        camera.bufferUniforms(model.getShaderProgram().getViewMatrixLocation());
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

    public void bufferDirLight(Model model){

        GL20.glUniform3(model.getShaderProgram().getDirectionalLightColorLocation(), cubeInstance.sun.getColorAsFBuffer());
        GL20.glUniform3(model.getShaderProgram().getDirectionalLightPositionLocation(), cubeInstance.sun.getPositionAsFBuffer());
        GL20.glUniform1f(model.getShaderProgram().getDirectionalLightIntensityLocation(), cubeInstance.sun.getIntensity());
    }

    public void useProgram(int programID) {
        currentProgram = programID;
        GL20.glUseProgram(currentProgram);
    }

    public static float degreesToRadians(float degrees) {
        return (float)(Math.PI/180) * degrees;
    }
}