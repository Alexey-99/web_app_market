package by.koroza.zoo_market.service.validation;

import by.koroza.zoo_market.service.exception.ValidationException;

public interface UserValidation {

	public boolean validEmail(String email);

	public boolean validLogin(String login);

	public boolean isRepeatUserLogin(String login) throws ValidationException;

	public boolean validPassword(String password);
}