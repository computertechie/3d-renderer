package tk.snowmew.cubes;

import org.lwjgl.util.vector.Vector3f;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:33 PM
 * Project: Cubes
 */
public abstract class Light {
    protected byte lightColorR, lightColorG, lightColorB;
    protected float range;
    protected Vector3f position;

    public Light(byte lCR, byte lCG, byte lCB, Vector3f pos, float ran){
        lightColorR = lCR;
        lightColorB = lCB;
        lightColorG = lCG;
        position = pos;
        range = ran;
    }

    public Light(){

    }

    public void setPosition(Vector3f pos){
        position = pos;
    }

    public void setRange(float ran){
        range = ran;
    }
}
