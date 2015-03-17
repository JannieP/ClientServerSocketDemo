package net.c0nan.dhs.cs.properties;

import net.c0nan.dhs.cs.dto.ConnectionDto;
import net.c0nan.dhs.cs.exceptions.PropertiesException;
import net.c0nan.dhs.cs.util.NumbersUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The class {@code PropertiesHelper}
 * is a helper class to read the server.properties file
 * and set up a {@code ConnectionDto} object
 * or return a file path
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class PropertiesHelper {

    private static PropertiesHelper _serverPropertiesHelper = null;
    private static final String PROPERTIES_FILE = "./server.properties";
    private Properties properties;

    /**
     * Constructs the {@code PropertiesHelper}
     */
    public static PropertiesHelper getInstance() {
        if (_serverPropertiesHelper == null) {
            _serverPropertiesHelper = new PropertiesHelper();
        }
        return _serverPropertiesHelper;
    }

    /**
     * Loads the properties from the server.properties file
     * in the root of the classpath or internal server.properties
     * file if root file is not found
     *
     * @throws IOException
     */
    private void loadProperties() throws IOException {
        InputStream inputStream = null;
        properties = new Properties();
        try {
            inputStream = new FileInputStream(PROPERTIES_FILE);
        } catch (IOException e) {
            inputStream = ClassLoader.getSystemResourceAsStream("server.properties");
        }
        properties.load(inputStream);
        inputStream.close();
    }

    /**
     * Gets the connection properties from the properties
     *
     * @return {@code ConnectionDto}
     * @throws PropertiesException
     */
    public ConnectionDto getConnectionProperties() throws PropertiesException {
        if (properties == null) {
            try {
                loadProperties();
            } catch (IOException e) {
                throw new PropertiesException(e);
            }
        }

        String portString = properties.getProperty("server.port");
        if (!NumbersUtil.isNumeric(portString)) {
            throw new PropertiesException("Invalid server.port property");
        }

        ConnectionDto connectionDto = new ConnectionDto();

        connectionDto.setPort(Integer.parseInt(portString));
        connectionDto.setServerAddress(properties.getProperty("server.address"));

        return connectionDto;

    }

    /**
     * Gets the file path properties from the properties
     *
     * @return {@code String}
     * @throws PropertiesException
     */
    public String getFilePathProperty() throws PropertiesException {
        if (properties == null) {
            try {
                loadProperties();
            } catch (IOException e) {
                throw new PropertiesException(e);
            }
        }

        return properties.getProperty("server.filePath");
    }


}
