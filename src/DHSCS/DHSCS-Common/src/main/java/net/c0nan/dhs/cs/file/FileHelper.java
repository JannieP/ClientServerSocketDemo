package net.c0nan.dhs.cs.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The class {@code FileHelper}
 * reads a file based of the supplied filePath
 * and returns the file content
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class FileHelper {

    /**
     * Reads the content of a specified file and returns it
     *
     * @param filePath
     * @return String
     * @throws IOException
     */
    public static String readFileContent(String filePath) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(filePath));

            String fileLine = bufferedReader.readLine();

            while (fileLine != null) {
                stringBuilder.append(fileLine);
                stringBuilder.append("\n");
                fileLine = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                //No error should happen
            }
        }
        return stringBuilder.toString();
    }
}
