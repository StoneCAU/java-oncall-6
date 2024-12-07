package oncall.exception;

public class OncallException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR] ";

    public OncallException(ErrorMessage errorMessage) {
        super(PREFIX + errorMessage.getMessage());
    }
}
