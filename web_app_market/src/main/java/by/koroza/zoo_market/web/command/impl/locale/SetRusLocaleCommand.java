package by.koroza.zoo_market.web.command.impl.locale;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PREVIOUS_PAGE;

import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SetRusLocaleCommand implements Command {
	private static final Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute(ATTRIBUTE_PREVIOUS_PAGE));
		session.setAttribute(ATTRIBUTE_SESSION_LOCALE, RUSSIAN);
		Router router = new Router("/Controller?command=" + session.getAttribute(ATTRIBUTE_PREVIOUS_PAGE));
		// request.Attribute("command", session.getAttribute(ATTRIBUTE_PREVIOUS_PAGE));

		System.out.println(router.getPagePath());
		return router;
	}
}