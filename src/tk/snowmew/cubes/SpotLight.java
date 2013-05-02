package tk.snowmew.cubes;

import org.lwjgl.util.vector.Vector3f;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */
public class SpotLight extends Light {
    Vector3f direction;
    float halfAngle;

    public SpotLight(Vector3f dir, Vector3f pos, float range, float angle, byte lCR, byte lCG, byte lCB){
        super(lCR, lCG, lCB, pos, range);
        direction = dir;
        halfAngle = angle;
    }
}
