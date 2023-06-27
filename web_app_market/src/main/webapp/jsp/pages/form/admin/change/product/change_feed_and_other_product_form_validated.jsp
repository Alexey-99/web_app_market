<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterValue"%>
<%@page import="by.koroza.zoo_market.web.command.name.PagePathName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ServletName"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.admin.create.CraetePetProductCommand"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.admin.create.CraeteOtherProductCommand"%>
<fmt:setLocale value="${AttributeName.ATTRIBUTE_SESSION_LOCALE}"
	scope="session" />
<fmt:setBundle
	basename="${PagePathName.PAGE_CONTENT_PROPERTIES}${locale}" />
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
<title><fmt:message key="change_product_form_validated.title" /></title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- map_product_pet_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_PET_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- map_product_feeds_and_other_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- product_pet = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET -->
<!-- product_pet_number_of_units = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT -->
<!-- admin_page_change_feeds_and_other_product_input_exception_type_and_message = AttributeName.ATTRIBUTE_ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
</head>
<body
	style="height: 1087px; display: flex; flex-direction: column; justify-content: space-between;">

	<%@ include file="/jsp/items/header_block_header_top.jsp"%>
	<main class="mb-5" style="min-height: 53vh">
		<div class="container" style="max-width: 1500px">
			<c:if test="${user != null}">
				<div class="row">
					<div class="col-12">
						<div
							class="position-absolute d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 add_product_form">
							<div
								class="position-relative w-100 d-flex flex-column add_product_form_inner">
								<div class="add_product_form_top">
									<div class="add_product_form_top_title">
										<h2 class="form_title text-center mb-3 text-lowercase">
											<fmt:message
												key="admin_page.all_products.change_product_form.title" />
										</h2>
									</div>
									<div
										class="d-flex justify-content-center align-items-center mb-4 add_product_form_top_btns">
										<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER }">
											<button class="close_btn" role="button">
												<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
													width="25px" height="25px">
                        <path
														d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
											</button>
										</form>
										<div
											class="btn text-uppercase active add_product_form_top_btn add_product_form_top_btn_product_pet">
											<fmt:message
												key="change_product_form_validated.change_feeds_and_other.title" />
										</div>
									</div>
								</div>

								<div class="add_other_product_form">
									<c:if
										test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message == null || admin_page_change_feeds_and_other_product_input_exception_type_and_message.isEmpty()}">
										<form class="add_other_product_form_body" method="post"
											action="${pageContext.request.contextPath}/${ServletName.SERVLET_UPLOAD_IMAGE_NAME}"
											enctype="multipart/form-data">
											<input type="hidden"
												name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_ID}"
												value="${product_feeds_and_other.getId()}" />
											<div
												class="form-floating mb-3 add_other_product_form_body_form_floating">
												<input type="file"
													class="form-control text-uppercase add_other_product_form_body_input_img"
													id="change_product_form_validated.change_feeds_and_other.lable.choose_image_product"
													name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
													placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.choose_image_product"/>' />
												<label
													class="text-lowercase add_product_pet_form_body_label_img"
													for="change_product_form_validated.change_feeds_and_other.lable.choose_image_product">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.lable.choose_image_product" />
													<span>*</span>
												</label>
												<div class="">
													<c:if
														test="${product_feeds_and_other.getImagePath() != null}">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.input.choose_image_product.massage.choosed_image_with_name" />
																	 ${product_feeds_and_other.getImagePath()}
														<img class="" style="width: 35px; height: 35px" alt=""
															src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${product_feeds_and_other.getImagePath()}" />
													</c:if>
													<c:if
														test="${product_feeds_and_other.getImagePath() == null}">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.input.choose_image_product.massage.not_choosed_image" />
													</c:if>
												</div>
											</div>
											<div class="form-floating mb-3">
												<div class="input-group mb-3 mt-3">
													<label> <span class="span_input"> <input
															type="checkbox"
															name="${InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE}"
															value="${ParameterValue.ADMIN_PAGE_PRODUCT_FORM_WITHOUT_IMAGE}" />
															<fmt:message
																key="change_product_form_validated.change_feeds_and_other.input.without_image" />
													</span>
													</label>
												</div>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="change_product_form_validated.change_feeds_and_other.lable.type_product"
													name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE}"
													value="${product_feeds_and_other.getProductType()}"
													placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.type_product"/>' />
												<label class="text-lowercase"
													for="change_product_form_validated.change_feeds_and_other.lable.type_product">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.lable.type_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="change_product_form_validated.change_feeds_and_other.lable.brand_product"
													name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND}"
													value="${product_feeds_and_other.getBrand()}"
													placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.brand_product"/>' />
												<label class="text-lowercase"
													for="change_product_form_validated.change_feeds_and_other.lable.brand_product">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.lable.brand_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="change_product_form_validated.change_feeds_and_other.lable.description_product"
													name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION}"
													value="${product_feeds_and_other.getDescriptions()}"
													placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.description_product"/>' />
												<label class="text-lowercase"
													for="change_product_form_validated.change_feeds_and_other.lable.description_product">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.lable.description_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product"
													name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES}"
													value='<c:if test="${product_feeds_and_other.getPetTypes() != null}">${product_feeds_and_other.getPetTypes().toString().substring(1, product_feeds_and_other.getPetTypes().toString().length() - 1)}</c:if>'
													placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product"/>' />
												<label class="text-lowercase"
													for="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="change_product_form_validated.change_feeds_and_other.lable.price_product"
													name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE}"
													value="${product_feeds_and_other.getPrice()}"
													placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.price_product"/>'
													pattern="^(\d+)(\.\d{1,2})?$" /> <label
													class="text-lowercase"
													for="change_product_form_validated.change_feeds_and_other.lable.price_product">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.lable.price_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="change_product_form_validated.change_feeds_and_other.lable.discount_product"
													name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT}"
													value="${product_feeds_and_other.getDiscount()}"
													placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.discount_product"/>'
													pattern="^(\d+)(\.\d{1,2})?$" /> <label
													class="text-lowercase"
													for="change_product_form_validated.change_feeds_and_other.lable.discount_product">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.lable.discount_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="number" class="form-control"
													id="change_product_form_validated.change_feeds_and_other.lable.number_unit_product"
													name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
													value="${product_feeds_and_other_number_of_units}"
													placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.number_unit_product"/>'
													pattern="^(\d+)$" /> <label class="text-lowercase"
													for="change_product_form_validated.change_feeds_and_other.lable.number_unit_product">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.lable.number_unit_product" />
												</label>
											</div>
											<div class="form_description">
												<h5>
													<span>* </span>
													<fmt:message
														key="change_product_form_validated.form_description.field_is_not_reguired_for_enter_information" />
												</h5>
											</div>
											<div
												class="add_other_product_form_fotter d-flex justify-content-end">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value="${CommandName.COMMAND_ADMIN_PAGE_CHANGE_FEED_AND_OTHER_PRODUCT }" />
												<button class="btn add_other_product_form_btn_submit"
													role="button">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.ok" />
												</button>
											</div>
										</form>
									</c:if>
									<c:if
										test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message != null && !admin_page_change_feeds_and_other_product_input_exception_type_and_message.isEmpty()}">
										<form class="add_other_product_form_body" method="post"
											action="${pageContext.request.contextPath}/${ServletName.SERVLET_UPLOAD_IMAGE_NAME}"
											enctype="multipart/form-data">
											<input type="hidden"
												name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_ID}"
												value="${product_feeds_and_other.getId()}" />
											<c:if
												test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_IMAGE)}">
												<div
													class="form-floating mb-3 add_other_product_form_body_form_floating">
													<input type="file"
														class="form-control text-uppercase add_other_product_form_body_input_img is-invalid"
														id="change_product_form_validated.change_feeds_and_other.lable.choose_image_product"
														name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.choose_image_product"/>' />
													<label
														class="text-lowercase add_product_pet_form_body_label_img"
														for="change_product_form_validated.change_feeds_and_other.lable.choose_image_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.choose_image_product" />
														<span>*</span>
													</label>
													<div class="invalid-feedback">${admin_page_change_feeds_and_other_product_input_exception_type_and_message.get(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_IMAGE)}</div>
												</div>
												<div class="form-floating mb-3">
													<div class="input-group mb-3 mt-3">
														<label> <span class="span_input span_input_1">
																<input type="checkbox"
																name="${InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE}"
																value="${ParameterValue.ADMIN_PAGE_PRODUCT_FORM_WITHOUT_IMAGE}" />
																<fmt:message
																	key="change_product_form_validated.change_feeds_and_other.input.without_image" />
														</span>
														</label>
													</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_IMAGE)}">
												<div
													class="form-floating mb-3 add_other_product_form_body_form_floating">
													<input type="file"
														class="form-control text-uppercase add_other_product_form_body_input_img is-valid"
														id="change_product_form_validated.change_feeds_and_other.lable.choose_image_product"
														name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.choose_image_product"/>' />
													<label
														class="text-lowercase add_product_pet_form_body_label_img"
														for="change_product_form_validated.change_feeds_and_other.lable.choose_image_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.choose_image_product" />
														<span>*</span>
													</label>
													<div class="valid-feedback">
														<c:if
															test="${product_feeds_and_other.getImagePath() != null}">
															<fmt:message
																key="change_product_form_validated.change_feeds_and_other.input.choose_image_product.massage.choosed_image_with_name" />
																	 ${product_feeds_and_other.getImagePath()}
																	 <img class="" style="width: 35px; height: 35px" alt=""
																src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${product_feeds_and_other.getImagePath()}" />
														</c:if>
														<c:if
															test="${product_feeds_and_other.getImagePath() == null}">
															<fmt:message
																key="change_product_form_validated.change_feeds_and_other.input.choose_image_product.massage.not_choosed_image" />
														</c:if>
													</div>
												</div>
												<div class="form-floating mb-3">
													<div class="input-group mb-3 mt-3">
														<label> <span class="span_input span_input_1">
																<input type="checkbox"
																name="${InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE}"
																value="${ParameterValue.ADMIN_PAGE_PRODUCT_FORM_WITHOUT_IMAGE}" />
																<fmt:message
																	key="change_product_form_validated.change_feeds_and_other.input.without_image" />
														</span>
														</label>
													</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PRODUCT_TYPE)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-invalid"
														id="change_product_form_validated.change_feeds_and_other.lable.type_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.type_product"/>' />
													<label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.type_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.type_product" />
													</label>
													<div class="invalid-feedback">${admin_page_change_feeds_and_other_product_input_exception_type_and_message.get(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PRODUCT_TYPE)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PRODUCT_TYPE)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-valid"
														id="change_product_form_validated.change_feeds_and_other.lable.type_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE}"
														value="${product_feeds_and_other.getProductType()}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.type_product"/>' />
													<label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.type_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.type_product" />
													</label>
													<div class="valid-feedback">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.positive_feed_back" />
													</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_BRAND)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-invalid"
														id="change_product_form_validated.change_feeds_and_other.lable.brand_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.brand_product"/>' />
													<label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.brand_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.brand_product" />
													</label>
													<div class="invalid-feedback">${admin_page_change_feeds_and_other_product_input_exception_type_and_message.get(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_BRAND)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_BRAND)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-valid"
														id="change_product_form_validated.change_feeds_and_other.lable.brand_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND}"
														value="${product_feeds_and_other.getBrand()}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.brand_product"/>' />
													<label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.brand_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.brand_product" />
													</label>
													<div class="valid-feedback">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.positive_feed_back" />
													</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_DESCRIPTION)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-invalid"
														id="change_product_form_validated.change_feeds_and_other.lable.description_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.description_product"/>' />
													<label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.description_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.description_product" />
													</label>
													<div class="invalid-feedback">${admin_page_change_feeds_and_other_product_input_exception_type_and_message.get(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_DESCRIPTION)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_DESCRIPTION)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-valid"
														id="change_product_form_validated.change_feeds_and_other.lable.description_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION}"
														value="${product_feeds_and_other.getDescriptions()}"
														placeholder='<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.description_product" />' />
													<label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.description_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.description_product" />
													</label>
													<div class="valid-feedback">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.positive_feed_back" />
													</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PET_TYPES)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-invalid"
														id="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES}"
														placeholder='<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product" />' />
													<label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product" />
													</label>
													<div class="invalid-feedback">${admin_page_change_feeds_and_other_product_input_exception_type_and_message.get(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PET_TYPES)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PET_TYPES)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-valid"
														id="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES}"
														value='<c:if test="${product_feeds_and_other.getPetTypes() != null}">${product_feeds_and_other.getPetTypes().toString().substring(1, product_feeds_and_other.getPetTypes().toString().length() - 1)}</c:if>'
														placeholder='<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product" />' />
													<label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product"><fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.types_pet_for_product" /></label>
													<div class="valid-feedback">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.positive_feed_back" />
													</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-invalid"
														id="change_product_form_validated.change_feeds_and_other.lable.price_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.price_product" />'
														pattern="^(\d+)(\.\d{1,2})?$" /> <label
														class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.price_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.price_product" />
													</label>
													<div class="invalid-feedback">${admin_page_change_feeds_and_other_product_input_exception_type_and_message.get(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PRICE)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_PRICE)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-valid"
														id="change_product_form_validated.change_feeds_and_other.lable.price_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE}"
														value="${product_feeds_and_other.getPrice()}"
														placeholder='<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.price_product" />'
														pattern="^(\d+)(\.\d{1,2})?$" /> <label
														class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.price_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.price_product" />
													</label>
													<div class="valid-feedback">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.positive_feed_back" />
													</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_DISCOUNT)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-invalid"
														id="change_product_form_validated.change_feeds_and_other.lable.discount_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.discount_product" />'
														pattern="^(\d+)(\.\d{1,2})?$" /> <label
														class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.discount_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.discount_product" />
													</label>
													<div class="invalid-feedback">${admin_page_change_feeds_and_other_product_input_exception_type_and_message.get(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_DISCOUNT)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_DISCOUNT)}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control is-valid"
														id="change_product_form_validated.change_feeds_and_other.lable.discount_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT}"
														value="${product_feeds_and_other.getDiscount()}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.discount_product" />'
														pattern="^(\d+)(\.\d{1,2})?$" /> <label
														class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.discount_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.discount_product" />
													</label>
													<div class="valid-feedback">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.positive_feed_back" />
													</div>
												</div>
											</c:if>
											<c:if
												test="${admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_NUMBER_OF_UNITS_PRODUCT)}">
												<div class="form-floating mb-3">
													<input type="number" class="form-control is-invalid"
														id="change_product_form_validated.change_feeds_and_other.lable.number_unit_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.number_unit_product" />'
														pattern="^(\d+)$" /> <label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.number_unit_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.number_unit_product" />
													</label>
													<div class="invalid-feedback">${admin_page_change_feeds_and_other_product_input_exception_type_and_message.get(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_NUMBER_OF_UNITS_PRODUCT)}</div>
												</div>
											</c:if>
											<c:if
												test="${!admin_page_change_feeds_and_other_product_input_exception_type_and_message.containsKey(CraeteOtherProductCommand.INPUT_EXCEPTION_TYPE_NUMBER_OF_UNITS_PRODUCT)}">
												<div class="form-floating mb-3">
													<input type="number" class="form-control is-valid"
														id="change_product_form_validated.change_feeds_and_other.lable.number_unit_product"
														name="${InputName.ADMIN_PAGE_CHANGE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
														value="${product_feeds_and_other_number_of_units}"
														placeholder='<fmt:message key="change_product_form_validated.change_feeds_and_other.lable.number_unit_product" />'
														pattern="^(\d+)$" /> <label class="text-lowercase"
														for="change_product_form_validated.change_feeds_and_other.lable.number_unit_product">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.lable.number_unit_product" />
													</label>
													<div class="valid-feedback">
														<fmt:message
															key="change_product_form_validated.change_feeds_and_other.positive_feed_back" />
													</div>
												</div>
											</c:if>
											<div class="form_description">
												<h5>
													<span>* </span>
													<fmt:message
														key="change_product_form_validated.form_description.field_is_not_reguired_for_enter_information" />
												</h5>
											</div>
											<div
												class="add_other_product_form_fotter d-flex justify-content-end">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value="${CommandName.COMMAND_ADMIN_PAGE_CHANGE_FEED_AND_OTHER_PRODUCT }" />
												<button class="btn add_other_product_form_btn_submit"
													role="button">
													<fmt:message
														key="change_product_form_validated.change_feeds_and_other.ok" />
												</button>
											</div>
										</form>
									</c:if>
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
							style="min-height: 45.3vh">
							<fmt:message
								key="change_product_form_validated.end_session.message" />
						</h3>
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