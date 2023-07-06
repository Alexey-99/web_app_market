package by.koroza.zoo_market.service.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PetValidation {

	private static final String PATTERN_FOR_BIRTH_DATE = "\\d{4}-\\d{2}-\\d{2}";
	private static final String PATTERN_FORMATTER_DATE_BIRTH_DATE = "yyyy-MM-dd";
	private static final String PATTERN_FOR_PRICE_AND_DISCOUNT = "^(\\d+)(\\.\\d{1,2})?$";
	private static final String PATTERN_FOR_NUMBER_OF_UNITS_PRODUCT = "^(\\d+)$";

	public static boolean validPetSpecie(String specie) {
		return isNotNull(specie) ? isNotEmpty(specie) : false;
	}

	public static boolean validPetBreed(String breed) {
		return isNotNull(breed) ? isNotEmpty(breed) : false;
	}

	public static boolean validPetBirthDate(String birthDate) {
		return isNotNull(birthDate)
				? (isNotEmpty(birthDate)
						? (matches(birthDate, PATTERN_FOR_BIRTH_DATE)
								? (isValidFormatDate(birthDate) ? isBeforeDateThenNow(birthDate) : false)
								: false)
						: false)
				: false;
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

	private static boolean isValidFormatDate(String line) {
		boolean result;
		try {
			result = LocalDate.parse(line.trim(),
					DateTimeFormatter.ofPattern(PATTERN_FORMATTER_DATE_BIRTH_DATE)) != null;
		} catch (DateTimeParseException | IllegalArgumentException e) {
			result = false;
		}
		return result;
	}

	private static boolean isBeforeDateThenNow(String line) {
		String[] arrayYearMonthDare = line.trim().split("-");
		LocalDate localDate = LocalDate.of(Integer.parseInt(arrayYearMonthDare[0]),
				Integer.parseInt(arrayYearMonthDare[1]), Integer.parseInt(arrayYearMonthDare[2]));
		LocalDate localDateNow = LocalDate.now();
		return localDate.isBefore(localDateNow);
	}
}