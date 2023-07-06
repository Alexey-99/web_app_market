package by.koroza.zoo_market.web.command.impl.user.show.market.other;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
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
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_RU;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BRAND_PRODUCT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BRAND_PRODUCT_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PET_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PET_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PRODUCT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PRODUCT_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_VALUE_DISCOUNT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_VALUE_DISCOUNT_RUS;
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
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PRODUCTS_FEED_AND_OTHER_PAGE_PATH;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.validation.impl.FilterValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowProductFeedsAndOtherIncludedFilterCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		List<FeedAndOther> productsByFilter = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER);
		try {
			Map<String, String> mapInputExceptions = new HashMap<>();
			String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
			FilterFeedsAndOther filterFeedsAndOther = getInputParameters(request, sessionLocale, mapInputExceptions);
			if (mapInputExceptions.isEmpty()) {
				productsByFilter = ProductFeedsAndOtherServiceImpl.getInstance()
						.getProductsFeedAndOtherByFilter(filterFeedsAndOther);
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, productsByFilter);
				if (session.getAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP) == null) {
					List<FeedAndOther> allProductsFeedAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
							.getAllProductsFeedsAndOther();
					Map<String, Set<String>> filterMap = createFilter(allProductsFeedAndOther, sessionLocale);
					session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP, filterMap);
				}
				session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER, filterFeedsAndOther);
			} else {
				session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
						mapInputExceptions);
				List<FeedAndOther> allProductsFeedAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
						.getAllProductsFeedsAndOther();
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, allProductsFeedAndOther);
				if (session.getAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP) == null) {
					Map<String, Set<String>> filterMap = createFilter(allProductsFeedAndOther, sessionLocale);
					session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP, filterMap);
				}
			}

		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return new Router(PRODUCTS_FEED_AND_OTHER_PAGE_PATH);
	}

	private FilterFeedsAndOther getInputParameters(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
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
		return mapInputExceptions.isEmpty()
				? new FilterFeedsAndOther.FilterFeedsAndOtherBuilder().setChoosedTypesPets(choosedTypesPets)
						.setChoosedProductBrend(choosedBrendsProduct).setChoosedTypesProduct(choosedTypesProduct)
						.setOnlyProductsWithDiscont(onlyProductsWithDiscount)
						.setMinDiscont(!minDiscount.isBlank() ? Double.parseDouble(minDiscount) : 0)
						.setMaxDiscont(!maxDiscount.isBlank() ? Double.parseDouble(maxDiscount) : 0)
						.setMinPrice(!minPrice.isBlank() ? Double.parseDouble(minPrice) : 0)
						.setMaxPrice(!maxPrice.isBlank() ? Double.parseDouble(maxPrice) : 0).build()
				: null;
	}

	private String[] getInputParameterTypesProduct(HttpServletRequest request) {
		String[] choosedTypesProduct = request.getParameterValues(INPUT_PRODUCT_TYPE);
		return choosedTypesProduct;
	}

	private String[] getInputParameterBrendsProduct(HttpServletRequest request) {
		String[] choosedBrendsProduct = request.getParameterValues(INPUT_PRODUCT_BREND);
		return choosedBrendsProduct;
	}

	private String[] getInputParameterTypesPets(HttpServletRequest request) {
		String[] choosedTypesPets = request.getParameterValues(INPUT_PET_TYPE);
		return choosedTypesPets;
	}

	private boolean getInputParameterOnlyProductsWithDiscount(HttpServletRequest request) {
		boolean onlyProductsWithDiscount = request.getParameterValues(INPUT_PROMOTIONS) != null;
		return onlyProductsWithDiscount;
	}

	private String[] getInputParametersMinMaxDiscount(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String minDiscount = request.getParameter(INPUT_MIN_PROCENT_PROMOTIONS);
		String maxDiscount = request.getParameter(INPUT_MAX_PROCENT_PROMOTIONS);
		if (minDiscount != null && maxDiscount != null) {
			if (!FilterValidation.validInputValuesNumbersDiscount(minDiscount, maxDiscount)) {
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
				}
			}
		} else {
			if (minDiscount != null) {
				if (!FilterValidation.validInputValuesNumberDiscount(minDiscount)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN + minDiscount);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN + minDiscount);
					}
				}
			} else if (maxDiscount != null) {
				if (!FilterValidation.validInputValuesNumberDiscount(maxDiscount)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX + maxDiscount);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_DISCOUNT,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX + maxDiscount);
					}
				}
			}
		}
		return new String[] { minDiscount, maxDiscount };
	}

	private String[] getInputParametersMinMaxPrice(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String minPrice = request.getParameter(INPUT_MIN_PRICE_PET);
		String maxPrice = request.getParameter(INPUT_MAX_PRICE_PET);
		if (minPrice != null && maxPrice != null) {
			if (!FilterValidation.validInputValuesNumbersPrice(minPrice, maxPrice)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
							RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE + minPrice
									+ RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO + maxPrice);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
							EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE + minPrice
									+ EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO + maxPrice);
				}
			}
		} else {
			if (minPrice != null) {
				if (!FilterValidation.validInputValuesNumberPrice(minPrice)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN + minPrice);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN + minPrice);
					}
				}
			} else if (maxPrice != null) {
				if (!FilterValidation.validInputValuesNumberPrice(maxPrice)) {
					if (sessionLocale.equals(RUSSIAN)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX + maxPrice);
					} else if (sessionLocale.equals(ENGLISH)) {
						mapInputExceptions.put(TYPE_INPUT_EXCEPTION_PRICE,
								EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX + maxPrice);
					}
				}
			}
		}
		return new String[] { minPrice, maxPrice };
	}

	private Map<String, Set<String>> createFilter(List<FeedAndOther> products, String sessionLocale) {
		Map<String, Set<String>> filterMap = new HashMap<>();
		if (sessionLocale.equals(RUSSIAN)) {
			Set<String> typesProductsSet = createFilterByTypeProduct(products);
			if (typesProductsSet.size() > 0) {
				filterMap.put(CHOOSE_TYPE_PRODUCT_RUS, typesProductsSet);
			}
			Set<String> brandsProductsSet = createFilterByBrandProducts(products);
			if (brandsProductsSet.size() > 0) {
				filterMap.put(CHOOSE_BRAND_PRODUCT_RUS, brandsProductsSet);
			}
			if (isHavingDiscountProducts(products)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_RUS, createFilterByDiscountProducts(products, sessionLocale));
			}
			Set<String> typesPetsSet = createFilterByTypePet(products);
			if (typesPetsSet.size() > 0) {
				filterMap.put(CHOOSE_TYPE_PET_RUS, typesPetsSet);
			}
		} else if (sessionLocale.equals(ENGLISH)) {
			Set<String> typesProductsSet = createFilterByTypeProduct(products);
			if (typesProductsSet.size() > 0) {
				filterMap.put(CHOOSE_TYPE_PRODUCT_EN, typesProductsSet);
			}
			Set<String> brandsProductsSet = createFilterByBrandProducts(products);
			if (brandsProductsSet.size() > 0) {
				filterMap.put(CHOOSE_BRAND_PRODUCT_EN, brandsProductsSet);
			}
			if (isHavingDiscountProducts(products)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_EN, createFilterByDiscountProducts(products, sessionLocale));
			}
			Set<String> typesPetsSet = createFilterByTypePet(products);
			if (typesPetsSet.size() > 0) {
				filterMap.put(CHOOSE_TYPE_PET_EN, typesPetsSet);
			}
		}
		return filterMap;
	}

	private Set<String> createFilterByTypeProduct(List<FeedAndOther> products) {
		Set<String> typesProductsSet = new HashSet<>();
		products.forEach(product -> typesProductsSet.add(product.getProductType()));
		return typesProductsSet;
	}

	private Set<String> createFilterByBrandProducts(List<FeedAndOther> products) {
		Set<String> brandsProductsSet = new HashSet<>();
		products.forEach(product -> brandsProductsSet.add(product.getBrand()));
		return brandsProductsSet;
	}

	private Set<String> createFilterByDiscountProducts(List<FeedAndOther> products, String sessionLocale) {
		Set<String> promotionsPetsSet = null;
		if (isHavingDiscountProducts(products)) {
			promotionsPetsSet = new HashSet<>();
			if (sessionLocale.equals(RUSSIAN)) {
				promotionsPetsSet.add(CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_RU);
			} else if (sessionLocale.equals(ENGLISH)) {
				promotionsPetsSet.add(CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_EN);
			}
		}
		return promotionsPetsSet;
	}

	private boolean isHavingDiscountProducts(List<FeedAndOther> products) {
		boolean isHavingDiscount = false;
		for (FeedAndOther items : products) {
			if (items.getDiscount() > 0) {
				isHavingDiscount = true;
			}
		}
		return isHavingDiscount;
	}

	private Set<String> createFilterByTypePet(List<FeedAndOther> products) {
		Set<String> typesPetsSet = new HashSet<>();
		products.forEach(product -> product.getPetTypes().stream().forEach(type -> typesPetsSet.add(type)));
		return typesPetsSet;
	}
}