package by.koroza.zoo_market.web.command.name.exception;

public final class MessageInputException {
	/* payment form = OrderPaymentCommand.class */
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD = "Вы ввели номер банковской карты не корректно. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD = "You entered bank card number incorrect. You entered: ";
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_ONE = "Вы ввели номер мсяц или год не корректно. Ваш ввод: месяц = ";
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_TWO = ", год = ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_ONE = "You entered month or year incorrect. You entered: month = ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_TWO = ", year = ";
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_CVC = "Вы ввели CVC не корректно. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_CVC = "You enterd CVC incorrect. You entered: ";
	public static final String RU_MESSAGE_TYPY_EXCEPTION_BANK_CARD = "Данной банковской карты не существует";
	public static final String EN_MESSAGE_TYPY_EXCEPTION_BANK_CARD = "This bank card doesn't exist";
	public static final String RU_MESSAGE_TYPY_EXCEPTION_SUM = "Не достаточно средств на карте";
	public static final String EN_MESSAGE_TYPY_EXCEPTION_SUM = "Not enough funds on the card";

	private MessageInputException() {
	}
}