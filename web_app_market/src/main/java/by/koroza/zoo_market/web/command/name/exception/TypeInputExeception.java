package by.koroza.zoo_market.web.command.name.exception;

import by.koroza.zoo_market.model.entity.status.UserRole;

public final class TypeInputExeception {

//	- payment form = OrderPaymentCommand.class
	public static final String TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD = "NUMBER_BANK_CARD";
	public static final String TYPY_INPUT_EXCEPTION_MONTH_YEAR = "MONTH_YEAR";
	public static final String TYPY_INPUT_EXCEPTION_CVC = "CVC";
	public static final String TYPY_EXCEPTION_BANK_CARD = "BANK_CARD";
	public static final String TYPY_EXCEPTION_SUM = "SUM";

//	- change password form = ChangePasswordCommand.class
//	- registration form (name, surname, email) form = RegistrationUserCommand.class
	public static final String TYPY_INPUT_EXCEPTION_PASSWORD = "PASSWORD";

//	- change login form = ChangeLoginCommand.class
//	- registration form (name, surname, email) form = RegistrationUserCommand.class
//	- change user status (role) = ChangeUserStatusCommand.class
	public static final String TYPY_INPUT_EXCEPTION_LOGIN = "LOGIN";

//	- change user status (role) = ChangeUserStatusCommand.class
	public static final String TYPY_INPUT_EXCEPTION_USER_ROLE = "USER_ROLE";

//	- change email form = ChangeEmailCommand.class
//	- change personal information form(name, surname, email) form = ChangePersonInformationCommand.class
//	- registration form (name, surname, email) form = RegistrationUserCommand.class	
	public static final String TYPY_INPUT_EXCEPTION_EMAIL = "EMAIL";

//	- change personal information form(name, surname, email) form = ChangePersonInformationCommand.class
//	- registration form (name, surname, email) form = RegistrationUserCommand.class	
	public static final String TYPY_INPUT_EXCEPTION_NAME = "NAME";
	public static final String TYPY_INPUT_EXCEPTION_SURNAME = "SURNAME";

//	MARKET PAGES
//	- market products page include filter = ShowProductPetsIncludedFilterCommand.class
	public static final String TYPE_INPUT_EXCEPTION_YEAR_MONTH = "YEAR_MONTH";

//	- market products page include filter = ShowProductPetsIncludedFilterCommand.class
//	- market products page include filter = ShowProductFeedsAndOtherIncludedFilterCommand.class
//	- create product pet = CraetePetProductCommand.class
//	- change product pet = ChangeProductPetCommand.class
//	- create product feeds and other = CraeteProductFeedsAndOtherCommand.class
//	- change product feeds and other = ChangeProductFeedsAndOtherCommand.class
	public static final String TYPE_INPUT_EXCEPTION_DISCOUNT = "DISCOUNT";
	public static final String TYPE_INPUT_EXCEPTION_PRICE = "PRICE";

//	- create product pet = CraetePetProductCommand.class
//	- change product pet = ChangeProductPetCommand.class
	public static final String TYPE_INPUT_EXCEPTION_SPECIE = "SPECIE";
	public static final String TYPE_INPUT_EXCEPTION_BREED = "BREED";
	public static final String TYPE_INPUT_EXCEPTION_BIRTH_DATE = "BIRTH_DATE";

//	- create product feeds and other = CraeteProductFeedsAndOtherCommand.class
//	- change product feeds and other = ChangeProductFeedsAndOtherCommand.class
	public static final String TYPE_INPUT_EXCEPTION_PRODUCT_TYPE = "PRODUCT_TYPE";
	public static final String TYPE_INPUT_EXCEPTION_BRAND = "BRAND";
	public static final String TYPE_INPUT_EXCEPTION_DESCRIPTION = "DESCRIPTION";
	public static final String TYPE_INPUT_EXCEPTION_PET_TYPES = "PET_TYPES";

//	- create product pet = CraetePetProductCommand.class
//	- change product pet = ChangeProductPetCommand.class
//	- create product feeds and other = CraeteProductFeedsAndOtherCommand.class
//	- change product feeds and other = ChangeProductFeedsAndOtherCommand.class
	public static final String TYPE_INPUT_EXCEPTION_IMAGE = "IMAGE";
	public static final String TYPE_INPUT_EXCEPTION_NUMBER_OF_UNITS_PRODUCT = "NUMBER_OF_UNITS_PRODUCT";

	private TypeInputExeception() {
	}
}