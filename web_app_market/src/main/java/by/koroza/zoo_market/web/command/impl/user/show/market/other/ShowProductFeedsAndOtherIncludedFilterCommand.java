package by.koroza.zoo_market.web.command.impl.user.show.market.other;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP;
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
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_DISCOUNT;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPE_INPUT_EXCEPTION_PRICE;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MAX_PRICE_PET;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MAX_PROCENT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MIN_PRICE_PET;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_MIN_PROCENT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_PET_TYPE;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_PRODUCT_BREND;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_PRODUCT_TYPE;
import static by.koroza.zoo_market.web.command.name.input.InputName.INPUT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PRODUCTS_FEED_AND_OTHER_PAGE_PATH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.ProductFeedsAndOtherService;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.factory.MarketFilterProductFactory;
import by.koroza.zoo_market.service.factory.impl.MarketFilterProductFactoryImpl;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.sorting.product.SortingProducts;
import by.koroza.zoo_market.service.sorting.product.impl.SortingProductsImpl;
import by.koroza.zoo_market.service.sorting.product.impl.comparator.list.product.impl.id.SortProductsByIdAscendingComparatorImpl;
import by.koroza.zoo_market.service.validation.FilterValidation;
import by.koroza.zoo_market.service.validation.impl.filter.FilterValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class ShowProductFeedsAndOtherIncludedFilterCommand.
 */
