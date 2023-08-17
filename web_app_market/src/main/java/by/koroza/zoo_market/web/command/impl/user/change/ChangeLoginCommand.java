package by.koroza.zoo_market.web.command.impl.user.change;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_CHANGING_LOGIN_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_LOGIN;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_LOGIN_INPUT_USER_NEW_LOGIN;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PASSWORD_LOGIN_INPUT_USER_OLD_LOGIN;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PASSWORD_LOGIN_INPUT_USER_OLD_PASSWORD;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_LOGIN_FORM_VALIDATED_INCORRECT_NEW_LOGIN_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_LOGIN_FORM_VALIDATED_INCORRECT_CURRENT_LOGIN_PASSWORD_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.UserService;
import by.koroza.zoo_market.service.exception.HashGeneratorException;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.ValidationException;
import by.koroza.zoo_market.service.impl.hash.HashGeneratorImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.validation.UserValidation;
import by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class ChangeLoginCommand.
 */
public class ChangeLoginCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The user validator. */
	private final UserValidation USER_VALIDATOR = UserValidationImpl.getInstance();

	/** The user service. */
	private final UserService USER_SERVICE = UserServiceImpl.getInstance();

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_CHANGING_LOGIN_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.getRole().getId() >= USER.getId() && user.isVerificatedEmail()) {
				if (isCorrectOldLoginAndPassword(request, user.getId())) {
					Map<String, String> mapInputExceptions = new HashMap<>();
					String newLogin = getInputParameterNewLogin(request, mapInputExceptions, user);
					if (mapInputExceptions.isEmpty()) {
						if (user.getLogin() != null ? !user.getLogin().equals(newLogin)
								: user.getLogin() == null && newLogin != null) {
							USER_SERVICE.changeLogin(user.getId(), newLogin);
							user.setLogin(newLogin);
							router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
						} else {
							router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
						}
					} else {
						session.setAttribute(ATTRIBUTE_CHANGING_LOGIN_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
								mapInputExceptions);
						router = new Router(CHANGE_LOGIN_FORM_VALIDATED_INCORRECT_NEW_LOGIN_PAGE_PATH);
					}
				} else {
					router = new Router(CHANGE_LOGIN_FORM_VALIDATED_INCORRECT_CURRENT_LOGIN_PASSWORD_PAGE_PATH);
				}

			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ValidationException | ServiceException | HashGeneratorException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}

	/**
	 * Get the input parameter new login.
	 *
	 * @param request            the request
	 * @param mapInputExceptions the map input exceptions
	 * @param user               the user
	 * @return the input parameter new login
	 * @throws ValidationException the validation exception
	 */
	private String getInputParameterNewLogin(HttpServletRequest request, Map<String, String> mapInputExceptions,
			User user) throws ValidationException {
		String newlogin = (String) request.getParameter(CHANGING_LOGIN_INPUT_USER_NEW_LOGIN);
		String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
		if (user.getLogin() != null ? !user.getLogin().equals(newlogin) : user.getLogin() == null && newlogin != null) {
			if (!USER_VALIDATOR.validLogin(newlogin)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + newlogin);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + newlogin);
				} else {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + newlogin);
				}
			} else {
				if (USER_VALIDATOR.isRepeatUserLogin(newlogin)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
								RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + newlogin);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
								EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + newlogin);
					} else {
						mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
								EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + newlogin);
					}
				}
			}
		}
		return newlogin;
	}

	/**
	 * Check if is correct old login and password.
	 *
	 * @param request the request
	 * @param userId  the user id
	 * @return true, if is correct old login and password
	 * @throws ServiceException       the service exception
	 * @throws HashGeneratorException the hash generator exception
	 */
	private boolean isCorrectOldLoginAndPassword(HttpServletRequest request, long userId)
			throws ServiceException, HashGeneratorException {
		String oldlogin = (String) request.getParameter(CHANGING_PASSWORD_LOGIN_INPUT_USER_OLD_LOGIN);
		String oldPassword = (String) request.getParameter(CHANGING_PASSWORD_LOGIN_INPUT_USER_OLD_PASSWORD);
		return USER_SERVICE.isExistsUserWithLoginAndPasswordByUserId(oldlogin,
				HashGeneratorImpl.getInstance().getHash(oldPassword), userId);
	}
}