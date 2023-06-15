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
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="show_personal_account_main_page" />
								<button
									class="btn btn-primary w-100 person_account_menu_link btn_first"
									role="button" aria-current="page">
									<fmt:message
										key="personal_account_admin_page.operation.add_product" />
								</button>
							</form>
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER}" />
								<button class="btn btn-primary person_account_menu_link w-100"
									role="button">
									<fmt:message
										key="personal_account_admin_page.operation.show_all_products" />
								</button>
							</form>
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="show_personal_account_main_page" />
								<button class="btn btn-primary person_account_menu_link w-100"
									role="button">показать заказы</button>
							</form>
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