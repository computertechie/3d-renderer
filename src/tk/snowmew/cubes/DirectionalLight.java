package tk.snowmew.cubes;

import org.lwjgl.util.vector.Vector3f;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */

public class DirectionalLight extends Light {

    public DirectionalLight(Vector3f dir, byte red, byte green, byte blue){
        super(red, green, blue, dir);
    }

    public Vector3f getDirection(){
        return position;
    }
}
