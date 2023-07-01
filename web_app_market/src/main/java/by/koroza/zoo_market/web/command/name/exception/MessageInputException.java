package by.koroza.zoo_market.web.command.name.exception;

public final class MessageInputException {
//	 - payment form = OrderPaymentCommand.class 
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

//	- change password from = ChangePasswordCommand.class 
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD = "Вы ввели пароль не корректно. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD = "You entered password incorrect. Your entered: ";

//	- change login from = ChangeLoginCommand.class 
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT = "Вы ввели login не корректно. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT = "You entered login incorrect. Your entered: ";
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS = "Логин уже существует. Введите другой логин. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS = "This login exists. Enter another login. You entered: ";

//	- change email form = ChangeEmailCommand.class
//	- change personal information(name, surname, email) form = ChangePersonInformationCommand.class
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL = "Вы ввели e-mail не корректно. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL = "You entered email incorrect. Your input: ";

//	 - change personal information(name, surname, email) form = ChangePersonInformationCommand.class
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_NAME = "";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_NAME = "";
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_SURNAME = "";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_SURNAME = "";

	private MessageInputException() {
	}
}