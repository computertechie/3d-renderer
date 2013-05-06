package tk.snowmew.cubes;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 5/4/13
 * Time: 1:46 PM
 * Project: Cubes
 */


public class DirectionalLightPool extends LightPool{

    public DirectionalLightPool(int size){
        super(size);
    }

    @Override
    public FloatBuffer getPoolAsBuffer() {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(getPoolSizeInFloat());
        for(Light light : lights){
            light.getLightAsFloatBuffer(buffer);
        }
        return buffer;
    }

    @Override
    public int getPoolSizeInFloat() {
        return lights.size()*DirectionalLight.sizeOf;
    }
}
