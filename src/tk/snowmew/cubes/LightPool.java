package tk.snowmew.cubes;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.List;

/**
 * User: Pepper
 * Date: 5/3/13
 * Time: 3:34 PM
 * Project: Cubes
 */

public abstract class LightPool {
    protected List<Light> lights;
    protected boolean modified = false;

    public LightPool(int initialSize){

    }

    public void clearPool(){
        lights.clear();
    }

    public boolean isPoolModified(){
        return modified;
    }

    public void addLight(){
        modified = true;
    }

    public void newFrame(){
        modified = false;
    }

    public FloatBuffer getPoolAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(getPoolSizeInFloat());
        for(Light light : lights){
            light.getLightAsFloatBuffer(buffer);
        }
        return buffer;
    }

    public abstract int getPoolSizeInFloat();
}