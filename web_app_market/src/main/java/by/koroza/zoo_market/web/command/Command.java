package by.koroza.zoo_market.web.command;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_IS_HAVING_REGISTRATED_USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;

import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@FunctionalInterface
public interface Command {

	public Router execute(HttpServletRequest request) throws CommandException;

	default void isRegistratedUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute(ATTRIBUTE_USER);
		if (object != null) {
			session.setAttribute(ATTRIBUTE_IS_HAVING_REGISTRATED_USER, true);
		} else {
			session.setAttribute(ATTRIBUTE_IS_HAVING_REGISTRATED_USER, false);
		}
	}
}