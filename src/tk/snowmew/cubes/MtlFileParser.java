package tk.snowmew.cubes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private static final int DIFFUSE_LINE = 0, AMBIENT_LINE = 1, SPECULAR_COLOR_LINE = 2, REFRACTION_LINE = 3, DISSOLVE_LINE = 4, SPECULAR_POWER_LINE = 5, TRANS_FILTER_LINE = 6, ILLUM_MODE_LINE = 7;
    private boolean firstMaterial = true;

    public MtlFileParser(String lib){
        try {
            bufferedReader = new BufferedReader(new FileReader("assets/models/"+lib));
            parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseFile() throws IOException {
        String line;
        while((line = bufferedReader.readLine())!=null){
            if(line.equals(""))
                continue;
            switch(line.charAt(0)){
                case 'K':
                    switch(line.charAt(1)){
                        case 'a':
                            parseLine(line, AMBIENT_LINE);
                            break;
                        case 's':
                            parseLine(line, SPECULAR_COLOR_LINE);
                            break;
                        case 'd':
                            parseLine(line, DIFFUSE_LINE);
                            break;
                    }
                    break;
                case 'N':
                    switch(line.charAt(1)){
                        case 'i':
                            parseLine(line, REFRACTION_LINE);
                            break;
                        case 's':
                            parseLine(line, SPECULAR_POWER_LINE);
                            break;
                    }
                    break;
                case 'n':
                    if(firstMaterial){
                        firstMaterial = false;
                        startMaterial(line);
                        continue;
                    }
                    endMaterial();
                    startMaterial(line);
                    break;
                case 'i':
                    parseLine(line, ILLUM_MODE_LINE);
                    break;
            }
        }
        endMaterial();
    }

    public void endMaterial(){
        System.out.println("material end");
        MaterialManager.getInstance().addMaterial(currentMaterial);
    }

    public void startMaterial(String line){
        System.out.println("material start");
        currentMaterial = new Material();
        currentMaterial.setName(line.trim().split(" ")[1]);
        System.out.println(currentMaterial.getName());
    }

    public void parseLine(String line, int type){
//        System.out.println("parseLine "+line+" "+type);
        String[] data = line.trim().split(" ");
        float[] dataAsFloat = new float[data.length-1];
        for(int i = 1; i < data.length; i++)
            dataAsFloat[i-1] = Float.parseFloat(data[i]);
        switch(type){
            case AMBIENT_LINE:
                currentMaterial.setAmbient(dataAsFloat[0],dataAsFloat[1],dataAsFloat[2]);
                break;
            case DIFFUSE_LINE:
                currentMaterial.setDiffuse(dataAsFloat[0], dataAsFloat[1], dataAsFloat[2]);
                break;
            case SPECULAR_COLOR_LINE:
                currentMaterial.setSpecular(dataAsFloat[0], dataAsFloat[1], dataAsFloat[2]);
                break;
            case SPECULAR_POWER_LINE:
                currentMaterial.setSpecPower(dataAsFloat[0]);
                break;
            case DISSOLVE_LINE:
                break;
            case REFRACTION_LINE:
                currentMaterial.setRefraction(dataAsFloat[0]);
                break;
            case TRANS_FILTER_LINE:
                currentMaterial.setTransFilter(dataAsFloat[0], dataAsFloat[1], dataAsFloat[2]);
                break;
            case ILLUM_MODE_LINE:
                currentMaterial.setIllumModel((int)dataAsFloat[0]);

        }
    }
}
