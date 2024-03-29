<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.input.InputName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.language.LanguageName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.ProductType"%>
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
<link rel="stylesheet" href="css/pages/products_page.css" />
<link rel="stylesheet" href="css/items/product_card.css" />
<link rel="stylesheet" href="css/items/admin/all_products_page.css" />
<title><fmt:message key="admin_page.all_products.title" /></title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- pet_class = AttributeName.SESSION_ATTRIBUTE_PET_CLASS_NAME -->
<!-- feed_and_other_class = AttributeName.SESSION_ATTRIBUTE_FEED_AND_OTHER_CLASS_NAME -->
<!-- map_products_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCTS_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- map_product_pet_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_PET_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- map_product_feeds_and_other_and_number_of_units = AttributeName.ATTRIBUTE_MAP_PRODUCT_FEED_AND_OTHER_AND_NUMBER_OF_UNITS_PRODUCT -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body>

	<%@ include file="/jsp/items/header_block_header_top.jsp"%>
	<main class="mb-5" style="min-height: 53vh">
		<div class="container" style="max-width: 1500px">
			<c:if test="${user != null}">
				<div class="row">
					<div class="col-12">
						<div class="top_btns d-flex justify-content-between">
							<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
								method="get">
								<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
									value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE }" />
								<button class="btn top_btns_add_product" role="button">
									<fmt:message key="admin_page.all_products.back" />
								</button>
							</form>
							<div class="top_btns_operations">
								<button class="btn top_btns_add_product"
									onclick="showAddProductForm()">
									<fmt:message key="admin_page.all_products.add_product" />
								</button>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<table class="table table-striped table-hover">
								<thead class="">
									<tr class="align-middle">
										<th scope="col">
											<div class="border-0 bg-transparent" style="font-weight: 400">
												<fmt:message key="admin_page.all_products.col.picture" />
											</div>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_ID_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message
														key="admin_page.all_products.col.number_of_product" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPE_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message key="admin_page.all_products.col.product_type" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_BRAND_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message key="admin_page.all_products.col.brand" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DESCRIPTION_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message key="admin_page.all_products.col.description" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TYPES_PETS_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message
														key="admin_page.all_products.col.types_pet_for_product" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_TYPE_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message key="admin_page.all_products.col.specie_pet" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BREED_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message key="admin_page.all_products.col.breed_pet" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PET_BIRTH_DATE_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message
														key="admin_page.all_products.col.birth_date_pet" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_PRICE_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message key="admin_page.all_products.col.price" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_DISCOUNT_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message key="admin_page.all_products.col.discount" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_PRODUCT_TOTAL_PRICE_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message
														key="admin_page.all_products.col.price_with_discount" />
												</button>
											</form>
										</th>
										<th scope="col">
											<form
												class="d-flex justify-content-center align-items-center"
												action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
												method="get">
												<input type="hidden"
													name="${ParameterName.PARAMETER_COMMAND}"
													value='<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) != null 
													&& pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_DESCENDING}
													</c:if>
													<c:if test="${pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND) == null 
													|| !pageContext.getRequest().getAttribute(AttributeName.REQUEST_ATTRIBUTE_COMMAND).equals(CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING)}">
													${CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_PRODUCTS_OFF_FILTER_SORTING_BY_NUMBER_OF_UNITS_ASCENDING}
													</c:if>' />
												<button class="border-0 bg-transparent text-lowercase"
													role="button">
													<fmt:message
														key="admin_page.all_products.col.number_of_units_in_available" />
												</button>
											</form>
										</th>
										<th scope="col">
											<div class="border-0 bg-transparent" style="font-weight: 400">
												<fmt:message key="admin_page.all_products.col.change" />
											</div>
										</th>
										<th scope="col">
											<div class="border-0 bg-transparent" style="font-weight: 400">
												<fmt:message key="admin_page.all_products.col.more_details" />
											</div>
										</th>
									</tr>
								</thead>
								<tbody class="">
									<c:forEach
										items="${map_products_and_number_of_units.entrySet()}"
										var="product">
										<c:if
											test="${product.getKey().getClass().toString().equals(pet_class)}">
											<tr class="align-middle">
												<td class=" table_row_product_img"
													onclick="showProductImage('p-${product.getKey().getId()}')"
													style="height: auto;">
													<div class="card-img-top"
														style="border: 1px solid var(--bs-card-border-color); margin: 0 auto; display: flex; justify-content: center; align-items: center; height: auto;">
														<c:if test="${product.getKey().getImagePath() != null}">
															<img class="" style="width: 35px; height: 35px" alt=""
																src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${product.getKey().getImagePath()}" />
														</c:if>
														<c:if test="${product.getKey().getImagePath() == null}">
															<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
																width="35px" height="35px" viewBox="0 0 64 64">
