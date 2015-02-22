package link.snowcat.cubes.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import link.snowcat.cubes.Cubes;

import java.nio.FloatBuffer;

public class Renderer{
    private static Renderer instance = new Renderer();
    private int projUniformLoc = -1;
    private Model renderModel;
    private boolean geometryPass = false;

    Matrix4f projectionMatrix = new Matrix4f();
    Camera camera;
    ShaderProgramManager shaderProgramManager;
    public Cubes cubeInstance;

    private Renderer(){ }

    public void setCubeInstance(Cubes cube){
        cubeInstance = cube;
    }

    public void setShaderProgramManager(ShaderProgramManager manager){
        shaderProgramManager = manager;
    }

    public void setCamera(Camera cam) {
        camera = cam;
    }

    public  void createProjectionMatrix(){
        projectionMatrix = new Matrix4f();
        projectionMatrix.setIdentity();
        float fieldOfView = 60;
        float aspectRatio = (float)cubeInstance.getWidth()/(float)cubeInstance.getHeight();
        float near_plane = 0.1F;
        float far_plane = 500;

        float y_scale = coTangent(degreesToRadians(fieldOfView * 0.5f));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = far_plane - near_plane;

        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((far_plane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((far_plane * near_plane)/ frustum_length);
        projectionMatrix.m33 = 0;
    }

    public static Renderer getInstance(){
        return instance;
    }

    public void beginGeometryPass(){
        geometryPass=true;
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void lightPass(GBuffer gBuffer){
        geometryPass = false;

        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        gBuffer.bindForRead();

        int halfHeight = cubeInstance.getHeight()/2, halfWidth = cubeInstance.getWidth()/2;

        //top left diffuse
        gBuffer.setReadBuffer(0);
        GL30.glBlitFramebuffer(0,0, cubeInstance.getWidth(), cubeInstance.getHeight(), 0, 0, halfWidth, halfHeight, GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR);

        //bottom left UV
        gBuffer.setReadBuffer(1);
        GL30.glBlitFramebuffer(0,0, cubeInstance.getWidth(), cubeInstance.getHeight(), 0, halfHeight, halfWidth, cubeInstance.getHeight(), GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR);

        //bottom right position
        gBuffer.setReadBuffer(2);
        GL30.glBlitFramebuffer(0,0, cubeInstance.getWidth(), cubeInstance.getHeight(), halfWidth, halfHeight, cubeInstance.getWidth(), cubeInstance.getHeight(), GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR);

        //top right normal
        gBuffer.setReadBuffer(3);
        GL30.glBlitFramebuffer(0,0, cubeInstance.getWidth(), cubeInstance.getHeight(), halfWidth, 0, cubeInstance.getWidth(), halfHeight, GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR);
    }

    public void render(String modelName, Matrix4f modelMatrix) {
        renderModel = ModelManager.getInstance().getModel(modelName);
        shaderProgramManager.bindProgram(renderModel.getProgramName());
        projUniformLoc = shaderProgramManager.getShaderProgram(renderModel.getProgramName()).getProjectionMatrixLocation();

        renderModel.setModelMatrix(modelMatrix);
        renderModel.bufferUniforms();
        bufferUniforms();

        if(!geometryPass) {
            cubeInstance.sun.buffer(shaderProgramManager.getShaderProgram(renderModel.getProgramName()));
        }

        camera.bufferUniforms(shaderProgramManager.getShaderProgram(renderModel.getProgramName()).getViewMatrixLocation());
        renderModel.render();
    }

    public void bufferUniforms() {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        projectionMatrix.store(buffer);
        buffer.flip();
        GL20.glUniformMatrix4(projUniformLoc, false, buffer);
    }

    public static float degreesToRadians(float degrees) {
        return (float)(Math.PI/180) * degrees;
    }

    public static float coTangent(float tan){
        return (float)(1.0D / Math.tan(tan));
    }
}