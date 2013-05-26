//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.26 at 12:07:36 AM MDT 
//


package collada.org.collada._2008._03.colladaschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for common_int_or_param_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="common_int_or_param_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="int" type="{http://www.collada.org/2008/03/COLLADASchema}int_type"/>
 *         &lt;element name="param" type="{http://www.collada.org/2008/03/COLLADASchema}common_param_type"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "common_int_or_param_type", propOrder = {
    "param",
    "_int"
})
@XmlSeeAlso({
    KinematicsIndexType.class
})
public class CommonIntOrParamType {

    protected CommonParamType param;
    @XmlElement(name = "int")
    protected Long _int;

    /**
     * Gets the value of the param property.
     * 
     * @return
     *     possible object is
     *     {@link CommonParamType }
     *     
     */
    public CommonParamType getParam() {
        return param;
    }

    /**
     * Sets the value of the param property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonParamType }
     *     
     */
    public void setParam(CommonParamType value) {
        this.param = value;
    }

    /**
     * Gets the value of the int property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInt() {
        return _int;
    }

    /**
     * Sets the value of the int property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInt(Long value) {
        this._int = value;
    }

}
