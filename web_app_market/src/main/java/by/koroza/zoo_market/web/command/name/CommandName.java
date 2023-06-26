package by.koroza.zoo_market.web.command.name;

import static by.koroza.zoo_market.web.command.CommandType.SHOW_HOME_PAGE;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_BACKET_PAGE;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_PERSONAL_ACCOUNT_PERSON_INFORMATION_PAGE;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_PRODUCT_PETS_OFF_FILTER;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_PRODUCT_PETS_INCLUDED_FILTER;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_MARKET_PAGE_PRODUCT_PETS_BY_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_PRODUCT_FEEDS_AND_OTHER_OFF_FILTER;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_PRODUCT_FEEDS_AND_OTHER_INCLUDED_FILTER;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_MARKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.CommandType.REGISTRATION_USER;
import static by.koroza.zoo_market.web.command.CommandType.VERIFICATION_REGISTRATION_INFORMATION;
import static by.koroza.zoo_market.web.command.CommandType.CONFIMATION_EMAIL;
import static by.koroza.zoo_market.web.command.CommandType.SHOW_CONFIMATION_EMAIL_FORM;
import static by.koroza.zoo_market.web.command.CommandType.SEND_ONE_MORE_TIME_CONFIMATION_EMAIL_CODE;
import static by.koroza.zoo_market.web.command.CommandType.SIGN_IN_PERSON_ACCOUNT;
import static by.koroza.zoo_market.web.command.CommandType.SIGN_OUT_PERSONAL_ACCOUNT;
import static by.koroza.zoo_market.web.command.CommandType.CHANGE_PERSON_INFORMATION;
import static by.koroza.zoo_market.web.command.CommandType.CHANGE_LOGIN_AND_PASSWORD;
import static by.koroza.zoo_market.web.command.CommandType.ORDER_PAYMENT;
import static by.koroza.zoo_market.web.command.CommandType.SET_ENGLISH_LOCALE;
import static by.koroza.zoo_market.web.command.CommandType.SET_RUSSIAN_LOCALE;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_CREATE_PET_PRODUCT;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_ADD_NEW_PRODUCT_PET;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_CREATE_PET_PRODUCT_FORM;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_CHANGE_PET_PRODUCT;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_UPDATE_CHANGED_PRODUCT_PET;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_CHANGE_PET_PRODUCT_FORM;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_CREATE_FEED_AND_OTHER_PRODUCT;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_ADD_NEW_PRODUCT_FEED_AND_OTHER;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_CREATE_FEED_AND_OTHER_PRODUCT_FORM;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_CHANGE_FEED_AND_OTHER_PRODUCT;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_UPDATE_CHANGED_PRODUCT_FEED_AND_OTHER;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_CHANGE_FEED_AND_OTHER_PRODUCT_FORM;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_DESCENDING;
import static by.koroza.zoo_market.web.command.CommandType.ADMIN_PAGE_CHANGE_USER_STATUS;

public class CommandName {
	public static final String COMMAND_SHOW_HOME_PAGE = SHOW_HOME_PAGE.toString().toLowerCase();

	public static final String COMMAND_SHOW_BACKET_PAGE = SHOW_BACKET_PAGE.toString().toLowerCase();

	public static final String COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE = SHOW_PERSONAL_ACCOUNT_PERSON_INFORMATION_PAGE
			.toString().toLowerCase();
	public static final String COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE = SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE
			.toString().toLowerCase();
	public static final String COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE = SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE.toString()
			.toLowerCase();

	public static final String COMMAND_SHOW_PRODUCT_PETS_OFF_FILTER_PAGE = SHOW_PRODUCT_PETS_OFF_FILTER.toString()
			.toLowerCase();
	public static final String COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE = SHOW_PRODUCT_PETS_INCLUDED_FILTER
			.toString().toLowerCase();
	public static final String COMMAND_SHOW_MAKET_PAGE_PRODUCT_PETS_BY_NUMBER_PAGE = SHOW_MARKET_PAGE_PRODUCT_PETS_BY_NUMBER_PAGE
			.toString().toLowerCase();

	public static final String COMMAND_SHOW_PRODUCT_FEED_AND_OTHER_OFF_FILTER_PAGE = SHOW_PRODUCT_FEEDS_AND_OTHER_OFF_FILTER
			.toString().toLowerCase();
	public static final String COMMAND_SHOW_PRODUCT_FEEDS_AND_OTHER_INCLUDED_FILTER_PAGE = SHOW_PRODUCT_FEEDS_AND_OTHER_INCLUDED_FILTER
			.toString().toLowerCase();
	public static final String COMMAND_SHOW_MAKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE = SHOW_MARKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE
			.toString().toLowerCase();

	public static final String COMMAND_REGISTRATION_USER = REGISTRATION_USER.toString().toLowerCase();
	public static final String COMMAND_VERIFICATION_REGISTRATION_INFORMATION = VERIFICATION_REGISTRATION_INFORMATION
			.toString().toLowerCase();

