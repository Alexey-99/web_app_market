package by.koroza.zoo_market.web.command.impl.admin.change.product;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_BRAND;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_PET_TYPES;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRODUCT_TYPE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_BRAND;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PET_TYPES;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRODUCT_TYPE;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_BRAND;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_DESCRIPTION;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_IMAGE;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_PET_TYPES;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_PRICE;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_PRODUCT_TYPE;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_ID;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_IS_CORRECT_FILE;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_PART;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CHANGE_FEED_AND_OTHER_PRODUCT;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.image.ImageFileServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.validation.impl.product.ProductFeedsAndOtherValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class ChangeProductFeedsAndOtherCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getIdRole() == ADMIN.getIdRole()) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				Map<FeedAndOther, Long> productAndNumber = getInputParameters(request, mapInputExceptions);
				if (mapInputExceptions.isEmpty()) {
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER,
							(FeedAndOther) productAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT,
							productAndNumber.get((FeedAndOther) productAndNumber.keySet().toArray()[0]));
					router = new Router(
							PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CHANGE_FEED_AND_OTHER_PRODUCT);
				} else {
					session.setAttribute(
							ATTRIBUTE_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
							mapInputExceptions);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER,
							(FeedAndOther) productAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT,
							productAndNumber.get((FeedAndOther) productAndNumber.keySet().toArray()[0]));
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (IOException | ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}

	private Map<FeedAndOther, Long> getInputParameters(HttpServletRequest request,
			Map<String, String> mapInputExceptions) throws IOException, CommandException, ServiceException {
		Map<FeedAndOther, Long> petAndNumber = new HashMap<>();
		HttpSession session = request.getSession();
		String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
		long id = Long.parseLong(request.getParameter(ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_ID));
		FeedAndOther productFeedAndOther = session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER) != null
				? (FeedAndOther) session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER)
				: ProductFeedsAndOtherServiceImpl.getInstance().getProductFeedAndOtherById(id);
		if (productFeedAndOther != null) {
			getInputParameterImage(request, productFeedAndOther, sessionLocale, mapInputExceptions);
			String type = getInputParameterProductType(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_PRODUCT_TYPE)) {
				productFeedAndOther.setProductType(type);
			}
			String brand = getInputParameterProductBrand(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_BRAND)) {
				productFeedAndOther.setBrand(brand);
			}
			String description = getInputParameterProductDescription(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_DESCRIPTION)) {
				productFeedAndOther.setDescriptions(description);
			}
			String petTypes = getInputParameterProductPetTypes(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_PET_TYPES)) {
				productFeedAndOther.setPetTypes(petTypes);
			}
			String price = getInputParameterProductPrice(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_PRICE)) {
				productFeedAndOther.setPrice(Double.parseDouble(price));
			}
			String discount = getInputParameterProductDiscount(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_DISCOUNT)) {
				productFeedAndOther.setDiscount(Double.parseDouble(discount));
			}
			String numberOfUnitsProduct = getInputParameterProductNumberOfUnitsProduct(request, sessionLocale,
					mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT)) {
				petAndNumber.put(productFeedAndOther, Long.parseLong(numberOfUnitsProduct));
			} else {
				petAndNumber.put(productFeedAndOther, 0L);
			}
		} else {
			log.log(Level.ERROR, "This product can't change because product didn't found in database.");
		}
		return petAndNumber;
	}

	private void getInputParameterImage(HttpServletRequest request, FeedAndOther productFeedAndOther,
			String sessionLocale, Map<String, String> mapInputExceptions) throws ServiceException, IOException {
		String oldImagePath = productFeedAndOther.getImagePath();
		if (request.getParameter(ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE) == null) {
			if ((boolean) request.getAttribute(PARAMETER_IS_CORRECT_FILE)) {
				Part part = (Part) request.getAttribute(PARAMETER_PART);
				if (part != null && !part.getSubmittedFileName().isBlank()) {
					productFeedAndOther.setImagePath(ImageFileServiceImpl.getInstance()
							.saveImageOnDisk(part.getInputStream(), part.getSubmittedFileName()));
				}
			} else {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_IMAGE, RU_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_IMAGE, EN_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE);
				} else {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_IMAGE, EN_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE);
				}
			}
		} else {
			productFeedAndOther.setImagePath(null);
		}
		if ((productFeedAndOther.getImagePath() != null
				? !productFeedAndOther.getImagePath().equalsIgnoreCase(oldImagePath)
				: productFeedAndOther.getImagePath() == null && oldImagePath != null) && (oldImagePath != null)) {
			ImageFileServiceImpl.getInstance().removeProductImage(oldImagePath);
		}
	}

	private String getInputParameterProductType(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String type = request.getParameter(ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE);
		if (!ProductFeedsAndOtherValidationImpl.getInstance().validProductType(type)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRODUCT_TYPE,
						RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRODUCT_TYPE + type);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRODUCT_TYPE,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRODUCT_TYPE + type);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRODUCT_TYPE,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRODUCT_TYPE + type);
			}

		}
		return type;
	}

	private String getInputParameterProductBrand(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String brand = request.getParameter(ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND);
		if (!ProductFeedsAndOtherValidationImpl.getInstance().validBrand(brand)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BRAND, RU_MESSAGE_TYPE_INPUT_EXCEPTION_BRAND + brand);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BRAND, EN_MESSAGE_TYPE_INPUT_EXCEPTION_BRAND + brand);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BRAND, EN_MESSAGE_TYPE_INPUT_EXCEPTION_BRAND + brand);
			}
		}
		return brand;
	}

	private String getInputParameterProductDescription(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String description = request.getParameter(ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION);
		if (!ProductFeedsAndOtherValidationImpl.getInstance().validDescription(description)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DESCRIPTION,
						RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM + description);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DESCRIPTION,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM + description);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DESCRIPTION,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM + description);
			}
		}
		return description;
	}

	private String getInputParameterProductPetTypes(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String petTypes = request.getParameter(ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES);
		if (!ProductFeedsAndOtherValidationImpl.getInstance().validPetTypes(petTypes)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PET_TYPES,
						RU_MESSAGE_TYPE_INPUT_EXCEPTION_PET_TYPES + petTypes);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PET_TYPES,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_PET_TYPES + petTypes);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PET_TYPES,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_PET_TYPES + petTypes);
			}
		}
		return petTypes;
	}

	private String getInputParameterProductPrice(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String price = request.getParameter(ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE);
		if (!ProductFeedsAndOtherValidationImpl.getInstance().validProductPrice(price)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE, RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM + price);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE, EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM + price);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE, EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM + price);
			}
		}
		return price;
	}

	private String getInputParameterProductDiscount(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String discount = request.getParameter(ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT);
		if (!ProductFeedsAndOtherValidationImpl.getInstance().validProductDiscount(discount)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
						RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM + discount);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM + discount);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM + discount);
			}
		}
		return discount;
	}

	private String getInputParameterProductNumberOfUnitsProduct(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String numberOfUnitsProduct = request
				.getParameter(ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT);
		if (!ProductFeedsAndOtherValidationImpl.getInstance().validNumberOfUnitsProduct(numberOfUnitsProduct)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT,
						RU_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT + numberOfUnitsProduct);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT + numberOfUnitsProduct);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT + numberOfUnitsProduct);
			}
		}
		return numberOfUnitsProduct;
	}
}