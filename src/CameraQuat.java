import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.nio.FloatBuffer;

public class CameraQuat
{
    private static final float PI_OVER_180 = 0.01745329F;
    private Vector3f position = new Vector3f();
    private Vector3f direction = new Vector3f();
    private FloatBuffer viewMatrixBuffer = BufferUtils.createFloatBuffer(16);
    private float pitchAngle;
    private float bearingAngle;
    private Quaternion pitch;
    private Quaternion bearing;
    private Quaternion rotation;

    public CameraQuat()
    {
        pitch = new Quaternion();
        bearing = new Quaternion();
        rotation = new Quaternion();
        bearingAngle = 0.0F;
        pitchAngle = 0.0F;
    }

    public CameraQuat(float initialBearing, float initialPitch)
    {
        pitch = new Quaternion();
        bearing = new Quaternion();
        rotation = new Quaternion();
        bearingAngle = initialBearing;
        pitchAngle = initialPitch;
    }

    public void reorient()
    {
        Quaternion.mul(pitch, bearing, rotation);

        Matrix4f matrix = convertQuaternionToMatrix4f(rotation);
        matrix.translate(new Vector3f(-position.x, -position.y, -position.z));
        matrix.store(viewMatrixBuffer);
        viewMatrixBuffer.rewind();

        Matrix4f pitchMatrix = convertQuaternionToMatrix4f(pitch);
        Quaternion temp = Quaternion.mul(bearing, pitch, null);
        matrix = convertQuaternionToMatrix4f(temp);
        direction.x = matrix.m20;
        direction.y = pitchMatrix.m21;
        direction.z = matrix.m22;
    }

    public void bearing(float bearingDelta)
    {
        System.out.println(bearingAngle);
        System.out.println(Math.sin(bearingAngle * 0.01745329F) + " " + Math.cos(bearingAngle * 0.01745329F));
        System.out.println();
        bearingAngle += bearingDelta;
        if (bearingAngle > 360.0F)
            bearingAngle -= 360.0F;
        if (bearingAngle < 0.0F)
            bearingAngle += 360.0F;
        bearing.setFromAxisAngle(new Vector4f(0.0F, 1.0F, 0.0F, bearingAngle * 0.01745329F));
        reorient();
    }

    public void pitch(float pitchDelta)
    {
        pitchAngle -= pitchDelta;
        if (pitchAngle >= 90.0F)
            pitchAngle = 89.900002F;
        else if (pitchAngle <= -90.0F)
            pitchAngle = -89.900002F;
        pitch.setFromAxisAngle(new Vector4f(1.0F, 0.0F, 0.0F, pitchAngle * 0.01745329F));
        reorient();
    }

    public void move(float units)
    {
        if ((bearingAngle == 0.0F) || (bearingAngle == 360.0F)) {
            position.z -= units;
        }
        else if (bearingAngle == 90.0F) {
            position.x += units;
        }
        else if (bearingAngle == 180.0F) {
            position.z += units;
        }
        else if (bearingAngle == 270.0F) {
            position.x -= units;
        }
        reorient();
    }

    public void strafe(float units)
    {
        Vector3f up = new Vector3f(0.0F, 1.0F, 0.0F);
        Vector3f cross = Vector3f.cross(direction, up, null);

        position.x += cross.x * units;

        position.z += cross.z * units;
        reorient();
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        position = position;
    }

    public Vector3f getDirection()
    {
        return direction;
    }

    public FloatBuffer getViewMatrixBuffer()
    {
        return viewMatrixBuffer;
    }

    private static Matrix4f convertQuaternionToMatrix4f(Quaternion q)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.m00 = (1.0F - 2.0F * (q.getY() * q.getY() + q.getZ() * q.getZ()));
        matrix.m01 = (2.0F * (q.getX() * q.getY() + q.getZ() * q.getW()));
        matrix.m02 = (2.0F * (q.getX() * q.getZ() - q.getY() * q.getW()));
        matrix.m03 = 0.0F;

        matrix.m10 = (2.0F * (q.getX() * q.getY() - q.getZ() * q.getW()));
        matrix.m11 = (1.0F - 2.0F * (q.getX() * q.getX() + q.getZ() * q.getZ()));
        matrix.m12 = (2.0F * (q.getZ() * q.getY() + q.getX() * q.getW()));
        matrix.m13 = 0.0F;

        matrix.m20 = (2.0F * (q.getX() * q.getZ() + q.getY() * q.getW()));
        matrix.m21 = (2.0F * (q.getY() * q.getZ() - q.getX() * q.getW()));
        matrix.m22 = (1.0F - 2.0F * (q.getX() * q.getX() + q.getY() * q.getY()));
        matrix.m23 = 0.0F;

        matrix.m30 = 0.0F;
        matrix.m31 = 0.0F;
        matrix.m32 = 0.0F;
        matrix.m33 = 1.0F;

        return matrix;
    }

    public void bufferUniforms(int location)
    {
        GL20.glUniformMatrix4(location, false, viewMatrixBuffer);
    }
}