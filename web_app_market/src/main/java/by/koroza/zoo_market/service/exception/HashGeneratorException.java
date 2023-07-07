package by.koroza.zoo_market.service.exception;

/**
 * The Class HashGeneratorException.
 */
@SuppressWarnings("serial")
public class HashGeneratorException extends Exception {

	/**
	 * Instantiates a new hash generator exception.
	 */
	public HashGeneratorException() {
		super();
	}

	/**
	 * Instantiates a new hash generator exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public HashGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new hash generator exception.
	 *
	 * @param message the message
	 */
	public HashGeneratorException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new hash generator exception.
	 *
	 * @param cause the cause
	 */
	public HashGeneratorException(Throwable cause) {
		super(cause);
	}
}