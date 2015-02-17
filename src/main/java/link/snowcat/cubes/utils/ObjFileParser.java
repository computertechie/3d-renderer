package link.snowcat.cubes.utils;

import link.snowcat.cubes.render.MaterialManager;
import link.snowcat.cubes.render.Mesh;
import link.snowcat.cubes.render.Vertex;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Pepper
 * Date: 4/14/13
 * Time: 7:47 PM
 * Project: Cubes
 */

public class ObjFileParser {
    private List<List<Float>> vertCoords = new ArrayList<List <Float>>();
    private List<List<Float>> textureCoords = new ArrayList<List<Float>>();
    private List<List<Float>> normals = new ArrayList<List<Float>>();
    private List<List<Integer>> indexes = new ArrayList<List<Integer>>();

    private Map<String,Mesh> materialMeshMap = new HashMap<String, Mesh>();
    private String material;

    private BufferedReader bufferedFileReader;

    private static final int VERT_LINE = 0, TEX_LINE = 1, NORM_LINE = 2, PARAM_LINE = 3;
    private boolean firstGroup = true;

    private int totalVertexCount=0, tempVertexCount = 0, totalTextureCount=0, tempTextureCount = 0, totalNormalCount = 0, tempNormalCount = 0;
    private String fileBase;

    public ObjFileParser(URL resource){
        try{
            bufferedFileReader = new BufferedReader(new InputStreamReader(resource.openStream()));
            fileBase = resource.toString().substring(0, resource.toString().lastIndexOf('/'));
            parseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseFile() throws IOException {
        String line;
        while((line = bufferedFileReader.readLine()) != null){
            if(line.equals(""))
                continue;
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
                        case 'n':
                            parseLine(line, NORM_LINE);
                            break;
                        default:
                            parseLine(line, VERT_LINE);
                            break;
                    }
                    break;
                case 'g':
                case 'o':
                    if(firstGroup){
                        firstGroup = !firstGroup;
                        startGroup();
                        continue;
                    }
                    endGroup();
                    startGroup();
                    break;
                case 'f':
                    parseFaces(line);
                    break;
                case 's':
                    parseSmoothing(line);
                    break;
                case 'u':
                    String[] words = line.trim().split(" ");
                    material = words[1];
                    break;
                case 'm':
                    String[] com = line.trim().split(" ");
                    if(!MaterialManager.getInstance().isMaterialLibraryRegistered(com[1])){
                        MaterialManager.getInstance().registerMaterialLibrary(com[1]);
                        new MtlFileParser(fileBase,com[1]);
                    }
                    break;
                default:
                    continue;
            }
        }
        endGroup();
    }

    public void endGroup(){
        ArrayList<Vertex> tempVertList = new ArrayList<Vertex>();
        Vertex vertex;
        for(List<Integer> list : indexes){
            vertex = new Vertex();
            if(list.get(0) != Integer.MAX_VALUE)
                vertex.setVertexes(vertCoords.get(list.get(0)-totalVertexCount-1));
            if(list.get(1) != Integer.MAX_VALUE)
                vertex.setTextures(textureCoords.get(list.get(1)-totalTextureCount-1));
            if(list.get(2) != Integer.MAX_VALUE)
                vertex.setNormals(normals.get(list.get(2)-totalNormalCount-1));
            tempVertList.add(vertex);
        }
        totalTextureCount += tempTextureCount;
        totalVertexCount += tempVertexCount;
        totalNormalCount += tempNormalCount;
        if(materialMeshMap.containsKey(material))
            materialMeshMap.get(material).addVertexes(tempVertList);
        else
            materialMeshMap.put(material, new Mesh(tempVertList,material));
    }

    public void startGroup(){
        vertCoords = new ArrayList<List<Float>>();
        textureCoords = new ArrayList<List<Float>>();
        normals = new ArrayList<List<Float>>();
        indexes = new ArrayList<List<Integer>>();
        material = "";
        tempNormalCount = tempTextureCount = tempVertexCount = 0;
    }

    public List<Mesh> getMeshes(){
        return new ArrayList<Mesh>(materialMeshMap.values());
//        return meshes;
    }

    public void parseSmoothing(String line){

    }

    public void parseLine(String line, int lineType){
        String[] coords = line.trim().split(" ");
        ArrayList<Float> coordList = new ArrayList<Float>();
        for(int i = 1; i<coords.length; i++) {
            coordList.add(Float.parseFloat(coords[i]));
        }

        switch(lineType){
            case VERT_LINE:
                vertCoords.add(coordList);
                tempVertexCount++;
                break;
            case TEX_LINE:
                textureCoords.add(coordList);
                tempTextureCount++;
                break;
            case NORM_LINE:
                normals.add(coordList);
                tempNormalCount++;
                break;
        }
    }

    public void parseFaces(String line){
        String[] dataIndexes = line.trim().split(" ");
        ArrayList<Integer> tempList;
        for(int i = 1; i<dataIndexes.length; i++){
            if(dataIndexes[i].equals(""))
                continue;
            tempList = new ArrayList<Integer>();
            String[] attribIndexes = dataIndexes[i].trim().split("/");
            switch(attribIndexes.length){
                case 1:
                    tempList.add(Integer.parseInt(attribIndexes[0]));
                    tempList.add(Integer.MAX_VALUE);
                    tempList.add(Integer.MAX_VALUE);
                    break;
                case 2:
                    tempList.add(Integer.parseInt(attribIndexes[0]));
                    tempList.add(Integer.parseInt(attribIndexes[1]));
                    tempList.add(Integer.MAX_VALUE);
                    break;
                case 3:
                    tempList.add(Integer.parseInt(attribIndexes[0]));
                    tempList.add(attribIndexes[1].equals("") ? Integer.MAX_VALUE : Integer.parseInt(attribIndexes[1]));
                    tempList.add(Integer.parseInt(attribIndexes[2]));
                    break;
            }
            indexes.add(tempList);
        }
    }
}
