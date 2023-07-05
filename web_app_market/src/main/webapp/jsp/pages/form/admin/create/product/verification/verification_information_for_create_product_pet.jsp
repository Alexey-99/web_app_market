<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<fmt:setLocale value="${locate}" scope="session" />
<fmt:setBundle basename="${PagePathName.PAGE_CONTENT_PROPERTIES}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet"
	href="css/items/admin/verification/verification_information_for_create_product.css" />
<title><fmt:message
		key="verification_information_for_creating_product_pet.title" /></title>
<!-- product_pet = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET -->
<!-- product_pet_number_of_units = AttributeName.ATTRIBUTE_BUFFER_PRODUCT_PET_NUMBER_OF_UNITS_PRODUCT -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
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
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
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
								<h4 class="text-center">
									<fmt:message
										key="verification_information_for_creating_product_pet.title" />
								</h4>
							</div>
							<div
								class="d-flex justify-content-center align-items-center flex-column verification_information_for_create_product_pet_body">
								<span><fmt:message
										key="verification_information_for_creating_product_pet.field_name.image" />
								</span>
								<h5>
									<c:if test="${product_pet.getImagePath() != null}">
										<div
											class="d-flex justify-content-center align-items-center flex-column">
											<div>${product_pet.getImagePath()}</div>
											<div>
												<img class="mb-3 mt-3 mw-100" style="max-height: 200px"
													alt=""
													src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${product_pet.getImagePath()}" />
											</div>
										</div>
									</c:if>
									<c:if test="${product_pet.getImagePath() == null}">
										<div>
											<fmt:message
												key="verification_information_for_creating_product_pet.field_value.massage.not_choosed_image" />
										</div>
									</c:if>
								</h5>
								<span><fmt:message
										key="verification_information_for_creating_product_pet.field_name.type_pet" />
								</span>
								<h5>${product_pet.getSpecie()}</h5>
								<span><fmt:message
										key="verification_information_for_creating_product_pet.field_name.breed_pet" />
								</span>
								<h5>${product_pet.getBreed()}</h5>
								<span><fmt:message
										key="verification_information_for_creating_product_pet.field_name.birth_date_pet" />
								</span>
								<h5>${product_pet.getBirthDate()}</h5>
								<span><fmt:message
										key="verification_information_for_creating_product_pet.field_name.price_pet" />
								</span>
								<h5>${product_pet.getPrice()}</h5>
								<span><fmt:message
										key="verification_information_for_creating_product_pet.field_name.discount_pet" />
								</span>
								<h5>${product_pet.getDiscount()}</h5>
								<span><fmt:message
										key="verification_information_for_creating_product_pet.field_name.number_unit_pet" />
								</span>
								<h5>${product_pet_number_of_units}</h5>
							</div>
							<div
								class="verification_information_for_create_product_pet_fotter d-flex justify-content-end">
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_CREATE_PET_PRODUCT_FORM}" />
									<button
										class="btn verification_information_for_create_product_pet_btn_incorrect"
										role="button">
										<fmt:message
											key="verification_information_for_creating_product_pet.back" />
									</button>
								</form>
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_ADMIN_PAGE_ADD_NEW_PET_PRODUCT}" />
									<button
										class="btn verification_information_for_create_product_pet_btn_correct"
										role="button">
										<fmt:message
											key="verification_information_for_creating_product_pet.ok" />
									</button>
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