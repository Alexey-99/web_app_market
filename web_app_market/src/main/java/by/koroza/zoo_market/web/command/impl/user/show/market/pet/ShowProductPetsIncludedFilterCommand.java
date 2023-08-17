package by.koroza.zoo_market.web.command.impl.user.show.market.pet;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_LIST_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.REQUEST_ATTRIBUTE_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FIVE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FOUR;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_SIX;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FIVE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FOUR;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_SIX;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_PRICE;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_YEAR_MONTH;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.ProductPetService;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.factory.MarketFilterProductFactory;
import by.koroza.zoo_market.service.factory.impl.MarketFilterProductFactoryImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.service.sorting.product.SortingProducts;
import by.koroza.zoo_market.service.sorting.product.impl.SortingProductsImpl;
import by.koroza.zoo_market.service.sorting.product.impl.comparator.list.product.impl.id.SortProductsByIdAscendingComparatorImpl;
import by.koroza.zoo_market.service.validation.impl.filter.FilterValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class ShowProductPetsIncludedFilterCommand.
 */
public class ShowProductPetsIncludedFilterCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The sort products. */
	private final SortingProducts SORT_PRODUCTS = SortingProductsImpl.getInstance();

	/** The product pet service. */
	private final ProductPetService PRODUCT_PET_SERVICE = ProductPetServiceImpl.getInstance();

	/** The filter factory. */
	private final MarketFilterProductFactory FILTER_FACTORY = MarketFilterProductFactoryImpl.getInstance();

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER);
		try {
			Map<String, String> mapInputExceptions = new HashMap<>();
			String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
			FilterPet filter = getInputParameters(request, sessionLocale, mapInputExceptions);
			if (mapInputExceptions.isEmpty()) {
				List<Entry<Pet, Long>> pets = PRODUCT_PET_SERVICE.getProductsPetsByFilter(filter);
				pets = SORT_PRODUCTS.sortProductsPets(pets, new SortProductsByIdAscendingComparatorImpl());
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS, pets);
				if (session.getAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP) == null) {
					Map<String, Set<String>> filterMap = FILTER_FACTORY.createFilterPets(
							PRODUCT_PET_SERVICE.getAllProductsPetsAndNumberOfUnits().keySet(), sessionLocale);
					session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
				}
				session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER, filter);
			} else {
				session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
						mapInputExceptions);
				Map<Pet, Long> mapPets = PRODUCT_PET_SERVICE.getAllProductsPetsAndNumberOfUnits();
				List<Entry<Pet, Long>> sortedPets = SORT_PRODUCTS.sortProductsPets(mapPets.entrySet().stream().toList(),
						new SortProductsByIdAscendingComparatorImpl());
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS, sortedPets);
				Map<String, Set<String>> filterMap = FILTER_FACTORY.createFilterPets(mapPets.keySet(), sessionLocale);
				session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
			}
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		return new Router(PRODUCTS_PETS_PAGE_PATH);
	}

	/**
	 * Get the input parameters.
	 *
	 * @param request            the request
	 * @param sessionLocale      the session locale
	 * @param mapInputExceptions the map input exceptions
	 * @return the input parameters
	 * @throws ServiceException the service exception
	 */
	private FilterPet getInputParameters(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String[] choosedTypesPets = getInputParameterTypesPets(request);
		String[] choosedBreedPets = getInputParameterBreedPets(request);
		boolean onlyPetsWithDiscont = getInputParameterOnlyProductsWithDiscount(request);
		String[] minMaxDiscount = getInputParametersMinMaxDiscount(request, sessionLocale, mapInputExceptions);
		String minDiscount = minMaxDiscount[0];
		String maxDiscount = minMaxDiscount[1];
		String[] minMaxPrice = getInputParametersMinMaxPrice(request, sessionLocale, mapInputExceptions);
		String minPrice = minMaxPrice[0];
		String maxPrice = minMaxPrice[1];
		String[] minMaxMonthYear = getInputParametersMonthYearStartEnd(request, sessionLocale, mapInputExceptions);
		String minYear = minMaxMonthYear[0];
		String minMonths = minMaxMonthYear[1];
		String maxYear = minMaxMonthYear[2];
		String maxMonths = minMaxMonthYear[3];
		return mapInputExceptions.isEmpty()
				? new FilterPet.FilterPetBuilder().setChoosedTypesPets(choosedTypesPets)
						.setChoosedBreedPets(choosedBreedPets).setOnlyProductsWithDiscont(onlyPetsWithDiscont)
						.setMinDiscont(
								minDiscount != null && !minDiscount.isBlank() ? Double.parseDouble(minDiscount) : 0)
						.setMaxDiscont(
								maxDiscount != null && !maxDiscount.isBlank() ? Double.parseDouble(maxDiscount) : 0)
						.setMinPrice(minPrice != null && !minPrice.isBlank() ? Double.parseDouble(minPrice) : 0)
						.setMaxPriceAllProducts(findMaxPriceInListProducts())
						.setMaxPriceEntered(maxPrice != null && !maxPrice.isBlank() ? Double.parseDouble(maxPrice)
								: findMaxPriceInListProducts())
						.setMinNumberYear(minPrice != null && !minYear.isBlank() ? Integer.parseInt(minYear) : 0)
						.setMinNumberMonth(minMonths != null && !minMonths.isBlank() ? Integer.parseInt(minMonths) : 0)
						.setMaxNumberYear(maxYear != null && !maxYear.isBlank() ? Integer.parseInt(maxYear) : 0)
						.setMaxNumberMonth(maxMonths != null && !maxMonths.isBlank() ? Integer.parseInt(maxMonths) : 0)
						.build()
				: null;
	}

	/**
	 * Get the input parameter types pets.
	 *
	 * @param request the request
	 * @return the input parameter types pets
	 */
	private String[] getInputParameterTypesPets(HttpServletRequest request) {
		String[] choosedTypesPets = request.getParameterValues(INPUT_PET_TYPE);
		return choosedTypesPets;
	}

	/**
	 * Get the input parameter breed pets.
	 *
	 * @param request the request
	 * @return the input parameter breed pets
	 */
	private String[] getInputParameterBreedPets(HttpServletRequest request) {
		String[] choosedBreedPets = request.getParameterValues(INPUT_PET_BREED);
		return choosedBreedPets;
	}

	/**
	 * Get the input parameter only products with discount.
	 *
	 * @param request the request
	 * @return the input parameter only products with discount
	 */
	private boolean getInputParameterOnlyProductsWithDiscount(HttpServletRequest request) {
		boolean onlyProductsWithDiscount = request.getParameterValues(INPUT_PROMOTIONS) != null;
		return onlyProductsWithDiscount;
	}

	/**
	 * Get the input parameters min max discount.
	 *
	 * @param request            the request
	 * @param sessionLocale      the session locale
	 * @param mapInputExceptions the map input exceptions
	 * @return the input parameters min max discount
	 */
	private String[] getInputParametersMinMaxDiscount(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String minDiscount = request.getParameter(INPUT_MIN_PROCENT_PROMOTIONS);
		String maxDiscount = request.getParameter(INPUT_MAX_PROCENT_PROMOTIONS);
		if (minDiscount != null && maxDiscount != null) {
			if (!FilterValidationImpl.getInstance().validInputValuesNumbersDiscount(minDiscount, maxDiscount)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
							RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_ONE + minDiscount
									+ RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_TWO
									+ maxDiscount);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
							EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_ONE + minDiscount
									+ EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_TWO
									+ maxDiscount);
				} else {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
							EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_ONE + minDiscount
									+ EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_TWO
									+ maxDiscount);
				}
			}
		} else {
			if (minDiscount != null) {
				if (!FilterValidationImpl.getInstance().validInputValueNumberDiscount(minDiscount)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN + minDiscount);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN + minDiscount);
					} else {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN + minDiscount);
					}
				}
			} else if (maxDiscount != null) {
				if (!FilterValidationImpl.getInstance().validInputValueNumberDiscount(maxDiscount)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX + maxDiscount);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX + maxDiscount);
					} else {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX + maxDiscount);
					}
				}
			}
		}
		return new String[] { minDiscount, maxDiscount };
	}

	/**
	 * Get the input parameters min max price.
	 *
	 * @param request            the request
	 * @param sessionLocale      the session locale
	 * @param mapInputExceptions the map input exceptions
	 * @return the input parameters min max price
	 */
	private String[] getInputParametersMinMaxPrice(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String minPrice = request.getParameter(INPUT_MIN_PRICE_PET);
		String maxPrice = request.getParameter(INPUT_MAX_PRICE_PET);
		if (minPrice != null && maxPrice != null) {
			if (!FilterValidationImpl.getInstance().validInputValuesNumbersPrice(minPrice, maxPrice)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
							RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE + minPrice
									+ RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO + maxPrice);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
							EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE + minPrice
									+ EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO + maxPrice);
				} else {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
							EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE + minPrice
									+ EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO + maxPrice);
				}
			}
		} else {
			if (minPrice != null) {
				if (!FilterValidationImpl.getInstance().validInputValueNumberPrice(minPrice)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN + minPrice);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN + minPrice);
					} else {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN + minPrice);
					}
				}
			} else if (maxPrice != null) {
				if (!FilterValidationImpl.getInstance().validInputValueNumberPrice(maxPrice)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX + maxPrice);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX + maxPrice);
					} else {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX + maxPrice);
					}
				}
			}
		}
		return new String[] { minPrice, maxPrice };
	}

	/**
	 * Get the input parameters month year start end.
	 *
	 * @param request            the request
	 * @param sessionLocale      the session locale
	 * @param mapInputExceptions the map input exceptions
	 * @return the input parameters month year start end
	 */
	private String[] getInputParametersMonthYearStartEnd(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String minYear = request.getParameter(INPUT_MIN_NUMBER_YEARS_PET);
		String minMonths = request.getParameter(INPUT_MIN_NUMBER_MONTHS_PET);
		String maxYear = request.getParameter(INPUT_MAX_NUMBER_YEARS_PET);
		String maxMonths = request.getParameter(INPUT_MAX_NUMBER_MONTHS_PET);
		if (!FilterValidationImpl.getInstance().validInputValuesNumbersYearMonth(minYear, maxYear, minMonths,
				maxMonths)) {
			StringBuilder build = new StringBuilder();
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_YEAR_MONTH,
						build.append(RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_ONE)
								.append(RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_TWO)
								.append(minYear != null && !minYear.isBlank() ? minYear : 0)
								.append(RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE)
								.append(minMonths != null && !minMonths.isBlank() ? minMonths : 0)
								.append(RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FOUR)
								.append(maxYear != null && !maxYear.isBlank() ? maxYear : 0)
								.append(RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FIVE)
								.append(maxMonths != null && !maxMonths.isBlank() ? maxMonths : 0)
								.append(RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_SIX).toString());
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_YEAR_MONTH,
						build.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_ONE)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_TWO)
								.append(minYear != null && !minYear.isBlank() ? minYear : 0)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE)
								.append(minMonths != null && !minMonths.isBlank() ? minMonths : 0)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FOUR)
								.append(maxYear != null && !maxYear.isBlank() ? maxYear : 0)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FIVE)
								.append(maxMonths != null && !maxMonths.isBlank() ? maxMonths : 0)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_SIX).toString());
			} else {
				mapInputExceptions.put(TYPE_INPUT_EXCEPTION_YEAR_MONTH,
						build.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_ONE)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_TWO)
								.append(minYear != null && !minYear.isBlank() ? minYear : 0)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE)
								.append(minMonths != null && !minMonths.isBlank() ? minMonths : 0)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FOUR)
								.append(maxYear != null && !maxYear.isBlank() ? maxYear : 0)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FIVE)
								.append(maxMonths != null && !maxMonths.isBlank() ? maxMonths : 0)
								.append(EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_SIX).toString());
			}
		}
		return new String[] { minYear, minMonths, maxYear, maxMonths };
	}

	/**
	 * Find max price in list products.
	 *
	 * @return the double
	 * @throws ServiceException the service exception
	 */
	private double findMaxPriceInListProducts() throws ServiceException {
		double maxPrice = 0;
		Map<Pet, Long> products = PRODUCT_PET_SERVICE.getAllProductsPetsAndNumberOfUnits();
		for (Entry<Pet, Long> entry : products.entrySet()) {
			Pet product = entry.getKey();
			Long numberOfUnits = entry.getValue();
			if (numberOfUnits > 0 && product.getPrice() > maxPrice) {
				maxPrice = product.getPrice();
			}
		}
		return maxPrice;
	}
}