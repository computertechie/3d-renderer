import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

public class Camera implements IMatrix
{
    Matrix4f viewMatrix = new Matrix4f(), cameraModelMatrix = new Matrix4f();
    Vector3f translation = new Vector3f(); Vector3f scale = new Vector3f(1.0F, 1.0F, 1.0F); Vector3f rotation = new Vector3f();
    Vector3f globalUp = new Vector3f(0,1,0), side,look,localUp;
    float angleX=30, angleY=45;

    public void bufferUniforms(int location)
    {
        FloatBuffer buf = BufferUtils.createFloatBuffer(16);
        viewMatrix.store(buf);
        buf.flip();
        GL20.glUniformMatrix4(location, false, buf);
    }

    public void calcLookVec(){
        float x, y, z;
        x = (float)Math.sin(rotation.x);
        y = (float)Math.sin(rotation.y);
        z = (float)Math.cos(rotation.x);
        look = new Vector3f(x,y,z).normalise(null);
    }

    public void genCameraMatrix(){
        calcLookVec();
        cameraModelMatrix = new Matrix4f();
        cameraModelMatrix.scale(scale);
        cameraModelMatrix.translate(translation);
        cameraModelMatrix.rotate(rotation.x, new Vector3f(1,0,0));
        cameraModelMatrix.rotate(rotation.y, new Vector3f(0,1,0));
        cameraModelMatrix.rotate(rotation.z, new Vector3f(0,0,1));
//        viewMatrix = cameraModelMatrix;
        Matrix4f.invert(cameraModelMatrix, viewMatrix);
    }

    public void update() {
        genCameraMatrix();
//        Matrix4f.invert(cameraModelMatrix, viewMatrix);
    }

    public void translate(float x, float y, float z)
    {
        translation = new Vector3f(translation.x+look.x,translation.y+look.y,translation.z+look.z);
        update();
    }

    public void moveForward(){
        translation.z -= look.z*0.1;
        translation.x -= look.x*0.1;
    }

    public void moveBackward(){
        translation.z += look.z*0.1;
        translation.x += look.x*0.1;
    }

    public void moveLeft(){
//        translation.z -= look.z;
        translation.x -= 0.1;//look.x;
    }

    public void moveRight(){
        translation.x += 0.1;
    }

    public void moveUp(){
        translation.y += 0.1f;
    }

    public void moveDown(){
        translation.y -= 0.1f;
    }

    public void scale(float x, float y, float z){
        scale = new Vector3f(x,y,z);
        update();
    }

    public void rotateX(float angle){
        rotation.x += Renderer.degreesToRadians(angle);
        if(rotation.x >= Renderer.degreesToRadians(90))
            rotation.x = Renderer.degreesToRadians(89.9f);
        else if(rotation.x <= Renderer.degreesToRadians(-90))
            rotation.x = Renderer.degreesToRadians(-89.9f);
        update();
    }

    public void rotateY(float angle){
        rotation.y -= Renderer.degreesToRadians(angle);
        if(rotation.y > Renderer.degreesToRadians(360))
            rotation.y -= Renderer.degreesToRadians(360);
        update();
    }

    public void rotateZ(float angle){
        rotation.z -= Renderer.degreesToRadians(angle);
        update();
    }
}

