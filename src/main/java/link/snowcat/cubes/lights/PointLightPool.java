package link.snowcat.cubes.lights;

/**
 * User: Pepper
 * Date: 5/6/13
 * Time: 9:20 AM
 * Project: Cubes
 */


public class PointLightPool extends LightPool {

    public PointLightPool(int initialSize){
        super(initialSize);
    }

    @Override
    public int getPoolSizeInFloat() {
        return lights.size()*PointLight.sizeOf;
    }
}
