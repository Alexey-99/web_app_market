package by.koroza.zoo_market.web.command.impl.user.sign;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PREVIOUS_COMMAND;

import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_HOME_PAGE;

import static by.koroza.zoo_market.web.command.name.ServletName.MAIN_SERVLET_CONTROLLER_NAME;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_COMMAND;

import static by.koroza.zoo_market.web.command.name.InputName.SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_LOGIN;
import static by.koroza.zoo_market.web.command.name.InputName.SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_PASSWORD;

import static by.koroza.zoo_market.web.command.name.PagePathName.SIGN_IN_VALIDATED_PAGE_PATH;

import java.util.Optional;

import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SignInPersonAccountCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		try {
			String login = request.getParameter(SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_LOGIN);
			String password = request.getParameter(SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_PASSWORD);
			Optional<AbstractRegistratedUser> user = UserServiceImpl.getInstance().getUserByLogin(login, password);
			if (user.isPresent()) {
				session.setAttribute(ATTRIBUTE_USER, user.get());
				String previousCommand = session.getAttribute(ATTRIBUTE_PREVIOUS_COMMAND) != null
						? (String) session.getAttribute(ATTRIBUTE_PREVIOUS_COMMAND)
						: COMMAND_SHOW_HOME_PAGE;
				router = new Router(new StringBuilder().append(MAIN_SERVLET_CONTROLLER_NAME).append("?")
						.append(PARAMETER_COMMAND).append("=").append(previousCommand).toString());
			} else {
				router = new Router(SIGN_IN_VALIDATED_PAGE_PATH);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}
}