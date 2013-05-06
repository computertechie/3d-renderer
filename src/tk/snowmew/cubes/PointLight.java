package tk.snowmew.cubes;

import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */
public class PointLight extends Light {
    private float range;

    public PointLight(byte red, byte green, byte blue, Vector3f pos, float range){
        super(red, green, blue, pos);
        this.range = range;
        sizeOf = 5;
    }

    public void getLightAsFloatBuffer(FloatBuffer buffer){
        super.getLightAsFloatBuffer(buffer);
        buffer.put(range);
    }
}
