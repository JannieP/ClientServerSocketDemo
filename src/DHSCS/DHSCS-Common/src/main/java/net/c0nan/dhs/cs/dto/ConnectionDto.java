package net.c0nan.dhs.cs.dto;

/**
 * The class {@code ConnectionDto}
 * is the data transfer object to relay the connection settings
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class ConnectionDto {
    private Integer port = null;
    private String serverAddress = null;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
