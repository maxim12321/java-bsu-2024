package by.maxsavitsky.quizer.exceptions;

public class PoolTaskEmptyException extends RuntimeException {

    public PoolTaskEmptyException() {
        super();
    }

    public PoolTaskEmptyException(String message) {
        super(message);
    }

    public PoolTaskEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolTaskEmptyException(Throwable cause) {
        super(cause);
    }

    protected PoolTaskEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
