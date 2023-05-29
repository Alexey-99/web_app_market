package by.koroza.zoo_market.web.command.impl.admin.create;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;

import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CREATE_FEEDS_AND_OTHER_PRODUCT;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM;

import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT;

import static by.koroza.zoo_market.web.command.name.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_PART;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_IS_CORRECT_FILE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.validation.FeedsAndOtherValidation;
import by.koroza.zoo_market.validation.PetValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class CraeteOtherProductCommand implements Command {
	public static final String INPUT_EXCEPTION_TYPE_IMAGE = TypeInputException.IMAGE.toString();
	public static final String INPUT_EXCEPTION_TYPE_PRODUCT_TYPE = TypeInputException.PRODUCT_TYPE.toString();
	public static final String INPUT_EXCEPTION_TYPE_BRAND = TypeInputException.BRAND.toString();
	public static final String INPUT_EXCEPTION_TYPE_DESCRIPTION = TypeInputException.DESCRIPTION.toString();
	public static final String INPUT_EXCEPTION_TYPE_PET_TYPES = TypeInputException.PET_TYPES.toString();
	public static final String INPUT_EXCEPTION_TYPE_PRICE = TypeInputException.PRICE.toString();
	public static final String INPUT_EXCEPTION_TYPE_DISCOUNT = TypeInputException.DISCOUNT.toString();
	public static final String INPUT_EXCEPTION_TYPE_NUMBER_OF_UNITS_PRODUCT = TypeInputException.NUMBER_OF_UNITS_PRODUCT
			.toString();

	public enum TypeInputException {
		IMAGE, PRODUCT_TYPE, BRAND, DESCRIPTION, PET_TYPES, PRICE, DISCOUNT, NUMBER_OF_UNITS_PRODUCT;
	}

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
				Map<String, String> mapInputExceptions = new HashMap<>();
				Map<FeedAndOther, Long> productAndNumber = getInputParameters(request, mapInputExceptions);
				if (mapInputExceptions.isEmpty()) {
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER,
							(FeedAndOther) productAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT,
							productAndNumber.get((FeedAndOther) productAndNumber.keySet().toArray()[0]));
					router = new Router(
							PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CREATE_FEEDS_AND_OTHER_PRODUCT);
				} else {
					session.setAttribute(
							ATTRIBUTE_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
							mapInputExceptions);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER,
							(FeedAndOther) productAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT,
							productAndNumber.get((FeedAndOther) productAndNumber.keySet().toArray()[0]));
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM);
				}
			}
		} catch (IOException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}

	private Map<FeedAndOther, Long> getInputParameters(HttpServletRequest request,
			Map<String, String> mapInputExceptions) throws IOException {
		Map<FeedAndOther, Long> petAndNumber = new HashMap<>();
		FeedAndOther productFeedAndOther = new FeedAndOther();
		if ((boolean) request.getAttribute(PARAMETER_IS_CORRECT_FILE)) {
			Part part = (Part) request.getAttribute(PARAMETER_PART);
			productFeedAndOther.setImgBytes(part.getInputStream().readAllBytes());
		} else {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.IMAGE.toString(),
						"Вы выбрали не корретный файл для картинки для товара");
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.IMAGE.toString(), "You choosed image incorrect for product");
			}
		}
		String type = request.getParameter(ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE);
		if (!FeedsAndOtherValidation.validPetType(type)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.PRODUCT_TYPE.toString(),
						"Вы ввели не корректно тип товара. Вы ввели: " + type);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.PRODUCT_TYPE.toString(),
						"You input product type incorrect. Your input: " + type);
			}
		} else {
			productFeedAndOther.setProductType(type);
		}
		String brand = request.getParameter(ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND);
		if (!FeedsAndOtherValidation.validBrand(brand)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.BRAND.toString(),
						"Вы ввели не корректно брэнд товара. Вы ввели: " + brand);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.BRAND.toString(),
						"You input brand of product incorrect. Your input: " + brand);
			}
		} else {
			productFeedAndOther.setBrand(brand);
		}
		String description = request.getParameter(ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION);
		if (!FeedsAndOtherValidation.validDescrription(description)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.DESCRIPTION.toString(),
						"Вы ввели не корректно описание товара. Вы ввели: " + description);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.DESCRIPTION.toString(),
						"You input description of product incorrect. Your input: " + description);
			}
		} else {
			productFeedAndOther.setDescriptions(description);
		}
		String petTypes = request.getParameter(ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES);
		if (!FeedsAndOtherValidation.validPetType(petTypes)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.PET_TYPES.toString(),
						"Вы ввели не корректно типы(тип) питомцев для кого данный товар. Вы ввели: " + petTypes);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.PET_TYPES.toString(),
						"You entered, type(types) of pets for whom this product, incorrect. Your input: " + petTypes);
			}
		} else {
			productFeedAndOther.setPetTypes(petTypes);
		}
		String price = request.getParameter(ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE);
		if (!PetValidation.validPetPrice(price)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.PRICE.toString(),
						"Вы ввели не корректно цену товара. Вы ввели: " + price);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.PRICE.toString(),
						"You input product price incorrect. Your input: " + price);
			}
		} else {
			productFeedAndOther.setPrice(Double.parseDouble(price));
		}
		String discount = request.getParameter(ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT);
		if (!PetValidation.validPetDiscount(discount)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.DISCOUNT.toString(),
						"Вы ввели не корректно скидку на продукт. Вы ввели: " + discount);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.DISCOUNT.toString(),
						"You input product discount incorrect. Your input: " + discount);
			}
		} else {
			productFeedAndOther.setDiscount(Double.parseDouble(discount));
		}
		String numberOfUnitsProduct = request
				.getParameter(ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT);
		if (!PetValidation.validPetNumberOfUnitsProduct(numberOfUnitsProduct)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.NUMBER_OF_UNITS_PRODUCT.toString(),
						"Вы ввели не корректно количество данного товара. Вы ввели: " + numberOfUnitsProduct);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.NUMBER_OF_UNITS_PRODUCT.toString(),
						"You input number of units product incorrect. Your input: " + numberOfUnitsProduct);
			}
			petAndNumber.put(productFeedAndOther, 0L);
		} else {
			petAndNumber.put(productFeedAndOther, Long.parseLong(numberOfUnitsProduct));
		}
		return petAndNumber;
	}
}