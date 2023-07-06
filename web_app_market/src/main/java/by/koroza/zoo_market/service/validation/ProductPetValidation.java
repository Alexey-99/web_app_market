package by.koroza.zoo_market.service.validation;

public interface ProductPetValidation extends AbstractProductValidation {

	public boolean validPetSpecie(String specie);

	public boolean validPetBreed(String breed);

	public boolean validPetBirthDate(String birthDate);
}