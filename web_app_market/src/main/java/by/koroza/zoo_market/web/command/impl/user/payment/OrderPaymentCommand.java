package by.koroza.zoo_market.web.command.impl.user.payment;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.BACKET_WITH_PRODUCTS_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.SUCCESS_ORDER_PAYMENT_PAGE_PATH;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.status.OrderStatus;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class OrderPaymentCommand.
 */
public class OrderPaymentCommand implements Command {

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
			if (user != null && user.isVerificatedEmail() && user.getRole().getIdRole() >= USER.getIdRole()) {
				Order order = OrderServiceImpl.getInstance().getOpenOrderByUserId(user.getId());
				if (order != null && (order.getProductsPets().size() + order.getOtherProducts().size()) > 0) {
					order.setStatus(OrderStatus.CLOSED);
					OrderServiceImpl.getInstance().changeOrder(order);
					UserServiceImpl.getInstance().changePersonPercentDiscount(user);
					router = new Router(SUCCESS_ORDER_PAYMENT_PAGE_PATH);
					session.removeAttribute(ATTRIBUTE_ORDER);
				} else {
					router = new Router(BACKET_WITH_PRODUCTS_PAGE_PATH);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}
}