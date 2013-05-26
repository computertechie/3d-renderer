//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.26 at 12:07:36 AM MDT 
//


package collada.org.collada._2008._03.colladaschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for gles_texcombiner_argument_alpha_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gles_texcombiner_argument_alpha_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="source" type="{http://www.collada.org/2008/03/COLLADASchema}gles_texcombiner_source_enum" />
 *       &lt;attribute name="operand" type="{http://www.collada.org/2008/03/COLLADASchema}gles_texcombiner_operand_alpha_enum" default="SRC_ALPHA" />
 *       &lt;attribute name="sampler" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gles_texcombiner_argument_alpha_type")
public class GlesTexcombinerArgumentAlphaType {

    @XmlAttribute(name = "source")
    protected GlesTexcombinerSourceEnum source;
    @XmlAttribute(name = "operand")
    protected GlesTexcombinerOperandAlphaEnum operand;
    @XmlAttribute(name = "sampler")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String sampler;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link GlesTexcombinerSourceEnum }
     *     
     */
    public GlesTexcombinerSourceEnum getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlesTexcombinerSourceEnum }
     *     
     */
    public void setSource(GlesTexcombinerSourceEnum value) {
        this.source = value;
    }

    /**
     * Gets the value of the operand property.
     * 
     * @return
     *     possible object is
     *     {@link GlesTexcombinerOperandAlphaEnum }
     *     
     */
    public GlesTexcombinerOperandAlphaEnum getOperand() {
        if (operand == null) {
            return GlesTexcombinerOperandAlphaEnum.SRC_ALPHA;
        } else {
            return operand;
        }
    }

    /**
     * Sets the value of the operand property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlesTexcombinerOperandAlphaEnum }
     *     
     */
    public void setOperand(GlesTexcombinerOperandAlphaEnum value) {
        this.operand = value;
    }

    /**
     * Gets the value of the sampler property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSampler() {
        return sampler;
    }

    /**
     * Sets the value of the sampler property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSampler(String value) {
        this.sampler = value;
    }

}
