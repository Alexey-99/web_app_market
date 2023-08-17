package by.koroza.zoo_market.web.command.impl.user.change;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_CHANGING_PASSWORD_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_PASSWORD;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PASSWORD_INPUT_USER_NEW_PASSWORD;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PASSWORD_LOGIN_INPUT_USER_OLD_LOGIN;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PASSWORD_LOGIN_INPUT_USER_OLD_PASSWORD;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_PASSWORD_FORM_VALIDATED_INCORRECT_NEW_PASSWORD_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_PASSWORD_FORM_VALIDATED_INCORRECT_CURRENT_LOGIN_PASSWORD_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.HashGenerator;
import by.koroza.zoo_market.service.UserService;
import by.koroza.zoo_market.service.exception.HashGeneratorException;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.hash.HashGeneratorImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class ChangePasswordCommand.
 */
public class ChangePasswordCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The user service. */
	private final UserService USER_SERVICE = UserServiceImpl.getInstance();

	/** The hash generator. */
	private final HashGenerator HASH_GENERATOR = HashGeneratorImpl.getInstance();

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
		session.removeAttribute(ATTRIBUTE_CHANGING_PASSWORD_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.getRole().getId() >= USER.getId() && user.isVerificatedEmail()) {
				if (isCorrectOldLoginAndPassword(request, user.getId())) {
					Map<String, String> mapInputExceptions = new HashMap<>();
					String password = getInputParameterPassword(request, mapInputExceptions, user);
					if (mapInputExceptions.isEmpty()) {
						if (user.getPassword() != null ? !user.getPassword().equals(password)
								: user.getPassword() == null && password != null) {
							USER_SERVICE.changePassword(user.getId(), HASH_GENERATOR.getHash(password));
							user.setPassword(HashGeneratorImpl.getInstance().getHash(password));
							router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
						} else {
							router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
						}
					} else {
						session.setAttribute(ATTRIBUTE_CHANGING_PASSWORD_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
								mapInputExceptions);
						router = new Router(CHANGE_PASSWORD_FORM_VALIDATED_INCORRECT_NEW_PASSWORD_PAGE_PATH);
					}
				} else {
					router = new Router(CHANGE_PASSWORD_FORM_VALIDATED_INCORRECT_CURRENT_LOGIN_PASSWORD_PAGE_PATH);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException | HashGeneratorException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}

	/**
	 * Get the input parameter password.
	 *
	 * @param request            the request
	 * @param mapInputExceptions the map input exceptions
	 * @param user               the user
	 * @return the input parameter password
	 * @throws HashGeneratorException the hash generator exception
	 */
	private String getInputParameterPassword(HttpServletRequest request, Map<String, String> mapInputExceptions,
			User user) throws HashGeneratorException {
		String password = (String) request.getParameter(CHANGING_PASSWORD_INPUT_USER_NEW_PASSWORD);
		String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
		if (user.getPassword() != null ? !user.getPassword().equals(HashGeneratorImpl.getInstance().getHash(password))
				: user.getPassword() == null && password != null) {
			if (!UserValidationImpl.getInstance().validPassword(password)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_PASSWORD,
							RU_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD + password);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_PASSWORD,
							EN_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD + password);
				} else {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_PASSWORD,
							EN_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD + password);
				}
			}
		}
		return password;
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
		return USER_SERVICE.isExistsUserWithLoginAndPasswordByUserId(oldlogin, HASH_GENERATOR.getHash(oldPassword),
				userId);
	}
}