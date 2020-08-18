package com.logging.jul;

import java.io.FileInputStream;
import java.util.logging.*;

/**
 * @author Hamza Ouni
 */
public class LoggingExample {

    static Logger logger = Logger.getLogger(LoggingExample.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("com/logging/mylogging.properties"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new MyHandler());
        try {
             //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler("C:\\Users\\ho\\Downloads\\logger.log", 2000, 5);
            fileHandler.setFormatter(new MyFormatter());
            //setting custom filter for FileHandler
            fileHandler.setFilter(new MyFilter());
            logger.addHandler(fileHandler);

            for(int i=0; i<1000; i++){
                //logging messages
                logger.log(Level.INFO, "Msg"+i);
            }
            logger.log(Level.CONFIG, "Config data");
        }catch(Exception e2) {
            e2.printStackTrace();
        }

    }
}
