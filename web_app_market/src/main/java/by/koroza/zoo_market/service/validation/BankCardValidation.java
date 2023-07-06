package by.koroza.zoo_market.service.validation;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.exception.ValidationException;

/**
 * The Interface BankCardValidation.
 */
public interface BankCardValidation {

	/**
	 * Validation number bank card.
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	public boolean validNumberBankCard(String number);

	/**
	 * Validation month and year end work card.
	 *
	 * @param numberMonth the number month
	 * @param numberYear  the number year
	 * @return true, if successful
	 */
	public boolean validMonthAndYear(String numberMonth, String numberYear);

	/**
	 * Validation CVC.
	 *
	 * @param cvc the cvc
	 * @return true, if successful
	 */
	public boolean validCVC(String cvc);

	/**
	 * Checks if is exists bank card.
	 *
	 * @param card the card
	 * @return true, if is exists bank card
	 * @throws ValidationException the validation exception
	 */
	public boolean isExistsBankCard(BankCard card) throws ValidationException;

	/**
	 * Validation sum.
	 *
	 * @param card  the card
	 * @param price the price
	 * @return true, if successful
	 * @throws ValidationException the validation exception
	 */
	public boolean validSum(BankCard card, double price) throws ValidationException;

	/**
	 * Validation month.
	 *
	 * @param numberMonth the number month
	 * @return true, if successful
	 */
	public boolean validMonth(String numberMonth);

	/**
	 * Validation year.
	 *
	 * @param numberYear the number year
	 * @return true, if successful
	 */
	public boolean validYear(String numberYear);
}