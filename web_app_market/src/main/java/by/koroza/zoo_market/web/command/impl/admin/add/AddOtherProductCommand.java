package by.koroza.zoo_market.web.command.impl.admin.add;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT;

import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH;

import java.util.Map;

import by.koroza.zoo_market.model.calculation.Calculator;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.impl.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.sorting.SortingMapAbstractProduct;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AddOtherProductCommand implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user == null || user.isVerificatedEmail() == false
					|| user.getRole().getIdRole() != UserRole.ADMIN.getIdRole()) {
				router = new Router(HOME_PAGE_PATH);
			} else {
				FeedAndOther otherProduct = (FeedAndOther) session
						.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER);
				if (otherProduct != null) {
					long numberOfUnitsProduct = (long) session
							.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT);
					ProductFeedsAndOtherServiceImpl.getInstance().addProduct(otherProduct, numberOfUnitsProduct);
					if (session
							.getAttribute(ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT) != null) {
						Map<FeedAndOther, Long> productsPetAndNumber = (Map<FeedAndOther, Long>) session
								.getAttribute(ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT);
						otherProduct.setTotalPrice(otherProduct.getPrice() - Calculator.getInstance()
								.calcProcentFromSum(otherProduct.getPrice(), otherProduct.getDiscount()));
						productsPetAndNumber.put(otherProduct, numberOfUnitsProduct);
						session.setAttribute(ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT,
								(Map<FeedAndOther, Long>) SortingMapAbstractProduct.getInstance()
										.sortMapById(productsPetAndNumber));
						router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
					} else {
						router = new Router(HOME_PAGE_PATH);
					}
					session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER);
					session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT);
				} else {
					router = new Router(HOME_PAGE_PATH);
				}
			}
		} catch (ServiceException | SortingException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}
}