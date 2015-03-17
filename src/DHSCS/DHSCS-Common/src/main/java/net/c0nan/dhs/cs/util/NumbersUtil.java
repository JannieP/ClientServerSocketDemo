package net.c0nan.dhs.cs.util;

/**
 * The class {@code NumbersUtil}
 * some basic static methods to perform number specific functionality
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class NumbersUtil {

    /**
     * Checks if a String value is Numeric
     *
     * @param string
     * @return boolean
     */
    public static boolean isNumeric(String string) {
        return string.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Checks is a String value is a Double
     *
     * @param string
     * @return boolean
     */
    public static boolean isDouble(String string) {
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks is a String value is a Integer
     *
     * @param string
     * @return boolean
     */
    public static boolean isInteger(String string) {
        try {
            int i = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


}
