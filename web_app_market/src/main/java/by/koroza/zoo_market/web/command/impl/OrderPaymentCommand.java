package by.koroza.zoo_market.web.command.impl;

import static by.koroza.zoo_market.web.command.name.AttributeName.*;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;

import java.util.HashMap;
import java.util.Map;

import static by.koroza.zoo_market.web.command.name.PagePathName.*;
import static by.koroza.zoo_market.web.command.name.InputName.*;

import by.koroza.zoo_market.model.entity.bank.BankCard;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.impl.OrderServiceImpl;
import by.koroza.zoo_market.service.impl.ProductFeedsAndOtherServiceImpl;
import by.koroza.zoo_market.service.impl.ProductPetServiceImpl;
import by.koroza.zoo_market.validation.BankCardValidation;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class OrderPaymentCommand implements Command {
	public static final String TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD = TypeInputException.NUMBER_BANK_CARD.toString();
	public static final String TYPY_INPUT_EXCEPTION_MONTH_YEAR = TypeInputException.MONTH_YEAR.toString();
	public static final String TYPY_INPUT_EXCEPTION_CVC = TypeInputException.CVC.toString();
	public static final String TYPY_EXCEPTION_BANK_CARD = TypeInputException.BANK_CARD.toString();
	public static final String TYPY_EXCEPTION_SUM = TypeInputException.SUM.toString();

	private enum TypeInputException {
		NUMBER_BANK_CARD, MONTH_YEAR, CVC, BANK_CARD, SUM;
	}

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
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
						String numberBankCard = request
								.getParameter(PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD);
						if (!BankCardValidation.validNumberBankCard(numberBankCard)) {
							mapInputExceptions.put(TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD,
									"Вы ввели номер банковской карты не корректно. Ваш ввод: " + numberBankCard);
						}
						String month = request.getParameter(PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH);
						String yaer = request.getParameter(PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR);
						if (!BankCardValidation.validMonthAndYear(month, yaer)) {
							mapInputExceptions.put(TYPY_INPUT_EXCEPTION_MONTH_YEAR,
									"Вы ввели номер мсяц или год не корректно. Ваш ввод: месяц = " + month + ", год = "
											+ yaer);
						}
						String cvc = request.getParameter(PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC);
						if (!BankCardValidation.validCVC(cvc)) {
							mapInputExceptions.put(TYPY_INPUT_EXCEPTION_CVC,
									"Вы ввели cvc не корректно. Ваш ввод: " + cvc);
						}
						BankCard bankCard = null;
						if (mapInputExceptions.isEmpty()) {
							bankCard = new BankCard.BankCardBuilder().setNumberCard(numberBankCard)
									.setMonthEnd(Integer.parseInt(month)).setYearEnd(Integer.parseInt(yaer))
									.setCvs(Integer.parseInt(cvc)).build();
							if (!BankCardValidation.isExistsBankCard(bankCard)) {
								mapInputExceptions.put(TYPY_EXCEPTION_BANK_CARD,
										"Данной банковской карты не существует.");
							}
						}
						if (mapInputExceptions.isEmpty()
								&& !BankCardValidation.validSum(bankCard, order.getTotalPaymentWithDiscountAmount())) {
							mapInputExceptions.put(TYPY_EXCEPTION_SUM, "Не достаточно средств на карте");
						}
						if (mapInputExceptions.isEmpty()) {
							OrderServiceImpl.getInstance().addOrder(order, user.getId());
							ProductPetServiceImpl.getInstance().changeNumberOfUnitsProducts(order);
							ProductFeedsAndOtherServiceImpl.getInstance().changeNumberOfUnitsProducts(order);
							router = new Router(SUCCESS_ORDER_PAYMENT_PAGE_PATH);
						} else {
							session.setAttribute(ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE,
									mapInputExceptions);
							router = new Router(ORDER_PAYMENT_FORM_VALIDATED_PAGE_PATH);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}
}