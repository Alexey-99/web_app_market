package by.koroza.zoo_market.web.command.impl.user.show.form.change;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_EMAIL_FORM_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The Class ShowChangeUserEmailFormComamnd.
 */
public class ShowChangeUserEmailFormComamnd implements Command {

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 */
	@Override
	public Router execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);
		return user != null && user.getRole().getId() >= USER.getId()
				? new Router(CHANGE_EMAIL_FORM_VALIDATED_PAGE_PATH)
				: new Router(HOME_PAGE_PATH);
	}
}