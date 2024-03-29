<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.input.InputName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterValue"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.ImagePath"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="${PagePathName.PAGE_CONTENT_PROPERTIES}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/pages/basket_with_products.css" />
<link rel="stylesheet" href="css/items/product_card.css" />
<link rel="stylesheet" href="css/items/payment_information_form.css" />
<title><fmt:message key="basket_page.title" /></title>
<!-- order = AttributeName.ATTRIBUTE_ORDER -->
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body>

	<%@ include file="/jsp/items/header_block_header_top.jsp"%>

	<main class="order">
		<div class="container">
			<div class="row">
				<c:if test="${user != null}">
					<div class="col-9 products_order">
						<div class="products_order_top">
							<div class="row pb-3">
								<div
									class="col-12 d-flex justify-content-around align-items-center">
									<div class="products_order_top_form h-100">
										<form class="h-100" style="width: 50vh"
											action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
											method="get">
											<input type="hidden"
												name="${ParameterName.PARAMETER_NUMBER_PAGE}"
												value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" /> <input
												type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_OFF_FILTER_PAGE }">
											<button style="min-width: 42vh"
												class="btn mx-auto h-100 d-flex justify-content-center align-items-center products_order_top_form_btn"
												role="button">
												<h5
													class="h-100 d-flex justify-content-center align-items-center">
													<fmt:message key="basket_page.add_product_pet" />
												</h5>
											</button>
										</form>
									</div>
									<div class="products_order_top_form h-100">
										<form class="h-100" style="width: 50vh"
											action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
											method="get">
											<input type="hidden"
												name="${ParameterName.PARAMETER_NUMBER_PAGE}"
												value="${ParameterValue.NUMBER_FIRST_PAGE_VALUE}" /> <input
												type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_SHOW_PRODUCT_FEED_AND_OTHER_OFF_FILTER_PAGE}">
											<button style="min-width: 42vh"
												class="btn mx-auto h-100 d-flex justify-content-center align-items-center products_order_top_form_btn"
												role="button">
												<h5
													class="h-100 d-flex justify-content-center align-items-center ">
													<fmt:message key="basket_page.add_product_for_pet" />
												</h5>
											</button>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div
							class="row row-cols-1 row-cols-sm-1 row-cols-lg-2 row-cols-xxl-3 g-4">
							<c:forEach items="${order.getProductsPets()}" var="entry"
								varStatus="status">
								<div class="col card_product">
									<div class="card h-100 card_product_inner">
										<div class="card-img-top card_img"
											style="border: 1px solid var(--bs-card-border-color)">
											<c:if test="${entry.getKey().getImagePath() != null}">
												<img class="w-100 h-100" alt=""
													src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${entry.getKey().getImagePath()}" />
											</c:if>
											<c:if test="${entry.getKey().getImagePath() == null}">
												<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
													width="100%" height="100%" viewBox="0 0 64 64">
