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
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_LOGIN_INPUT_USER_LOGIN;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_LOGIN_FORM_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.ValidationException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.validation.UserValidation;
import by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLoginCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_CHANGING_LOGIN_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.getRole().getIdRole() >= USER.getIdRole()) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				String login = getInputParameterLogin(request, mapInputExceptions, user);
				if (mapInputExceptions.isEmpty()) {
					if (user.getLogin() != null ? !user.getLogin().equals(login)
							: user.getLogin() == null && login != null) {
						UserServiceImpl.getInstance().changeLogin(user.getId(), login);
						user.setLogin(login);
						router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
					} else {
						router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
					}
				} else {
					session.setAttribute(ATTRIBUTE_CHANGING_LOGIN_INPUT_EXCEPTION_TYPE_AND_MASSAGE, mapInputExceptions);
					router = new Router(CHANGE_LOGIN_FORM_VALIDATED_PAGE_PATH);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ValidationException | ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}

	private String getInputParameterLogin(HttpServletRequest request, Map<String, String> mapInputExceptions, User user)
			throws ValidationException {
		String login = (String) request.getParameter(CHANGING_LOGIN_INPUT_USER_LOGIN);
		String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
		UserValidation validator = UserValidationImpl.getInstance();
		if (user.getLogin() != null ? !user.getLogin().equals(login) : user.getLogin() == null && login != null) {
			if (!validator.validLogin(login)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + login);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + login);
				} else {
					mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
							EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT + login);
				}
			} else {
				if (validator.isRepeatUserLogin(login)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
								RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + login);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
								EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + login);
					} else {
						mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
								EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS + login);
					}
				}
			}
		}
		return login;
	}
}