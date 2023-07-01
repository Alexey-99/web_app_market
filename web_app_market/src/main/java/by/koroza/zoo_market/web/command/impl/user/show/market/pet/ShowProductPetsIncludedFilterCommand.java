package by.koroza.zoo_market.web.command.impl.user.show.market.pet;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_LIST_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.REQUEST_ATTRIBUTE_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BREED_PET_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BREED_PET_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PET_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PET_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_VALUE_DISCOUNT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_VALUE_DISCOUNT_RUS;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MAX_NUMBER_MONTHS_PET;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MAX_NUMBER_YEARS_PET;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MAX_PRICE_PET;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MAX_PROCENT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MIN_NUMBER_MONTHS_PET;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MIN_NUMBER_YEARS_PET;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MIN_PRICE_PET;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MIN_PROCENT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_PET_BREED;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_PET_TYPE;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PRODUCTS_PETS_PAGE_PATH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.validation.FilterValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.command.exception.InputException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowProductPetsIncludedFilterCommand implements Command {
	public static final String INPUT_EXCEPTION_TYPE_DESCOUNT = TypeException.DISCOUNT.toString();
	public static final String INPUT_EXCEPTION_TYPE_PRICE = TypeException.PRICE.toString();
	public static final String INPUT_EXCEPTION_TYPE_YEAR_MONTH = TypeException.YEAR_MONTH.toString();

	public enum TypeException {
		DISCOUNT, PRICE, YEAR_MONTH
	}

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		List<Pet> pets = new ArrayList<>();
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER);
		try {
			FilterPet filter = null;
			try {
				filter = getParametersFromFilter(request);
				pets = ProductPetServiceImpl.getInstance().getProductsPetsByFilter(filter);
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS, pets);
				if (session.getAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP) == null) {
					List<Pet> allProductsPets = ProductPetServiceImpl.getInstance().getAllHavingProductsPets();
					Map<String, Set<String>> filterMap = createFilter(allProductsPets, session);
					session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
				}
				session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER, filter);
			} catch (InputException e) {
				pets = ProductPetServiceImpl.getInstance().getAllHavingProductsPets();
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS, pets);
				if (session.getAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP) == null) {
					Map<String, Set<String>> filterMap = createFilter(pets, session);
					session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
				}
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		isRegistratedUser(request);
		return new Router(PRODUCTS_PETS_PAGE_PATH);
	}

	private FilterPet getParametersFromFilter(HttpServletRequest request) throws InputException {
		Map<String, String> mapTypeAndMessageException = new HashMap<>();
		FilterPet filter = null;

		String[] choosedTypesPets = request.getParameterValues(INPUT_PET_TYPE);
		String[] choosedBreedPets = request.getParameterValues(INPUT_PET_BREED);
		boolean onlyPetsWithDiscont = (request.getParameterValues(INPUT_PROMOTIONS) != null ? true : false);

		String minDiscount = request.getParameter(INPUT_MIN_PROCENT_PROMOTIONS) != null
				? request.getParameter(INPUT_MIN_PROCENT_PROMOTIONS)
				: "";
		String maxDiscount = request.getParameter(INPUT_MAX_PROCENT_PROMOTIONS) != null
				? request.getParameter(INPUT_MAX_PROCENT_PROMOTIONS)
				: "";

		if (!FilterValidation.validInputValuesNumbersDiscount(minDiscount, maxDiscount)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapTypeAndMessageException.put(INPUT_EXCEPTION_TYPE_DESCOUNT,
						"Вы ввели не корректно величину скидки. Вы ввели: минимальная скидка = " + minDiscount
								+ ", максимальная скидка = " + maxDiscount);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapTypeAndMessageException.put(INPUT_EXCEPTION_TYPE_DESCOUNT,
						"You input discount values incorrect: minDiscont = " + minDiscount + ", maxDiscont = "
								+ maxDiscount);
			}
		}
		String minPrice = request.getParameter(INPUT_MIN_PRICE_PET) != null ? request.getParameter(INPUT_MIN_PRICE_PET)
				: "";
		String maxPrice = request.getParameter(INPUT_MAX_PRICE_PET) != null ? request.getParameter(INPUT_MAX_PRICE_PET)
				: "";
		if (!FilterValidation.validInputValuesNumbersPrice(minPrice, maxPrice)) {
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapTypeAndMessageException.put(INPUT_EXCEPTION_TYPE_DESCOUNT,
						"Вы ввели не корректно цену. Вы ввели: минимальная цена = " + minPrice
								+ ", максимальная цена = " + maxPrice);
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapTypeAndMessageException.put(INPUT_EXCEPTION_TYPE_PRICE,
						"You input price values incorrect: min price = " + minPrice + ", max price = " + maxPrice);
			}
		}
		String minYear = request.getParameter(INPUT_MIN_NUMBER_YEARS_PET) != null
				? request.getParameter(INPUT_MIN_NUMBER_YEARS_PET)
				: "";
		String minMonths = request.getParameter(INPUT_MIN_NUMBER_MONTHS_PET) != null
				? request.getParameter(INPUT_MIN_NUMBER_MONTHS_PET)
				: "";
		String maxYear = request.getParameter(INPUT_MAX_NUMBER_YEARS_PET) != null
				? request.getParameter(INPUT_MAX_NUMBER_YEARS_PET)
				: "";
		String maxMonths = request.getParameter(INPUT_MAX_NUMBER_MONTHS_PET) != null
				? request.getParameter(INPUT_MAX_NUMBER_MONTHS_PET)
				: "";
		if (!FilterValidation.validInputValuesNumbersYearMonth(minYear, maxYear, minMonths, maxMonths)) {
			StringBuilder build = new StringBuilder();
			if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(RUSSIAN)) {
				mapTypeAndMessageException.put(INPUT_EXCEPTION_TYPE_YEAR_MONTH,
						build.append("Вы ввели не корректно год или месяц: ").append("минимальный возрост = ")
								.append(!minYear.isBlank() ? minYear : 0).append("лет")
								.append(!minMonths.isBlank() ? minMonths : 0).append("месяцев; максимальный возрост = ")
								.append(!maxYear.isBlank() ? maxYear : 0).append("лет")
								.append(!maxMonths.isBlank() ? maxMonths : 0).append("месяцев;").toString());
			} else if (((String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE)).equals(ENGLISH)) {
				mapTypeAndMessageException.put(INPUT_EXCEPTION_TYPE_YEAR_MONTH,
						build.append("You input values year or month incorrect: ").append("minYear")
								.append(!minYear.isBlank() ? minYear : 0).append(", minMonths = ")
								.append(!minMonths.isBlank() ? minMonths : 0).append("; maxYear = ")
								.append(!maxYear.isBlank() ? maxYear : 0).append(", maxMonths = ")
								.append(!maxMonths.isBlank() ? maxMonths : 0).toString());
			}
		}
		if (mapTypeAndMessageException.size() > 0) {
			request.getSession().setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
					mapTypeAndMessageException);
			throw new InputException(mapTypeAndMessageException.toString());
		} else {
			filter = new FilterPet.FilterPetBuilder().setChoosedTypesPets(choosedTypesPets)
					.setChoosedBreedPets(choosedBreedPets).setOnlyProductsWithDiscont(onlyPetsWithDiscont)
					.setMinDiscont(!minDiscount.isBlank() ? Double.parseDouble(minDiscount) : 0)
					.setMaxDiscont(!maxDiscount.isBlank() ? Double.parseDouble(maxDiscount) : 0)
					.setMinPrice(!minPrice.isBlank() ? Double.parseDouble(minPrice) : 0)
					.setMaxPrice(!maxPrice.isBlank() ? Double.parseDouble(maxPrice) : 0)
					.setMinNumberYear(!minYear.isBlank() ? Integer.parseInt(minYear) : 0)
					.setMinNumberMonth(!minMonths.isBlank() ? Integer.parseInt(minMonths) : 0)
					.setMaxNumberYear(!maxYear.isBlank() ? Integer.parseInt(maxYear) : 0)
					.setMaxNumberMonth(!maxMonths.isBlank() ? Integer.parseInt(maxMonths) : 0).build();
		}
		return filter;
	}

	private Map<String, Set<String>> createFilter(List<Pet> petsList, HttpSession session) {
		Map<String, Set<String>> filterMap = new HashMap<>();
		if (session.getAttribute(ATTRIBUTE_SESSION_LOCALE).equals(RUSSIAN)) {
			filterMap.put(CHOOSE_TYPE_PET_RUS, createFilterBySpeciePets(petsList));
			filterMap.put(CHOOSE_BREED_PET_RUS, createFilterByBreedPets(petsList));
			if (isHavingPromotionsProductsPets(petsList)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_RUS, createFilterByPromotionsProductsPets(petsList, session));
			}
		} else if (session.getAttribute(ATTRIBUTE_SESSION_LOCALE).equals(ENGLISH)) {
			filterMap.put(CHOOSE_TYPE_PET_EN, createFilterBySpeciePets(petsList));
			filterMap.put(CHOOSE_BREED_PET_EN, createFilterByBreedPets(petsList));
			if (isHavingPromotionsProductsPets(petsList)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_EN, createFilterByPromotionsProductsPets(petsList, session));
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

	private Set<String> createFilterByPromotionsProductsPets(List<Pet> petsList, HttpSession session) {
		Set<String> promotionsPetsSet = null;
		if (isHavingPromotionsProductsPets(petsList)) {
			promotionsPetsSet = new HashSet<>();
			if (session.getAttribute(ATTRIBUTE_SESSION_LOCALE).equals(RUSSIAN)) {
				promotionsPetsSet.add("только акционные товары");
			} else if (session.getAttribute(ATTRIBUTE_SESSION_LOCALE).equals(ENGLISH)) {
				promotionsPetsSet.add("only products with discount");
			}
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