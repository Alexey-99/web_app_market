package by.koroza.zoo_market.web.command.impl.user.show.market.pet.page;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.REQUEST_ATTRIBUTE_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_RU;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BREED_PET_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BREED_PET_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PET_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PET_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_VALUE_DISCOUNT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_VALUE_DISCOUNT_RUS;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PRODUCTS_PETS_PAGE_PATH;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowProductPetNumberPageCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		try {
			List<Pet> allProductsPets = ProductPetServiceImpl.getInstance().getAllHavingProductsPets();
			Map<String, Set<String>> filterMap = createFilter(allProductsPets,
					(String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE));
			session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		return new Router(PRODUCTS_PETS_PAGE_PATH);
	}

	private Map<String, Set<String>> createFilter(List<Pet> petsList, String sessionLocale) {
		Map<String, Set<String>> filterMap = new LinkedHashMap<>();
		if (sessionLocale.equals(RUSSIAN)) {
			filterMap.put(CHOOSE_TYPE_PET_RUS, createFilterBySpeciePets(petsList));
			filterMap.put(CHOOSE_BREED_PET_RUS, createFilterByBreedPets(petsList));
			if (isHavingDiscountProducts(petsList)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_RUS, createFilterByPromotionsProductsPets(petsList, sessionLocale));
			}
		} else if (sessionLocale.equals(ENGLISH)) {
			filterMap.put(CHOOSE_TYPE_PET_EN, createFilterBySpeciePets(petsList));
			filterMap.put(CHOOSE_BREED_PET_EN, createFilterByBreedPets(petsList));
			if (isHavingDiscountProducts(petsList)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_EN, createFilterByPromotionsProductsPets(petsList, sessionLocale));
			}
		}
		return filterMap;
	}

	private Set<String> createFilterBySpeciePets(List<Pet> petsList) {
		Set<String> speciesPetsSet = new HashSet<>();
		petsList.forEach(pet -> speciesPetsSet.add(pet.getSpecie()));
		return speciesPetsSet;
	}

	private Set<String> createFilterByBreedPets(List<Pet> petsList) {
		Set<String> breedsPetsSet = new HashSet<>();
		petsList.forEach(pet -> breedsPetsSet.add(pet.getBreed()));
		return breedsPetsSet;
	}

	private Set<String> createFilterByPromotionsProductsPets(List<Pet> petsList, String sessionLocale) {
		Set<String> promotionsPetsSet = null;
		if (isHavingDiscountProducts(petsList)) {
			promotionsPetsSet = new HashSet<>();
			if (sessionLocale.equals(RUSSIAN)) {
				promotionsPetsSet.add(CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_EN);
			} else if (sessionLocale.equals(ENGLISH)) {
				promotionsPetsSet.add(CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_RU);
			}
		}
		return promotionsPetsSet;
	}

	private boolean isHavingDiscountProducts(List<Pet> petsList) {
		boolean isHavingDiscount = false;
		for (Pet pet : petsList) {
			if (pet.getDiscount() > 0) {
				isHavingDiscount = true;
			}
		}
		return isHavingDiscount;
	}
}