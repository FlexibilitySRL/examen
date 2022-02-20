package ar.com.plug.examen.domain.util;

import ar.com.plug.examen.domain.constants.ErrorConstants;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerExample {

    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
    public static void main(String[] args) throws SecurityException, IOException {
        try{
            LOGGER.info("Logger Name: "+LOGGER.getName());
            LOGGER.warning(ErrorConstants.API_ERROR);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex);
        }


    }

}
