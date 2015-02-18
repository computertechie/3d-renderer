
package link.snowcat.cubes.generated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;


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
    private List<Object> position = new ArrayList<Object>(Arrays.asList(null, null, null));
    @Expose
    private List<Object> rotation = new ArrayList<Object>(Arrays.asList(null, null, null));
    @Expose
    private List<Object> scale = new ArrayList<Object>(Arrays.asList(null, null, null));

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
    public List<Object> getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The position
     */
    public void setPosition(List<Object> position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The rotation
     */
    public List<Object> getRotation() {
        return rotation;
    }

    /**
     * 
     * @param rotation
     *     The rotation
     */
    public void setRotation(List<Object> rotation) {
        this.rotation = rotation;
    }

    /**
     * 
     * @return
     *     The scale
     */
    public List<Object> getScale() {
        return scale;
    }

    /**
     * 
     * @param scale
     *     The scale
     */
    public void setScale(List<Object> scale) {
        this.scale = scale;
    }

}
