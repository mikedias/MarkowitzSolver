/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.MarkowitzSolver.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import service_client1.MarkowitzSolverWS;
import service_client1.SolverModelResponse;

/**
 * REST Web Service
 *
 * @author Mike
 */

@Path("markowitzsolverwsport")
public class MarkowitzSolverWSPort {
    private MarkowitzSolverWS port;
    @Context
    private UriInfo context;

    /** Creates a new instance of MarkowitzSolverWSPort */
    public MarkowitzSolverWSPort() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method SolverModel
     * @param pRisk resource URI parameter
     * @param pValue resource URI parameter
     * @param pStartDt resource URI parameter
     * @param pEndDt resource URI parameter
     * @return an instance of javax.xml.bind.JAXBElement<service_client1.SolverModelResponse>
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("solvermodel/")
    public JAXBElement<SolverModelResponse> postSolverModel(double pRisk, double pValue, JAXBElement<XMLGregorianCalendar> pStartDt, JAXBElement<XMLGregorianCalendar> pEndDt) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.util.List<service_client1.StockProportion> result = port.solverModel(pRisk, pValue, pStartDt.getValue(), pEndDt.getValue());

                class SolverModelResponse_1 extends service_client1.SolverModelResponse {

                    SolverModelResponse_1(java.util.List<service_client1.StockProportion> _return) {
                        this._return = _return;
                    }
                }
                service_client1.SolverModelResponse response = new SolverModelResponse_1(result);
                return new service_client1.ObjectFactory().createSolverModelResponse(response);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     *
     */
    private MarkowitzSolverWS getPort() {
        try {
            // Call Web Service Operation
            service_client1.MarkowitzSolverWSService service = new service_client1.MarkowitzSolverWSService();
            service_client1.MarkowitzSolverWS p = service.getMarkowitzSolverWSPort();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
