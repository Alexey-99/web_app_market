package by.koroza.zoo_market.web.command.impl.admin.show.form.change;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;

import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowChangePetProductFormCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		if (user != null && user.isVerificatedEmail() == true
				&& user.getRole().getIdRole() == UserRole.ADMIN.getIdRole()) {
			router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM);
		} else {
			router = new Router(HOME_PAGE_PATH);
		}
		isRegistratedUser(request);
		return router;
	}
}