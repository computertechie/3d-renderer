package tk.snowmew.cubes;

import java.util.ArrayList;

/**
 * User: Pepper
 * Date: 4/16/13
 * Time: 7:13 PM
 * Project: Cubes
 */
public class Mesh {
    private ArrayList<Vertex> vertexes;

    public Mesh(ArrayList<Vertex> verts){
        vertexes = verts;
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
}
