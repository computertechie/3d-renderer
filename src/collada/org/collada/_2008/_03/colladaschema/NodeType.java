//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.26 at 12:07:36 AM MDT 
//


package collada.org.collada._2008._03.colladaschema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 			Nodes embody the hierarchical relationship of elements in the scene.
 * 			
 * 
 * <p>Java class for node_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="node_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="asset" type="{http://www.collada.org/2008/03/COLLADASchema}asset_type" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="lookat" type="{http://www.collada.org/2008/03/COLLADASchema}lookat_type"/>
 *           &lt;element name="matrix" type="{http://www.collada.org/2008/03/COLLADASchema}matrix_type"/>
 *           &lt;element name="rotate" type="{http://www.collada.org/2008/03/COLLADASchema}rotate_type"/>
 *           &lt;element name="scale" type="{http://www.collada.org/2008/03/COLLADASchema}scale_type"/>
 *           &lt;element name="skew" type="{http://www.collada.org/2008/03/COLLADASchema}skew_type"/>
 *           &lt;element name="translate" type="{http://www.collada.org/2008/03/COLLADASchema}translate_type"/>
 *         &lt;/choice>
 *         &lt;element name="instance_camera" type="{http://www.collada.org/2008/03/COLLADASchema}instance_camera_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="instance_controller" type="{http://www.collada.org/2008/03/COLLADASchema}instance_controller_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="instance_geometry" type="{http://www.collada.org/2008/03/COLLADASchema}instance_geometry_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="instance_light" type="{http://www.collada.org/2008/03/COLLADASchema}instance_light_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="instance_node" type="{http://www.collada.org/2008/03/COLLADASchema}instance_node_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="node" type="{http://www.collada.org/2008/03/COLLADASchema}node_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extra" type="{http://www.collada.org/2008/03/COLLADASchema}extra_type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="sid" type="{http://www.collada.org/2008/03/COLLADASchema}sid_type" />
 *       &lt;attribute name="type" type="{http://www.collada.org/2008/03/COLLADASchema}node_enum" default="NODE" />
 *       &lt;attribute name="layer" type="{http://www.collada.org/2008/03/COLLADASchema}list_of_names_type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "node_type", propOrder = {
    "asset",
    "lookatsAndMatrixesAndRotates",
    "instanceCameras",
    "instanceControllers",
    "instanceGeometries",
    "instanceLights",
    "instanceNodes",
    "nodes",
    "extras"
})
public class NodeType {

    protected AssetType asset;
    @XmlElements({
        @XmlElement(name = "lookat", type = LookatType.class),
        @XmlElement(name = "matrix", type = MatrixType.class),
        @XmlElement(name = "rotate", type = RotateType.class),
        @XmlElement(name = "scale", type = ScaleType.class),
        @XmlElement(name = "skew", type = SkewType.class),
        @XmlElement(name = "translate", type = TranslateType.class)
    })
    protected List<Object> lookatsAndMatrixesAndRotates;
    @XmlElement(name = "instance_camera")
    protected List<InstanceCameraType> instanceCameras;
    @XmlElement(name = "instance_controller")
    protected List<InstanceControllerType> instanceControllers;
    @XmlElement(name = "instance_geometry")
    protected List<InstanceGeometryType> instanceGeometries;
    @XmlElement(name = "instance_light")
    protected List<InstanceLightType> instanceLights;
    @XmlElement(name = "instance_node")
    protected List<InstanceNodeType> instanceNodes;
    @XmlElement(name = "node")
    protected List<NodeType> nodes;
    @XmlElement(name = "extra")
    protected List<ExtraType> extras;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String name;
    @XmlAttribute(name = "sid")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    @XmlAttribute(name = "type")
    protected NodeEnum type;
    @XmlAttribute(name = "layer")
    protected List<String> layers;

    /**
     * Gets the value of the asset property.
     * 
     * @return
     *     possible object is
     *     {@link AssetType }
     *     
     */
    public AssetType getAsset() {
        return asset;
    }

    /**
     * Sets the value of the asset property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetType }
     *     
     */
    public void setAsset(AssetType value) {
        this.asset = value;
    }

    /**
     * Gets the value of the lookatsAndMatrixesAndRotates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lookatsAndMatrixesAndRotates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLookatsAndMatrixesAndRotates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LookatType }
     * {@link MatrixType }
     * {@link RotateType }
     * {@link ScaleType }
     * {@link SkewType }
     * {@link TranslateType }
     * 
     * 
     */
    public List<Object> getLookatsAndMatrixesAndRotates() {
        if (lookatsAndMatrixesAndRotates == null) {
            lookatsAndMatrixesAndRotates = new ArrayList<Object>();
        }
        return this.lookatsAndMatrixesAndRotates;
    }

    /**
     * Gets the value of the instanceCameras property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instanceCameras property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstanceCameras().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceCameraType }
     * 
     * 
     */
    public List<InstanceCameraType> getInstanceCameras() {
        if (instanceCameras == null) {
            instanceCameras = new ArrayList<InstanceCameraType>();
        }
        return this.instanceCameras;
    }

    /**
     * Gets the value of the instanceControllers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instanceControllers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstanceControllers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceControllerType }
     * 
     * 
     */
    public List<InstanceControllerType> getInstanceControllers() {
        if (instanceControllers == null) {
            instanceControllers = new ArrayList<InstanceControllerType>();
        }
        return this.instanceControllers;
    }

    /**
     * Gets the value of the instanceGeometries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instanceGeometries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstanceGeometries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceGeometryType }
     * 
     * 
     */
    public List<InstanceGeometryType> getInstanceGeometries() {
        if (instanceGeometries == null) {
            instanceGeometries = new ArrayList<InstanceGeometryType>();
        }
        return this.instanceGeometries;
    }

    /**
     * Gets the value of the instanceLights property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instanceLights property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstanceLights().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceLightType }
     * 
     * 
     */
    public List<InstanceLightType> getInstanceLights() {
        if (instanceLights == null) {
            instanceLights = new ArrayList<InstanceLightType>();
        }
        return this.instanceLights;
    }

    /**
     * Gets the value of the instanceNodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instanceNodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstanceNodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceNodeType }
     * 
     * 
     */
    public List<InstanceNodeType> getInstanceNodes() {
        if (instanceNodes == null) {
            instanceNodes = new ArrayList<InstanceNodeType>();
        }
        return this.instanceNodes;
    }

    /**
     * Gets the value of the nodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link collada.org.collada._2008._03.colladaschema.NodeType }
     * 
     * 
     */
    public List<NodeType> getNodes() {
        if (nodes == null) {
            nodes = new ArrayList<NodeType>();
        }
        return this.nodes;
    }

    /**
     * Gets the value of the extras property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extras property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtras().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtraType }
     * 
     * 
     */
    public List<ExtraType> getExtras() {
        if (extras == null) {
            extras = new ArrayList<ExtraType>();
        }
        return this.extras;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the sid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSid() {
        return sid;
    }

    /**
     * Sets the value of the sid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSid(String value) {
        this.sid = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link NodeEnum }
     *     
     */
    public NodeEnum getType() {
        if (type == null) {
            return NodeEnum.NODE;
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link NodeEnum }
     *     
     */
    public void setType(NodeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the layers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLayers() {
        if (layers == null) {
            layers = new ArrayList<String>();
        }
        return this.layers;
    }

}
