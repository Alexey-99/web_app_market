<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.UserRole"%>
<%@page import="by.koroza.zoo_market.model.entity.status.OrderStatus"%>
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
<link rel="stylesheet" href="css/items/product_card.css" />
<title><fmt:message key="personal_account_history_orders.title" />
</title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- user_history_orders = AttributeName.ATTRIBUTE_USER_HISTORY_ORDERS -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body style="min-height: 100vh">
	<%@ include file="/jsp/items/header_block_header_top.jsp"%>

	<main class="mb-5">
		<div class="container">
			<div class="row">
				<c:if test="${user != null}">
					<div class="col-md-2 col-sm-3 col-xs-4">
						<div class="btn-group person_account_menu_links">
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
								method="get">
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
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE}" />
									<button
										class="btn btn-primary active person_account_menu_link w-100 btn_last"
										role="button">
										<fmt:message key="personal_account.history_orders" />
									</button>
								</form>
							</c:if>
							<c:if
								test="${user.getRole().getIdRole() == UserRole.ADMIN.getIdRole()}">
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE}" />
									<button
										class="btn btn-primary active person_account_menu_link w-100"
										role="button">
										<fmt:message key="personal_account.history_orders" />
									</button>
								</form>
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE}" />
									<button
										class="btn btn-primary w-100 person_account_menu_link btn_last"
										role="button" aria-current="page">
										<fmt:message key="personal_account.admin_page" />
									</button>
								</form>
							</c:if>
						</div>
					</div>

					<div class="col-md-10 col-sm-9 col-xs-8">
						<div class="row">
							<div class="col-12 mb-5">
								<h1 class="person_part_title">
									<fmt:message key="personal_account.history_orders" />
								</h1>
							</div>
						</div>
						<div class="row">
							<c:if test="${user_history_orders.size() == 0}">
								<div class="col-12">
									<h5 class="">
										<fmt:message
											key="personal_account_history_orders.if_not_found_orders" />
									</h5>
								</div>
							</c:if>
							<c:if test="${user_history_orders.size() > 0}">
								<div class="col-12">
									<div class="accordion accordion_form" id="accordionExample">
										<c:forEach items="${user_history_orders}" var="order"
											varStatus="status">
											<div class="accordion-item">
												<h2 class="accordion-header">
													<button
														class="accordion-button accordion_header_btn collapsed text-uppercase"
														type="button" data-bs-toggle="collapse"
														data-bs-target="#collapse_${status.getIndex()}"
														aria-expanded="false"
														aria-controls="collapse_${status.getIndex()}">
														<div
															class="d-flex flex-row justify-content-between w-100 align-items-center">
															<div class="accordion_header_btn_id_and_date_order">
																<fmt:message key="personal_account_history_orders.order" />
																<span> </span> № ${order.getId()} <span> </span>
																<fmt:message key="personal_account_history_orders.from" />
																<span> </span> ${order.getDateCreation()}
															</div>
															<div class="accordion_header_btn_status_order pe-4">
																<c:if
																	test="${order.getStatus().getId() == OrderStatus.OPEN.getId()}">
																	<fmt:message
																		key="personal_account_history_orders.order_status.open" />
																</c:if>
																<c:if
																	test="${order.getStatus().getId() == OrderStatus.WAITING_PAY.getId()}">
																	<fmt:message
																		key="personal_account_history_orders.order_status.waiting_pay" />
																</c:if>
																<c:if
																	test="${order.getStatus().getId() == OrderStatus.CLOSED.getId()}">
																	<fmt:message
																		key="personal_account_history_orders.order_status.closed" />
																</c:if>
															</div>
														</div>
													</button>
												</h2>
												<div id="collapse_${status.getIndex()}"
													class="accordion-collapse collapse">
													<div class="accordion-body">
														<div
															class="accordion_footer accordion_footer d-flex justify-content-between align-items-center mt-3 mb-3 pt-3 ps-3 pe-3 pb-3">
															<div
																class="d-flex flex-column justify-content-center align-items-center">
																<div class="">
																	<fmt:message
																		key="personal_account_history_orders.order_total_payment_amount" />
																</div>
																<div>${String.format("%,.2f", order.getTotalPaymentAmount())}
																</div>
															</div>
															<div
																class="d-flex flex-column justify-content-center align-items-center">
																<div
																	class="d-flex flex-column justify-content-center align-items-center">
																	<div class="">
																		<fmt:message
																			key="personal_account_history_orders.order_total_discount_amount" />
																	</div>
																	<div class="">${String.format("%,.2f", order.getTotalDiscountAmount())}
																	</div>
																</div>
															</div>
															<div
																class="d-flex flex-column justify-content-center align-items-center">
																<div class="">
																	<fmt:message
																		key="personal_account_history_orders.order_total_payment_with_discount_amount" />
																</div>
																<div class="">${String.format("%,.2f", order.getTotalPaymentWithDiscountAmount())}
																</div>
															</div>
														</div>
														<div
															class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xxl-4 g-4">
															<c:forEach items="${order.getProductsPets()}" var="pet"
																varStatus="status">
																<div class="col">
																	<div class="card h-100 card_product_inner">
																		<div class="card-img-top card_img"
																			style="border: 1px solid var(--bs-card-border-color)">
																			<c:if test="${pet.getImagePath() != null}">
																				<img class="w-100 h-100" alt=""
																					src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${pet.getImagePath()}" />
																			</c:if>
																			<c:if test="${pet.getImagePath() == null}">
																				<svg xmlns="http://www.w3.org/2000/svg" x="0px"
																					y="0px" width="100%" height="100%"
																					viewBox="0 0 64 64">