<path
																	d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
														</c:if>
													</div>
												</td>
												<th class="" scope="row">p-${product.getKey().getId()}</th>
												<td class="text-lowercase">${ProductType.PETS.toString()}</td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">-</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">-</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">-</div></td>
												<td class="text-lowercase"><div
														class="d-flex justify-content-center align-items-center">${product.getKey().getSpecie()}</div></td>
												<td class="text-lowercase"><div
														class="d-flex justify-content-center align-items-center">${product.getKey().getBreed()}</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${product.getKey().getBirthDate().toString()}</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${String.format("%,.2f", product.getKey().getPrice())}
													</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${String.format("%,.2f", product.getKey().getDiscount())}</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${String.format("%,.2f", product.getKey().getTotalPrice())}</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${product.getValue()}</div></td>

												<td class="edit_product_td">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
														<input type="hidden"
															name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_CHANGE_PET_PRODUCT_FORM}">
														<input type="hidden"
															name="${ParameterName.PARAMETER_PRODUCT_ID}"
															value="${product.getKey().getId()}">
														<button class="bg-transparent w-100" style="border: none;"
															role="button">
															<svg class="w-100" style="margin: 0 auto"
																xmlns="http://www.w3.org/2000/svg" viewBox="0 0 30 30"
																width="25px" height="25px">
                      <path
																	d="M 22.828125 3 C 22.316375 3 21.804562 3.1954375 21.414062 3.5859375 L 19 6 L 24 11 L 26.414062 8.5859375 C 27.195062 7.8049375 27.195062 6.5388125 26.414062 5.7578125 L 24.242188 3.5859375 C 23.851688 3.1954375 23.339875 3 22.828125 3 z M 17 8 L 5.2597656 19.740234 C 5.2597656 19.740234 6.1775313 19.658 6.5195312 20 C 6.8615312 20.342 6.58 22.58 7 23 C 7.42 23.42 9.6438906 23.124359 9.9628906 23.443359 C 10.281891 23.762359 10.259766 24.740234 10.259766 24.740234 L 22 13 L 17 8 z M 4 23 L 3.0566406 25.671875 A 1 1 0 0 0 3 26 A 1 1 0 0 0 4 27 A 1 1 0 0 0 4.328125 26.943359 A 1 1 0 0 0 4.3378906 26.939453 L 4.3632812 26.931641 A 1 1 0 0 0 4.3691406 26.927734 L 7 26 L 5.5 24.5 L 4 23 z" />
                    </svg>
														</button>
													</form>
												</td>
												<td class="edit_product_td">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
														<input type="hidden"
															name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_PET}">
														<input type="hidden"
															name="${ParameterName.PARAMETER_PRODUCT_ID}"
															value="${product.getKey().getId()}">
														<button class="bg-transparent w-100"
															style="padding-top: 5px; border: none;" role="button">
															<svg class="d-flex w-100" style="margin: 0 auto;"
																xmlns="http://www.w3.org/2000/svg" width="27"
																height="27" fill="currentColor" class="bi bi-three-dots"
																viewBox="0 0 16 16">
  <path
																	d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z" />
