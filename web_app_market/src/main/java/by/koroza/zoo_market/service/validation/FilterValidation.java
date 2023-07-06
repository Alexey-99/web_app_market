package by.koroza.zoo_market.service.validation;

/**
 * The Interface FilterValidation.
 */
public interface FilterValidation {

	/**
	 * Validation input values numbers discount.
	 *
	 * @param min the min
	 * @param max the max
	 * @return true, if successful
	 */
	public boolean validInputValuesNumbersDiscount(String min, String max);

	/**
	 * Validation input value number discount.
	 *
	 * @param discount the discount
	 * @return true, if successful
	 */
	public boolean validInputValueNumberDiscount(String discount);

	/**
	 * Validation input values numbers price.
	 *
	 * @param min the min
	 * @param max the max
	 * @return true, if successful
	 */
	public boolean validInputValuesNumbersPrice(String min, String max);

	/**
	 * Validation input value number price.
	 *
	 * @param price the price
	 * @return true, if successful
	 */
	public boolean validInputValueNumberPrice(String price);

	/**
	 * Validation input values numbers year month.
	 *
	 * @param minYear  the min year
	 * @param maxYear  the max year
	 * @param minMonth the min month
	 * @param maxMonth the max month
	 * @return true, if successful
	 */
	public boolean validInputValuesNumbersYearMonth(String minYear, String maxYear, String minMonth, String maxMonth);
}