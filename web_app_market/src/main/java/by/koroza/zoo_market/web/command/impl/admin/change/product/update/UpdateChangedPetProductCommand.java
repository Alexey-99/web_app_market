package by.koroza.zoo_market.web.command.impl.admin.change.product.update;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT;
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
import by.koroza.zoo_market.service.sorting.SortingProducts;
import by.koroza.zoo_market.service.sorting.comparator.map.impl.product.id.SortProductsByIdAscendingComparatorImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UpdateChangedPetProductCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getIdRole() == ADMIN.getIdRole()) {
				Pet pet = (Pet) session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET);
				if (pet != null) {
					long numberOfUnitsProduct = session
							.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT) != null
									? (long) session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT)
									: 0;
					String oldImagePath = ProductPetServiceImpl.getInstance()
							.getProductImagePathByProductId(pet.getId());
					ProductPetServiceImpl.getInstance().updateProductPet(pet, numberOfUnitsProduct);
					if (oldImagePath != null) {
						ImageFileServiceImpl.getInstance().removeProductImage(oldImagePath);
					}
					Map<Pet, Long> productPets = ProductPetServiceImpl.getInstance()
							.getAllProductsPetsAndNumberOfUnits();
					Map<FeedAndOther, Long> productFeedsAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
							.getAllProductsFeedAndOtherAndNumberOfUnits();
					Map<AbstractProduct, Long> products = new HashMap<>();
					products.putAll(productPets);
					products.putAll(productFeedsAndOther);
					session.setAttribute(ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT, SortingProducts
							.getInstance().sortProductsMap(products, new SortProductsByIdAscendingComparatorImpl()));
					session.setAttribute(SESSION_ATTRIBUTE_PET_CLASS_NAME, SESSION_ATTRIBUTE_PET_CLASS);
					session.setAttribute(SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME,
							SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS);
					removeBufferAtributes(session);
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
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
		session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET);
		session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT);
	}
}