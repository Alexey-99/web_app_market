package by.koroza.zoo_market.dao.name;

public final class ColumnName {

	public static final String ROLES_ID = "roles.id";
	public static final String ROLES_NAME = "roles.name";

	public static final String USERS_ID = "users.id";
	public static final String USERS_NAME = "users.name";
	public static final String USERS_SURNAME = "users.surname";
	public static final String USERS_ROLE_ID = "users.roles_id";
	public static final String USERS_EMAIL = "users.email";
	public static final String USERS_EMAIL_CONFIRMED = "users.email_confirmed";
	public static final String USERS_LOGIN = "users.login";
	public static final String USERS_PASSWORD = "users.password";
	public static final String USERS_DISCOUNT = "users.discount";
	public static final String USERS_DATE_CREATE = "users.date_create";

	public static final String PETS_ID = "pets.id";
	public static final String PETS_IMAGE_PATH = "pets.image_path";
	public static final String PETS_SPECIE = "pets.specie";
	public static final String PETS_BREED = "pets.breed";
	public static final String PETS_BIRTH_DATE = "pets.birth_date";
	public static final String PETS_PRICE = "pets.price";
	public static final String PETS_DISCOUNT = "pets.discount";
	public static final String PETS_NUMBER_OF_UNITS_PRODUCT = "pets.number_of_units_products";
	public static final String PETS_DATE_UPDATE = "pets.date_update";

	public static final String FEEDS_AND_OTHER_ID = "feeds_and_other.id";
	public static final String FEEDS_AND_OTHER_IMAGE_PATH = "feeds_and_other.image_path";
	public static final String FEEDS_AND_OTHER_TYPE = "feeds_and_other.type";
	public static final String FEEDS_AND_OTHER_BRAND = "feeds_and_other.brand";
	public static final String FEEDS_AND_OTHER_DESCRIPTION = "feeds_and_other.description";
	public static final String FEEDS_AND_OTHER_PET_TYPE = "feeds_and_other.pet_type";
	public static final String FEEDS_AND_OTHER_PRICE = "feeds_and_other.price";
	public static final String FEEDS_AND_OTHER_DISCOUNT = "feeds_and_other.discount";
	public static final String FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT = "feeds_and_other.number_of_units_products";
	public static final String FEEDS_AND_OTHER_DATE_UPDATE = "feeds_and_other.date_update";

	public static final String ORDER_STUTUSES_ID = "order_statuses.id";
	public static final String ORDER_STUTUSES_NAME = "order_statuses.name";

	public static final String ORDERS_ID = "orders.id";
	public static final String ORDERS_USERS_ID = "orders.users_id";
	public static final String ORDERS_TOTAL_PAYMENT_AMOUNT = "orders.total_payment_amount";
	public static final String ORDERS_TOTAL_PRODUCTS_DISCOUNT_AMOUNT = "orders.total_products_discount_amount";
	public static final String ORDERS_TOTAL_PERSON_DISCOUNT_AMOUNT = "orders.total_person_discount_amount";
	public static final String ORDERS_TOTAL_DISCOUNT_AMOUNT = "orders.total_discount_amount";
	public static final String ORDERS_TOTAL_PAYMENT_WITH_DISCOUNT_AMOUNT = "orders.total_payment_with_discount_amount";
	public static final String ORDERS_DATE = "orders.date";
	public static final String ORDERS_STATUS_ID = "orders.order_statuses_id";

	public static final String PRODUCT_TYPES_ID = "product_types.id";
	public static final String PRODUCT_TYPES_NAME = "product_types.name";

	public static final String ORDER_PRODUCTS_ORDER_ID = "order_products.orders_id";
	public static final String ORDER_PRODUCTS_PRODUCT_TYPES_ID = "order_products.product_types_id";
	public static final String ORDER_PRODUCTS_PRODUCT_PETS_ID = "order_products.pets_id";
	public static final String ORDER_PRODUCTS_PRODUCT_FEEDS_AND_OTHER_ID = "order_products.feeds_and_other_id";

	public static final String VERIFICATE_CODES_ID = "verificate_codes.id";
	public static final String VERIFICATE_CODES_USER_ID = "verificate_codes.users_id";
	public static final String VERIFICATE_CODES_CODE = "verificate_сodes.code";
	public static final String VERIFICATE_CODES_STATUS = "verificate_codes.is_open";

	public static final String BANK_CARDS_ID = "bank_cards.id";
	public static final String BANK_CARDS_NUMBER = "bank_cards.number";
	public static final String BANK_CARDS_MONTH_END = "bank_cards.month_end";
	public static final String BANK_CARDS_YEAR_END = "bank_cards.year_end";
	public static final String BANK_CARDS_CVC = "bank_cards.cvc";
	public static final String BANK_CARDS_SUM = "bank_cards.sum";

	public static final String IDENTIFIER_LAST_INSERT_ID = "last_insert_id()";
	public static final String IDENTIFIER_COUNT_ROWS = "COUNT";
	public static final String IDENTIFIER_COUNT_ROWS_OF_USER_LOGINS = IDENTIFIER_COUNT_ROWS + "(" + USERS_LOGIN + ")";
	public static final String IDENTIFIER_COUNT_ROWS_OF_VERIFICATE_CODES_CODE = IDENTIFIER_COUNT_ROWS + "("
			+ VERIFICATE_CODES_CODE + ")";
	public static final String IDENTIFIER_COUNT_ROWS_OF_BANK_CARDS_ID = IDENTIFIER_COUNT_ROWS + "(" + BANK_CARDS_ID
			+ ")";
	public static final String IDENTIFIER_COUNT_ROWS_OF_PETS_IMAGE_PATH = IDENTIFIER_COUNT_ROWS + "(" + PETS_IMAGE_PATH
			+ ")";
	public static final String IDENTIFIER_COUNT_ROWS_OF_FEEDS_AND_OTHER_IMAGE_PATH = IDENTIFIER_COUNT_ROWS + "("
			+ FEEDS_AND_OTHER_IMAGE_PATH + ")";

	private ColumnName() {
	}
}