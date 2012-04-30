package br.com.MarkowitzSolver.priceProvider;

import java.util.Date;
import java.util.List;
import br.com.MarkowitzSolver.model.StockPrice;

/** Interface responsável por encapsular a implementação dos provedores de preços.
 * @author Mike Dias
 */
public interface PriceProvider {

    /** Método que, dado um periodo e uma ação, retorna uma lista de preços correspondente
     * @param pStock A ação em questão
     * @param pStartDt Data de inicio do período
     * @param pEndDt Data de fim do período
     * @return A lista de preços para o periodo e a ação
     * @throws Exception
     */
    public List<StockPrice> getStockPrice(String pStock, Date pStartDt, Date pEndDt) throws Exception;


}
