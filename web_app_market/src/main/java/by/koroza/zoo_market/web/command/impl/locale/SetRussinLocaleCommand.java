package by.koroza.zoo_market.web.command.impl.locale;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PREVIOUS_COMMAND;

import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;

import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_HOME_PAGE;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SetRussinLocaleCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		session.setAttribute(ATTRIBUTE_SESSION_LOCALE, RUSSIAN);
		String previousCommand = session.getAttribute(ATTRIBUTE_PREVIOUS_COMMAND) != null
				? (String) session.getAttribute(ATTRIBUTE_PREVIOUS_COMMAND)
				: COMMAND_SHOW_HOME_PAGE;
		Router router = new Router("/Controller?command=" + previousCommand);
		return router;
	}
}