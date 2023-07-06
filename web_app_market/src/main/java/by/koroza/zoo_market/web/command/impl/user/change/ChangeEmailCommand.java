package by.koroza.zoo_market.web.command.impl.user.change;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.email.EmailComponent.EMAIL_SUBJECT;
import static by.koroza.zoo_market.web.command.name.email.EmailComponent.EMAIL_TEXT;
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

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.confirmation.ConfirmationServiceImpl;
import by.koroza.zoo_market.service.impl.generator.GenerationConfirmationEmailCodeImpl;
import by.koroza.zoo_market.service.impl.sender.EmailSenderImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.validation.impl.UserValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeEmailCommand implements Command {

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
					sendConfirmationEmailCode(user);
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
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}

	private String getEmailParameter(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String userEmail = request.getParameter(REGISTRATION_INPUT_USER_EMAIL);
		if (!UserValidation.validEmail(userEmail)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_EMAIL, RU_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL + userEmail);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_EMAIL, EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL + userEmail);
			}
		}
		return userEmail;
	}

	private void sendConfirmationEmailCode(User user) throws ServiceException {
		String code = GenerationConfirmationEmailCodeImpl.getInstance().getGeneratedCode();
		ConfirmationServiceImpl.getInstance().addVerificateCodeWithUserId(user.getId(), code);
		EmailSenderImpl.getInstance().emailSend(EMAIL_SUBJECT, EMAIL_TEXT + code, user.getEmail());
	}
}