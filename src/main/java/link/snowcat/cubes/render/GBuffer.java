package link.snowcat.cubes.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * User: Pepper
 * Date: 5/4/13
 * Time: 7:10 PM
 * Project: Cubes
 */


public class GBuffer {
    private int FBO, depth, width, height;
    private int[] textures = new int[4];

    public GBuffer(int width, int height){
        this.width = width;
        this.height = height;
        initialize(width, height);
    }

    public void initialize(int w, int h){
        FBO = GL30.glGenFramebuffers();
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, FBO);

        for(int i =0 ; i<textures.length; i++)
            textures[i] = GL11.glGenTextures();
        depth = GL11.glGenTextures();

        width = w;
        height = h;


        for(int i = 0; i<textures.length; i++){
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[i]);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA32F, width, height, 0, GL11.GL_RGBA, GL11.GL_FLOAT, (ByteBuffer) null);
            GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, (GL30.GL_COLOR_ATTACHMENT0 + i), GL11.GL_TEXTURE_2D, textures[i], 0);
        }

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, depth);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_DEPTH_COMPONENT32F, width, height, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);
        GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, depth, 0);

        int[] drawBuffers = new int[]{GL30.GL_COLOR_ATTACHMENT0,
                GL30.GL_COLOR_ATTACHMENT1,
                GL30.GL_COLOR_ATTACHMENT2,
                GL30.GL_COLOR_ATTACHMENT3};

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
        GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, FBO);
    }

    public void setReadBuffer(int bufferIndex){
        GL11.glReadBuffer((GL30.GL_COLOR_ATTACHMENT0+bufferIndex));
    }
}
