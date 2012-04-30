
package br.com.MarkowitzSolver.service.jaxws;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "SolverModel", namespace = "http://service.MarkowitzSolver.com.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolverModel", namespace = "http://service.MarkowitzSolver.com.br/", propOrder = {
    "pRisk",
    "pValue",
    "pStartDt",
    "pEndDt"
})
public class SolverModel {

    @XmlElement(name = "pRisk", namespace = "")
    private double pRisk;
    @XmlElement(name = "pValue", namespace = "")
    private double pValue;
    @XmlElement(name = "pStartDt", namespace = "")
    private Date pStartDt;
    @XmlElement(name = "pEndDt", namespace = "")
    private Date pEndDt;

    /**
     * 
     * @return
     *     returns double
     */
    public double getPRisk() {
        return this.pRisk;
    }

    /**
     * 
     * @param pRisk
     *     the value for the pRisk property
     */
    public void setPRisk(double pRisk) {
        this.pRisk = pRisk;
    }

    /**
     * 
     * @return
     *     returns double
     */
    public double getPValue() {
        return this.pValue;
    }

    /**
     * 
     * @param pValue
     *     the value for the pValue property
     */
    public void setPValue(double pValue) {
        this.pValue = pValue;
    }

    /**
     * 
     * @return
     *     returns Date
     */
    public Date getPStartDt() {
        return this.pStartDt;
    }

    /**
     * 
     * @param pStartDt
     *     the value for the pStartDt property
     */
    public void setPStartDt(Date pStartDt) {
        this.pStartDt = pStartDt;
    }

    /**
     * 
     * @return
     *     returns Date
     */
    public Date getPEndDt() {
        return this.pEndDt;
    }

    /**
     * 
     * @param pEndDt
     *     the value for the pEndDt property
     */
    public void setPEndDt(Date pEndDt) {
        this.pEndDt = pEndDt;
    }

}
