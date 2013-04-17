package tk.snowmew.cubes;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * User: Pepper
 * Date: 4/9/13
 * Time: 7:41 PM
 * Project: Cubes
 */
public class Vertex {
    private ArrayList<Float> vertexes = new ArrayList<Float>();
    private ArrayList<Float> normals = new ArrayList<Float>();
    private ArrayList<Float> texCoords = new ArrayList<Float>();
    private int vertOffset = 0, texOffset = 0, normOffset = 0;
    static int stride = 24;
    private int sizeOfFloat = 4, vertElementCount = 4, texElementCount = 2, normalElementCount=0, totalElementCount;

    public Vertex(ArrayList<Float> verts, ArrayList<Float> texes, ArrayList<Float> norms){
        vertexes = verts;
        normals = norms;
        texCoords = texes;
        texOffset = sizeOfFloat*vertElementCount;
        normOffset = sizeOfFloat*texElementCount + texOffset;
        normalElementCount = norms.size();
        totalElementCount = vertElementCount + texElementCount + normalElementCount;
        stride = sizeOfFloat * vertElementCount + sizeOfFloat * texElementCount + sizeOfFloat * normalElementCount;
    }

    public Vertex(ArrayList<Float> verts, ArrayList<Float> texes){
        vertexes = verts;
        texCoords = texes;
        texOffset = sizeOfFloat * vertElementCount;
        totalElementCount = texElementCount + vertElementCount;
        stride = sizeOfFloat * vertElementCount + sizeOfFloat * texElementCount;
    }

    public Vertex(ArrayList<Float> verts){
        vertexes = verts;
        totalElementCount = vertElementCount;
    }

    public Vertex(){

    }

    public FloatBuffer getElementsAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(totalElementCount);
        for(int i = 0; i< vertexes.size(); i++)
            buffer.put(vertexes.get(i));
        if(texCoords.size()>0)
            for(int i = 0; i< texCoords.size(); i++)
                buffer.put(texCoords.get(i));
        if(normals.size() > 0)
            for(int i = 0; i< texCoords.size(); i++)
                buffer.put(normals.get(i));
        buffer.flip();
        return buffer;
    }

    public float[] getElementsAsFloatArray(){
         float[] arr = new float[totalElementCount];

        for(int i = 0; i< vertexes.size(); i++)
            arr[i] = vertexes.get(i);
        if(texCoords.size() > 0)
            for(int i = texOffset; i< texCoords.size(); i++)
                arr[i] = texCoords.get(i);
        if(normals.size() > 0)
            for(int i = normOffset; i<normals.size(); i++)
                arr[i] = normals.get(i);

        return arr;
    }

    public ArrayList<Float> getVertexes(){
        return vertexes;
    }

    public ArrayList<Float> getTexture(){
        return texCoords;
    }

    public void setTextures(ArrayList<Float> list){
        texCoords = list;
    }

    public void setNormals(ArrayList<Float> list){
        normals = list;
    }

    public void setVertexes(ArrayList<Float> list){
        vertexes = list;
    }

    public int getVertOffset() {
        return vertOffset;
    }

    public int getTexOffset() {
        return texOffset;
    }

    public int getNormOffset() {
        return normOffset;
    }

    public int getStride(){
        return stride;
    }

    public int getVertexCoordSize(){
        return vertexes.size();
    }

    public int getTextureCoordSize(){
        return texCoords.size();
    }

    public int getNormalsSize(){
        return normals.size();
    }
}
