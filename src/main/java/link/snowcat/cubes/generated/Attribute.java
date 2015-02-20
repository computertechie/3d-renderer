
package link.snowcat.cubes.generated;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;


/**
 * A shader attribute.
 * 
 */
@Generated("org.jsonschema2pojo")
public class Attribute {

    @Expose
    private String attributeName;
    @Expose
    private int attributeLocation;
    @Expose
    private int attributeSize;
    @Expose
    private int attributeOffset;

    /**
     * 
     * @return
     *     The attributeName
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * 
     * @param attributeName
     *     The attributeName
     */
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    /**
     * 
     * @return
     *     The attributeLocation
     */
    public int getAttributeLocation() {
        return attributeLocation;
    }

    /**
     * 
     * @param attributeLocation
     *     The attributeLocation
     */
    public void setAttributeLocation(int attributeLocation) {
        this.attributeLocation = attributeLocation;
    }

    /**
     * 
     * @return
     *     The attributeSize
     */
    public int getAttributeSize() {
        return attributeSize;
    }

    /**
     * 
     * @param attributeSize
     *     The attributeSize
     */
    public void setAttributeSize(int attributeSize) {
        this.attributeSize = attributeSize;
    }

    /**
     * 
     * @return
     *     The attributeOffset
     */
    public int getAttributeOffset() {
        return attributeOffset;
    }

    /**
     * 
     * @param attributeOffset
     *     The attributeOffset
     */
    public void setAttributeOffset(int attributeOffset) {
        this.attributeOffset = attributeOffset;
    }

}
