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

public class DirectionalLight extends Light {
    private float altitute = 0,  azimuth = 0;

    public DirectionalLight(Vector3f col, float intensity, float alt, float azi){
        super(null, col, intensity);
        altitute = alt;
        azimuth = azi;
        updatePosition();
    }

    public void bufferForLighting(ShaderProgram program){
        GL20.glUniform3(program.getUniformAttributes().get("dirLight.color"), getColorAsFBuffer());
        GL20.glUniform3(program.getUniformAttributes().get("dirLight.position"), getPositionAsFBuffer());
        GL20.glUniform1f(program.getUniformAttributes().get("dirLight.intensity"), getIntensity());
    }

    public void bufferForGeometry(ShaderProgram program){

    }

    public float getAltitute() {
        return altitute;
    }

    public void setAltitute(float altitute) {
        this.altitute = altitute;
    }

    public float getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(float azimuth) {
        this.azimuth = azimuth;
    }

    public FloatBuffer getPositionAsFBuffer(){
        updatePosition();
        FloatBuffer temp = BufferUtils.createFloatBuffer(3);
        position.store(temp);
        temp.flip();
        return temp;
    }

    private void updatePosition(){
        float x, y, z, hyp, altRads = (float)Math.toRadians(altitute), aziRads = (float)Math.toRadians(azimuth);
        y = (float)Math.sin(altRads);
        hyp = (float)Math.cos(altRads);
        z = hyp*(float)Math.cos(aziRads);
        x = hyp*(float)Math.sin(aziRads);

        position = new Vector3f(x,y,z);
    }
}
