package by.koroza.zoo_market.web.command;

import by.koroza.zoo_market.web.command.impl.OrderPaymentCommand;
import by.koroza.zoo_market.web.command.impl.RegistrationUserCommand;
import by.koroza.zoo_market.web.command.impl.SendOneMoreTimeVerificationCodeCommand;
import by.koroza.zoo_market.web.command.impl.change.ChangeLoginAndPasswordCommand;
import by.koroza.zoo_market.web.command.impl.change.ChangePersonInformationCommand;
import by.koroza.zoo_market.web.command.impl.locale.SetEnglishLocaleCommand;
import by.koroza.zoo_market.web.command.impl.locale.SetRussinLocaleCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowBacketPageCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowHomePageCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowPersonalAccountHistoryOrdersPageCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowPersonalAccountPersonInfomationPageCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowProductFeedsAndOtherIncludedFilterCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowProductFeedsAndOtherOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowProductPetsIncludedFilterCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowProductPetsOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.show.ShowVerificationPersonAccountFormCommand;
import by.koroza.zoo_market.web.command.impl.sign.SignInPersonAccountCommand;
import by.koroza.zoo_market.web.command.impl.sign.SignOutPersonalAccountCommand;
import by.koroza.zoo_market.web.command.impl.varification.VerificationPersonAccountCommand;
import by.koroza.zoo_market.web.command.impl.varification.VerificationRegistrationInformationCommand;

public enum CommandType {
	SHOW_PRODUCT_PETS_OFF_FILTER(new ShowProductPetsOffFilterCommand()),
	SHOW_PRODUCT_PETS_INCLUDED_FILTER(new ShowProductPetsIncludedFilterCommand()),
	SHOW_PRODUCT_FEEDS_AND_OTHER_OFF_FILTER(new ShowProductFeedsAndOtherOffFilterCommand()),
	SHOW_PRODUCT_FEEDS_AND_OTHER_INCLUDED_FILTER(new ShowProductFeedsAndOtherIncludedFilterCommand()),
	SHOW_HOME_PAGE(new ShowHomePageCommand()), SHOW_BACKET_PAGE(new ShowBacketPageCommand()),
	SHOW_PERSONAL_ACCOUNT_PERSON_INFORMATION_PAGE(new ShowPersonalAccountPersonInfomationPageCommand()),
	SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE(new ShowPersonalAccountHistoryOrdersPageCommand()),
	REGISTRATION_USER(new RegistrationUserCommand()),
	VERIFICATION_REGISTRATION_INFORMATION(new VerificationRegistrationInformationCommand()),
	VERIFICATION_PERSONAL_ACCOUNT(new VerificationPersonAccountCommand()),
	SHOW_VERIFICATION_PERSONAL_ACCOUNT_FORM(new ShowVerificationPersonAccountFormCommand()),
	SIGN_IN_PERSON_ACCOUNT(new SignInPersonAccountCommand()),
	CHANGE_PERSON_INFORMATION(new ChangePersonInformationCommand()),
	CHANGE_LOGIN_AND_PASSWORD(new ChangeLoginAndPasswordCommand()),
	SEND_ONE_MORE_TIME_VERIFICATION_CODE(new SendOneMoreTimeVerificationCodeCommand()),
	ORDER_PAYMENT(new OrderPaymentCommand()), SIGN_OUT_PERSONAL_ACCOUNT(new SignOutPersonalAccountCommand()),
	SET_RUSSIAN_LOCALE(new SetRussinLocaleCommand()), SET_ENGLISH_LOCALE(new SetEnglishLocaleCommand());

	Command command;

	private CommandType(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
}