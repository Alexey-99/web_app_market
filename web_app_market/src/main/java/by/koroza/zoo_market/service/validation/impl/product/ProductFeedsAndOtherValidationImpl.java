package by.koroza.zoo_market.service.validation.impl.product;

import by.koroza.zoo_market.service.validation.ProductFeedsAndOtherValidation;
import by.koroza.zoo_market.service.validation.impl.product.abstraction.AbstractProductValidationImpl;

/**
 * The Class ProductFeedsAndOtherValidationImpl.
 */
public final class ProductFeedsAndOtherValidationImpl extends AbstractProductValidationImpl
		implements ProductFeedsAndOtherValidation {

	/** The Constant INSTANCE. */
	private static final ProductFeedsAndOtherValidation INSTANCE = new ProductFeedsAndOtherValidationImpl();

	/**
	 * Instantiates a new product feeds and other validation impl.
	 */
	private ProductFeedsAndOtherValidationImpl() {
	}

	/**
	 * Get the single instance of ProductFeedsAndOtherValidationImpl.
	 *
	 * @return single instance of ProductFeedsAndOtherValidationImpl
	 */
	public static ProductFeedsAndOtherValidation getInstance() {
		return INSTANCE;
	}

	/**
	 * Valid product type.
	 *
	 * @param productType the product type
	 * @return true, if successful
	 */
	@Override
	public boolean validProductType(String productType) {
		return productType != null && !productType.isBlank();
	}

	/**
	 * Valid brand.
	 *
	 * @param brand the brand
	 * @return true, if successful
	 */
	@Override
	public boolean validBrand(String brand) {
		return brand != null && !brand.isBlank();
	}

	/**
	 * Valid description.
	 *
	 * @param description the description
	 * @return true, if successful
	 */
	@Override
	public boolean validDescription(String description) {
		return description != null && !description.isBlank();
	}

	/**
	 * Valid pet types.
	 *
	 * @param petType the pet type
	 * @return true, if successful
	 */
	@Override
	public boolean validPetTypes(String petType) {
		return petType != null && !petType.isBlank();
	}
}