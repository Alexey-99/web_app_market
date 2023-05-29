<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet"
	href="css/items/admin/verification/verification_information_for_create_product.css" />
<title>Insert title here</title>
<!-- product_feeds_and_other = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER -->
<!-- product_feeds_and_other_number_of_units = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_FEEDS_AND_OTHER_NUMBER_OF_UNITS_PRODUCT -->
</head>
<body>
	<section class="header pb-5" style="height: 100vh">
		<div class="container">
			<div class="row header_top">
				<div class="col-12">
					<div
						class="position-fixed d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 verification_information_for_create_product_pet">
						<div
							class="position-relative w-100 d-flex flex-column verification_information_for_create_product_pet_inner">
							<div
								class="d-flex justify-content-center align-items-center mb-4 verification_information_for_create_product_pet_top_btns">
								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER}" />
									<button class="close_btn border-0 bg-transparent" role="button"
										style="top: 25px; right: 30px">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
											width="25px" height="25px">
                        <path
												d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
									</button>
								</form>
								<h4 class="text-center">Проверка информации для создания
									товара (товары для питомцев)</h4>
							</div>
							<div
								class="d-flex justify-content-center align-items-center flex-column verification_information_for_create_product_pet_body">
								<span>Тип товара</span>
								<h5>${product_feeds_and_other.getProductType()}</h5>
								<span>Брэнд товара</span>
								<h5>${product_feeds_and_other.getBrand()}</h5>
								<span>Описание товара</span>
								<h5>${product_feeds_and_other.getDescription()}</h5>
								<span>Список типов питомцев для которых подходит товар</span>
								<h5>${product_feeds_and_other.getPetTypes()}</h5>
								<span>Цена</span>
								<h5>${product_feeds_and_other.getPrice()}</h5>
								<span>Процент скидки</span>
								<h5>${product_feeds_and_other.getDiscount()}</h5>
								<span>Количество</span>
								<h5>${product_feeds_and_other_number_of_units}</h5>
							</div>
							<div
								class="verification_information_for_create_product_pet_fotter d-flex justify-content-end">
								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_CREATE_FEED_AND_OTHER_PRODUCT_FORM}" />
									<button
										class="btn verification_information_for_create_product_pet_btn_incorrect"
										role="button">Назад</button>
								</form>

								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_ADMIN_PAGE_ADD_NEW_FEED_AND_OTHER_PRODUCT}" />
									<button
										class="btn verification_information_for_create_product_pet_btn_correct"
										role="button">Всё корректно!!!</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="js/bootstrap.bundle.js"></script>
</body>
</html>