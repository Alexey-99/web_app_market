package by.koroza.zoo_market.web.command.exception;

@SuppressWarnings("serial")
public class CommandException extends Exception {

	public CommandException() {
		super();
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(Throwable cause) {
		super(cause);
	}
}