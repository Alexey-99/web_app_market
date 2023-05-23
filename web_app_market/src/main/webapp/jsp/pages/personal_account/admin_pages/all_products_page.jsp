<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.UserRole"%>
<%@page import="by.koroza.zoo_market.web.command.name.LanguageName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.ProductType"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/items/main_header.css" />
<link rel="stylesheet" href="css/pages/personal_account.css" />
<link rel="stylesheet" href="css/pages/products_page.css" />
<link rel="stylesheet" href="css/items/product_card.css" />
<title>Insert title here</title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- map_product_pet_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_PET_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- map_product_feeds_and_other_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT -->
</head>
<body>

	<%@ include file="/jsp/items/header_block_header_top.jsp"%>
	<main class="mb-5" style="min-height: 53vh">
		<div class="container" style="max-width: 1500px">
			<c:if test="${user != null}">
				<div class="row">
					<div class="col-1">
						<nav class="navbar bg-body-tertiary">
							<div class="container-fluid justify-content-center">
								<button class="navbar-toggler" type="button"
									data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
									aria-controls="offcanvasNavbar">
									<svg fill="#000000" height="20px" width="20px" version="1.1"
										id="Layer_1" xmlns="http://www.w3.org/2000/svg"
										xmlns:xlink="http://www.w3.org/1999/xlink"
										viewBox="0 0 512 512" xml:space="preserve">
<g>
	<g>
		<path
											d="M8.959,0v81.89l201.666,201.666V512l90.75-60.5V283.556L503.041,81.89V0H8.959z M271.125,435.31l-30.25,20.167V292.416
			h30.25V435.31z M279.985,262.166h-47.969L60.6,90.75h390.802L279.985,262.166z M472.791,60.5H39.209V30.25h433.582V60.5z" />
	</g>
