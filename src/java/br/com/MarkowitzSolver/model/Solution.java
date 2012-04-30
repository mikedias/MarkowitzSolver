package br.com.MarkowitzSolver.model;

import java.util.List;

/**Classe que contém os dados resultantes à otimização.
 * Possui a lista de proporções calculada, o risco total da carteira e as informações utilizadas para o cálculo.
 * @author Mike Dias
 */
public class Solution {
    
    private double portifolioRisk;
    private List<StockProportion> stockProportion;
    private List<StockInformation> stockInformation;

    /**
     * @return o risco total da carteira
     */
    public double getPortifolioRisk() {
        return portifolioRisk;
    }

    /**
     * @param portifolioRisk o risco total da carteira
     */
    public void setPortifolioRisk(double portifolioRisk) {
        this.portifolioRisk = portifolioRisk;
    }

    /**
     * @return a lista de proporções calculada
     */
    public List<StockProportion> getStockProportion() {
        return stockProportion;
    }

    /**
     * @param stockProportion a lista de proporções calculada
     */
    public void setStockProportion(List<StockProportion> stockProportion) {
        this.stockProportion = stockProportion;
    }

    /**
     * @return as informações utilizadas para o cálculo
     */
    public List<StockInformation> getStockInformation() {
        return stockInformation;
    }

    /**
     * @param stockInformation informações utilizadas para o cálculo
     */
    public void setStockInformation(List<StockInformation> stockInformation) {
        this.stockInformation = stockInformation;
    }

}
