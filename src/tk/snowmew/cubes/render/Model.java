package tk.snowmew.cubes.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tk.snowmew.cubes.Cubes;
import tk.snowmew.cubes.IMatrix;

import java.io.File;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public class Model implements IMatrix
{
    private Matrix4f modelMatrix = new Matrix4f();
    private IntBuffer meshVBOs, meshVAOs;
    private ShaderProgram shaderProgram;
    private Vector3f rotation = new Vector3f(); Vector3f translation = new Vector3f(); Vector3f scale = new Vector3f(1,1,1);
    private List<Mesh> meshes;
    private ObjFileParser parser;
    private String[] vertAttribs = {"position","in_tex","normal"};
    private String[] uniformAttribs= {"texture","diffuseColor"};
    private int numVerts, numTexes, numNormals;

    public Model(File file) {
        this(file,"assets/shaders/basic_block_vert.glsl","assets/shaders/basic_block_frag.glsl");
    }

    public Model(String file){
        this(new File(file),"assets/shaders/basic_block_vert.glsl","assets/shaders/basic_block_frag.glsl");
    }

    public Model(File file, String vert, String frag){
        parser = new ObjFileParser(file);
        meshes = parser.getMeshes();
        shaderProgram = new ShaderProgram(vert, frag, vertAttribs, uniformAttribs);
        meshVBOs = BufferUtils.createIntBuffer(meshes.size());
        meshVAOs = BufferUtils.createIntBuffer(meshes.size());
        System.out.println(meshes.size());
        numVerts = getSizeOfModelVertexCoords();
        numTexes = getSizeOfModelTextureCoords();
        numNormals = getSizeOfModelNormals();
        genIDs();
        buffer();
        update();
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
                GL20.glEnableVertexAttribArray(shaderProgram.getAttribLocation(vertAttribs[i]));

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, meshVBOs.get(vao));
            GL20.glVertexAttribPointer(shaderProgram.getAttribLocation("position"), 3, GL11.GL_FLOAT, false, 32, 0);
            GL20.glVertexAttribPointer(shaderProgram.getAttribLocation("normal"), 3, GL11.GL_FLOAT, false, 32, 12);
            GL20.glVertexAttribPointer(shaderProgram.getAttribLocation("in_tex"), 2, GL11.GL_FLOAT, false, 32, 24);
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
                GL20.glUniform1i(shaderProgram.getUniformLocation("texture"),temp.getTexUnit());
            }
            else{
                GL20.glUniform3(shaderProgram.getUniformLocation("diffuseColor"),Cubes.materialManagerInstance.getMaterialFromName(meshes.get(i).getMaterial()).getDiffuseColor());
            }
//            GL20.glUniform3f(shaderProgram.getUniformLocation("diffuseColor"),0.5f,0.5f,0.5f);
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
        GL20.glUniformMatrix4(shaderProgram.getModelMatrixLocation(), false, buf);

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

    public ShaderProgram getShaderProgram(){
        return shaderProgram;
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
