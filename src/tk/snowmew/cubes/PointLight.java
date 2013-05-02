package tk.snowmew.cubes;

import org.lwjgl.util.vector.Vector3f;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */
public class PointLight extends Light {

    public PointLight(byte red, byte green, byte blue, Vector3f pos, float range){
        super(red, green, blue, pos, range);
    }
}
