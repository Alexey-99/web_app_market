package by.koroza.zoo_market.web.command.impl.admin.show.form.create;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowCreateFeedsAndOtherProductFormCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		if (user != null && user.isVerificatedEmail() == true
				&& user.getRole().getIdRole() == UserRole.ADMIN.getIdRole()) {
			router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM);
		} else {
			router = new Router(HOME_PAGE_PATH);
		}
		isRegistratedUser(request);
		return router;
	}
}