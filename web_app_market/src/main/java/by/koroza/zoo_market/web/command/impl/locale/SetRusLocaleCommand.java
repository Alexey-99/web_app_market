package by.koroza.zoo_market.web.command.impl.locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;

public class SetRusLocaleCommand implements Command {
	private static final Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router("/Controller?command=show_home_page");
		router.setForward();
		return router;
	}

}
