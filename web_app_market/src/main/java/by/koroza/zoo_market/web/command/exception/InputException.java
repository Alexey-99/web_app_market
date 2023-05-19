package by.koroza.zoo_market.web.command.exception;

@SuppressWarnings("serial")
public class InputException extends Exception {

	public InputException() {
		super();
	}

	public InputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputException(String message) {
		super(message);
	}

	public InputException(Throwable cause) {
		super(cause);
	}
}