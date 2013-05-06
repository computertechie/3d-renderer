package tk.snowmew.cubes;

import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:33 PM
 * Project: Cubes
 */
public abstract class Light {
    protected byte lightColorR, lightColorG, lightColorB;
    protected Vector3f position;
    protected static int sizeOf;

    public Light(byte lCR, byte lCG, byte lCB, Vector3f pos){
        lightColorR = lCR;
        lightColorB = lCB;
        lightColorG = lCG;
        position = pos;
        sizeOf = 4;
    }

    public Light(){

    }

    public void setPosition(Vector3f pos){
        position = pos;
    }

    public Vector3f getPosition(){
        return position;
    }

    public void getLightAsFloatBuffer(FloatBuffer buffer){
        int temp = lightColorR << 24 | lightColorG << 16 | lightColorB << 8;
        buffer.put((float)temp);
        position.store(buffer);
    }

    public int getSizeOf(){
        return sizeOf;
    }
}
