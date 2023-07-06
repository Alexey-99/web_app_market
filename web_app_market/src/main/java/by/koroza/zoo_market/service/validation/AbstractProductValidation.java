package by.koroza.zoo_market.service.validation;

/**
 * The Interface AbstractProductValidation.
 */
public interface AbstractProductValidation {

	/**
	 * Validation product price.
	 *
	 * @param price the price
	 * @return true, if successful
	 */
	public boolean validProductPrice(String price);

	/**
	 * Validation product discount.
	 *
	 * @param discount the discount
	 * @return true, if successful
	 */
	public boolean validProductDiscount(String discount);

	/**
	 * Validation number of units product.
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	public boolean validNumberOfUnitsProduct(String number);
}