public class ShowProductFeedsAndOtherIncludedFilterCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The feeds and other service. */
	private final ProductFeedsAndOtherService FEEDS_AND_OTHER_SERVICE = ProductFeedsAndOtherServiceImpl.getInstance();

	/** The filter factory. */
	private final MarketFilterProductFactory FILTER_FACTORY = MarketFilterProductFactoryImpl.getInstance();

	/** The sort products. */
	private final SortingProducts SORT_PRODUCTS = SortingProductsImpl.getInstance();

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
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER);
		try {
			Map<String, String> mapInputExceptions = new HashMap<>();
			String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
			FilterFeedsAndOther filterFeedsAndOther = getInputParameters(request, sessionLocale, mapInputExceptions);
			if (mapInputExceptions.isEmpty()) {
				List<Entry<FeedAndOther, Long>> products = FEEDS_AND_OTHER_SERVICE
						.getProductsFeedAndOtherByFilter(filterFeedsAndOther);
				products = SORT_PRODUCTS.sortProductsFeedsAndOther(products,
						new SortProductsByIdAscendingComparatorImpl());
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, products);
				if (session.getAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP) == null) {
					Map<String, Set<String>> filterMap = FILTER_FACTORY.createFilterFeedAndOther(
							FEEDS_AND_OTHER_SERVICE.getAllProductsFeedAndOtherAndNumberOfUnits().keySet(),
							sessionLocale);
					session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP, filterMap);
				}
				session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER, filterFeedsAndOther);
			} else {
				session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
						mapInputExceptions);
				Map<FeedAndOther, Long> allProductsFeedAndOther = FEEDS_AND_OTHER_SERVICE
						.getAllProductsFeedAndOtherAndNumberOfUnits();
				List<Entry<FeedAndOther, Long>> products = SORT_PRODUCTS.sortProductsFeedsAndOther(
						allProductsFeedAndOther.entrySet().stream().toList(),
						new SortProductsByIdAscendingComparatorImpl());
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, products);
				Map<String, Set<String>> filterMap = FILTER_FACTORY
						.createFilterFeedAndOther(allProductsFeedAndOther.keySet(), sessionLocale);
				session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP, filterMap);
			}
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		return new Router(PRODUCTS_FEED_AND_OTHER_PAGE_PATH);
	}

	/**
	 * Gets the input parameters.
	 *
	 * @param request            the request
	 * @param sessionLocale      the session locale
	 * @param mapInputExceptions the map input exceptions
	 * @return the input parameters
	 * @throws ServiceException the service exception
	 */
	private FilterFeedsAndOther getInputParameters(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) throws ServiceException {
		String[] choosedTypesProduct = getInputParameterTypesProduct(request);
		String[] choosedBrendsProduct = getInputParameterBrendsProduct(request);
		String[] choosedTypesPets = getInputParameterTypesPets(request);
		boolean onlyProductsWithDiscount = getInputParameterOnlyProductsWithDiscount(request);
		String[] minMaxDiscount = getInputParametersMinMaxDiscount(request, sessionLocale, mapInputExceptions);
		String minDiscount = minMaxDiscount[0];
		String maxDiscount = minMaxDiscount[1];
		String[] minMaxPrice = getInputParametersMinMaxPrice(request, sessionLocale, mapInputExceptions);
		String minPrice = minMaxPrice[0];
		String maxPrice = minMaxPrice[1];
		return mapInputExceptions.isEmpty() ? new FilterFeedsAndOther.FilterFeedsAndOtherBuilder()
				.setChoosedTypesPets(choosedTypesPets).setChoosedProductBrend(choosedBrendsProduct)
				.setChoosedTypesProduct(choosedTypesProduct).setOnlyProductsWithDiscont(onlyProductsWithDiscount)
				.setMinDiscont(minDiscount != null && !minDiscount.isBlank() ? Double.parseDouble(minDiscount)
						: FilterFeedsAndOther.MIN_DISCOUNT)
				.setMaxDiscont(maxDiscount != null && !maxDiscount.isBlank() ? Double.parseDouble(maxDiscount)
						: FilterFeedsAndOther.MAX_DISCOUNT)
				.setMinPrice(minPrice != null && !minPrice.isBlank() ? Double.parseDouble(minPrice)
						: FilterFeedsAndOther.MIN_PRICE)
				.setMaxPriceAllProducts(findMaxPriceInListProducts())
				.setMaxPriceEntered(maxPrice != null && !maxPrice.isBlank() ? Double.parseDouble(maxPrice)
						: findMaxPriceInListProducts())
				.build() : null;
	}

	/**
	 * Get the input parameter types product.
	 *
	 * @param request the request
	 * @return the input parameter types product
	 */
	private String[] getInputParameterTypesProduct(HttpServletRequest request) {
		String[] choosedTypesProduct = request.getParameterValues(INPUT_PRODUCT_TYPE);
		return choosedTypesProduct;
	}

	/**
	 * Get the input parameter brends product.
	 *
	 * @param request the request
	 * @return the input parameter brends product
	 */
	private String[] getInputParameterBrendsProduct(HttpServletRequest request) {
		String[] choosedBrendsProduct = request.getParameterValues(INPUT_PRODUCT_BREND);
		return choosedBrendsProduct;
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
		FilterValidation validator = FilterValidationImpl.getInstance();
		String minDiscount = request.getParameter(INPUT_MIN_PROCENT_PROMOTIONS);
		String maxDiscount = request.getParameter(INPUT_MAX_PROCENT_PROMOTIONS);
		if (minDiscount != null && maxDiscount != null) {
			if (!validator.validInputValuesNumbersDiscount(minDiscount, maxDiscount)) {
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
				if (!validator.validInputValueNumberDiscount(minDiscount)) {
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
				if (!validator.validInputValueNumberDiscount(maxDiscount)) {
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
		FilterValidation validator = FilterValidationImpl.getInstance();
		String minPrice = request.getParameter(INPUT_MIN_PRICE_PET);
		String maxPrice = request.getParameter(INPUT_MAX_PRICE_PET);
		if (minPrice != null && maxPrice != null) {
			if (!validator.validInputValuesNumbersPrice(minPrice, maxPrice)) {
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
				if (!validator.validInputValueNumberPrice(minPrice)) {
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
				if (!validator.validInputValueNumberPrice(maxPrice)) {
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
	 * Find max price in list products.
	 *
	 * @return the double
	 * @throws ServiceException the service exception
	 */
	private double findMaxPriceInListProducts() throws ServiceException {
		double maxPrice = 0;
		Map<FeedAndOther, Long> products = FEEDS_AND_OTHER_SERVICE.getAllProductsFeedAndOtherAndNumberOfUnits();
		for (Map.Entry<FeedAndOther, Long> entry : products.entrySet()) {
			FeedAndOther product = entry.getKey();
			Long numberOfUnits = entry.getValue();
			if (numberOfUnits > 0 && product.getPrice() > maxPrice) {
				maxPrice = product.getPrice();
			}
		}
		return maxPrice;
	}
}