package net.c0nan.dhs.cs.tests;

import junit.framework.Assert;
import net.c0nan.dhs.cs.factory.ServerBuilder;
import net.c0nan.dhs.cs.interfaces.IServer;
import net.c0nan.dhs.cs.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * The class {@code ServerTest}
 * is the junit test for the server
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class ServerTest {

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
    public void testServer() {
        ServerBuilder serverBuilder = new ServerBuilder();
        try {

            IServer server = new Server(1234);
            Assert.assertTrue(server.isBound());
            Assert.assertTrue(server instanceof Thread);

            String result = server.getFileContent(fileName);
            Assert.assertTrue(testValue.equals(result.trim()));

            IServer serverMock = mock(IServer.class);
            when(serverMock.getFileContent(any(String.class))).thenReturn(testValue);
            result = serverMock.getFileContent(fileName);
            Assert.assertTrue(testValue.equals(result.trim()));

        } catch (IOException e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @After
    public void afterTest() {
        new File(fileName).delete();
        new File(propertiesFileName).delete();
    }

}
