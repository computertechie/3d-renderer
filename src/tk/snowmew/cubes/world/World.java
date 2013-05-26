package tk.snowmew.cubes.world;

import tk.snowmew.cubes.entities.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Pepper
 * Date: 5/25/13
 * Time: 6:12 PM
 * Project: Cubes
 */


public class World {
    private List<Entity> entityList;

    public World(){
        entityList = new ArrayList<Entity>();
    }

}
