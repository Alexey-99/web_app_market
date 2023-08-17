package by.koroza.zoo_market.web.command.impl.user.show.home;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.name.path.PagePathName;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The Class ShowHomePageCommand.
 */
public class ShowHomePageCommand implements Command {

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 */
	@Override
	public Router execute(HttpServletRequest request) {
		return new Router(PagePathName.HOME_PAGE_PATH);
	}
}