<path
														d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
											</c:if>
										</div>
										<div class="card-body card_body">
											<div class="discription">
												<ul class="discription_top">
													<li><fmt:message key="basket_page.product.number" />
														p-${entry.getKey().getId()}</li>
													<li><fmt:message key="basket_page.product_pet.type" />
														${entry.getKey().getBreed()}</li>
													<li><fmt:message key="basket_page.product_pet.breed" />
														${entry.getKey().getBreed()}</li>
													<li><fmt:message
															key="basket_page.product_pet.birth_date" />
														${entry.getKey().getBirthDate()}</li>
												</ul>
												<ul class="discription_botton">
													<li><fmt:message key="basket_page.product.price" />
														${String.format("%,.2f", entry.getKey().getPrice())}</li>
													<li><fmt:message key="basket_page.product.discount" />
														${entry.getKey().getDiscount()} <img
														class="discription_botton_discont_procent_img"
														src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${ImagePath.ICON_PERCENT_PNG_IMAGE_PATH}"
														alt="percent" /></li>
													<li><fmt:message key="basket_page.product.total_price" />
														${String.format("%,.2f", entry.getKey().getTotalPrice())}</li>
												</ul>
												<p class="w-100 text-center">
													<fmt:message key="basket_page.product.number_of_units" />
													${entry.getValue()}
												</p>
											</div>
											<div class="row body_btns w-100">
												<div class="col-12 body_btn">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
														method="get">
														<input type="hidden"
															name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_DELETE_PRODUCT_PETS_FROM_ORDER}" />
														<input type="hidden"
															name="${ParameterName.PARAMETER_PRODUCT_ID}"
															value="${entry.getKey().getId()}" />
														<button class="w-100 h-100 body_btn_input"
															id="liveToastBtn${status.getIndex()}" role="button"
															onclick="deleteProducAndGetProducts('.productsId_cards_pet_${status.getIndex()}' ,'p', ${entry.getKey().getId()})">
															<fmt:message key="basket_page.product.delete" />
														</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="toast-container position-fixed bottom-0 end-0 p-3">
									<div id="liveToast${status.getIndex()}" class="toast"
										role="alert" aria-live="assertive" aria-atomic="true">
										<div class="toast-header">
											<img class="rounded me-2 toast_logo"
												src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${ImagePath.LOGO_PNG_IMAGE_PATH}"
												alt="logo"> <strong class="me-auto">Zoo
												ковчег</strong>
											<button type="button" class="btn-close"
												data-bs-dismiss="toast" aria-label="Закрыть"></button>
										</div>
										<div class="toast-body${status.getIndex()}">
											<fmt:message key="basket_page.product.toast.massege" />
										</div>
									</div>
								</div>
							</c:forEach>

							<c:forEach items="${order.getOtherProducts()}" var="entry"
								varStatus="status">
								<div class="col card_product">
									<div class="card h-100 card_product_inner">
										<div class="card-img-top card_img"
											style="border: 1px solid var(--bs-card-border-color);">
											<c:if test="${entry.getKey().getImagePath() != null}">
												<img class="w-100 h-100" alt=""
													src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${entry.getKey().getImagePath()}" />
											</c:if>
											<c:if test="${entry.getKey().getImagePath() == null}">
												<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
													width="100%" height="100%" viewBox="0 0 64 64">
