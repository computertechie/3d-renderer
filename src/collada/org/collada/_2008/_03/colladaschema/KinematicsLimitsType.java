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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for kinematics_limits_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="kinematics_limits_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="min" type="{http://www.collada.org/2008/03/COLLADASchema}common_float_or_param_type"/>
 *         &lt;element name="max" type="{http://www.collada.org/2008/03/COLLADASchema}common_float_or_param_type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "kinematics_limits_type", propOrder = {
    "min",
    "max"
})
public class KinematicsLimitsType {

    @XmlElement(required = true)
    protected CommonFloatOrParamType min;
    @XmlElement(required = true)
    protected CommonFloatOrParamType max;

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link CommonFloatOrParamType }
     *     
     */
    public CommonFloatOrParamType getMin() {
        return min;
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonFloatOrParamType }
     *     
     */
    public void setMin(CommonFloatOrParamType value) {
        this.min = value;
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link CommonFloatOrParamType }
     *     
     */
    public CommonFloatOrParamType getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonFloatOrParamType }
     *     
     */
    public void setMax(CommonFloatOrParamType value) {
        this.max = value;
    }

}
