package tk.snowmew.cubes;

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

    public Mesh(List<Vertex> verts, String mat){
        vertexes = verts;
        material = mat;
    }

    public int sizeOfVertCoords(){
        int size = 0;
        for(Vertex vertex : vertexes)
            size += vertex.getVertexCoordSize();
        return size;
    }

    public int sizeOfTexCoords(){
        int size = 0;
        for(Vertex vertex : vertexes)
            size += vertex.getTextureCoordSize();
        return size;
    }

    public int sizeOfNormals(){
        int size = 0;
        for(Vertex vertex : vertexes)
            size += vertex.getNormalsSize();
        return size;
    }

    public int sizeOfMesh(){
        return sizeOfVertCoords() + sizeOfTexCoords() + sizeOfNormals();
    }

    public FloatBuffer getMeshVertexesAsFloatBuffer(){
        FloatBuffer verts = BufferUtils.createFloatBuffer(sizeOfVertCoords());
        for(Vertex vertex : vertexes)
            verts.put(vertex.getVertexesAsPrimFloatArray());
        return verts;
    }

    public FloatBuffer getMeshTexturesAsFloatBuffer(){
        FloatBuffer textures = BufferUtils.createFloatBuffer(sizeOfTexCoords());
        for(Vertex vertex : vertexes)
            textures.put(vertex.getTexturesAsPrimFloatArray());
        return textures;
    }

    public FloatBuffer getMeshNormalsAsFloatBuffer(){
        FloatBuffer norms = BufferUtils.createFloatBuffer(sizeOfNormals());
        for(Vertex vertex : vertexes)
            norms.put(vertex.getNormalsAsPrimFloatArray());
        return norms;
    }

    public Vertex getVertex(int i){
        return vertexes.get(i);
    }
}
