package net.c0nan.dhs.cs.server;

import net.c0nan.dhs.cs.file.FileHelper;
import net.c0nan.dhs.cs.interfaces.IServer;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * The class {@code Server} extends {@code Thread} implements {@code IServer}
 * has a threaded method to avoid hanging up the VM and listens for connection on the set port,
 * which will receive a file path which calls the fileHelper to get the file content and
 * streams the content back to the client over the network connection.
 * The class {@code Server} can be invoked without the network listener
 * and the content of a file can be read.
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class Server extends Thread implements IServer {

    private static Logger logger = Logger.getLogger(Server.class.getName());

    private ServerSocket serverSocket;

    public Server() {
    }

    /**
     * Constructs an {@code NetworkClient}
     *
     * @param port
     * @throws IOException
     */
    public Server(Integer port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    /**
     * Checks if server is bound to port
     * @return boolean
     */
    public boolean isBound(){
        return serverSocket.isBound();
    }

    /**
     * Calls the {@code FileHelper} and get the content of a file based on the filePath supplied
     *
     * @param filePath
     * @return String
     * @throws IOException
     */
    public String getFileContent(String filePath) throws IOException {
        return FileHelper.readFileContent(filePath);
    }

    /**
     * The thread start method activates the listens to a set port for the client to send
     * a file path, and in return streams back the content of the file
     */
    public void run() {
        while (true) {
            try {

                logger.debug("Listening to port: " + serverSocket.getLocalPort());
                Socket socket = serverSocket.accept();

                logger.debug("Connection from: " + socket.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(socket.getInputStream());

                String filePath = in.readUTF();

                String response = null;
                boolean isSuccess = false;

                try {
                    response = getFileContent(filePath);
                    isSuccess = true;
                } catch (IOException e) {
                    response = e.getLocalizedMessage();
                }

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                dataOutputStream.writeBoolean(isSuccess);
                if (isSuccess) {
                    dataOutputStream.writeUTF(response);
                    dataOutputStream.flush();
                }

                socket.close();

            } catch (SocketTimeoutException e) {
                logger.error(e.getLocalizedMessage());
                break;
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage());
                break;
            }
        }
    }
}
