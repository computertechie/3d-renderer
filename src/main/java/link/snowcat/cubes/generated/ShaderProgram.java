
package link.snowcat.cubes.generated;

import java.util.Map;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;


/**
 * A fully compiled and linked shader program (a rendering process).
 * 
 */
@Generated("org.jsonschema2pojo")
public class ShaderProgram {

    @Expose
    private java.lang.String renderProcessName;
    @Expose
    private int vertexShaderID;
    @Expose
    private int fragmentShaderID;
    @Expose
    private int programID;
    @Expose
    private int projectionMatrixLocation;
    @Expose
    private int viewMatrixLocation;
    @Expose
    private int modelMatrixLocation;
    @Expose
    private Map<String,  Integer> vertexAttributes;
    @Expose
    private Map<String,  Integer> uniformAttributes;

    /**
     * 
     * @return
     *     The renderProcessName
     */
    public java.lang.String getRenderProcessName() {
        return renderProcessName;
    }

    /**
     * 
     * @param renderProcessName
     *     The renderProcessName
     */
    public void setRenderProcessName(java.lang.String renderProcessName) {
        this.renderProcessName = renderProcessName;
    }

    /**
     * 
     * @return
     *     The vertexShaderID
     */
    public int getVertexShaderID() {
        return vertexShaderID;
    }

    /**
     * 
     * @param vertexShaderID
     *     The vertexShaderID
     */
    public void setVertexShaderID(int vertexShaderID) {
        this.vertexShaderID = vertexShaderID;
    }

    /**
     * 
     * @return
     *     The fragmentShaderID
     */
    public int getFragmentShaderID() {
        return fragmentShaderID;
    }

    /**
     * 
     * @param fragmentShaderID
     *     The fragmentShaderID
     */
    public void setFragmentShaderID(int fragmentShaderID) {
        this.fragmentShaderID = fragmentShaderID;
    }

    /**
     * 
     * @return
     *     The programID
     */
    public int getProgramID() {
        return programID;
    }

    /**
     * 
     * @param programID
     *     The programID
     */
    public void setProgramID(int programID) {
        this.programID = programID;
    }

    /**
     * 
     * @return
     *     The projectionMatrixLocation
     */
    public int getProjectionMatrixLocation() {
        return projectionMatrixLocation;
    }

    /**
     * 
     * @param projectionMatrixLocation
     *     The projectionMatrixLocation
     */
    public void setProjectionMatrixLocation(int projectionMatrixLocation) {
        this.projectionMatrixLocation = projectionMatrixLocation;
    }

    /**
     * 
     * @return
     *     The viewMatrixLocation
     */
    public int getViewMatrixLocation() {
        return viewMatrixLocation;
    }

    /**
     * 
     * @param viewMatrixLocation
     *     The viewMatrixLocation
     */
    public void setViewMatrixLocation(int viewMatrixLocation) {
        this.viewMatrixLocation = viewMatrixLocation;
    }

    /**
     * 
     * @return
     *     The modelMatrixLocation
     */
    public int getModelMatrixLocation() {
        return modelMatrixLocation;
    }

    /**
     * 
     * @param modelMatrixLocation
     *     The modelMatrixLocation
     */
    public void setModelMatrixLocation(int modelMatrixLocation) {
        this.modelMatrixLocation = modelMatrixLocation;
    }

    /**
     * 
     * @return
     *     The vertexAttributes
     */
    public Map<String,  Integer> getVertexAttributes() {
        return vertexAttributes;
    }

    /**
     * 
     * @param vertexAttributes
     *     The vertexAttributes
     */
    public void setVertexAttributes(Map<String,  Integer> vertexAttributes) {
        this.vertexAttributes = vertexAttributes;
    }

    /**
     * 
     * @return
     *     The uniformAttributes
     */
    public Map<String,  Integer> getUniformAttributes() {
        return uniformAttributes;
    }

    /**
     * 
     * @param uniformAttributes
     *     The uniformAttributes
     */
    public void setUniformAttributes(Map<String,  Integer> uniformAttributes) {
        this.uniformAttributes = uniformAttributes;
    }

}
