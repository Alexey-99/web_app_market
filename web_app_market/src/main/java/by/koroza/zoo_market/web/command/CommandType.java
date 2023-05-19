package by.koroza.zoo_market.web.command;

import by.koroza.zoo_market.web.command.impl.ChangeLoginAndPasswordCommand;
import by.koroza.zoo_market.web.command.impl.ChangePersonInformationCommand;
import by.koroza.zoo_market.web.command.impl.OrderPaymentCommand;
import by.koroza.zoo_market.web.command.impl.RegistrationUserCommand;
import by.koroza.zoo_market.web.command.impl.SendOneMoreTimeVerificationCodeCommand;
import by.koroza.zoo_market.web.command.impl.ShowBacketPageCommand;
import by.koroza.zoo_market.web.command.impl.ShowHomePageCommand;
import by.koroza.zoo_market.web.command.impl.ShowPersonalAccountPersonInfomationPageCommand;
import by.koroza.zoo_market.web.command.impl.ShowProductFeedsAndOtherIncludedFilterCommand;
import by.koroza.zoo_market.web.command.impl.ShowProductFeedsAndOtherOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.ShowProductPetsIncludedFilterCommand;
import by.koroza.zoo_market.web.command.impl.ShowProductPetsOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.ShowVerificationPersonAccountFormCommand;
import by.koroza.zoo_market.web.command.impl.SignInPersonAccountCommand;
import by.koroza.zoo_market.web.command.impl.SignOutPersonalAccountCommand;
import by.koroza.zoo_market.web.command.impl.VerificationPersonAccountCommand;
import by.koroza.zoo_market.web.command.impl.VerificationRegistrationInformationCommand;
import by.koroza.zoo_market.web.command.impl.locale.SetRusLocaleCommand;
import by.koroza.zoo_market.web.command.impl.ShowPersonalAccountHistoryOrdersPageCommand;

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
	ORDER_PAYMENT(new OrderPaymentCommand()), SIGN_OUT_PERSONAL_ACCOUNT(new SignOutPersonalAccountCommand()), SET_RUS_LOCALE(new SetRusLocaleCommand());

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