package by.koroza.zoo_market.dao.exception.uncheckable;

/**
 * The Class ConnectionPoolException.
 */
@SuppressWarnings("serial")
public class ConnectionPoolException extends RuntimeException {

	/**
	 * Instantiates a new connection pool exception.
	 */
	public ConnectionPoolException() {
		super();
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message the message
	 */
	public ConnectionPoolException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param cause the cause
	 */
	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}
}