package by.koroza.zoo_market.service.validation.impl.product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import by.koroza.zoo_market.service.validation.ProductPetValidation;
import by.koroza.zoo_market.service.validation.impl.product.abstraction.AbstractProductValidationImpl;

/**
 * The Class ProductPetValidationImpl.
 */
public final class ProductPetValidationImpl extends AbstractProductValidationImpl implements ProductPetValidation {

	/** The Constant INSTANCE. */
	private static final ProductPetValidation INSTANCE = new ProductPetValidationImpl();

	/** The Constant PATTERN_FOR_BIRTH_DATE. */
	private static final String PATTERN_FOR_BIRTH_DATE = "\\d{4}-\\d{2}-\\d{2}";

	/** The Constant PATTERN_FORMATTER_DATE_BIRTH_DATE. */
	private static final String PATTERN_FORMATTER_DATE_BIRTH_DATE = "yyyy-MM-dd";

	/**
	 * Instantiates a new product pet validation impl.
	 */
	private ProductPetValidationImpl() {
	}

	/**
	 * Gets the single instance of ProductPetValidationImpl.
	 *
	 * @return single instance of ProductPetValidationImpl
	 */
	public static ProductPetValidation getInstance() {
		return INSTANCE;
	}

	/**
	 * Validation pet species.
	 *
	 * @param specie the species
	 * @return true, if successful
	 */
	@Override
	public boolean validPetSpecie(String specie) {
		return specie != null && !specie.isBlank();
	}

	/**
	 * Validation pet breed.
	 *
	 * @param breed the breed
	 * @return true, if successful
	 */
	@Override
	public boolean validPetBreed(String breed) {
		return breed != null && !breed.isBlank();
	}

	/**
	 * Validation pet birth date.
	 *
	 * @param birthDate the birth date
	 * @return true, if successful
	 */
	@Override
	public boolean validPetBirthDate(String birthDate) {
		return birthDate != null && !birthDate.isBlank() && birthDate.trim().matches(PATTERN_FOR_BIRTH_DATE)
				&& isValidFormatDate(birthDate) && isBeforeDateThenNow(birthDate);
	}

	/**
	 * Check if is valid format date.
	 *
	 * @param line the line
	 * @return true, if is valid format date
	 */
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

	/**
	 * Check if is before date then now.
	 *
	 * @param line the line
	 * @return true, if is before date then now
	 */
	private static boolean isBeforeDateThenNow(String line) {
		String[] arrayYearMonthDare = line.trim().split("-");
		LocalDate localDate = LocalDate.of(Integer.parseInt(arrayYearMonthDare[0]),
				Integer.parseInt(arrayYearMonthDare[1]), Integer.parseInt(arrayYearMonthDare[2]));
		LocalDate localDateNow = LocalDate.now();
		return localDate.isBefore(localDateNow);
	}
}