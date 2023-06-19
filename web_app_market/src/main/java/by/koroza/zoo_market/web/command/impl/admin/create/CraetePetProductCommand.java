package by.koroza.zoo_market.web.command.impl.admin.create;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_UPLOAD_FILE_DIRECTORY;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;

import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CREATE_PET_PRODUCT;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_PET_PRODUCT_FORM;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_IS_CORRECT_FILE;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_PART;

import static by.koroza.zoo_market.web.command.name.ParameterValue.ADMIN_PAGE_CREATE_PRODUCT_FORM_WITHOUT_IMAGE;

import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_SPECIE;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BREED;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_PRICE;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT;

import static by.koroza.zoo_market.web.command.name.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.ImageFileServiceImpl;
import by.koroza.zoo_market.validation.PetValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class CraetePetProductCommand implements Command {
	public static final String INPUT_EXCEPTION_TYPE_IMAGE = TypeInputException.IMAGE.toString();
	public static final String INPUT_EXCEPTION_TYPE_SPECIE = TypeInputException.SPECIE.toString();
	public static final String INPUT_EXCEPTION_TYPE_BREED = TypeInputException.BREED.toString();
	public static final String INPUT_EXCEPTION_TYPE_BIRTH_DATE = TypeInputException.BIRTH_DATE.toString();
	public static final String INPUT_EXCEPTION_TYPE_PRICE = TypeInputException.PRICE.toString();
	public static final String INPUT_EXCEPTION_TYPE_DISCOUNT = TypeInputException.DISCOUNT.toString();
	public static final String INPUT_EXCEPTION_TYPE_NUMBER_OF_UNITS_PRODUCT = TypeInputException.NUMBER_OF_UNITS_PRODUCT
			.toString();

	public enum TypeInputException {
		IMAGE, SPECIE, BREED, BIRTH_DATE, PRICE, DISCOUNT, NUMBER_OF_UNITS_PRODUCT;
	}

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
				Map<String, String> mapInputExceptions = new HashMap<>();
				Map<Pet, Long> petAndNumber = getInputParameters(request, mapInputExceptions);
				if (mapInputExceptions.isEmpty()) {
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET, (Pet) petAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT,
							petAndNumber.get((Pet) petAndNumber.keySet().toArray()[0]));
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CREATE_PET_PRODUCT);
				} else {
					session.setAttribute(ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
							mapInputExceptions);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET, (Pet) petAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT,
							petAndNumber.get((Pet) petAndNumber.keySet().toArray()[0]));
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_PET_PRODUCT_FORM);
				}
			}
		} catch (IOException | ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}

	private Map<Pet, Long> getInputParameters(HttpServletRequest request, Map<String, String> mapInputExceptions)
			throws IOException, ServiceException {
		Map<Pet, Long> petAndNumber = new HashMap<>();
		HttpSession session = request.getSession();
		Pet pet = session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET) != null
				? (Pet) session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET)
				: new Pet();
		if (request.getParameter(ADMIN_PAGE_CREATE_PRODUCT_FORM_WITHOUT_IMAGE) == null) {
			if ((boolean) request.getAttribute(PARAMETER_IS_CORRECT_FILE)) {
				Part part = (Part) request.getAttribute(PARAMETER_PART);
				if (part != null && !part.getSubmittedFileName().isBlank()) {
					pet.setImagePath(ImageFileServiceImpl.getInstance().saveImageOnDisk(part,
							(String) request.getAttribute(ATTRIBUTE_UPLOAD_FILE_DIRECTORY)));
				}
			} else {
				if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
					mapInputExceptions.put(TypeInputException.IMAGE.toString(),
							"Вы выбрали не корретный файл для картинки для товара");
				} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
					mapInputExceptions.put(TypeInputException.IMAGE.toString(),
							"You choosed image incorrect for product");
				}
			}
		} else {
			pet.setImagePath(null);
		}
		String specie = request.getParameter(ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_SPECIE);
		if (!PetValidation.validPetSpecie(specie)) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.SPECIE.toString(),
						"Вы ввели не корректно тип питомца. Вы ввели: " + specie);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.SPECIE.toString(),
						"You input pet specie incorrect. Your input: " + specie);
			}
		} else {
			pet.setSpecie(specie);
		}
		String breed = request.getParameter(ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BREED);
		if (!PetValidation.validPetBreed(breed)) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.BREED.toString(),
						"Вы ввели не корректно породу питомца. Вы ввели: " + breed);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.BREED.toString(),
						"You input pet breed incorrect. Your input: " + breed);
			}
		} else {
			pet.setBreed(breed);
		}
		String birthDate = request.getParameter(ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE);
		if (!PetValidation.validPetBirthDate(birthDate)) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.BIRTH_DATE.toString(),
						"Вы ввели не корректно дату рождения питомца. Вы ввели: " + birthDate);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.BIRTH_DATE.toString(),
						"You input pet birth date incorrect. Your input: " + birthDate);
			}
		} else {
			pet.setBirthDate(birthDate);
		}
		String price = request.getParameter(ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_PRICE);
		if (!PetValidation.validPetPrice(price)) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.PRICE.toString(),
						"Вы ввели не корректно цену питомца. Вы ввели: " + price);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.PRICE.toString(),
						"You input pet price incorrect. Your input: " + price);
			}
		} else {
			pet.setPrice(Double.parseDouble(price));
		}
		String discount = request.getParameter(ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_DISCOUNT);
		if (!PetValidation.validPetDiscount(discount)) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.DISCOUNT.toString(),
						"Вы ввели не корректно скидку питомца. Вы ввели: " + discount);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.DISCOUNT.toString(),
						"You input pet discount incorrect. Your input: " + discount);
			}
		} else {
			pet.setDiscount(Double.parseDouble(discount));
		}
		String numberOfUnitsProduct = request
				.getParameter(ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT);
		if (!PetValidation.validPetNumberOfUnitsProduct(numberOfUnitsProduct)) {
			if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapInputExceptions.put(TypeInputException.NUMBER_OF_UNITS_PRODUCT.toString(),
						"Вы ввели не корректно количество данного товара питомца. Вы ввели: " + numberOfUnitsProduct);
			} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapInputExceptions.put(TypeInputException.NUMBER_OF_UNITS_PRODUCT.toString(),
						"You input number of units pet incorrect. Your input: " + numberOfUnitsProduct);
			}
			petAndNumber.put(pet, 0L);
		} else {
			petAndNumber.put(pet, Long.parseLong(numberOfUnitsProduct));
		}
		return petAndNumber;
	}
}