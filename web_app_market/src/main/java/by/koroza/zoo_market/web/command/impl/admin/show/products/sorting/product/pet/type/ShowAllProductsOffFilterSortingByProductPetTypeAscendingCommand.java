package by.koroza.zoo_market.web.command.impl.admin.show.products.sorting.product.pet.type;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.REQUEST_ATTRIBUTE_COMMAND;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.SESSION_ATTRIBUTE_PET_CLASS_NAME;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeValue.SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeValue.SESSION_ATTRIBUTE_PET_CLASS;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.service.sorting.SortingMapProducts;
import by.koroza.zoo_market.service.sorting.comparator.impl.product.pet.type.SortProductsByProductPetTypeAscendingComparatorImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowAllProductsOffFilterSortingByProductPetTypeAscendingCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.getRole().getIdRole() == ADMIN.getIdRole() && user.isVerificatedEmail()) {
				Map<Pet, Long> productPets = ProductPetServiceImpl.getInstance().getAllProductsPetsAndNumberOfUnits();
				Map<FeedAndOther, Long> productFeedsAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
						.getAllProductsFeedAndOtherAndNumberOfUnits();
				Map<AbstractProduct, Long> products = new HashMap<>();
				products.putAll(productPets);
				products.putAll(productFeedsAndOther);
				session.setAttribute(ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT,
						SortingMapProducts.getInstance().sortProductsMap(products,
								new SortProductsByProductPetTypeAscendingComparatorImpl()));
				session.setAttribute(SESSION_ATTRIBUTE_PET_CLASS_NAME, SESSION_ATTRIBUTE_PET_CLASS);
				session.setAttribute(SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME,
						SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS);
				router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		request.setAttribute(REQUEST_ATTRIBUTE_COMMAND, request.getParameter(PARAMETER_COMMAND).trim());
		isRegisteredUser(request);
		return router;
	}
}