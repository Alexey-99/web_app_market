package by.koroza.zoo_market.service.validation;

public interface FilterValidation {

	public boolean validInputValuesNumbersDiscount(String min, String max);

	public boolean validInputValueNumberDiscount(String discount);

	public boolean validInputValuesNumbersPrice(String min, String max);

	public boolean validInputValueNumberPrice(String price);

	public boolean validInputValuesNumbersYearMonth(String minYear, String maxYear, String minMonth, String maxMonth);
}