package by.koroza.zoo_market.web.command.impl;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ShowPaymentInformationFormCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		isRegistratedUser(request);
		return null;
	}

}
