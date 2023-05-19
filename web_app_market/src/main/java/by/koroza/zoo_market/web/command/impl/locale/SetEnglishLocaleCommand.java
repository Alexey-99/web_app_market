package by.koroza.zoo_market.web.command.impl.locale;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;

public class SetEnglishLocaleCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {

		return null;
	}

}
