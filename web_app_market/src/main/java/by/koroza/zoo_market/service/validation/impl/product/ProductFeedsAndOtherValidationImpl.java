package by.koroza.zoo_market.service.validation.impl.product;

import by.koroza.zoo_market.service.validation.ProductFeedsAndOtherValidation;
import by.koroza.zoo_market.service.validation.impl.product.abstraction.AbstractProductValidationImpl;

public final class ProductFeedsAndOtherValidationImpl extends AbstractProductValidationImpl
		implements ProductFeedsAndOtherValidation {
	private static final ProductFeedsAndOtherValidation INSTANCE = new ProductFeedsAndOtherValidationImpl();

	private ProductFeedsAndOtherValidationImpl() {
	}

	public static ProductFeedsAndOtherValidation getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean validProductType(String productType) {
		return productType != null && !productType.isBlank();
	}

	@Override
	public boolean validBrand(String brand) {
		return brand != null && !brand.isBlank();
	}

	@Override
	public boolean validDescrription(String description) {
		return description != null && !description.isBlank();
	}

	@Override
	public boolean validPetTypes(String petType) {
		return petType != null && !petType.isBlank();
	}
}