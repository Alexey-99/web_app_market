package by.koroza.zoo_market.web.command.impl.user.change;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.InputName.REGISTRATION_INPUT_USER_EMAIL;
import static by.koroza.zoo_market.web.command.name.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.CONFIMARTION_EMAIL_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.CHANGE_EMAIL_FORM_VALIDATED_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import by.koroza.zoo_market.model.entity.user.reserved.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.generator.GenerationVeriicationCode;
import by.koroza.zoo_market.service.impl.confirmation.ConfirmationServiceImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.sender.EmailSender;
import by.koroza.zoo_market.validation.UserValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.command.impl.user.registration.RegistrationUserCommand.InputNameException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeEmailCommand implements Command {
	public static final String EMAIL = InputNameException.EMAIL.toString();

	private static final String EMAIL_SUBJECT = "CONFIRMATION EMAIL";
	private static final String EMAIL_TEXT = "Your code for conformation email: ";

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				String email = getEmailParameter(request, mapInputExceptions);
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

	private String getEmailParameter(HttpServletRequest request, Map<String, String> mapInputExceptions) {
		HttpSession session = request.getSession();
		String userEmail = request.getParameter(REGISTRATION_INPUT_USER_EMAIL);
		if (!UserValidation.validEmail(userEmail)) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(EMAIL, "Вы ввели e-mail не корректно. Ваш ввод: " + userEmail);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(EMAIL, "You entered email incorrect. Your input: " + userEmail);
			}
		}
		return userEmail;
	}

	private void sendConfirmationEmailCode(User user) throws ServiceException {
		String code = GenerationVeriicationCode.getInstance().getGeneratedCode();
		ConfirmationServiceImpl.getInstance().addVerificateCodeWithUserId(user.getId(), code);
		EmailSender.getInstance().emailSend(EMAIL_SUBJECT, EMAIL_TEXT + code, user.getEmail());
	}
}