<path
														d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
											</c:if>
										</div>
										<div class="card-body card_body">
											<div class="discription">
												<ul class="discription_top">
													<li><fmt:message key="basket_page.product.number" />
														o-${entry.getKey().getId()}</li>
													<li><fmt:message
															key="basket_page.product_feed_and_other.type" />
														${entry.getKey().getProductType()}</li>
													<li><fmt:message
															key="basket_page.product_feed_and_other.brand" />
														${entry.getKey().getBrand()}</li>
													<li><fmt:message
															key="basket_page.product_feed_and_other.description" />
														${entry.getKey().getDescriptions()}</li>
													<li><fmt:message
															key="basket_page.product_feed_and_other.pet_types" />
														${entry.getKey().getPetTypes().toString().substring(1, entry.getKey().getPetTypes().toString().length() - 1)}</li>
												</ul>
												<ul class="discription_botton">
													<li><fmt:message key="basket_page.product.price" />
														${String.format("%,.2f", entry.getKey().getPrice())}</li>
													<li><fmt:message key="basket_page.product.discount" />
														${entry.getKey().getDiscount()}<img
														class="discription_botton_discont_procent_img"
														src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${ImagePath.ICON_PERCENT_PNG_IMAGE_PATH}"
														alt="percent" /></li>
													<li><fmt:message key="basket_page.product.total_price" />
														${String.format("%,.2f", entry.getKey().getTotalPrice())}</li>
												</ul>
												<p class="w-100 text-center">
													<fmt:message key="basket_page.product.number_of_units" />
													${entry.getValue()}
												</p>
											</div>
											<div class="row body_btns w-100 ">
												<div class="col body_btn">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
														method="get">
														<input type="hidden"
															name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_DELETE_PRODUCT_FEEDS_AND_OTHER_FROM_ORDER}" />
														<input type="hidden"
															name="${ParameterName.PARAMETER_PRODUCT_ID}"
															value="${entry.getKey().getId()}" />
														<button class="w-100 h-100 body_btn_input" role="button"
															id="liveToastBtn${order.getProductsPets().size() + status.getIndex()}"
															onclick="deleteProducAndGetProducts('.productsId_cards_item_${status.getIndex()}' ,'o', ${entry.getKey().getId()})">
															<fmt:message key="basket_page.product.delete" />
														</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="toast-container position-fixed bottom-0 end-0 p-3">
									<div
										id="liveToast${order.getProductsPets().size() + status.getIndex()}"
										class="toast" role="alert" aria-live="assertive"
										aria-atomic="true">
										<div class="toast-header">
											<img class="rounded me-2 toast_logo"
												src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${ImagePath.LOGO_PNG_IMAGE_PATH}"
												alt="logo"> <strong class="me-auto">Zoo
												ковчег</strong>
											<button type="button" class="btn-close"
												data-bs-dismiss="toast" aria-label="Закрыть"></button>
										</div>
										<div
											class="toast-body${order.getProductsPets().size() + status.getIndex()}">
											<fmt:message key="basket_page.product.toast.massege" />
										</div>
									</div>
								</div>
							</c:forEach>
							<c:if
								test="${order.getProductsPets().size() == 0 && order.getOtherProducts().size() == 0} ">
								<div class="text">
									<h4 class="text-center">
										<fmt:message key="basket_page.if_basket_empty.massege" />
									</h4>
								</div>
							</c:if>
						</div>
					</div>
					<div class="col-3">
						<h1 class="text-uppercase d-flex justify-content-center">
							<fmt:message key="basket_page.order" />
						</h1>
						<div class="description_body">
							<ul class="description_body_list">
								<c:forEach items="${order.getProductsPets()}" var="entry"
									varStatus="status">
									<li class="body_list_item">
										<h6>
											<span class="fw-bolder">${status.getIndex() + 1}.</span>
											<fmt:message key="basket_page.product.number" />
											p-${entry.getKey().getId()}
										</h6>
										<h6>
											<fmt:message key="basket_page.product.price" />
											${String.format("%,.2f", entry.getKey().getPrice())} *
											${entry.getValue()} = ${String.format("%,.2f", entry.getKey().getPrice() * entry.getValue())}
										</h6>
										<h6>
											<fmt:message key="basket_page.product.discount" />
											${String.format("%,.2f", entry.getKey().getPrice() * entry.getKey().getDiscount() / 100)}
											<c:if test="${entry.getKey().getDiscount() > 0}">
										* ${entry.getValue()} = ${String.format("%,.2f", (entry.getKey().getPrice() * entry.getKey().getDiscount() / 100) * entry.getValue())}
										</c:if>
										</h6>
										<h6>
											<fmt:message key="basket_page.product.total_price" />
											${String.format("%,.2f", entry.getKey().getTotalPrice())} *
											${entry.getValue()} = ${String.format("%,.2f", entry.getKey().getTotalPrice() * entry.getValue())}
										</h6>
									</li>
								</c:forEach>
								<c:forEach items="${order.getOtherProducts()}" var="entry"
									varStatus="status">
									<li class="body_list_item">
										<h6>
											<span class="fw-bolder"> ${status.getIndex() + 1}.</span>
											<fmt:message key="basket_page.product.number" />
											o-${entry.getKey().getId()}
										</h6>
										<h6>
											<fmt:message key="basket_page.product.price" />
											${String.format("%,.2f", entry.getKey().getPrice())} *
											${entry.getValue()} = ${String.format("%,.2f", entry.getKey().getPrice() * entry.getValue())}
										</h6>
										<h6>
											<fmt:message key="basket_page.product.discount" />
											${String.format("%,.2f", entry.getKey().getPrice() * entry.getKey().getDiscount() / 100)}
											<c:if test="${entry.getKey().getDiscount() > 0}">
										* ${entry.getValue()} = ${String.format("%,.2f", (entry.getKey().getPrice() * entry.getKey().getDiscount() / 100) * entry.getValue())}
										</c:if>
										</h6>
										<h6>
											<fmt:message key="basket_page.product.total_price" />
											${String.format("%,.2f", entry.getKey().getTotalPrice())} *
											${entry.getValue()} = ${String.format("%,.2f", entry.getKey().getTotalPrice() * entry.getValue())}
										</h6>
									</li>
								</c:forEach>
							</ul>
							<div class="description_footer">
								<div class="description_footer_totat">
									<p>
										<fmt:message key="basket_page.order.price" />
										${String.format("%,.2f", order.getTotalPaymentAmount())}
									</p>
									<p>
										<fmt:message key="basket_page.order.discount_from_products" />
										${String.format("%,.2f", order.getTotalProductsDiscountAmount())}
									</p>
									<p>
										<fmt:message key="basket_page.order.discount_personal" />
										${String.format("%,.2f", order.getTotalPersonDiscountAmount())}
									</p>
									<p>
										<fmt:message key="basket_page.order.total_discount" />
										${String.format("%,.2f", order.getTotalDiscountAmount())}
									</p>
									<p>
										<fmt:message key="basket_page.order.total_price_with_discount" />
										${String.format("%,.2f", order.getTotalPaymentWithDiscountAmount())}
									</p>
								</div>
								<div class="description_footer_btns">
									<div class="description_footer">
										<button class="description_footer_btn"
											onclick="showPaymentInformationForm()">
											<fmt:message key="basket_page.pay" />
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-12">
						<div
							class="position-fixed d-flex d-none justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 payment_information_form">
							<div
								class="position-relative w-100 d-flex flex-column payment_information_form_inner">
								<div class="payment_information_form_top">
									<div class="close_btn border-0 bg-transparent"
										onclick="closedPaymentInformationForm()">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
											width="25px" height="25px">
                      <path
												d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                    </svg>
									</div>

									<h2 class="form_title text-center mb-3">
										<fmt:message key="basket_page.paymant_form.title" />
									</h2>
								</div>
								<form class=""
									action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="post">
									<h6>
										<fmt:message
											key="basket_page.paymant_form.input.enter_number_bank_card" />
									</h6>
									<div class="form-floating mb-3">
										<input type="text" class="form-control"
											id="basket_page.paymant_form.input.enter_number_bank_card"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											pattern="\d{4}\s\d{4}\s\d{4}\s\d{4}" required
											placeholder='<fmt:message key="basket_page.paymant_form.input.enter_number_bank_card"/>' />
										<label class="text-lowercase"
											for="basket_page.paymant_form.input.enter_number_bank_card">
											<fmt:message
												key="basket_page.paymant_form.input.enter_number_bank_card" />
										</label>
									</div>
									<div class="input-group mb-3">
										<div class="input_group_title">
											<h6>
												<fmt:message
													key="basket_page.paymant_form.input.enter_month_and_year_end_card" />
											</h6>
										</div>
										<div class="input_group_forms d-flex flex-row w-100">
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control"
													id="basket_page.paymant_form.lable.month"
													pattern="^(12|11|10|(0)?9|(0)?8|(0)?7|(0)?6|(0)?5|(0)?4|(0)?3|(0)?2|(0)?1)$"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													required
													placeholder='<fmt:message key="basket_page.paymant_form.lable.month"/>' />
												<label class="text-lowercase"
													for="basket_page.paymant_form.lable.month"> <fmt:message
														key="basket_page.paymant_form.lable.month" />
												</label>
											</div>
											<span class="input-group-text">/</span>
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control"
													id="basket_page.paymant_form.lable.year" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													required
													placeholder='<fmt:message key="basket_page.paymant_form.lable.year"/>' />
												<label class="text-lowercase"
													for="basket_page.paymant_form.lable.year"><fmt:message
														key="basket_page.paymant_form.lable.year" /> </label>
											</div>
										</div>
									</div>
									<h6>
										<fmt:message key="basket_page.paymant_form.input.enter_cvc" />
									</h6>
									<div class="form-floating mb-3">
										<input type="text" class="form-control"
											id="basket_page.paymant_form.input.enter_cvc"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											pattern="\d{3}" required
											placeholder='<fmt:message key="basket_page.paymant_form.input.enter_cvc"/>' />
										<label class="text-lowercase"
											for="basket_page.paymant_form.input.enter_cvc"><fmt:message
												key="basket_page.paymant_form.input.enter_cvc" /> </label>
									</div>
									<div
										class="payment_information_form_fotter d-flex justify-content-end">
										<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
											value="${CommandName.COMMAND_BANK_DATA_PROCESSING}" />
										<button class="btn payment_information_form_btn_submit"
											role="button">
											<fmt:message key="basket_page.paymant_form.ready" />
										</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${user == null}">
					<div class="col-12">
						<h3
							class="text-center d-flex flex-column justify-content-center align-items-center"
							style="min-height: 47.8vh">
							<fmt:message key="basket_page.if_finish_session.massege" />
						</h3>
					</div>
				</c:if>
			</div>
		</div>
	</main>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/header_top.js"></script>
	<script src="js/basket.js"></script>

	<script>
		let arrayProductsPetsId = ${order.getProductsPets().stream().map(entry -> entry.getKey().getId()).toList()};
		let arrayProductsOtherId = ${order.getOtherProducts().stream().map(entry -> entry.getKey().getId()).toList()};
		showToast(arrayProductsPetsId, arrayProductsOtherId);
	</script>
</body>
</html>