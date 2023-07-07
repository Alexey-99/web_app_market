package by.koroza.zoo_market.service.validation.impl.filter;

import by.koroza.zoo_market.service.validation.FilterValidation;

/**
 * The Class FilterValidationImpl.
 */
public final class FilterValidationImpl implements FilterValidation {

	/** The Constant INSTANCE. */
	private static final FilterValidation INSTANCE = new FilterValidationImpl();

	/** The Constant REG_EX_INPUT_VALUE_DOUBLE. */
	private static final String REG_EX_INPUT_VALUE_DOUBLE = "(\\d+(\\.\\d+)?)";

	/** The Constant REG_EX_INPUT_VALUE_INT. */
	private static final String REG_EX_INPUT_VALUE_INT = "\\d+";

	/** The Constant MAX_VALUE_DISCOUNT. */
	private static final int MAX_VALUE_DISCOUNT = 100;

	/** The Constant MIN_VALUE_ZERO. */
	private static final String MIN_VALUE_ZERO = "0";

	/** The Constant MAX_NUMBER_MONTH. */
	private static final int MAX_NUMBER_MONTH = 12;

	/** The Constant MIN_NUMBER_MONTH. */
	private static final int MIN_NUMBER_MONTH = 0;

	/**
	 * Instantiates a new filter validation impl.
	 */
	private FilterValidationImpl() {
	}

	/**
	 * Get the single instance of FilterValidationImpl.
	 *
	 * @return single instance of FilterValidationImpl
	 */
	public static FilterValidation getInstance() {
		return INSTANCE;
	}

	/**
	 * Validation input values numbers discount.
	 *
	 * @param min the min
	 * @param max the max
	 * @return true, if successful
	 */
	@Override
	public boolean validInputValuesNumbersDiscount(String min, String max) {
		return (min != null && max != null)
				&& ((!min.isBlank() ? min.trim().matches(REG_EX_INPUT_VALUE_DOUBLE) : true)
						&& (!max.isBlank() ? max.trim().matches(REG_EX_INPUT_VALUE_DOUBLE) : true))
				&& (validMaxMoreMinValue(min, max)
						&& (validCorrectMaxValueDiscount(max) && validCorrectMaxValueDiscount(min)));
	}

	/**
	 * Validation input value number discount.
	 *
	 * @param discount the discount
	 * @return true, if successful
	 */
	@Override
	public boolean validInputValueNumberDiscount(String discount) {
		return (discount != null) && (!discount.isBlank() ? discount.trim().matches(REG_EX_INPUT_VALUE_DOUBLE) : true)
				&& (validCorrectMaxValueDiscount(discount));
	}

	/**
	 * Validation input values numbers price.
	 *
	 * @param min the min
	 * @param max the max
	 * @return true, if successful
	 */
	@Override
	public boolean validInputValuesNumbersPrice(String min, String max) {
		return (min != null && max != null) && ((min.isBlank() ? true : min.trim().matches(REG_EX_INPUT_VALUE_DOUBLE))
				&& (max.isBlank() ? true : max.trim().matches(REG_EX_INPUT_VALUE_DOUBLE))
				&& (validMaxMoreMinValue(min, max)));
	}

	/**
	 * Validation input value number price.
	 *
	 * @param price the price
	 * @return true, if successful
	 */
	@Override
	public boolean validInputValueNumberPrice(String price) {
		return price != null ? !price.isBlank() ? price.trim().matches(REG_EX_INPUT_VALUE_DOUBLE) : true : false;
	}

	/**
	 * Validation input values numbers year month.
	 *
	 * @param minYear  the min year
	 * @param maxYear  the max year
	 * @param minMonth the min month
	 * @param maxMonth the max month
	 * @return true, if successful
	 */
	@Override
	public boolean validInputValuesNumbersYearMonth(String minYear, String maxYear, String minMonth, String maxMonth) {
		String minYearCor = minYear != null && !minYear.isBlank() ? minYear : MIN_VALUE_ZERO;
		String minMonthCor = minMonth != null && !minMonth.isBlank()
				? (minMonth.length() < 2 ? MIN_VALUE_ZERO + minMonth : minMonth)
				: MIN_VALUE_ZERO;
		String maxYearCor = maxYear != null && !maxYear.isBlank() ? maxYear : MIN_VALUE_ZERO;
		String maxMonthCor = maxMonth != null && !maxMonth.isBlank()
				? (maxMonth.length() < 2 ? MIN_VALUE_ZERO + maxMonth : maxMonth)
				: MIN_VALUE_ZERO;
		String minAge = minYearCor.concat(".").concat(minMonthCor);
		String maxAge = maxYearCor.concat(".").concat(maxMonthCor);
		return validInputValuesNumbersYear(minYearCor, maxYearCor)
				&& validInputValuesNumbersMonth(minMonthCor, maxMonthCor)
						? Double.parseDouble(minAge) - Double.parseDouble(maxAge) >= 0
						: false;
	}

	/**
	 * Validation input values numbers year.
	 *
	 * @param min the min
	 * @param max the max
	 * @return true, if successful
	 */
	private boolean validInputValuesNumbersYear(String min, String max) {
		return (min != null && max != null)
				&& ((!min.isBlank() ? min.trim().matches(REG_EX_INPUT_VALUE_INT) : true)
						&& (!max.isBlank() ? max.trim().matches(REG_EX_INPUT_VALUE_INT) : true))
				&& (validMaxMoreMinValue(min, max) ? true : max.trim().equals(MIN_VALUE_ZERO));
	}

	/**
	 * Validation input values numbers month.
	 *
	 * @param min the min
	 * @param max the max
	 * @return true, if successful
	 */
	private boolean validInputValuesNumbersMonth(String min, String max) {
		return (min != null && max != null)
				&& ((!min.isBlank() ? min.trim().matches(REG_EX_INPUT_VALUE_INT) : true)
						&& (!max.isBlank() ? max.trim().matches(REG_EX_INPUT_VALUE_INT) : true))
				&& (validCorrectNumberMonth(min) && validCorrectNumberMonth(max));
	}

	/**
	 * Validation max more min value.
	 *
	 * @param min the min
	 * @param max the max
	 * @return true, if successful
	 */
	private boolean validMaxMoreMinValue(String min, String max) {
		return min.isBlank() || max.isBlank() ? true
				: Double.parseDouble(!min.isBlank() ? min : MIN_VALUE_ZERO) <= Double
						.parseDouble(!max.isBlank() ? max : MIN_VALUE_ZERO);
	}

	/**
	 * Validation correct max value discount.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	private boolean validCorrectMaxValueDiscount(String value) {
		return Double.parseDouble(!value.isBlank() ? value : MIN_VALUE_ZERO) <= MAX_VALUE_DISCOUNT;
	}

	/**
	 * Validation correct number month.
	 *
	 * @param numberMonth the number month
	 * @return true, if successful
	 */
	private boolean validCorrectNumberMonth(String numberMonth) {
		int numberMonthInt = !numberMonth.isBlank() ? Integer.parseInt(numberMonth) : 0;
		return numberMonthInt >= MIN_NUMBER_MONTH && numberMonthInt <= MAX_NUMBER_MONTH;
	}
}