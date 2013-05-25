package tk.snowmew.cubes.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.IntBuffer;

/**
 * User: Pepper
 * Date: 5/4/13
 * Time: 7:10 PM
 * Project: Cubes
 */


public class GBuffer {
    private int FBO, depth, fin, width, height;
    private int[] textures = new int[3];

    public GBuffer(int width, int height){
        this.width = width;
        this.height = height;
        for(int i =0 ; i<textures.length; i++)
            textures[i] = GL11.glGenTextures();
        depth = GL11.glGenTextures();
        fin = GL11.glGenTextures();
        FBO = GL30.glGenFramebuffers();
        resizeBuffer(width, height);
    }

    public void resizeBuffer(int w, int h){
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, FBO);
        width = w;
        height = h;
        for(int i = 0; i<textures.length; i++){
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[i]);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA32F, width, height, 0, GL11.GL_RGB, GL11.GL_FLOAT, 0L);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0 + i, GL11.GL_TEXTURE_2D, textures[i], 0);
        }

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, depth);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D,0, GL30.GL_DEPTH32F_STENCIL8, width, height, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, 0);
        GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_DEPTH_STENCIL_ATTACHMENT, GL11.GL_TEXTURE_2D, depth, 0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, fin);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGB, GL11.GL_FLOAT, 0);
        GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT4, GL11.GL_TEXTURE_2D, fin, 0);

        int status = GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER);
        if(status != GL30.GL_FRAMEBUFFER_COMPLETE)
            System.out.println("FrameBuffer error: "+status);

        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
    }

    public void startFrame(){
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, FBO);
        GL20.glDrawBuffers(GL30.GL_COLOR_ATTACHMENT4);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void bindForLightPass(){

    }

    public void bindForGeometryPass(){
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, FBO);
        IntBuffer buff = BufferUtils.createIntBuffer(textures.length);
        for(int i = 0; i<textures.length; i++)
            buff.put(GL30.GL_COLOR_ATTACHMENT0+i);
        GL20.glDrawBuffers(buff);
    }
}