	public static final String COMMAND_CONFIRMATION_EMAIL = CONFIMATION_EMAIL.toString().toLowerCase();
	public static final String COMMAND_SHOW_CONFIRMATION_EMAIL_FORN = SHOW_CONFIMATION_EMAIL_FORM.toString()
			.toLowerCase();
	public static final String COMMAND_SEND_ONE_MORE_TIME_CONFIMATION_EMAIL_CODE = SEND_ONE_MORE_TIME_CONFIMATION_EMAIL_CODE
			.toString().toLowerCase();

	public static final String COMMAND_SIGN_IN_PERSON_ACCOUNT = SIGN_IN_PERSON_ACCOUNT.toString().toLowerCase();
	public static final String COMMAND_SIGN_OUT_PERSONAL_ACCOUNT = SIGN_OUT_PERSONAL_ACCOUNT.toString().toLowerCase();

	public static final String COMMAND_CHANGING_PERSON_INFORMATION = CHANGE_PERSON_INFORMATION.toString().toLowerCase();
	public static final String COMMAND_CHANGING_LOGIN_AND_PASSWORD = CHANGE_LOGIN_AND_PASSWORD.toString().toLowerCase();

	public static final String COMMAND_ORDER_PAYMENT = ORDER_PAYMENT.toString().toLowerCase();

	public static final String COMMAND_SET_ENGLISH_LOCALE = SET_ENGLISH_LOCALE.toString().toLowerCase();
	public static final String COMMAND_SET_RUSSIAN_LOCALE = SET_RUSSIAN_LOCALE.toString().toLowerCase();

	/* ADMIN part */
	public static final String COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER
			.toString().toLowerCase();

	/* create product pet */
	public static final String COMMAND_ADMIN_PAGE_CREATE_PET_PRODUCT = ADMIN_PAGE_CREATE_PET_PRODUCT.toString()
			.toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_ADD_NEW_PET_PRODUCT = ADMIN_PAGE_ADD_NEW_PRODUCT_PET.toString()
			.toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_CREATE_PET_PRODUCT_FORM = ADMIN_PAGE_SHOW_CREATE_PET_PRODUCT_FORM
			.toString().toLowerCase();
	/* change product pet */
	public static final String COMMAND_ADMIN_PAGE_CHANGE_PET_PRODUCT = ADMIN_PAGE_CHANGE_PET_PRODUCT.toString()
			.toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_UPDATE_CHANGED_PET_PRODUCT = ADMIN_PAGE_UPDATE_CHANGED_PRODUCT_PET
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_CHANGE_PET_PRODUCT_FORM = ADMIN_PAGE_SHOW_CHANGE_PET_PRODUCT_FORM
			.toString().toLowerCase();

	/* create product feeds and other */
	public static final String COMMAND_ADMIN_PAGE_CREATE_FEED_AND_OTHER_PRODUCT = ADMIN_PAGE_CREATE_FEED_AND_OTHER_PRODUCT
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_ADD_NEW_FEED_AND_OTHER_PRODUCT = ADMIN_PAGE_ADD_NEW_PRODUCT_FEED_AND_OTHER
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_CREATE_FEED_AND_OTHER_PRODUCT_FORM = ADMIN_PAGE_SHOW_CREATE_FEED_AND_OTHER_PRODUCT_FORM
			.toString().toLowerCase();
	/* change product feeds and other */
	public static final String COMMAND_ADMIN_PAGE_CHANGE_FEED_AND_OTHER_PRODUCT = ADMIN_PAGE_CHANGE_FEED_AND_OTHER_PRODUCT
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_UPDATE_CHANGED_FEED_AND_OTHER_PRODUCT = ADMIN_PAGE_UPDATE_CHANGED_PRODUCT_FEED_AND_OTHER
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_CHANGE_FEED_AND_OTHER_PRODUCT_FORM = ADMIN_PAGE_SHOW_CHANGE_FEED_AND_OTHER_PRODUCT_FORM
			.toString().toLowerCase();

	/* SORTING PRODUCTS */
	/* by id */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_DESCENDING
			.toString().toLowerCase();
	/* by product type */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_DESCENDING
			.toString().toLowerCase();
	/* by product brand */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_DESCENDING
			.toString().toLowerCase();
	/* by product description */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_DESCENDING
			.toString().toLowerCase();
	/* by product types pets */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_DESCENDING
			.toString().toLowerCase();
	/* by product pet type */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_DESCENDING
			.toString().toLowerCase();
	/* by product pet breed */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_DESCENDING
			.toString().toLowerCase();
	/* by product pet birth date */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_DESCENDING
			.toString().toLowerCase();
	/* by product price */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_DESCENDING
			.toString().toLowerCase();
	/* by product discount */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_DESCENDING
			.toString().toLowerCase();
	/* by product total price */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_DESCENDING
			.toString().toLowerCase();
	/* by number of units */
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING
			.toString().toLowerCase();
	public static final String COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_DESCENDING = ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_DESCENDING
			.toString().toLowerCase();
	/* CHANGE_USER_STATUS */
	public static final String COMMAND_ADMIN_PAGE_CHANGE_USER_STATUS = ADMIN_PAGE_CHANGE_USER_STATUS.toString()
			.toLowerCase();

	private CommandName() {
	}
}