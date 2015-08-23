package link.snowcat.cubes.lights;

import link.snowcat.cubes.generated.ShaderProgram;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 4/25/13
 * Time: 9:34 PM
 * Project: Cubes
 */
public class PointLight extends Light {
    private float constantTerm, linearTerm, quadTerm;

    public PointLight(Vector3f pos, Vector3f col, float intensity, float constant, float linear, float quad){
        super(pos, col, intensity);
        this.linearTerm = linear;
        this.constantTerm = constant;
        this.quadTerm = quad;
        sizeOf = 5;
    }

    public void getLightAsFloatBuffer(FloatBuffer buffer){
        super.getLightAsFloatBuffer(buffer);
    }

    public void bufferForLighting(ShaderProgram program){
        GL20.glUniform3(program.getUniformAttributes().get("pointLight.color"), getColorAsFBuffer());
        GL20.glUniform3(program.getUniformAttributes().get("pointLight.position"), getPositionAsFBuffer());
        GL20.glUniform1f(program.getUniformAttributes().get("pointLight.intensity"), getIntensity());
        GL20.glUniform1f(program.getUniformAttributes().get("pointLight.constant"), getConstantTerm());
        GL20.glUniform1f(program.getUniformAttributes().get("pointLight.linear"), getLinearTerm());
        GL20.glUniform1f(program.getUniformAttributes().get("pointLight.quadratic"), getQuadTerm());
    }

    public float getConstantTerm() {
        return constantTerm;
    }

    public void setConstantTerm(float constantTerm) {
        this.constantTerm = constantTerm;
    }

    public float getLinearTerm() {
        return linearTerm;
    }

    public void setLinearTerm(float linearTerm) {
        this.linearTerm = linearTerm;
    }

    public float getQuadTerm() {
        return quadTerm;
    }

    public void setQuadTerm(float quadTerm) {
        this.quadTerm = quadTerm;
    }
}
