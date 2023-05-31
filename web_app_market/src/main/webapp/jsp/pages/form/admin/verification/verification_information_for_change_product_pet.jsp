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
<!-- product_pet = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET -->
<!-- product_pet_number_of_units = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT -->
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
								<h4 class="text-center">Проверка информации для изменения
									товара (питомца)</h4>
							</div>
							<div
								class="d-flex justify-content-center align-items-center flex-column verification_information_for_create_product_pet_body">
								<span>Выбранная картинка:
									<div
										class="image_product_id_body h-100 d-flex justify-content-center align-items-center">
										<c:if
											test="${product_pet.getImageFile() != null && product_pet.getImageFile().getName() != null && product_pet.getImageFile().getBytes() != null}">
											<img class=""
												src='<c:url value="${PagePathName.LOCALHOST_STORAGE_IMAGES_FOLDER_AND_SEPORATOR_PATH.concat(product_pet.getImageFile().getName())}"/>'
												alt="" style="width: auto; height: 100%" />
										</c:if>

										<c:if
											test="${product_pet.getImageFile() == null || product_pet.getImageFile().getName() == null || product_pet.getImageFile().getBytes() == null}">
										картинка не выбрана
										</c:if>
									</div>
								</span>
								<h5>${product_pet.getSpecie()}</h5>
								<span>Тип питомца</span>
								<h5>${product_pet.getSpecie()}</h5>
								<span>Порода питомца</span>
								<h5>${product_pet.getBreed()}</h5>
								<span>Дата рождения</span>
								<h5>${product_pet.getBirthDate()}</h5>
								<span>Цена</span>
								<h5>${product_pet.getPrice()}</h5>
								<span>Процент скидки</span>
								<h5>${product_pet.getDiscount()}</h5>
								<span>Количество</span>
								<h5>${product_pet_number_of_units}</h5>
							</div>
							<div
								class="verification_information_for_create_product_pet_fotter d-flex justify-content-end">
								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_CHANGE_PET_PRODUCT_FORM}" />
									<button
										class="btn verification_information_for_create_product_pet_btn_incorrect"
										role="button">Назад</button>
								</form>

								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_ADMIN_PAGE_ADD_NEW_PET_PRODUCT}" />
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