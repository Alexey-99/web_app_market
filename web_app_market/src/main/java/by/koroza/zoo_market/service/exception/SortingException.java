package by.koroza.zoo_market.service.exception;

/**
 * The Class SortingException.
 */
@SuppressWarnings("serial")
public class SortingException extends Exception {

	/**
	 * Instantiates a new sorting exception.
	 */
	public SortingException() {
		super();
	}

	/**
	 * Instantiates a new sorting exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public SortingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new sorting exception.
	 *
	 * @param message the message
	 */
	public SortingException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new sorting exception.
	 *
	 * @param cause the cause
	 */
	public SortingException(Throwable cause) {
		super(cause);
	}
}