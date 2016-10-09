package c.okami.core.exception;

public class DigestException extends RuntimeException {
    public DigestException() {
        super();
    }

    public DigestException(String message) {
        super(message);
    }

    public DigestException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigestException(Throwable cause) {
        super(cause);
    }

    protected DigestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
