package by.koroza.zoo_market.web.command.name;

public final class AttributeName {

	public static final String ATTRIBUTE_SESSION_LOCALE = "locale";
	public static final String ATTRIBUTE_ENCODING = "encoding";
	public static final String ATTRIBUTE_INDEX_PAGE = "index_page";
	public static final String ATTRIBUTE_PREVIOUS_COMMAND = "previous_command";

	public static final String ATTRIBUTE_USER = "user";
	public static final String ATTRIBUTE_ORDER = "order";
	public static final String ATTRIBUTE_IS_HAVING_REGISTRATED_USER = "is_hiving_registrated_user";

	/* INPUT_EXCEPTIONS */
	/* registration */
	public static final String ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "registration_input_exception_type_and_message";
	/* payment_form */
	public static final String ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "order_payment_input_exception_type_and_message";
	/* market_pages.filters */
	public static final String ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "products_pets_filter_input_exception_type_and_message";
	public static final String ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "products_feeds_and_other_filter_input_exception_type_and_message";
	/* pesron_account */
	/* pesron_account.change_person_information_form */
	public static final String ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "changing_person_information_input_exception_type_and_message";
	/* pesron_account.change_login_and_password_form */
	public static final String ATTRIBUTE_CHANGING_LOGIN_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "changing_login_input_exception_type_and_message";
	public static final String ATTRIBUTE_CHANGING_PASSWORD_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "changing_password_input_exception_type_and_message";
	/* create_products_form */
	public static final String ATTRIBUTE_ADMIN_PAGE_CREATE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "admin_page_create_pet_product_input_exception_type_and_message";
	public static final String ATTRIBUTE_ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "admin_page_create_feeds_and_other_product_input_exception_type_and_message";
	/* change_products_form */
	public static final String ATTRIBUTE_ADMIN_PAGE_CHANGE_PET_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "admin_page_change_pet_product_input_exception_type_and_message";
	public static final String ATTRIBUTE_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "admin_page_change_feeds_and_other_product_input_exception_type_and_message";
	/* change_user_status_form */
	public static final String ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE = "admin_page_change_user_status_input_exception_type_and_message";

	public static final String ATTRIBUTE_USER_HISTORY_ORDERS = "user_history_orders";

	/* WEB_FILTER_MAP */
	public static final String ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP = "products_pets_filter_map";
	public static final String ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER_MAP = "products_feeds_and_other_filter_map";

	/* JAVA_OBJECT_FILTER */
	public static final String ATTRIBUTE_PRODUCTS_PETS_FILTER = "products_pets_filter";
	public static final String ATTRIBUTE_PRODUCTS_FEEDS_AND_OTHER_FILTER = "products_feeds_and_other_filter";

	/* LIST_PRODUCTS - market page */
	public static final String ATTRIBUTE_LIST_PRODUCTS_PETS = "list_products_pets";
	public static final String ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER = "list_products_feeds_and_other";

	/* MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT - admin page - all products */
	public static final String ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT = "map_products_and_number_of_units";

	public static final String SESSION_ATTRIBUTE_PET_CLASS_NAME = "pet_class";
	public static final String SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME = "feed_and_other_class";

	public static final String ATTRIBUTE_FOR_LIST_PETS = "productsPets";
	public static final String ATTRIBUTE_FOR_LIST_FEEDS_AND_OTHER = "productsOther";

	public static final String REQUEST_ATTRIBUTE_NUMBER_PAGE = "number_page";

	public static final String REQUEST_ATTRIBUTE_COMMAND = "sorting_command";

	/* BUFFER */
	/* create or change product */
	public static final String ATTRIBUTE_BUFFER_PRODUCT_PET = "product_pet";
	public static final String ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT = "product_pet_number_of_units";
	public static final String ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER = "product_feeds_and_other";
	public static final String ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT = "product_feeds_and_other_number_of_units";
	/* change user status */
	public static final String ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_LOGIN = "changing_user_login";
	public static final String ATTRIBUTE_BUFFER_CHANGE_USER_STATUS_ROLE_ID = "changing_user_role_id";

	public static final String ATTRIBUTE_SAVED_PRODUCTS_ID_IN_JSP_PAGE = "saved_products_id";

	/* IMAGE_CONTROLLER */
	public static final String ATTRIBUTE_UPLOAD_FILE_EXCEPTION = "upload_file_exception";
	public static final String ATTRIBUTE_UPLOAD_FILE_DIRECTORY = "upload_file_dir";

	private AttributeName() {
	}
}