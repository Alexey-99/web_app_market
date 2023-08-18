package by.koroza.zoo_market.web.command.impl.admin.show.products.information;

import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_PRODUCT_ID;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_FEED_AND_OTHER_PAGE_PATH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;

public class ShowMoreDetailsAboutProductFeedsAndOtherCommand extends ShowMoreDetailsAboutProduct implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		String productId = request.getParameter(PARAMETER_PRODUCT_ID);

		return new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_FEED_AND_OTHER_PAGE_PATH);
	}

}
