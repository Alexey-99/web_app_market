<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.OrderPaymentCommand"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/items/payment_information_form.css" />
<!-- order = AttributeName.ATTRIBUTE_ORDER -->
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- order_payment_input_exception_type_and_message = AttributeName.ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
</head>
<body>
	<section class="payment_information"
		style="min-height: 100vh; background: var(--color2)">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div
						class="position-fixed d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 payment_information_form">
						<div
							class="position-relative w-100 d-flex flex-column payment_information_form_inner">
							<div class="payment_information_form_top">
								<form action="Controller">
									<input type="hidden" name="command"
										name="${CommandName.COMMAND_SHOW_BACKET_PAGE}" />
									<button class="close_btn border-0 bg-transparent" role="button">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
											width="25px" height="25px">
                        <path
												d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
									</button>
								</form>
								<h2 class="form_title text-center mb-3">Данные для оплаты</h2>
							</div>
							<form class="" action="Controller">
								<h6>Введите номер банковской карты</h6>
								<c:if
									test="${order_payment_input_exception_type_and_message.containsKey(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control is-invalid"
											id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											placeholder="1234 5678 8765 4321" pattern="(\d{4}\s?){4}"
											required /> <label class="text-lowercase"
											for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}">Введите
											номер карты</label>
										<div class="invalid-feedback">${order_payment_input_exception_type_and_message.get(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD)}</div>
									</div>
								</c:if>
								<c:if
									test="${!order_payment_input_exception_type_and_message.containsKey(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control is-valid"
											id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											 placeholder="1234 5678 8765 4321"
											pattern="(\d{4}\s?){4}" required /> <label
											class="text-lowercase"
											for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}">Введите
											номер карты</label>
										<div class="valid-feedback">Все хорошо!</div>
									</div>
								</c:if>
								<div class="input-group mb-3">
									<div class="input_group_title">
										<h6>Введите месяц и год окончания работы карты</h6>
									</div>
									<c:if
										test="${order_payment_input_exception_type_and_message.containsKey(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_MONTH_YEAR)}">
										<div class="input_group_forms d-flex flex-row w-100">
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control is-invalid"
													id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													placeholder="месяц" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													required /> <label class="text-lowercase"
													for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}">месяц</label>
											</div>
											<span class="input-group-text">/</span>
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control is-invalid"
													id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													placeholder="год" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													required /> <label class="text-lowercase"
													for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}">год</label>
												<div class="invalid-feedback">${order_payment_input_exception_type_and_message.get(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_MONTH_YEAR)}</div>
											</div>
										</div>
									</c:if>
									<c:if
										test="${!order_payment_input_exception_type_and_message.containsKey(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_MONTH_YEAR)}">
										<div class="input_group_forms d-flex flex-row w-100">
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control is-valid"
													id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													placeholder="месяц" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													required /> <label class="text-lowercase"
													for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}">месяц</label>
											</div>
											<span class="input-group-text">/</span>
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control is-valid"
													id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													placeholder="год" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													required /> <label class="text-lowercase"
													for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}">год</label>
												<div class="valid-feedback">Все хорошо!</div>
											</div>
										</div>
									</c:if>
								</div>
								<h6>Введите CVC (3 цифры)</h6>
								<c:if
									test="${order_payment_input_exception_type_and_message.containsKey(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_CVC)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control is-invalid"
											id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											placeholder="123" pattern="\d{3}" required /> <label
											class="text-lowercase"
											for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}">Введите
											CVC</label>
										<div class="invalid-feedback">${order_payment_input_exception_type_and_message.get(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_CVC)}</div>
									</div>
								</c:if>
								<c:if
									test="${!order_payment_input_exception_type_and_message.containsKey(OrderPaymentCommand.TYPY_INPUT_EXCEPTION_CVC)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control is-valid"
											id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											 placeholder="123" pattern="\d{3}" required /> <label
											class="text-lowercase"
											for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}">Введите
											CVC</label>
										<div class="valid-feedback">Все хорошо!</div>
									</div>
								</c:if>
								<c:if
									test="${order_payment_input_exception_type_and_message.containsKey(OrderPaymentCommand.TYPY_EXCEPTION_BANK_CARD)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control d-none is-invalid" />
										<div class="invalid-feedback">${order_payment_input_exception_type_and_message.get(OrderPaymentCommand.TYPY_EXCEPTION_BANK_CARD)}</div>
									</div>
								</c:if>

								<c:if
									test="${order_payment_input_exception_type_and_message.containsKey(OrderPaymentCommand.TYPY_EXCEPTION_SUM)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control d-none is-invalid" />
										<div class="invalid-feedback">${order_payment_input_exception_type_and_message.get(OrderPaymentCommand.TYPY_EXCEPTION_SUM)}</div>
									</div>
								</c:if>
								<div
									class="payment_information_form_fotter d-flex justify-content-end">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_ORDER_PAYMENT}" />
									<button class="btn payment_information_form_btn_submit"
										role="button">готово</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/basket.js"></script>
</body>
</html>