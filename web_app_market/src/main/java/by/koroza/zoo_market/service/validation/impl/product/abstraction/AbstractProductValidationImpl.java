package by.koroza.zoo_market.service.validation.impl.product.abstraction;

import by.koroza.zoo_market.service.validation.AbstractProductValidation;

public abstract class AbstractProductValidationImpl implements AbstractProductValidation {
	private static final String PATTERN_FOR_PRICE_AND_DISCOUNT = "^(\\d+)(\\.\\d{1,2})?$";
	private static final String PATTERN_FOR_NUMBER_OF_UNITS_PRODUCT = "^(\\d+)$";

	@Override
	public boolean validProductPrice(String price) {
		return price != null && !price.isBlank() && price.trim().matches(PATTERN_FOR_PRICE_AND_DISCOUNT);
	}

	@Override
	public boolean validProductDiscount(String discount) {
		return discount != null && !discount.isBlank() && discount.trim().matches(PATTERN_FOR_PRICE_AND_DISCOUNT);
	}

	@Override
	public boolean validNumberOfUnitsProduct(String number) {
		return number != null && !number.isBlank() && number.trim().matches(PATTERN_FOR_NUMBER_OF_UNITS_PRODUCT);
	}
}