//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.26 at 12:07:36 AM MDT 
//


package collada.org.w3._1998.math.mathml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * <p>Java class for set.type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="set.type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://www.w3.org/1998/Math/MathML}set.content" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;attGroup ref="{http://www.w3.org/1998/Math/MathML}set.attlist"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "set.type", propOrder = {
    "cnsAndCisAndCsymbols"
})
@XmlRootElement(name = "set")
public class Set {

    @XmlElementRefs({
        @XmlElementRef(name = "divide", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "vectorproduct", namespace = "http://www.w3.org/1998/Math/MathML", type = Vectorproduct.class, required = false),
        @XmlElementRef(name = "mpadded", namespace = "http://www.w3.org/1998/Math/MathML", type = Mpadded.class, required = false),
        @XmlElementRef(name = "union", namespace = "http://www.w3.org/1998/Math/MathML", type = Union.class, required = false),
        @XmlElementRef(name = "complexes", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "times", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "geq", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "implies", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "notin", namespace = "http://www.w3.org/1998/Math/MathML", type = Notin.class, required = false),
        @XmlElementRef(name = "plus", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "msub", namespace = "http://www.w3.org/1998/Math/MathML", type = Msub.class, required = false),
        @XmlElementRef(name = "outerproduct", namespace = "http://www.w3.org/1998/Math/MathML", type = Outerproduct.class, required = false),
        @XmlElementRef(name = "or", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "real", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "lcm", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "interval", namespace = "http://www.w3.org/1998/Math/MathML", type = Interval.class, required = false),
        @XmlElementRef(name = "list", namespace = "http://www.w3.org/1998/Math/MathML", type = org.w3._1998.math.mathml.List.class, required = false),
        @XmlElementRef(name = "mn", namespace = "http://www.w3.org/1998/Math/MathML", type = Mn.class, required = false),
        @XmlElementRef(name = "in", namespace = "http://www.w3.org/1998/Math/MathML", type = In.class, required = false),
        @XmlElementRef(name = "domain", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "compose", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "forall", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mspace", namespace = "http://www.w3.org/1998/Math/MathML", type = Mspace.class, required = false),
        @XmlElementRef(name = "maligngroup", namespace = "http://www.w3.org/1998/Math/MathML", type = Maligngroup.class, required = false),
        @XmlElementRef(name = "arctan", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cn", namespace = "http://www.w3.org/1998/Math/MathML", type = Cn.class, required = false),
        @XmlElementRef(name = "variance", namespace = "http://www.w3.org/1998/Math/MathML", type = Variance.class, required = false),
        @XmlElementRef(name = "imaginary", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "rem", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "transpose", namespace = "http://www.w3.org/1998/Math/MathML", type = Transpose.class, required = false),
        @XmlElementRef(name = "abs", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arcsec", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "conjugate", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "grad", namespace = "http://www.w3.org/1998/Math/MathML", type = Grad.class, required = false),
        @XmlElementRef(name = "inverse", namespace = "http://www.w3.org/1998/Math/MathML", type = Inverse.class, required = false),
        @XmlElementRef(name = "exists", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "minus", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mrow", namespace = "http://www.w3.org/1998/Math/MathML", type = Mrow.class, required = false),
        @XmlElementRef(name = "arccosh", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "int", namespace = "http://www.w3.org/1998/Math/MathML", type = Int.class, required = false),
        @XmlElementRef(name = "infinity", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "exp", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "integers", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ident", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "semantics", namespace = "http://www.w3.org/1998/Math/MathML", type = Semantics.class, required = false),
        @XmlElementRef(name = "reals", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "factorial", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mean", namespace = "http://www.w3.org/1998/Math/MathML", type = Mean.class, required = false),
        @XmlElementRef(name = "neq", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tan", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "msqrt", namespace = "http://www.w3.org/1998/Math/MathML", type = Msqrt.class, required = false),
        @XmlElementRef(name = "codomain", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "csymbol", namespace = "http://www.w3.org/1998/Math/MathML", type = Csymbol.class, required = false),
        @XmlElementRef(name = "mtable", namespace = "http://www.w3.org/1998/Math/MathML", type = Mtable.class, required = false),
        @XmlElementRef(name = "arcsinh", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arcsech", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "setdiff", namespace = "http://www.w3.org/1998/Math/MathML", type = Setdiff.class, required = false),
        @XmlElementRef(name = "mphantom", namespace = "http://www.w3.org/1998/Math/MathML", type = Mphantom.class, required = false),
        @XmlElementRef(name = "factorof", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "msubsup", namespace = "http://www.w3.org/1998/Math/MathML", type = Msubsup.class, required = false),
        @XmlElementRef(name = "leq", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arccos", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "maction", namespace = "http://www.w3.org/1998/Math/MathML", type = Maction.class, required = false),
        @XmlElementRef(name = "ms", namespace = "http://www.w3.org/1998/Math/MathML", type = Ms.class, required = false),
        @XmlElementRef(name = "lambda", namespace = "http://www.w3.org/1998/Math/MathML", type = Lambda.class, required = false),
        @XmlElementRef(name = "primes", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tendsto", namespace = "http://www.w3.org/1998/Math/MathML", type = Tendsto.class, required = false),
        @XmlElementRef(name = "ci", namespace = "http://www.w3.org/1998/Math/MathML", type = Ci.class, required = false),
        @XmlElementRef(name = "coth", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "pi", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "gt", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "malignmark", namespace = "http://www.w3.org/1998/Math/MathML", type = Malignmark.class, required = false),
        @XmlElementRef(name = "imaginaryi", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "sin", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ceiling", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cartesianproduct", namespace = "http://www.w3.org/1998/Math/MathML", type = Cartesianproduct.class, required = false),
        @XmlElementRef(name = "vector", namespace = "http://www.w3.org/1998/Math/MathML", type = Vector.class, required = false),
        @XmlElementRef(name = "csch", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tanh", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "power", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arctanh", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mroot", namespace = "http://www.w3.org/1998/Math/MathML", type = Mroot.class, required = false),
        @XmlElementRef(name = "curl", namespace = "http://www.w3.org/1998/Math/MathML", type = Curl.class, required = false),
        @XmlElementRef(name = "domainofapplication", namespace = "http://www.w3.org/1998/Math/MathML", type = Domainofapplication.class, required = false),
        @XmlElementRef(name = "xor", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "matrix", namespace = "http://www.w3.org/1998/Math/MathML", type = Matrix.class, required = false),
        @XmlElementRef(name = "not", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cot", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arccot", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "sdev", namespace = "http://www.w3.org/1998/Math/MathML", type = Sdev.class, required = false),
        @XmlElementRef(name = "munder", namespace = "http://www.w3.org/1998/Math/MathML", type = Munder.class, required = false),
        @XmlElementRef(name = "gcd", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "condition", namespace = "http://www.w3.org/1998/Math/MathML", type = Condition.class, required = false),
        @XmlElementRef(name = "scalarproduct", namespace = "http://www.w3.org/1998/Math/MathML", type = Scalarproduct.class, required = false),
        @XmlElementRef(name = "mstyle", namespace = "http://www.w3.org/1998/Math/MathML", type = Mstyle.class, required = false),
        @XmlElementRef(name = "momentabout", namespace = "http://www.w3.org/1998/Math/MathML", type = Momentabout.class, required = false),
        @XmlElementRef(name = "declare", namespace = "http://www.w3.org/1998/Math/MathML", type = Declare.class, required = false),
        @XmlElementRef(name = "quotient", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "uplimit", namespace = "http://www.w3.org/1998/Math/MathML", type = Uplimit.class, required = false),
        @XmlElementRef(name = "apply", namespace = "http://www.w3.org/1998/Math/MathML", type = Apply.class, required = false),
        @XmlElementRef(name = "set", namespace = "http://www.w3.org/1998/Math/MathML", type = Set.class, required = false),
        @XmlElementRef(name = "laplacian", namespace = "http://www.w3.org/1998/Math/MathML", type = Laplacian.class, required = false),
        @XmlElementRef(name = "sech", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "rationals", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "card", namespace = "http://www.w3.org/1998/Math/MathML", type = Card.class, required = false),
        @XmlElementRef(name = "selector", namespace = "http://www.w3.org/1998/Math/MathML", type = Selector.class, required = false),
        @XmlElementRef(name = "mode", namespace = "http://www.w3.org/1998/Math/MathML", type = Mode.class, required = false),
        @XmlElementRef(name = "subset", namespace = "http://www.w3.org/1998/Math/MathML", type = Subset.class, required = false),
        @XmlElementRef(name = "mi", namespace = "http://www.w3.org/1998/Math/MathML", type = Mi.class, required = false),
        @XmlElementRef(name = "product", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mtext", namespace = "http://www.w3.org/1998/Math/MathML", type = Mtext.class, required = false),
        @XmlElementRef(name = "true", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arccsch", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cos", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mmultiscripts", namespace = "http://www.w3.org/1998/Math/MathML", type = Mmultiscripts.class, required = false),
        @XmlElementRef(name = "determinant", namespace = "http://www.w3.org/1998/Math/MathML", type = Determinant.class, required = false),
        @XmlElementRef(name = "divergence", namespace = "http://www.w3.org/1998/Math/MathML", type = Divergence.class, required = false),
        @XmlElementRef(name = "eulergamma", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "lowlimit", namespace = "http://www.w3.org/1998/Math/MathML", type = Lowlimit.class, required = false),
        @XmlElementRef(name = "false", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "equivalent", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "logbase", namespace = "http://www.w3.org/1998/Math/MathML", type = Logbase.class, required = false),
        @XmlElementRef(name = "eq", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "image", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "max", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "notanumber", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mfrac", namespace = "http://www.w3.org/1998/Math/MathML", type = Mfrac.class, required = false),
        @XmlElementRef(name = "bvar", namespace = "http://www.w3.org/1998/Math/MathML", type = Bvar.class, required = false),
        @XmlElementRef(name = "arccoth", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "emptyset", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "sec", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "approx", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arcsin", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mfenced", namespace = "http://www.w3.org/1998/Math/MathML", type = Mfenced.class, required = false),
        @XmlElementRef(name = "and", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "prsubset", namespace = "http://www.w3.org/1998/Math/MathML", type = Prsubset.class, required = false),
        @XmlElementRef(name = "merror", namespace = "http://www.w3.org/1998/Math/MathML", type = Merror.class, required = false),
        @XmlElementRef(name = "floor", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "median", namespace = "http://www.w3.org/1998/Math/MathML", type = Median.class, required = false),
        @XmlElementRef(name = "partialdiff", namespace = "http://www.w3.org/1998/Math/MathML", type = Partialdiff.class, required = false),
        @XmlElementRef(name = "lt", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "naturalnumbers", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "root", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mo", namespace = "http://www.w3.org/1998/Math/MathML", type = Mo.class, required = false),
        @XmlElementRef(name = "log", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arg", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ln", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "menclose", namespace = "http://www.w3.org/1998/Math/MathML", type = Menclose.class, required = false),
        @XmlElementRef(name = "arccsc", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "exponentiale", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mover", namespace = "http://www.w3.org/1998/Math/MathML", type = Mover.class, required = false),
        @XmlElementRef(name = "sinh", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "notsubset", namespace = "http://www.w3.org/1998/Math/MathML", type = Notsubset.class, required = false),
        @XmlElementRef(name = "notprsubset", namespace = "http://www.w3.org/1998/Math/MathML", type = Notprsubset.class, required = false),
        @XmlElementRef(name = "cosh", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "piecewise", namespace = "http://www.w3.org/1998/Math/MathML", type = Piecewise.class, required = false),
        @XmlElementRef(name = "min", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "moment", namespace = "http://www.w3.org/1998/Math/MathML", type = Moment.class, required = false),
        @XmlElementRef(name = "diff", namespace = "http://www.w3.org/1998/Math/MathML", type = Diff.class, required = false),
        @XmlElementRef(name = "intersect", namespace = "http://www.w3.org/1998/Math/MathML", type = Intersect.class, required = false),
        @XmlElementRef(name = "degree", namespace = "http://www.w3.org/1998/Math/MathML", type = Degree.class, required = false),
        @XmlElementRef(name = "csc", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "limit", namespace = "http://www.w3.org/1998/Math/MathML", type = Limit.class, required = false),
        @XmlElementRef(name = "msup", namespace = "http://www.w3.org/1998/Math/MathML", type = Msup.class, required = false),
        @XmlElementRef(name = "sum", namespace = "http://www.w3.org/1998/Math/MathML", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "munderover", namespace = "http://www.w3.org/1998/Math/MathML", type = Munderover.class, required = false)
    })
    protected java.util.List<Object> cnsAndCisAndCsymbols;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "class")
    @XmlSchemaType(name = "NMTOKENS")
    protected java.util.List<String> clazzs;
    @XmlAttribute(name = "style")
    protected String style;
    @XmlAttribute(name = "xref")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object xref;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String href;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the cnsAndCisAndCsymbols property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cnsAndCisAndCsymbols property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCnsAndCisAndCsymbols().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Vectorproduct }
     * {@link Mpadded }
     * {@link Union }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Notin }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link LogicType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link LogicType }{@code >}
     * {@link Outerproduct }
     * {@link Msub }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link org.w3._1998.math.mathml.List }
     * {@link Interval }
     * {@link Mn }
     * {@link In }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link FunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link FunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link LogicType }{@code >}
     * {@link Mspace }
     * {@link Maligngroup }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Cn }
     * {@link Variance }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Transpose }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Grad }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link LogicType }{@code >}
     * {@link Inverse }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Mrow }
     * {@link Int }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link FunctionsType }{@code >}
     * {@link Semantics }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link Mean }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link FunctionsType }{@code >}
     * {@link Msqrt }
     * {@link Csymbol }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Mtable }
     * {@link Setdiff }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link Mphantom }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link Msubsup }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Lambda }
     * {@link Ms }
     * {@link Maction }
     * {@link Ci }
     * {@link Tendsto }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Malignmark }
     * {@link Cartesianproduct }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Vector }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Mroot }
     * {@link Domainofapplication }
     * {@link Curl }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link LogicType }{@code >}
     * {@link Matrix }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link LogicType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Sdev }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Munder }
     * {@link Scalarproduct }
     * {@link Condition }
     * {@link Momentabout }
     * {@link Mstyle }
     * {@link Declare }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Uplimit }
     * {@link collada.org.w3._1998.math.mathml.Set }
     * {@link Apply }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Laplacian }
     * {@link Card }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Selector }
     * {@link Subset }
     * {@link Mode }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Mi }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Mtext }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Determinant }
     * {@link Mmultiscripts }
     * {@link Divergence }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Lowlimit }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Logbase }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link FunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Mfrac }
     * {@link Bvar }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Prsubset }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Mfenced }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Merror }
     * {@link Partialdiff }
     * {@link Median }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link RelationsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Mo }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Menclose }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ConstantType }{@code >}
     * {@link Mover }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Notsubset }
     * {@link Notprsubset }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Piecewise }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Moment }
     * {@link Diff }
     * {@link Intersect }
     * {@link Degree }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ElementaryFunctionsType }{@code >}
     * {@link Limit }
     * {@link Msup }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ArithType }{@code >}
     * {@link Munderover }
     * 
     * 
     */
    public java.util.List<Object> getCnsAndCisAndCsymbols() {
        if (cnsAndCisAndCsymbols == null) {
            cnsAndCisAndCsymbols = new ArrayList<Object>();
        }
        return this.cnsAndCisAndCsymbols;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the clazzs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clazzs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClazzs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public java.util.List<String> getClazzs() {
        if (clazzs == null) {
            clazzs = new ArrayList<String>();
        }
        return this.clazzs;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyle(String value) {
        this.style = value;
    }

    /**
     * Gets the value of the xref property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getXref() {
        return xref;
    }

    /**
     * Sets the value of the xref property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setXref(Object value) {
        this.xref = value;
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
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
