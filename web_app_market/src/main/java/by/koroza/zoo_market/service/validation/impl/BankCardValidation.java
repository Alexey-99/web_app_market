package by.koroza.zoo_market.service.validation.impl;

import java.time.LocalDate;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.bank.BankCardServiceImpl;

public class BankCardValidation {
	private static final String REG_EX_NUMBER_BANK_CARD = "\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}";
	private static final String REG_EX_DIGITS = "\\d+";

	private static final int FIRST_NUMBER_MONTH = 1;
	private static final int LAST_NUMBER_MONTH = 12;

	public static boolean validNumberBankCard(String number) {
		return isNotNull(number) ? (isNotEmptyLine(number) ? compareToPattern(number, REG_EX_NUMBER_BANK_CARD) : false)
				: false;
	}

	public static boolean validMonthAndYear(String numberMonth, String numberYear) { // isHaveCardInDB
		return isNotNull(numberMonth) && isNotNull(numberYear)
				? (isNotEmptyLine(numberMonth) && isNotEmptyLine(numberYear)
						? (compareToPattern(numberMonth, REG_EX_DIGITS) && compareToPattern(numberYear, REG_EX_DIGITS)
								? (isCorrectNumberMonth(numberMonth) ? validTermWorkCard(numberMonth, numberYear)
										: false)
								: false)
						: false)
				: false;
	}

	public static boolean validCVC(String cvc) {
		return isNotNull(cvc) ? (isNotEmptyLine(cvc) ? compareToPattern(cvc, REG_EX_DIGITS) : false) : false;
	}

	public static boolean isExistsBankCard(BankCard card) throws ServiceException {
		return BankCardServiceImpl.getInstance().isHaveBankCard(card);
	}

	public static boolean validSum(BankCard card, double price) throws ServiceException {
		return BankCardServiceImpl.getInstance().isHaveSumOnBankCard(card, price);
	}

	public static boolean validMonth(String numberMonth) {
		return isNotNull(numberMonth) ? (isNotEmptyLine(numberMonth)
				? (compareToPattern(numberMonth, REG_EX_DIGITS) ? isCorrectNumberMonth(numberMonth) : false)
				: false) : false;
	}

	public static boolean validYear(String numberYear) {
		return isNotNull(numberYear)
				? (isNotEmptyLine(numberYear) ? compareToPattern(numberYear, REG_EX_DIGITS) : false)
				: false;
	}

	private static boolean isNotNull(String number) {
		return number != null;
	}

	private static boolean isNotEmptyLine(String number) {
		return !number.isBlank();
	}

	private static boolean compareToPattern(String number, String pattern) {
		return number.trim().matches(pattern);
	}

	private static boolean isCorrectNumberMonth(String number) {
		int numberMonth = Integer.parseInt(number.trim());
		return numberMonth >= FIRST_NUMBER_MONTH && numberMonth <= LAST_NUMBER_MONTH;
	}

	private static boolean validTermWorkCard(String numberMonth, String numberYear) {
		LocalDate dateNow = LocalDate.now();
		int month = Integer.parseInt(numberMonth.trim());
		int year = Integer.parseInt(numberYear.trim()) + 2000;
		LocalDate result = dateNow.minusYears(year).minusMonths(month);
		return result.getYear() < 0;
	}
}