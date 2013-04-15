package tk.snowmew.cubes;

import java.io.*;
import java.util.ArrayList;

/**
 * User: Pepper
 * Date: 4/14/13
 * Time: 7:47 PM
 * Project: Cubes
 */
public class ObjFileParser {
    private ArrayList<ArrayList<Vertex>> meshGroups = new ArrayList<ArrayList<Vertex>>();
    private ArrayList<Vertex> vertexes = new ArrayList<Vertex>();
    private ArrayList<ArrayList<Float>> rawVertexes = new ArrayList<ArrayList <Float>>();
    private ArrayList<ArrayList<Float>> textureCoords = new ArrayList<ArrayList<Float>>();
    private ArrayList<ArrayList<Float>> normals = new ArrayList<ArrayList<Float>>();
    private ArrayList<Integer> indexes = new ArrayList<Integer>();
    private BufferedReader bufferedFileReader;

    public ObjFileParser(String fileName){
        try {
            bufferedFileReader = new BufferedReader(new FileReader(fileName));
            parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjFileParser(File file){
        try {
            bufferedFileReader = new BufferedReader(new FileReader(file));
            parseFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseFile() throws IOException {
        String line;
        while((line = bufferedFileReader.readLine()) != null){
            switch(line.charAt(0)){
                case '#':
                    continue;
                case 'v':
                    if(line.charAt(1)=='t')
                        parseTexture(line);
                    else if(line.charAt(1)=='p')
                        parseParameterVerts(line);
                    else
                        parseVertex(line);
                    break;
                case 'g':
                    endGroup();
                    startGroup();
                    break;
                case 'o':
                    endObject();
                    startObject();
                    break;
                case 'f':
                    parseFaces(line);
                    break;
                case 's':
                    parseSmoothing(line);
                    break;
                default:
                    continue;
            }
        }
    }

    public void parseSmoothing(String line){

    }

    public void parseVertex(String line){
        String[] coords = line.trim().split(" ");
        ArrayList<Float> coordList = new ArrayList<Float>();
    }

    public void parseTexture(String line){

    }

    public void parseNormals(String line){

    }

    public void parseParameterVerts(String line){

    }

    public void parseFaces(String line){

    }
}
