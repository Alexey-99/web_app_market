package by.koroza.zoo_market.web.command.impl.user.change;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_CHANGING_PASSWORD_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;

import static by.koroza.zoo_market.web.command.name.InputName.CHANGING_PASSWORD_INPUT_USER_PASSWORD;

import static by.koroza.zoo_market.web.command.name.PagePathName.CHANGE_PASSWORD_FORM_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.hash.HashGenerator;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.validation.UserValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangePasswordCommand implements Command {
	private static final Logger log = LogManager.getLogger();

	public static final String TYPY_INPUT_EXCEPTION_PASSWORD = TypeInputException.PASSWORD.toString();

	private enum TypeInputException {
		PASSWORD;
	}

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_CHANGING_PASSWORD_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user == null || user.getRole().getIdRole() == 0) {
				router = new Router(HOME_PAGE_PATH);
			} else {
				if (user.getRole().getIdRole() < UserRole.USER.getIdRole()) {
					router = new Router(HOME_PAGE_PATH);
				} else {
					Map<String, String> mapInputExceptions = new HashMap<>();

					String password = getInputParameterPassword(request, mapInputExceptions, user);

					if ((user.getPassword() != null ? !user.getPassword().equals(password)
							: user.getPassword() == null && password != null)
							&& (session
									.getAttribute(ATTRIBUTE_CHANGING_PASSWORD_INPUT_EXCEPTION_TYPE_AND_MASSAGE) == null
									&& mapInputExceptions.isEmpty())) {
						UserServiceImpl.getInstance().changePassword(user,
								HashGenerator.getInstance().getHash(password));
						user.setPassword(HashGenerator.getInstance().getHash(password));
						router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
					} else if (user.getPassword() != null ? user.getPassword().equals(password)
							: user.getPassword() == null && password == null) {
						router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
					} else if (!mapInputExceptions.isEmpty()) {
						session.setAttribute(ATTRIBUTE_CHANGING_PASSWORD_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
								mapInputExceptions);
						router = new Router(CHANGE_PASSWORD_FORM_VALIDATED_PAGE_PATH);
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

	private String getInputParameterPassword(HttpServletRequest request, Map<String, String> mapInputExceptions,
			AbstractRegistratedUser user) {
		String password = (String) request.getParameter(CHANGING_PASSWORD_INPUT_USER_PASSWORD);
		if (user.getPassword() != null ? !user.getPassword().equals(HashGenerator.getInstance().getHash(password))
				: user.getPassword() == null && password != null) {
			if (!UserValidation.validPassword(password)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_PASSWORD,
						"Вы ввели пароль не корректно. Ваш ввод: " + password);
			}
		}
		return password;
	}
}