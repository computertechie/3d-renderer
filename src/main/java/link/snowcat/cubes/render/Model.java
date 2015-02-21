package link.snowcat.cubes.render;

import link.snowcat.cubes.generated.Attribute;
import link.snowcat.cubes.generated.ShaderProgram;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import link.snowcat.cubes.Cubes;
import link.snowcat.cubes.utils.IMatrix;
import link.snowcat.cubes.utils.ObjFileParser;

import java.net.URL;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public class Model
{
    private Matrix4f modelMatrix = new Matrix4f();
    private IntBuffer meshVBOs, meshVAOs;
    private List<Mesh> meshes;
    private String programName, modelName, modelFile;
    private VertexFormat vertexFormat;

    public String getProgramName(){
        return programName;
    }

    public void initialize(){
        ShaderProgramManager.getInstance().loadAndBindProgram(programName);
        loadGeometry(Cubes.class.getResource(modelFile));
        meshVBOs = BufferUtils.createIntBuffer(meshes.size());
        meshVAOs = BufferUtils.createIntBuffer(meshes.size());
        genIDs();
        buffer();
    }

    public void loadGeometry(URL modelFile){
        meshes = new ObjFileParser(modelFile).getMeshes();
    }

    public void setModelMatrix(Matrix4f matrix) {
        modelMatrix = matrix;
    }

    public void buffer() {
        FloatBuffer aBuf;
        for(int vao = 0; vao < meshVAOs.limit(); vao++){
            aBuf = BufferUtils.createFloatBuffer(meshes.get(vao).sizeOfMesh());
            aBuf.put(meshes.get(vao).getInterleavedMeshBuffer());
            aBuf.flip();

            ShaderProgram modelProgram = ShaderProgramManager.getInstance().getShaderProgram(programName);

            GL30.glBindVertexArray(meshVAOs.get(vao));
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, meshVBOs.get(vao));
            int i = 0, offset = 0;
            for(Attribute attribute : modelProgram.getVertexAttributes()){
                GL20.glEnableVertexAttribArray(attribute.getAttributeLocation());
                GL20.glVertexAttribPointer(attribute.getAttributeLocation(), vertexFormat.getVertexElements().get(i).getElementCount(), GL11.GL_FLOAT, false, modelProgram.getStride(), offset);
                offset +=  vertexFormat.getVertexElements().get(i).getElementCount()*vertexFormat.getVertexElements().get(i).getElementSize();
                i++;
            }

            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, aBuf, GL15.GL_STATIC_DRAW);
        }

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
    }

    public void render() {
        String map;
        Texture temp;
        for(int i = 0; i<meshVAOs.limit(); i++){
            if(Cubes.materialManagerInstance.getMaterialFromName(meshes.get(i).getMaterial()).isDiffuseMapped()){
                map = Cubes.materialManagerInstance.getMaterialFromName(meshes.get(i).getMaterial()).getDiffuseMap();
                temp = Cubes.textureManagerInstance.getTexture(map);
                Cubes.textureManagerInstance.bindTexture(map);
                GL20.glUniform1i(Cubes.shaderProgramManager.getShaderProgram(programName).getUniformAttributes().get("texMap"), temp.getTexUnit());
            }
            else{
                GL20.glUniform3(Cubes.shaderProgramManager.getShaderProgram(programName).getUniformAttributes().get("diffuseColor"), Cubes.materialManagerInstance.getMaterialFromName(meshes.get(i).getMaterial()).getDiffuseColor());
            }
            GL30.glBindVertexArray(meshVAOs.get(i));
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, meshes.get(i).getNumberOfVertexes());
            Cubes.textureManagerInstance.unbindTexture();
        }
        GL30.glBindVertexArray(0);
    }

    public void genIDs() {
        GL30.glGenVertexArrays(meshVAOs);
        GL15.glGenBuffers(meshVBOs);
    }

    public void bufferUniforms() {
        FloatBuffer buf = BufferUtils.createFloatBuffer(16);
        modelMatrix.store(buf);
        buf.flip();
        GL20.glUniformMatrix4(Cubes.shaderProgramManager.getShaderProgram(programName).getModelMatrixLocation(), false, buf);
    }

    private int getSizeOfModel(){
        int size = 0;
        for(Mesh mesh : meshes)
            size += mesh.sizeOfMesh();
        return size;
    }

    private int getSizeOfModelVertexCoords(){
        int size = 0;
        for(Mesh mesh : meshes)
            size += mesh.getNumberOfVertexes();
        return size;
    }

    private int getSizeOfModelTextureCoords(){
        int size = 0;
        for(Mesh mesh : meshes)
            size += mesh.getNumberOfTextureCoords();
        return size;
    }

    private int getSizeOfModelNormals(){
        int size = 0;
        for(Mesh mesh : meshes)
            size += mesh.getNumberOfNormals();
        return size;
    }

    public String getModelName(){
        return modelName;
    }

    public void setModelName(String name){
        modelName = name;
    }
}
