package tk.snowmew.cubes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * User: Pepper
 * Date: 4/24/13
 * Time: 9:14 PM
 * Project: Cubes
 */
public class MtlFileParser {
    private BufferedReader bufferedReader;
    private ArrayList<Float> diffuse;
    private ArrayList<Float> specular;
    private ArrayList<Float> ambient;
    private ArrayList<Float> filter;
    private float specularPower, transparency, refraction;
    private int illumModel;
    private Material currentMaterial;

    public MtlFileParser(String lib){
        try {
            bufferedReader = new BufferedReader(new FileReader(lib));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parseFile(){

    }
}
