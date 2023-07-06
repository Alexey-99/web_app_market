package by.koroza.zoo_market.service.validation;

public interface AbstractProductValidation {

	public boolean validProductPrice(String price);

	public boolean validProductDiscount(String discount);

	public boolean validNumberOfUnitsProduct(String number);
}