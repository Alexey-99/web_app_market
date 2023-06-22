package by.koroza.zoo_market.web.command.impl.admin.change.update;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_MAP_PRODUCTS_PET_AND_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET;

import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH;

import java.util.Map;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.impl.ProductPetServiceImpl;
import by.koroza.zoo_market.service.sorting.SortingMapAbstractProduct;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UpdateChangedPetProductCommand implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user == null || user.isVerificatedEmail() == false
					|| user.getRole().getIdRole() != UserRole.ADMIN.getIdRole()) {
				router = new Router(HOME_PAGE_PATH);
			} else {
				Pet pet = (Pet) session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET);
				if (pet != null) {
					long numberOfUnitsProduct = (long) session
							.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT);
					ProductPetServiceImpl.getInstance().upadateProductPet(pet, numberOfUnitsProduct);
					Map<Pet, Long> productsPetAndNumber = ProductPetServiceImpl.getInstance()
							.getAllProductsPetsAndNumberOfUnits();
					session.setAttribute(ATTRIBUTE_MAP_PRODUCTS_PET_AND_NUMBER_OF_UNITS_PRODUCT,
							(Map<Pet, Long>) SortingMapAbstractProduct.getInstance().sortMapById(productsPetAndNumber));
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);

					session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET);
					session.removeAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT);
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