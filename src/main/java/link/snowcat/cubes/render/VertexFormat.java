
package link.snowcat.cubes.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Description of a vertex's structure (attributes, sizes, offsets).
 * 
 */
@Generated("org.jsonschema2pojo")
public enum VertexFormat {
    @SerializedName("POSITION_UV_NORMAL")
    POSITION_UV_NORMAL(Arrays.asList(new VertexElement[]{new VertexElement(4,3,0), new VertexElement(4,2,0), new VertexElement(4,3,0)})),
    @SerializedName("POSITION_NORMAL")
    POSITION_NORMAL(Arrays.asList(new VertexElement[]{new VertexElement(4, 3, 0), new VertexElement(4, 3, 0)}));

    @Expose
    private List<VertexElement> vertexElements = new ArrayList<VertexElement>();
    private int stride = 0;

    VertexFormat(){}

    VertexFormat(List<VertexElement> elements){
        setVertexElements(elements);
    }

    public void addVertexElement(VertexElement element){
        vertexElements.add(element);
    }

    private VertexFormat appendElement(VertexElement element){
        vertexElements.add(element);
        stride += element.getElementCount() * element.getElementSize();
        return this;
    }

    /**
     * 
     * @return
     *     The vertexElements
     */
    public List<VertexElement> getVertexElements() {
        return vertexElements;
    }

    /**
     * 
     * @param vertexElements
     *     The vertexElements
     */
    public void setVertexElements(List<VertexElement> elements) {
        vertexElements.clear();
        for(VertexElement element : elements){
            appendElement(element);
        }
    }

}
