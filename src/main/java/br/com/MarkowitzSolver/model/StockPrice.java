package br.com.MarkowitzSolver.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Classe responsável por armazenar o preço da ação em uma determinada data.
 * @author Mike Dias
 */
public class StockPrice implements Comparable {

    private Date date;
    private double price;

    private static final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String toString() {
        return formatter.format(getDate()) + " = " + getPrice();
    }

    /**
     * @return A data do preço
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date A data do preço
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return O valor do preço
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price O valor do preço
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public int compareTo(Object o) {
        return this.getDate().compareTo(((StockPrice)o).getDate());
    }
}
