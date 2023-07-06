package by.koroza.zoo_market.service.validation.impl.product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import by.koroza.zoo_market.service.validation.ProductPetValidation;
import by.koroza.zoo_market.service.validation.impl.product.abstraction.AbstractProductValidationImpl;

public final class ProductPetValidationImpl extends AbstractProductValidationImpl implements ProductPetValidation {
	private static final ProductPetValidation INSTANCE = new ProductPetValidationImpl();

	private static final String PATTERN_FOR_BIRTH_DATE = "\\d{4}-\\d{2}-\\d{2}";
	private static final String PATTERN_FORMATTER_DATE_BIRTH_DATE = "yyyy-MM-dd";

	private ProductPetValidationImpl() {
	}

	public static ProductPetValidation getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean validPetSpecie(String specie) {
		return specie != null && !specie.isBlank();
	}

	@Override
	public boolean validPetBreed(String breed) {
		return breed != null && !breed.isBlank();
	}

	@Override
	public boolean validPetBirthDate(String birthDate) {
		return birthDate != null && !birthDate.isBlank() && birthDate.trim().matches(PATTERN_FOR_BIRTH_DATE)
				&& isValidFormatDate(birthDate) && isBeforeDateThenNow(birthDate);
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