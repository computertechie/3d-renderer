package tk.snowmew.cubes.lights;

import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */
public class SpotLight extends Light {
    Vector3f direction;
    float halfAngle, range;

    public SpotLight(Vector3f pos, Vector3f dir, Vector3f col, float range, float angle, float intensity){
        super(pos, col, intensity);
        direction = dir;
        halfAngle = angle;
        this.range = range;
        sizeOf = 9;
    }

    public void getLightAsFloatBuffer(FloatBuffer buffer){
        super.getLightAsFloatBuffer(buffer);
        direction.store(buffer);
        buffer.put(halfAngle);
        buffer.put(range);
    }
}
