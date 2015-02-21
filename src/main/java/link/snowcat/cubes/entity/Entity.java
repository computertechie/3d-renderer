
package link.snowcat.cubes.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import link.snowcat.cubes.render.ModelManager;
import link.snowcat.cubes.render.ShaderProgramManager;
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
        scale = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
        ModelManager.getInstance().loadModel(modelName);
    }

    public void update(){

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

}
