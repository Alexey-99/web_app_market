package by.koroza.zoo_market.web.command.impl.show;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controler.Router;
import by.koroza.zoo_market.web.command.exception.CommandException;

import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_TYPE_PRODUCT;
import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_BREND_PRODUCT;
import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_TYPE_PET;
import static by.koroza.zoo_market.web.command.name.FilterName.CHOOSE_VALUE_DISCOUNT;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER;

import static by.koroza.zoo_market.web.command.name.PagePathName.PRODUCTS_FEED_AND_OTHER_PAGE_PATH;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowProductFeedsAndOtherOffFilterCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			List<FeedAndOther> productsFeedAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
					.getAllProductsFeedsAndOther();
			session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER, productsFeedAndOther);
			Map<String, Set<String>> filter = createFilter(productsFeedAndOther);
			session.setAttribute(ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER, filter);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		this.isRegistratedUser(request);
		return new Router(PRODUCTS_FEED_AND_OTHER_PAGE_PATH);
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