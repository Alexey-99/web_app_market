package by.koroza.zoo_market.web.command.impl.user.create;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_EXCEPTION_BANK_CARD;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_EXCEPTION_SUM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_CVC;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.EN_MESSAGE_TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_EXCEPTION_BANK_CARD;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_EXCEPTION_SUM;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_CVC;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_ONE;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_TWO;
import static by.koroza.zoo_market.web.command.name.exception.MessageInputException.RU_MESSAGE_TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_EXCEPTION_BANK_CARD;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_EXCEPTION_SUM;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_CVC;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_MONTH_YEAR;
import static by.koroza.zoo_market.web.command.name.exception.TypeInputExeception.TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD;
import static by.koroza.zoo_market.web.command.name.input.InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC;
import static by.koroza.zoo_market.web.command.name.input.InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH;
import static by.koroza.zoo_market.web.command.name.input.InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR;
import static by.koroza.zoo_market.web.command.name.input.InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.BACKET_WITH_PRODUCTS_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.ORDER_PAYMENT_FORM_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.SUCCESS_ORDER_PAYMENT_PAGE_PATH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.exception.ValidationException;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.service.sorting.SortingProducts;
import by.koroza.zoo_market.service.sorting.comparator.list.product.impl.id.SortProductsByIdAscendingComparatorImpl;
import by.koroza.zoo_market.service.validation.impl.bank.BankCardValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CreateOrderCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		session.removeAttribute(ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getIdRole() >= USER.getIdRole()) {
				Order order = (Order) session.getAttribute(ATTRIBUTE_ORDER);
				if (order != null && (order.getProductsPets().size() + order.getOtherProducts().size()) > 0) {
					Map<String, String> mapInputExceptions = new HashMap<>();
					String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
					BankCard bankCard = createBankCardFromInputParameters(request, mapInputExceptions, sessionLocale);
					if ((bankCard != null && mapInputExceptions.isEmpty())
							&& (validationBankCard(mapInputExceptions, sessionLocale, bankCard, order))) {
						@SuppressWarnings("unchecked")
						List<Pet> sortedPets = (List<Pet>) SortingProducts.getInstance().sortProductsList(
								order.getProductsPets(), new SortProductsByIdAscendingComparatorImpl());
						order.setProductsPets(sortedPets);
						Map<Integer, Boolean> haveProductPets = ProductPetServiceImpl.getInstance()
								.changeNumberOfUnitsProducts(order.getProductsPets());
						@SuppressWarnings("unchecked")
						List<FeedAndOther> sortedProductFeedAndOther = (List<FeedAndOther>) SortingProducts
								.getInstance().sortProductsList(order.getOtherProducts(),
										new SortProductsByIdAscendingComparatorImpl());
						order.setOtherProducts(sortedProductFeedAndOther);
						Map<Integer, Boolean> haveProductFeedAndOther = ProductFeedsAndOtherServiceImpl.getInstance()
								.changeNumberOfUnitsProducts(order.getOtherProducts());
						session.setAttribute(ATTRIBUTE_ORDER, order);
						session.setAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_PETS, haveProductPets);
						session.setAttribute(ATTRIBUTE_IS_HAVE_ORDER_PRODUCTS_FEED_AND_OTHER, haveProductFeedAndOther);
						router = new Router(SUCCESS_ORDER_PAYMENT_PAGE_PATH);
					} else {
						session.setAttribute(ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
								mapInputExceptions);
						router = new Router(ORDER_PAYMENT_FORM_VALIDATED_PAGE_PATH);
					}
				} else {
					router = new Router(BACKET_WITH_PRODUCTS_PAGE_PATH);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException | ValidationException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}

	private BankCard createBankCardFromInputParameters(HttpServletRequest request,
			Map<String, String> mapInputExceptions, String sessionLocale) {
		String numberBankCard = getInputParameterNumberBankCard(request, sessionLocale, mapInputExceptions);
		String[] monthYear = getInputParametersMonthYear(request, sessionLocale, mapInputExceptions);
		String month = monthYear[0];
		String yaer = monthYear[1];
		String cvc = getInputParameterCVC(request, sessionLocale, mapInputExceptions);
		return mapInputExceptions.isEmpty()
				? new BankCard.BankCardBuilder().setNumberCard(numberBankCard).setMonthEnd(Integer.parseInt(month))
						.setYearEnd(Integer.parseInt(yaer)).setCVC(Integer.parseInt(cvc)).build()
				: null;
	}

	private String getInputParameterNumberBankCard(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String numberBankCard = request.getParameter(PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD);
		if (!BankCardValidationImpl.getInstance().validNumberBankCard(numberBankCard)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD,
						RU_MESSAGE_TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD + numberBankCard);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD + numberBankCard);
			} else {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD + numberBankCard);
			}
		}
		return numberBankCard;
	}

	private String[] getInputParametersMonthYear(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String month = request.getParameter(PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH);
		String yaer = request.getParameter(PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR);
		if (!BankCardValidationImpl.getInstance().validMonthAndYear(month, yaer)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_MONTH_YEAR,
						RU_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_ONE + month
								+ RU_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_TWO + yaer);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_MONTH_YEAR,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_ONE + month
								+ EN_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_TWO + yaer);
			} else {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_MONTH_YEAR,
						EN_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_ONE + month
								+ EN_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_TWO + yaer);
			}
		}
		return new String[] { month, yaer };
	}

	private String getInputParameterCVC(HttpServletRequest request, String sessionLocale,
			Map<String, String> mapInputExceptions) {
		String cvc = request.getParameter(PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC);
		if (!BankCardValidationImpl.getInstance().validCVC(cvc)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_CVC, RU_MESSAGE_TYPY_INPUT_EXCEPTION_CVC + cvc);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_CVC, EN_MESSAGE_TYPY_INPUT_EXCEPTION_CVC + cvc);
			} else {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_CVC, EN_MESSAGE_TYPY_INPUT_EXCEPTION_CVC + cvc);
			}
		}
		return cvc;
	}

	private boolean validationBankCard(Map<String, String> mapInputExceptions, String sessionLocale, BankCard bankCard,
			Order order) throws ValidationException {
		return bankCard != null
				? isExistsBankCard(mapInputExceptions, sessionLocale, bankCard) && validSumBankCard(mapInputExceptions,
						sessionLocale, bankCard, order.getTotalPaymentWithDiscountAmount())
				: false;
	}

	private boolean isExistsBankCard(Map<String, String> mapInputExceptions, String sessionLocale, BankCard bankCard)
			throws ValidationException {
		boolean result = false;
		if (mapInputExceptions.isEmpty() && bankCard != null) {
			if (!BankCardValidationImpl.getInstance().isExistsBankCard(bankCard)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPY_EXCEPTION_BANK_CARD, RU_MESSAGE_TYPY_EXCEPTION_BANK_CARD);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPY_EXCEPTION_BANK_CARD, EN_MESSAGE_TYPY_EXCEPTION_BANK_CARD);
				} else {
					mapInputExceptions.put(TYPY_EXCEPTION_BANK_CARD, EN_MESSAGE_TYPY_EXCEPTION_BANK_CARD);
				}
			} else {
				result = true;
			}
		}
		return result;
	}

	private boolean validSumBankCard(Map<String, String> mapInputExceptions, String sessionLocale, BankCard bankCard,
			double totalPaymentWithDiscountAmount) throws ValidationException {
		boolean result = false;
		if (!BankCardValidationImpl.getInstance().validSum(bankCard, totalPaymentWithDiscountAmount)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_EXCEPTION_SUM, RU_MESSAGE_TYPY_EXCEPTION_SUM);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_EXCEPTION_SUM, EN_MESSAGE_TYPY_EXCEPTION_SUM);
			} else {
				mapInputExceptions.put(TYPY_EXCEPTION_SUM, EN_MESSAGE_TYPY_EXCEPTION_SUM);
			}
		} else {
			result = true;
		}
		return result;
	}
}