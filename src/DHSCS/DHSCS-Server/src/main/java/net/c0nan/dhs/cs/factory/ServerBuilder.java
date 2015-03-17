package net.c0nan.dhs.cs.factory;

import net.c0nan.dhs.cs.dto.ConnectionDto;
import net.c0nan.dhs.cs.exceptions.PropertiesException;
import net.c0nan.dhs.cs.properties.PropertiesHelper;
import net.c0nan.dhs.cs.server.Server;

import java.io.IOException;

/**
 * The class {@code ServerBuilder}
 * uses the builder pattern to generate the {@code Server}
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class ServerBuilder {

    private Server server = null;
    private ConnectionDto connectionDto;

    /**
     * Gets the connection details from the {@code PropertiesHelper}
     * throws {@code PropertiesException} and {@code IOException}
     */
    public ServerBuilder setupConnection() throws PropertiesException, IOException {
        PropertiesHelper helper = PropertiesHelper.getInstance();
        connectionDto = helper.getConnectionProperties();
        return this;
    }

    /**
     * Creates the {@code Server} and loads the port info
     * throws {@code PropertiesException} and {@code IOException}
     */
    public ServerBuilder setupServer() throws PropertiesException, IOException {
        server = new Server(connectionDto.getPort());
        return this;
    }

    /**
     * Gets the {@code Server}
     */
    public Server build() {
        return server;
    }

}
