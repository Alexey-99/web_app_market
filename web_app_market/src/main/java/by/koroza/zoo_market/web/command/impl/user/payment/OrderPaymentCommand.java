package by.koroza.zoo_market.web.command.impl.user.payment;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.BACKET_WITH_PRODUCTS_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.ORDER_PAYMENT_FORM_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.SUCCESS_ORDER_PAYMENT_PAGE_PATH;
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

import java.util.HashMap;
import java.util.Map;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.validation.BankCardValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class OrderPaymentCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		session.removeAttribute(ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			if (user == null || user.getRole().getIdRole() == 0) {
				router = new Router(HOME_PAGE_PATH);
			} else {
				if (user.getRole().getIdRole() == 1) {
					router = new Router(HOME_PAGE_PATH);
				} else {
					Order order = (Order) session.getAttribute(ATTRIBUTE_ORDER);
					if (order == null || order.getProductsPets().size() + order.getOtherProducts().size() == 0) {
						router = new Router(BACKET_WITH_PRODUCTS_PAGE_PATH);
					} else {
						Map<String, String> mapInputExceptions = new HashMap<>();
						String sessionLocale = (String) request.getSession().getAttribute(ATTRIBUTE_SESSION_LOCALE);
						BankCard bankCard = createBankCardFromInputParameters(request, mapInputExceptions,
								sessionLocale);
						if (bankCard != null && mapInputExceptions.isEmpty()) {
							if (validationBankCard(mapInputExceptions, sessionLocale, bankCard, order)) {
								OrderServiceImpl.getInstance().addOrder(order, user.getId());

								ProductPetServiceImpl.getInstance().changeNumberOfUnitsProducts(order);
								ProductFeedsAndOtherServiceImpl.getInstance().changeNumberOfUnitsProducts(order);
								router = new Router(SUCCESS_ORDER_PAYMENT_PAGE_PATH);
							} else {
								session.setAttribute(ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
										mapInputExceptions);
								router = new Router(ORDER_PAYMENT_FORM_VALIDATED_PAGE_PATH);
							}
						} else {
							session.setAttribute(ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
									mapInputExceptions);
							router = new Router(ORDER_PAYMENT_FORM_VALIDATED_PAGE_PATH);
						}
					}
				}
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
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
		if (!BankCardValidation.validNumberBankCard(numberBankCard)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD,
						RU_MESSAGE_TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD + numberBankCard);
			} else if (sessionLocale.equals(ENGLISH)) {
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
		if (!BankCardValidation.validMonthAndYear(month, yaer)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_MONTH_YEAR,
						RU_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_ONE + month
								+ RU_MESSAGE_TYPY_INPUT_EXCEPTION_MONTH_YEAR_PART_TWO + yaer);
			} else if (sessionLocale.equals(ENGLISH)) {
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
		if (!BankCardValidation.validCVC(cvc)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_CVC, RU_MESSAGE_TYPY_INPUT_EXCEPTION_CVC + cvc);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_INPUT_EXCEPTION_CVC, EN_MESSAGE_TYPY_INPUT_EXCEPTION_CVC + cvc);
			}
		}
		return cvc;
	}

	private boolean validationBankCard(Map<String, String> mapInputExceptions, String sessionLocale, BankCard bankCard,
			Order order) throws ServiceException {
		return bankCard != null
				? isExistsBankCard(mapInputExceptions, sessionLocale, bankCard) && validSumBankCard(mapInputExceptions,
						sessionLocale, bankCard, order.getTotalPaymentWithDiscountAmount())
				: false;
	}

	private boolean isExistsBankCard(Map<String, String> mapInputExceptions, String sessionLocale, BankCard bankCard)
			throws ServiceException {
		boolean result = false;
		if (mapInputExceptions.isEmpty() && bankCard != null) {
			if (!BankCardValidation.isExistsBankCard(bankCard)) {
				if (sessionLocale.equals(RUSSIAN)) {
					mapInputExceptions.put(TYPY_EXCEPTION_BANK_CARD, RU_MESSAGE_TYPY_EXCEPTION_BANK_CARD);
				} else if (sessionLocale.equals(ENGLISH)) {
					mapInputExceptions.put(TYPY_EXCEPTION_BANK_CARD, EN_MESSAGE_TYPY_EXCEPTION_BANK_CARD);
				}
			} else {
				result = true;
			}
		}
		return result;
	}

	private boolean validSumBankCard(Map<String, String> mapInputExceptions, String sessionLocale, BankCard bankCard,
			double totalPaymentWithDiscountAmount) throws ServiceException {
		boolean result = false;
		if (!BankCardValidation.validSum(bankCard, totalPaymentWithDiscountAmount)) {
			if (sessionLocale.equals(RUSSIAN)) {
				mapInputExceptions.put(TYPY_EXCEPTION_SUM, RU_MESSAGE_TYPY_EXCEPTION_SUM);
			} else if (sessionLocale.equals(ENGLISH)) {
				mapInputExceptions.put(TYPY_EXCEPTION_SUM, EN_MESSAGE_TYPY_EXCEPTION_SUM);
			}
		} else {
			result = true;
		}
		return result;
	}
}