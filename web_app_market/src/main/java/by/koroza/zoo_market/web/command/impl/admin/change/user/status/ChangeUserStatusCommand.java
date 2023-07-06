package by.koroza.zoo_market.web.command.impl.admin.change.user.status;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_LOGIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_ROLE_ID;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_USER_ROLE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_USER_WITH_LOGIN_NOT_EXISTS;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_USER_ROLE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_USER_WITH_LOGIN_NOT_EXISTS;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_LOGIN;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_USER_ROLE;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_INPUT_LOGIN;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_CHANGE_USER_STATUS_FORM_VALIDATED_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_SUCCESS_CHANGED_USER_STATUS_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeUserStatusCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getIdRole() == ADMIN.getIdRole()) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
				String login = getInputParameterLogin(request, sessionLocale, mapInputExceptions);
				String roleId = getInputParameterRoleId(request, sessionLocale, mapInputExceptions);
				if (mapInputExceptions.isEmpty()) {
					UserServiceImpl.getInstance().changeRoleStatus(login, Integer.parseInt(roleId));
					session.setAttribute(ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_LOGIN, login);
					session.setAttribute(ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_ROLE_ID, roleId);
					router = new Router(PERSONAL_ACCOUNT_ADMIN_SUCCESS_CHANGED_USER_STATUS_PAGE_PATH);
				} else {
					session.setAttribute(ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
							mapInputExceptions);
					router = new Router(PERSONAL_ACCOUNT_ADMIN_CHANGE_USER_STATUS_FORM_VALIDATED_PATH);
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

	private String getInputParameterLogin(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String login = request.getParameter(ADMIN_PAGE_CHANGE_USER_STATUS_FORM_INPUT_LOGIN);
		if (UserServiceImpl.getInstance().isExistsUserWithLogin(login) == false) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
						RU_MESSAGE_TYPY_INPUT_EXCEPTION_USER_WITH_LOGIN_NOT_EXISTS + login);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_USER_WITH_LOGIN_NOT_EXISTS + login);
			} else {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_LOGIN,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_USER_WITH_LOGIN_NOT_EXISTS + login);
			}
		}
		return login;
	}

	private String getInputParameterRoleId(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String roleId = request.getParameter(ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE);
		if (!(roleId != null && (roleId.equalsIgnoreCase(Integer.toString(USER.getIdRole()))
				|| roleId.equalsIgnoreCase(Integer.toString(ADMIN.getIdRole()))))) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_USER_ROLE, RU_MESSAGE_TYPY_INPUT_EXCEPTION_USER_ROLE);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_USER_ROLE, EN_MESSAGE_TYPY_INPUT_EXCEPTION_USER_ROLE);
			} else {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_USER_ROLE, EN_MESSAGE_TYPY_INPUT_EXCEPTION_USER_ROLE);
			}
		}
		return roleId;
	}
}