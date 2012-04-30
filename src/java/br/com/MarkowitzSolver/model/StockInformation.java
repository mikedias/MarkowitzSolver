package br.com.MarkowitzSolver.model;

import java.util.List;

/** Classe responsável por armazenar os medidores utilizados no cálculo da otimização.
 * Contém a Média de Retornos, o Desvio Padrão, as Correlações, as Covariâncias e os Preços para cada ação.
 * @author Mike Dias
 */
public class StockInformation implements Comparable {

    private String stock;
    private double meanReturn;
    private double stdDeviation;
    private List<StockCorrelation> correlations;
    private List<StockCovariance> covariances;
    private List<StockPrice> prices;

    @Override
    public String toString() {
        return getStock() + " " + getMeanReturn();
    }

    /** Construtor padrão
     */
    public StockInformation() {
        super();
    }

    /** Construtor por Ação
     * @param pStock A ação em questão
     */
    public StockInformation(String pStock) {
        super();
        this.setStock(pStock);
    }

    /**
     * @return A ação em questão
     */
    public String getStock() {
        return stock;
    }

    /**
     * @param stock A ação em questão
     */
    public void setStock(String stock) {
        this.stock = stock;
    }

    /**
     * @return A Média de Retornos
     */
    public double getMeanReturn() {
        return meanReturn;
    }

    /**
     * @param meanReturn A Média de Retornos
     */
    public void setMeanReturn(double meanReturn) {
        this.meanReturn = meanReturn;
    }

    /**
     * @return As covariâncias
     */
    public List<StockCovariance> getCovariances() {
        return covariances;
    }

    /**
     * @param covariances As covariâncias
     */
    public void setCovariances(List<StockCovariance> covariances) {
        this.covariances = covariances;
    }

    /**
     * @return Os preços
     */
    public List<StockPrice> getPrices() {
        return prices;
    }

    /**
     * @param prices Os preços
     */
    public void setPrices(List<StockPrice> prices) {
        this.prices = prices;
    }

    public int compareTo(Object o) {
        return this.getStock().compareTo(((StockInformation) o).getStock());
    }

    /**
     * @return O Desvio Padrão
     */
    public double getStdDeviation() {
        return stdDeviation;
    }

    /**
     * @param stdDeviation O Desvio Padrão
     */
    public void setStdDeviation(double stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    /**
     * @return As correlações
     */
    public List<StockCorrelation> getCorrelations() {
        return correlations;
    }

    /**
     * @param correlations As correlações
     */
    public void setCorrelations(List<StockCorrelation> correlations) {
        this.correlations = correlations;
    }
}
