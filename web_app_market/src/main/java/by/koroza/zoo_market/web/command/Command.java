package by.koroza.zoo_market.web.command;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_IS_HAVING_REGISTRATED_USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;

import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Interface Command.
 */
@FunctionalInterface
public interface Command {

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	public Router execute(HttpServletRequest request) throws CommandException;

	/**
	 * Checks if is registered user.
	 *
	 * @param request the request
	 */
	default void isRegisteredUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute(ATTRIBUTE_USER);
		session.setAttribute(ATTRIBUTE_IS_HAVING_REGISTRATED_USER, object != null);
	}
}