package by.koroza.zoo_market.validation;

public class FeedsAndOtherValidation {
	private static final String PATTERN_FOR_PRICE_AND_DISCOUNT = "^(\\d+)(\\.\\d{1,2})?$";
	private static final String PATTERN_FOR_NUMBER_OF_UNITS_PRODUCT = "^(\\d+)$";

	public static boolean validProductType(String productType) {
		return isNotNull(productType) ? isNotEmpty(productType) : false;
	}

	public static boolean validBrand(String brand) {
		return isNotNull(brand) ? isNotEmpty(brand) : false;
	}

	public static boolean validDescrription(String description) {
		return isNotNull(description) ? isNotEmpty(description) : false;
	}

	public static boolean validPetType(String petType) {
		return isNotNull(petType) ? isNotEmpty(petType) : false;
	}

	public static boolean validPetPrice(String price) {
		return isNotNull(price) ? (isNotEmpty(price) ? matches(price, PATTERN_FOR_PRICE_AND_DISCOUNT) : false) : false;
	}

	public static boolean validPetDiscount(String discount) {
		return isNotNull(discount) ? (isNotEmpty(discount) ? matches(discount, PATTERN_FOR_PRICE_AND_DISCOUNT) : false)
				: false;
	}

	public static boolean validPetNumberOfUnitsProduct(String number) {
		return isNotNull(number) ? (isNotEmpty(number) ? matches(number, PATTERN_FOR_NUMBER_OF_UNITS_PRODUCT) : false)
				: false;
	}

	private static boolean isNotNull(String line) {
		return line != null;
	}

	private static boolean isNotEmpty(String line) {
		return !line.isBlank();
	}

	private static boolean matches(String line, String pattern) {
		return line.trim().matches(pattern);
	}
}