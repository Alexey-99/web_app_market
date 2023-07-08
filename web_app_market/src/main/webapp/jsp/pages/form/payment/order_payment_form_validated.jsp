<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.input.InputName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.exception.TypeInputExeception"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="${PagePathName.PAGE_CONTENT_PROPERTIES}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="basket_page.paymant_form.title" /></title>
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/items/payment_information_form.css" />
<!-- order = AttributeName.ATTRIBUTE_ORDER -->
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- order_payment_input_exception_type_and_message = AttributeName.ATTRIBUTE_ORDER_PAYMENT_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body>
	<header class="payment_information"
		style="min-height: 100vh; background: var(--color2)">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div
						class="position-fixed d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 payment_information_form">
						<div
							class="position-relative w-100 d-flex flex-column payment_information_form_inner">
							<div class="payment_information_form_top">
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										name="${CommandName.COMMAND_SHOW_BACKET_PAGE}" />
									<button class="close_btn border-0 bg-transparent" role="button">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
											width="25px" height="25px">
                        <path
												d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
									</button>
								</form>
								<h2 class="form_title text-center mb-3">
									<fmt:message key="basket_page.paymant_form.title" />
								</h2>
							</div>
							<form class=""
								action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
								method="get">
								<h6>
									<fmt:message
										key="basket_page.paymant_form.input.enter_number_bank_card" />
								</h6>
								<c:if
									test="${order_payment_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control is-invalid"
											id="basket_page.paymant_form.input.enter_number_bank_card"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											pattern="(\d{4}\s?){4}" required
											placeholder='<fmt:message key="basket_page.paymant_form.input.enter_number_bank_card"/>' />
										<label class="text-lowercase"
											for="basket_page.paymant_form.input.enter_number_bank_card">
											<fmt:message
												key="basket_page.paymant_form.input.enter_number_bank_card" />
										</label>
										<div class="invalid-feedback">
											${order_payment_input_exception_type_and_message.get(TypeInputExeception.TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD)}
										</div>
									</div>
								</c:if>
								<c:if
									test="${!order_payment_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_NUMBER_BANK_CARD)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control is-valid"
											id="basket_page.paymant_form.input.enter_number_bank_card"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											pattern="(\d{4}\s?){4}" required
											placeholder='<fmt:message key="basket_page.paymant_form.input.enter_number_bank_card"/>' />
										<label class="text-lowercase"
											for="basket_page.paymant_form.input.enter_number_bank_card">
											<fmt:message
												key="basket_page.paymant_form.input.enter_number_bank_card" />
										</label>
										<div class="valid-feedback">
											<fmt:message key="paymant_form.valid_feed_back" />
										</div>
									</div>
								</c:if>
								<div class="input-group mb-3">
									<div class="input_group_title">
										<h6>
											<fmt:message
												key="basket_page.paymant_form.input.enter_month_and_year_end_card" />
										</h6>
									</div>
									<c:if
										test="${order_payment_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_MONTH_YEAR)}">
										<div class="input_group_forms d-flex flex-row w-100">
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control is-invalid"
													id="basket_page.paymant_form.lable.month" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													required
													placeholder='<fmt:message key="basket_page.paymant_form.lable.month"/>' />
												<label class="text-lowercase"
													for="basket_page.paymant_form.lable.month"> <fmt:message
														key="basket_page.paymant_form.lable.month" />
												</label>
											</div>
											<span class="input-group-text">/</span>
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control is-invalid"
													id="basket_page.paymant_form.lable.year" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													required
													placeholder='<fmt:message key="basket_page.paymant_form.lable.year"/>' />
												<label class="text-lowercase"
													for="basket_page.paymant_form.lable.year"> <fmt:message
														key="basket_page.paymant_form.lable.year" />
												</label>
												<div class="invalid-feedback">
													${order_payment_input_exception_type_and_message.get(TypeInputExeception.TYPY_INPUT_EXCEPTION_MONTH_YEAR)}
												</div>
											</div>
										</div>
									</c:if>
									<c:if
										test="${!order_payment_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_MONTH_YEAR)}">
										<div class="input_group_forms d-flex flex-row w-100">
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control is-valid"
													id="basket_page.paymant_form.lable.month" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													required
													placeholder='<fmt:message key="basket_page.paymant_form.lable.month"/>' />
												<label class="text-lowercase"
													for="basket_page.paymant_form.lable.month"> <fmt:message
														key="basket_page.paymant_form.lable.month" />
												</label>
											</div>
											<span class="input-group-text">/</span>
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control is-valid"
													id="basket_page.paymant_form.lable.year" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													required
													placeholder='<fmt:message key="basket_page.paymant_form.lable.year"/>' />
												<label class="text-lowercase"
													for="basket_page.paymant_form.lable.year"><fmt:message
														key="basket_page.paymant_form.lable.year" /></label>
												<div class="valid-feedback">
													<fmt:message key="paymant_form.valid_feed_back" />
												</div>
											</div>
										</div>
									</c:if>
								</div>
								<h6>
									<fmt:message key="basket_page.paymant_form.input.enter_cvc" />
								</h6>
								<c:if
									test="${order_payment_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_CVC)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control is-invalid"
											id="basket_page.paymant_form.input.enter_cvc"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											pattern="\d{3}" required
											placeholder='<fmt:message key="basket_page.paymant_form.input.enter_cvc"/>' />
										<label class="text-lowercase"
											for="basket_page.paymant_form.input.enter_cvc"><fmt:message
												key="basket_page.paymant_form.input.enter_cvc" /></label>
										<div class="invalid-feedback">
											${order_payment_input_exception_type_and_message.get(TypeInputExeception.TYPY_INPUT_EXCEPTION_CVC)}
										</div>
									</div>
								</c:if>
								<c:if
									test="${!order_payment_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_CVC)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control is-valid"
											id="basket_page.paymant_form.input.enter_cvc"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											pattern="\d{3}" required
											placeholder='<fmt:message key="basket_page.paymant_form.input.enter_cvc"/>' />
										<label class="text-lowercase"
											for="basket_page.paymant_form.input.enter_cvc"><fmt:message
												key="basket_page.paymant_form.input.enter_cvc" /> </label>
										<div class="valid-feedback">
											<fmt:message key="paymant_form.valid_feed_back" />
										</div>
									</div>
								</c:if>
								<c:if
									test="${order_payment_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_EXCEPTION_BANK_CARD)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control d-none is-invalid" />
										<div class="invalid-feedback">
											${order_payment_input_exception_type_and_message.get(TypeInputExeception.TYPY_EXCEPTION_BANK_CARD)}
										</div>
									</div>
								</c:if>

								<c:if
									test="${order_payment_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_EXCEPTION_SUM)}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control d-none is-invalid" />
										<div class="invalid-feedback">
											${order_payment_input_exception_type_and_message.get(TypeInputExeception.TYPY_EXCEPTION_SUM)}
										</div>
									</div>
								</c:if>
								<div
									class="payment_information_form_fotter d-flex justify-content-end">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_ORDER_PAYMENT}" />
									<button class="btn payment_information_form_btn_submit"
										role="button">
										<fmt:message key="basket_page.paymant_form.ready" />
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/basket.js"></script>
</body>
</html>