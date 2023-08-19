<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.input.InputName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.language.LanguageName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.ProductType"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="${PagePathName.PAGE_CONTENT_PROPERTIES}" />
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
<title><fmt:message key="admin_page.all_products.title" /></title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- pet_class = AttributeName.SESSION_ATTRIBUTE_PET_CLASS_NAME -->
<!-- feed_and_other_class = AttributeName.SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME -->
<!-- map_products_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- map_product_pet_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_PET_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- map_product_feeds_and_other_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body>

	<%@ include file="/jsp/items/header_block_header_top.jsp"%>
	<main class="mb-5" style="min-height: 53vh">
		<div class="container" style="max-width: 1500px">
			<c:if test="${user != null}">
				<div class="row">
					<div class="col-12">
						<div class="top_btns d-flex justify-content-between">
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
								method="get">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE }" />
								<button class="btn top_btns_add_product" role="button">
									<fmt:message key="admin_page.all_products.back" />
								</button>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<table class="table table-striped table-hover">
								<thead class="">
									<tr class="align-middle">
										<td class="text-center" scope="col"><fmt:message
												key="personal_account_admin_page.operation.show_all_clients.col.user_id" />
										</td>
										<td class="text-center" scope="col"><fmt:message
												key="personal_account_admin_page.operation.show_all_clients.col.user_login" />
										</td>
										<td class="text-center" scope="col"><fmt:message
												key="personal_account_admin_page.operation.show_all_clients.col.user_role" />
										</td>
										<td class="text-center" scope="col"><fmt:message
												key="personal_account_admin_page.operation.show_all_clients.col.user_email" />
										</td>
										<td class="text-center" scope="col"><fmt:message
												key="personal_account_admin_page.operation.show_all_clients.col.user_is_verificated_email" />
										</td>
										<td class="text-center" scope="col"><fmt:message
												key="personal_account_admin_page.operation.show_all_clients.col.personal_discount" />
										</td>
										<td class="text-center" scope="col"><fmt:message
												key="personal_account_admin_page.operation.show_all_clients.col.is_active_account" />
										</td>
										<td class="text-center" scope="col"><fmt:message
												key="personal_account_admin_page.operation.show_all_clients.col.date_at_create_account" />
										</td>
									</tr>
								</thead>
								<tbody class="">
									<c:forEach items="${list_users}" var="user">
										<tr class="align-middle">
											<th class="text-center" scope="row">${user.getId()}</th>
											<td class="text-lowercase text-center">${user.getLogin()}</td>
											<td class="edit_product_td text-center">
												<button class="bg-transparent w-100" style="border: none;"
													data-bs-toggle="modal"
													data-bs-target="#changeUserRole${user.getId()}">
													<div
														class="d-flex justify-content-center align-items-center">${user.getRole()}</div>
												</button>
												<div class="modal fade" id="changeUserRole${user.getId()}"
													tabindex="-1" aria-labelledby="exampleModalLabel"
													aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<h1 class="modal-title fs-5" id="exampleModalLabel">
																	<fmt:message
																		key="personal_account_admin_page.operation.show_all_clients.col.user_role.form.change_user_role.title" />
																	${user.getId()}
																</h1>
																<button type="button" class="btn-close"
																	data-bs-dismiss="modal" aria-label="Закрыть"></button>
															</div>
															<form
																action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
																method="get">
																<div class="modal-body">
																	<input type="hidden"
																		name="${ParameterName.PARAMETER_COMMAND}"
																		value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_USERS_CHANGE_USER_STATUS}" />
																	<input type="hidden"
																		name="${ParameterName.PARAMETER_USER_ID}"
																		value="${user.getId()}" /> <select
																		name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE}"
																		class="form-select" required
																		aria-label="select example">
																		<option value="">
																			<fmt:message
																				key="change_user_status.form.select.optional.select_role" />
																		</option>
																		<option value="${UserRole.USER.getId()}">
																			<fmt:message
																				key="change_user_status.form.select.optional.user" />
																		</option>
																		<option value="${UserRole.ADMIN.getId()}">
																			<fmt:message
																				key="change_user_status.form.select.optional.admin" />
																		</option>
																	</select>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-bs-dismiss="modal">
																		<fmt:message
																			key="admin_page.all_products.col.more_details.form.change_order_status.footer_btn.close" />
																	</button>
																	<button role="button" class="btn btn-primary">
																		<fmt:message
																			key="admin_page.all_products.col.more_details.form.change_order_status.footer_btn.save" />
																	</button>
																</div>
															</form>
														</div>
													</div>
												</div>
											</td>
											<td class="text-center"><div
													class="d-flex justify-content-center align-items-center">${user.getEmail()}</div>
											</td>
											<td class="text-center"><div
													class="d-flex justify-content-center align-items-center">${user.isVerificatedEmail()}</div>
											</td>
											<td class="text-lowercase text-center"><div
													class="d-flex justify-content-center align-items-center">${user.getDiscount()}</div></td>
											<td class="text-lowercase text-center"><div
													class="d-flex justify-content-center align-items-center">${user.isActive()}</div></td>
											<td class="text-center"><div
													class="d-flex justify-content-center align-items-center">${user.getDateAtCreate().toString()}</div></td>
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
							style="min-height: 45.3vh">
							<fmt:message key="admin_page.all_products.end_session.message" />
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