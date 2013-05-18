package tk.snowmew.cubes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Pepper
 * Date: 4/24/13
 * Time: 4:14 PM
 * Project: Cubes
 */

public class MaterialManager {
    private static MaterialManager materialManager = new MaterialManager();
    private Map<String,Material> nameToMaterial = new HashMap<String, Material>();
    private List<String> registeredMaterialLibraries = new ArrayList<String>();

    private MaterialManager(){

    }

    public static MaterialManager getInstance(){
        return materialManager;
    }

    public boolean addMaterial(Material material){
        if(!nameToMaterial.containsKey(material.getName())){
            nameToMaterial.put(material.getName(), material);
            return true;
        }
        return false;
    }

    public boolean isMaterialLibraryRegistered(String name){
        return registeredMaterialLibraries.contains(name);
    }

    public void registerMaterialLibrary(String library){
        registeredMaterialLibraries.add(library);
    }

    public Material getMaterialFromName(String name){
        return nameToMaterial.get(name);
    }
}
