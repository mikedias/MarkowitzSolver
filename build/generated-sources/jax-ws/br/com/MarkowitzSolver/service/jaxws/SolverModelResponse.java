
package br.com.MarkowitzSolver.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import br.com.MarkowitzSolver.model.Solution;

@XmlRootElement(name = "SolverModelResponse", namespace = "http://service.MarkowitzSolver.com.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolverModelResponse", namespace = "http://service.MarkowitzSolver.com.br/")
public class SolverModelResponse {

    @XmlElement(name = "return", namespace = "")
    private Solution _return;

    /**
     * 
     * @return
     *     returns Solution
     */
    public Solution getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Solution _return) {
        this._return = _return;
    }

}
