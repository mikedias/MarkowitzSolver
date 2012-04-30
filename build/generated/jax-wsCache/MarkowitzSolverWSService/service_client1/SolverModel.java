
package service_client1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SolverModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SolverModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pRisk" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pStartDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pEndDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolverModel", propOrder = {
    "pRisk",
    "pValue",
    "pStartDt",
    "pEndDt"
})
public class SolverModel {

    protected double pRisk;
    protected double pValue;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pStartDt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pEndDt;

    /**
     * Gets the value of the pRisk property.
     * 
     */
    public double getPRisk() {
        return pRisk;
    }

    /**
     * Sets the value of the pRisk property.
     * 
     */
    public void setPRisk(double value) {
        this.pRisk = value;
    }

    /**
     * Gets the value of the pValue property.
     * 
     */
    public double getPValue() {
        return pValue;
    }

    /**
     * Sets the value of the pValue property.
     * 
     */
    public void setPValue(double value) {
        this.pValue = value;
    }

    /**
     * Gets the value of the pStartDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPStartDt() {
        return pStartDt;
    }

    /**
     * Sets the value of the pStartDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPStartDt(XMLGregorianCalendar value) {
        this.pStartDt = value;
    }

    /**
     * Gets the value of the pEndDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPEndDt() {
        return pEndDt;
    }

    /**
     * Sets the value of the pEndDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPEndDt(XMLGregorianCalendar value) {
        this.pEndDt = value;
    }

}
