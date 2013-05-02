package tk.snowmew.cubes;

import org.lwjgl.util.vector.Vector3f;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */

public class DirectionalLight extends Light {
    Vector3f direction;

    public DirectionalLight(Vector3f dir){

        direction = dir;
    }

    public Vector3f getDirection(){
        return direction;
    }
}
