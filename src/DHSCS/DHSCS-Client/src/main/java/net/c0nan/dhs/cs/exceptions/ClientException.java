package net.c0nan.dhs.cs.exceptions;

/**
 * The class {@code ClientException}
 * used when exceptions occur in the Client
 *
 * @author Jannie
 *         Date: 2013/07/13
 */
public class ClientException extends Exception {
    /**
     * Constructs {@code ClientException}
     */
    public ClientException() {
        super();
    }

    /**
     * Constructs an {@code ClientException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public ClientException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code ClientException} with the specified detail message
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
    public ClientException(String message, Throwable cause) {
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
    public ClientException(Throwable cause) {
        super(cause);
    }
}
