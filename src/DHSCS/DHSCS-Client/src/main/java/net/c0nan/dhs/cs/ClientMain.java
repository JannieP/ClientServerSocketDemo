package net.c0nan.dhs.cs;

import net.c0nan.dhs.cs.client.IClient;
import net.c0nan.dhs.cs.client.impl.NetworkClient;
import net.c0nan.dhs.cs.client.impl.StandaloneClient;
import net.c0nan.dhs.cs.enums.ClientModeEnum;
import net.c0nan.dhs.cs.exceptions.ClientException;
import net.c0nan.dhs.cs.exceptions.PropertiesException;
import net.c0nan.dhs.cs.properties.PropertiesHelper;

import java.util.logging.Logger;

/**
 * The class {@code ClientMain}
 * defines the execution point for the client
 * and takes 1 parameter
 * {@code ClientModeEnum.NETWORK} to run in network mode
 * OR
 * {@code ClientModeEnum.STANDALONE} to run in standalone mode
 * and no parameter to run in standard mode
 *
 * @param {@code String -> ClientModeEnum}
 * @author Jannie
 *         Date: 2013/07/13
 */
public class ClientMain {
    private static Logger logger = Logger.getLogger(ClientMain.class.getName());

    public static void main(String[] args) {

        logger.fine("Started Client");

        IClient client = null;
        PropertiesHelper helper = new PropertiesHelper();
        String parameter = null;

        if (args.length == 0) {
            logger.severe("Invalid parameter, should be NETWORK or STANDALONE");
        } else {
            parameter = args[0];
        }

        try {

            if (ClientModeEnum.STANDALONE.name().equals(parameter)) {
                client = new StandaloneClient(helper.getFilePathProperty());
            } else if (ClientModeEnum.NETWORK.name().equals(parameter)) {
                client = new NetworkClient(helper.getConnectionProperties(), helper.getFilePathProperty());
            }

            if (client != null) {
                client.runClient();
            }

        } catch (PropertiesException e) {
            logger.severe(e.getLocalizedMessage());
        } catch (ClientException e) {
            logger.severe(e.getLocalizedMessage());
        }

    }
}
