package by.koroza.zoo_market.web.command.impl.user.payment;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.BACKET_WITH_PRODUCTS_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.SUCCESS_ORDER_PAYMENT_PAGE_PATH;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class OrderPaymentCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getIdRole() >= USER.getIdRole()) {
				Order order = (Order) session.getAttribute(ATTRIBUTE_ORDER);
				if (order != null && (order.getProductsPets().size() + order.getOtherProducts().size()) > 0) {
					@SuppressWarnings("unchecked")
					Map<Integer, Boolean> haveProductPets = session
							.getAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS) != null
									? (Map<Integer, Boolean>) session
											.getAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS)
									: new LinkedHashMap<>();
					haveProductPets.entrySet().stream().forEach(entry -> {
						if (!entry.getValue()) {
							order.getProductsPets().remove((int) entry.getKey());
						}
					});
					@SuppressWarnings("unchecked")
					Map<Integer, Boolean> haveProductFeedAndOther = session
							.getAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER) != null
									? (Map<Integer, Boolean>) session
											.getAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER)
									: new LinkedHashMap<>();
					haveProductFeedAndOther.entrySet().stream().forEach(entry -> {
						if (!entry.getValue()) {
							order.getOtherProducts().remove((int) entry.getKey());
						}
					});
					order.setTotalPaymentAmount(OrderServiceImpl.getInstance()
							.calcTotalPaymentAmount(order.getProductsPets(), order.getOtherProducts()));
					order.setTotalProductsDiscountAmount(OrderServiceImpl.getInstance()
							.calcTotalProductsDiscountAmount(order.getProductsPets(), order.getOtherProducts()));
					order.setTotalPersonDiscountAmount(OrderServiceImpl.getInstance().calcTotalPersonDiscountAmount(
							order.getTotalPaymentAmount(), order.getTotalProductsDiscountAmount(), user.getDiscount()));
					order.setTotalDiscountAmount(OrderServiceImpl.getInstance().calcTotalDiscountAmount(
							order.getTotalProductsDiscountAmount(), order.getTotalPersonDiscountAmount()));
					order.setTotalPaymentWithDiscountAmount(
							OrderServiceImpl.getInstance().calcTotalPaymentWithDiscountAmount(
									order.getTotalPaymentAmount(), order.getTotalDiscountAmount()));
					OrderServiceImpl.getInstance().addOrder(order, user.getId());
					UserServiceImpl.getInstance().changePersonPercentDiscount(user);
					router = new Router(SUCCESS_ORDER_PAYMENT_PAGE_PATH);
					session.removeAttribute(ATTRIBUTE_ORDER);
					session.removeAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS);
					session.removeAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER);
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