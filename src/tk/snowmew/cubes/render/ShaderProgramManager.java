package tk.snowmew.cubes.render;

import org.lwjgl.opengl.GL20;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Pepper
 * Date: 5/25/13
 * Time: 8:36 PM
 * Project: Cubes
 */


public class ShaderProgramManager {
    private static ShaderProgramManager instance = new ShaderProgramManager();
    private Map<String, ShaderProgram> nameProgramMap;
    private String currentProgram="";

    private ShaderProgramManager(){
        nameProgramMap = new HashMap<String,ShaderProgram>();
    }

    public static ShaderProgramManager getInstance(){
        return instance;
    }

    public void registerProgram(String name, ShaderProgram program){
        if(nameProgramMap.containsKey(name))
            return;
        nameProgramMap.put(name,program);
    }

    public void bindProgram(String name){
        if(currentProgram.equals(name)){
            return;
        }
        else{
            currentProgram = name;
            GL20.glUseProgram(nameProgramMap.get(name).getProgramID());
        }
    }

    public ShaderProgram getShaderProgram(String name){
        return nameProgramMap.get(name);
    }
}
