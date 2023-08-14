package by.koroza.zoo_market.service.factory.impl;

import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_RU;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BRAND_PRODUCT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BRAND_PRODUCT_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BREED_PET_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_BREED_PET_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PET_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PET_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PRODUCT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_TYPE_PRODUCT_RUS;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_VALUE_DISCOUNT_EN;
import static by.koroza.zoo_market.web.command.name.filter.FilterName.CHOOSE_VALUE_DISCOUNT_RUS;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.factory.MarketFilterProductFactory;

/**
 * The Class MarketFilterProductFactoryImpl.
 */
public class MarketFilterProductFactoryImpl implements MarketFilterProductFactory {

	/** The Constant INSTANCE. */
	private static final MarketFilterProductFactory INSTANCE = new MarketFilterProductFactoryImpl();

	/**
	 * Get the single instance of MarketFilterProductFactoryImpl.
	 *
	 * @return single instance of MarketFilterProductFactoryImpl
	 */
	public static MarketFilterProductFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Instantiates a new market filter product factory impl.
	 */
	private MarketFilterProductFactoryImpl() {
	}

	/**
	 * Create the filter feed and other.
	 *
	 * @param products      the products
	 * @param sessionLocale the session locale
	 * @return the map
	 */
	@Override
	public Map<String, Set<String>> createFilterFeedAndOther(Set<FeedAndOther> products, String sessionLocale) {
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
		} else {
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

	/**
	 * Create the filter pets.
	 *
	 * @param petsList      the pets list
	 * @param sessionLocale the session locale
	 * @return the map
	 */
	@Override
	public Map<String, Set<String>> createFilterPets(Set<Pet> petsList, String sessionLocale) {
		Map<String, Set<String>> filterMap = new LinkedHashMap<>();
		if (sessionLocale.equals(RUSSIAN)) {
			filterMap.put(CHOOSE_TYPE_PET_RUS, createFilterBySpeciePets(petsList));
			filterMap.put(CHOOSE_BREED_PET_RUS, createFilterByBreedPets(petsList));
			if (isHavingDiscountProducts(petsList)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_RUS, createFilterByDiscountProducts(petsList, sessionLocale));
			}
		} else if (sessionLocale.equals(ENGLISH)) {
			filterMap.put(CHOOSE_TYPE_PET_EN, createFilterBySpeciePets(petsList));
			filterMap.put(CHOOSE_BREED_PET_EN, createFilterByBreedPets(petsList));
			if (isHavingDiscountProducts(petsList)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_EN, createFilterByDiscountProducts(petsList, sessionLocale));
			}
		} else {
			filterMap.put(CHOOSE_TYPE_PET_EN, createFilterBySpeciePets(petsList));
			filterMap.put(CHOOSE_BREED_PET_EN, createFilterByBreedPets(petsList));
			if (isHavingDiscountProducts(petsList)) {
				filterMap.put(CHOOSE_VALUE_DISCOUNT_EN, createFilterByDiscountProducts(petsList, sessionLocale));
			}
		}
		return filterMap;
	}

	/**
	 * Create the filter by type product.
	 *
	 * @param products the products
	 * @return the sets the
	 */
	private Set<String> createFilterByTypeProduct(Set<FeedAndOther> products) {
		Set<String> typesProductsSet = new HashSet<>();
		products.forEach(product -> typesProductsSet.add(product.getProductType()));
		return typesProductsSet;
	}

	/**
	 * Create the filter by brand products.
	 *
	 * @param products the products
	 * @return the sets the
	 */
	private Set<String> createFilterByBrandProducts(Set<FeedAndOther> products) {
		Set<String> brandsProductsSet = new HashSet<>();
		products.forEach(product -> brandsProductsSet.add(product.getBrand()));
		return brandsProductsSet;
	}

	/**
	 * Create the filter by discount products.
	 *
	 * @param products      the products
	 * @param sessionLocale the session locale
	 * @return the sets the
	 */
	private Set<String> createFilterByDiscountProducts(Set<? extends AbstractProduct> products, String sessionLocale) {
		Set<String> promotionsPetsSet = null;
		if (isHavingDiscountProducts(products)) {
			promotionsPetsSet = new HashSet<>();
			if (sessionLocale.equals(RUSSIAN)) {
				promotionsPetsSet.add(CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_RU);
			} else if (sessionLocale.equals(ENGLISH)) {
				promotionsPetsSet.add(CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_EN);
			} else {
				promotionsPetsSet.add(CHECK_BOX_CHOOSE_ONLY_PRODUCTS_WITH_DISCOUNT_EN);
			}
		}
		return promotionsPetsSet;
	}

	/**
	 * Check if is having discount products.
	 *
	 * @param products the products
	 * @return true, if is having discount products
	 */
	private boolean isHavingDiscountProducts(Set<? extends AbstractProduct> products) {
		boolean isHavingDiscount = false;
		for (AbstractProduct product : products) {
			if (product.getDiscount() > 0) {
				isHavingDiscount = true;
			}
		}
		return isHavingDiscount;
	}

	/**
	 * Create the filter by type pet.
	 *
	 * @param products the products
	 * @return the sets the
	 */
	private Set<String> createFilterByTypePet(Set<FeedAndOther> products) {
		Set<String> typesPetsSet = new HashSet<>();
		products.forEach(product -> product.getPetTypes().stream().forEach(type -> typesPetsSet.add(type)));
		return typesPetsSet;
	}

	/**
	 * Create the filter by species pets.
	 *
	 * @param petsList the pets list
	 * @return the sets the
	 */
	private Set<String> createFilterBySpeciePets(Set<Pet> petsList) {
		Set<String> speciesPetsSet = new HashSet<>();
		petsList.forEach(pet -> speciesPetsSet.add(pet.getSpecie()));
		return speciesPetsSet;
	}

	/**
	 * Create the filter by breed pets.
	 *
	 * @param petsList the pets list
	 * @return the sets the
	 */
	private Set<String> createFilterByBreedPets(Set<Pet> petsList) {
		Set<String> breedsPetsSet = new HashSet<>();
		petsList.forEach(pet -> breedsPetsSet.add(pet.getBreed()));
		return breedsPetsSet;
	}
}