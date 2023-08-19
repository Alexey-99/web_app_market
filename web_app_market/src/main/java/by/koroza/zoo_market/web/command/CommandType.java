package by.koroza.zoo_market.web.command;

import by.koroza.zoo_market.web.command.impl.admin.change.order.quantity.ChangeQuantityProductFeedAndOtherCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.order.quantity.ChangeQuantityProductPetCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.order.status.ChangeOrderStatusWithProductFeedAndOtherCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.order.status.ChangeOrderStatusWithProductPetCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.product.ChangeProductFeedsAndOtherCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.product.ChangeProductPetCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.product.update.UpdateChangedFeedsAndOtherProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.product.update.UpdateChangedPetProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.user.status.AdminPageChangeUserRoleCommand;
import by.koroza.zoo_market.web.command.impl.admin.change.user.status.ChangeUserRoleCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.CraetePetProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.CraeteProductFeedsAndOtherCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.add.AddOtherProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.create.add.AddPetProductCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.form.change.product.ShowChangeFeedsAndOtherProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.form.change.product.ShowChangePetProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.form.create.ShowCreateFeedsAndOtherProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.form.create.ShowCreatePetProductFormCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.ShowAllProductsCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.numberunits.ShowAllProductsSortingByNumberOfUnitsProductAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.numberunits.ShowAllProductsSortingByNumberOfUnitsProductDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.discount.ShowAllProductsSortingByDiscountAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.discount.ShowAllProductsSortingByDiscountDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.id.ShowAllProductsSortingByIdAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.id.ShowAllProductsSortingByIdDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.other.brand.ShowAllProductsSortingByProductBrandAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.other.brand.ShowAllProductsSortingByProductBrandDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.other.description.ShowAllProductsSortingByProductDescriptionAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.other.description.ShowAllProductsSortingByProductDescriptionDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.other.typespets.ShowAllProductsSortingByProductTypesPetsAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.other.typespets.ShowAllProductsSortingByProductTypesPetsDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.pet.birthdate.ShowAllProductsSortingByProductPetBirthDateAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.pet.birthdate.ShowAllProductsSortingByProductPetBirthDateDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.pet.breed.ShowAllProductsSortingByProductPetBreedAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.pet.breed.ShowAllProductsSortingByProductPetBreedDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.pet.type.ShowAllProductsSortingByProductPetTypeAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.pet.type.ShowAllProductsSortingByProductPetTypeDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.price.ShowAllProductsSortingByPriceAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.price.ShowAllProductsSortingByPriceDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.totalprice.ShowAllProductsSortingByTotalPriceAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.totalprice.ShowAllProductsSortingByTotalPriceDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.type.ShowAllProductsSortingByProductTypeAscendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.all.sorting.product.type.ShowAllProductsSortingByProductTypeDescendingCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.information.ShowMoreDetailsAboutProductFeedsAndOtherCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.products.information.ShowMoreDetailsAboutProductPetCommand;
import by.koroza.zoo_market.web.command.impl.admin.show.user.ShowAllUsersCommand;
import by.koroza.zoo_market.web.command.impl.locale.SetEnglishLocaleCommand;
import by.koroza.zoo_market.web.command.impl.locale.SetRussinLocaleCommand;
import by.koroza.zoo_market.web.command.impl.user.add.AddFeedAndOtherProductToOrderCommand;
import by.koroza.zoo_market.web.command.impl.user.add.AddPetProductToOrderCommand;
import by.koroza.zoo_market.web.command.impl.user.change.ChangeEmailCommand;
import by.koroza.zoo_market.web.command.impl.user.change.ChangeEmailPersonAccountCommand;
import by.koroza.zoo_market.web.command.impl.user.change.ChangeLoginCommand;
import by.koroza.zoo_market.web.command.impl.user.change.ChangePasswordCommand;
import by.koroza.zoo_market.web.command.impl.user.confirmation.ConfirmationEmailCommand;
import by.koroza.zoo_market.web.command.impl.user.confirmation.SendOneMoreTimeConfirmationEmailCodeCommand;
import by.koroza.zoo_market.web.command.impl.user.create.BankDataProcessingCommand;
import by.koroza.zoo_market.web.command.impl.user.delete.DeleteFeedAndOtherProductFromOrderCommand;
import by.koroza.zoo_market.web.command.impl.user.delete.DeletePetProductFromOrderCommand;
import by.koroza.zoo_market.web.command.impl.user.payment.OrderPaymentCommand;
import by.koroza.zoo_market.web.command.impl.user.registration.RegistrationUserCommand;
import by.koroza.zoo_market.web.command.impl.user.registration.verification.VerificationRegistrationInformationCommand;
import by.koroza.zoo_market.web.command.impl.user.show.basket.ShowBacketPageCommand;
import by.koroza.zoo_market.web.command.impl.user.show.confirmation.ShowConfirmationEmailFormCommand;
import by.koroza.zoo_market.web.command.impl.user.show.form.change.ShowChangeUserEmailFormComamnd;
import by.koroza.zoo_market.web.command.impl.user.show.form.registration.ShowRegistrationUserFormCommand;
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

