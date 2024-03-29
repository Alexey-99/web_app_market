package by.koroza.zoo_market.web.command.impl.user.registration;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_LOGIN;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_PASSWORD;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_EMAIL;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_LOGIN;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_PASSWORD;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.REGISTRATION_FORM_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.VERIFICATION_REGISTRATION_INFORMATION_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ValidationException;
import by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class RegistrationUserCommand.
 */
public class RegistrationUserCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		Map<String, String> mapInputExceptions = new HashMap<>();
		try {
			User user = getUserFromInputParameters(request, mapInputExceptions);
			session.setAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE, mapInputExceptions);
			session.setAttribute(ATTRIBUTE_USER, user);
		} catch (ValidationException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return mapInputExceptions.isEmpty() ? new Router(VERIFICATION_REGISTRATION_INFORMATION_PAGE_PATH)
				: new Router(REGISTRATION_FORM_PAGE_PATH);
	}

	/**
	 * Get the user from input parameters.
	 *
	 * @param request            the request
	 * @param mapInputExceptions the map input exceptions
	 * @return the user from input parameters
	 * @throws ValidationException the validation exception
	 */
	private User getUserFromInputParameters(HttpServletRequest request, Map<String, String> mapInputExceptions)
			throws ValidationException {
		HttpSession session = request.getSession();
		User user = session.getAttribute(ATTRIBUTE_USER) != null ? (User) session.getAttribute(ATTRIBUTE_USER)
				: new User();
		String sessionLocale = (String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE);
		user = createUser(request, mapInputExceptions, sessionLocale, user);
		return user;
	}

	/**
	 * Create the user.
	 *
	 * @param request            the request
	 * @param mapInputExceptions the map input exceptions
	 * @param sessionLocale      the session locale
	 * @param user               the user
	 * @return the user
	 * @throws ValidationException the validation exception
	 */
	private User createUser(HttpServletRequest request, Map<String, String> mapInputExceptions, String sessionLocale,
			User user) throws ValidationException {
		String email = getInputParameterEmail(request, sessionLocale, mapInputExceptions);
		if (!mapInputExceptions.containsKey(TYPY_INPUT_EXCEPTION_EMAIL)) {
			user.setEmail(email);
		}

		String login = getInputParameterLogin(request, sessionLocale, mapInputExceptions);
		if (!mapInputExceptions.containsKey(TYPY_INPUT_EXCEPTION_LOGIN)) {
			user.setLogin(login);
		}

		String password = getInputParameterPassword(request, sessionLocale, mapInputExceptions);
		if (!mapInputExceptions.containsKey(TYPY_INPUT_EXCEPTION_PASSWORD)) {
			user.setPassword(password);
		}
		return user;
	}

	/**
	 * Get the input parameter email.
	 *
	 * @param request            the request
	 * @param sessionLocale      the session locale
	 * @param mapInputExceptions the map input exceptions
	 * @return the input parameter email
	 */
	private String getInputParameterEmail(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String userEmail = request.getParameter(REGISTRATION_INPUT_USER_EMAIL);
		if (!UserValidationImpl.getInstance().validEmail(userEmail)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_EMAIL, RU_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL + userEmail);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_EMAIL, EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL + userEmail);
			} else {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_EMAIL, EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL + userEmail);
			}
		}
		return userEmail;
	}

	/**
	 * Get the input parameter login.
	 *
	 * @param request            the request
	 * @param sessionLocale      the session locale
	 * @param mapInputExceptions the map input exceptions
	 * @return the input parameter login
	 * @throws ValidationException the validation exception
	 */
	private String getInputParameterLogin(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ValidationException {
		String userLogin = request.getParameter(REGISTRATION_INPUT_USER_LOGIN);
		if (!UserValidationImpl.getInstance().validLogin(userLogin)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
						RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + userLogin);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + userLogin);
			} else {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + userLogin);
			}
		} else {
			if (UserValidationImpl.getInstance().isRepeatUserLogin(userLogin)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + userLogin);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + userLogin);
				} else {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + userLogin);
				}
			}
		}
		return userLogin;
	}

	/**
	 * Get the input parameter password.
	 *
	 * @param request            the request
	 * @param sessionLocale      the session locale
	 * @param mapInputExceptions the map input exceptions
	 * @return the input parameter password
	 */
	private String getInputParameterPassword(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String userPassword = request.getParameter(REGISTRATION_INPUT_USER_PASSWORD);
		if (!UserValidationImpl.getInstance().validPassword(userPassword)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_PASSWORD,
						RU_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD + userPassword);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_PASSWORD,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD + userPassword);
			} else {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_PASSWORD,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD + userPassword);
			}
		}
		return userPassword;
	}
}