package link.snowcat.cubes.render;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.List;

/**
 * User: Pepper
 * Date: 4/16/13
 * Time: 7:13 PM
 * Project: Cubes
 */

public class Mesh {
    private List<Vertex> vertexes;
    private String material;
    private int sizeOfVertCoords = 0, sizeOfTexCoords = 0, sizeOfNormals = 0;
    private boolean hasUVs = false, hasNormals = false;

    public Mesh(List<Vertex> verts, String mat){
        vertexes = verts;
        material = mat;
        recalcSize();
    }

    private void recalcSize(){
        sizeOfVertCoords = sizeOfVertCoords();
        sizeOfTexCoords = sizeOfTexCoords();
        sizeOfNormals = sizeOfNormals();
    }

    public String getMaterial(){
        return material;
    }

    private int sizeOfVertCoords(){
        int size = 0;
        for(Vertex vertex : vertexes)
            size += vertex.getNumPositionElements();
        return size;
    }

    public int getNumberOfVertexes(){
        return sizeOfVertCoords;
    }

    private int sizeOfTexCoords(){
        int size = 0;
        for(Vertex vertex : vertexes) {
            size += vertex.getNumTextureElements();
        }

        if(size>0){
            hasUVs = true;
        }

        return size;
    }

    public int getNumberOfTextureCoords(){
        return sizeOfTexCoords;
    }

    private int sizeOfNormals(){
        int size = 0;
        for(Vertex vertex : vertexes) {
            size += vertex.getNumNormalElements();
        }

        if(size>0){
            hasNormals = true;
        }

        return size;
    }

    public int getNumberOfNormals(){
        return sizeOfNormals;
    }

    public int sizeOfMesh(){
        return sizeOfVertCoords + sizeOfTexCoords + sizeOfNormals;
    }

    public boolean hasUVs(){
        return hasUVs;
    }

    public boolean hasNormals(){
        return hasNormals;
    }

    public FloatBuffer getMeshVertexesAsFloatBuffer(){
        FloatBuffer verts = BufferUtils.createFloatBuffer(sizeOfVertCoords());
        for(Vertex vertex : vertexes)
            verts.put(vertex.getPositionAsBuffer());
        verts.flip();
        return verts;
    }

    public FloatBuffer getMeshTexturesAsFloatBuffer(){
        FloatBuffer textures = BufferUtils.createFloatBuffer(sizeOfTexCoords());
        for(Vertex vertex : vertexes)
            textures.put(vertex.getTexturesAsBuffer());
        textures.flip();
        return textures;
    }

    public FloatBuffer getMeshNormalsAsFloatBuffer(){
        FloatBuffer norms = BufferUtils.createFloatBuffer(sizeOfNormals());
        for(Vertex vertex : vertexes)
            norms.put(vertex.getNormalsAsBuffer());
        norms.flip();
        return norms;
    }

    public FloatBuffer getInterleavedMeshBuffer(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(sizeOfMesh());
        for(Vertex vert : vertexes){
            buffer.put(vert.getPositionAsBuffer());
            buffer.put(vert.getNormalsAsBuffer());
            buffer.put(vert.getTexturesAsBuffer());
        }
        buffer.flip();
        return buffer;
    }

    public int getStride(){
        int stride = 0;
        stride += 12; //vertex - 3 * GL_FLOAT.size
        if(hasUVs){
            stride += 8; // UV - 2 * GL_FLOAT.size
        }
        if(hasNormals){
            stride += 12; //normal - 3 * GL_FLOAT.size
        }
        return stride;
    }

    public int getVertexOffset(){
        return 0;
    }

    public int getNormalOffset(){
        return getVertexOffset()+12;
    }

    public int getUVOffset(){
        int offset = 0;
        if(hasNormals){
            offset += getNormalOffset();
        }
        offset += 12; //3*GL_FLOAT - for size of normals or vertexes
        return offset;
    }

    public Vertex getVertex(int i){
        return vertexes.get(i);
    }

    public void addVertexes(List<Vertex> verts){
        vertexes.addAll(verts);
        recalcSize();
    }

    public List<Vertex> getVertexList(){
        return vertexes;
    }

    public static void combine(Mesh with, Mesh to){
        with.addVertexes(to.getVertexList());
    }
}
