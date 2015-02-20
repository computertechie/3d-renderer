
package link.snowcat.cubes.render;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;


/**
 * A specific element of a vertex (a position, normal, UV, etc).
 * 
 */
@Generated("org.jsonschema2pojo")
public class VertexElement {

    @Expose
    private int elementSize;
    @Expose
    private int elementUse;
    @Expose
    private int elementCount;

    public VertexElement(int size, int count, int use){
        elementCount = count;
        elementSize = size;
        elementUse = use;
    }

    /**
     * 
     * @return
     *     The elementSize
     */
    public int getElementSize() {
        return elementSize;
    }

    /**
     * 
     * @param elementSize
     *     The elementSize
     */
    public void setElementSize(int elementSize) {
        this.elementSize = elementSize;
    }

    /**
     * 
     * @return
     *     The elementUse
     */
    public int getElementUse() {
        return elementUse;
    }

    /**
     * 
     * @param elementUse
     *     The elementUse
     */
    public void setElementUse(int elementUse) {
        this.elementUse = elementUse;
    }

    /**
     * 
     * @return
     *     The elementCount
     */
    public int getElementCount() {
        return elementCount;
    }

    /**
     * 
     * @param elementCount
     *     The elementCount
     */
    public void setElementCount(int elementCount) {
        this.elementCount = elementCount;
    }

}
