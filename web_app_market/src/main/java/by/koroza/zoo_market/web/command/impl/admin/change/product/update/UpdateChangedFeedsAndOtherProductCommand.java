package by.koroza.zoo_market.web.command.impl.admin.change.product.update;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.SESSION_ATTRIBUTE_PET_CLASS_NAME;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeValue.SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeValue.SESSION_ATTRIBUTE_PET_CLASS;
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
import by.koroza.zoo_market.service.impl.image.ImageFileServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.service.sorting.product.impl.SortingProductsImpl;
import by.koroza.zoo_market.service.sorting.product.impl.comparator.map.impl.product.id.SortProductsByIdAscendingComparatorImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UpdateChangedFeedsAndOtherProductCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getId() == ADMIN.getId()) {
				FeedAndOther productFeedAndOther = session
						.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER) != null
								? (FeedAndOther) session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER)
								: null;
				if (productFeedAndOther != null) {
					long numberOfUnitsProduct = (long) session
							.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT);
					String oldImagePath = ProductFeedsAndOtherServiceImpl.getInstance()
							.getProductImagePathByProductId(productFeedAndOther.getId());
					ProductFeedsAndOtherServiceImpl.getInstance().updateProductById(productFeedAndOther,
							numberOfUnitsProduct);
					if (oldImagePath != null) {
						ImageFileServiceImpl.getInstance().removeProductImage(oldImagePath);
					}
					Map<Pet, Long> productPets = ProductPetServiceImpl.getInstance().getAllProductsAndNumberOfUnits();
					Map<FeedAndOther, Long> productFeedsAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
							.getAllProductsAndNumberOfUnits();
					Map<AbstractProduct, Long> products = new HashMap<>();
					products.putAll(productPets);
					products.putAll(productFeedsAndOther);
					session.setAttribute(ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT, SortingProductsImpl
							.getInstance().sortProductsMap(products, new SortProductsByIdAscendingComparatorImpl()));
					session.setAttribute(SESSION_ATTRIBUTE_PET_CLASS_NAME, SESSION_ATTRIBUTE_PET_CLASS);
					session.setAttribute(SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME,
							SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS);
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
					removeBufferAtributes(session);
				} else {
					router = new Router(HOME_PAGE_PATH);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}

	private void removeBufferAtributes(HttpSession session) {
		session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER);
		session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT);
	}
}