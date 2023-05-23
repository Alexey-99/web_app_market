package by.koroza.zoo_market.web.command.impl.locale;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PREVIOUS_COMMAND;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;

import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_HOME_PAGE;

import static by.koroza.zoo_market.web.command.name.LanguageName.ENGLISH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SetEnglishLocaleCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		session.setAttribute(ATTRIBUTE_SESSION_LOCALE, ENGLISH);
		String previousCommand = session.getAttribute(ATTRIBUTE_PREVIOUS_COMMAND) != null
				? (String) session.getAttribute(ATTRIBUTE_PREVIOUS_COMMAND)
				: COMMAND_SHOW_HOME_PAGE;
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP);
		return new Router("/Controller?command=" + previousCommand);
	}
}