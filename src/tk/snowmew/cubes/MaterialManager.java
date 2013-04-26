package tk.snowmew.cubes;

import java.util.ArrayList;
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
    private ArrayList<String> registeredMaterialLibraries = new ArrayList<String>();

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
        new MtlFileParser(library);
    }

    public Material getMaterialFromName(String name){
        return nameToMaterial.get(name);
    }
}
