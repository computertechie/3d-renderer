package link.snowcat.cubes.render;

import link.snowcat.cubes.lights.Light;
import link.snowcat.cubes.lights.SpotLight;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
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
        GL11.glDepthMask(true);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        geometryPass = true;
    }

    public void endGeometryPass(){
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        geometryPass = false;
    }

    public void beginLightPasses(){
        GL11.glEnable(GL11.GL_BLEND);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        cubeInstance.gBuffer.bindForRead();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void directionalLightPass(){
        shaderProgramManager.bindProgram("deferred_directional");
        cubeInstance.sun.bufferForLighting(shaderProgramManager.getCurrentProgram());

        //gbuffer textures
        int textureUnit = 0;
        for(String bufferTexture : cubeInstance.gBuffer.getTextures()){
            if(bufferTexture.equals("depth")){
                continue;
            }
            GL20.glUniform1i(shaderProgramManager.getCurrentProgram().getDeferredTextures().get(bufferTexture), textureUnit);
            textureUnit++;
        }
        //eye position
        GL20.glUniform3f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("eyePosition"), camera.getPosition().getX(), camera.getPosition().getY(), camera.getPosition().getZ());
        //specular intensity
        GL20.glUniform1f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("specularIntensity"), 0.5f);
        //specular power
        GL20.glUniform1f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("specularPower"), 0.5f);

        Matrix4f modelMatrix = new Matrix4f();
        Matrix4f.setIdentity(modelMatrix);
        //fullscreen quad for lighting fragment
        render("quad", modelMatrix);
    }

    public void pointLightPass(Light pointLight){
        shaderProgramManager.bindProgram("deferred_point");
        pointLight.bufferForLighting(shaderProgramManager.getCurrentProgram());

        //gbuffer textures
        int textureUnit = 0;
        for(String bufferTexture : cubeInstance.gBuffer.getTextures()){
            if(bufferTexture.equals("depth")){
                continue;
            }
            GL20.glUniform1i(shaderProgramManager.getCurrentProgram().getDeferredTextures().get(bufferTexture), textureUnit);
            textureUnit++;
        }
        //eye position
        GL20.glUniform3f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("eyePosition"), camera.getPosition().getX(), camera.getPosition().getY(), camera.getPosition().getZ());
        //specular intensity
        GL20.glUniform1f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("specularIntensity"), 0.5f);
        //specular power
        GL20.glUniform1f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("specularPower"), 0.5f);

        Matrix4f modelMatrix = new Matrix4f();
        Matrix4f.setIdentity(modelMatrix);
        //fullscreen quad for lighting fragment
        render("quad", modelMatrix);
    }

    public void spotLightPass(SpotLight spotLight) {
        shaderProgramManager.bindProgram("deferred_spot");
        spotLight.bufferForLighting(shaderProgramManager.getCurrentProgram());

        //gbuffer textures
        int textureUnit = 0;
        for(String bufferTexture : cubeInstance.gBuffer.getTextures()){
            if(bufferTexture.equals("depth")){
                continue;
            }
            GL20.glUniform1i(shaderProgramManager.getCurrentProgram().getDeferredTextures().get(bufferTexture), textureUnit);
            textureUnit++;
        }
        //eye position
        GL20.glUniform3f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("eyePosition"), camera.getPosition().getX(), camera.getPosition().getY(), camera.getPosition().getZ());
        //specular intensity
        GL20.glUniform1f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("specularIntensity"), 0.5f);
        //specular power
        GL20.glUniform1f(shaderProgramManager.getCurrentProgram().getUniformAttributes().get("specularPower"), 0.5f);

        Matrix4f modelMatrix = new Matrix4f();
        Matrix4f.setIdentity(modelMatrix);
        //fullscreen quad for lighting fragment
        render("quad", modelMatrix);
    }

    public void render(String modelName, Matrix4f modelMatrix) {
        renderModel = ModelManager.getInstance().getModel(modelName);

        if(geometryPass) {
            shaderProgramManager.bindProgram(renderModel.getProgramName());
            projUniformLoc = shaderProgramManager.getShaderProgram(renderModel.getProgramName()).getProjectionMatrixLocation();

            renderModel.setModelMatrix(modelMatrix);
            renderModel.bufferUniforms();
            bufferUniforms();
            camera.bufferUniforms(shaderProgramManager.getShaderProgram(renderModel.getProgramName()).getViewMatrixLocation());
        }

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