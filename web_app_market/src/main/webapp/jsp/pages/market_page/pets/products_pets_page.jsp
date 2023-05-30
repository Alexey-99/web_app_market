<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.FilterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.LanguageName"%>
<%@page import="by.koroza.zoo_market.web.command.name.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.show.ShowProductPetsIncludedFilterCommand"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>products_pets.jsp</title>
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
										id="offcanvasNavbarLabel">фильтр</h5>
									<button type="button" class="btn-close"
										data-bs-dismiss="offcanvas" aria-label="Закрыть"></button>
								</div>
								<c:if test="${locale == LanguageName.ENGLISH}">
									<div class="offcanvas-body">
										<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
											<li class="nav-item dropdown">
												<form action="Controller">
													<input type="hidden" name="command"
														value="show_product_pets_off_filter" />
													<button class="btn form_submit mb-3" role="button">Сбросить
														фильтр</button>
												</form> <c:if
													test="${products_pets_filter_input_exception_type_and_message.isEmpty() || products_pets_filter_input_exception_type_and_message == null}">
													<form action="Controller">
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
																			data-bs-target="#collapse_CHOOSE_VALUE_DISCOUNT"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_VALUE_DISCOUNT">${FilterName.CHOOSE_VALUE_DISCOUNT_EN }</button>
																	</h2>
																	<div id="collapse_CHOOSE_VALUE_DISCOUNT"
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
																					class="input-group-text accordion_item_six_span">от</span>
																				<input type="text" class="form-control"
																					pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																					aria-label="Процент скидки"
																					name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																				<span class="span_procent"> <img
																					class="span_procent_img" src="img/percent.svg"
																					alt="percent.svg" />
																				</span> <span
																					class="input-group-text accordion_item_six_span">до</span>
																				<input type="text" class="form-control"
																					pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																					name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																				<span class="span_procent"> <img
																					class="span_procent_img" src="img/percent.svg"
																					alt="percent.svg" />
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
																		aria-controls="collapseThree">Цена</button>
																</h2>
																<div id="collapseThree"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<div class="input-group mb-3">
																			<span class="input-group-text">от</span> <input
																				type="text" class="form-control"
																				pattern="[0-9]+(\.[0-9]{2})?"
																				placeHolder="00,00 руб"
																				name="${InputName.INPUT_MIN_PRICE_PET}"
																				aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																			<span class="input-group-text">до</span> <input
																				type="text" pattern="[0-9]+(\.[0-9]{2})?"
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
																		aria-controls="collapseFour">Возрост</button>
																</h2>
																<div id="collapseFour"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<div class="input-group">
																			<span
																				class="input-group-text accordion_item_four_span">от</span>
																			<input type="text" class="form-control"
																				pattern="[0-9]+" aria-label="Количество лет"
																				name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span">года
																				(лет)</span> <input type="text" class="form-control"
																				pattern="[0-9]{1,2}" aria-label="Количество месяцев"
																				name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																			<span
																				class="input-group-text accordion_item_four_span">месяца
																				(ев)<br />
																			</span>
																		</div>

																		<div class="input-group mb-3">
																			<span
																				class="input-group-text accordion_item_four_span">до</span>
																			<input type="text" class="form-control"
																				pattern="[0-9]+" aria-label="Количество лет"
																				name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span">года
																				(лет)</span> <input type="text" class="form-control"
																				pattern="[0-9]{1,2}" aria-label="Количество месяцев"
																				name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																			<span
																				class="input-group-text accordion_item_four_span">месяца
																				(ев)</span>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<input type="hidden" name="command"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit" value="Поиск" />
													</form>
												</c:if> <c:if
													test="${!products_pets_filter_input_exception_type_and_message.isEmpty && products_pets_filter_input_exception_type_and_message != null}">
													<form action="Controller">
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
																			data-bs-target="#collapse_CHOOSE_VALUE_DISCOUNT"
																			aria-expanded="false"
																			aria-controls="collapse_CHOOSE_VALUE_DISCOUNT">${FilterName.CHOOSE_VALUE_DISCOUNT_EN }</button>
																	</h2>
																	<div id="collapse_CHOOSE_VALUE_DISCOUNT"
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
																						class="input-group-text accordion_item_six_span">от</span>
																					<input type="text" class="form-control is-invalid"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Процент скидки"
																						name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																					<span class="span_procent"> <img
																						class="span_procent_img" src="img/percent.svg"
																						alt="percent.svg" />
																					</span> <span
																						class="input-group-text accordion_item_six_span">до</span>
																					<input type="text" class="form-control is-invalid"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																						name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																					<span class="span_procent"> <img
																						class="span_procent_img" src="img/percent.svg"
																						alt="percent.svg" />
																					</span>
																				</div>
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}</div>
																			</c:if>
																			<c:if
																				test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}">
																				<div class="input-group mb-3 mt-3">
																					<span
																						class="input-group-text accordion_item_six_span">от</span>
																					<input type="text" class="form-control"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Процент скидки"
																						name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																					<span class="span_procent"> <img
																						class="span_procent_img" src="img/percent.svg"
																						alt="percent.svg" />
																					</span> <span
																						class="input-group-text accordion_item_six_span">до</span>
																					<input type="text" class="form-control"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																						name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																					<span class="span_procent"> <img
																						class="span_procent_img" src="img/percent.svg"
																						alt="percent.svg" />
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
																		aria-controls="collapseThree">Цена</button>
																</h2>
																<div id="collapseThree"
																	class="accordion-collapse collapse">
																	<c:if
																		test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
																		<div class="accordion-body">
																			<div class="input-group mb-3">
																				<span class="input-group-text">от</span> <input
																					type="text" class="form-control is-invalid"
																					pattern="[0-9]+(\.[0-9]{2})?"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MIN_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<span class="input-group-text">до</span> <input
																					type="text" pattern="[0-9]+(\.[0-9]{2})?"
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
																				<span class="input-group-text">от</span> <input
																					type="text" class="form-control"
																					pattern="[0-9]+(\.[0-9]{2})?"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MIN_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<span class="input-group-text">до</span> <input
																					type="text" pattern="[0-9]+(\.[0-9]{2})?"
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
																		aria-controls="collapseFour">Возрост</button>
																</h2>
																<div id="collapseFour"
																	class="accordion-collapse collapse">
																	<c:if
																		test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}">
																		<div class="accordion-body">
																			<div class="input-group">
																				<span
																					class="input-group-text accordion_item_four_span">от</span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">года
																					(лет)</span> <input type="text"
																					class="form-control is-invalid"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">месяца
																					(ев)<br />
																				</span>
																			</div>

																			<div class="input-group mb-3">
																				<span
																					class="input-group-text accordion_item_four_span">до</span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">года
																					(лет)</span> <input type="text"
																					class="form-control is-invalid"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">месяца
																					(ев)</span>
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}</div>
																			</div>

																		</div>
																	</c:if>
																	<c:if
																		test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}">
																		<div class="accordion-body">
																			<div class="input-group">
																				<span
																					class="input-group-text accordion_item_four_span">от</span>
																				<input type="text" class="form-control"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">года
																					(лет)</span> <input type="text" class="form-control"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">месяца
																					(ев)<br />
																				</span>
																			</div>

																			<div class="input-group mb-3">
																				<span
																					class="input-group-text accordion_item_four_span">до</span>
																				<input type="text" class="form-control"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">года
																					(лет)</span> <input type="text" class="form-control"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">месяца
																					(ев)</span>
																			</div>
																		</div>
																	</c:if>

																</div>
															</div>
														</div>

														<input type="hidden" name="command"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit" value="Поиск" />
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
												<form action="Controller">
													<input type="hidden" name="command"
														value="show_product_pets_off_filter" />
													<button class="btn form_submit mb-3" role="button">Сбросить
														фильтр</button>
												</form> <c:if
													test="${products_pets_filter_input_exception_type_and_message.isEmpty() || products_pets_filter_input_exception_type_and_message == null}">
													<form action="Controller">
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
																					class="input-group-text accordion_item_six_span">от</span>
																				<input type="text" class="form-control"
																					pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																					aria-label="Процент скидки"
																					name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																				<span class="span_procent"> <img
																					class="span_procent_img" src="img/percent.svg"
																					alt="percent.svg" />
																				</span> <span
																					class="input-group-text accordion_item_six_span">до</span>
																				<input type="text" class="form-control"
																					pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																					name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																				<span class="span_procent"> <img
																					class="span_procent_img" src="img/percent.svg"
																					alt="percent.svg" />
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
																		aria-controls="collapseThree">Цена</button>
																</h2>
																<div id="collapseThree"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<div class="input-group mb-3">
																			<span class="input-group-text">от</span> <input
																				type="text" class="form-control"
																				pattern="[0-9]+(\.[0-9]{2})?"
																				placeHolder="00,00 руб"
																				name="${InputName.INPUT_MIN_PRICE_PET}"
																				aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																			<span class="input-group-text">до</span> <input
																				type="text" pattern="[0-9]+(\.[0-9]{2})?"
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
																		aria-controls="collapseFour">Возрост</button>
																</h2>
																<div id="collapseFour"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<div class="input-group">
																			<span
																				class="input-group-text accordion_item_four_span">от</span>
																			<input type="text" class="form-control"
																				pattern="[0-9]+" aria-label="Количество лет"
																				name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span">года
																				(лет)</span> <input type="text" class="form-control"
																				pattern="[0-9]{1,2}" aria-label="Количество месяцев"
																				name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																			<span
																				class="input-group-text accordion_item_four_span">месяца
																				(ев)<br />
																			</span>
																		</div>

																		<div class="input-group mb-3">
																			<span
																				class="input-group-text accordion_item_four_span">до</span>
																			<input type="text" class="form-control"
																				pattern="[0-9]+" aria-label="Количество лет"
																				name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span">года
																				(лет)</span> <input type="text" class="form-control"
																				pattern="[0-9]{1,2}" aria-label="Количество месяцев"
																				name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																			<span
																				class="input-group-text accordion_item_four_span">месяца
																				(ев)</span>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<input type="hidden" name="command"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit" value="Поиск" />
													</form>
												</c:if> <c:if
													test="${!products_pets_filter_input_exception_type_and_message.isEmpty && products_pets_filter_input_exception_type_and_message != null}">
													<form action="Controller">
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
																	<div id="collapse_CHOOSE_VALUE_DISCOUNT"
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
																						class="input-group-text accordion_item_six_span">от</span>
																					<input type="text" class="form-control is-invalid"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Процент скидки"
																						name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																					<span class="span_procent"> <img
																						class="span_procent_img" src="img/percent.svg"
																						alt="percent.svg" />
																					</span> <span
																						class="input-group-text accordion_item_six_span">до</span>
																					<input type="text" class="form-control is-invalid"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																						name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																					<span class="span_procent"> <img
																						class="span_procent_img" src="img/percent.svg"
																						alt="percent.svg" />
																					</span>
																				</div>
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}</div>
																			</c:if>
																			<c:if
																				test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_DESCOUNT)}">
																				<div class="input-group mb-3 mt-3">
																					<span
																						class="input-group-text accordion_item_six_span">от</span>
																					<input type="text" class="form-control"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Процент скидки"
																						name="${InputName.INPUT_MIN_PROCENT_PROMOTIONS}" />
																					<span class="span_procent"> <img
																						class="span_procent_img" src="img/percent.svg"
																						alt="percent.svg" />
																					</span> <span
																						class="input-group-text accordion_item_six_span">до</span>
																					<input type="text" class="form-control"
																						pattern="10{2}|[0-9]{0,2}(\.[0-9]{0,2})?"
																						aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)"
																						name="${InputName.INPUT_MAX_PROCENT_PROMOTIONS }" />
																					<span class="span_procent"> <img
																						class="span_procent_img" src="img/percent.svg"
																						alt="percent.svg" />
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
																		aria-controls="collapseThree">Цена</button>
																</h2>
																<div id="collapseThree"
																	class="accordion-collapse collapse">
																	<c:if
																		test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
																		<div class="accordion-body">
																			<div class="input-group mb-3">
																				<span class="input-group-text">от</span> <input
																					type="text" class="form-control is-invalid"
																					pattern="[0-9]+(\.[0-9]{2})?"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MIN_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<span class="input-group-text">до</span> <input
																					type="text" pattern="[0-9]+(\.[0-9]{2})?"
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
																				<span class="input-group-text">от</span> <input
																					type="text" class="form-control"
																					pattern="[0-9]+(\.[0-9]{2})?"
																					placeHolder="00,00 руб"
																					name="${InputName.INPUT_MIN_PRICE_PET}"
																					aria-label="Сумма в рублях (с точкой и двумя десятичными знаками)" />
																				<span class="input-group-text">до</span> <input
																					type="text" pattern="[0-9]+(\.[0-9]{2})?"
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
																		aria-controls="collapseFour">Возрост</button>
																</h2>
																<div id="collapseFour"
																	class="accordion-collapse collapse">
																	<c:if
																		test="${products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}">
																		<div class="accordion-body">
																			<div class="input-group">
																				<span
																					class="input-group-text accordion_item_four_span">от</span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">года
																					(лет)</span> <input type="text"
																					class="form-control is-invalid"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">месяца
																					(ев)<br />
																				</span>
																			</div>

																			<div class="input-group mb-3">
																				<span
																					class="input-group-text accordion_item_four_span">до</span>
																				<input type="text" class="form-control is-invalid"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">года
																					(лет)</span> <input type="text"
																					class="form-control is-invalid"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">месяца
																					(ев)</span>
																				<div class="invalid-feedback">${products_pets_filter_input_exception_type_and_message.get(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}</div>
																			</div>

																		</div>
																	</c:if>
																	<c:if
																		test="${!products_pets_filter_input_exception_type_and_message.containsKey(ShowProductPetsIncludedFilterCommand.INPUT_EXCEPTION_TYPE_YEAR_MONTH)}">
																		<div class="accordion-body">
																			<div class="input-group">
																				<span
																					class="input-group-text accordion_item_four_span">от</span>
																				<input type="text" class="form-control"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">года
																					(лет)</span> <input type="text" class="form-control"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">месяца
																					(ев)<br />
																				</span>
																			</div>

																			<div class="input-group mb-3">
																				<span
																					class="input-group-text accordion_item_four_span">до</span>
																				<input type="text" class="form-control"
																					pattern="[0-9]+" aria-label="Количество лет"
																					name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">года
																					(лет)</span> <input type="text" class="form-control"
																					pattern="[0-9]{1,2}"
																					aria-label="Количество месяцев"
																					name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" />
																				<span
																					class="input-group-text accordion_item_four_span">месяца
																					(ев)</span>
																			</div>
																		</div>
																	</c:if>

																</div>
															</div>
														</div>

														<input type="hidden" name="command"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit" value="Поиск" />
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
					<c:if test="${locale == LanguageName.ENGLISH}">
						<c:if
							test="${products_pets_filter_input_exception_type_and_message == null || products_pets_filter_input_exception_type_and_message.isEmpty()}">
							<h6>${products_pets_filter.toString()}</h6>
						</c:if>
						<c:if
							test="${products_pets_filter_input_exception_type_and_message != null && !products_pets_filter_input_exception_type_and_message.isEmpty()}">
							<h6>
								Mistake in filter:
								<c:forEach
									items="${products_pets_filter_input_exception_type_and_message.entrySet()}"
									var="exception">${exception.getValue()}</c:forEach>
							</h6>
						</c:if>
					</c:if>
					<c:if test="${locale == LanguageName.RUSSIAN}">
						<c:if
							test="${products_pets_filter_input_exception_type_and_message == null || products_pets_filter_input_exception_type_and_message.isEmpty()}">
							<h6>${products_pets_filter.toString()}</h6>
						</c:if>
						<c:if
							test="${products_pets_filter_input_exception_type_and_message != null && !products_pets_filter_input_exception_type_and_message.isEmpty()}">
							<h6>
								Ошибка в фильтре:
								<c:forEach
									items="${products_pets_filter_input_exception_type_and_message.entrySet()}"
									var="exception">${exception.getValue()}</c:forEach>
							</h6>
						</c:if>
					</c:if>
					<c:if test="${list_products_pets.size() > 0}">
						<div
							class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xxl-4 g-4 mb-4">
							<c:forEach items="${list_products_pets}" var="pet"
								varStatus="status">
								<div class="col card_product">
									<div class="card h-100 card_product_inner">
										<div class="card-img-top card_img"
											style="border: 1px solid var(--bs-card-border-color);">
											<c:if
												test="${pet.getImageFile() != null && pet.getImageFile().getName() != null && pet.getImageFile().getBytes() != null}">
												<img class=""
													src='<c:url value="C:\Users\Евгений\Desktop\img\product_images\product"/>'
													alt="" style="width: 100%; height: 100%">
											</c:if>

											<c:if
												test="${pet.getImageFile() == null || pet.getImageFile().getName() == null || pet.getImageFile().getBytes() == null}">
												<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
													width="100%" height="100%" viewBox="0 0 64 64">
<path
														d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
											</c:if>
										</div>
										<div
											class="card-body d-flex flex-column justify-content-between">
											<ul class="discription_top">
												<li>ID: p-${pet.getId()}</li>
												<li>Specie: ${pet.getSpecie()}</li>
												<li>breed: ${pet.getBreed()}</li>
												<li>date birthday: ${pet.getBirthDate()}</li>
											</ul>
											<ul class="discription_botton">
												<li>price: ${pet.getPrice()}</li>
												<li>Discount: ${pet.getDiscount()}<img
													class="discription_botton_discont_procent_img"
													src="img/percent.svg" alt="percent.svg" /></li>
												<li>TotalPrice: ${pet.getTotalPrice()}</li>
											</ul>
											<c:if
												test="${AttributeName.ATTRIBUTE_USER != null && user.getRole().getIdRole() >= 2 && user.isVerificatedEmail() == true}">
												<div class="row body_btns w-100">
													<div class="col-12 d-flex justify-content-center body_btn">
														<input type="hidden" id="productId" value="${pet.getId()}" />
														<button class="w-100 h-100 body_btn_input"
															id="liveToastBtn${pet.getId()}" type="button"
															onclick="addProductPet(${pet.getId()}, ${status.getIndex()})">в
															карзину</button>
													</div>
												</div>
											</c:if>
										</div>
										<div class="card-footer">
											<small class="text-body-secondary">Последнее
												обновление ${pet.getUpdateDateTime().toString()} назад</small>
										</div>
									</div>
								</div>
								<div class="toast-container position-fixed bottom-0 end-0 p-3">
									<div id="liveToast${pet.getId()}" class="toast" role="alert"
										aria-live="assertive" aria-atomic="true">
										<div class="toast-header">
											<img src="img/logo.svg" class="rounded me-2 toast_logo"
												alt="logo.svg"> <strong class="me-auto">Zoo
												ковчег</strong>
											<button type="button" class="btn-close"
												data-bs-dismiss="toast" aria-label="Закрыть"></button>
										</div>
										<div class="toast-body${pet.getId()}">Вы добавили товар</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<nav aria-label="Пример навигации по страницам">
							<ul
								class="pagination d-flex justify-content-center align-items-center">
								<li class="page-item"><a class="page-link" href="#">Предыдущая</a>
								</li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">Следующая</a>
								</li>
							</ul>
						</nav>
					</c:if>
					<c:if test="${list_products_pets.size() == 0}">
						<div class="logo d-flex justify-content-center">
							<img class="img logo_img" src="img/logo.svg" alt="logo.svg" />
						</div>
						<div class="text">
							<h4 class="text-center">На данный момент товаров по
								выбранным не найдено</h4>
							<h5 class="text-center">Перейдите</h5>
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
								<button
									class="text_link bg-transparent border-top-0 border-end-0 border-start-0"
									role="button">
									<h5>на главную страницу</h5>
								</button>
							</form>

							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_PRODUCT_FEED_AND_OTHER_OFF_FILTER_PAGE}">
								<button
									class="text_link bg-transparent border-top-0 border-end-0 border-start-0"
									role="button">
									<h5>на страницу с товарами для питомцев</h5>
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
	<script src="js/products_pages.js"></script>

	<script>
		showToast(${list_products_pets.stream().map(pet -> pet.getId()).toList()});
    </script>
</body>
</html>