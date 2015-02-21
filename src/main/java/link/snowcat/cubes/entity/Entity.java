
package link.snowcat.cubes.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import link.snowcat.cubes.render.ModelManager;
import link.snowcat.cubes.render.ShaderProgramManager;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;


/**
 * An entity.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Entity {

    @Expose
    private String entityName;
    @Expose
    private String modelName;
    @Expose
    private Vector3f position;
    @Expose
    private Vector3f rotation;
    @Expose
    private Vector3f scale;

    public void initialize(){
        position = new Vector3f(0,0,0);
        scale = new Vector3f(1,1,1);
        rotation = new Vector3f(0,0,0);
        ModelManager.getInstance().loadModel(modelName);
    }

    public void update(){

    }

    public Matrix4f getModelMatrix(){
        Matrix4f modelMatrix = new Matrix4f();
        modelMatrix.translate(new Vector3f(position.x, position.y, -position.z));
        modelMatrix.rotate(rotation.x, new Vector3f(1.0F, 0.0F, 0.0F));
        modelMatrix.rotate(rotation.y, new Vector3f(0.0F, 1.0F, 0.0F));
        modelMatrix.rotate(rotation.z, new Vector3f(0.0F, 0.0F, 1.0F));
        modelMatrix.scale(scale);

        return modelMatrix;
    }

    /**
     * 
     * @return
     *     The entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * 
     * @param entityName
     *     The entityName
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * 
     * @return
     *     The modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * 
     * @param modelName
     *     The modelName
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * 
     * @return
     *     The position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The position
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The rotation
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * 
     * @param rotation
     *     The rotation
     */
    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    /**
     * 
     * @return
     *     The scale
     */
    public Vector3f getScale() {
        return scale;
    }

    /**
     * 
     * @param scale
     *     The scale
     */
    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void move(float x, float y, float z){
        position.setX(position.getX()+x);
        position.setY(position.getY()+y);
        position.setZ(position.getZ()+z);
    }
    
    public void scale(float x, float y, float z){
        scale.setX(scale.getX()+x);
        scale.setY(scale.getY()+y);
        scale.setZ(scale.getZ()+z);
    }
    
    public void rotate(float x, float y, float z){
        rotation.setX(rotation.getX()+x);
        rotation.setY(rotation.getY()+y);
        rotation.setZ(rotation.getZ()+z);
    }

}
