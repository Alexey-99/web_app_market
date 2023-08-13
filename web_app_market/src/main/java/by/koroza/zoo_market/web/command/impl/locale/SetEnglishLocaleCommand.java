package by.koroza.zoo_market.web.command.impl.locale;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PREVIOUS_COMMAND;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.command.CommandName.COMMAND_SHOW_HOME_PAGE;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.servlet.ServletName.MAIN_SERVLET_CONTROLLER_NAME;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SetEnglishLocaleCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(ATTRIBUTE_SESSION_LOCALE, ENGLISH);
		String previousCommand = session.getAttribute(ATTRIBUTE_PREVIOUS_COMMAND) != null
				? (String) session.getAttribute(ATTRIBUTE_PREVIOUS_COMMAND)
				: COMMAND_SHOW_HOME_PAGE;
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP);
		return new Router(new StringBuilder().append("/").append(MAIN_SERVLET_CONTROLLER_NAME).append("?")
				.append(PARAMETER_COMMAND).append("=").append(previousCommand).toString());
	}
}