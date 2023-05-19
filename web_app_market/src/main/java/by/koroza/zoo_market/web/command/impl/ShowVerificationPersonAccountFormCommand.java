package by.koroza.zoo_market.web.command.impl;

import static by.koroza.zoo_market.web.command.name.PagePathName.VERIFICATION_PERSONAL_ACCOUNT_PAGE_PATH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;

public class ShowVerificationPersonAccountFormCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		isRegistratedUser(request);
		return new Router(VERIFICATION_PERSONAL_ACCOUNT_PAGE_PATH);
	}
}