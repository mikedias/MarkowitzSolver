package br.com.MarkowitzSolver.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.com.MarkowitzSolver.model.StockCovariance;
import br.com.MarkowitzSolver.model.StockInformation;
import br.com.MarkowitzSolver.model.StockProportion;
import org.apache.log4j.Logger;

/** Classe controladora que tem a inteligência do motor CPLEX, responsável por montar, executar e ler o modelo CPLEX.
 * @author Mike Dias
 */
public class CPLEXController {

    private final String mainPath = "C:\\ILOG-CPLEX\\AMPL";
    private final String modelPath = "MarkowitzModel";
    private static final Logger log = Logger.getLogger(CPLEXController.class);

    //Constroi o arquivo .dat
    private void buildDat(double pRisk, List<StockInformation> pStockInfo) throws FileNotFoundException {

        File file = new File(mainPath + "\\" + modelPath + "\\MarkowitzAMPL.dat");
        PrintWriter pw = new PrintWriter(file);

        /*RISCO*/
        pw.println("param risk := " + pRisk + ";");

        pw.println("");

        /*AÇÕES*/
        pw.print("set STOCKS := ");
        int i = 0;
        for (StockInformation si : pStockInfo) {
            if (i < pStockInfo.size() - 1) {
                pw.print(si.getStock() + ", ");
            } else {
                pw.println(si.getStock() + ";");
            }

            i++;
        }

        pw.println("");

        /*MÉDIA DE RETORNOS*/
        pw.println("param meanReturn := ");
        for (StockInformation si : pStockInfo) {
            pw.println("		" + si.getStock() + " " + si.getMeanReturn());

        }
        pw.println(";");

        pw.println("");

        /*MATRIZ DE COVARIANCIA*/
        pw.print("param covariance : 		        ");
        for (StockInformation si : pStockInfo) {
            pw.print(si.getStock() + "     ");
        }
        pw.println(" :=");
        for (StockInformation si : pStockInfo) {
            pw.print("					" + si.getStock() + "  ");
            for (StockCovariance sc : si.getCovariances()) {
                pw.print(sc.getCovariance() + "     ");
            }
            pw.println("");
        }
        pw.println(";");

        pw.flush();
        pw.close();


    }

    //Constroi o arquivo .run
    private void buildRun() throws FileNotFoundException {

        File file = new File(mainPath + "\\" + modelPath + "\\MarkowitzAMPL.run");
        PrintWriter pw = new PrintWriter(file);

        pw.println("reset;");
        pw.println("option solver cplex;");
        pw.println("model " + mainPath + "\\" + modelPath + "\\MarkowitzAMPL.mod;");
        pw.println("data " + mainPath + "\\" + modelPath + "\\MarkowitzAMPL.dat;");
        pw.println("solve;");
        pw.println("display proportion > " + mainPath + "\\" + modelPath + "\\proportion.txt;");
        pw.println("display obj > " + mainPath + "\\" + modelPath + "\\obj.txt;");

        pw.flush();
        pw.close();


    }

    //Constroi o arquivo .bat
    private void buildBat() throws FileNotFoundException {

        File file = new File(mainPath + "\\" + modelPath + "\\MarkowitzAMPL.bat");
        PrintWriter pw = new PrintWriter(file);

        pw.println("cd " + mainPath);
        pw.println("ampl " + modelPath + "\\MarkowitzAMPL.run");

        pw.flush();
        pw.close();


    }

    //Constroi o arquivo .mod
    private void buildMod() throws IOException {
        File file = new File(mainPath + "\\" + modelPath + "\\MarkowitzAMPL.mod");
        PrintWriter pw = new PrintWriter(file);

        pw.println("#Vetor de ações");
        pw.println("set STOCKS;");
        pw.println("");
        pw.println("#Parametros de calculo");
        pw.println("param risk;");
        pw.println("param meanReturn{STOCKS};");
        pw.println("param covariance {STOCKS, STOCKS};");
        pw.println("");
        pw.println("#proporções");
        pw.println("var proportion{STOCKS};");
        pw.println("");
        pw.println("#Formula");
        pw.println("minimize obj: risk * sum{i in STOCKS, j in STOCKS} proportion[i] * proportion[j] * covariance[i, j] - (1-risk) * sum{i in STOCKS} proportion[i] * meanReturn[i];");
        pw.println("s.t. constrains: sum{i in STOCKS} proportion[i] = 1;");
        pw.println("end;");

        pw.flush();
        pw.close();

    }

    
    /** Método que controi o modelo CPLEX e o executa, obtendo a solução otimizada.
     * @param pRisk O risco aceito pelo investidor
     * @param pStockInfo As informações utilizadas para o cálculo
     * @throws IOException
     * @throws InterruptedException
     */
    public void solveModel(double pRisk, List<StockInformation> pStockInfo) throws IOException, InterruptedException {

        buildDat(pRisk, pStockInfo);
        buildMod();
        buildRun();
        buildBat();

        Process pr = Runtime.getRuntime().exec(mainPath + "\\" + modelPath + "\\MarkowitzAMPL.bat");
        pr.waitFor();

    }

    
    /**Método que lê as proporções calculadas pelo modelo CPLEX.
     * @param pValue O valor investido
     * @return A lista de proporções calculada
     * @throws IOException
     */
    public List<StockProportion> readStockProp(double pValue) throws IOException {

        List<StockProportion> result = new Vector<StockProportion>();

        //Lê as proporções
        File file = new File(mainPath + "\\" + modelPath + "\\proportion.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        //TODO: Melhorar a forma de ler os numeros do arquivo
        Pattern ptStock = Pattern.compile("\\w\\w\\w\\w\\w");
        Pattern ptProp = Pattern.compile("-?[0-9]+\\.[0-9]+");

        log.info("<-- RESULTADO -->");

        String s;
        while ((s = br.readLine()) != null) {
            Matcher mcStock = ptStock.matcher(s);
            Matcher mcProp = ptProp.matcher(s);

            if ((mcStock.find()) && (mcProp.find())) {
                StockProportion sp = new StockProportion();

                sp.setStock(mcStock.group());
                sp.setProportion(Double.parseDouble(mcProp.group()));
                sp.setValue(Double.parseDouble(mcProp.group()) * pValue);
                result.add(sp);

                log.info(sp);
            }
        }
        br.close();

        return result;
    }

    /**Método que lê o risco da carteira calculado pelo modelo CPLEX.
     * @return O risco da carteira
     * @throws IOException
     */
    public Double readPortifolioRisk() throws IOException {

        Double result = 0.0;

        //Lê o risco da carteria
        File file = new File(mainPath + "\\" + modelPath + "\\obj.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        Pattern ptObj = Pattern.compile("-?[0-9]+\\.[0-9]+");

        String s = null;
        while ((s = br.readLine()) != null) {
            Matcher mcObj = ptObj.matcher(s);
            if (mcObj.find()) {
                result = Double.parseDouble(mcObj.group());
            }
        }
        br.close();

        return result;

    }
}
