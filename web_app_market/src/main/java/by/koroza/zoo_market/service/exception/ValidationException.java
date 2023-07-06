package by.koroza.zoo_market.service.exception;

/**
 * The Class ValidationException.
 */
@SuppressWarnings("serial")
public class ValidationException extends Exception {

	/**
	 * Instantiates a new validation exception.
	 */
	public ValidationException() {
		super();
	}

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param message the message
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param cause the cause
	 */
	public ValidationException(Throwable cause) {
		super(cause);
	}
}