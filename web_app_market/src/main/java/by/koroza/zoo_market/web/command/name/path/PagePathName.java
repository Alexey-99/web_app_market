package by.koroza.zoo_market.web.command.name.path;

public final class PagePathName {
	public static final String INDEX_PAGE_PATH = "index.jsp";
	public static final String ERROR_PAGE_500_PATH = "jsp/pages/error/error_page_500.jsp";
	public static final String ERROR_PAGE_404_PATH = "jsp/pages/error/error_page_404.jsp";

//	PROPERTIES
	public static final String PAGE_CONTENT_PROPERTIES = "locale.pageContent";

//	MARKET 
	public static final String PRODUCTS_PETS_PAGE_PATH = "jsp/pages/market_page/pets/products_pets_page.jsp";
	public static final String PRODUCTS_FEED_AND_OTHER_PAGE_PATH = "jsp/pages/market_page/other/products_feed_ond_other_page.jsp";

//	HOME 
	public static final String HOME_PAGE_PATH = "jsp/pages/home_page.jsp";

//	BASKET 
	public static final String BACKET_WITH_PRODUCTS_PAGE_PATH = "jsp/pages/backet/basket_with_products.jsp";

//	ORDER_PREVIEW
	public static final String ORDER_PREVIEW_PAGE_PATH = "jsp/pages/order/order_preview_page.jsp";

//	PERSONAL_ACCOUNT
	public static final String PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH = "jsp/pages/personal_account/personal_account_person_infomation.jsp";
	public static final String PERSONAL_ACCOUNT_ORDERS_HISTORY_PAGE_PATH = "jsp/pages/personal_account/personal_account_orders.jsp";
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_PATH = "jsp/pages/personal_account/personal_account_admin_page.jsp";

//	ADMIN_PAGES
//	ADMIN_OPERATIONS
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH = "jsp/pages/personal_account/admin_pages/products/all_products_page.jsp";

//	ADMIN_change_user_status_form_validated 
	public static final String PERSONAL_ACCOUNT_ADMIN_CHANGE_USER_STATUS_FORM_VALIDATED_PATH = "jsp/pages/form/admin/change/user/change_user_status_form_validated.jsp";
//	success_changed_user_status 
	public static final String PERSONAL_ACCOUNT_ADMIN_SUCCESS_CHANGED_USER_STATUS_PAGE_PATH = "jsp/pages/form/admin/change/user/success/success_changed_user_status.jsp";

//	ADMIN_CREATE_PRODUCT_FORM
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_PET_PRODUCT_FORM = "jsp/pages/form/admin/create/product/create_pet_product_form_validated.jsp";
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM = "jsp/pages/form/admin/create/product/create_feed_and_other_product_form_validated.jsp";
//	ADMIN_VERIDICATION_INFORMATION
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CREATE_PET_PRODUCT = "jsp/pages/form/admin/create/product/verification/verification_information_for_create_product_pet.jsp";
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CREATE_FEEDS_AND_OTHER_PRODUCT = "jsp/pages/form/admin/create/product/verification/verification_information_for_create_product_feed_and_other.jsp";

//	ADMIN_CHANGE_PRODUCT_FORM
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_PET_PRODUCT_FORM = "jsp/pages/form/admin/change/product/change_pet_product_form_validated.jsp";
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM = "jsp/pages/form/admin/change/product/change_feed_and_other_product_form_validated.jsp";
//	ADMIN_VERIDICATION_INFORMATION
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CHANGE_PET_PRODUCT = "jsp/pages/form/admin/change/product/verification/verification_information_for_change_product_pet.jsp";
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_VERIFICATION_INFORMATION_FOR_CHANGE_FEED_AND_OTHER_PRODUCT = "jsp/pages/form/admin/change/product/verification/verification_information_for_change_product_feed_and_other.jsp";
//	ADMIN_SHOW_MORE_DETAILS_PRODUCT
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_FEED_AND_OTHER_PAGE_PATH = "jsp/pages/personal_account/admin_pages/information/product/more_details_about_product_feeds_and_other.jsp";
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_PET_PAGE_PATH = "jsp/pages/personal_account/admin_pages/information/product/more_details_about_product_pet.jsp";
//	ADMIN_SHOW_ALL_USERS
	public static final String PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_USERS_PAGE_PATH = "jsp/pages/personal_account/admin_pages/users/all_users_page.jsp";

//	REGISTRATION
	public static final String REGISTRATION_FORM_PAGE_PATH = "jsp/pages/form/registration/registration_form.jsp";
//	VERIDICATION_REGISTRATION_INFORMATION
	public static final String VERIFICATION_REGISTRATION_INFORMATION_PAGE_PATH = "jsp/pages/form/registration/verification/verification_registration_information.jsp";

	public static final String CONFIMARTION_EMAIL_PAGE_PATH = "jsp/pages/form/confirmation_email/confirmation_email.jsp";
	public static final String CONFIMARTION_EMAIL_VALIDATED_PAGE_PATH = "jsp/pages/form/confirmation_email/confirmation_email_with_exception.jsp";

	public static final String SIGN_IN_VALIDATED_PAGE_PATH = "jsp/pages/form/sign_in/sign_in_exception.jsp";

	public static final String CHANGE_PERSON_INFOMATION_FORM_VALIDATED_PAGE_PATH = "jsp/pages/personal_account/forms/change_person_information_form_validated.jsp";
	public static final String CHANGE_LOGIN_FORM_VALIDATED_INCORRECT_NEW_LOGIN_PAGE_PATH = "jsp/pages/personal_account/forms/change_login_form_validated_incorrect_new_login.jsp";
	public static final String CHANGE_LOGIN_FORM_VALIDATED_INCORRECT_CURRENT_LOGIN_PASSWORD_PAGE_PATH = "jsp/pages/personal_account/forms/change_login_form_validated_incorrect_currect_login_and_pasword.jsp";
	public static final String CHANGE_PASSWORD_FORM_VALIDATED_INCORRECT_NEW_PASSWORD_PAGE_PATH = "jsp/pages/personal_account/forms/change_password_form_validated_incorrect_new_password.jsp";
	public static final String CHANGE_PASSWORD_FORM_VALIDATED_INCORRECT_CURRENT_LOGIN_PASSWORD_PAGE_PATH = "jsp/pages/personal_account/forms/change_password_form_validated_incorrect_current_login_and_password.jsp";
	public static final String CHANGE_EMAIL_FORM_VALIDATED_PAGE_PATH = "jsp/pages/form/change/user/change_email_form_validated.jsp";

	public static final String ORDER_PAYMENT_FORM_VALIDATED_PAGE_PATH = "jsp/pages/form/payment/order_payment_form_validated.jsp";
	public static final String SUCCESS_ORDER_PAYMENT_PAGE_PATH = "jsp/pages/form/payment/success/success_order_payment.jsp";

	private PagePathName() {
	}
}