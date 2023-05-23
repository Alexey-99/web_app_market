<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/items/main_header.css" />
<link rel="stylesheet" href="css/pages/personal_account.css" />
<link rel="stylesheet" href="css/items/product_card.css" />
<title>personal_account_orders.jsp</title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- user_history_orders = AttributeName.ATTRIBUTE_USER_HISTORY_ORDERS -->
</head>
<body>
	<%@ include file="/jsp/items/header_block_header_top.jsp"%>

	<main class="mb-5">
		<div class="container">
			<div class="row">
				<c:if test="${user != null}">
					<div class="col-md-2 col-sm-3 col-xs-4">
						<div class="btn-group person_account_menu_links">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE}" />
								<button
									class="btn btn-primary w-100 person_account_menu_link btn_first"
									role="button" aria-current="page">Профиль</button>
							</form>
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE}" />
								<button
									class="btn btn-primary active person_account_menu_link w-100 btn_last"
									role="button">Заказы</button>
							</form>
							<c:if
								test="${user.getRole().getIdRole() == UserRole.ADMIN.getIdRole()}">
								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE}" />
									<button class="btn btn-primary w-100 person_account_menu_link"
										role="button" aria-current="page">Страница
										администратора</button>
								</form>
							</c:if>
							<form class="mt-3" action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SIGN_OUT_PERSONAL_ACCOUNT}" />
								<button
									class="btn btn-primary person_account_menu_link w-100 btn_last"
									role="button">Выйти</button>
							</form>
						</div>
					</div>

					<div class="col-md-10 col-sm-9 col-xs-8">
						<div class="row">
							<div class="col-12 mb-5">
								<h1 class="person_part_title">Заказы</h1>
							</div>
						</div>
						<div class="row">
							<c:if test="${user_history_orders.size() == 0}">
								<div class="col-12">
									<h5 class="">Заказы не найдены</h5>
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
															<div class="accordion_header_btn_id_and_date_order">Заказ
																№ ${order.getId()} от ${order.getDateCreation()}</div>
															<div class="accordion_header_btn_status_order pe-4">${order.getStatus()}</div>
														</div>
													</button>
												</h2>
												<div id="collapse_${status.getIndex()}"
													class="accordion-collapse collapse">
													<div class="accordion-body">
														<div
															class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xxl-4 g-4">
															<c:forEach items="${order.getProductsPets()}" var="pet"
																varStatus="status">
																<div class="col">
																	<div class="card h-100 card_product_inner">
																		<img src="img/logo.svg" class="card-img-top card_img"
																			alt="..." />
																		<div
																			class="card-body d-flex flex-column justify-content-between">
																			<ul class="discription_top">
																				<li>ID: p-${pet.getId()}</li>
																				<li>breed: ${pet.getBreed()}</li>
																				<li>date birthday: ${pet.getBirthDate()}</li>
																			</ul>
																		</div>
																	</div>
																</div>
															</c:forEach>
															<c:forEach items="${order.getOtherProducts()}"
																var="product_item" varStatus="status">
																<div class="col card_product">
																	<div class="card h-100 card_product_inner">
																		<img src="img/logo.svg" class="card-img-top card_img"
																			alt="img/logo.svg" />
																		<div
																			class="card-body d-flex flex-column justify-content-between">
																			<ul class="discription_top">
																				<li>ID: o-${product_item.getId()}</li>
																				<li>Product type:
																					${product_item.getProductType()}</li>
																				<li>Brand: ${product_item.getBrand()}</li>
																				<li>Description:
																					${product_item.getDescriptions()}</li>
																				<li>For pets:
																					${product_item.getPetTypes().toString()}</li>
																			</ul>
																		</div>
																	</div>
																</div>
															</c:forEach>
														</div>
														<div
															class="accordion_footer accordion_footer d-flex justify-content-between align-items-center pt-3 mt-3 ps-3 pe-3 pb-3">
															<div
																class="d-flex flex-column justify-content-center align-items-center">
																<div class="">TotalPaymentAmount</div>
																<div>${order.getTotalPaymentAmount()}</div>
															</div>
															<div
																class="d-flex flex-column justify-content-center align-items-center">
																<div class="">
																	<div class="">TotalDiscountAmount</div>
																	<div class="">${order.getTotalDiscountAmount()}</div>
																</div>
															</div>
															<div
																class="d-flex flex-column justify-content-center align-items-center">
																<div class="">TotalPaymentWithDiscountAmount</div>
																<div class="">${order.getTotalPaymentWithDiscountAmount()}</div>
															</div>
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
							style="min-height: 47vh">Ваше время сессии завершено.
							Перезайдите в учётную запись.</h3>
					</div>
				</c:if>
			</div>
		</div>
	</main>
	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>

</body>
</html>