package by.koroza.zoo_market.web.command.impl.show.pesonal;

import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ORDERS_HISTORY_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER_HISTORY_ORDERS;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.OrderServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowPersonalAccountHistoryOrdersPageCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null) {
				session.setAttribute(ATTRIBUTE_USER_HISTORY_ORDERS,
						OrderServiceImpl.getInstance().getOrderProductsByUserId(user.getId()));
				router = new Router(PERSONAL_ACCOUNT_ORDERS_HISTORY_PAGE_PATH);
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}
}