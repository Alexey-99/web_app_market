package by.koroza.zoo_market.web.command.impl.user.show.basket;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.BACKET_WITH_PRODUCTS_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowBacketPageCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		if (user != null && user.getRole().getIdRole() >= USER.getIdRole() && user.isVerificatedEmail()) {
			try {
				Order order = OrderServiceImpl.getInstance().getOpenOrderByUserId(user.getId());
				session.setAttribute(ATTRIBUTE_ORDER, order);
				router = new Router(BACKET_WITH_PRODUCTS_PAGE_PATH);
			} catch (ServiceException e) {
				log.log(Level.ERROR, e.getMessage());
				throw new CommandException(e);
			}
		} else {
			router = new Router(HOME_PAGE_PATH);
		}
		return router;
	}
}