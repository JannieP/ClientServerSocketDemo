package net.c0nan.dhs.cs.client.impl;

import net.c0nan.dhs.cs.exceptions.ClientException;
import net.c0nan.dhs.cs.interfaces.IServer;
import net.c0nan.dhs.cs.server.Server;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The class {@code StandaloneClient} extends {@code AbstractClient}
 * and uses {@code Server} to read the contents of a file
 * this class sends a file path to the {@code Server} and receives the content
 * of the file and outputs it to {@code System.out}
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class StandaloneClient extends AbstractClient {
    private static Logger logger = Logger.getLogger(StandaloneClient.class.getName());
    private String filePath;

    /**
     * Constructs an {@code StandaloneClient}
     *
     * @param filePath
     */
    public StandaloneClient(String filePath) {
        this.filePath = filePath;
        setOut();
    }

    /**
     * the generic method defined by the interface {@code IClient}
     *
     * @throws ClientException
     */
    public void runClient() throws ClientException {
        runStandAloneMode();
    }

    /**
     * Gets the {@code Server}
     *
     * @return IServer
     */
    protected IServer getServer() {
        /*
        This method is purely for test case Override
         */
        return new Server();
    }

    /**
     * Main operation to send file path and name to {@code Server}
     * and receives a response back which is sent to {@code System.out}
     *
     * @throws ClientException
     */
    private void runStandAloneMode() throws ClientException {
        IServer server = getServer();
        try {

            String fileContent = server.getFileContent(filePath);
            System.out.print(fileContent);

        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            throw new ClientException(e);
        }

    }

}
