package by.koroza.zoo_market.validation;

public class FilterValidation {
	private static final String REG_EX_INPUT_VALUE_DOUBLE = "(\\d+(\\.\\d+)?)";
	private static final String REG_EX_INPUT_VALUE_INT = "\\d+";

	private static final int MAX_VALUE_DISCOUNT = 100;
	private static final String MIN_VALUE_ZERO = "0";
	private static final int MAX_NUMBER_MONTH = 12;
	private static final int MIN_NUMBER_MONTH = 0;

	public static boolean validInputValuesNumbersDiscount(String min, String max) {
		return validMaxMinNotNull(min, max)
				? ((isDigitsNumberDouble(min) && isDigitsNumberDouble(max)) ? (validMaxMoreMinValue(min, max)
						? (validCorrectMaxValueDiscount(max) && validCorrectMaxValueDiscount(min) ? true : false)
						: false) : false)
				: false;
	}

	public static boolean validInputValuesNumberDiscount(String discount) {
		return discount != null ? (isDigitsNumberDouble(discount) ? validCorrectMaxValueDiscount(discount) : false)
				: false;
	}

	public static boolean validInputValuesNumbersPrice(String min, String max) {
		return validMaxMinNotNull(min, max) ? ((isDigitsNumberDouble(min) && isDigitsNumberDouble(max))
				? (validMaxMoreMinValue(min, max) ? true : false)
				: false) : false;
	}

	public static boolean validInputValuesNumberPrice(String price) {
		return price != null ? isDigitsNumberDouble(price) : false;
	}

	public static boolean validInputValuesNumbersYearMonth(String minYear, String maxYear, String minMonth,
			String maxMonth) {
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

	private static boolean validInputValuesNumbersYear(String min, String max) {
		return validMaxMinNotNull(min, max) ? ((isDigitsNumberInt(min) && isDigitsNumberInt(max))
				? (validMaxMoreMinValue(min, max)) ? true : max.equals(MIN_VALUE_ZERO)
				: false) : false;
	}

	private static boolean validInputValuesNumbersMonth(String min, String max) {
		return validMaxMinNotNull(min, max) ? ((isDigitsNumberInt(min) && isDigitsNumberInt(max))
				? (validCorrectNumberMonth(min) && validCorrectNumberMonth(max) ? true : false)
				: false) : false;
	}

	private static boolean validMaxMinNotNull(String min, String max) {
		return min != null && max != null;
	}

	private static boolean isDigitsNumberDouble(String number) {
		return number.matches(REG_EX_INPUT_VALUE_DOUBLE) | number.isBlank();
	}

	private static boolean isDigitsNumberInt(String number) {
		return number.matches(REG_EX_INPUT_VALUE_INT) | number.isBlank();
	}

	private static boolean validMaxMoreMinValue(String min, String max) {
		return min.isBlank() || max.isBlank() ? true
				: Double.parseDouble(!min.isBlank() ? min : MIN_VALUE_ZERO) <= Double
						.parseDouble(!max.isBlank() ? max : MIN_VALUE_ZERO);
	}

	private static boolean validCorrectMaxValueDiscount(String value) {
		return Double.parseDouble(!value.isBlank() ? value : MIN_VALUE_ZERO) <= MAX_VALUE_DISCOUNT;
	}

	private static boolean validCorrectNumberMonth(String numberMonth) {
		int numberMonthInt = !numberMonth.isBlank() ? Integer.parseInt(numberMonth) : 0;
		return numberMonthInt >= MIN_NUMBER_MONTH && numberMonthInt <= MAX_NUMBER_MONTH;
	}
}