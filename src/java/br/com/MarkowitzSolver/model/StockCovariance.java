package br.com.MarkowitzSolver.model;

/** Classe que representa o valor da covariância de uma ação em questão.
 * @author Mike Dias
 */
public class StockCovariance implements Comparable{

    private String stock;
    private double covariance;

    @Override
    public String toString(){
        return getStock() + " " + getCovariance();
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
     * @return O valor da covariância
     */
    public double getCovariance() {
        return covariance;
    }

    /**
     * @param covariance O valor da covariância
     */
    public void setCovariance(double covariance) {
        this.covariance = covariance;
    }

    public int compareTo(Object o) {
        return this.getStock().compareTo(((StockCovariance)o).getStock());
    }

}
