package by.koroza.zoo_market.web.command.exception;

@SuppressWarnings("serial")
public class RuntimeCommandException extends RuntimeException {

	public RuntimeCommandException() {
		super();
	}

	public RuntimeCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuntimeCommandException(String message) {
		super(message);
	}

	public RuntimeCommandException(Throwable cause) {
		super(cause);
	}
}