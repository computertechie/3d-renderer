//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.26 at 12:07:36 AM MDT 
//


package collada.org.collada._2008._03.colladaschema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gles_texcombiner_operator_rgb_enum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="gles_texcombiner_operator_rgb_enum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="REPLACE"/>
 *     &lt;enumeration value="MODULATE"/>
 *     &lt;enumeration value="ADD"/>
 *     &lt;enumeration value="ADD_SIGNED"/>
 *     &lt;enumeration value="INTERPOLATE"/>
 *     &lt;enumeration value="SUBTRACT"/>
 *     &lt;enumeration value="DOT3_RGB"/>
 *     &lt;enumeration value="DOT3_RGBA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "gles_texcombiner_operator_rgb_enum")
@XmlEnum
public enum GlesTexcombinerOperatorRgbEnum {

    REPLACE("REPLACE"),
    MODULATE("MODULATE"),
    ADD("ADD"),
    ADD_SIGNED("ADD_SIGNED"),
    INTERPOLATE("INTERPOLATE"),
    SUBTRACT("SUBTRACT"),
    @XmlEnumValue("DOT3_RGB")
    DOT_3_RGB("DOT3_RGB"),
    @XmlEnumValue("DOT3_RGBA")
    DOT_3_RGBA("DOT3_RGBA");
    private final String value;

    GlesTexcombinerOperatorRgbEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GlesTexcombinerOperatorRgbEnum fromValue(String v) {
        for (GlesTexcombinerOperatorRgbEnum c: GlesTexcombinerOperatorRgbEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
