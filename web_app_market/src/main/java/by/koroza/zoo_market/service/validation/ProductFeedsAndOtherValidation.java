package by.koroza.zoo_market.service.validation;

/**
 * The Interface ProductFeedsAndOtherValidation.
 */
public interface ProductFeedsAndOtherValidation extends AbstractProductValidation {

	/**
	 * Validation product type.
	 *
	 * @param productType the product type
	 * @return true, if successful
	 */
	public boolean validProductType(String productType);

	/**
	 * Validation product brand.
	 *
	 * @param brand the brand
	 * @return true, if successful
	 */
	public boolean validBrand(String brand);

	/**
	 * Validation product description.
	 *
	 * @param description the description
	 * @return true, if successful
	 */
	public boolean validDescription(String description);

	/**
	 * Validation pet types.
	 *
	 * @param petTypes the pet types
	 * @return true, if successful
	 */
	public boolean validPetTypes(String petTypes);
}