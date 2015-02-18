package link.snowcat.cubes.render;

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
    private List<Float> position = new ArrayList<Float>();
    private List<Float> normal = new ArrayList<Float>();
    private List<Float> UV = new ArrayList<Float>();
    private int vertOffset = 0, texOffset = 0, normOffset = 0;
    static int stride = 24;
    private int sizeOfFloat = 4, vertElementCount = 4, texElementCount = 2, normalElementCount=0, totalElementCount;

    public Vertex(List<Float> verts, List<Float> texes, List<Float> norms){
        position = verts;
        normal = norms;
        UV = texes;
        texOffset = sizeOfFloat*vertElementCount;
        normOffset = sizeOfFloat*texElementCount + texOffset;
        normalElementCount = norms.size();
        totalElementCount = vertElementCount + texElementCount + normalElementCount;
        stride = sizeOfFloat * vertElementCount + sizeOfFloat * texElementCount + sizeOfFloat * normalElementCount;
    }

    public Vertex(List<Float> verts, List<Float> texes){
        position = verts;
        UV = texes;
        texOffset = sizeOfFloat * vertElementCount;
        totalElementCount = texElementCount + vertElementCount;
        stride = sizeOfFloat * vertElementCount + sizeOfFloat * texElementCount;
    }

    public Vertex(List<Float> verts){
        position = verts;
        totalElementCount = vertElementCount;
    }

    public Vertex(){

    }

    public FloatBuffer getElementsAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(totalElementCount);
        for(Float f : position)
            buffer.put(f);
        if(UV.size()>0)
            for(Float f : UV)
                buffer.put(f);
        if(normal.size() > 0)
            for(Float f : normal)
                buffer.put(f);
        buffer.flip();
        return buffer;
    }

    public float[] getElementsAsFloatArray(){
        float[] arr = new float[totalElementCount];
        int vC = 0, tC = 0, nC = 0;
        for(Float f : position){
            arr[vC] = f;
            vC++;
        }
        if(UV.size() > 0)
            for(Float f : UV){
                arr[tC] = f;
                tC++;
            }
        if(normal.size() > 0)
            for(Float f : normal){
                arr[nC] = f;
                nC++;
            }

        return arr;
    }

    public List<Float> getPosition(){
        return position;
    }

    public List<Float> getTexture(){
        return UV;
    }

    public List<Float> getNormal(){
        return normal;
    }

    public FloatBuffer getPositionAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(getNumPositionElements());
        for(Float f : position){
            buffer.put(f);
        }
        buffer.flip();
        return buffer;
    }

    public FloatBuffer getTexturesAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(getNumTextureElements());
        for(Float f : UV){
            buffer.put(f);
        }
        buffer.flip();
        return buffer;
    }

    public FloatBuffer getNormalsAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(getNumNormalElements());
        for(Float f : normal){
            buffer.put(f);
        }
        buffer.flip();
        return buffer;
    }

    public void setTextures(List<Float> list){
        UV = list;
    }

    public void setNormal(List<Float> list){
        normal = list;
    }

    public void setPosition(List<Float> list){
        position = list;
    }

    public int getPositionOffset() {
        return vertOffset;
    }

    public int getUVOffset() {
        return texOffset;
    }

    public int getNormOffset() {
        return normOffset;
    }

    public int getStride(){
        return stride;
    }

    public int getNumPositionElements(){
        return position.size();
    }

    public int getNumTextureElements(){
        return UV.size();
    }

    public int getNumNormalElements(){
        return normal.size();
    }
}
