package link.snowcat.cubes.render;

import com.google.gson.Gson;
import link.snowcat.cubes.Cubes;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pepper on 2/20/2015.
 */

public class ModelManager {
    public static ModelManager instance = new ModelManager();
    private final String modelsAssetDir = "/assets/json/models/";
    private final Gson modelGson = new Gson();
    Map<String, Map<String, Model>> shaderNameToModelsMap;
    private ModelManager(){
        shaderNameToModelsMap = new HashMap<>();
    }

    public static ModelManager getInstance(){
        return instance;
    }

    public void loadModel(String modelName){
        for(Map<String, Model> modelNameModel : shaderNameToModelsMap.values()){
            if(modelNameModel.containsKey(modelName)){
                return;
            }
        }

        Model newModel = modelGson.fromJson(new InputStreamReader(Cubes.class.getResourceAsStream(modelsAssetDir + modelName + ".json")), Model.class);
        newModel.initialize();

        if(!shaderNameToModelsMap.containsKey(newModel.getProgramName())){
            Map<String, Model> modelMap = new HashMap<>();
            modelMap.put(modelName, newModel);
            shaderNameToModelsMap.put(newModel.getProgramName(), modelMap);
        }
        else{
            shaderNameToModelsMap.get(newModel.getProgramName()).put(modelName, newModel);
        }
    }

    public Model getModel(String modelName){
        for(Map<String, Model> modelNameModel : shaderNameToModelsMap.values()){
            if(modelNameModel.containsKey(modelName)){
                return modelNameModel.get(modelName);
            }
        }

        return null;
    }
}
