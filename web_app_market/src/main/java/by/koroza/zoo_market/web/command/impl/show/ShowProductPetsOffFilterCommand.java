package by.koroza.zoo_market.web.command.impl.show;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.ProductPetServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controler.Router;
import by.koroza.zoo_market.web.command.exception.CommandException;

import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_TYPE_PET;
import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_BREED_PET;
import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_VALUE_DISCOUNT;

import static by.koroza.zoo_market.web.command.name.PagePathName.PRODUCTS_PETS_PAGE_PATH;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_LIST_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowProductPetsOffFilterCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER);
		List<Pet> pets = new ArrayList<>();
		try {
			pets = ProductPetServiceImpl.getInstance().getAllHavingProductsPets();
			session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS, pets);
			Map<String, Set<String>> filterMap = createFilter(pets);
			session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return new Router(PRODUCTS_PETS_PAGE_PATH);
	}

	private Map<String, Set<String>> createFilter(List<Pet> petsList) {
		Map<String, Set<String>> filterMap = new HashMap<>();
		filterMap.put(CHOOSE_TYPE_PET, createFilterBySpeciePets(petsList));
		filterMap.put(CHOOSE_BREED_PET, createFilterByBreedPets(petsList));
		if (isHavingPromotionsProductsPets(petsList)) {
			filterMap.put(CHOOSE_VALUE_DISCOUNT, createFilterByPromotionsProductsPets(petsList));
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

	private Set<String> createFilterByPromotionsProductsPets(List<Pet> petsList) {
		Set<String> promotionsPetsSet = null;
		if (isHavingPromotionsProductsPets(petsList)) {
			promotionsPetsSet = new HashSet<>();
			promotionsPetsSet.add("только акционные товары");
		}
		return promotionsPetsSet;
	}

	private boolean isHavingPromotionsProductsPets(List<Pet> petsList) {
		boolean havingPromotions = false;
		for (Pet pet : petsList) {
			if (pet.getDiscount() > 0) {
				havingPromotions = true;
			}
		}
		return havingPromotions;
	}
}