<path
																						d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
																			</c:if>
																		</div>
																		<div class="card-body card_body">
																			<div class="discription">
																				<ul class="discription_top">
																					<li><fmt:message
																							key="basket_page.product.number" />
																						p-${pet.getId()}</li>
																					<li><fmt:message
																							key="basket_page.product_pet.type" />
																						${pet.getBreed()}</li>
																					<li><fmt:message
																							key="basket_page.product_pet.breed" />
																						${pet.getBreed()}</li>
																					<li><fmt:message
																							key="basket_page.product_pet.birth_date" />
																						${pet.getBirthDate()}</li>
																				</ul>
																			</div>
																		</div>
																	</div>
																</div>
															</c:forEach>
															<c:forEach items="${order.getOtherProducts()}"
																var="product_item" varStatus="status">
																<div class="col card_product">
																	<div class="card h-100 card_product_inner">
																		<div class="card-img-top card_img"
																			style="border: 1px solid var(--bs-card-border-color);">
																			<c:if test="${product_item.getImagePath() != null}">
																				<img class="w-100 h-100" alt=""
																					src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${item.getImagePath()}" />
																			</c:if>
																			<c:if test="${product_item.getImagePath() == null}">
																				<svg xmlns="http://www.w3.org/2000/svg" x="0px"
																					y="0px" width="100%" height="100%"
																					viewBox="0 0 64 64">
<path
																						d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
																			</c:if>
																		</div>
																		<div class="card-body card_body">
																			<div class="discription w-100">
																				<ul class="discription_top w-100">
																					<li><fmt:message
																							key="basket_page.product.number" />
																						o-${product_item.getId()}</li>
																					<li><fmt:message
																							key="basket_page.product_feed_and_other.type" />
																						${product_item.getProductType()}</li>
																					<li><fmt:message
																							key="basket_page.product_feed_and_other.brand" />
																						${product_item.getBrand()}</li>
																					<li><fmt:message
																							key="basket_page.product_feed_and_other.description" />
																						${product_item.getDescriptions()}</li>
																					<li><fmt:message
																							key="basket_page.product_feed_and_other.pet_types" />
																						${product_item.getPetTypes().toString().substring(1, product_item.getPetTypes().toString().length() - 1)}</li>
																				</ul>
																			</div>
																		</div>
																	</div>
																</div>
															</c:forEach>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</c:if>
				<c:if test="${user == null}">
					<div class="col-12">
						<h3
							class="text-center d-flex flex-column justify-content-center align-items-center"
							style="min-height: 47vh">
							<fmt:message
								key="personal_account_history_orders.end_session.message" />
						</h3>
					</div>
				</c:if>
			</div>
		</div>
	</main>
	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>

</body>
</html>