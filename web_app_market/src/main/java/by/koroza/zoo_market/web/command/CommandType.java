package by.koroza.zoo_market.web.command;

import by.koroza.zoo_market.web.command.impl.admin.change.ChangeProductFeedsAndOtherCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.ChangeProductPetCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.update.UpdateChangedFeedsAndOtherProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.update.UpdateChangedPetProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.CraeteOtherProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.CraetePetProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.add.AddOtherProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.add.AddPetProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.form.change.ShowChangeFeedsAndOtherProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.form.change.ShowChangePetProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.form.create.ShowCreateFeedsAndOtherProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.form.create.ShowCreatePetProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.ShowAllProductsOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.sorting.id.ShowAllProductsOffFilterSortingByIdAscending;
import by.koroza.zoo_market.web.command.impl.admin.show.products.sorting.id.ShowAllProductsOffFilterSortingByIdDescending;
import by.koroza.zoo_market.web.command.impl.locale.SetEnglishLocaleCommand;
import by.koroza.zoo_market.web.command.impl.locale.SetRussinLocaleCommand;
import by.koroza.zoo_market.web.command.impl.user.change.ChangeLoginAndPasswordCommand;
import by.koroza.zoo_market.web.command.impl.user.change.ChangePersonInformationCommand;
import by.koroza.zoo_market.web.command.impl.user.confirmation.ConfirmationEmailCommand;
import by.koroza.zoo_market.web.command.impl.user.confirmation.SendOneMoreTimeConfirmationEmailCodeCommand;
import by.koroza.zoo_market.web.command.impl.user.payment.OrderPaymentCommand;
import by.koroza.zoo_market.web.command.impl.user.registration.RegistrationUserCommand;
import by.koroza.zoo_market.web.command.impl.user.show.basket.ShowBacketPageCommand;
import by.koroza.zoo_market.web.command.impl.user.show.confirmation.ShowConfirmationEmailFormCommand;
import by.koroza.zoo_market.web.command.impl.user.show.home.ShowHomePageCommand;
import by.koroza.zoo_market.web.command.impl.user.show.market.other.ShowProductFeedsAndOtherIncludedFilterCommand;
import by.koroza.zoo_market.web.command.impl.user.show.market.other.ShowProductFeedsAndOtherOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.user.show.market.other.page.ShowProductFeedsAndOtherNumberPageCommand;
import by.koroza.zoo_market.web.command.impl.user.show.market.pet.ShowProductPetsIncludedFilterCommand;
import by.koroza.zoo_market.web.command.impl.user.show.market.pet.ShowProductPetsOffFilterCommand;
import by.koroza.zoo_market.web.command.impl.user.show.market.pet.page.ShowProductPetNumberPageCommand;
import by.koroza.zoo_market.web.command.impl.user.show.pesonal.ShowPersonalAccountAdminPage;
import by.koroza.zoo_market.web.command.impl.user.show.pesonal.ShowPersonalAccountHistoryOrdersPageCommand;
import by.koroza.zoo_market.web.command.impl.user.show.pesonal.ShowPersonalAccountPersonInfomationPageCommand;
import by.koroza.zoo_market.web.command.impl.user.sign.SignInPersonAccountCommand;
import by.koroza.zoo_market.web.command.impl.user.sign.SignOutPersonalAccountCommand;
import by.koroza.zoo_market.web.command.impl.user.verification.VerificationRegistrationInformationCommand;

