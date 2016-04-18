package br.com.MarkowitzSolver.controller;

import br.com.MarkowitzSolver.model.StockCorrelation;
import br.com.MarkowitzSolver.model.StockCovariance;
import br.com.MarkowitzSolver.model.StockInformation;
import br.com.MarkowitzSolver.model.StockPrice;
import br.com.MarkowitzSolver.priceProvider.FactoryPriceProvider;
import br.com.MarkowitzSolver.priceProvider.PriceProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**Classe controladora que possui a inteligência dos medidores relacionados as ações.
 * @author Mike Dias
 */
@Slf4j
public class StockController {

    //TODO: Colocar as ações do IBOV em um arquivo e carregar em uma lista
    //Vetor de ações
    private final String[] stocks = {"GFSA3", "OGXP3", "PETR4", "VALE5"};

    /** Método responsável por obter as informações necessárias para o cálculo do modelo.
     * @param pStartDt A data inicial para o cálculo
     * @param pEndDt A data final para o cálculo
     * @return As informações necessárias para o cálculo
     * @throws Exception
     */
    public List<StockInformation> buildStockInfo(Date pStartDt, Date pEndDt) throws Exception {

        List<StockInformation> result = new Vector<StockInformation>();

        //Obtem o provedor de preços atual
        PriceProvider pp = FactoryPriceProvider.getCurrentPriceProvider();

        //Varre todo o vetor de ações
        for (int i = 0; i < stocks.length; i++) {
            StockInformation si = new StockInformation(stocks[i]);

            //obtem os preços da ação
            List<StockPrice> sp = pp.getStockPrice(stocks[i], pStartDt, pEndDt);

            //Ordena pela Data
            Collections.sort(sp);

            si.setPrices(sp);

            result.add(si);

        }

        Collections.sort(result);

        //Calcula os medidores do modelo
        calculateMeasurers(result);

        return result;

    }

    private void calculateMeasurers(List<StockInformation> pListStockInfo) {

        //1 - Média de Retornos
        log.debug("<-- Inicio do cálculo da Média de Retornos -->");
        for (StockInformation si : pListStockInfo) {

            si.setMeanReturn(calcMeanReturn(si.getPrices()));

            log.debug("Média de Retornos de '" + si.getStock() + "': " + si.getMeanReturn());
        }
        log.debug("<-- Fim do cálculo da Média de Retornos -->");

        //2 - Desvio Padrão
        log.debug("<-- Inicio do cálculo do Desvio Padrão -->");
        for (StockInformation si : pListStockInfo) {

            si.setStdDeviation(calcStdDeviation(si.getPrices()));

            log.debug("Desvio Padrão de '" + si.getStock() + "': " + si.getStdDeviation());
        }
        log.debug("<-- Fim do cálculo do Desvio Padrão -->");


        //3 - Matriz de Covariância
        log.debug("<-- Inicio do cálculo da Covariância -->");
        for (StockInformation si : pListStockInfo) {

            si.setCovariances(calcCovariance(si, pListStockInfo));

        }
        log.debug("<-- Fim do cálculo da Covariância -->");

        //4 - Matriz de Correlação
        log.debug("<-- Inicio do cálculo da Correlação -->");
        for (StockInformation si : pListStockInfo) {

            si.setCorrelations(calcCorrelation(si, pListStockInfo));

        }
        log.debug("<-- Fim do cálculo da Correlação -->");

    }

    private double calcMeanReturn(List<StockPrice> prices) {
        double result = 0;
        double lastPrice = 0;

        for (StockPrice sp : prices) {
            if (lastPrice > 0) {
                result += calcReturn(sp.getPrice(), lastPrice);
            }

            lastPrice = sp.getPrice();
        }

        return result / (prices.size() - 1);

    }

    private double calcReturn(double p1, double p2) {
        return Math.log(p1) - Math.log(p2);
    }

    private double calcStdDeviation(List<StockPrice> prices) {
        //Este medidor não foi implementado por não ser necessário na resolução do modelo.
        //Porém, para estudos mais abrangentes, ele se faz necessário.
        //Portanto, sua implementação foi prevista na arquitetura
        return 0;
    }

    private List<StockCovariance> calcCovariance(StockInformation si_i, List<StockInformation> pListStockInfo) {

        List<StockCovariance> listCov = new Vector<StockCovariance>();

        for (StockInformation si_j : pListStockInfo) {

            double sumCovar = 0;

            double lastPrice_i = 0;
            double lastPrice_j = 0;

            for (int i = 0; i < si_j.getPrices().size(); i++) {

                StockPrice sp_i = si_i.getPrices().get(i);
                StockPrice sp_j = si_j.getPrices().get(i);

                if ((lastPrice_i > 0) && (lastPrice_j > 0)) {
                    sumCovar += (calcReturn(sp_i.getPrice(), lastPrice_i) - si_i.getMeanReturn())
                            * (calcReturn(sp_j.getPrice(), lastPrice_j) - si_j.getMeanReturn());
                }

                lastPrice_i = sp_i.getPrice();
                lastPrice_j = sp_j.getPrice();
            }

            sumCovar = sumCovar / (si_j.getPrices().size() - 1);

            StockCovariance sc = new StockCovariance();

            sc.setStock(si_j.getStock());
            sc.setCovariance(sumCovar);

            log.debug("Covariancia de '" + si_i.getStock() + "' para '" + si_j.getStock() + "': " + sumCovar);

            listCov.add(sc);

        }
        Collections.sort(listCov);

        return listCov;
    }

    private List<StockCorrelation> calcCorrelation(StockInformation si_i, List<StockInformation> pListStockInfo) {
        //Este medidor não foi implementado por não ser necessário na resolução do modelo.
        //Porém, para estudos mais abrangentes, ele se faz necessário.
        //Portanto, sua implementação foi prevista na arquitetura
        return new Vector<StockCorrelation>();
    }
}
