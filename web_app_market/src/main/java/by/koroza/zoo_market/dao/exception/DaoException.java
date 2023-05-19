package by.koroza.zoo_market.dao.exception;

@SuppressWarnings("serial")
public class DaoException extends Exception {

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException() {
		super();
	}
}