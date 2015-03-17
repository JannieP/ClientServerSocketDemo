package net.c0nan.dhs.cs.out;

import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * The class {@code OutputStream}
 * is an interceptor for an OutputStream e.g.: System.out
 * and redirects the print and println outputs to logger
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class OutputStream extends PrintStream {
    private Logger logger = Logger.getLogger(OutputStream.class.getName());

    /**
     * Construct {@code net.c0nan.dhs.cs.out.OutputStream}
     * @param outputStream
     */
    public OutputStream(java.io.OutputStream outputStream){
        super(outputStream, true);
    }

    /**
     * Send a string to the Logger.info stream
     * @param message
     */
    @Override
    public void print(String message){
        logger.info(message);
    }

    /**
     * Send a string to the Logger.info stream
     * @param message
     */
    @Override
    public void println(String message){
        logger.info(message);
    }
}
