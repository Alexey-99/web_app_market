package by.koroza.zoo_market.service.validation;

import by.koroza.zoo_market.service.exception.ValidationException;

/**
 * The Interface UserValidation.
 */
public interface UserValidation {

	/**
	 * Validation user email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean validEmail(String email);

	/**
	 * Validation user login.
	 *
	 * @param login the login
	 * @return true, if successful
	 */
	public boolean validLogin(String login);

	/**
	 * Checks if is repeat user login.
	 *
	 * @param login the login
	 * @return true, if is repeat user login
	 * @throws ValidationException the validation exception
	 */
	public boolean isRepeatUserLogin(String login) throws ValidationException;

	/**
	 * Validation user password.
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean validPassword(String password);
}