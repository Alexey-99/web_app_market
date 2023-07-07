package by.koroza.zoo_market.dao.exception.uncheckable;

@SuppressWarnings("serial")
public class ConnectionPoolException extends RuntimeException {

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}
}