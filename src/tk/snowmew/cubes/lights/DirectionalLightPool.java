package tk.snowmew.cubes.lights;

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
    public int getPoolSizeInFloat() {
        return lights.size()*DirectionalLight.getSizeOf();
    }
}
