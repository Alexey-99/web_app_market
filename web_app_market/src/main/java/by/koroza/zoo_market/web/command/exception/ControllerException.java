package by.koroza.zoo_market.web.command.exception;

@SuppressWarnings("serial")
public class ControllerException extends RuntimeException {

	public ControllerException() {
		super();
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}
}