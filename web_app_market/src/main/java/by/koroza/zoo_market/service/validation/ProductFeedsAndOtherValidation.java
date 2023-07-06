package by.koroza.zoo_market.service.validation;

public interface ProductFeedsAndOtherValidation extends AbstractProductValidation {

	public boolean validProductType(String productType);

	public boolean validBrand(String brand);

	public boolean validDescrription(String description);

	public boolean validPetTypes(String petTypes);
}