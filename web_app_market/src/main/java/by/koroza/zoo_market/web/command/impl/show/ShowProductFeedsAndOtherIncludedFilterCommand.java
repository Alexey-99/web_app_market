package by.koroza.zoo_market.web.command.impl.show;

import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_BREND_PRODUCT;
import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_TYPE_PET;
import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_TYPE_PRODUCT;
import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_VALUE_DISCOUNT;

import static by.koroza.zoo_market.web.command.name.InputName.INPUT_MAX_PRICE_PET;
import static by.koroza.zoo_market.web.command.name.InputName.INPUT_MAX_PROCENT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.InputName.INPUT_MIN_PRICE_PET;
import static by.koroza.zoo_market.web.command.name.InputName.INPUT_MIN_PROCENT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.InputName.INPUT_PET_TYPE;
import static by.koroza.zoo_market.web.command.name.InputName.INPUT_PROMOTIONS;
import static by.koroza.zoo_market.web.command.name.InputName.INPUT_PRODUCT_TYPE;
import static by.koroza.zoo_market.web.command.name.InputName.INPUT_PRODUCT_BREND;

import static by.koroza.zoo_market.web.command.name.PagePathName.PRODUCTS_FEED_AND_OTHER_PAGE_PATH;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.filter.FilterFeedsAndOther;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.validation.FilterValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.command.exception.InputException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowProductFeedsAndOtherIncludedFilterCommand implements Command {
	public static final String INPUT_EXCEPTION_TYPE_DESCOUNT = TypeException.DISCOUNT.toString();
	public static final String INPUT_EXCEPTION_TYPE_PRICE = TypeException.PRICE.toString();

	public enum TypeException {
		DISCOUNT, PRICE
	}

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		List<FeedAndOther> productsByFilter = null;
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			FilterFeedsAndOther filterFeedsAndOther = null;
			try {
				filterFeedsAndOther = getParametersFromFilter(request);
				productsByFilter = ProductFeedsAndOtherServiceImpl.getInstance()
						.getProductsFeedAndOtherByFilter(filterFeedsAndOther);
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, productsByFilter);
				if (session.getAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER) == null) {
					List<FeedAndOther> allProductsFeedAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
							.getAllProductsFeedsAndOther();
					Map<String, Set<String>> filterMap = createFilter(allProductsFeedAndOther);
					session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER, filterMap);
				}
				router = new Router(PRODUCTS_FEED_AND_OTHER_PAGE_PATH);
			} catch (InputException e) {
				List<FeedAndOther> allProductsFeedAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
						.getAllProductsFeedsAndOther();
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, allProductsFeedAndOther);
				if (session.getAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER) == null) {
					Map<String, Set<String>> filterMap = createFilter(allProductsFeedAndOther);
					session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER, filterMap);
				}
				router = new Router(PRODUCTS_FEED_AND_OTHER_PAGE_PATH);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		this.isRegistratedUser(request);
		return router;
	}

	private FilterFeedsAndOther getParametersFromFilter(HttpServletRequest request)
			throws CommandException, InputException {
		FilterFeedsAndOther filter = null;
		Map<String, String> mapTypeAndMessageException = new HashMap<>();

		String[] choosedTypesProduct = request.getParameterValues(INPUT_PRODUCT_TYPE);
		String[] choosedBrendsProduct = request.getParameterValues(INPUT_PRODUCT_BREND);
		String[] choosedTypesPets = request.getParameterValues(INPUT_PET_TYPE);

		boolean onlyPetsWithDiscont = (request.getParameterValues(INPUT_PROMOTIONS) != null ? true : false);

		String minDiscont = request.getParameter(INPUT_MIN_PROCENT_PROMOTIONS) != null
				? request.getParameter(INPUT_MIN_PROCENT_PROMOTIONS)
				: "";
		String maxDiscont = request.getParameter(INPUT_MAX_PROCENT_PROMOTIONS) != null
				? request.getParameter(INPUT_MAX_PROCENT_PROMOTIONS)
				: "";
		if (!FilterValidation.validInputValuesNumbersDiscount(minDiscont, maxDiscont)) {
			mapTypeAndMessageException.put(INPUT_EXCEPTION_TYPE_DESCOUNT,
					"You input discount values incorrect: minDiscont = " + minDiscont + ", maxDiscont = " + maxDiscont);
		}
		String minPrice = request.getParameter(INPUT_MIN_PRICE_PET);
		String maxPrice = request.getParameter(INPUT_MAX_PRICE_PET);
		if (!FilterValidation.validInputValuesNumbersPrice(minPrice, maxPrice)) {
			mapTypeAndMessageException.put(INPUT_EXCEPTION_TYPE_PRICE,
					"You input price values incorrect: min price = " + minPrice + ", max price = " + maxPrice);
		}
		if (mapTypeAndMessageException.size() > 0) {
			request.getSession().setAttribute(
					ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
					mapTypeAndMessageException);
			throw new InputException(mapTypeAndMessageException.toString());
		} else {
			filter = new FilterFeedsAndOther.FilterFeedsAndOtherBuilder().setChoosedTypesPets(choosedTypesPets)
					.setChoosedProductBrend(choosedBrendsProduct).setChoosedTypesProduct(choosedTypesProduct)
					.setOnlyProductsWithDiscont(onlyPetsWithDiscont)
					.setMinDiscont(!minDiscont.isBlank() ? Double.parseDouble(minDiscont) : 0)
					.setMaxDiscont(!maxDiscont.isBlank() ? Double.parseDouble(maxDiscont) : 0)
					.setMinPrice(!minPrice.isBlank() ? Double.parseDouble(minPrice) : 0)
					.setMaxPrice(!maxPrice.isBlank() ? Double.parseDouble(maxPrice) : 0).build();
		}
		return filter;
	}

	private Map<String, Set<String>> createFilter(List<FeedAndOther> products) {
		Map<String, Set<String>> filterMap = new HashMap<>();
		Set<String> typesProductsSet = createFilterByTypeProduct(products);
		if (typesProductsSet.size() > 0) {
			filterMap.put(CHOOSE_TYPE_PRODUCT, typesProductsSet);
		}
		Set<String> brandsProductsSet = createFilterByBrandProducts(products);
		if (brandsProductsSet.size() > 0) {
			filterMap.put(CHOOSE_BREND_PRODUCT, brandsProductsSet);
		}
		if (isHavingDiscountProducts(products)) {
			filterMap.put(CHOOSE_VALUE_DISCOUNT, createFilterByDiscountProducts(products));
		}
		Set<String> typesPetsSet = createFilterByTypePet(products);
		if (typesPetsSet.size() > 0) {
			filterMap.put(CHOOSE_TYPE_PET, typesPetsSet);
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

	private Set<String> createFilterByDiscountProducts(List<FeedAndOther> products) {
		Set<String> promotionsPetsSet = null;
		if (isHavingDiscountProducts(products)) {
			promotionsPetsSet = new HashSet<>();
			promotionsPetsSet.add("только акционные товары");
		}
		return promotionsPetsSet;
	}

	private boolean isHavingDiscountProducts(List<FeedAndOther> products) {
		boolean ishavingDiscont = false;
		for (FeedAndOther items : products) {
			if (items.getDiscount() > 0) {
				ishavingDiscont = true;
			}
		}
		return ishavingDiscont;
	}

	private Set<String> createFilterByTypePet(List<FeedAndOther> products) {
		Set<String> typesPetsSet = new HashSet<>();
		products.forEach(product -> product.getPetTypes().stream().forEach(type -> typesPetsSet.add(type)));
		return typesPetsSet;
	}
}