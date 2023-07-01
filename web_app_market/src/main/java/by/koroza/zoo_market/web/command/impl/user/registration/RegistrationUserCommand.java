package by.koroza.zoo_market.web.command.impl.user.registration;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_EMAIL;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_LOGIN;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_NAME;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_PASSWORD;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_SURNAME;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.REGISTRATION_FORM_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.VERIFICATION_REGISTRATION_INFORMATION_PAGE_PATH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;
import by.koroza.zoo_market.validation.UserValidation;
import by.koroza.zoo_market.model.entity.user.reserved.User;
import by.koroza.zoo_market.service.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RegistrationUserCommand implements Command {
	public static final String NAME = InputNameException.NAME.toString();
	public static final String SURNAME = InputNameException.SURNAME.toString();
	public static final String EMAIL = InputNameException.EMAIL.toString();
	public static final String LOGIN = InputNameException.LOGIN.toString();
	public static final String PASSWORD = InputNameException.PASSWORD.toString();

	public static enum InputNameException {
		NAME, SURNAME, EMAIL, LOGIN, PASSWORD
	}

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = createUser(request);
		session.setAttribute(ATTRIBUTE_USER, user);
		isRegistratedUser(request);
		return session.getAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE) == null
				? new Router(VERIFICATION_REGISTRATION_INFORMATION_PAGE_PATH)
				: new Router(REGISTRATION_FORM_PAGE_PATH);
	}

	private User createUser(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		User user = session.getAttribute(ATTRIBUTE_USER) != null ? (User) session.getAttribute(ATTRIBUTE_USER)
				: new User();
		session.removeAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			Map<String, String> mapInputExceptions = new HashMap<>();
			String userName = request.getParameter(REGISTRATION_INPUT_USER_NAME);
			user.setName(userName);
			String userSurName = request.getParameter(REGISTRATION_INPUT_USER_SURNAME);
			user.setSurname(userSurName);
			String userEmail = request.getParameter(REGISTRATION_INPUT_USER_EMAIL);
			if (!UserValidation.validEmail(userEmail)) {
				if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
					mapInputExceptions.put(EMAIL, "Вы ввели e-mail не корректно. Ваш ввод: " + userEmail);
				} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
					mapInputExceptions.put(EMAIL, "You entered email incorrect. Your input: " + userEmail);
				}
			} else {
				user.setEmail(userEmail);
			}
			String userLogin = request.getParameter(REGISTRATION_INPUT_USER_LOGIN);
			if (!UserValidation.validLogin(userLogin)) {
				if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
					mapInputExceptions.put(LOGIN, "Вы ввели login не корректно. Ваш ввод: " + userLogin);
				} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
					mapInputExceptions.put(LOGIN, "You entered login incorrect. Your input: " + userLogin);
				}
			} else {
				if (UserValidation.isRepeatUserLogin(userLogin)) {
					if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
						mapInputExceptions.put(LOGIN,
								"Логин уже существует. Введите другой логин. Ваш ввод: " + userLogin);
					} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
						mapInputExceptions.put(LOGIN,
								"This login already exists. Enter another login. Your input: " + userLogin);
					}
				} else {
					user.setLogin(userLogin);
				}
			}
			String userPassword = request.getParameter(REGISTRATION_INPUT_USER_PASSWORD);
			if (!UserValidation.validPassword(userPassword)) {
				if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
					mapInputExceptions.put(PASSWORD, "Вы ввели пароль не корректно. Ваш ввод: " + userPassword);
				} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
					mapInputExceptions.put(PASSWORD, "You entered password incorrect. Your input: " + userPassword);
				}
			} else {
				user.setPassword(userPassword);
			}
			if (!mapInputExceptions.isEmpty()) {
				session.setAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE, mapInputExceptions);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return user;
	}
}