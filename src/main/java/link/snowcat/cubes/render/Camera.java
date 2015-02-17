package link.snowcat.cubes.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

public class Camera {
    private static final float PI_OVER_180 = (float)Math.PI/180;
    private Vector3f position = new Vector3f();
    private Vector3f direction = new Vector3f();
    private FloatBuffer viewMatrixBuffer = BufferUtils.createFloatBuffer(16);
    private float pitchAngle;
    private float headingAngle;

    public Camera(){
        this(0,0);
    }

    public Camera(float initialBearing, float initialPitch){
        headingAngle = initialBearing;
        pitchAngle = initialPitch;
    }

    public void update(Vector3f input){
        Matrix4f headingMatrix = new Matrix4f();
        headingMatrix.rotate(headingAngle*PI_OVER_180, new Vector3f(0,1,0));

        Matrix4f pitchMatrix = new Matrix4f();
        pitchMatrix.rotate(pitchAngle*PI_OVER_180, new Vector3f(1,0,0));

        Matrix4f finalMatrix = new Matrix4f();
        Matrix4f.mul(pitchMatrix, headingMatrix, finalMatrix);

        Vector3f movement = new Vector3f(
                (headingMatrix.m00*input.getX())+(headingMatrix.m01*input.getY())+(headingMatrix.m02*input.getZ()),
                (headingMatrix.m10*input.getX())+(headingMatrix.m11*input.getY())+(headingMatrix.m12*input.getZ()),
                (headingMatrix.m20*input.getX())+(headingMatrix.m21*input.getY())+(headingMatrix.m22*input.getZ()));

        position = new Vector3f(position.getX()+movement.getX(), position.getY()+movement.getY(), position.getZ()+movement.getZ());
        finalMatrix.translate(position);
        finalMatrix.store(viewMatrixBuffer);
        viewMatrixBuffer.rewind();
    }

    public void changeBearing(float bearingDelta){
        headingAngle += bearingDelta;
        if (headingAngle > 360.0F)
            headingAngle -= 360.0F;
        if (headingAngle < 0.0F)
            headingAngle += 360.0F;
    }

    public void changePitch(float pitchDelta){
        pitchAngle -= pitchDelta;
        if (pitchAngle >= 90.0F)
            pitchAngle = 89.9F;
        else if (pitchAngle <= -90.0F)
            pitchAngle = -89.9F;
    }

    public void zeroBearing(){
        headingAngle = 0;
    }

    public void zeroPitch(){
        pitchAngle = 0;
    }

    public Vector3f getPosition(){
        return position;
    }

    public void setPosition(Vector3f inPosition){
        position.x = -inPosition.x;
        position.y = -inPosition.y;
        position.z = -inPosition.z;
    }

    public Vector3f getDirection(){
        return direction;
    }

    public FloatBuffer getViewMatrixBuffer(){
        return viewMatrixBuffer;
    }

    public void bufferUniforms(int location){
        GL20.glUniformMatrix4(location, false, viewMatrixBuffer);
    }

    public void setPitch(int pitch) {
        pitchAngle = pitch;
    }
}