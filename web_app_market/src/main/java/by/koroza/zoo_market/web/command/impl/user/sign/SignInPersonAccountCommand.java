package by.koroza.zoo_market.web.command.impl.user.sign;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PREVIOUS_COMMAND;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.command.CommandName.COMMAND_SHOW_HOME_PAGE;
import static by.koroza.zoo_market.web.command.name.input.InputName.SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_LOGIN;
import static by.koroza.zoo_market.web.command.name.input.InputName.SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_PASSWORD;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.SIGN_IN_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.servlet.ServletName.MAIN_SERVLET_CONTROLLER_NAME;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.HashGeneratorException;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.hash.HashGeneratorImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SignInPersonAccountCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		try {
			String login = request.getParameter(SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_LOGIN);
			String password = request.getParameter(SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_PASSWORD);
			Optional<User> user = UserServiceImpl.getInstance().getUserByLogin(login,
					HashGeneratorImpl.getInstance().getHash(password));
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
		} catch (ServiceException | HashGeneratorException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}
}