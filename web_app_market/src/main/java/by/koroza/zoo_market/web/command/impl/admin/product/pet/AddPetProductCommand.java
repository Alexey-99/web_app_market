package by.koroza.zoo_market.web.command.impl.admin.product.pet;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AddPetProductCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		if (user == null || user.isVerificatedEmail() == false
				|| user.getRole().getIdRole() != UserRole.ADMIN.getIdRole()) {
			router = new Router(HOME_PAGE_PATH);
		} else {
			
		}
		return router;
	}

}
