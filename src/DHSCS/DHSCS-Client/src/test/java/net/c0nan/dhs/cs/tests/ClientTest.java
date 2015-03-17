package net.c0nan.dhs.cs.tests;

import junit.framework.Assert;
import net.c0nan.dhs.cs.client.IClient;
import net.c0nan.dhs.cs.client.impl.NetworkClient;
import net.c0nan.dhs.cs.client.impl.StandaloneClient;
import net.c0nan.dhs.cs.exceptions.ClientException;
import net.c0nan.dhs.cs.exceptions.PropertiesException;
import net.c0nan.dhs.cs.factory.ServerBuilder;
import net.c0nan.dhs.cs.interfaces.IServer;
import net.c0nan.dhs.cs.properties.PropertiesHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The class {@code ClientTest}
 * is the junit test for the client
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class ClientTest {

    private static String testValue = "TEST1";
    private static String fileName = "./testFile.txt";
    private static String propertiesFileName = "./server.properties";

    @Before
    public void beforTest() {
        setupDataFile();
        setupPropertiesFile();
    }

    private void setupDataFile() {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(testValue);
            fileWriter.close();
        } catch (IOException e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }

    private void setupPropertiesFile() {
        try {

            File propertiesFile = new File(propertiesFileName);
            FileWriter propertiesFileWriter = new FileWriter(propertiesFile);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("#server details\r\n")
                    .append("server.port=1234\r\n")
                    .append("server.address=localhost\r\n")
                    .append("server.filePath=c:\\dhscs\\testFile2.txt");
            propertiesFileWriter.write(stringBuilder.toString());
            propertiesFileWriter.close();
        } catch (IOException e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testClient() {
        ServerBuilder serverBuilder = new ServerBuilder();
        try {

            IClient client;
            PropertiesHelper helper = new PropertiesHelper();

            final IServer serverMock = mock(IServer.class);
            when(serverMock.getFileContent(any(String.class))).thenReturn(testValue);
            String result = serverMock.getFileContent(fileName);
            Assert.assertTrue(testValue.equals(result.trim()));
            client = new StandaloneClient(helper.getFilePathProperty()) {
                @Override
                protected IServer getServer() {
                    return serverMock;
                }
            };
            client.runClient();

            final Socket socket = mock(Socket.class);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);

            final InputStream inputStream = mock(InputStream.class);
            final DataInputStream dataInputStream = new DataInputStream(inputStream);

            client = new NetworkClient(helper.getConnectionProperties(), helper.getFilePathProperty()) {
                @Override
                protected Socket createSocket(String address, Integer port) {
                    return socket;
                }

                @Override
                protected void writeUTF(DataOutputStream dataOutputStream, String filePath) throws IOException {
                    //Do Nothing
                }

                @Override
                protected Boolean readBoolean(DataInputStream dataInputStream) {
                    return true;
                }

                @Override
                protected String readUTF(DataInputStream dataInputStream) {
                    return testValue;
                }
            };

            client.runClient();

        } catch (PropertiesException e) {
            Assert.fail(e.getLocalizedMessage());
        } catch (IOException e) {
            Assert.fail(e.getLocalizedMessage());
        } catch (ClientException e) {
            Assert.fail(e.getLocalizedMessage());
        }

    }

    @After
    public void afterTest() {
        new File(fileName).delete();
        new File(propertiesFileName).delete();
    }


}
