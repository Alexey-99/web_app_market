package by.koroza.zoo_market.service.validation.impl.product.abstraction;

import by.koroza.zoo_market.service.validation.ProductValidation;

/**
 * The Class AbstractProductValidationImpl.
 */
public abstract class AbstractProductValidationImpl implements ProductValidation {

	/** The Constant PATTERN_FOR_PRICE_AND_DISCOUNT. */
	private static final String PATTERN_FOR_PRICE_AND_DISCOUNT = "^(\\d+)(\\.\\d{1,2})?$";

	/** The Constant PATTERN_FOR_NUMBER_OF_UNITS_PRODUCT. */
	private static final String PATTERN_FOR_NUMBER_OF_UNITS_PRODUCT = "^(\\d+)$";

	/**
	 * Validation product price.
	 *
	 * @param price the price
	 * @return true, if successful
	 */
	@Override
	public boolean validProductPrice(String price) {
		return price != null && !price.isBlank() && price.trim().matches(PATTERN_FOR_PRICE_AND_DISCOUNT);
	}

	/**
	 * Validation product discount.
	 *
	 * @param discount the discount
	 * @return true, if successful
	 */
	@Override
	public boolean validProductDiscount(String discount) {
		return discount != null && !discount.isBlank() && discount.trim().matches(PATTERN_FOR_PRICE_AND_DISCOUNT);
	}

	/**
	 * Validation number of units product.
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	@Override
	public boolean validNumberOfUnitsProduct(String number) {
		return number != null && !number.isBlank() && number.trim().matches(PATTERN_FOR_NUMBER_OF_UNITS_PRODUCT);
	}
}