</svg>
														</button>
													</form>
												</td>
											</tr>
											<div
												class="position-fixed d-none d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 image_product_id image_product_id_p-${product.getKey().getId()}">
												<div
													class="position-relative w-100 d-flex flex-column image_product_id_inner image_product_id_p-${product.getKey().getId()}_inner"
													style="height: 60vh; max-width: 70vh">
													<div class="image_product_id_top">
														<div class="close_btn"
															onclick="closedProductImage('p-${product.getKey().getId()}')">
															<svg xmlns="http://www.w3.org/2000/svg"
																viewBox="0 0 24 24" width="25px" height="25px">
                          <path
																	d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                        </svg>
														</div>
														<div class="add_product_form_top_title">
															<h2 class="form_title text-center mb-3 text-lowercase">
																<fmt:message key="big_product_image.title" />
															</h2>
														</div>
													</div>
													<div
														class="image_product_id_body h-100 d-flex justify-content-center align-items-center">
														<c:if test="${product.getKey().getImagePath() != null}">

															<img class=""
																style="max-height: 420px; max-width: 550px;" alt=""
																src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${product.getKey().getImagePath()}" />
														</c:if>
														<c:if test="${product.getKey().getImagePath() == null}">
															<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
																width="auto" height="45vh" viewBox="0 0 64 64">
<path
																	d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
														</c:if>
													</div>
												</div>
											</div>
										</c:if>
										<c:if
											test="${product.getKey().getClass().toString().equals(feed_and_other_class)}">
											<tr class="align-middle">
												<td class="table_row_product_img"
													onclick="showProductImage('o-${product.getKey().getId()}')"
													style=""><div class="card-img-top"
														style="border: 1px solid var(--bs-card-border-color); margin: 0 auto; display: flex; justify-content: center; align-items: center; height: auto;">
														<c:if test="${product.getKey().getImagePath() != null}">
															<img class="" style="width: 35px; height: 35px" alt=""
																src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${product.getKey().getImagePath()}" />
														</c:if>
														<c:if test="${product.getKey().getImagePath() == null}">
															<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
																width="35px" height="35px" viewBox="0 0 64 64">
