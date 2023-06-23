package by.koroza.zoo_market.web.command.impl.admin.change;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_UPLOAD_FILE_DIRECTORY;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;

import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_ID;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BREED;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_PRICE;
import static by.koroza.zoo_market.web.command.name.InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_SPECIE;

import static by.koroza.zoo_market.web.command.name.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;

import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM;
import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CHANGE_PET_PRODUCT;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_IS_CORRECT_FILE;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_PART;

import static by.koroza.zoo_market.web.command.name.ParameterValue.ADMIN_PAGE_CREATE_PRODUCT_FORM_WITHOUT_IMAGE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.image.ImageFileServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.validation.PetValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.command.impl.admin.create.CraetePetProductCommand.TypeInputException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class ChangeProductPetCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			if (user != null && user.isVerificatedEmail() == true
					&& user.getRole().getIdRole() == UserRole.ADMIN.getIdRole()) {
				Map<String, String> mapInputExceptions = new HashMap<>();
				Map<Pet, Long> petAndNumber = this.getInputParameters(request, mapInputExceptions);
				if (mapInputExceptions.isEmpty()) {
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET, (Pet) petAndNumber.keySet().toArray()[0]);
					session.setAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT,
							petAndNumber.get((Pet) petAndNumber.keySet().toArray()[0]));
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CHANGE_PET_PRODUCT);
				} else {
					session.setAttribute(ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
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
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}

	private Map<Pet, Long> getInputParameters(HttpServletRequest request, Map<String, String> mapInputExceptions)
			throws IOException, ServiceException, CommandException {
		Map<Pet, Long> petAndNumber = new HashMap<>();
		HttpSession session = request.getSession();
		long id = Long.parseLong(request.getParameter(ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM_INPUT_ID));
		Pet pet = session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET) != null
				? (Pet) session.getAttribute(ATTRIBUTE_BUFFER_PRODUCT_PET)
				: ProductPetServiceImpl.getInstance().getProductPetById(id);
		if (pet != null) {
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
							"Вы ввели не корректно количество данного товара питомца. Вы ввели: "
									+ numberOfUnitsProduct);
				} else if (((String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
					mapInputExceptions.put(TypeInputException.NUMBER_OF_UNITS_PRODUCT.toString(),
							"You input number of units pet incorrect. Your input: " + numberOfUnitsProduct);
				}
				petAndNumber.put(pet, 0L);
			} else {
				petAndNumber.put(pet, Long.parseLong(numberOfUnitsProduct));
			}
		} else {
			throw new CommandException("This product pet can't change because product didn't found in database.");
		}
		return petAndNumber;
	}
}