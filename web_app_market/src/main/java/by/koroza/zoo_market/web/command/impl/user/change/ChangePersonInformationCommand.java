package by.koroza.zoo_market.web.command.impl.user.change;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_EMAIL;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_NAME;
import static by.koroza.zoo_market.web.command.name.input.InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_SURNAME;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_PERSON_INFOMATION_FORM_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.generator.GenerationVeriicationCode;
import by.koroza.zoo_market.service.impl.confirmation.ConfirmationServiceImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.sender.EmailSender;
import by.koroza.zoo_market.validation.UserValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangePersonInformationCommand implements Command {
	private static final Logger log = LogManager.getLogger();

	public static final String TYPY_INPUT_EXCEPTION_NAME = TypeInputException.NAME.toString();
	public static final String TYPY_INPUT_EXCEPTION_SURNAME = TypeInputException.SURNAME.toString();
	public static final String TYPY_INPUT_EXCEPTION_EMAIL = TypeInputException.EMAIL.toString();

	private enum TypeInputException {
		NAME, SURNAME, EMAIL
	}

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user == null || user.getRole().getIdRole() == 0) {
				router = new Router(HOME_PAGE_PATH);
			} else {
				if (user.getRole().getIdRole() == 1) {
					router = new Router(HOME_PAGE_PATH);
				} else {
					Map<String, String> mapInputExceptions = new HashMap<>();
					String name = (String) request.getParameter(CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_NAME);
					String surname = (String) request.getParameter(CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_SURNAME);
					String email = (String) request.getParameter(CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_EMAIL);
					if (!UserValidation.validEmail(email)) {
						mapInputExceptions.put(TYPY_INPUT_EXCEPTION_EMAIL,
								"Вы ввели e-mail не корректно. Ваш ввод: " + email);
					}
					if (((user.getEmail() != null ? !user.getEmail().equals(email)
							: user.getEmail() == null && email != null)
							|| (user.getName() != null ? !user.getName().equals(name)
									: user.getName() == null && name != null)
							|| (user.getSurname() != null ? !user.getSurname().equals(surname)
									: user.getSurname() == null && surname != null))
							&& (session.getAttribute(
									ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE) == null
									&& mapInputExceptions.isEmpty())) {
						if (UserServiceImpl.getInstance().changePersonInformation(user, name, surname, email)) {
							user.setName(name);
							user.setSurname(surname);
							if (user.getEmail() != null ? !user.getEmail().equals(email)
									: user.getEmail() == null && email != null) {
								user.setEmail(email);
								user.setRole(UserRole.WAITING_CODE_REGISTRATION);
								String code = GenerationVeriicationCode.getInstance().getGeneratedCode();
								ConfirmationServiceImpl.getInstance().addVerificateCodeWithUserId(user.getId(), code);
								EmailSender.getInstance().emailSend("Изменение данных в личном кабинете",
										"Введите данный код когда будете подтверждать учётную запись. /n Ваш код: "
												+ code,
										user.getEmail());
								router = new Router(HOME_PAGE_PATH);
							} else {
								router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
							}
						} else {
							user.setRole(UserRole.USER);
							log.log(Level.ERROR, "Didn't change infomation.");
							throw new CommandException("Didn't change infomation.");
						}
					} else if (((user.getEmail() != null ? user.getEmail().equals(email)
							: user.getEmail() == null && email == null)
							|| (user.getName() != null ? user.getName().equals(name)
									: user.getName() == null && name == null)
							|| (user.getSurname() != null ? user.getSurname().equals(surname)
									: user.getSurname() == null && surname == null))) {
						router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
					} else if (!mapInputExceptions.isEmpty()) {
						session.setAttribute(ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
								mapInputExceptions);
						router = new Router(CHANGE_PERSON_INFOMATION_FORM_VALIDATED_PAGE_PATH);
					} else {
						router = new Router(HOME_PAGE_PATH);
					}
				}
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}
}