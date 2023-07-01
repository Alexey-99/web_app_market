package by.koroza.zoo_market.web.command.name.exception;

public final class TypeInputExeception {
//	- payment form = OrderPaymentCommand.class
	public static final String TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD = "NUMBER_BANK_CARD";
	public static final String TYPY_INPUT_EXCEPTION_MONTH_YEAR = "MONTH_YEAR";
	public static final String TYPY_INPUT_EXCEPTION_CVC = "CVC";
	public static final String TYPY_EXCEPTION_BANK_CARD = "BANK_CARD";
	public static final String TYPY_EXCEPTION_SUM = "SUM";

//	 - change password form = ChangePasswordCommand.class
	public static final String TYPY_INPUT_EXCEPTION_PASSWORD = "PASSWORD";

//	 - change login form = ChangeLoginCommand.class
	public static final String TYPY_INPUT_EXCEPTION_LOGIN = "LOGIN";

//	 - change email form = ChangeEmailCommand.class
//	 - change personal information(name, surname, email) form = ChangePersonInformationCommand.class
	public static final String TYPY_INPUT_EXCEPTION_EMAIL = "EMAIL";

//	 - change personal information(name, surname, email) form = ChangePersonInformationCommand.class
	public static final String TYPY_INPUT_EXCEPTION_NAME = "NAME";
	public static final String TYPY_INPUT_EXCEPTION_SURNAME = "SURNAME";

	private TypeInputExeception() {
	}
}