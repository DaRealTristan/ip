package nubish.utils;

/**
 * Represents an error caused by invalid Nubish input or processing.
 */
public class NubishException extends RuntimeException {
    /**
     * Creates an exception without a detail message.
     */
    public NubishException() {
        super();
    }

    /**
     * Creates an exception with the specified detail message.
     */
    public NubishException(String message) {
        super(message);
    }
}
