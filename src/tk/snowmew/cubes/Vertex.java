package tk.snowmew.cubes;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 4/9/13
 * Time: 7:41 PM
 * Project: Cubes
 */
public class Vertex {
    private float[] vertex = new float[]{};
    private float[] normals = new float[]{};
    private float[] texcoords = new float[]{};
    private int vertOffset = 0, texOffset = 0, normOffset = 0;
    static int stride = 24;
    private int sizeOfFloat = 4, vertElementCount = 4, texElementCount = 2, normalElementCount=0, totalElementCount;

    /*public Vertex(float[] verts, float[] texes, float[] norms){
        vertex = verts;
        normals = norms;
        texcoords = texes;
        texOffset = sizeOfFloat*vertElementCount;
        normOffset = sizeOfFloat*texElementCount + texOffset;
        normalElementCount = norms.length;
        totalElementCount = vertElementCount + texElementCount + normalElementCount;
        stride = sizeOfFloat * vertElementCount + sizeOfFloat * texElementCount + sizeOfFloat * normalElementCount;
    }*/

    public Vertex(float[] verts, float[] texes){
        vertex = verts;
        texcoords = texes;
        texOffset = sizeOfFloat * vertElementCount;
        totalElementCount = texElementCount + vertElementCount;
        stride = sizeOfFloat * vertElementCount + sizeOfFloat * texElementCount;
    }

    public Vertex(float[] verts){
        vertex = verts;
        totalElementCount = vertElementCount;
    }

    public FloatBuffer getElementsAsBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(totalElementCount);
        buffer.put(vertex);
        if(texcoords.length>0)
            buffer.put(texcoords);
//        if(normals.length > 0)
//            buffer.put(normals);
        buffer.flip();
        return buffer;
    }

    public float[] getElementsAsFloatArray(){
        float[] arr = new float[totalElementCount];

        for(int i = 0; i<vertex.length;i++)
            arr[i] = vertex[i];
        if(texcoords.length > 0)
            for(int i = texOffset; i<texcoords.length;i++)
                arr[i] = texcoords[i];
        if(normals.length > 0)
            for(int i = normOffset; i<normalElementCount; i++)
                arr[i] = normals[i];

        return arr;
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
}
