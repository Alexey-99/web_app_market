package by.koroza.zoo_market.service.validation;

/**
 * The Interface ProductPetValidation.
 */
public interface ProductPetValidation extends AbstractProductValidation {

	/**
	 * Validation pet species.
	 *
	 * @param specie the species
	 * @return true, if successful
	 */
	public boolean validPetSpecie(String specie);

	/**
	 * Validation pet breed.
	 *
	 * @param breed the breed
	 * @return true, if successful
	 */
	public boolean validPetBreed(String breed);

	/**
	 * Validation pet birth date.
	 *
	 * @param birthDate the birth date
	 * @return true, if successful
	 */
	public boolean validPetBirthDate(String birthDate);
}