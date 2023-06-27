package by.koroza.zoo_market.web.command.impl.admin.change.user.status;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_INPUT_LOGIN;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE;
import static by.koroza.zoo_market.web.command.name.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;

import java.util.HashMap;
import java.util.Map;

import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeUserStatusCommand implements Command {
	public static final String INPUT_EXCEPTION_TYPE_LOGIN = TypeInputException.LOGIN.toString();
	public static final String INPUT_EXCEPTION_TYPE_ROLE = TypeInputException.ROLE.toString();

	public enum TypeInputException {
		LOGIN, ROLE;
	}

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			if (user != null && user.isVerificatedEmail() == true && user.getRole().getIdRole() == ADMIN.getIdRole()) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				String[] loginAndRole = getInputParameters(request, mapInputExceptions);
				if (mapInputExceptions.isEmpty()) {
					UserServiceImpl.getInstance().changeRoleStatus(loginAndRole[0], loginAndRole[1]);
				} else {
					session.setAttribute(ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
							mapInputExceptions);
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

	private String[] getInputParameters(HttpServletRequest request, Map<String, String> mapInputExceptions)
			throws ServiceException {
		HttpSession session = request.getSession();
		String login = request.getParameter(ADMIN_PAGE_CHANGE_USER_STATUS_FORM_INPUT_LOGIN);
		if (UserServiceImpl.getInstance().isExistsUserWithLogin(login) == false) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(INPUT_EXCEPTION_TYPE_LOGIN, "Пользователь не найден с логином " + login);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(INPUT_EXCEPTION_TYPE_LOGIN, "User didn't find with login " + login);
			}
		}
		String role = request.getParameter(ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE);
		if (!(role.equalsIgnoreCase(USER.toString()) || role.equalsIgnoreCase(ADMIN.toString()))) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(INPUT_EXCEPTION_TYPE_LOGIN, "Выбран не корректный статус: " + role);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(INPUT_EXCEPTION_TYPE_ROLE, "You selected incorrect status: " + role);
			}
		}
		return new String[] { login, role };
	}
}