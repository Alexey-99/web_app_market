<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.PagePathName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ServletName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterValue"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
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
<link rel="stylesheet"
	href="css/items/home_page/main_types_products_btns.css" />
<link rel="stylesheet" href="css/items/home_page/promtions.css" />
<title><fmt:message key="home_page.title" /></title>
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body>
	<span>${PagePathName.PAGE_CONTENT_PROPERTIES}${locale}</span>
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
								action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
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

	<section id="promotions" class="promotions">
		<div class="container">
			<div class="row">
				<div class="col-12 promotions_title">
					<h5 class="text-center text-uppercase mb-5 mt-5 promotions_title">
						<fmt:message key="header_top.promotions" />
					</h5>
				</div>
			</div>
			<div id="carouselExampleIndicators"
				class="carousel slide promotion_slider" data-bs-ride="false">
				<div class="carousel-indicators btns_bottom">
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="0" class="active btn_bottom" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="1" aria-label="Slide 2" class="btn_bottom"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="2" aria-label="Slide 3" class="btn_bottom"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="3" aria-label="Slide 4" class="btn_bottom"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="4" aria-label="Slide 5" class="btn_bottom"></button>
				</div>
				<div class="carousel-inner carousel_promotions">
					<div class="carousel-item active clide">
						<div class="row">
							<div class="col-6">
								<img src="img/logo.svg"
									class="d-block img-fluid carousel-item_img" alt="logo.svg" />
							</div>
							<div class="col-6">Lorem ipsum dolor sit amet consectetur
								adipisicing elit. Maxime labore est blanditiis!</div>
						</div>
					</div>
					<div class="carousel-item clide">
						<div class="row">
							<div class="col-6">
								<img src="img/logo.svg"
									class="d-block img-fluid carousel-item_img" alt="logo.svg" />
							</div>
							<div class="col-6">Lorem ipsum dolor sit amet consectetur
								adipisicing elit. Maxime labore est blanditiis!</div>
						</div>
					</div>
					<div class="carousel-item clide">
						<div class="row">
							<div class="col-6">
								<img src="img/logo.svg"
									class="d-block img-fluid carousel-item_img" alt="logo.svg" />
							</div>
							<div class="col-6">Lorem ipsum dolor sit amet consectetur
								adipisicing elit. Maxime labore est blanditiis!</div>
						</div>
					</div>
					<div class="carousel-item img-fluid clide">
						<div class="row">
							<div class="col-6">
								<img src="img/logo.svg"
									class="d-block img-fluid carousel-item_img" alt="logo.svg" />
							</div>
							<div class="col-6">Lorem ipsum dolor sit amet consectetur
								adipisicing elit. Maxime labore est blanditiis!</div>
						</div>
					</div>
					<div class="carousel-item clide">
						<div class="row">
							<div class="col-6">
								<img src="img/logo.svg"
									class="d-block img-fluid carousel-item_img" alt="logo.svg" />
							</div>
							<div class="col-6">Lorem ipsum dolor sit amet consectetur
								adipisicing elit. Maxime labore est blanditiis!</div>
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon clider_btn_icon"
						aria-hidden="true"></span> <span class="visually-hidden"> <fmt:message
							key="slider_promotions.previous" />
					</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon clider_btn_icon"
						aria-hidden="true"></span> <span class="visually-hidden"><fmt:message
							key="slider_promotions.next" /></span>
				</button>
			</div>
		</div>
	</section>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
</body>
</html>