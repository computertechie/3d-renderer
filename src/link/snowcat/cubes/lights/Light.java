package link.snowcat.cubes.lights;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:33 PM
 * Project: Cubes
 */
public abstract class Light {
    protected Vector3f position, color;
    protected static int sizeOf;
    protected float intensity;

    public Light(Vector3f pos, Vector3f col, float intens){
        position = pos;
        color = col;
        intensity = intens;
        sizeOf = 7;
    }

    public Light(){

    }

    public void setPosition(Vector3f pos){
        position = pos;
    }

    public void setColor(Vector3f color){
        this.color = color;
    }

    public Vector3f getPosition(){
        return position;
    }

    public void getLightAsFloatBuffer(FloatBuffer buffer){
        color.store(buffer);
        position.store(buffer);
    }

    public static int getSizeOf(){
        return sizeOf;
    }

    public FloatBuffer getColorAsFBuffer(){
        FloatBuffer temp = BufferUtils.createFloatBuffer(3);
        color.store(temp);
        temp.flip();
        return temp;
    }

    public FloatBuffer getPositionAsFBuffer(){
        FloatBuffer temp = BufferUtils.createFloatBuffer(3);
        position.store(temp);
        temp.flip();
        return temp;
    }

    public float getIntensity(){
        return intensity;
    }

    public Vector3f getColor(){
        return color;
    }
}