<path
																	d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
														</c:if>
													</div></td>
												<th class="text-lowercase" scope="row">o-${product.getKey().getId()}</th>
												<td class="text-lowercase"><c:if
														test="${product.getKey().getProductType() == null || product.getKey().getProductType().isBlank()}">${ProductType.PETS.toString()}</c:if>
													<c:if
														test="${product.getKey().getProductType() != null && !product.getKey().getProductType().isBlank()}">${product.getKey().getProductType()}</c:if>
												</td>
												<td class="">${product.getKey().getBrand()}</td>
												<td class="">${product.getKey().getDescriptions()}</td>
												<td class="text-lowercase">${product.getKey().getPetTypes().toString().substring(1, product.getKey().getPetTypes().toString().length() - 1)}</td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">-
													</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">-
													</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">-
													</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${String.format("%,.2f", product.getKey().getPrice())}</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${String.format("%,.2f", product.getKey().getDiscount())}</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${String.format("%,.2f", product.getKey().getTotalPrice())}</div></td>
												<td class=""><div
														class="d-flex justify-content-center align-items-center">${product.getValue()}</div></td>
												<td class="edit_product_td">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
														<input type="hidden"
															name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_CHANGE_FEED_AND_OTHER_PRODUCT_FORM}">
														<input type="hidden"
															name="${ParameterName.PARAMETER_PRODUCT_ID}"
															value="${product.getKey().getId()}">
														<button class="bg-transparent w-100" style="border: none;"
															role="button">
															<svg class="w-100" style="margin: 0 auto"
																xmlns="http://www.w3.org/2000/svg" viewBox="0 0 30 30"
																width="25px" height="25px">
                      <path
																	d="M 22.828125 3 C 22.316375 3 21.804562 3.1954375 21.414062 3.5859375 L 19 6 L 24 11 L 26.414062 8.5859375 C 27.195062 7.8049375 27.195062 6.5388125 26.414062 5.7578125 L 24.242188 3.5859375 C 23.851688 3.1954375 23.339875 3 22.828125 3 z M 17 8 L 5.2597656 19.740234 C 5.2597656 19.740234 6.1775313 19.658 6.5195312 20 C 6.8615312 20.342 6.58 22.58 7 23 C 7.42 23.42 9.6438906 23.124359 9.9628906 23.443359 C 10.281891 23.762359 10.259766 24.740234 10.259766 24.740234 L 22 13 L 17 8 z M 4 23 L 3.0566406 25.671875 A 1 1 0 0 0 3 26 A 1 1 0 0 0 4 27 A 1 1 0 0 0 4.328125 26.943359 A 1 1 0 0 0 4.3378906 26.939453 L 4.3632812 26.931641 A 1 1 0 0 0 4.3691406 26.927734 L 7 26 L 5.5 24.5 L 4 23 z" />
                    </svg>
														</button>
													</form>
												</td>
												<td class="edit_product_td">
													<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
														<input type="hidden"
															name="${ParameterName.PARAMETER_COMMAND}"
															value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_FEEDS_AND_OTHER}">
														<input type="hidden"
															name="${ParameterName.PARAMETER_PRODUCT_ID}"
															value="${product.getKey().getId()}">
														<button class="bg-transparent w-100"
															style="padding-top: 5px; border: none;" role="button">
															<svg class="d-flex w-100" style="margin: 0 auto;"
																xmlns="http://www.w3.org/2000/svg" width="27"
																height="27" fill="currentColor" class="bi bi-three-dots"
																viewBox="0 0 16 16">
  <path
																	d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z" />
</svg>
														</button>
													</form>
												</td>
											</tr>
											<div
												class="position-fixed d-none d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 image_product_id image_product_id_o-${product.getKey().getId()}">
												<div
													class="position-relative w-100 d-flex flex-column image_product_id_inner image_product_id_o-${product.getKey().getId()}_inner"
													style="height: 60vh; max-width: 70vh">
													<div class="image_product_id_top">
														<div class="close_btn"
															onclick="closedProductImage('o-${product.getKey().getId()}')">
															<svg xmlns="http://www.w3.org/2000/svg"
																viewBox="0 0 24 24" width="25px" height="25px">
                          <path
																	d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                        </svg>
														</div>
														<div class="add_product_form_top_title">
															<h2 class="form_title text-center mb-3 text-lowercase">
																<fmt:message key="big_product_image.title" />
															</h2>
														</div>
													</div>
													<div
														class="image_product_id_body h-100 d-flex justify-content-center align-items-center">
														<c:if test="${product.getKey().getImagePath() != null}">

															<img class=""
																style="max-height: 420px; max-width: 550px;" alt=""
																src=" ${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${product.getKey().getImagePath()}" />
														</c:if>
														<c:if test="${product.getKey().getImagePath() == null}">
															<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
																width="auto" height="45vh" viewBox="0 0 64 64">
<path
																	d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z"></path>
