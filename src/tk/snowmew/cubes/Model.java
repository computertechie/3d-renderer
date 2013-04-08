package tk.snowmew.cubes;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Model implements IMatrix
{
    Matrix4f modelMatrix = new Matrix4f();
    int VAO, vertexVBO, indVBO;
    ShaderProgram shaderProgram;
    Vector3f rotation = new Vector3f(); Vector3f translation = new Vector3f(); Vector3f scale = new Vector3f(1,1,1);

    float[] verts = {
            -0.5F, 0.5F, 0.5F,
            0.5F, 0.5F, 0.5F,
            0.5F, 0.5F, -0.5F,
            -0.5F, 0.5F, -0.5F,

            -0.5F, -0.5F, -0.5F,
            -0.5F, -0.5F, 0.5F,
            0.5F, -0.5F, 0.5F,
            0.5F, -0.5F, -0.5F
    };

    byte[] inds = {
            0, 5, 6,
            0, 6, 1,
            0, 3, 4,
            0, 4, 5,
            0, 1, 2,
            0, 2, 3,
            7, 5, 6,
            7, 4, 5,
            7, 1, 6,
            7, 1, 2,
            7, 4, 3,
            7, 3, 2
    };

    public Model()
    {
        shaderProgram = new ShaderProgram("assets/basic_block_vert.glsl","assets/basic_block_frag.glsl","position");
        genIDs();
        buffer();
        update();
    }

    public void update() {
        modelMatrix = new Matrix4f();
        modelMatrix.translate(translation);
        modelMatrix.rotate(rotation.x, new Vector3f(1.0F, 0.0F, 0.0F));
        modelMatrix.rotate(rotation.y, new Vector3f(0.0F, 1.0F, 0.0F));
        modelMatrix.rotate(rotation.z, new Vector3f(0.0F, 0.0F, 1.0F));
        modelMatrix.scale(scale);
    }

    public void buffer() {
        FloatBuffer vBuf = BufferUtils.createFloatBuffer(verts.length);
        vBuf.put(verts);
        vBuf.flip();
        ByteBuffer iBuf = BufferUtils.createByteBuffer(inds.length);
        iBuf.put(inds);
        iBuf.flip();

        GL30.glBindVertexArray(VAO);

        GL20.glEnableVertexAttribArray(shaderProgram.getAttribLocation("position"));
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexVBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vBuf, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(shaderProgram.getAttribLocation("position"),3,GL11.GL_FLOAT, false, 0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indVBO);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, iBuf, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        GL30.glBindVertexArray(0);
    }

    public int getProgramID(){
        return shaderProgram.getProgramID();
    }

    public void render() {
        GL30.glBindVertexArray(VAO);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indVBO);
        GL11.glDrawElements(GL11.GL_TRIANGLES,inds.length,GL11.GL_UNSIGNED_BYTE,0 );
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        GL30.glBindVertexArray(0);
    }

    public void genIDs() {
        VAO = GL30.glGenVertexArrays();
        vertexVBO = GL15.glGenBuffers();
        indVBO = GL15.glGenBuffers();
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
}
