package br.com.MarkowitzSolver.priceProvider;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import br.com.MarkowitzSolver.model.StockPrice;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/** Provedor de preços que obtém as cotações a partir de arquivos .xls armazenados no servidor.
 * É importante lembrar que os arquivos seguem um determinado padrão que deve ser obedecido.
 * @author Mike Dias
 */
public class FixedXLSPriceProvider implements PriceProvider {

    private static final Logger log = Logger.getLogger(FixedXLSPriceProvider.class);
    final String XLSFolder = "C:\\Users\\Mike\\Documents\\NetBeansProjects\\MarkowitzSolver\\StockPrices\\xls";

    /** Método que, dado um periodo e uma ação, retorna uma lista de preços correspondente
     * @param pStock A ação em questão
     * @param pStartDt Data de inicio do período
     * @param pEndDt Data de fim do período
     * @return A lista de preços para o periodo e a ação
     * @throws Exception
     */
    public List<StockPrice> getStockPrice(String pStock, Date pStartDt, Date pEndDt) throws Exception {

        log.debug("<-- Inicio da carga preços de " + pStock + "-->");

        List<StockPrice> result = new Vector<StockPrice>();

        Workbook wb = new HSSFWorkbook(new FileInputStream(XLSFolder + "\\" + pStock + ".xls"));
        Sheet sheet = wb.getSheetAt(0);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for (Row row : sheet) {
            Cell cDate = row.getCell(0);
            Cell cPrice = row.getCell(1);

            try {
                Date dt = formatter.parse(cDate.getStringCellValue());

                if ((dt.after(pStartDt) || dt.equals(pStartDt)) && ((dt.before(pEndDt) || dt.equals(pEndDt))) && (cPrice.getCellType() == Cell.CELL_TYPE_NUMERIC)) {

                    StockPrice sp = new StockPrice();

                    sp.setDate(dt);
                    sp.setPrice(cPrice.getNumericCellValue());

                    log.debug(pStock + " " + sp);

                    result.add(sp);
                }

            } catch (ParseException pe) {
                //Não era uma data valida na celula, então passa pra proxima linha
            }
        }

        log.debug("<-- Fim da carga preços de " + pStock + " -->");

        return result;

    }
}
