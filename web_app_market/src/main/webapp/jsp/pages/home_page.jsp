<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterValue"%>
<c:set var="language" value="${sessionScope.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="${PagePathName.PAGE_CONTENT_PROPERTIES}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet"
	href="css/items/home_page/main_types_products_btns.css" />
<link rel="stylesheet" href="css/items/home_page/promtions.css" />
<title><fmt:message key="home_page.title" /></title>
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body>

	<header class="header pb-5" style="height: 100vh;">
		<div class="container">
			<div class="row header_top">
				<%@ include file="/jsp/items/header_top.jsp"%>
			</div>
			<div class="row">
				<div
					class="col-12 type_products d-flex justify-content-between align-items-center"
					style="height: 50vh">
					<div class="row w-100" style="height: 40vh;">
						<div class="col-6">
							<form class="h-100"
								action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
								method="get">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND }"
									value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_OFF_FILTER_PAGE}" />
								<input type="hidden"
									name="${ParameterName.PARAMETER_NUMBER_PAGE}"
									value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" />
								<button
									class="btn btn-primary w-100 type_products_btn text-uppercase"
									role="button">
									<fmt:message key="home_page_header_btn.pets" />
								</button>
							</form>
						</div>
						<div class="col-6">
							<form class="h-100"
								action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
								method="get">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND }"
									value="${CommandName.COMMAND_SHOW_PRODUCT_FEED_AND_OTHER_OFF_FILTER_PAGE}">
								<button
									class="btn btn-primary w-100 text-uppercase type_products_btn"
									role="button">
									<fmt:message key="home_page_header_btn.products_for_pets" />
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
</body>
</html>