package br.com.MarkowitzSolver.model;

/** Classe responsável por armazenar as informações obtidas pela otimização em valores para investimento.
 * Contém a ação em questão, a proporção investida e o valor investido.
 * @author Mike Dias
 */
public class StockProportion {

    private String stock;
    private double proportion;
    private double value;

    @Override
    public String toString(){
        return getStock() + " Proporção = " + getProportion() + " Valor = " + getValue();
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
     * @return A proporção investida
     */
    public double getProportion() {
        return proportion;
    }

    /**
     * @param proportion A proporção investida
     */
    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    /**
     * @return O valor investido
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value O valor investido
     */
    public void setValue(double value) {
        this.value = value;
    }

}
