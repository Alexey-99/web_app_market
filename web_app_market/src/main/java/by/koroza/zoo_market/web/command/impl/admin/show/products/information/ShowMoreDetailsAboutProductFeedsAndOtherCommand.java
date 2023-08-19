package by.koroza.zoo_market.web.command.impl.admin.show.products.information;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.SESSION_ATTRIBUTE_SHOW_MORE_DETAILS_ABOUT_RODUCT;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_PRODUCT_ID;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_FEED_AND_OTHER_PAGE_PATH;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.detalization.InformatorAboutProductFeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.status.OrderStatus;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.OrderService;
import by.koroza.zoo_market.service.ProductFeedsAndOtherService;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowMoreDetailsAboutProductFeedsAndOtherCommand extends ShowMoreDetailsAboutProduct implements Command {
	private static Logger log = LogManager.getLogger();

	private final ProductFeedsAndOtherService PRODUCT_FFED_AND_OTHER_SERVICE = ProductFeedsAndOtherServiceImpl
			.getInstance();
	private final OrderService ORDER_SERVICE = OrderServiceImpl.getInstance();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getId() == ADMIN.getId()) {
				String productId = request.getParameter(PARAMETER_PRODUCT_ID);
				if (productId != null && productId.matches("\\d+")) {
					FeedAndOther product = PRODUCT_FFED_AND_OTHER_SERVICE.getProductById(Long.parseLong(productId));
					if (product != null) {
						InformatorAboutProductFeedAndOther informatorAboutProduct = new InformatorAboutProductFeedAndOther();
						informatorAboutProduct.setProduct(product);
						informatorAboutProduct.setQuantityAvailable(
								PRODUCT_FFED_AND_OTHER_SERVICE.getFreeNumberOfUnitsByProductId(product.getId()));
						informatorAboutProduct.setQuantityInReserveInOpenOrders(
								PRODUCT_FFED_AND_OTHER_SERVICE.getQuantityInOrdersByProductIdAndOrderStatus(
										product.getId(), OrderStatus.OPEN.getId()));
						informatorAboutProduct.setQuantityInReserveInWaitingPayOrders(
								PRODUCT_FFED_AND_OTHER_SERVICE.getQuantityInOrdersByProductIdAndOrderStatus(
										product.getId(), OrderStatus.WAITING_PAY.getId()));
						informatorAboutProduct
								.setQuantityInReserve(informatorAboutProduct.getQuantityInReserveInOpenOrders()
										+ informatorAboutProduct.getQuantityInReserveInWaitingPayOrders());
						informatorAboutProduct.setQuantityInReserveInCloseOrders(
								PRODUCT_FFED_AND_OTHER_SERVICE.getQuantityInOrdersByProductIdAndOrderStatus(
										product.getId(), OrderStatus.CLOSED.getId()));
						informatorAboutProduct.setDetailsOpenOrdersWithProduct(
								ORDER_SERVICE.getDetailsAboutOrdersByProductFeedAndOtherIdAndOrderStatus(
										OrderStatus.OPEN.getId(), product.getId()));
						informatorAboutProduct.setDetailsWaitingPayOrdersWithProduct(
								ORDER_SERVICE.getDetailsAboutOrdersByProductFeedAndOtherIdAndOrderStatus(
										OrderStatus.WAITING_PAY.getId(), product.getId()));
						informatorAboutProduct.setDetailsCloseOrdersWithProduct(
								ORDER_SERVICE.getDetailsAboutOrdersByProductFeedAndOtherIdAndOrderStatus(
										OrderStatus.CLOSED.getId(), product.getId()));
						session.setAttribute(SESSION_ATTRIBUTE_SHOW_MORE_DETAILS_ABOUT_RODUCT, informatorAboutProduct);
						router = new Router(
								PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_FEED_AND_OTHER_PAGE_PATH);
					} else {
						router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
					}
				} else {
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
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