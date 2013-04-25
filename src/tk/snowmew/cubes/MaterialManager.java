package tk.snowmew.cubes;

import java.util.HashMap;

/**
 * User: Pepper
 * Date: 4/24/13
 * Time: 4:14 PM
 * Project: Cubes
 */
public class MaterialManager {
    private static MaterialManager materialManager = new MaterialManager();
    private HashMap<String,Material> nameToMaterial = new HashMap<String, Material>();

    private MaterialManager(){

    }

    public boolean addMaterial(Material material){
        if(!nameToMaterial.containsKey(material.getName())){
            nameToMaterial.put(material.getName(), material);
            return true;
        }
        return false;
    }

    public Material getMaterialFromName(String name){
        return nameToMaterial.get(name);
    }
}
