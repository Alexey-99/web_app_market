<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.FilterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.LanguageName"%>
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
													<div class="accordion accordion_form" id="accordionExample">
														<c:if
															test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_TYPE_PET) && products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET).size() > 0 == true}">
															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapse_CHOOSE_TYPE_PET"
																		aria-expanded="false"
																		aria-controls="collapse_CHOOSE_TYPE_PET">${FilterName.CHOOSE_TYPE_PET}</button>
																</h2>
																<div id="collapse_CHOOSE_TYPE_PET"
																	class="accordion-collapse collapse show">
																	<div class="accordion-body">
																		<c:forEach
																			items="${products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET)}"
																			var="value" varStatus="innerStutus">
																			<label> <span
																				class="span_input span_input_CHOOSE_TYPE_PET">
																					<input type="checkbox"
																					name="${InputName.INPUT_PET_TYPE}" value="${value}" />
																					${value}
																			</span>
																			</label>
																			<br />
																		</c:forEach>
																	</div>
																</div>
															</div>
														</c:if>

														<c:if
															test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_BREED_PET) && products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET).size() > 0 == true}">
															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapse_CHOOSE_BREED_PET"
																		aria-expanded="false"
																		aria-controls="collapse_CHOOSE_BREED_PET">${FilterName.CHOOSE_BREED_PET }</button>
																</h2>
																<div id="collapse_CHOOSE_BREED_PET"
																	class="accordion-collapse collapse ">
																	<div class="accordion-body">
																		<c:forEach
																			items="${products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET)}"
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
															test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_VALUE_DISCOUNT) && products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT).size() > 0 == true}">
															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapse_CHOOSE_VALUE_DISCOUNT"
																		aria-expanded="false"
																		aria-controls="collapse_CHOOSE_VALUE_DISCOUNT">${FilterName.CHOOSE_VALUE_DISCOUNT }</button>
																</h2>
																<div id="collapse_CHOOSE_VALUE_DISCOUNT"
																	class="accordion-collapse collapse">
																	<div class="accordion-body">
																		<c:forEach
																			items="${products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT)}"
																			var="value" varStatus="innerStutus">
																			<label> <span
																				class="span_input span_input_${FilterName.CHOOSE_VALUE_DISCOUNT }">
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
																			pattern="[0-9]+(\.[0-9]{2})?" placeHolder="00,00 руб"
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
																			name="${InputName.INPUT_MIN_NUMBER_MONTHS_PET}" /> <span
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
																			name="${InputName.INPUT_MAX_NUMBER_MONTHS_PET}" /> <span
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
													<div class="accordion accordion_form" id="accordionExample">
														<c:if
															test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_TYPE_PET) && products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET).size() > 0 == true}">
															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapse_CHOOSE_TYPE_PET"
																		aria-expanded="false"
																		aria-controls="collapse_CHOOSE_TYPE_PET">${FilterName.CHOOSE_TYPE_PET}</button>
																</h2>
																<div id="collapse_CHOOSE_TYPE_PET"
																	class="accordion-collapse collapse show">
																	<div class="accordion-body">
																		<c:forEach
																			items="${products_pets_filter_map.get(FilterName.CHOOSE_TYPE_PET)}"
																			var="value" varStatus="innerStutus">
																			<label> <span
																				class="span_input span_input_CHOOSE_TYPE_PET">
																					<input type="checkbox"
																					name="${InputName.INPUT_PET_TYPE}" value="${value}" />
																					${value}
																			</span>
																			</label>
																			<br />
																		</c:forEach>
																	</div>
																</div>
															</div>
														</c:if>

														<c:if
															test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_BREED_PET) && products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET).size() > 0 == true}">
															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapse_CHOOSE_BREED_PET"
																		aria-expanded="false"
																		aria-controls="collapse_CHOOSE_BREED_PET">${FilterName.CHOOSE_BREED_PET }</button>
																</h2>
																<div id="collapse_CHOOSE_BREED_PET"
																	class="accordion-collapse collapse ">
																	<div class="accordion-body">
																		<c:forEach
																			items="${products_pets_filter_map.get(FilterName.CHOOSE_BREED_PET)}"
																			var="value" varStatus="innerStutus">
																			<label> <span
																				class="span_input span_input_${FilterName.CHOOSE_BREED_PET} }">
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
															test="${products_pets_filter_map.containsKey(FilterName.CHOOSE_VALUE_DISCOUNT) && products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT).size() > 0 == true}">
															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapse_CHOOSE_VALUE_DISCOUNT"
																		aria-expanded="false"
																		aria-controls="collapse_CHOOSE_VALUE_DISCOUNT">${FilterName.CHOOSE_VALUE_DISCOUNT }</button>
																</h2>
																<div id="collapse_CHOOSE_VALUE_DISCOUNT"
																	class="accordion-collapse collapse ">
																	<div class="accordion-body">
																		<c:forEach
																			items="${products_pets_filter_map.get(FilterName.CHOOSE_VALUE_DISCOUNT)}"
																			var="value" varStatus="innerStutus">
																			<label> <span
																				class="span_input span_input_${FilterName.CHOOSE_VALUE_DISCOUNT }">
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
																				name="${InputName.INPUT_MIN_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span">года
																				(лет)</span> <input type="text"
																				class="form-control is-invalid" pattern="[0-9]{1,2}"
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
																				name="${InputName.INPUT_MAX_NUMBER_YEARS_PET}" /> <span
																				class="input-group-text accordion_item_four_span">года
																				(лет)</span> <input type="text"
																				class="form-control is-invalid" pattern="[0-9]{1,2}"
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
							</div>
						</div>
					</nav>
				</div>

				<div class="col-11">
					<c:if test="${locale == LanguageName.ENGLISH}">
						<h6>${products_pets_filter.toString()}</h6>
					</c:if>
					<c:if test="${locale == LanguageName.RUSSIAN}">
						<h6>${products_pets_filter.toStringRus()}</h6>
					</c:if>
					<c:if test="${list_products_pets.size() > 0}">
						<div
							class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xxl-4 g-4 mb-4">
							<c:forEach items="${list_products_pets}" var="pet">
								<div class="col card_product">
									<div class="card h-100 card_product_inner">
										<img src="img/logo.svg" class="card-img-top card_img"
											alt="..." />
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
												test="${AttributeName.ATTRIBUTE_USER != null && user.getRole().getIdRole() >= 2}">
												<div class="row body_btns w-100">
													<div class="col-12 d-flex justify-content-center body_btn">
														<input type="hidden" id="productId" value="${pet.getId()}" />
														<button class="w-100 h-100 body_btn_input"
															id="liveToastBtn${pet.getId()}" type="button"
															onclick="addProductPet(${pet.getId()})">в
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
										<div class="toast-body${pet.getId()}">Вы добавили
											следующий товар в корзину: ${pet.getDescription()}</div>
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