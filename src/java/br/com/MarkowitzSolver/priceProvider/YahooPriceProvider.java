package br.com.MarkowitzSolver.priceProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.com.MarkowitzSolver.model.StockPrice;

/** Provedor de preços que obtém as cotações a partir do servidor do YahooFinance.
 * @author Mike Dias
 * @deprecated Este provedor está depreciado, pois as informações obtidas no servidor Yahoo não são confiáveis
 */
public class YahooPriceProvider implements PriceProvider {

    //Server o Yahoo finance
    final String yahooServer = "http://table.finance.yahoo.com/table.csv";

    /** Método que, dado um periodo e uma ação, retorna uma lista de preços correspondente
     * @param pStock A ação em questão
     * @param pStartDt Data de inicio do período
     * @param pEndDt Data de fim do período
     * @return A lista de preços para o periodo e a ação
     * @throws Exception
     */
    public List<StockPrice> getStockPrice(String pStock, Date pStartDt, Date pEndDt) throws Exception {
        
        List<StockPrice> result = new Vector<StockPrice>();

        Calendar cStartDt = Calendar.getInstance();
        Calendar cEndDt = Calendar.getInstance();

        cStartDt.setTime(pStartDt);
        cEndDt.setTime(pEndDt);

        // montando a url para obter as cotações
        String request = yahooServer
                + "?a=" + cStartDt.get(Calendar.MONTH)
                + "&b=" + cStartDt.get(Calendar.DAY_OF_MONTH)
                + "&c=" + cStartDt.get(Calendar.YEAR)
                + "&d=" + cEndDt.get(Calendar.MONTH)
                + "&e=" + cEndDt.get(Calendar.DAY_OF_MONTH)
                + "&f=" + cEndDt.get(Calendar.YEAR)
                + "&s=" + pStock + ".SA"
                + "&y=0&g=d";

        URL url = new URL(request);

        System.out.println(request);

        InputStream rstream = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));

        Pattern ptDate = Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d)");
        Pattern ptPrice = Pattern.compile("[0-9]+\\.[0-9]+");

        String s;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        while ((s = br.readLine()) != null) {
            Matcher mcDate = ptDate.matcher(s);
            Matcher mcPrice = ptPrice.matcher(s);

            //Usado para testes
            System.out.println(s);

            if ((mcDate.find()) && (mcPrice.find())) {
                StockPrice sp = new StockPrice();

                //Gambiarra pra pegar o ultimo preço
                for (int i = 0; i < 4 ;i++)
                    mcPrice.find();

                sp.setDate(formatter.parse(mcDate.group()));
                sp.setPrice(Double.parseDouble(mcPrice.group()));

                //utilizando em testes
                System.out.println(sp);

                result.add(sp);
            }
        }
        br.close();

        return result;
    }

}
