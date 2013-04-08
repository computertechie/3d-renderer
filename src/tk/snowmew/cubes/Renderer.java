package tk.snowmew.cubes;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

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
        projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2.0F * near_plane * far_plane )/ frustum_length);
        projectionMatrix.m33 = 0;
    }

    public static Renderer getInstance(){
        return instance;
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