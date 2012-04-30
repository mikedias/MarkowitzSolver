package br.com.MarkowitzSolver.util;

import org.apache.log4j.xml.DOMConfigurator;
/**
 *
 * @author Mike
 */
public class AppLoggerConfig {
    
    private static final String LOG4J_FILE_CONFIG = "C:\\Users\\Mike\\Documents\\NetBeansProjects\\MarkowitzSolver\\src\\java\\LOG4J.xml";

    public static void configure(){
       DOMConfigurator.configure(LOG4J_FILE_CONFIG);
    }


}
