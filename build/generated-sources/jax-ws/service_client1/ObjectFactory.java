
package service_client1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service_client1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SolverModelResponse_QNAME = new QName("http://service/", "SolverModelResponse");
    private final static QName _SolverModel_QNAME = new QName("http://service/", "SolverModel");
    private final static QName _IOException_QNAME = new QName("http://service/", "IOException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service_client1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link StockProportion }
     * 
     */
    public StockProportion createStockProportion() {
        return new StockProportion();
    }

    /**
     * Create an instance of {@link SolverModel }
     * 
     */
    public SolverModel createSolverModel() {
        return new SolverModel();
    }

    /**
     * Create an instance of {@link SolverModelResponse }
     * 
     */
    public SolverModelResponse createSolverModelResponse() {
        return new SolverModelResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolverModelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "SolverModelResponse")
    public JAXBElement<SolverModelResponse> createSolverModelResponse(SolverModelResponse value) {
        return new JAXBElement<SolverModelResponse>(_SolverModelResponse_QNAME, SolverModelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolverModel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "SolverModel")
    public JAXBElement<SolverModel> createSolverModel(SolverModel value) {
        return new JAXBElement<SolverModel>(_SolverModel_QNAME, SolverModel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

}
