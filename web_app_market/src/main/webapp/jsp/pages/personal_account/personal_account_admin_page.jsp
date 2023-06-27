<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.PagePathName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ServletName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.UserRole"%>
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
<link rel="stylesheet" href="css/items/admin/all_products_page.css" />
<title><fmt:message key="personal_account_admin_page.title" /></title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
</head>
<body>
	<%@ include file="/jsp/items/header_block_header_top.jsp"%>

	<main class="mb-5">
		<div class="container">
			<div class="row">
				<c:if test="${user != null}">
					<div class="col-md-2 col-sm-3 col-xs-4">
						<div class="btn-group person_account_menu_links">
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE}" />
								<button
									class="btn btn-primary w-100 person_account_menu_link btn_first"
									role="button" aria-current="page">
									<fmt:message key="personal_account.profile" />
								</button>
							</form>
							<c:if
								test="${user.getRole().getIdRole() != UserRole.ADMIN.getIdRole()}">
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE}" />
									<button
										class="btn btn-primary person_account_menu_link w-100 btn_last"
										role="button">
										<fmt:message key="personal_account.history_orders" />
									</button>
								</form>
							</c:if>
							<c:if
								test="${user.getRole().getIdRole() == UserRole.ADMIN.getIdRole()}">
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE}" />
									<button class="btn btn-primary person_account_menu_link w-100"
										role="button">
										<fmt:message key="personal_account.history_orders" />
									</button>
								</form>
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE}" />
									<button
										class="btn btn-primary active w-100 person_account_menu_link btn_last"
										role="button" aria-current="page">
										<fmt:message key="personal_account.admin_page" />
									</button>
								</form>
							</c:if>
							<form class="mt-3"
								action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="${CommandName.COMMAND_SIGN_OUT_PERSONAL_ACCOUNT}" />
								<button
									class="btn btn-primary person_account_menu_link w-100 btn_first btn_last"
									role="button">
									<fmt:message key="personal_account.exit" />
								</button>
							</form>
						</div>
					</div>
					<div
						class="col-md-10 col-sm-9 col-xs-8 d-flex flex-column justify-content-center align-items-center person_account_admin_menu">
						<div class="person_account_admin_menu_title">
							<h4>
								<fmt:message key="personal_account_admin_page.title" />
							</h4>
						</div>
						<div
							class="btn-group flex-column person_account_admin_menu_links mt-4"
							style="width: 80%; margin: 0 auto">
							<button
								class="btn btn-primary w-100 person_account_menu_link btn_first"
								onclick="showAddProductForm()">
								<fmt:message
									key="personal_account_admin_page.operation.add_product" />
							</button>
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER}" />
								<button class="btn btn-primary person_account_menu_link w-100"
									role="button">
									<fmt:message
										key="personal_account_admin_page.operation.show_all_products" />
								</button>
							</form>
							<button
								class="btn btn-primary person_account_menu_link w-100 btn_last"
								role="button" onclick="showChangeUserStatusForm()">
								<fmt:message
									key="personal_account_admin_page.operation.change_user_status" />
							</button>
						</div>
					</div>

					<div class="row">
						<div class="col-12">
							<div
								class="position-fixed d-none d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 change_user_status_form">
								<div
									class="position-relative w-100 d-flex flex-column change_user_status_form_inner">
									<div class="change_user_status_form_top">
										<div
											class="d-flex justify-content-center align-items-center mb-4 change_user_status_form_top">
											<div class="close_btn" onclick="closedChangeUserStatusForm()">
												<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
													width="25px" height="25px">
                        <path
														d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
											</div>
											<h2 class="form_title text-center mb-3 text-lowercase">
												<fmt:message key="change_user_status.form.title" />
											</h2>
										</div>
									</div>
									<form class="change_user_status_form_body" method="post"
										action="${pageContext.request.contextPath}/${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="change_user_status.form.input_lable.login" required
												name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_INPUT_LOGIN}"
												placeholder='<fmt:message key="change_user_status.form.input_lable.login"/>' />
											<label class="text-lowercase"
												for="change_user_status.form.input_lable.login"> <fmt:message
													key="change_user_status.form.input_lable.login" />
											</label>
										</div>
										<div class="mb-3">
											<select
												name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE}"
												class="form-select" required aria-label="select example">
												<option value="">
													<fmt:message
														key="change_user_status.form.select.optional.select_role" />
												</option>
												<option value="${UserRole.USER.getIdRole()}">
													<fmt:message
														key="change_user_status.form.select.optional.user" />
												</option>
												<option value="${UserRole.ADMIN.getIdRole()}">
													<fmt:message
														key="change_user_status.form.select.optional.admin" />
												</option>
											</select>
										</div>
										<div
											class="change_user_status_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_ADMIN_PAGE_CHANGE_USER_STATUS}" />
											<button class="btn change_user_status_form_btn_submit"
												role="button">
												<fmt:message key="change_user_status.form.ok" />
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>




					<div class="row">
						<div class="col-12">
							<div
								class="position-fixed d-none d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 add_product_form">
								<div
									class="position-relative w-100 d-flex flex-column add_product_form_inner">
									<div class="add_product_form_top">
										<div class="add_product_form_top_title">
											<h2 class="form_title text-center mb-3 text-lowercase">
												<fmt:message
													key="admin_page.all_products.add_product_form.title" />
											</h2>
										</div>
										<div
											class="d-flex justify-content-center align-items-center mb-4 add_product_form_top_btns">
											<div class="close_btn" onclick="closeAddProductForm()">
												<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
													width="25px" height="25px">
                        <path
														d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
											</div>
											<button
												class="btn text-uppercase active add_product_form_top_btn add_product_form_top_btn_product_pet"
												role="button" onclick="openAddProductPetForm()">
												<fmt:message
													key="admin_page.all_products.add_product_form.add_pet.title" />
											</button>
											<button
												class="btn text-uppercase add_product_form_top_btn add_product_form_top_btn_other_product"
												role="button" onclick="openAddOtherProductForm()">
												<fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.title" />
											</button>
										</div>
									</div>

									<div class="add_product_pet_form">
										<form class="add_product_pet_form_body" method="post"
											action="${pageContext.request.contextPath}/${ServletName.SERVLET_UPLOAD_IMAGE_NAME}"
											enctype="multipart/form-data">
											<div
												class="form-floating mb-3 add_product_pet_form_body_form_floating">
												<input type="file"
													class="form-control text-uppercase add_product_pet_form_body_input_img"
													id="admin_page.all_products.add_product_form.add_pet.lable.choose_image_product"
													name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.choose_image_product"/>' />
												<label
													class="text-lowercase add_product_pet_form_body_label_img"
													for="admin_page.all_products.add_product_form.add_pet.lable.choose_image_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_pet.lable.choose_image_product" />
													<span> *</span></label>
											</div>
											<div class="form-floating mb-3">
												<div class="input-group mb-3 mt-3">
													<label> <span class="span_input span_input_1">
															<input type="checkbox"
															name="${InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE}"
															value="${ParameterValue.ADMIN_PAGE_CREATE_PRODUCT_FORM_WITHOUT_IMAGE}" />
															<fmt:message
																key="add_product_form_validated.add_pet.input.without_image" />
													</span>
													</label>
												</div>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="admin_page.all_products.add_product_form.add_pet.lable.type_pet"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_SPECIE}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.type_pet"/>' />
												<label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_pet.lable.type_pet"><fmt:message
														key="admin_page.all_products.add_product_form.add_pet.lable.type_pet" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control "
													id="admin_page.all_products.add_product_form.add_pet.lable.breed_pet"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BREED}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.breed_pet"/>' />
												<label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_pet.lable.breed_pet"><fmt:message
														key="admin_page.all_products.add_product_form.add_pet.lable.breed_pet" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="date" class="form-control "
													id="admin_page.all_products.add_product_form.add_pet.lable.birth_date_pet"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.birth_date_pet"/>' />
												<label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_pet.lable.birth_date_pet"><fmt:message
														key="admin_page.all_products.add_product_form.add_pet.lable.birth_date_pet" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="admin_page.all_products.add_product_form.add_pet.lable.price_pet"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_PRICE}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.price_pet"/>'
													pattern="^(\d+)(\.\d{1,2})?$" /> <label
													class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_pet.lable.price_pet"><fmt:message
														key="admin_page.all_products.add_product_form.add_pet.lable.price_pet" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control "
													id="admin_page.all_products.add_product_form.add_pet.lable.discount_pet"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_DISCOUNT}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.discount_pet"/>'
													pattern="^(\d+)(\.\d{1,2})?$" /> <label
													class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_pet.lable.discount_pet"><fmt:message
														key="admin_page.all_products.add_product_form.add_pet.lable.discount_pet" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="number" class="form-control "
													id="admin_page.all_products.add_product_form.add_pet.lable.number_unit_pet"
													name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.number_unit_pet"/>'
													pattern="^(\d+)$" /> <label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_pet.lable.number_unit_pet"><fmt:message
														key="admin_page.all_products.add_product_form.add_pet.lable.number_unit_pet" /></label>
											</div>
											<div
												class="add_product_pet_form_fotter d-flex justify-content-end">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value="${CommandName.COMMAND_ADMIN_PAGE_CREATE_PET_PRODUCT}" />
												<button class="btn add_product_pet_form_btn_submit"
													role="button">
													<fmt:message
														key="admin_page.all_products.add_product_form.add_pet.ok" />
												</button>
											</div>
										</form>
									</div>

									<div class="add_other_product_form d-none">
										<form class="add_product_pet_form_body" method="post"
											action="${pageContext.request.contextPath}/${ServletName.SERVLET_UPLOAD_IMAGE_NAME}"
											enctype="multipart/form-data">
											<div
												class="form-floating mb-3 add_other_product_form_body_form_floating">
												<input type="file"
													class="form-control text-uppercase add_other_product_form_body_input_img"
													id="admin_page.all_products.add_product_form.add_feeds_and_other.choose_image_product"
													name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.choose_image_product"/>' />
												<label
													class="text-lowercase add_product_pet_form_body_label_img"
													for="admin_page.all_products.add_product_form.add_feeds_and_other.choose_image_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.choose_image_product" />
													<span>*</span> </label>
											</div>
											<div class="form-floating mb-3">
												<div class="input-group mb-3 mt-3">
													<label> <span class="span_input span_input_1">
															<input type="checkbox"
															name="${InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE}"
															value="${ParameterValue.ADMIN_PAGE_CREATE_PRODUCT_FORM_WITHOUT_IMAGE}" />
															<fmt:message
																key="add_product_form_validated.add_feeds_and_other.input.without_image" />
													</span>
													</label>
												</div>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.type_product"
													name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.type_product"/>' />
												<label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.type_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.type_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.brand_product"
													name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.brand_product"/>' />
												<label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.brand_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.brand_product" /></label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.description_product"
													name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.description_product"/>' />
												<label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.description_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.description_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.types_pet_for_product"
													name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.types_pet_for_product"/>' />
												<label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.types_pet_for_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.types_pet_for_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.price_product"
													name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.price_product"/>'
													pattern="^(\d+)(\.\d{1,2})?$" /> <label
													class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.price_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.price_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="text" class="form-control"
													id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.discount_product"
													name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.discount_product"/>'
													pattern="^(\d+)(\.\d{1,2})?$" /> <label
													class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.discount_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.discount_product" />
												</label>
											</div>
											<div class="form-floating mb-3">
												<input type="number" class="form-control"
													id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.number_unit_product"
													name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
													placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.number_unit_product"/>'
													pattern="^(\d+)$" /> <label class="text-lowercase"
													for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.number_unit_product"><fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.number_unit_product" />
												</label>
											</div>
											<div class="form_description">
												<h5>
													<span>* </span>
													<fmt:message
														key="admin_page.all_products.add_product_form.form_description.field_is_not_reguired_for_enter_information" />
												</h5>
											</div>
											<div
												class="add_other_product_form_fotter d-flex justify-content-end">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value="${CommandName.COMMAND_ADMIN_PAGE_CREATE_FEED_AND_OTHER_PRODUCT }" />
												<button class="btn add_other_product_form_btn_submit"
													role="button">
													<fmt:message
														key="admin_page.all_products.add_product_form.add_feeds_and_other.ok" />
												</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>

				</c:if>
				<c:if test="${user == null}">
					<div class="col-12">
						<h3
							class="text-center d-flex flex-column justify-content-center align-items-center"
							style="min-height: 45.3vh">
							<fmt:message
								key="personal_account_person_infomation.end_session.message" />
						</h3>
					</div>
				</c:if>
			</div>
		</div>
	</main>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/personal_account.js"></script>
</body>
</html>