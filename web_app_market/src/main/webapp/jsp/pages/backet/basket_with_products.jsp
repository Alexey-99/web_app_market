<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/pages/basket_with_products.css" />
<link rel="stylesheet" href="css/items/product_card.css" />
<link rel="stylesheet" href="css/items/payment_information_form.css" />
<title>basket_with_products.jsp</title>
<!-- order = AttributeName.ATTRIBUTE_ORDER -->
<!-- user = AttributeName.ATTRIBUTE_USER -->
</head>
<body>

	<%@ include file="/jsp/items/header_block_header_top.jsp"%>

	<main class="order">
		<div class="container">
			<div class="row">
				<c:if test="${user != null}">
					<div class="col-9 products_order">
						<div
							class="row row-cols-1 row-cols-sm-1 row-cols-lg-2 row-cols-xxl-3 g-4">
							<c:forEach items="${order.getProductsPets()}" var="pet"
								varStatus="status">
								<div class="col card_product">
									<div class="card h-100 card_product_inner">
										<img src="img/logo.svg" class="card-img-top card_img"
											alt="..." />
										<div class="card-body card_body">
											<div class="discription">
												<ul class="discription_top">
													<li>
														<h2>ID: P - ${pet.getId()}</h2>
													</li>
													<li>breed: ${pet.getBreed()}</li>
													<li>date birthday: ${pet.getBirthDate()}</li>
												</ul>
												<ul class="discription_botton">
													<li>price: ${pet.getPrice()}</li>
													<li>Discount: ${pet.getDiscount()} <img
														class="discription_botton_discont_procent_img"
														src="img/percent.svg" alt="percent.svg" /></li>
													<li>TotalPrice: ${pet.getTotalPrice()}</li>
												</ul>
											</div>
											<div class="row body_btns w-100">
												<div class="col-12 body_btn">
													<form action="Controller">
														<input type="hidden" name="command"
															value="${CommandName.COMMAND_SHOW_BACKET_PAGE}" /><input
															class="productsId_cards_pet_${status.getIndex()}"
															type="hidden"
															name="${AttributeName.ATTRIBUTE_SAVED_PRODUCTS_ID_IN_JSP_PAGE}" />
														<button class="w-100 h-100 body_btn_input"
															id="liveToastBtn${status.getIndex()}" role="button"
															onclick="deleteProducAndGetProducts('.productsId_cards_pet_${status.getIndex()}' ,'p', ${pet.getId()})">удалить</button>
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
											<img src="img/logo.svg" class="rounded me-2 toast_logo"
												alt="logo.svg"> <strong class="me-auto">Zoo
												ковчег</strong>
											<button type="button" class="btn-close"
												data-bs-dismiss="toast" aria-label="Закрыть"></button>
										</div>
										<div class="toast-body${status.getIndex()}">Вы удалили
											из корзтны следующий товар:
											${productsPets.get(status.getIndex()).getDescription()}</div>
									</div>
								</div>
							</c:forEach>

							<c:forEach items="${order.getOtherProducts()}" var="item"
								varStatus="status">
								<div class="col card_product">
									<div class="card h-100 card_product_inner">
										<img src="img/logo.svg" class="card-img-top card_img"
											alt="img/logo.svg" />
										<div class="card-body card_body">
											<div class="discription">
												<ul class="discription_top">
													<li>
														<h2>ID: O - ${item.getId()}</h2>
													</li>
													<li>Product type: ${item.getProductType()}</li>
													<li>Brand: ${item.getBrand()}</li>
													<li>Description: ${item.getDescriptions().toString()}</li>
													<li>For pets: ${item.getPetTypes().toString()}</li>
												</ul>
												<ul class="discription_botton">
													<li>Price: ${item.getPrice()}</li>
													<li>Discont: ${item.getDiscount()}<img
														class="discription_botton_discont_procent_img"
														src="img/percent.svg" alt="percent.svg" />
													</li>
													<li>TotalPrice: ${item.getTotalPrice()}</li>
												</ul>
											</div>
											<div class="row body_btns w-100 ">
												<div class="col body_btn">
													<form action="Controller">
														<input type="hidden" name="command"
															value="${CommandName.COMMAND_SHOW_BACKET_PAGE}" /> <input
															class="productsId_cards_item_${status.getIndex()}"
															type="hidden"
															name="${AttributeName.ATTRIBUTE_SAVED_PRODUCTS_ID_IN_JSP_PAGE}" />
														<button class="w-100 h-100 body_btn_input" role="button"
															id="liveToastBtn${order.getProductsPets().size() + status.getIndex()}"
															onclick="deleteProducAndGetProducts('.productsId_cards_item_${status.getIndex()}' ,'o', ${item.getId()})">удалить</button>
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
											<img src="img/logo.svg" class="rounded me-2 toast_logo"
												alt="logo.svg"> <strong class="me-auto">Zoo
												ковчег</strong>
											<button type="button" class="btn-close"
												data-bs-dismiss="toast" aria-label="Закрыть"></button>
										</div>
										<div
											class="toast-body${order.getProductsPets().size() + status.getIndex()}">Вы
											удалили из корзтны следующий товар:
											${order.getOtherProducts().get(status.getIndex()).getDescription()}</div>
									</div>
								</div>
							</c:forEach>
							<c:if
								test="${order.getProductsPets().size() == 0 && order.getOtherProducts().size() == 0} ">
								<div class="text">
									<h4 class="text-center">Ваша корзина пуста.</h4>
								</div>
							</c:if>
							<div class="col card_product">
								<div class="card h-100 card_product_inner">
									<form class="h-100" action="Controller">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_SHOW_PRODUCT_PETS_OFF_FILTER_PAGE }">
										<button
											class="btn mx-auto h-100 d-flex justify-content-center align-items-center"
											role="button">
											<h2
												class="h-100 d-flex justify-content-center align-items-center">Добавить
												питомца</h2>
										</button>
									</form>
								</div>
							</div>
							<div class="col card_product">
								<div class="card h-100 card_product_inner">
									<form class="h-100" action="Controller">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_SHOW_PRODUCT_FEED_AND_OTHER_OFF_FILTER_PAGE}">
										<button
											class="btn mx-auto h-100 d-flex justify-content-center align-items-center"
											role="button">
											<h2
												class="h-100 d-flex justify-content-center align-items-center">Добавить
												товар для питомца</h2>
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="col-3">
						<h1 class="text-uppercase d-flex justify-content-center">Заказ</h1>
						<div class="description_body">
							<ul class="description_body_list">
								<c:forEach items="${order.getProductsPets()}" var="pet"
									varStatus="status">
									<li class="body_list_item"><span class="fw-bolder">
											${status.getIndex() + 1}. </span> ${pet.getDescription()}<br />
										Price: ${pet.getPrice()} <br /> Discount: ${pet.getPrice() * pet.getDiscount() / 100}
										<br /> Price with discount: ${pet.getTotalPrice()}</li>
								</c:forEach>
								<c:forEach items="${order.getOtherProducts()}" var="item"
									varStatus="status">
									<li class="body_list_item"><span class="fw-bolder">
											${status.getIndex() + 1}. </span>${item.getDescription()}<br />
										Price: ${item.getPrice()} <br /> Discount: ${item.getPrice() * item.getDiscount() / 100}
										<br /> Price with discount: ${item.getTotalPrice()}</li>
								</c:forEach>
							</ul>
							<div class="description_footer">
								<div class="description_footer_totat">
									<p>Итого: ${String.format("%,.2f", order.getTotalPaymentAmount())}</p>
									<p>Скидка на акционные товары: ${String.format("%,.2f", order.getTotalProductsDiscountAmount())}</p>
									<p>Скидка персональная: ${String.format("%,.2f", order.getTotalPersonDiscountAmount())}</p>
									<p>Игого скидка: ${String.format("%,.2f", order.getTotalDiscountAmount())}</p>
									<p>Итого со скидкой к оплате: ${String.format("%,.2f", order.getTotalPaymentWithDiscountAmount())}</p>
								</div>
								<div class="description_footer_btns">
									<div class="description_footer">
										<button class="description_footer_btn"
											onclick="showPaymentInformationForm()">Оформить
											заказ</button>
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

									<h2 class="form_title text-center mb-3">Данные для оплаты</h2>
								</div>
								<form class=" " action="Controller">
									<h6>Введите номер банковской карты</h6>
									<div class="form-floating mb-3">
										<input type="text" class="form-control"
											id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}"
											placeholder="1234 5678 8765 4321"
											pattern="\d{4}\s\d{4}\s\d{4}\s\d{4}" required /> <label
											class="text-lowercase"
											for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_NUMBER_BANK_CARD}">Введите
											номер карты</label>
									</div>
									<div class="input-group mb-3">
										<div class="input_group_title">
											<h6>Введите месяц и год окончания работы карты</h6>
										</div>
										<div class="input_group_forms d-flex flex-row w-100">
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control"
													id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													placeholder="месяц"
													pattern="^(12|11|10|(0)?9|(0)?8|(0)?7|(0)?6|(0)?5|(0)?4|(0)?3|(0)?2|(0)?1)$"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}"
													required /> <label class="text-lowercase"
													for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_MONTH}">месяц</label>
											</div>
											<span class="input-group-text">/</span>
											<div class="form-floating" style="width: inherit">
												<input type="number" class="form-control"
													id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													placeholder="год" pattern="\d{2}"
													name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}"
													required /> <label class="text-lowercase"
													for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_YEAR}">год</label>
											</div>
										</div>
									</div>
									<h6>Введите SVC (3 цифры)</h6>
									<div class="form-floating mb-3">
										<input type="text" class="form-control"
											id="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											name="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}"
											placeholder="123" pattern="\d{3}" required /> <label
											class="text-lowercase"
											for="${InputName.PAYMENT_INFOMATION_FORM_BANK_CARD_INPUT_BANK_CARD_CVC}">Введите
											CVS</label>
									</div>
									<div
										class="payment_information_form_fotter d-flex justify-content-end">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_ORDER_PAYMENT}" />
										<button class="btn payment_information_form_btn_submit"
											role="button">готово</button>
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
							style="min-height: 47.8vh">Ваше время сессии завершено.
							Перезайдите в учётную запись.</h3>
					</div>
				</c:if>
			</div>
		</div>
	</main>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/basket.js"></script>

	<script>
		let arrayProductsPetsId = ${productsPets.stream().map(pet -> pet.getId()).toList()};
		let arrayProductsOtherId = ${productsOther.stream().map(product -> product.getId()).toList()};
		showToast(arrayProductsPetsId, arrayProductsOtherId);
	</script>
</body>
</html>