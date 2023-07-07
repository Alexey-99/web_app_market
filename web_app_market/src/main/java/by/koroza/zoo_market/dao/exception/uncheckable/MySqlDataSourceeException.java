package by.koroza.zoo_market.dao.exception.uncheckable;

@SuppressWarnings("serial")
public class MySqlDataSourceeException extends RuntimeException {

	public MySqlDataSourceeException() {
		super();
	}

	public MySqlDataSourceeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MySqlDataSourceeException(String message) {
		super(message);
	}

	public MySqlDataSourceeException(Throwable cause) {
		super(cause);
	}
}