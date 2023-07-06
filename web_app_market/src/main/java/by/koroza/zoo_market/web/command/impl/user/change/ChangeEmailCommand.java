package by.koroza.zoo_market.web.command.impl.user.change;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.input.InputName.REGISTRATION_INPUT_USER_EMAIL;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_EMAIL_FORM_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CONFIMARTION_EMAIL_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.confirmation.ConfirmationEmailCodeServiceImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.validation.impl.user.UserValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeEmailCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
				String email = getEmailParameter(request, sessionLocale, mapInputExceptions);
				if (mapInputExceptions.isEmpty()) {
					user.setEmail(email);
					user.setVerificatedEmail(false);
					UserServiceImpl.getInstance().changeEmail(user);
					ConfirmationEmailCodeServiceImpl.getInstance().sendConfirmationEmailCode(user.getId(),
							user.getEmail());
					session.setAttribute(ATTRIBUTE_USER, user);
					router = new Router(CONFIMARTION_EMAIL_PAGE_PATH);
				} else {
					session.setAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE, mapInputExceptions);
					router = new Router(CHANGE_EMAIL_FORM_VALIDATED_PAGE_PATH);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}

	private String getEmailParameter(HttpServletRequest request, String sessionLocale,
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
}