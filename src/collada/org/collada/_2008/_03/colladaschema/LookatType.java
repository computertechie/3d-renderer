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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 			The lookat element contains a position and orientation transformation suitable for aiming a camera.
 * 			The lookat element contains three mathematical vectors within it that describe: 
 * 			1.	The position of the object;
 * 			2.	The position of the interest point;
 * 			3.	The direction that points up.
 * 			
 * 
 * <p>Java class for lookat_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lookat_type">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.collada.org/2008/03/COLLADASchema>float3x3_type">
 *       &lt;attribute name="sid" type="{http://www.collada.org/2008/03/COLLADASchema}sid_type" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lookat_type", propOrder = {
    "values"
})
public class LookatType {

    @XmlValue
    protected List<Double> values;
    @XmlAttribute(name = "sid")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;

    /**
     * Gets the value of the values property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the values property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     * 
     * 
     */
    public List<Double> getValues() {
        if (values == null) {
            values = new ArrayList<Double>();
        }
        return this.values;
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

}
