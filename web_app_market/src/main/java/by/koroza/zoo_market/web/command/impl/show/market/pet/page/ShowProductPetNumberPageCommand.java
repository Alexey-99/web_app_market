package by.koroza.zoo_market.web.command.impl.show.market.pet.page;

import static by.koroza.zoo_market.web.command.name.AttributeName.REQUEST_ATTRIBUTE_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.PagePathName.PRODUCTS_PETS_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_NUMBER_PAGE;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;

public class ShowProductPetNumberPageCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		isRegistratedUser(request);
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		return new Router(PRODUCTS_PETS_PAGE_PATH);
	}
}