package by.koroza.zoo_market.web.command.impl.user.change;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.email.EmailComponent.EMAIL_SUBJECT;
import static by.koroza.zoo_market.web.command.name.email.EmailComponent.EMAIL_TEXT;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_EMAIL;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_NAME;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_SURNAME;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_PERSON_INFOMATION_FORM_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.confirmation.ConfirmationServiceImpl;
import by.koroza.zoo_market.service.impl.generator.GenerationConfirmationEmailCodeImpl;
import by.koroza.zoo_market.service.impl.sender.EmailSenderImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.validation.UserValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangePersonInformationCommand implements Command {
	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.getRole().getIdRole() >= USER.getIdRole()) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
				String name = getInputParameterName(request);
				String surname = getInputParameterSurName(request);
				String email = getInputParameterEmail(request, sessionLocale, mapInputExceptions);

				if (mapInputExceptions.isEmpty()) {
					if ((user.getEmail() != null ? !user.getEmail().equals(email)
							: user.getEmail() == null && email != null)
							|| (user.getName() != null ? !user.getName().equals(name)
									: user.getName() == null && name != null)
							|| (user.getSurname() != null ? !user.getSurname().equals(surname)
									: user.getSurname() == null && surname != null)) {
						user.setName(name);
						user.setSurname(surname);
						if (user.getEmail() != null ? !user.getEmail().equals(email)
								: user.getEmail() == null && email != null) {
							user.setEmail(email);
							user.setVerificatedEmail(false);
							UserServiceImpl.getInstance().changePersonInformation(user);
							sendConfirmationEmailCode(user);
						} else {
							UserServiceImpl.getInstance().changePersonInformation(user);
						}
						router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
					} else {
						router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
					}
				} else {
					session.setAttribute(ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
							mapInputExceptions);
					router = new Router(CHANGE_PERSON_INFOMATION_FORM_VALIDATED_PAGE_PATH);
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

	private String getInputParameterName(HttpServletRequest request) {
		String name = (String) request.getParameter(CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_NAME);
		return name;
	}

	private String getInputParameterSurName(HttpServletRequest request) {
		String surname = (String) request.getParameter(CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_SURNAME);
		return surname;
	}

	private String getInputParameterEmail(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String email = (String) request.getParameter(CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_EMAIL);
		if (!UserValidation.validEmail(email)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_EMAIL, RU_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL + email);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_EMAIL, EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL + email);
			}
		}
		return email;
	}

	private void sendConfirmationEmailCode(User user) throws ServiceException {
		String code = GenerationConfirmationEmailCodeImpl.getInstance().getGeneratedCode();
		ConfirmationServiceImpl.getInstance().addVerificateCodeWithUserId(user.getId(), code);
		EmailSenderImpl.getInstance().emailSend(EMAIL_SUBJECT, EMAIL_TEXT + code, user.getEmail());
	}
}