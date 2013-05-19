package tk.snowmew.cubes;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Pepper
 * Date: 4/9/13
 * Time: 7:41 PM
 * Project: Cubes
 */
public class Vertex {
    private List<Float> vertexes = new ArrayList<Float>();
    private List<Float> normals = new ArrayList<Float>();
    private List<Float> texCoords = new ArrayList<Float>();
    private int vertOffset = 0, texOffset = 0, normOffset = 0;
    static int stride = 24;
    private int sizeOfFloat = 4, vertElementCount = 4, texElementCount = 2, normalElementCount=0, totalElementCount;

    public Vertex(List<Float> verts, List<Float> texes, List<Float> norms){
        vertexes = verts;
        normals = norms;
        texCoords = texes;
        texOffset = sizeOfFloat*vertElementCount;
        normOffset = sizeOfFloat*texElementCount + texOffset;
        normalElementCount = norms.size();
        totalElementCount = vertElementCount + texElementCount + normalElementCount;
        stride = sizeOfFloat * vertElementCount + sizeOfFloat * texElementCount + sizeOfFloat * normalElementCount;
    }

    public Vertex(List<Float> verts, List<Float> texes){
        vertexes = verts;
        texCoords = texes;
        texOffset = sizeOfFloat * vertElementCount;
        totalElementCount = texElementCount + vertElementCount;
        stride = sizeOfFloat * vertElementCount + sizeOfFloat * texElementCount;
    }

    public Vertex(List<Float> verts){
        vertexes = verts;
        totalElementCount = vertElementCount;
    }

    public Vertex(){

    }

    public FloatBuffer getElementsAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(totalElementCount);
        for(Float f : vertexes)
            buffer.put(f);
        if(texCoords.size()>0)
            for(Float f : texCoords)
                buffer.put(f);
        if(normals.size() > 0)
            for(Float f : normals)
                buffer.put(f);
        buffer.flip();
        return buffer;
    }

    public float[] getElementsAsFloatArray(){
        float[] arr = new float[totalElementCount];
        int vC = 0, tC = 0, nC = 0;
        for(Float f : vertexes){
            arr[vC] = f;
            vC++;
        }
        if(texCoords.size() > 0)
            for(Float f : texCoords){
                arr[tC] = f;
                tC++;
            }
        if(normals.size() > 0)
            for(Float f : normals){
                arr[nC] = f;
                nC++;
            }

        return arr;
    }

    public List<Float> getVertexes(){
        return vertexes;
    }

    public List<Float> getTexture(){
        return texCoords;
    }

    public List<Float> getNormals(){
        return normals;
    }

    public FloatBuffer getVertexesAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(getNormalsSize());
        for(Float f : vertexes){
            buffer.put(f);
        }
        buffer.flip();
        return buffer;
    }

    public FloatBuffer getTexturesAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(getNormalsSize());
        for(Float f : texCoords){
            buffer.put(f);
        }
        buffer.flip();
        return buffer;
    }

    public FloatBuffer getNormalsAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(getNormalsSize());
        for(Float f : normals){
            buffer.put(f);
        }
        buffer.flip();
        return buffer;
    }

    public void setTextures(List<Float> list){
        texCoords = list;
    }

    public void setNormals(List<Float> list){
        normals = list;
    }

    public void setVertexes(List<Float> list){
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
