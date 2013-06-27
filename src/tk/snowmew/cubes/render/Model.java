package tk.snowmew.cubes.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tk.snowmew.cubes.Cubes;
import tk.snowmew.cubes.utils.IMatrix;
import tk.snowmew.cubes.utils.ObjFileParser;

import java.io.File;
import java.net.URL;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public class Model implements IMatrix
{
    private Matrix4f modelMatrix = new Matrix4f();
    private IntBuffer meshVBOs, meshVAOs;
    private Vector3f rotation = new Vector3f(); Vector3f translation = new Vector3f(); Vector3f scale = new Vector3f(1,1,1);
    private List<Mesh> meshes;
    private String[] vertAttribs = {"position","in_tex","normal"};
    private String[] uniformAttribs= {"texture","diffuseColor"};
    private int numVerts, numTexes, numNormals;
    private String programName="standard";

//    public Model(String filePath){
//        this(new File(filePath));
//    }

    public Model(String file){
        ObjFileParser parser = new ObjFileParser(new File(file));
        meshes = parser.getMeshes();
        meshVBOs = BufferUtils.createIntBuffer(meshes.size());
        meshVAOs = BufferUtils.createIntBuffer(meshes.size());
        numVerts = getSizeOfModelVertexCoords();
        numTexes = getSizeOfModelTextureCoords();
        numNormals = getSizeOfModelNormals();
        genIDs();
        buffer();
        update();
    }

    public Model(URL resource){
        System.out.println(resource.getFile());
        ObjFileParser parser = new ObjFileParser(resource);
        meshes = parser.getMeshes();
        meshVBOs = BufferUtils.createIntBuffer(meshes.size());
        meshVAOs = BufferUtils.createIntBuffer(meshes.size());
        numVerts = getSizeOfModelVertexCoords();
        numTexes = getSizeOfModelTextureCoords();
        numNormals = getSizeOfModelNormals();
        genIDs();
        buffer();
        update();
    }

    public String getProgramName(){
        return programName;
    }

    public void update() {
        modelMatrix = new Matrix4f();
        modelMatrix.translate(new Vector3f(translation.x, translation.y, -translation.z));
        modelMatrix.rotate(rotation.x, new Vector3f(1.0F, 0.0F, 0.0F));
        modelMatrix.rotate(rotation.y, new Vector3f(0.0F, 1.0F, 0.0F));
        modelMatrix.rotate(rotation.z, new Vector3f(0.0F, 0.0F, 1.0F));
        modelMatrix.scale(scale);
    }

    public void buffer() {
        FloatBuffer aBuf;
        for(int vao = 0; vao < meshVAOs.limit(); vao++){
            aBuf = BufferUtils.createFloatBuffer(meshes.get(vao).sizeOfMesh());
            aBuf.put(meshes.get(vao).getInterleavedMeshBuffer());
            aBuf.flip();

            GL30.glBindVertexArray(meshVAOs.get(vao));

            for(int i = 0; i<vertAttribs.length; i++)
                GL20.glEnableVertexAttribArray(Cubes.shaderProgramManager.getShaderProgram(programName).getAttribLocation(vertAttribs[i]));

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, meshVBOs.get(vao));
            GL20.glVertexAttribPointer(Cubes.shaderProgramManager.getShaderProgram(programName).getAttribLocation("position"), 3, GL11.GL_FLOAT, false, 32, 0);
            GL20.glVertexAttribPointer(Cubes.shaderProgramManager.getShaderProgram(programName).getAttribLocation("normal"), 3, GL11.GL_FLOAT, false, 32, 12);
            GL20.glVertexAttribPointer(Cubes.shaderProgramManager.getShaderProgram(programName).getAttribLocation("in_tex"), 2, GL11.GL_FLOAT, false, 32, 24);
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
                GL20.glUniform1i(Cubes.shaderProgramManager.getShaderProgram(programName).getUniformLocation("texture"),temp.getTexUnit());
            }
            else{
                GL20.glUniform3(Cubes.shaderProgramManager.getShaderProgram(programName).getUniformLocation("diffuseColor"),Cubes.materialManagerInstance.getMaterialFromName(meshes.get(i).getMaterial()).getDiffuseColor());
            }
            GL30.glBindVertexArray(meshVAOs.get(i));
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, numVerts);
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

    public void translate(float x, float y, float z){
        translation = new Vector3f(x,y,z);
        update();
    }

    public void scale(float x, float y, float z){
        scale = new Vector3f(x,y,z);
        update();
    }

    public void rotateX(float angle){
        rotation.x += Renderer.degreesToRadians(angle);
        update();
    }

    public void rotateY(float angle){
        rotation.y += Renderer.degreesToRadians(angle);
        update();
    }

    public void rotateZ(float angle){
        rotation.z += Renderer.degreesToRadians(angle);
        update();
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
}