</g>
</svg>
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
														<input type="hidden" name="command" value="" />
														<button class="btn form_submit mb-3" role="button">
															Сбросить фильтр</button>
													</form>
													<form action="Controller">
														<div class="accordion accordion_form"
															id="accordionExample">
															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapse_CHOOSE_TYPE_PET"
																		aria-expanded="false"
																		aria-controls="collapse_CHOOSE_TYPE_PET">Тип
																		продукта</button>
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
																					name="${InputName.INPUT_PET_TYPE}" value="${value}" />
																					${value}
																			</span>
																			</label>
																			<br />
																		</c:forEach>
																		<label> <span
																			class="span_input span_input_CHOOSE_TYPE_PET text-lowercase">
																				<input type="checkbox"
																				name="${InputName.INPUT_PET_TYPE}" value="${value}" />
																				Pet
																		</span>
																		</label> <br /> <label> <span
																			class="span_input span_input_CHOOSE_TYPE_PET text-lowercase">
																				<input type="checkbox"
																				name="${InputName.INPUT_PET_TYPE}" value="${value}" />
																				Feed_and_other
																		</span>
																		</label>
																	</div>
																</div>
															</div>
														</div>

														<input type="hidden" name="command"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit" value="Поиск" />
													</form>
												</li>
											</ul>
										</div>
									</c:if>
									<c:if test="${locale == LanguageName.RUSSIAN}">
										<div class="offcanvas-body">
											<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
												<li class="nav-item dropdown">
													<form action="Controller">
														<input type="hidden" name="command" value="" />
														<button class="btn form_submit mb-3" role="button">
															Сбросить фильтр</button>
													</form>
													<form action="Controller">
														<div class="accordion accordion_form"
															id="accordionExample">
															<div class="accordion-item">
																<h2 class="accordion-header">
																	<button
																		class="accordion-button collapsed text-uppercase"
																		type="button" data-bs-toggle="collapse"
																		data-bs-target="#collapse_CHOOSE_TYPE_PET"
																		aria-expanded="false"
																		aria-controls="collapse_CHOOSE_TYPE_PET">Тип
																		продукта</button>
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
																					name="${InputName.INPUT_PET_TYPE}" value="${value}" />
																					${value}
																			</span>
																			</label>
																			<br />
																		</c:forEach>
																		<label> <span
																			class="span_input span_input_CHOOSE_TYPE_PET text-lowercase">
																				<input type="checkbox"
																				name="${InputName.INPUT_PET_TYPE}" value="${value}" />
																				Pet
																		</span>
																		</label> <br /> <label> <span
																			class="span_input span_input_CHOOSE_TYPE_PET text-lowercase">
																				<input type="checkbox"
																				name="${InputName.INPUT_PET_TYPE}" value="${value}" />
																				Feed_and_other
																		</span>
																		</label>
																	</div>
																</div>
															</div>
														</div>

														<input type="hidden" name="command"
															value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_INCLUDED_FILTER_PAGE}" />
														<input class="form_submit" type="submit" value="Поиск" />
													</form>
												</li>
											</ul>
										</div>
									</c:if>
								</div>
							</div>
						</nav>
					</div>
					<div class="row">
						<div class="col-12">
							<table class="table table-striped table-hover">
								<thead class="">
									<tr class="align-middle">
										<th scope="col">  img</th>
										<th scope="col">id</th>
										<th scope="col">type</th>
										<th scope="col">brand</th>
										<th scope="col">description</th>
										<th scope="col">pet_type</th>
										<th scope="col">specie</th>
										<th scope="col">breed</th>
										<th scope="col">birth date</th>
										<th scope="col">price</th>
										<th scope="col">discount</th>
										<th scope="col">total price</th>
										<th scope="col">number</th>
									</tr>
								</thead>

								<tbody class="">
									<c:forEach
										items="${map_product_pet_and_number_of_units.entrySet()}"
										var="pet">
										<tr class="align-middle">
											<td class="">img</td>
											<th class="" scope="row">p-${pet.getKey().getId()}</th>
											<td class="text-lowercase">${ProductType.PETS.toString()}</td>
											<td class="">-</td>
											<td class="">-</td>
											<td class="">-</td>
											<td class="text-lowercase">${pet.getKey().getSpecie()}</td>
											<td class="text-lowercase">${pet.getKey().getBreed()}</td>
											<td class="">${pet.getKey().getBirthDate().toString()}</td>
											<td class="">${pet.getKey().getPrice()}</td>
											<td class="">${pet.getKey().getDiscount()}</td>
											<td class="">${pet.getKey().getTotalPrice()}</td>
											<td class="">${pet.getValue()}</td>
										</tr>
									</c:forEach>
									<c:forEach
										items="${map_product_feeds_and_other_and_number_of_units.entrySet()}"
										var="product">
										<tr class="align-middle">
											<td>img</td>
											<th class="text-lowercase" scope="row">o-${product.getKey().getId()}</th>
											<td class="text-lowercase"><c:if
													test="${product.getKey().getProductType() == null || product.getKey().getProductType().isBlank()}">${ProductType.PETS.toString()}</c:if>
												<c:if
													test="${product.getKey().getProductType() != null && !product.getKey().getProductType().isBlank()}">${product.getKey().getProductType()}</c:if>
											</td>
											<td class="">${product.getKey().getBrand()}</td>
											<td class="">${product.getKey().getDescriptions()}</td>
											<td class="text-lowercase">${product.getKey().getPetTypes().toString().substring(1, product.getKey().getPetTypes().toString().length() - 1)}</td>
											<td class="">-</td>
											<td class="">-</td>
											<td class="">-</td>
											<td class="">${product.getKey().getPrice()}</td>
											<td class="">${product.getKey().getDiscount()}</td>
											<td class="">${product.getKey().getTotalPrice()}</td>
											<td class="">${product.getValue()}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${user == null}">
				<div class="row">
					<div class="col-12">
						<h3
							class="text-center d-flex flex-column justify-content-center align-items-center"
							style="min-height: 45.3vh">Ваше время сессии завершено.
							Перезайдите в учётную запись.</h3>
					</div>
				</div>
			</c:if>
		</div>

	</main>
	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/personal_account.js"></script>
</body>
</html>