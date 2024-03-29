package by.koroza.zoo_market.web.command.impl.user.sign;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The Class SignOutPersonalAccountCommand.
 */
public class SignOutPersonalAccountCommand implements Command {

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 */
	@Override
	public Router execute(HttpServletRequest request) {
		request.getSession().removeAttribute(ATTRIBUTE_USER);
		return new Router(HOME_PAGE_PATH);
	}
}