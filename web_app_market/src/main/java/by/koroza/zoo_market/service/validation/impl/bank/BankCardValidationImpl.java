package by.koroza.zoo_market.service.validation.impl.bank;

import java.time.LocalDate;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.ValidationException;
import by.koroza.zoo_market.service.impl.bank.BankCardServiceImpl;
import by.koroza.zoo_market.service.validation.BankCardValidation;

/**
 * The Class BankCardValidationImpl.
 */
public final class BankCardValidationImpl implements BankCardValidation {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final BankCardValidation INSTANCE = new BankCardValidationImpl();

	/** The Constant REG_EX_NUMBER_BANK_CARD. */
	private static final String REG_EX_NUMBER_BANK_CARD = "\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}";

	/** The Constant REG_EX_DIGITS. */
	private static final String REG_EX_DIGITS = "\\d+";

	/** The Constant FIRST_NUMBER_MONTH. */
	private static final int FIRST_NUMBER_MONTH = 1;

	/** The Constant LAST_NUMBER_MONTH. */
	private static final int LAST_NUMBER_MONTH = 12;

	/**
	 * Instantiates a new bank card validation impl.
	 */
	private BankCardValidationImpl() {
	}

	/**
	 * Get the single instance of BankCardValidationImpl.
	 *
	 * @return single instance of BankCardValidationImpl
	 */
	public static BankCardValidation getInstance() {
		return INSTANCE;
	}

	/**
	 * Validation number bank card.
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	@Override
	public boolean validNumberBankCard(String number) {
		return number != null ? (!number.isBlank() ? number.trim().matches(REG_EX_NUMBER_BANK_CARD) : false) : false;
	}

	/**
	 * Validation month and year.
	 *
	 * @param numberMonth the number month
	 * @param numberYear  the number year
	 * @return true, if successful
	 */
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

	/**
	 * Validation CVC.
	 *
	 * @param cvc the cvc
	 * @return true, if successful
	 */
	@Override
	public boolean validCVC(String cvc) {
		return cvc != null ? (!cvc.isBlank() ? cvc.trim().matches(REG_EX_DIGITS) : false) : false;
	}

	/**
	 * Check if is exists bank card.
	 *
	 * @param card the card
	 * @return true, if is exists bank card
	 * @throws ValidationException the validation exception
	 */
	@Override
	public boolean isExistsBankCard(BankCard card) throws ValidationException {
		try {
			return BankCardServiceImpl.getInstance().isHaveBankCard(card);
		} catch (ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ValidationException(e);
		}
	}

	/**
	 * Validation sum.
	 *
	 * @param card  the card
	 * @param price the price
	 * @return true, if successful
	 * @throws ValidationException the validation exception
	 */
	@Override
	public boolean validSum(BankCard card, double price) throws ValidationException {
		try {
			return BankCardServiceImpl.getInstance().isHaveSumOnBankCard(card, price);
		} catch (ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new ValidationException(e);
		}
	}

	/**
	 * Validation month.
	 *
	 * @param numberMonth the number month
	 * @return true, if successful
	 */
	@Override
	public boolean validMonth(String numberMonth) {
		return numberMonth != null ? (!numberMonth.isBlank()
				? (numberMonth.trim().matches(REG_EX_DIGITS) ? isCorrectNumberMonth(numberMonth) : false)
				: false) : false;
	}

	/**
	 * Validation year.
	 *
	 * @param numberYear the number year
	 * @return true, if successful
	 */
	@Override
	public boolean validYear(String numberYear) {
		return numberYear != null ? (!numberYear.isBlank() ? numberYear.trim().matches(REG_EX_DIGITS) : false) : false;
	}

	/**
	 * Check if is correct number month.
	 *
	 * @param number the number
	 * @return true, if is correct number month
	 */
	private static boolean isCorrectNumberMonth(String number) {
		int numberMonth = Integer.parseInt(number.trim());
		return numberMonth >= FIRST_NUMBER_MONTH && numberMonth <= LAST_NUMBER_MONTH;
	}

	/**
	 * Validation term work card.
	 *
	 * @param numberMonth the number month
	 * @param numberYear  the number year
	 * @return true, if successful
	 */
	private static boolean validTermWorkCard(String numberMonth, String numberYear) {
		LocalDate dateNow = LocalDate.now();
		int month = Integer.parseInt(numberMonth.trim());
		int year = Integer.parseInt(numberYear.trim()) + 2000;
		LocalDate result = dateNow.minusYears(year).minusMonths(month);
		return result.getYear() < 0;
	}
}