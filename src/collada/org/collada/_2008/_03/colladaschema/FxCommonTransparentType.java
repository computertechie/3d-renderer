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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fx_common_transparent_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fx_common_transparent_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.collada.org/2008/03/COLLADASchema}fx_common_color_or_texture_type">
 *       &lt;attribute name="opaque" type="{http://www.collada.org/2008/03/COLLADASchema}fx_opaque_enum" default="A_ONE" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fx_common_transparent_type")
public class FxCommonTransparentType
    extends FxCommonColorOrTextureType
{

    @XmlAttribute(name = "opaque")
    protected FxOpaqueEnum opaque;

    /**
     * Gets the value of the opaque property.
     * 
     * @return
     *     possible object is
     *     {@link FxOpaqueEnum }
     *     
     */
    public FxOpaqueEnum getOpaque() {
        if (opaque == null) {
            return FxOpaqueEnum.A_ONE;
        } else {
            return opaque;
        }
    }

    /**
     * Sets the value of the opaque property.
     * 
     * @param value
     *     allowed object is
     *     {@link FxOpaqueEnum }
     *     
     */
    public void setOpaque(FxOpaqueEnum value) {
        this.opaque = value;
    }

}
