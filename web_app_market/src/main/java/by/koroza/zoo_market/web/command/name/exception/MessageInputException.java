package by.koroza.zoo_market.web.command.name.exception;

public final class MessageInputException {
//	- payment form = OrderPaymentCommand.class 
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

//	- change password form = ChangePasswordCommand.class 
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD = "Вы ввели пароль не корректно. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_PASSWORD = "You entered password incorrect. Your entered: ";

//	- change login form = ChangeLoginCommand.class 
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT = "Вы ввели login не корректно. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_INCORRECT = "You entered login incorrect. Your entered: ";
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS = "Логин уже существует. Введите другой логин. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_LOGIN_EXISTS = "This login exists. Enter another login. You entered: ";

//	- change user status (role) = ChangeUserStatusCommand.class
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_USER_WITH_LOGIN_NOT_EXISTS = "Пользователь не найден с логином ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_USER_WITH_LOGIN_NOT_EXISTS = "User didn't find with login ";
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_USER_ROLE = "Выбран не корректный статус";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_USER_ROLE = "You selected incorrect status";

//	- change email form = ChangeEmailCommand.class
//	- change personal information(name, surname, email) form = ChangePersonInformationCommand.class
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL = "Вы ввели e-mail не корректно. Ваш ввод: ";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_EMAIL = "You entered email incorrect. Your input: ";

//	- change personal information(name, surname, email) form = ChangePersonInformationCommand.class
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_NAME = "";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_NAME = "";
	public static final String RU_MESSAGE_TYPY_INPUT_EXCEPTION_SURNAME = "";
	public static final String EN_MESSAGE_TYPY_INPUT_EXCEPTION_SURNAME = "";

//	MARKET PAGES
//	- market products page include filter = ShowProductPetsIncludedFilterCommand.class
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_ONE = "Вы ввели не корректно год или месяц: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_ONE = "You input values year or month incorrect: ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_TWO = "минимальный возрост = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_TWO = "min age = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE = " лет ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE = " years ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FOUR = " месяцев; максимальный возрост = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FOUR = " months; max age = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FIVE = RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE;
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_FIVE = EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_THREE;
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_SIX = " месяцев";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_YEAR_MONTH_PART_SIX = " months";

//	- market products page include filter = ShowProductPetsIncludedFilterCommand.class
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_ONE = "Вы ввели не корректно величину скидки. Вы ввели: минимальная скидка = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_ONE = "You input discount values incorrect: min discount = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_TWO = ", максимальная скидка = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN_OR_MAX_PART_TWO = ", max discount = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN = "Вы ввели не корректно минимальную величину скидки. Вы ввели: минимальная скидка = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MIN = "You input discount min value incorrect: min discount = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX = "Вы ввели не корректно максимальную величину скидки. Вы ввели: максимальная скидка = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FILTER_MAX = "You input discount min value incorrect: max discount = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE = "Вы ввели не корректно цену. Вы ввели: минимальная цена = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_ONE = "You input price values incorrect: min price = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO = ", максимальная цена = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN_OR_MAX_PART_TWO = ", max price = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN = "Вы ввели не корректно минимальную цену. Вы ввели: минимальная цена = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MIN = "You input price max value incorrect: min price = ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX = "Вы ввели не корректно максимальную цену. Вы ввели: максимальная цена = ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FILTER_MAX = "You input price max value incorrect: max price = ";

//	- create product pet form = CraetePetProductCommand.class
//	- change product pet form = ChangeProductPetCommand.class
//	- create product feeds and other form = CraeteProductFeedsAndOtherCommand.class
//	- change product feeds and other form = ChangeProductFeedsAndOtherCommand.class
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE = "Вы выбрали не корретный файл с картинкой для товара";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_IMAGE = "You choosed image incorrect for product";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM = "Вы ввели не корректно цену для товара. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRICE_FORM = "You input product price incorrect. Your input: ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM = "Вы ввели не корректно скидку на товар. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_DISCOUNT_FORM = "You input product discount incorrect. Your input: ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT = "Вы ввели не корректно количество данного товара. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT = "You input number of units product incorrect. Your input: ";

//	- create product pet form = CraetePetProductCommand.class
//	- change product pet form = ChangeProductPetCommand.class
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_SPECIE = "Вы ввели не корректно тип питомца. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_SPECIE = "You input pet specie incorrect. Your input: ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_BREED = "Вы ввели не корректно породу питомца. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_BREED = "You input pet breed incorrect. Your input: ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_BIRTH_DATE = "Вы ввели не корректно дату рождения питомца. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_BIRTH_DATE = "You input pet birth date incorrect. Your input: ";

//	- create product feeds and other form = CraeteProductFeedsAndOtherCommand.class
//	- change product feeds and other form = ChangeProductFeedsAndOtherCommand.class
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_PRODUCT_TYPE = "Вы ввели не корректно тип товара. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_PRODUCT_TYPE = "You input product type incorrect. Your input: ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_BRAND = "Вы ввели не корректно брэнд товара. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_BRAND = "You input brand of product incorrect. Your input: ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_DESCRIPTION = "Вы ввели не корректно описание товара. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_DESCRIPTION = "You input description of product incorrect. Your input: ";
	public static final String RU_MESSAGE_TYPE_INPUT_EXCEPTION_PET_TYPES = "Вы ввели не корректно типы (тип) питомцев для кого данный товар. Вы ввели: ";
	public static final String EN_MESSAGE_TYPE_INPUT_EXCEPTION_PET_TYPES = "You entered, type (types) of pets for whom this product, incorrect. Your input: ";

	private MessageInputException() {
	}
}