public enum CommandType {
//	market page.product pet
	SHOW_PRODUCT_PETS_OFF_FILTER(new ShowProductPetsOffFilterCommand()),
	SHOW_PRODUCT_PETS_INCLUDED_FILTER(new ShowProductPetsIncludedFilterCommand()),
	SHOW_MARKET_PAGE_PRODUCT_PETS_BY_NUMBER_PAGE(new ShowProductPetNumberPageCommand()),
//	market page.add product pet in order
	ADD_PET_PRODUCT_TO_ORDER(new AddPetProductToOrderCommand()),
//	market page.product feeds and other
	SHOW_PRODUCT_FEEDS_AND_OTHER_OFF_FILTER(new ShowProductFeedsAndOtherOffFilterCommand()),
	SHOW_PRODUCT_FEEDS_AND_OTHER_INCLUDED_FILTER(new ShowProductFeedsAndOtherIncludedFilterCommand()),
	SHOW_MARKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE(new ShowProductFeedsAndOtherNumberPageCommand()),
//	market page.add product feeds_and_othrer in order
	ADD_FEEDS_AND_OTHER_PRODUCT_TO_ORDER(new AddFeedAndOtherProductToOrderCommand()),
//	home page
	SHOW_HOME_PAGE(new ShowHomePageCommand()),
//	basket page
	SHOW_BACKET_PAGE(new ShowBacketPageCommand()),
//	basket page.delete product from order 
	DELETE_PET_PRODUCT_FROM_ORDER(new DeletePetProductFromOrderCommand()),
	DELETE_FEEDS_AND_OTHER_PRODUCT_FROM_ORDER(new DeleteFeedAndOtherProductFromOrderCommand()),
//	registration
	REGISTRATION_USER(new RegistrationUserCommand()),
	VERIFICATION_REGISTRATION_INFORMATION(new VerificationRegistrationInformationCommand()),
	SHOW_REGISTRATION_FORN_VALDATED(new ShowRegistrationUserFormCommand()),
//	confirmation email
	CONFIMATION_EMAIL(new ConfirmationEmailCommand()),
	SHOW_CONFIMATION_EMAIL_FORM(new ShowConfirmationEmailFormCommand()),
	SEND_ONE_MORE_TIME_CONFIMATION_EMAIL_CODE(new SendOneMoreTimeConfirmationEmailCodeCommand()),
//	sign in / out 
	SIGN_IN_PERSON_ACCOUNT(new SignInPersonAccountCommand()),
	SIGN_OUT_PERSONAL_ACCOUNT(new SignOutPersonalAccountCommand()),
//	change personal information
	CHANGE_EMAIL_PERSON_ACCOUNT(new ChangeEmailPersonAccountCommand()), CHANGE_LOGIN(new ChangeLoginCommand()),
	CHANGE_PASSWORD(new ChangePasswordCommand()), CHANGE_EMAIL(new ChangeEmailCommand()),
	SHOW_CHANGE_EMAIL_FORM(new ShowChangeUserEmailFormComamnd()),
//	ORDER
//	bank data processing
	BANK_DATA_PROCESSING(new BankDataProcessingCommand()),
//	order_payment
	ORDER_PAYMENT(new OrderPaymentCommand()),
//	change locale 
	SET_RUSSIAN_LOCALE(new SetRussinLocaleCommand()), SET_ENGLISH_LOCALE(new SetEnglishLocaleCommand()),
//	personal account pages
	SHOW_PERSONAL_ACCOUNT_PERSON_INFORMATION_PAGE(new ShowPersonalAccountPersonInfomationPageCommand()),
	SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE(new ShowPersonalAccountHistoryOrdersPageCommand()),
	SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE(new ShowPersonalAccountAdminPage()),
//	ADMIN PART
//	admin page with all products
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER(new ShowAllProductsCommand()),
//	create pet
	ADMIN_PAGE_CREATE_PET_PRODUCT(new CraetePetProductCommand()),
	ADMIN_PAGE_ADD_NEW_PRODUCT_PET(new AddPetProductCommand()),
	ADMIN_PAGE_SHOW_CREATE_PET_PRODUCT_FORM(new ShowCreatePetProductFormCommand()),
//	create feeds and other
	ADMIN_PAGE_CREATE_FEED_AND_OTHER_PRODUCT(new CraeteProductFeedsAndOtherCommand()),
	ADMIN_PAGE_ADD_NEW_PRODUCT_FEED_AND_OTHER(new AddOtherProductCommand()),
	ADMIN_PAGE_SHOW_CREATE_FEED_AND_OTHER_PRODUCT_FORM(new ShowCreateFeedsAndOtherProductFormCommand()),
//	change pet
	ADMIN_PAGE_CHANGE_PET_PRODUCT(new ChangeProductPetCommand()),
	ADMIN_PAGE_UPDATE_CHANGED_PRODUCT_PET(new UpdateChangedPetProductCommand()),
	ADMIN_PAGE_SHOW_CHANGE_PET_PRODUCT_FORM(new ShowChangePetProductFormCommand()),
//	change feeds and other
	ADMIN_PAGE_CHANGE_FEED_AND_OTHER_PRODUCT(new ChangeProductFeedsAndOtherCommand()),
	ADMIN_PAGE_UPDATE_CHANGED_PRODUCT_FEED_AND_OTHER(new UpdateChangedFeedsAndOtherProductCommand()),
	ADMIN_PAGE_SHOW_CHANGE_FEED_AND_OTHER_PRODUCT_FORM(new ShowChangeFeedsAndOtherProductFormCommand()),
//	SORTING PRODUCTS
//	product id
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING(
			new ShowAllProductsSortingByIdAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_DESCENDING(
			new ShowAllProductsSortingByIdDescendingCommand()),
//	product type
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING(
			new ShowAllProductsSortingByProductTypeAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_DESCENDING(
			new ShowAllProductsSortingByProductTypeDescendingCommand()),
//	product brand
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING(
			new ShowAllProductsSortingByProductBrandAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_DESCENDING(
			new ShowAllProductsSortingByProductBrandDescendingCommand()),
//	product description
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_ASCENDING(
			new ShowAllProductsSortingByProductDescriptionAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_DESCENDING(
			new ShowAllProductsSortingByProductDescriptionDescendingCommand()),
//	product types pets
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING(
			new ShowAllProductsSortingByProductTypesPetsAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_DESCENDING(
			new ShowAllProductsSortingByProductTypesPetsDescendingCommand()),
//	product pet type
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING(
			new ShowAllProductsSortingByProductPetTypeAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_DESCENDING(
			new ShowAllProductsSortingByProductPetTypeDescendingCommand()),
//	product pet breed
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING(
			new ShowAllProductsSortingByProductPetBreedAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_DESCENDING(
			new ShowAllProductsSortingByProductPetBreedDescendingCommand()),
//	product pet birth date
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING(
			new ShowAllProductsSortingByProductPetBirthDateAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_DESCENDING(
			new ShowAllProductsSortingByProductPetBirthDateDescendingCommand()),
//	product price
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING(
			new ShowAllProductsSortingByPriceAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_DESCENDING(
			new ShowAllProductsSortingByPriceDescendingCommand()),
//	product discount
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING(
			new ShowAllProductsSortingByDiscountAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_DESCENDING(
			new ShowAllProductsSortingByDiscountDescendingCommand()),
//	product total price
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING(
			new ShowAllProductsSortingByTotalPriceAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_DESCENDING(
			new ShowAllProductsSortingByTotalPriceDescendingCommand()),
//	product number of units
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING(
			new ShowAllProductsSortingByNumberOfUnitsProductAscendingCommand()),
	ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_DESCENDING(
			new ShowAllProductsSortingByNumberOfUnitsProductDescendingCommand()),
//	SHOW MORE DETAILS
	ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_PET(new ShowMoreDetailsAboutProductPetCommand()),
	ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_FEEDS_AND_OTHER(new ShowMoreDetailsAboutProductFeedsAndOtherCommand()),
//	CHANGE_ORDER
	ADMIN_PAGE_CHANGE_QUANTITY_PRODUCT_IN_ORDER_FEED_AND_OTHER(new ChangeQuantityProductFeedAndOtherCommand()),
	ADMIN_PAGE_CHANGE_QUANTITY_PRODUCT_IN_ORDER_PET(new ChangeQuantityProductPetCommand()),
	ADMIN_PAGE_CHANGE_ORDER_STATUS_WITH_PRODUCT_FEED_AND_OTHER(new ChangeOrderStatusWithProductFeedAndOtherCommand()),
	ADMIN_PAGE_CHANGE_ORDER_STATUS_WITH_PRODUCT_PET(new ChangeOrderStatusWithProductPetCommand()),

//	SHOW ALL USERS
	ADMIN_PAGE_SHOW_ALL_USERS(new ShowAllUsersCommand()),

//	CHANGE_USER
	ADMIN_PAGE_CHANGE_USER_STATUS(new ChangeUserRoleCommand()),
	ADMIN_PAGE_SHOW_ALL_USERS_CHANGE_USER_STATUS(new AdminPageChangeUserRoleCommand());

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