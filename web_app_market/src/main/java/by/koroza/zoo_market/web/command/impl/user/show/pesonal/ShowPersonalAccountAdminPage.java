package by.koroza.zoo_market.web.command.impl.user.show.pesonal;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The Class ShowPersonalAccountAdminPage.
 */
public class ShowPersonalAccountAdminPage implements Command {

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 */
	@Override
	public Router execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);
		return user != null && user.isVerificatedEmail() && user.getRole().getId() == ADMIN.getId()
				? new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_PATH)
				: new Router(HOME_PAGE_PATH);
	}
}