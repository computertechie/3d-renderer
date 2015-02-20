
package link.snowcat.cubes.generated;

import java.util.ArrayList;
import java.util.List;
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
    private java.lang.String vertexShaderFile;
    @Expose
    private java.lang.String fragmentShaderFile;
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
    private int stride;
    @Expose
    private List<Attribute> vertexAttributes = new ArrayList<Attribute>();
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
     *     The vertexShaderFile
     */
    public java.lang.String getVertexShaderFile() {
        return vertexShaderFile;
    }

    /**
     * 
     * @param vertexShaderFile
     *     The vertexShaderFile
     */
    public void setVertexShaderFile(java.lang.String vertexShaderFile) {
        this.vertexShaderFile = vertexShaderFile;
    }

    /**
     * 
     * @return
     *     The fragmentShaderFile
     */
    public java.lang.String getFragmentShaderFile() {
        return fragmentShaderFile;
    }

    /**
     * 
     * @param fragmentShaderFile
     *     The fragmentShaderFile
     */
    public void setFragmentShaderFile(java.lang.String fragmentShaderFile) {
        this.fragmentShaderFile = fragmentShaderFile;
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
     *     The stride
     */
    public int getStride() {
        return stride;
    }

    /**
     * 
     * @param stride
     *     The stride
     */
    public void setStride(int stride) {
        this.stride = stride;
    }

    /**
     * 
     * @return
     *     The vertexAttributes
     */
    public List<Attribute> getVertexAttributes() {
        return vertexAttributes;
    }

    /**
     * 
     * @param vertexAttributes
     *     The vertexAttributes
     */
    public void setVertexAttributes(List<Attribute> vertexAttributes) {
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
