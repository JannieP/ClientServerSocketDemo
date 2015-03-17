package net.c0nan.dhs.cs.exceptions;

/**
 * The class {@code PropertiesException}
 * used when exceptions occur in the Properties Helper
 *
 * @author Jannie
 *         Date: 2013/07/13
 */

public class PropertiesException extends Exception {
    /**
     * Constructs an {@code PropertiesException} with {@code null}
     * as its error detail message.
     */
    public PropertiesException() {
        super();
    }

    /**
     * Constructs an {@code PropertiesException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public PropertiesException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code PropertiesException} with the specified detail message
     * and cause.
     * <p/>
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     * @param cause   The cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A null value is permitted,
     *                and indicates that the cause is nonexistent or unknown.)
     */
    public PropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code PropertiesException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for IO exceptions that are little more
     * than wrappers for other throwables.
     *
     * @param cause The cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public PropertiesException(Throwable cause) {
        super(cause);
    }
}