</svg>
														</c:if>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-12">
						<div
							class="position-fixed d-none d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 add_product_form">
							<div
								class="position-relative w-100 d-flex flex-column add_product_form_inner">
								<div class="add_product_form_top">
									<div class="add_product_form_top_title">
										<h2 class="form_title text-center mb-3 text-lowercase">
											<fmt:message
												key="admin_page.all_products.add_product_form.title" />
										</h2>
									</div>
									<div
										class="d-flex justify-content-center align-items-center mb-4 add_product_form_top_btns">
										<div class="close_btn" onclick="closeAddProductForm()">
											<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
												width="25px" height="25px">
                        <path
													d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
										</div>
										<button
											class="btn text-uppercase active add_product_form_top_btn add_product_form_top_btn_product_pet"
											role="button" onclick="openAddProductPetForm()">
											<fmt:message
												key="admin_page.all_products.add_product_form.add_pet.title" />
										</button>
										<button
											class="btn text-uppercase add_product_form_top_btn add_product_form_top_btn_other_product"
											role="button" onclick="openAddOtherProductForm()">
											<fmt:message
												key="admin_page.all_products.add_product_form.add_feeds_and_other.title" />
										</button>
									</div>
								</div>

								<div class="add_product_pet_form">
									<form class="add_product_pet_form_body" method="post"
										action="${pageContext.request.contextPath}/${ServletName.SERVLET_UPLOAD_IMAGE_NAME}"
										enctype="multipart/form-data">
										<div
											class="form-floating mb-3 add_product_pet_form_body_form_floating">
											<input type="file"
												class="form-control text-uppercase add_product_pet_form_body_input_img"
												id="admin_page.all_products.add_product_form.add_pet.lable.choose_image_product"
												name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.choose_image_product"/>' />
											<label
												class="text-lowercase add_product_pet_form_body_label_img"
												for="admin_page.all_products.add_product_form.add_pet.lable.choose_image_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_pet.lable.choose_image_product" />
												<span> *</span></label>
										</div>
										<div class="form-floating mb-3">
											<div class="input-group mb-3 mt-3">
												<label> <span class="span_input span_input_1">
														<input type="checkbox"
														name="${InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE}"
														value="${ParameterValue.ADMIN_PAGE_PRODUCT_FORM_WITHOUT_IMAGE}" />
														<fmt:message
															key="add_product_form_validated.add_pet.input.without_image" />
												</span>
												</label>
											</div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="admin_page.all_products.add_product_form.add_pet.lable.type_pet"
												name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_SPECIE}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.type_pet"/>' />
											<label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_pet.lable.type_pet"><fmt:message
													key="admin_page.all_products.add_product_form.add_pet.lable.type_pet" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control "
												id="admin_page.all_products.add_product_form.add_pet.lable.breed_pet"
												name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BREED}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.breed_pet"/>' />
											<label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_pet.lable.breed_pet"><fmt:message
													key="admin_page.all_products.add_product_form.add_pet.lable.breed_pet" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="date" class="form-control "
												id="admin_page.all_products.add_product_form.add_pet.lable.birth_date_pet"
												name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_BIRTH_DATE}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.birth_date_pet"/>' />
											<label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_pet.lable.birth_date_pet"><fmt:message
													key="admin_page.all_products.add_product_form.add_pet.lable.birth_date_pet" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="admin_page.all_products.add_product_form.add_pet.lable.price_pet"
												name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_PRICE}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.price_pet"/>'
												pattern="^(\d+)(\.\d{1,2})?$" /> <label
												class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_pet.lable.price_pet"><fmt:message
													key="admin_page.all_products.add_product_form.add_pet.lable.price_pet" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control "
												id="admin_page.all_products.add_product_form.add_pet.lable.discount_pet"
												name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_DISCOUNT}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.discount_pet"/>'
												pattern="^(\d+)(\.\d{1,2})?$" /> <label
												class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_pet.lable.discount_pet"><fmt:message
													key="admin_page.all_products.add_product_form.add_pet.lable.discount_pet" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="number" class="form-control "
												id="admin_page.all_products.add_product_form.add_pet.lable.number_unit_pet"
												name="${InputName.ADMIN_PAGE_CREATE_PET_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_pet.lable.number_unit_pet"/>'
												pattern="^(\d+)$" /> <label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_pet.lable.number_unit_pet"><fmt:message
													key="admin_page.all_products.add_product_form.add_pet.lable.number_unit_pet" /></label>
										</div>
										<div
											class="add_product_pet_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_ADMIN_PAGE_CREATE_PET_PRODUCT}" />
											<button class="btn add_product_pet_form_btn_submit"
												role="button">
												<fmt:message
													key="admin_page.all_products.add_product_form.add_pet.ok" />
											</button>
										</div>
									</form>
								</div>

								<div class="add_other_product_form d-none">
									<form class="add_product_pet_form_body" method="post"
										action="${pageContext.request.contextPath}/${ServletName.SERVLET_UPLOAD_IMAGE_NAME}"
										enctype="multipart/form-data">
										<div
											class="form-floating mb-3 add_other_product_form_body_form_floating">
											<input type="file"
												class="form-control text-uppercase add_other_product_form_body_input_img"
												id="admin_page.all_products.add_product_form.add_feeds_and_other.choose_image_product"
												name="${ParameterName.PARAMETER_PRODUCT_IMAGE}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.choose_image_product"/>' />
											<label
												class="text-lowercase add_product_pet_form_body_label_img"
												for="admin_page.all_products.add_product_form.add_feeds_and_other.choose_image_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.choose_image_product" />
												<span>*</span> </label>
										</div>
										<div class="form-floating mb-3">
											<div class="input-group mb-3 mt-3">
												<label> <span class="span_input span_input_1">
														<input type="checkbox"
														name="${InputName.ADMIN_PAGE_PRODUCT_FORM_INPUT_WITHOUT_IMAGE}"
														value="${ParameterValue.ADMIN_PAGE_PRODUCT_FORM_WITHOUT_IMAGE}" />
														<fmt:message
															key="add_product_form_validated.add_feeds_and_other.input.without_image" />
												</span>
												</label>
											</div>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.type_product"
												name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRODUCT_TYPE}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.type_product"/>' />
											<label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.type_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.type_product" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.brand_product"
												name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_BRAND}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.brand_product"/>' />
											<label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.brand_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.brand_product" /></label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.description_product"
												name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DESCRIPTION}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.description_product"/>' />
											<label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.description_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.description_product" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.types_pet_for_product"
												name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PET_TYPES}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.types_pet_for_product"/>' />
											<label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.types_pet_for_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.types_pet_for_product" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.price_product"
												name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_PRICE}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.price_product"/>'
												pattern="^(\d+)(\.\d{1,2})?$" /> <label
												class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.price_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.price_product" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.discount_product"
												name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_DISCOUNT}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.discount_product"/>'
												pattern="^(\d+)(\.\d{1,2})?$" /> <label
												class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.discount_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.discount_product" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="number" class="form-control"
												id="admin_page.all_products.add_product_form.add_feeds_and_other.lable.number_unit_product"
												name="${InputName.ADMIN_PAGE_CREATE_FEEDS_AND_OTHER_PRODUCT_FORM_INPUT_NUMBER_OF_UNITS_PRODUCT}"
												placeholder='<fmt:message key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.number_unit_product"/>'
												pattern="^(\d+)$" /> <label class="text-lowercase"
												for="admin_page.all_products.add_product_form.add_feeds_and_other.lable.number_unit_product"><fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.lable.number_unit_product" />
											</label>
										</div>
										<div class="form_description">
											<h5>
												<span>* </span>
												<fmt:message
													key="admin_page.all_products.add_product_form.form_description.field_is_not_reguired_for_enter_information" />
											</h5>
										</div>
										<div
											class="add_other_product_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_ADMIN_PAGE_CREATE_FEED_AND_OTHER_PRODUCT }" />
											<button class="btn add_other_product_form_btn_submit"
												role="button">
												<fmt:message
													key="admin_page.all_products.add_product_form.add_feeds_and_other.ok" />
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${user == null}">
				<div class="row">
					<div class="col-12">
						<h3
							class="text-center d-flex flex-column justify-content-center align-items-center"
							style="min-height: 45.3vh">
							<fmt:message key="admin_page.all_products.end_session.message" />
						</h3>
					</div>
				</div>
			</c:if>
		</div>
	</main>
	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/personal_account.js"></script>
</body>
</html>