package com.logging.jul;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * @author Hamza Ouni
 */
public class MyHandler extends StreamHandler {

    @Override
    public void publish(LogRecord record) {
        //add own logic to publish
        super.publish(record);
    }


    @Override
    public void flush() {
        super.flush();
    }


    @Override
    public void close() throws SecurityException {
        super.close();
    }
}
