package by.koroza.zoo_market.service.validation.impl.bank;

import java.time.LocalDate;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.ValidationException;
import by.koroza.zoo_market.service.impl.bank.BankCardServiceImpl;
import by.koroza.zoo_market.service.validation.BankCardValidation;

public final class BankCardValidationImpl implements BankCardValidation {
	private static final BankCardValidation INSTANCE = new BankCardValidationImpl();
	private static final String REG_EX_NUMBER_BANK_CARD = "\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}";
	private static final String REG_EX_DIGITS = "\\d+";

	private static final int FIRST_NUMBER_MONTH = 1;
	private static final int LAST_NUMBER_MONTH = 12;

	private BankCardValidationImpl() {
	}

	public static BankCardValidation getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean validNumberBankCard(String number) {
		return number != null ? (!number.isBlank() ? number.trim().matches(REG_EX_NUMBER_BANK_CARD) : false) : false;
	}

	@Override
	public boolean validMonthAndYear(String numberMonth, String numberYear) {
		return numberMonth != null && numberYear != null
				? (!numberMonth.isBlank() && !numberYear.isBlank()
						? (numberMonth.trim().matches(REG_EX_DIGITS) && numberYear.trim().matches(REG_EX_DIGITS)
								? (isCorrectNumberMonth(numberMonth) ? validTermWorkCard(numberMonth, numberYear)
										: false)
								: false)
						: false)
				: false;
	}

	@Override
	public boolean validCVC(String cvc) {
		return cvc != null ? (!cvc.isBlank() ? cvc.trim().matches(REG_EX_DIGITS) : false) : false;
	}

	@Override
	public boolean isExistsBankCard(BankCard card) throws ValidationException {
		try {
			return BankCardServiceImpl.getInstance().isHaveBankCard(card);
		} catch (ServiceException e) {
			throw new ValidationException(e);
		}
	}

	@Override
	public boolean validSum(BankCard card, double price) throws ValidationException {
		try {
			return BankCardServiceImpl.getInstance().isHaveSumOnBankCard(card, price);
		} catch (ServiceException e) {
			throw new ValidationException(e);
		}
	}

	@Override
	public boolean validMonth(String numberMonth) {
		return numberMonth != null ? (!numberMonth.isBlank()
				? (numberMonth.trim().matches(REG_EX_DIGITS) ? isCorrectNumberMonth(numberMonth) : false)
				: false) : false;
	}

	@Override
	public boolean validYear(String numberYear) {
		return numberYear != null ? (!numberYear.isBlank() ? numberYear.trim().matches(REG_EX_DIGITS) : false) : false;
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