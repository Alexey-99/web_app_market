package by.koroza.zoo_market.web.command.impl.user.show.pesonal;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER_HISTORY_ORDERS;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ORDERS_HISTORY_PAGE_PATH;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.service.sorting.order.impl.SortingOrdersImpl;
import by.koroza.zoo_market.service.sorting.order.impl.comparator.impl.date.SortComparatorOrdersByDateAtCreatingThenStatusId;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class ShowPersonalAccountHistoryOrdersPageCommand.
 */
public class ShowPersonalAccountHistoryOrdersPageCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.getRole().getId() >= USER.getId() && user.isVerificatedEmail()) {
				List<Order> listOrders = SortingOrdersImpl.getInstance().sortOrders(
						OrderServiceImpl.getInstance().getOrderProductsByUserId(user.getId()),
						new SortComparatorOrdersByDateAtCreatingThenStatusId());
				session.setAttribute(ATTRIBUTE_USER_HISTORY_ORDERS, listOrders);
				router = new Router(PERSONAL_ACCOUNT_ORDERS_HISTORY_PAGE_PATH);
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}
}