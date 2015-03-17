package net.c0nan.dhs.cs;

import net.c0nan.dhs.cs.exceptions.PropertiesException;
import net.c0nan.dhs.cs.factory.ServerBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 *
 * The class {@code ServerMain}
 * defines the execution point for the server
 *
 * @author Jannie
 * Date: 2013/07/13
 *
 */
public class ServerMain {

    private static Logger logger = Logger.getLogger(ServerMain.class.getName());

    public static void main(String[] args) {

        logger.debug("Started Server");

        try {

            Thread serverThread = new ServerBuilder().setupConnection().setupServer().build();
            serverThread.start();

        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        } catch (PropertiesException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

}
