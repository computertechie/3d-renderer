package link.snowcat.cubes.lights;

import link.snowcat.cubes.generated.ShaderProgram;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */
public class SpotLight extends PointLight {
    Vector3f direction;
    float cutOffAngle;

    public SpotLight(Vector3f pos, Vector3f dir, Vector3f col, float angle, float intensity, float constant, float linear, float quadratic){
        super(pos, col, intensity, constant, linear, quadratic);
        direction = dir;
        cutOffAngle = angle;
        sizeOf = 9;
    }

    public void getLightAsFloatBuffer(FloatBuffer buffer){
        super.getLightAsFloatBuffer(buffer);
        direction.store(buffer);
        buffer.put(cutOffAngle);
    }

    public void bufferForLighting(ShaderProgram program){
        System.out.println("Direction: " + direction);
        System.out.println("Position: " + position);
        GL20.glUniform3(program.getUniformAttributes().get("spotLight.color"), getColorAsFBuffer());
        GL20.glUniform3(program.getUniformAttributes().get("spotLight.position"), getPositionAsFBuffer());
        GL20.glUniform3(program.getUniformAttributes().get("spotLight.direction"), getDirectionAsFBuffer());
        GL20.glUniform1f(program.getUniformAttributes().get("spotLight.intensity"), getIntensity());
        GL20.glUniform1f(program.getUniformAttributes().get("spotLight.cutOff"), (float)Math.cos(Math.toRadians(getCutOffAngle())));
        GL20.glUniform1f(program.getUniformAttributes().get("spotLight.constant"), getConstantTerm());
        GL20.glUniform1f(program.getUniformAttributes().get("spotLight.linear"), getLinearTerm());
        GL20.glUniform1f(program.getUniformAttributes().get("spotLight.quadratic"), getQuadTerm());
    }

    public float getCutOffAngle() {
        return cutOffAngle;
    }

    public void setCutOffAngle(float cutOffAngle) {
        this.cutOffAngle = cutOffAngle;
    }

    public FloatBuffer getDirectionAsFBuffer(){
        FloatBuffer temp = BufferUtils.createFloatBuffer(3);
        direction.store(temp);
        temp.flip();
        return temp;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
}
