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
    private ArrayList<Mesh> meshGroups = new ArrayList<Mesh>();
    private ArrayList<ArrayList<Float>> vertCoords = new ArrayList<ArrayList <Float>>();
    private ArrayList<ArrayList<Float>> textureCoords = new ArrayList<ArrayList<Float>>();
    private ArrayList<ArrayList<Float>> normals = new ArrayList<ArrayList<Float>>();
    private ArrayList<ArrayList<Integer>> indexes = new ArrayList<ArrayList<Integer>>();
    private BufferedReader bufferedFileReader;
    private static final int VERT_LINE = 0, TEX_LINE = 1, NORM_LINE = 2, FACE_LINE = 3, PARAM_LINE = 4;

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
                    switch(line.charAt(1)){
                        case 't':
                            parseLine(line, TEX_LINE);
                            break;
                        case 'p':
                            parseLine(line, PARAM_LINE);
                            break;
                        default:
                            parseLine(line, VERT_LINE);
                            break;
                    }
                    break;
                case 'g':
                    endGroup();
                    startGroup();
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

    public void endGroup(){
        ArrayList<Vertex> tempVertList = new ArrayList<Vertex>();
        Vertex vertex;
        Mesh mesh;
        for(ArrayList<Integer> list : indexes){
            vertex = new Vertex();
            vertex.setVertexes(vertCoords.get(list.get(0)));
            vertex.setTextures(textureCoords.get(list.get(1)));
            vertex.setNormals(normals.get(list.get(2)));
            tempVertList.add(vertex);
        }
        mesh = new Mesh(tempVertList);
        meshGroups.add(mesh);
    }

    public void startGroup(){
        vertCoords = new ArrayList<ArrayList<Float>>();
        textureCoords = new ArrayList<ArrayList<Float>>();
        normals = new ArrayList<ArrayList<Float>>();
        indexes = new ArrayList<ArrayList<Integer>>();
    }

    public ArrayList<Mesh> getMeshes(){
        return meshGroups;
    }

    public void parseSmoothing(String line){

    }

    public void parseLine(String line, int lineType){
        String[] coords = line.trim().split(" ");
        ArrayList<Float> coordList = new ArrayList<Float>();
        for(int i = 1; i<coords.length; i++)
            coordList.add(Float.parseFloat(coords[i]));
        switch(lineType){
            case VERT_LINE:
                vertCoords.add(coordList);
                break;
            case TEX_LINE:
                textureCoords.add(coordList);
                break;
            case NORM_LINE:
                normals.add(coordList);
                break;
        }
    }

    public void parseFaces(String line){
        String[] dataIndexes = line.trim().split(" ");
        ArrayList<Integer> tempList;
        for(int i = 1; i<dataIndexes.length; i++){
            tempList = new ArrayList<Integer>();
            String[] attribIndexes = dataIndexes[i].trim().split("/");
            switch(attribIndexes.length){
                case 1:
                    tempList.add(Integer.parseInt(attribIndexes[0]));
                    tempList.add(null);
                    tempList.add(null);
                    break;
                case 2:
                    tempList.add(Integer.parseInt(attribIndexes[0]));
                    tempList.add(Integer.parseInt(attribIndexes[1]));
                    tempList.add(null);
                    break;
                case 3:
                    tempList.add(Integer.parseInt(attribIndexes[0]));
                    tempList.add(attribIndexes[1] == "" ? null : Integer.parseInt(attribIndexes[1]));
                    tempList.add(Integer.parseInt(attribIndexes[2]));
                    break;
            }
            indexes.add(tempList);
        }
    }
}
