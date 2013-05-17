package tk.snowmew.cubes;

import org.lwjgl.util.vector.Vector3f;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */

public class DirectionalLight extends Light {

    public DirectionalLight(Vector3f dir, Vector3f col, float intensity){
        super(dir, col, intensity);
    }

    public Vector3f getDirection(){
        return position;
    }

    public void setDirection(Vector3f direction){
        super.setPosition(direction);
    }
}
