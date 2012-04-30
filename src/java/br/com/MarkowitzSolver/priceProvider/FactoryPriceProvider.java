package br.com.MarkowitzSolver.priceProvider;

/** Classe responsável por retornar a classe que é utilizada atualmente para obter as cotações.
 * @author Mike Dias
 */
public class FactoryPriceProvider {

    /** Método que estancia a classe que é utilizada atualmente para obter as cotações
     * @return A classe que é utilizada atualmente para obter as cotações
     */
    public static PriceProvider getCurrentPriceProvider(){

        return new FixedXLSPriceProvider();

        //TODO: No futuro, Tentar recuperar do valor economico
        //TODO: No futuro, Tentar recuperar da folha
        //TODO: No futuro, Tentar recuperar do InfoMoney
    }

}
