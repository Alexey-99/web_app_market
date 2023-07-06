package by.koroza.zoo_market.service.validation.impl;

import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;

public class UserValidation {
	private static final String REG_EX_EMAIL = "([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})";

	public static boolean validEmail(String email) {
		return isNotNull(email) && isNotEmpty(email) && isCorrectEmailByPattern(email);
	}

	public static boolean validLogin(String login) throws ServiceException {
		return isNotNull(login) && isNotEmpty(login);
	}

	public static boolean isRepeatUserLogin(String login) throws ServiceException {
		return UserServiceImpl.getInstance().checkRepeatLogin(login);
	}

	public static boolean validPassword(String password) {
		return isNotNull(password) && isNotEmpty(password);
	}

	private static boolean isNotNull(String line) {
		return line != null;
	}

	private static boolean isNotEmpty(String line) {
		return !line.isBlank();
	}

	private static boolean isCorrectEmailByPattern(String email) {
		return email.matches(REG_EX_EMAIL);
	}
}