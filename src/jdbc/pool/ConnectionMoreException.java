package jdbc.pool;

public class ConnectionMoreException extends RuntimeException {
    public ConnectionMoreException(String message) {
        super(message);
    }
}
