package by.koroza.zoo_market.web.command.impl.user.show.basket;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_SAVED_PRODUCTS_ID_IN_JSP_PAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.BACKET_WITH_PRODUCTS_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.status.OrderStatus;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowBacketPageCommand implements Command {
	private static Logger log = LogManager.getLogger();
	private static final String REG_EX_SPLIT_BY_PART = "(\\s\\;\\s)";
	private static final String REG_EX_SPLIT_BY_TYPE_PRODUCT_AND_ID = "\\,";

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		String[] productsIdArr = request.getParameterValues(PARAMETER_SAVED_PRODUCTS_ID_IN_JSP_PAGE);
		Map<String, String> productsIdMap = parseSavedIdProducts(
				productsIdArr[0] != null ? productsIdArr[0] : productsIdArr[1]);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		if (user != null && user.getRole().getIdRole() >= USER.getIdRole() && user.isVerificatedEmail()) {
			try {
				Order order = null;
				if (productsIdMap != null) {
					List<Pet> productsPets = ProductPetServiceImpl.getInstance().getProductsPetsById(productsIdMap);
					List<FeedAndOther> productsOther = ProductFeedsAndOtherServiceImpl.getInstance()
							.getHavingProductsFeedAndOtherById(productsIdMap);
					order = new Order.OrderBuilder().setUserId(user.getId()).setProductsPets(productsPets)
							.setOtherProducts(productsOther)
							.setTotalPaymentAmount(
									OrderServiceImpl.getInstance().calcTotalPaymentAmount(productsPets, productsOther))
							.setTotalProductsDiscountAmount(OrderServiceImpl.getInstance()
									.calcTotalProductsDiscountAmount(productsPets, productsOther))
							.setStatus(OrderStatus.OPEN).build();
					order.setTotalPersonDiscountAmount(OrderServiceImpl.getInstance().calcTotalPersonDiscountAmount(
							order.getTotalPaymentAmount(), order.getTotalProductsDiscountAmount(), user.getDiscount()));
					order.setTotalDiscountAmount(OrderServiceImpl.getInstance().calcTotalDiscountAmount(
							order.getTotalProductsDiscountAmount(), order.getTotalPersonDiscountAmount()));
					order.setTotalPaymentWithDiscountAmount(
							OrderServiceImpl.getInstance().calcTotalPaymentWithDiscountAmount(
									order.getTotalPaymentAmount(), order.getTotalDiscountAmount()));
				} else {
					order = new Order.OrderBuilder().setUserId(user.getId()).build();
				}
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

	private Map<String, String> parseSavedIdProducts(String productsId) {
		Map<String, String> productsIdMap = new HashMap<>();
		for (String part : productsId.split(REG_EX_SPLIT_BY_PART)) {
			if (!part.isBlank()) {
				String[] typeProductAndId = part.split(REG_EX_SPLIT_BY_TYPE_PRODUCT_AND_ID);
				productsIdMap.put(typeProductAndId[0], typeProductAndId[1]);
			}
		}
		return productsIdMap;
	}
}