package by.koroza.zoo_market.web.command.impl.admin.show.products;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.REQUEST_ATTRIBUTE_COMMAND;
import static by.koroza.zoo_market.web.command.name.AttributeName.SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME;
import static by.koroza.zoo_market.web.command.name.AttributeName.SESSION_ATTRIBUTE_PET_CLASS_NAME;

import static by.koroza.zoo_market.web.command.name.AttributeValue.SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS;
import static by.koroza.zoo_market.web.command.name.AttributeValue.SESSION_ATTRIBUTE_PET_CLASS;

import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH;

import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_ID_ASCENDING;

import java.util.HashMap;
import java.util.Map;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.service.sorting.SortingMapProducts;
import by.koroza.zoo_market.service.sorting.comparator.id.SortProductsByIdAscendingComparatorImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowAllProductsOffFilterCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.getRole().getIdRole() == UserRole.ADMIN.getIdRole()
					&& user.isVerificatedEmail() == true) {
				Map<Pet, Long> productPets = ProductPetServiceImpl.getInstance().getAllProductsPetsAndNumberOfUnits();
				Map<FeedAndOther, Long> productFeedsAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
						.getAllProductsFeedAndOtherAndNumberOfUnits();
				Map<AbstractProduct, Long> products = new HashMap<>();
				products.putAll(productPets);
				products.putAll(productFeedsAndOther);
				session.setAttribute(ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT, SortingMapProducts
						.getInstance().sortProductsMap(products, new SortProductsByIdAscendingComparatorImpl()));
				session.setAttribute(SESSION_ATTRIBUTE_PET_CLASS_NAME, SESSION_ATTRIBUTE_PET_CLASS);
				session.setAttribute(SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME,
						SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS);
				router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException | SortingException e) {
			throw new CommandException(e);
		}
		request.setAttribute(REQUEST_ATTRIBUTE_COMMAND,
				COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_ID_ASCENDING);
		isRegistratedUser(request);
		return router;
	}
}