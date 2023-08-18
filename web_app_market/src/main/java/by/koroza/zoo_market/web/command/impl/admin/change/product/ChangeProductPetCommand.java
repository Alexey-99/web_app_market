package by.koroza.zoo_market.web.command.impl.admin.change.product;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ADMIN_PAGE_CHANGE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_BIRTH_DATE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_BREED;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_SPECIE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_BIRTH_DATE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_BREED;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_SPECIE;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_BIRTH_DATE;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_BREED;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_IMAGE;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_PRICE;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_SPECIE;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_BREED;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_ID;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_PRICE;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_SPECIE;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_IS_CORRECT_FILE;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_PART;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CHANGE_PET_PRODUCT;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.image.ImageFileServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.service.validation.impl.product.ProductPetValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class ChangeProductPetCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CHANGE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getId() == ADMIN.getId()) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				Map<Pet, Long> petAndNumber = getInputParameters(request, mapInputExceptions);
				if (mapInputExceptions.isEmpty()) {
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET, (Pet) petAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT,
							petAndNumber.get((Pet) petAndNumber.keySet().toArray()[0]));
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CHANGE_PET_PRODUCT);
				} else {
					session.setAttribute(ATTRIBUTE_ADMIN_PAGE_CHANGE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
							mapInputExceptions);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET, (Pet) petAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT,
							petAndNumber.get((Pet) petAndNumber.keySet().toArray()[0]));
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM);
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

	private Map<Pet, Long> getInputParameters(HttpServletRequest request, Map<String, String> mapInputExceptions)
			throws IOException, ServiceException, CommandException {
		Map<Pet, Long> petAndNumber = new HashMap<>();
		HttpSession session = request.getSession();
		String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
		long id = Long.parseLong(request.getParameter(ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_ID));
		Pet pet = session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET) != null
				? (Pet) session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET)
				: ProductPetServiceImpl.getInstance().getProductPetById(id);
		if (pet != null) {
			getInputParameterImage(request, pet, sessionLocale, mapInputExceptions);
			String specie = getInputParameterSpecie(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_SPECIE)) {
				pet.setSpecie(specie);
			}
			String breed = getInputParameterBreed(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_BREED)) {
				pet.setBreed(breed);
			}
			String birthDate = getInputParameterBirthDate(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_BIRTH_DATE)) {
				pet.setBirthDate(birthDate);
			}
			String price = getInputParameterPrice(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_PRICE)) {
				pet.setPrice(Double.parseDouble(price));
			}
			String discount = getInputParameterDiscount(request, sessionLocale, mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_DISCOUNT)) {
				pet.setDiscount(Double.parseDouble(discount));
			}
			String numberOfUnitsProduct = getInputParameterNumberOfUnitsProduct(request, sessionLocale,
					mapInputExceptions);
			if (!mapInputExceptions.containsKey(TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT)) {
				petAndNumber.put(pet, Long.parseLong(numberOfUnitsProduct));
			} else {
				petAndNumber.put(pet, 0L);
			}
		} else {
			log.log(Level.ERROR, "This product can't change because product didn't found in database.");
		}
		return petAndNumber;
	}

	private void getInputParameterImage(HttpServletRequest request, Pet pet, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException, IOException {
		String oldImagePath = pet.getImagePath();
		if (request.getParameter(ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE) == null) {
			if ((boolean) request.getAttribute(PARAMETER_IS_CORRECT_FILE)) {
				Part part = (Part) request.getAttribute(PARAMETER_PART);
				if (part != null && !part.getSubmittedFileName().isBlank()) {
					pet.setImagePath(ImageFileServiceImpl.getInstance().saveImageOnDisk(part.getInputStream(),
							part.getSubmittedFileName()));
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
			pet.setImagePath(null);
		}
		if ((pet.getImagePath() != null ? !pet.getImagePath().equals(oldImagePath)
				: pet.getImagePath() == null && oldImagePath != null) && (oldImagePath != null)) {
			ImageFileServiceImpl.getInstance().removeProductImage(oldImagePath);
		}
	}

	private String getInputParameterSpecie(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String specie = request.getParameter(ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_SPECIE);
		if (!ProductPetValidationImpl.getInstance().validPetSpecie(specie)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_SPECIE, RU_MESSAGE_TYPE_INPUT_EXCEPTION_SPECIE + specie);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_SPECIE, EN_MESSAGE_TYPE_INPUT_EXCEPTION_SPECIE + specie);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_SPECIE, EN_MESSAGE_TYPE_INPUT_EXCEPTION_SPECIE + specie);
			}
		}
		return specie;
	}

	private String getInputParameterBreed(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String breed = request.getParameter(ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_BREED);
		if (!ProductPetValidationImpl.getInstance().validPetBreed(breed)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BREED, RU_MESSAGE_TYPE_INPUT_EXCEPTION_BREED + breed);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BREED, EN_MESSAGE_TYPE_INPUT_EXCEPTION_BREED + breed);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BREED, EN_MESSAGE_TYPE_INPUT_EXCEPTION_BREED + breed);
			}
		}
		return breed;
	}

	private String getInputParameterBirthDate(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String birthDate = request.getParameter(ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE);
		if (!ProductPetValidationImpl.getInstance().validPetBirthDate(birthDate)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BIRTH_DATE,
						RU_MESSAGE_TYPE_INPUT_EXCEPTION_BIRTH_DATE + birthDate);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BIRTH_DATE,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_BIRTH_DATE + birthDate);
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_BIRTH_DATE,
						EN_MESSAGE_TYPE_INPUT_EXCEPTION_BIRTH_DATE + birthDate);
			}
		}
		return birthDate;
	}

	private String getInputParameterPrice(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String price = request.getParameter(ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_PRICE);
		if (!ProductPetValidationImpl.getInstance().validProductPrice(price)) {
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

	private String getInputParameterDiscount(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String discount = request.getParameter(ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_DISCOUNT);
		if (!ProductPetValidationImpl.getInstance().validProductDiscount(discount)) {
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

	private String getInputParameterNumberOfUnitsProduct(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String numberOfUnitsProduct = request
				.getParameter(ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT);
		if (!ProductPetValidationImpl.getInstance().validNumberOfUnitsProduct(numberOfUnitsProduct)) {
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