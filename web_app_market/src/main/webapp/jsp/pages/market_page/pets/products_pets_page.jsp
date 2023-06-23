<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="pgn" uri="customtags"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.FilterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.LanguageName"%>
<%@page import="by.koroza.zoo_market.web.command.name.PagePathName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterValue"%>
<%@page import="by.koroza.zoo_market.web.command.name.ServletName"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.user.show.market.pet.ShowProductPetsIncludedFilterCommand"%>
<fmt:setLocale value="${AttributeName.ATTRIBUTE_SESSION_LOCALE}"
	scope="session" />
<fmt:setBundle
	basename="${PagePathName.PAGE_CONTENT_PROPERTIES}${locale}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="market_product_pets_page.title" /></title>
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/pages/products_page.css" />
<link rel="stylesheet" href="css/items/product_card.css" />
<link rel="stylesheet" href="css/items/toast.css" />
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- products_pets_filter_input_exception_type_and_message = AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
<!-- products_pets_filter_map = AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP -->
<!-- products_pets_filter = AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER -->
<!-- list_products_pets = AttributeName.ATTRIBUTE_LIST_PRODUCTS_PETS -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body class="body">

	<%@ include file="/jsp/items/header_block_header_top.jsp"%>

	<main class="products_pets mb-5 pb-5">
		<div class="container">
			<div class="row">
				<div class="col-1">
					<nav class="navbar bg-body-tertiary">
						<div class="container-fluid justify-content-center">
							<button class="navbar-toggler" type="button"
								data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
								aria-controls="offcanvasNavbar">
								<span class="navbar-toggler-icon"></span>
							</button>
							<div class="offcanvas offcanvas-start" tabindex="-1"
								id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
								<div class="offcanvas-header">
									<h5 class="offcanvas-title text-uppercase"
										id="offcanvasNavbarLabel">
										<fmt:message
											key="market_product_pets_page.filter.title.filter" />
									</h5>
									<button type="button" class="btn-close"
										data-bs-dismiss="offcanvas" aria-label="Закрыть"></button>
								</div>
								<c:if test="${locale == LanguageName.ENGLISH}">
									<div class="offcanvas-body">
										<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
											<li class="nav-item dropdown">
												<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
													<input type="hidden"
														name="${ParameterName.PARAMETER_NUMBER_PAGE}"
														value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" /> <input
														type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
														value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_OFF_FILTER_PAGE }" />
													<button class="btn form_submit mb-3" role="button">
														<fmt:message
															key="market_product_pets_page.filter.top_buttons.reset_filter_btn.reset_filter" />
													</button>
												</form> <c:if
													test="${products_pets_filter_input_exception_type_and_message.isEmpty() || products_pets_filter_input_exception_type_and_message == null}">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
														<div class="accordion accordion_form"
															id="accordionExample">
															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_TYPE_PET_EN) && products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET_EN).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_TYPE_PET"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_TYPE_PET">${FilterName.CHOOSE_TYPE_PET_EN}</button>
																	</h2>
																	<div id="collapse_CHOOSE_TYPE_PET"
																		class="accordion-collapse collapse show">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET_EN)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_TYPE_PET">
																						<input type="checkbox"
																						name="${InputName.INPUT_PET_TYPE}"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</c:if>

															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_BREED_PET_EN) && products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET_EN).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_BREED_PET"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_BREED_PET">${FilterName.CHOOSE_BREED_PET_EN }</button>
																	</h2>
																	<div id="collapse_CHOOSE_BREED_PET"
																		class="accordion-collapse collapse ">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET_EN)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_BREED_PET }">
																						<input type="checkbox"
																						name="${InputName.INPUT_PET_BREED }"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</c:if>

															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_VALUE_DISCOUNT_EN) && products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT_EN).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_VALUE_DISCOUNT_EN"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_VALUE_DISCOUNT_EN">${FilterName.CHOOSE_VALUE_DISCOUNT_EN }</button>
																	</h2>
																	<div id="collapse_CHOOSE_VALUE_DISCOUNT_EN"
																		class="accordion-collapse collapse">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT_EN)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_${FilterName.CHOOSE_VALUE_DISCOUNT_EN }">
																						<input type="checkbox"
																						name="${InputName.INPUT_PROMOTIONS}"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																			<div class="input-group mb-3 mt-3">
																				<span
																					class="input-group-text accordion_item_six_span">
																					<fmt:message
																						key="market_product_pets_page.filter.promotions_part.span_input.from" />
																				</span> <input type="text" class="form-control"
																					pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																					aria-label="Процент скидки"
																					name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																				<span
																					class="span_procent d-flex justify-content-center align-items-center">
																					<svg xmlns="http://www.w3.org/2000/svg" height="20"
																						viewBox="0 96 960 960" width="20">
																						<path
																							d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																				</span> <span
																					class="input-group-text accordion_item_six_span"><fmt:message
																						key="market_product_pets_page.filter.promotions_part.span_input.to" />
																				</span> <input type="text" class="form-control"
																					pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																					name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																				<span
																					class="span_procent d-flex justify-content-center align-items-center">
																					<svg xmlns="http://www.w3.org/2000/svg" height="20"
																						viewBox="0 96 960 960" width="20">
																						<path
																							d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>
															</c:if>

															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapseThree" aria-expanded="false"
																		aria-controls="collapseThree">
																		<fmt:message
																			key="market_product_pets_page.filter.price_part.title.price" />
																	</button>
																</h2>
																<div id="collapseThree"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<div class="input-group mb-3">
																			<span class="input-group-text"><fmt:message
																					key="market_product_pets_page.filter.price_part.span_input.from" />
																			</span> <input type="text" class="form-control"
																				pattern="[0-9]+(\.[0-9]{2})?"
																				placeHolder="00,00 руб"
																				name="${InputName.INPUT_MIN_PRICE_PET}"
																				aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																			<span class="input-group-text"><fmt:message
																					key="market_product_pets_page.filter.price_part.span_input.to" />
																			</span> <input type="text" pattern="[0-9]+(\.[0-9]{2})?"
																				class="form-control" placeHolder="00,00 руб"
																				name="${InputName.INPUT_MAX_PRICE_PET}"
																				aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																		</div>
																	</div>
																</div>
															</div>

															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapseFour" aria-expanded="false"
																		aria-controls="collapseFour">
																		<fmt:message
																			key="market_product_pets_page.filter.age_part.title.age" />
																	</button>
																</h2>
																<div id="collapseFour"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<div class="input-group">
																			<span
																				class="input-group-text accordion_item_four_span justify-content-center"
																				style="width: 40px"><fmt:message
																					key="market_product_pets_page.filter.age_part.span_input.from" />
																			</span> <input type="text" class="form-control"
																				pattern="[0-9]+" aria-label="Количество лет"
																				name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.year_or_years" />
																			</span> <input type="text" class="form-control"
																				pattern="[0-9]{1,2}" aria-label="Количество месяцев"
																				name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																			<span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.month_or_months" />
																				<br /> </span>
																		</div>

																		<div class="input-group mb-3">
																			<span
																				class="input-group-text accordion_item_four_span justify-content-center"
																				style="width: 40px"><fmt:message
																					key="market_product_pets_page.filter.age_part.span_input.to" />
																			</span> <input type="text" class="form-control"
																				pattern="[0-9]+" aria-label="Количество лет"
																				name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																			<input type="text" class="form-control"
																				pattern="[0-9]{1,2}" aria-label="Количество месяцев"
																				name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																			<span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.month_or_months" /></span>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<input type="hidden"
															name="${ParameterName.PARAMETER_NUMBER_PAGE}"
															value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" /> <input
															type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit"
															value='<fmt:message key="market_product_pets_page.filter.filter_footer.btn.search" />' />
													</form>
												</c:if> <c:if
													test="${!products_pets_filter_input_exception_type_and_message.isEmpty && products_pets_filter_input_exception_type_and_message != null}">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
														<div class="accordion accordion_form"
															id="accordionExample">
															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_TYPE_PET_EN) && products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET_EN).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_TYPE_PET"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_TYPE_PET">${FilterName.CHOOSE_TYPE_PET_EN}</button>
																	</h2>
																	<div id="collapse_CHOOSE_TYPE_PET"
																		class="accordion-collapse collapse show">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET_EN)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_TYPE_PET">
																						<input type="checkbox"
																						name="${InputName.INPUT_PET_TYPE}"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</c:if>

															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_BREED_PET_EN) && products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET_EN).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_BREED_PET"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_BREED_PET">${FilterName.CHOOSE_BREED_PET_EN }</button>
																	</h2>
																	<div id="collapse_CHOOSE_BREED_PET"
																		class="accordion-collapse collapse ">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET_EN)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_${FilterName.CHOOSE_BREED_PET_EN} }">
																						<input type="checkbox"
																						name="${InputName.INPUT_PET_BREED }"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</c:if>

															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_VALUE_DISCOUNT_EN) && products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT_EN).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_VALUE_DISCOUNT_EN"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_VALUE_DISCOUNT_EN">${FilterName.CHOOSE_VALUE_DISCOUNT_EN }</button>
																	</h2>
																	<div id="collapse_CHOOSE_VALUE_DISCOUNT_EN"
																		class="accordion-collapse collapse ">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT_EN)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_${FilterName.CHOOSE_VALUE_DISCOUNT_EN }">
																						<input type="checkbox"
																						name="${InputName.INPUT_PROMOTIONS}"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																			<c:if
																				test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}">
																				<div class="input-group mb-3 mt-3">
																					<span
																						class="input-group-text accordion_item_six_span"><fmt:message
																							key="market_product_pets_page.filter.promotions_part.span_input.from" />
																					</span> <input type="text" class="form-control is-invalid"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Процент скидки"
																						name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																					<span class="span_procent"><svg
																							class="span_procent_img"
																							xmlns="http://www.w3.org/2000/svg" height="20"
																							viewBox="0 96 960 960" width="20">
																							<path
																								d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																					</span> <span
																						class="input-group-text accordion_item_six_span"><fmt:message
																							key="market_product_pets_page.filter.promotions_part.span_input.to" /></span>
																					<input type="text" class="form-control is-invalid"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																						name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																					<span class="span_procent"> <svg
																							class="span_procent_img"
																							xmlns="http://www.w3.org/2000/svg" height="20"
																							viewBox="0 96 960 960" width="20">
																							<path
																								d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																					</span>
																					<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}</div>
																				</div>
																			</c:if>
																			<c:if
																				test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}">
																				<div class="input-group mb-3 mt-3">
																					<span
																						class="input-group-text accordion_item_six_span"><fmt:message
																							key="market_product_pets_page.filter.promotions_part.span_input.from" /></span>
																					<input type="text" class="form-control"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Процент скидки"
																						name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																					<span class="span_procent"> <svg
																							class="span_procent_img"
																							xmlns="http://www.w3.org/2000/svg" height="20"
																							viewBox="0 96 960 960" width="20">
																							<path
																								d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																					</span> <span
																						class="input-group-text accordion_item_six_span"><fmt:message
																							key="market_product_pets_page.filter.promotions_part.span_input.to" /></span>
																					<input type="text" class="form-control"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																						name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																					<span class="span_procent"> <svg
																							class="span_procent_img"
																							xmlns="http://www.w3.org/2000/svg" height="20"
																							viewBox="0 96 960 960" width="20">
																							<path
																								d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																					</span>
																				</div>
																			</c:if>
																		</div>
																	</div>
																</div>
															</c:if>

															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapseThree" aria-expanded="false"
																		aria-controls="collapseThree">
																		<fmt:message
																			key="market_product_pets_page.filter.price_part.title.price" />
																	</button>
																</h2>
																<div id="collapseThree"
																	class="accordion-collapse collapse">
																	<c:if
																		test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
																		<div class="accordion-body">
																			<div class="input-group mb-3">
																				<span class="input-group-text"><fmt:message
																						key="market_product_pets_page.filter.price_part.span_input.from" />
																				</span> <input type="text" class="form-control is-invalid"
																					pattern="[0-9]+(\.[0-9]{2})?"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MIN_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<span class="input-group-text"><fmt:message
																						key="market_product_pets_page.filter.price_part.span_input.to" />
																				</span> <input type="text" pattern="[0-9]+(\.[0-9]{2})?"
																					class="form-control is-invalid"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MAX_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_PRICE)}</div>
																			</div>
																		</div>
																	</c:if>
																	<c:if
																		test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
																		<div class="accordion-body">
																			<div class="input-group mb-3">
																				<span class="input-group-text"><fmt:message
																						key="market_product_pets_page.filter.price_part.span_input.from" /></span>
																				<input type="text" class="form-control"
																					pattern="[0-9]+(\.[0-9]{2})?"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MIN_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<span class="input-group-text"><fmt:message
																						key="market_product_pets_page.filter.price_part.span_input.to" />
																				</span> <input type="text" pattern="[0-9]+(\.[0-9]{2})?"
																					class="form-control" placeHolder="00,00 руб"
																					name="${InputName.INPUT_MAX_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																			</div>
																		</div>
																	</c:if>
																</div>
															</div>

															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapseFour" aria-expanded="false"
																		aria-controls="collapseFour">
																		<fmt:message
																			key="market_product_pets_page.filter.age_part.title.age" />
																	</button>
																</h2>
																<div id="collapseFour"
																	class="accordion-collapse collapse">
																	<c:if
																		test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}">
																		<div class="accordion-body">
																			<div class="input-group">
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.span_input.from" />
																				</span> <input type="text" class="form-control is-invalid"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.year_or_years" />
																				</span> <input type="text" class="form-control is-invalid"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.month_or_months" />
																					<br /> </span>
																			</div>

																			<div class="input-group mb-3">
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.span_input.to" /></span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.month_or_months" /></span>
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}</div>
																			</div>

																		</div>
																	</c:if>
																	<c:if
																		test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}">
																		<div class="accordion-body">
																			<div class="input-group">
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.span_input.from" />
																					от</span> <input type="text" class="form-control"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																				<input type="text" class="form-control"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.month_or_months" /><br />
																				</span>
																			</div>

																			<div class="input-group mb-3">
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.span_input.to" /></span>
																				<input type="text" class="form-control"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																				<input type="text" class="form-control"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.month_or_months" /></span>
																			</div>
																		</div>
																	</c:if>

																</div>
															</div>
														</div>
														<input type="hidden"
															name="${ParameterName.PARAMETER_NUMBER_PAGE}"
															value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" /> <input
															type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit"
															value='<fmt:message key="market_product_pets_page.filter.filter_footer.btn.search"/>' />
													</form>
												</c:if>
											</li>
										</ul>
									</div>
								</c:if>
								<c:if test="${locale == LanguageName.RUSSIAN}">
									<div class="offcanvas-body">
										<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
											<li class="nav-item dropdown">
												<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
													<input type="hidden"
														name="${ParameterName.PARAMETER_NUMBER_PAGE}"
														value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" /> <input
														type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
														value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_OFF_FILTER_PAGE }" />
													<button class="btn form_submit mb-3" role="button">
														<fmt:message
															key="market_product_pets_page.filter.top_buttons.reset_filter_btn.reset_filter" />
													</button>
												</form> <c:if
													test="${products_pets_filter_input_exception_type_and_message.isEmpty() || products_pets_filter_input_exception_type_and_message == null}">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
														<div class="accordion accordion_form"
															id="accordionExample">
															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_TYPE_PET_RUS) && products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET_RUS).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_TYPE_PET_RUS"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_TYPE_PET_RUS">${FilterName.CHOOSE_TYPE_PET_RUS}</button>
																	</h2>
																	<div id="collapse_CHOOSE_TYPE_PET_RUS"
																		class="accordion-collapse collapse show">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET_RUS)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_TYPE_PET">
																						<input type="checkbox"
																						name="${InputName.INPUT_PET_TYPE}"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</c:if>

															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_BREED_PET_RUS) && products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET_RUS).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_BREED_PET_RUS"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_BREED_PET_RUS">${FilterName.CHOOSE_BREED_PET_RUS }</button>
																	</h2>
																	<div id="collapse_CHOOSE_BREED_PET_RUS"
																		class="accordion-collapse collapse ">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET_RUS)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_BREED_PET_RUS }">
																						<input type="checkbox"
																						name="${InputName.INPUT_PET_BREED }"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</c:if>

															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_VALUE_DISCOUNT_RUS) && products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT_RUS).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_VALUE_DISCOUNT_RUS"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_VALUE_DISCOUNT_RUS">${FilterName.CHOOSE_VALUE_DISCOUNT_RUS }</button>
																	</h2>
																	<div id="collapse_CHOOSE_VALUE_DISCOUNT_RUS"
																		class="accordion-collapse collapse">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT_RUS)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_VALUE_DISCOUNT_RUS">
																						<input type="checkbox"
																						name="${InputName.INPUT_PROMOTIONS}"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																			<div class="input-group mb-3 mt-3">
																				<span
																					class="input-group-text accordion_item_six_span"><fmt:message
																						key="market_product_pets_page.filter.promotions_part.span_input.from" />
																				</span> <input type="text" class="form-control"
																					pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																					aria-label="Процент скидки"
																					name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																				<span class="span_procent"> <svg
																						class="span_procent_img"
																						xmlns="http://www.w3.org/2000/svg" height="20"
																						viewBox="0 96 960 960" width="20">
																						<path
																							d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																				</span> <span
																					class="input-group-text accordion_item_six_span"><fmt:message
																						key="market_product_pets_page.filter.promotions_part.span_input.to" />
																				</span> <input type="text" class="form-control"
																					pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																					name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																				<span class="span_procent"> <svg
																						class="span_procent_img"
																						xmlns="http://www.w3.org/2000/svg" height="20"
																						viewBox="0 96 960 960" width="20">
																						<path
																							d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>
															</c:if>

															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapseThree" aria-expanded="false"
																		aria-controls="collapseThree">
																		<fmt:message
																			key="market_product_pets_page.filter.price_part.title.price" />
																	</button>
																</h2>
																<div id="collapseThree"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<div class="input-group mb-3">
																			<span class="input-group-text"><fmt:message
																					key="market_product_pets_page.filter.price_part.span_input.from" /></span>
																			<input type="text" class="form-control"
																				pattern="[0-9]+(\.[0-9]{2})?"
																				placeHolder="00,00 руб"
																				name="${InputName.INPUT_MIN_PRICE_PET}"
																				aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																			<span class="input-group-text"><fmt:message
																					key="market_product_pets_page.filter.price_part.span_input.to" /></span>
																			<input type="text" pattern="[0-9]+(\.[0-9]{2})?"
																				class="form-control" placeHolder="00,00 руб"
																				name="${InputName.INPUT_MAX_PRICE_PET}"
																				aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																		</div>
																	</div>
																</div>
															</div>

															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapseFour" aria-expanded="false"
																		aria-controls="collapseFour">
																		<fmt:message
																			key="market_product_pets_page.filter.age_part.title.age" />
																	</button>
																</h2>
																<div id="collapseFour"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<div class="input-group">
																			<span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.span_input.from" /></span>
																			<input type="text" class="form-control"
																				pattern="[0-9]+" aria-label="Количество лет"
																				name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																			<input type="text" class="form-control"
																				pattern="[0-9]{1,2}" aria-label="Количество месяцев"
																				name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																			<span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.month_or_months" /><br />
																			</span>
																		</div>

																		<div class="input-group mb-3">
																			<span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.span_input.to" /></span>
																			<input type="text" class="form-control"
																				pattern="[0-9]+" aria-label="Количество лет"
																				name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																			<input type="text" class="form-control"
																				pattern="[0-9]{1,2}" aria-label="Количество месяцев"
																				name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																			<span
																				class="input-group-text accordion_item_four_span"><fmt:message
																					key="market_product_pets_page.filter.age_part.month_or_months" /></span>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<input type="hidden"
															name="${ParameterName.PARAMETER_NUMBER_PAGE}"
															value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" /> <input
															type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit"
															value='<fmt:message key="market_product_pets_page.filter.filter_footer.btn.search"/>' />
													</form>
												</c:if> <c:if
													test="${!products_pets_filter_input_exception_type_and_message.isEmpty && products_pets_filter_input_exception_type_and_message != null}">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
														<div class="accordion accordion_form"
															id="accordionExample">
															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_TYPE_PET_RUS) && products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET_RUS).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_TYPE_PET_RUS"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_TYPE_PET_RUS">${FilterName.CHOOSE_TYPE_PET_RUS}</button>
																	</h2>
																	<div id="collapse_CHOOSE_TYPE_PET_RUS"
																		class="accordion-collapse collapse show">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET_RUS)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_TYPE_PET_RUS">
																						<input type="checkbox"
																						name="${InputName.INPUT_PET_TYPE}"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</c:if>

															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_BREED_PET_RUS) && products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET_RUS).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_BREED_PET_RUS"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_BREED_PET_RUS">${FilterName.CHOOSE_BREED_PET_RUS }</button>
																	</h2>
																	<div id="collapse_CHOOSE_BREED_PET_RUS"
																		class="accordion-collapse collapse ">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET_RUS)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_BREED_PET_RUS">
																						<input type="checkbox"
																						name="${InputName.INPUT_PET_BREED }"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																		</div>
																	</div>
																</div>
															</c:if>

															<c:if
																test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_VALUE_DISCOUNT_RUS) && products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT_RUS).size() > 0 == true}">
																<div class="accordion-item">
																	<h2 class="accordion-header">
																		<button
																			class="accordion-button collapsed text-uppercase"
																			type="button" data-bs-toggle="collapse"
																			data-bs-target="#collapse_CHOOSE_VALUE_DISCOUNT_RUS"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_VALUE_DISCOUNT_RUS">${FilterName.CHOOSE_VALUE_DISCOUNT_RUS }</button>
																	</h2>
																	<div id="collapse_CHOOSE_VALUE_DISCOUNT_RUS"
																		class="accordion-collapse collapse ">
																		<div class="accordion-body">
																			<c:forEach
																				items="${products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT_RUS)}"
																				var="value" varStatus="innerStutus">
																				<label> <span
																					class="span_input span_input_CHOOSE_VALUE_DISCOUNT_RUS">
																						<input type="checkbox"
																						name="${InputName.INPUT_PROMOTIONS}"
																						value="${value}" /> ${value}
																				</span>
																				</label>
																				<br />
																			</c:forEach>
																			<c:if
																				test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}">
																				<div class="input-group mb-3 mt-3">
																					<span
																						class="input-group-text accordion_item_six_span"><fmt:message
																							key="market_product_pets_page.filter.promotions_part.span_input.from" /></span>
																					<input type="text" class="form-control is-invalid"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Процент скидки"
																						name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																					<span class="span_procent"> <svg
																							class="span_procent_img"
																							xmlns="http://www.w3.org/2000/svg" height="20"
																							viewBox="0 96 960 960" width="20">
																							<path
																								d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																					</span> <span
																						class="input-group-text accordion_item_six_span"><fmt:message
																							key="market_product_pets_page.filter.promotions_part.span_input.to" /></span>
																					<input type="text" class="form-control is-invalid"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																						name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																					<span class="span_procent"><svg
																							class="span_procent_img"
																							xmlns="http://www.w3.org/2000/svg" height="20"
																							viewBox="0 96 960 960" width="20">
																							<path
																								d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																					</span>
																				</div>
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}</div>
																			</c:if>
																			<c:if
																				test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}">
																				<div class="input-group mb-3 mt-3">
																					<span
																						class="input-group-text accordion_item_six_span"><fmt:message
																							key="market_product_pets_page.filter.promotions_part.span_input.from" /></span>
																					<input type="text" class="form-control"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Процент скидки"
																						name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																					<span class="span_procent"> <svg
																							class="span_procent_img"
																							xmlns="http://www.w3.org/2000/svg" height="20"
																							viewBox="0 96 960 960" width="20">
																							<path
																								d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																					</span> <span
																						class="input-group-text accordion_item_six_span"><fmt:message
																							key="market_product_pets_page.filter.promotions_part.span_input.to" /></span>
																					<input type="text" class="form-control"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																						name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																					<span class="span_procent"> <svg
																							class="span_procent_img"
																							xmlns="http://www.w3.org/2000/svg" height="20"
																							viewBox="0 96 960 960" width="20">
																							<path
																								d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" /></svg>
																					</span>
																				</div>
																			</c:if>
																		</div>
																	</div>
																</div>
															</c:if>

															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapseThree" aria-expanded="false"
																		aria-controls="collapseThree">
																		<fmt:message
																			key="market_product_pets_page.filter.price_part.title.price" />
																	</button>
																</h2>
																<div id="collapseThree"
																	class="accordion-collapse collapse">
																	<c:if
																		test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
																		<div class="accordion-body">
																			<div class="input-group mb-3">
																				<span class="input-group-text"><fmt:message
																						key="market_product_pets_page.filter.price_part.span_input.from" /></span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]+(\.[0-9]{2})?"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MIN_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<span class="input-group-text"><fmt:message
																						key="market_product_pets_page.filter.price_part.span_input.to" />
																				</span> <input type="text" pattern="[0-9]+(\.[0-9]{2})?"
																					class="form-control is-invalid"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MAX_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_PRICE)}</div>
																			</div>
																		</div>
																	</c:if>
																	<c:if
																		test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
																		<div class="accordion-body">
																			<div class="input-group mb-3">
																				<span class="input-group-text"><fmt:message
																						key="market_product_pets_page.filter.price_part.span_input.from" />
																				</span> <input type="text" class="form-control"
																					pattern="[0-9]+(\.[0-9]{2})?"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MIN_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<span class="input-group-text"><fmt:message
																						key="market_product_pets_page.filter.price_part.span_input.to" />
																				</span> <input type="text" pattern="[0-9]+(\.[0-9]{2})?"
																					class="form-control" placeHolder="00,00 руб"
																					name="${InputName.INPUT_MAX_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																			</div>
																		</div>
																	</c:if>
																</div>
															</div>

															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapseFour" aria-expanded="false"
																		aria-controls="collapseFour">
																		<fmt:message
																			key="market_product_pets_page.filter.age_part.title.age" />
																	</button>
																</h2>
																<div id="collapseFour"
																	class="accordion-collapse collapse">
																	<c:if
																		test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}">
																		<div class="accordion-body">
																			<div class="input-group">
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.span_input.from" />
																				</span> <input type="text" class="form-control is-invalid"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.month_or_months" /><br />
																				</span>
																			</div>

																			<div class="input-group mb-3">
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.span_input.to" /></span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.month_or_months" /></span>
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}</div>
																			</div>

																		</div>
																	</c:if>
																	<c:if
																		test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}">
																		<div class="accordion-body">
																			<div class="input-group">
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.span_input.from" /></span>
																				<input type="text" class="form-control"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																				<input type="text" class="form-control"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.month_or_months" /><br />
																				</span>
																			</div>

																			<div class="input-group mb-3">
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.span_input.to" /></span>
																				<input type="text" class="form-control"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.year_or_years" /></span>
																				<input type="text" class="form-control"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span"><fmt:message
																						key="market_product_pets_page.filter.age_part.month_or_months" /></span>
																			</div>
																		</div>
																	</c:if>

																</div>
															</div>
														</div>
														<input type="hidden"
															name="${ParameterName.PARAMETER_NUMBER_PAGE}"
															value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" /> <input
															type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit"
															value='<fmt:message key="market_product_pets_page.filter.filter_footer.btn.search"/>' />
													</form>
												</c:if>
											</li>
										</ul>
									</div>
								</c:if>
							</div>
						</div>
					</nav>
				</div>

				<div class="col-11">
					<div class="">
						<c:if
							test="${products_pets_filter_input_exception_type_and_message == null || products_pets_filter_input_exception_type_and_message.isEmpty()}">
							<c:if test="${locale == LanguageName.ENGLISH}">
								<h6>${products_pets_filter.toString()}</h6>
							</c:if>
							<c:if test="${locale == LanguageName.RUSSIAN}">
								<h6>${products_pets_filter.toStringRus()}</h6>
							</c:if>
						</c:if>
						<c:if
							test="${products_pets_filter_input_exception_type_and_message != null && !products_pets_filter_input_exception_type_and_message.isEmpty()}">
							<h6>
								<fmt:message
									key="market_product_pets_page.filter.exception_in_filter" />
								<c:forEach
									items="${products_pets_filter_input_exception_type_and_message.entrySet()}"
									var="exception">${exception.getValue()}</c:forEach>
							</h6>
						</c:if>
					</div>
					<c:if test="${list_products_pets.size() > 0}">
						<pgn:pagination_pet
							numberPage="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_NUMBER_PAGE)}"
							maxCountProductsOnPage="4" />
					</c:if>
					<c:if test="${list_products_pets.size() == 0}">
						<div class="logo d-flex justify-content-center">
							<img class="img logo_img"
								src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${ImagePath.LOGO_PNG_IMAGE_PATH}"
								alt="logo" />
						</div>
						<div class="text">
							<h4 class="text-center">
								<fmt:message
									key="market_product_pets_page.if_not_found_product.text.there_are_currently_no_products_found_according_to_the_selected_criteria" />
							</h4>
							<h5 class="text-center">
								<fmt:message
									key="market_product_pets_page.if_not_found_product.navigate" />
							</h5>
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
								<button
									class="text_link bg-transparent border-top-0 border-end-0 border-start-0"
									role="button">
									<h5>
										<fmt:message
											key="market_product_pets_page.if_not_found_product.to_home_page" />
									</h5>
								</button>
							</form>

							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="${CommandName.COMMAND_SHOW_PRODUCT_FEED_AND_OTHER_OFF_FILTER_PAGE}">
								<button
									class="text_link bg-transparent border-top-0 border-end-0 border-start-0"
									role="button">
									<h5>
										<fmt:message
											key="market_product_pets_page.if_not_found_product.to_market_product_feeds_and_other_page" />
									</h5>
								</button>
							</form>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</main>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/products_pages.js"></script>

	<script>
		showToast(${list_products_pets.stream().map(pet -> pet.getId()).toList()});
    </script>
</body>
</html>