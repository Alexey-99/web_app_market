<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.UserRole"%>
<%@page import="by.koroza.zoo_market.web.command.name.LanguageName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.ProductType"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.admin.product.pet.CraetePetProductCommand"%>
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
<link rel="stylesheet" href="css/items/admin/all_products_page.css" />
<title>Insert title here</title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- map_product_pet_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_PET_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- map_product_feeds_and_other_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- product_pet = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET -->
<!-- product_pet_number_of_units = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT -->
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
					<div class="col-11">
						<div class="top_btns d-flex justify-content-between">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE }" />
								<button class="btn top_btns_add_product" role="button">
									Назад</button>
							</form>
							<div class="top_btns_operations">
								<button class="btn top_btns_add_product"
									onclick="showAddProductForm()">Добавить товар</button>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<table class="table table-striped table-hover">
								<thead class="">
									<tr class="align-middle">
										<th scope="col">img</th>
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
				<div class="row">
					<div class="col-12">
						<div
							class="position-fixed d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 add_product_form">
							<div
								class="position-relative w-100 d-flex flex-column add_product_form_inner">
								<div class="add_product_form_top">
									<div class="add_product_form_top_title">
										<h2 class="form_title text-center mb-3 text-lowercase">
											Добавление товара</h2>
									</div>
									<div
										class="d-flex justify-content-center align-items-center mb-4 add_product_form_top_btns">
										<form action="Controller">
											<input type="hidden" name="command"
												value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER }">
											<button class="close_btn" role="button">
												<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
													width="25px" height="25px">
                        <path
														d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
											</button>
										</form>
										<button
											class="btn text-uppercase active add_product_form_top_btn add_product_form_top_btn_product_pet"
											role="button" onclick="openAddProductPetForm()">
											Питомцы</button>
										<button
											class="btn text-uppercase add_product_form_top_btn add_product_form_top_btn_other_product"
											role="button" onclick="openAddOtherProductForm()">
											товары для питомцев</button>
									</div>
								</div>

								<div class="add_product_pet_form">
									<c:if
										test="${admin_page_create_pet_product_input_exception_type_and_message == null || admin_page_create_pet_product_input_exception_type_and_message.isEmpty()}">
										<form class="add_product_pet_form_body" method="post"
											action="${pageContext.request.contextPath}/imageServlet"
											enctype="multipart/form-data">
											<div
												class="form-floating mb-3 add_product_pet_form_body_form_floating">
												<input type="file"
													class="form-control text-uppercase add_product_pet_form_body_input_img"
													id="floatingInputSpecie"
													name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
													placeholder="dog" /> <label
													class="text-lowercase add_product_pet_form_body_label_img"
													for="floatingInputSpecie">Выберите картинку для
													товара</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control text-uppercase"
													id="floatingInputSpecie"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_SPECIE}"
													placeholder="dog" /> <label class="text-lowercase"
													for="floatingInputSpecie">тип питомца</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control text-uppercase"
													id="floatingInputBreed"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BREED}"
													placeholder="Breed" /> <label class="text-lowercase"
													for="floatingInputBreed">порода</label>
											</div>
											<div class="form-floating mb-3">
												<input type="date" class="form-control text-uppercase"
													id="floatingInputBirthDate"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE}"
													placeholder="BirthDate" /> <label class="text-lowercase"
													for="floatingInputBirthDate">Дата рождения</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control text-uppercase"
													id="floatingInputPrice"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_PRICE}"
													placeholder="Price" pattern="^(\d+)(\.\d{1,2})?$" /> <label
													class="text-lowercase" for="floatingInputPrice">Цена</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control text-uppercase"
													id="floatingInputDiscount"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_DISCOUNT}"
													placeholder="Discount" pattern="^(\d+)(\.\d{1,2})?$" /> <label
													class="text-lowercase" for="floatingInputDiscount">Скидка
													(в %)</label>
											</div>
											<div class="form-floating mb-3">
												<input type="number" class="form-control text-uppercase"
													id="floatingInputNumberOfUnitsProducts"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
													placeholder="NumberOfUnitsProducts" pattern="^(\d+)$" /> <label
													class="text-lowercase"
													for="floatingInputNumberOfUnitsProducts">количество
													единиц</label>
											</div>
											<div
												class="add_product_pet_form_fotter d-flex justify-content-end">
												<input type="hidden" name="command"
													value="${CommandName.COMMAND_ADMIN_PAGE_CREATE_PET_PRODUCT}" />
												<button class="btn add_product_pet_form_btn_submit"
													role="button">готово</button>
											</div>
										</form>
									</c:if>
									<c:if
										test="${admin_page_create_pet_product_input_exception_type_and_message != null && !admin_page_create_pet_product_input_exception_type_and_message.isEmpty()}">
										<form class="add_product_pet_form_body" method="post"
											action="${pageContext.request.contextPath}/imageServlet"
											enctype="multipart/form-data">
											<div
												class="form-floating mb-3 add_product_pet_form_body_form_floating">
												<input type="file"
													class="form-control text-uppercase add_product_pet_form_body_input_img"
													id="floatingInputSpecie"
													name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
													placeholder="dog" /> <label
													class="text-lowercase add_product_pet_form_body_label_img"
													for="floatingInputSpecie">Выберите картинку для
													товара</label>
											</div>
											<c:if
												test="${admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_SPECIE) }">
												<div class="form-floating mb-3">
													<input type="text"
														class="form-control text-uppercase is-invalid"
														id="floatingInputSpecie"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_SPECIE}"
														placeholder="dog" /> <label class="text-lowercase"
														for="floatingInputSpecie">тип питомца</label>
													<div class="invalid-feedback">${admin_page_create_pet_product_input_exception_type_and_message.get(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_SPECIE)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_SPECIE) }">
												<div class="form-floating mb-3">
													<input type="text"
														class="form-control text-uppercase is-valid"
														id="floatingInputSpecie"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_SPECIE}"
														value="${product_pet.getSpecie()}" placeholder="dog" /> <label
														class="text-lowercase" for="floatingInputSpecie">тип
														питомца</label>
													<div class="valid-feedback">Всё хорошо!!!</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_BREED) }">
												<div class="form-floating mb-3">
													<input type="text"
														class="form-control text-uppercase is-invalid"
														id="floatingInputBreed"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BREED}"
														placeholder="Breed" /> <label class="text-lowercase"
														for="floatingInputBreed">порода</label>
													<div class="invalid-feedback">${admin_page_create_pet_product_input_exception_type_and_message.get(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_BREED)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_BREED) }">
												<div class="form-floating mb-3">
													<input type="text"
														class="form-control text-uppercase is-valid"
														id="floatingInputBreed"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BREED}"
														value="${product_pet.getBreed()}" placeholder="Breed" />
													<label class="text-lowercase" for="floatingInputBreed">порода</label>
													<div class="valid-feedback">Всё хорошо!!!</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_BIRTH_DATE) }">
												<div class="form-floating mb-3">
													<input type="date"
														class="form-control text-uppercase is-invalid"
														id="floatingInputBirthDate"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE}"
														placeholder="BirthDate" /> <label class="text-lowercase"
														for="floatingInputBirthDate">Дата рождения</label>
													<div class="invalid-feedback">${admin_page_create_pet_product_input_exception_type_and_message.get(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_BIRTH_DATE)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_BIRTH_DATE) }">
												<div class="form-floating mb-3">
													<input type="date"
														class="form-control text-uppercase is-valid"
														id="floatingInputBirthDate"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE}"
														value="${product_pet.getBirthDate().toString()}"
														placeholder="BirthDate" /> <label class="text-lowercase"
														for="floatingInputBirthDate">Дата рождения</label>
													<div class="valid-feedback">Всё хорошо!!!</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_PRICE) }">
												<div class="form-floating mb-3">
													<input type="text"
														class="form-control text-uppercase is-invalid"
														id="floatingInputPrice"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_PRICE}"
														placeholder="Price" pattern="^(\d+)(\.\d{1,2})?$" /> <label
														class="text-lowercase" for="floatingInputPrice">Цена</label>
													<div class="invalid-feedback">${admin_page_create_pet_product_input_exception_type_and_message.get(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_PRICE)}</div>
												</div>
											</c:if>
											<span>${!admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_PRICE)}
											</span>
											<c:if
												test="${!admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
												<div class="form-floating mb-3">
													<input type="text"
														class="form-control text-uppercase is-valid"
														id="floatingInputPrice"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_PRICE}"
														value="${product_pet.getPrice()}" placeholder="Price"
														pattern="^(\d+)(\.\d{1,2})?$" /> <label
														class="text-lowercase" for="floatingInputPrice">Цена</label>
													<div class="valid-feedback">Всё хорошо!!!</div>
												</div>
											</c:if>
											<span>${admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_DISCOUNT) }</span>
											<c:if
												test="${admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_DISCOUNT) }">
												<div class="form-floating mb-3">
													<input type="text"
														class="form-control text-uppercase is-invalid"
														id="floatingInputDiscount"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_DISCOUNT}"
														placeholder="Discount" pattern="^(\d+)(\.\d{1,2})?$" /> <label
														class="text-lowercase" for="floatingInputDiscount">Скидка
														(в %)</label>
													<div class="invalid-feedback">${admin_page_create_pet_product_input_exception_type_and_message.get(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_DISCOUNT)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_DISCOUNT) }">
												<div class="form-floating mb-3">
													<input type="text"
														class="form-control text-uppercase is-valid"
														id="floatingInputDiscount"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_DISCOUNT}"
														value="${product_pet.getDiscount()}"
														placeholder="Discount" pattern="^(\d+)(\.\d{1,2})?$" /> <label
														class="text-lowercase" for="floatingInputDiscount">Скидка
														(в %)</label>
													<div class="valid-feedback">Всё хорошо!!!</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_NUMBER_OF_UNITS_PRODUCT) }">
												<div class="form-floating mb-3">
													<input type="number"
														class="form-control text-uppercase is-invalid"
														id="floatingInputNumberOfUnitsProducts"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
														placeholder="NumberOfUnitsProducts" pattern="^(\d+)$" />
													<label class="text-lowercase"
														for="floatingInputNumberOfUnitsProducts">количество
														единиц</label>
													<div class="invalid-feedback">${admin_page_create_pet_product_input_exception_type_and_message.get(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_NUMBER_OF_UNITS_PRODUCT)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_create_pet_product_input_exception_type_and_message.containsKey(CraetePetProductCommand.INPUT_EXCEPTION_TYPE_NUMBER_OF_UNITS_PRODUCT) }">
												<div class="form-floating mb-3">
													<input type="number"
														class="form-control text-uppercase is-valid"
														id="floatingInputNumberOfUnitsProducts"
														name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
														value="${product_pet_number_of_units}"
														placeholder="NumberOfUnitsProducts" pattern="^(\d+)$" />
													<label class="text-lowercase"
														for="floatingInputNumberOfUnitsProducts">количество
														единиц</label>
													<div class="valid-feedback">Всё хорошо!!!</div>
												</div>
											</c:if>
											<div
												class="add_product_pet_form_fotter d-flex justify-content-end">
												<input type="hidden" name="command"
													value="${CommandName.COMMAND_ADMIN_PAGE_CREATE_PET_PRODUCT}" />
												<button class="btn add_product_pet_form_btn_submit"
													role="button">готово</button>
											</div>
										</form>
									</c:if>
								</div>

								<div class="add_other_product_form d-none">
									<form class="registration_form_body" action="Controller">
										<div class="form-floating mb-3">
											<input type="text" class="form-control text-uppercase"
												id="floatingInputName" name="floatingInputName"
												placeholder="Robert" /> <label class="text-lowercase"
												for="floatingInputName">имя <span>*</span>
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control text-uppercase"
												id="floatingInputSurname" name="floatingInputSurname"
												placeholder="Robert" /> <label class="text-lowercase"
												for="floatingInputSurname">Фамилия <span>*</span>
											</label>
										</div>
										<div class="input-group mb-3">
											<span class="input-group-text">+375 </span>
											<div class="form-floating">
												<input type="tel" class="form-control text-uppercase"
													id="floatingInputTel" name="floatingInputTel"
													placeholder="375 (25) 000-00-00"
													pattern="^(17|25|29|33|44)(\s+)?[0-9]{3}-?[0-9]{2}-?[0-9]{2}$" />
												<label class="text-lowercase" for="floatingInputTel">Телефон
													<span>*</span>
												</label>
											</div>
										</div>
										<div class="form-floating mb-3">
											<input type="email"
												class="form-control text-uppercase is-invalid"
												id="floatingInputEmail" name="floatingInputEmail"
												placeholder="name@example.com"
												pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})" />
											<label class="text-lowercase" for="floatingInputEmail">Адрес
												электронной почты</label>
										</div>

										<div class="form-floating mb-3">
											<input type="text" class="form-control text-uppercase"
												id="floatingInputLogin" name="floatingInputLogin"
												placeholder="Robert" /> <label class="text-lowercase"
												for="floatingInputLogin">Логин</label>
										</div>
										<div
											class="form-floating mb-3 d-flex justify-content-center align-items-center">
											<input type="password"
												class="form-control text-uppercase registration_form_input_password"
												id="floatingInputPassword" name="floatingInputPassword"
												placeholder="123456" /> <label class="text-lowercase"
												for="floatingInputPassword">Пароль </label>
											<div class="btn registration_form_password_btn"
												onclick="showPasswordRegistrationFormInput()">
												<svg class="registration_form_open_eye"
													xmlns="http://www.w3.org/2000/svg" height="25"
													viewBox="0 96 960 960" width="25">
                          <path
														d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                        </svg>
												<svg class="registration_form_close_eye d-none"
													xmlns="http://www.w3.org/2000/svg" height="25"
													viewBox="0 96 960 960" width="25">
                          <path
														d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                        </svg>
											</div>
										</div>
										<div class="form_description">
											<h5>
												<span>*</span> - поле не обязательное для заполнения
											</h5>
										</div>

										<div
											class="registration_form_fotter d-flex justify-content-end">
											<div class="btn registration_form_btn_cancel"
												onclick="closeSignInAndRegistrationForm()">отмена</div>
											<input type="hidden" name="command"
												value="show_personal_account_main_page" />
											<button class="btn registration_form_btn_submit"
												role="button">готово</button>
										</div>
									</form>
								</div>
							</div>
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