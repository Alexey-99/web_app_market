package by.koroza.zoo_market.service.validation.impl.user;

import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.ValidationException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.validation.UserValidation;

public final class UserValidationImpl implements UserValidation {
	private static final UserValidation INSTANCE = new UserValidationImpl();

	private static final String REG_EX_EMAIL = "([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})";

	private UserValidationImpl() {
	}

	public static UserValidation getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean validEmail(String email) {
		return email != null && !email.isBlank() && email.trim().matches(REG_EX_EMAIL);
	}

	@Override
	public boolean validLogin(String login) {
		return login != null && !login.isBlank();
	}

	@Override
	public boolean isRepeatUserLogin(String login) throws ValidationException {
		try {
			return UserServiceImpl.getInstance().checkRepeatLogin(login);
		} catch (ServiceException e) {
			throw new ValidationException(e);
		}
	}

	@Override
	public boolean validPassword(String password) {
		return password != null && !password.isBlank();
	}
}