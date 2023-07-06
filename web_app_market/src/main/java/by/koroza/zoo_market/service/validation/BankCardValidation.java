package by.koroza.zoo_market.service.validation;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.exception.ValidationException;

public interface BankCardValidation {

	public boolean validNumberBankCard(String number);

	public boolean validMonthAndYear(String numberMonth, String numberYear);

	public boolean validCVC(String cvc);

	public boolean isExistsBankCard(BankCard card) throws ValidationException;

	public boolean validSum(BankCard card, double price) throws ValidationException;

	public boolean validMonth(String numberMonth);

	public boolean validYear(String numberYear);
}