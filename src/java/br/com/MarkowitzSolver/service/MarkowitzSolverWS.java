package br.com.MarkowitzSolver.service;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import br.com.MarkowitzSolver.controller.CPLEXController;
import br.com.MarkowitzSolver.controller.StockController;
import br.com.MarkowitzSolver.model.Solution;
import br.com.MarkowitzSolver.util.AppLoggerConfig;
import java.util.List;
import org.apache.log4j.Logger;

/**Classe que representa o serviço de otimização, orquestrando as chamadas necessárias para as classes controladoras.
 * @author Mike Dias
 */
@WebService()
public class MarkowitzSolverWS {

    //Configura o log
    static {
        AppLoggerConfig.configure();
    }

    private static final Logger log = Logger.getLogger(MarkowitzSolverWS.class);

    /** Método responsável por obter a solução otimizada baseando-se nos parâmetros.
     * @param pRisk O risco aceito pelo investidor
     * @param pValue O valor disponível para investimento
     * @param pStartDt A data inicial para o cálculo
     * @param pEndDt A data final para o cálculo
     * @return A solução otimizada
     * @throws Exception
     */    
    @WebMethod(operationName = "SolverModel")
    public Solution SolverModel(
            @WebParam(name = "pRisk") double pRisk,
            @WebParam(name = "pValue") double pValue,
            @WebParam(name = "pStartDt") Date pStartDt,
            @WebParam(name = "pEndDt") Date pEndDt) throws Exception {

        try {
            //Valida os parametros
            validate(pRisk, pValue, pStartDt, pEndDt);

            log.info("<-- INICIANDO -->");

            //Obtem a informação nos preços
            StockController sc = new StockController();
            List si = sc.buildStockInfo(pStartDt, pEndDt);

            //Obtem a melhor solução
            CPLEXController cc = new CPLEXController();
            cc.solveModel(pRisk, si);
            List sp = cc.readStockProp(pValue);
            Double r = cc.readPortifolioRisk();

            //Retorna a solução
            Solution s = new Solution();
            s.setPortifolioRisk(r);
            s.setStockInformation(si);
            s.setStockProportion(sp);

            log.info("<-- TERMINANDO -->");

            return s;

        } catch (Exception e) {
            log.error("Ocorreu um erro no processamento: " + e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }
    }

    private void validate(double pRisk, double pValue, Date pStartDt, Date pEndDt) throws Exception {
        if ((pRisk <= 0) || (pRisk >= 1)) {
            throw new Exception("Parâmetro 'pRisk' deve estar entre 0 e 1.");
        }

        if (pValue <= 0) {
            throw new Exception("Parâmetro 'pValue' deve ser maior do que 0.");
        }

        if (pStartDt == null) {
            throw new Exception("Parâmetro 'pStartDt' deve ser uma data válida.");
        }

        if (pEndDt == null) {
            throw new Exception("Parâmetro 'pEndDt' deve ser uma data válida.");
        }

    }
}
