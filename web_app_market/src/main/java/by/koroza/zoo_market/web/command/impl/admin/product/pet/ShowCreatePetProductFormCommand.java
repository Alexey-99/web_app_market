package by.koroza.zoo_market.web.command.impl.admin.product.pet;

import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_PET_PRODUCT_FORM;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ShowCreatePetProductFormCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		isRegistratedUser(request);
		return new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_PET_PRODUCT_FORM);
	}
}