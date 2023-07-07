package by.koroza.zoo_market.dao.exception.uncheckable;

/**
 * The Class MySqlDataSourceeException.
 */
@SuppressWarnings("serial")
public class MySqlDataSourceeException extends RuntimeException {

	/**
	 * Instantiates a new my sql data sourcee exception.
	 */
	public MySqlDataSourceeException() {
		super();
	}

	/**
	 * Instantiates a new my sql data sourcee exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public MySqlDataSourceeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new my sql data sourcee exception.
	 *
	 * @param message the message
	 */
	public MySqlDataSourceeException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new my sql data sourcee exception.
	 *
	 * @param cause the cause
	 */
	public MySqlDataSourceeException(Throwable cause) {
		super(cause);
	}
}