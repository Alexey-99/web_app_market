package by.koroza.zoo_market.service.validation.impl.user;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.ValidationException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.validation.UserValidation;

/**
 * The Class UserValidationImpl.
 */
public final class UserValidationImpl implements UserValidation {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final UserValidation INSTANCE = new UserValidationImpl();

	/** The Constant REG_EX_EMAIL. */
	private static final String REG_EX_EMAIL = "([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,})\\.([A-z]{2,8})";

	/**
	 * Instantiates a new user validation impl.
	 */
	private UserValidationImpl() {
	}

	/**
	 * Get the single instance of UserValidationImpl.
	 *
	 * @return single instance of UserValidationImpl
	 */
	public static UserValidation getInstance() {
		return INSTANCE;
	}

	/**
	 * Validation email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	@Override
	public boolean validEmail(String email) {
		return email != null && !email.isBlank() && email.trim().matches(REG_EX_EMAIL);
	}

	/**
	 * Validation login.
	 *
	 * @param login the login
	 * @return true, if successful
	 */
	@Override
	public boolean validLogin(String login) {
		return login != null && !login.isBlank();
	}

	/**
	 * Check if is repeat user login.
	 *
	 * @param login the login
	 * @return true, if is repeat user login
	 * @throws ValidationException the validation exception
	 */
	@Override
	public boolean isRepeatUserLogin(String login) throws ValidationException {
		try {
			return UserServiceImpl.getInstance().checkRepeatLogin(login);
		} catch (ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ValidationException(e);
		}
	}

	/**
	 * Validation password.
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	@Override
	public boolean validPassword(String password) {
		return password != null && !password.isBlank();
	}
}