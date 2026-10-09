package exceptions;

public class MontantInvallideException extends Exception{
    public MontantInvallideException() {
    }

    public MontantInvallideException(String message) {
        super(message);
    }

    public MontantInvallideException(String message, Throwable cause) {
        super(message, cause);
    }

    public MontantInvallideException(Throwable cause) {
        super(cause);
    }
}
