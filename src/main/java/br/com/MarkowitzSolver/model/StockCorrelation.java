package br.com.MarkowitzSolver.model;

/** Classe que representa o valor da correlação de uma ação em questão.
 * @author Mike Dias
 */
    public class StockCorrelation implements Comparable {

    private String stock;
    private double correlation;

    @Override
    public String toString(){
        return getStock() + " " + getCorrelation();
    }


    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Não implementado");
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
     * @return O valor da correlação
     */
    public double getCorrelation() {
        return correlation;
    }

    /**
     * @param correlation O valor da correlação
     */
    public void setCorrelation(double correlation) {
        this.correlation = correlation;
    }
}
