package net.c0nan.dhs.cs.client.impl;

import net.c0nan.dhs.cs.dto.ConnectionDto;
import net.c0nan.dhs.cs.exceptions.ClientException;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * The class {@code NetworkClient} extends {@code AbstractClient}
 * and access the network server via an address and port.
 * this class sends a file path to the server and receives the content
 * of the file and outputs it to {@code System.out}
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class NetworkClient extends AbstractClient {
    private static Logger logger = Logger.getLogger(NetworkClient.class.getName());
    private String filePath;
    private ConnectionDto connectionDto;

    /**
     * Constructs an {@code NetworkClient}
     *
     * @param connectionDto Contains server address and port
     * @param filePath path the the file to read
     */
    public NetworkClient(ConnectionDto connectionDto, String filePath) {
        this.connectionDto = connectionDto;
        this.filePath = filePath;
        setOut();
    }

    /**
     * The generic method defined by the interface {@code IClient}
     *
     * @throws ClientException
     */
    public void runClient() throws ClientException {
        runNetworkMode();
    }

    /**
     * Creates {@code Socket}
     *
     * @throws IOException
     */
    protected Socket createSocket(String address, Integer port) throws IOException {
         /*
        This method is purely for test case Override
         */
        return new Socket(address, port);
    }

    /**
     * Gets {@code DataInputStream}
     *
     * @param inputStream
     * @return DataInputStream
     */
    protected DataInputStream getDataInputStream(InputStream inputStream) {
         /*
        This method is purely for test case Override
         */
        return new DataInputStream(inputStream);
    }

    /**
     * Writes to the {@code DataOutputStream}
     *
     * @param dataOutputStream
     * @param filePath
     * @throws IOException
     */
    protected void writeUTF(DataOutputStream dataOutputStream, String filePath) throws IOException {
         /*
        This method is purely for test case Override
         */
        dataOutputStream.writeUTF(filePath);
    }

    /**
     * Reads boolean from {@code DataInputStream}
     *
     * @param dataInputStream
     * @return Boolean
     * @throws IOException
     */
    protected Boolean readBoolean(DataInputStream dataInputStream) throws IOException {
         /*
        This method is purely for test case Override
         */
        return dataInputStream.readBoolean();
    }

    /**
     * Reads string from {@code DataInputStream}
     *
     * @param dataInputStream
     * @return String
     * @throws IOException
     */
    protected String readUTF(DataInputStream dataInputStream) throws IOException {
         /*
        This method is purely for test case Override
         */
        return dataInputStream.readUTF();
    }

    /**
     * Main operation to send file path and name to {@code Socket}
     * and receives a response  back which is sent to {@code System.out}
     *
     * @throws ClientException
     */
    private void runNetworkMode() throws ClientException {
        try {

            Socket clientSocket = createSocket(connectionDto.getServerAddress(), connectionDto.getPort());

            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            writeUTF(dataOutputStream, filePath);

            DataInputStream dataInputStream = getDataInputStream(clientSocket.getInputStream());

            boolean isSuccess = readBoolean(dataInputStream);
            if (isSuccess) {

                String fileContent = readUTF(dataInputStream);
                System.out.print(fileContent);

            } else {
                logger.error("FAILED.");
            }

            clientSocket.close();

        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            throw new ClientException(e);
        }

    }

}
