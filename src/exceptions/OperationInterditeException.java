package exceptions;

public class OperationInterditeException extends Exception{
    public OperationInterditeException() {
    }

    public OperationInterditeException(String message) {
        super(message);
    }

    public OperationInterditeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationInterditeException(Throwable cause) {
        super(cause);
    }
}
