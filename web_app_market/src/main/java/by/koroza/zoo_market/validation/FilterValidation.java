package by.koroza.zoo_market.validation;

public class FilterValidation {
	private static final String REG_EX_INPUT_VALUE_DOUBLE = "(\\d+(\\.\\d+)?)";
	private static final String REG_EX_INPUT_VALUE_INT = "\\d+";

	private static final int MAX_VALUE_DISCOUNT = 100;
	private static final int MAX_NUMBER_MONTH = 12;
	private static final int MIN_NUMBER_MONTH = 0;

	public static boolean validInputValuesNumbersDiscount(String min, String max) {
		return validMaxMinNotNull(min, max)
				? ((isDigitsNumberDouble(min) && isDigitsNumberDouble(max)) ? (validMaxMoreMinValue(min, max)
						? (validCorrectMaxValueDiscount(max) && validCorrectMaxValueDiscount(min) ? true : false)
						: false) : false)
				: false;
	}

	public static boolean validInputValuesNumbersPrice(String min, String max) {
		return validMaxMinNotNull(min, max) ? ((isDigitsNumberDouble(min) && isDigitsNumberDouble(max))
				? (validMaxMoreMinValue(min, max) ? true : false)
				: false) : false;
	}

	public static boolean validInputValuesNumbersYearMonth(String minYear, String maxYear, String minMonth,
			String maxMonth) {
		String min = new StringBuilder().append(!minYear.isBlank() ? minYear : "0").append(".")
				.append(!minMonth.isBlank() ? (minMonth.length() < 2 ? "0" + minMonth : minMonth) : "0").toString();
		String max = new StringBuilder().append(!maxYear.isBlank() ? maxYear : "0").append(".")
				.append(!maxMonth.isBlank() ? (maxMonth.length() < 2 ? "0" + maxMonth : maxMonth) : "0").toString();
		return validInputValuesNumbersYear(minYear, maxYear) && validInputValuesNumbersMonth(minMonth, maxMonth)
				? Double.parseDouble(max) - Double.parseDouble(min) >= 0
				: false;
	}

	private static boolean validInputValuesNumbersYear(String min, String max) {
		return validMaxMinNotNull(min, max)
				? ((isDigitsNumberInt(min) && isDigitsNumberInt(max)) ? (validMaxMoreMinValue(min, max) ? true : false)
						: false)
				: false;
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
				: Double.parseDouble(!min.isBlank() ? min : "0") <= Double.parseDouble(!max.isBlank() ? max : "0");
	}

	private static boolean validCorrectMaxValueDiscount(String value) {
		return Double.parseDouble(!value.isBlank() ? value : "0") <= MAX_VALUE_DISCOUNT;
	}

	private static boolean validCorrectNumberMonth(String numberMonth) {
		int numberMonthInt = !numberMonth.isBlank() ? Integer.parseInt(numberMonth) : 0;
		return numberMonthInt >= MIN_NUMBER_MONTH && numberMonthInt <= MAX_NUMBER_MONTH;
	}
}