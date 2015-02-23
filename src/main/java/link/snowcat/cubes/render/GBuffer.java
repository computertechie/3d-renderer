package link.snowcat.cubes.render;

import link.snowcat.cubes.Cubes;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Pepper
 * Date: 5/4/13
 * Time: 7:10 PM
 * Project: Cubes
 */


public class GBuffer {
    private int FBO, width, height;
    private String[] textures = new String[]{"positionTexture","normalTexture","colorTexture","depth"};
    private Map<String, Integer> bufferTexturesToIDMap = new HashMap<>();

    public GBuffer(int width, int height){
        this.width = width;
        this.height = height;
        initialize(width, height);
    }

    public void initialize(int w, int h){
        FBO = GL30.glGenFramebuffers();
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, FBO);

        for(int i =0 ; i<textures.length; i++){
            bufferTexturesToIDMap.put(textures[i], GL11.glGenTextures());
        }

        width = w;
        height = h;
        int i = 0;
        for(String key : textures){
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, bufferTexturesToIDMap.get(key));
            if(key.equals("depth")){
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_DEPTH_COMPONENT32F, width, height, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
                GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, bufferTexturesToIDMap.get(key), 0);
            }
            else {
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA32F, width, height, 0, GL11.GL_RGBA, GL11.GL_FLOAT, (ByteBuffer) null);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
                GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, (GL30.GL_COLOR_ATTACHMENT0 + i), GL11.GL_TEXTURE_2D, bufferTexturesToIDMap.get(key), 0);
                i++;
            }
        }

        int[] drawBuffers = new int[]{
                GL30.GL_COLOR_ATTACHMENT0,
                GL30.GL_COLOR_ATTACHMENT1,
                GL30.GL_COLOR_ATTACHMENT2
        };

        IntBuffer drawBuffersBuffer = BufferUtils.createIntBuffer(drawBuffers.length);
        drawBuffersBuffer.put(drawBuffers);
        drawBuffersBuffer.flip();

        GL20.glDrawBuffers(drawBuffersBuffer);

        int status = GL30.glCheckFramebufferStatus(GL30.GL_DRAW_FRAMEBUFFER);
        if(status != GL30.GL_FRAMEBUFFER_COMPLETE)
            System.err.println("FrameBuffer error: "+status);

        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
    }

    public void resize(int width, int height){
        this.width = width;
        this.height = height;

        initialize(width, height);
    }

    public void bindForWrite(){
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, FBO);
    }

    public void bindForRead(){
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
//        GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, FBO);
        Cubes.checkGLError("GBuffer.bindForRead:1");

        int textureIndex = 0;
        for(String key : getTextures()) {
            if(key.equals("depth"))
                continue;
            GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureIndex);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, bufferTexturesToIDMap.get(key));
            textureIndex++;
        }
    }

    public void bindForBlit(){
        GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, FBO);
    }

    public void setReadBuffer(int bufferIndex){
        GL11.glReadBuffer((GL30.GL_COLOR_ATTACHMENT0+bufferIndex));
    }

    public Map<String, Integer> getBufferTexturesToIDMap() {
        return bufferTexturesToIDMap;
    }

    public String[] getTextures() {
        return textures;
    }
}
