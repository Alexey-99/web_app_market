package by.koroza.zoo_market.web.command.impl.admin.show.form.change.product;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ShowChangeFeedsAndOtherProductFormCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);
		return user != null && user.getRole().getIdRole() == ADMIN.getIdRole() && user.isVerificatedEmail()
				? new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM)
				: new Router(HOME_PAGE_PATH);
	}
}