package by.koroza.zoo_market.web.command;

import by.koroza.zoo_market.web.command.impl.OrderPaymentCommand;
import by.koroza.zoo_market.web.command.impl.RegistrationUserCommand;
import by.koroza.zoo_market.web.command.impl.SendOneMoreTimeVerificationCodeCommand;
import by.koroza.zoo_market.web.command.impl.admin.add.AddOtherProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.add.AddPetProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.ChangeProductFeedsAndOtherCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.ChangeProductPetCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.CraeteOtherProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.CraetePetProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.ShowAllProductsOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.ShowChangePetProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.ShowCreateFeedsAndOtherProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.ShowCreatePetProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.updete.UpdateChangedPetProductCommand;
import by.koroza.zoo_market.web.command.impl.change.ChangeLoginAndPasswordCommand;
import by.koroza.zoo_market.web.command.impl.change.ChangePersonInformationCommand;
import by.koroza.zoo_market.web.command.impl.locale.SetEnglishLocaleCommand;
import by.koroza.zoo_market.web.command.impl.locale.SetRussinLocaleCommand;
import by.koroza.zoo_market.web.command.impl.show.basket.ShowBacketPageCommand;
import by.koroza.zoo_market.web.command.impl.show.home.ShowHomePageCommand;
import by.koroza.zoo_market.web.command.impl.show.market.other.ShowProductFeedsAndOtherIncludedFilterCommand;
import by.koroza.zoo_market.web.command.impl.show.market.other.ShowProductFeedsAndOtherOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.show.market.other.page.ShowProductFeedsAndOtherNumberPageCommand;
import by.koroza.zoo_market.web.command.impl.show.market.pet.ShowProductPetsIncludedFilterCommand;
import by.koroza.zoo_market.web.command.impl.show.market.pet.ShowProductPetsOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.show.market.pet.page.ShowProductPetNumberPageCommand;
import by.koroza.zoo_market.web.command.impl.show.pesonal.ShowPersonalAccountAdminPage;
import by.koroza.zoo_market.web.command.impl.show.pesonal.ShowPersonalAccountHistoryOrdersPageCommand;
import by.koroza.zoo_market.web.command.impl.show.pesonal.ShowPersonalAccountPersonInfomationPageCommand;
import by.koroza.zoo_market.web.command.impl.show.verification.ShowVerificationPersonAccountFormCommand;
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
	SET_RUSSIAN_LOCALE(new SetRussinLocaleCommand()), SET_ENGLISH_LOCALE(new SetEnglishLocaleCommand()),
	SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE(new ShowPersonalAccountAdminPage()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER(new ShowAllProductsOffFilterCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_INCLUDED_FILTER(new ShowAllProductsOffFilterCommand()),
	ADMIN_PAGE_CREATE_PET_PRODUCT(new CraetePetProductCommand()), ADD_NEW_PRODUCT_PET(new AddPetProductCommand()),
	ADMIN_PAGE_SHOW_CREATE_PET_PRODUCT_FORM(new ShowCreatePetProductFormCommand()),
	ADMIN_PAGE_CREATE_FEED_AND_OTHER_PRODUCT(new CraeteOtherProductCommand()),
	ADD_NEW_PRODUCT_FEED_AND_OTHER(new AddOtherProductCommand()),
	ADMIN_PAGE_SHOW_CREATE_FEED_AND_OTHER_PRODUCT_FORM(new ShowCreateFeedsAndOtherProductFormCommand()),
	ADMIN_PAGE_CHANGE_PET_PRODUCT(new ChangeProductPetCommand()),
	ADMIN_PAGE_CHANGE_FEED_AND_OTHER_PRODUCT(new ChangeProductFeedsAndOtherCommand()),
	ADMIN_PAGE_SHOW_CHANGE_PET_PRODUCT_FORM(new ShowChangePetProductFormCommand()),
	UPDATE_CHANGED_PRODUCT_PET(new UpdateChangedPetProductCommand()),
	SHOW_MARKET_PAGE_PRODUCT_PETS_BY_NUMBER_PAGE(new ShowProductPetNumberPageCommand()),
	SHOW_MARKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE(new ShowProductFeedsAndOtherNumberPageCommand());

	private Command command;

	private CommandType(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return this.command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
}