public enum CommandType {
	/* market page.product pet */
	SHOW_PRODUCT_PETS_OFF_FILTER(new ShowProductPetsOffFilterCommand()),
	SHOW_PRODUCT_PETS_INCLUDED_FILTER(new ShowProductPetsIncludedFilterCommand()),
	SHOW_MARKET_PAGE_PRODUCT_PETS_BY_NUMBER_PAGE(new ShowProductPetNumberPageCommand()),
	/* market page.product feeds and other */
	SHOW_PRODUCT_FEEDS_AND_OTHER_OFF_FILTER(new ShowProductFeedsAndOtherOffFilterCommand()),
	SHOW_PRODUCT_FEEDS_AND_OTHER_INCLUDED_FILTER(new ShowProductFeedsAndOtherIncludedFilterCommand()),
	SHOW_MARKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE(new ShowProductFeedsAndOtherNumberPageCommand()),
	/* home page */
	SHOW_HOME_PAGE(new ShowHomePageCommand()),
	/* basket page */
	SHOW_BACKET_PAGE(new ShowBacketPageCommand()),
	/* registration */
	REGISTRATION_USER(new RegistrationUserCommand()),
	VERIFICATION_REGISTRATION_INFORMATION(new VerificationRegistrationInformationCommand()),
	/* confirmation email */
	CONFIMATION_EMAIL(new ConfirmationEmailCommand()),
	SHOW_CONFIMATION_EMAIL_FORM(new ShowConfirmationEmailFormCommand()),
	SEND_ONE_MORE_TIME_CONFIMATION_EMAIL_CODE(new SendOneMoreTimeConfirmationEmailCodeCommand()),
	/* sign in / out */
	SIGN_IN_PERSON_ACCOUNT(new SignInPersonAccountCommand()),
	SIGN_OUT_PERSONAL_ACCOUNT(new SignOutPersonalAccountCommand()),
	/* change personal information */
	CHANGE_PERSON_INFORMATION(new ChangePersonInformationCommand()),
	CHANGE_LOGIN_AND_PASSWORD(new ChangeLoginAndPasswordCommand()),
	/* order payment */
	ORDER_PAYMENT(new OrderPaymentCommand()),
	/* change locale */
	SET_RUSSIAN_LOCALE(new SetRussinLocaleCommand()), SET_ENGLISH_LOCALE(new SetEnglishLocaleCommand()),
	/* personal account pages */
	SHOW_PERSONAL_ACCOUNT_PERSON_INFORMATION_PAGE(new ShowPersonalAccountPersonInfomationPageCommand()),
	SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE(new ShowPersonalAccountHistoryOrdersPageCommand()),
	SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE(new ShowPersonalAccountAdminPage()),
	/* admin page with all products */
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER(new ShowAllProductsOffFilterCommand()),
	/* create pet */
	ADMIN_PAGE_CREATE_PET_PRODUCT(new CraetePetProductCommand()),
	ADMIN_PAGE_ADD_NEW_PRODUCT_PET(new AddPetProductCommand()),
	ADMIN_PAGE_SHOW_CREATE_PET_PRODUCT_FORM(new ShowCreatePetProductFormCommand()),
	/* create feeds and other */
	ADMIN_PAGE_CREATE_FEED_AND_OTHER_PRODUCT(new CraeteOtherProductCommand()),
	ADMIN_PAGE_ADD_NEW_PRODUCT_FEED_AND_OTHER(new AddOtherProductCommand()),
	ADMIN_PAGE_SHOW_CREATE_FEED_AND_OTHER_PRODUCT_FORM(new ShowCreateFeedsAndOtherProductFormCommand()),
	/* change pet */
	ADMIN_PAGE_CHANGE_PET_PRODUCT(new ChangeProductPetCommand()),
	ADMIN_PAGE_UPDATE_CHANGED_PRODUCT_PET(new UpdateChangedPetProductCommand()),
	ADMIN_PAGE_SHOW_CHANGE_PET_PRODUCT_FORM(new ShowChangePetProductFormCommand()),
	/* change feeds and other */
	ADMIN_PAGE_CHANGE_FEED_AND_OTHER_PRODUCT(new ChangeProductFeedsAndOtherCommand()),
	ADMIN_PAGE_UPDATE_CHANGED_PRODUCT_FEED_AND_OTHER(new UpdateChangedFeedsAndOtherProductCommand()),
	ADMIN_PAGE_SHOW_CHANGE_FEED_AND_OTHER_PRODUCT_FORM(new ShowChangeFeedsAndOtherProductFormCommand()),
	/* SORTING PRODUCTS */
	/* id */
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_ID_ASCENDING(new ShowAllProductsOffFilterSortingByIdAscending()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_ID_DESCENDING(
			new ShowAllProductsOffFilterSortingByIdDescending()),
	/**/;

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