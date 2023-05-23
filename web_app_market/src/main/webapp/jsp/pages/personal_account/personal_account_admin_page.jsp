<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/items/main_header.css" />
<link rel="stylesheet" href="css/pages/personal_account.css" />
<title>Insert title here</title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
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
								<button class="btn btn-primary person_account_menu_link w-100"
									role="button">Заказы</button>
							</form>
							<c:if
								test="${user.getRole().getIdRole() == UserRole.ADMIN.getIdRole()}">
								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE}" />
									<button class="btn btn-primary active w-100 person_account_menu_link"
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
					<div
						class="col-md-10 col-sm-9 col-xs-8 d-flex flex-column justify-content-center align-items-center person_account_admin_menu">
						<div class="person_account_admin_menu_title">
							<h4>Выберите операцию</h4>
						</div>
						<div
							class="btn-group flex-column person_account_admin_menu_links mt-4"
							style="width: 80%; margin: 0 auto">
							<form action="Controller">
								<input type="hidden" name="command"
									value="show_personal_account_main_page" />
								<button
									class="btn btn-primary w-100 person_account_menu_link btn_first"
									role="button" aria-current="page">добавить товар</button>
							</form>
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_ADMIN_PAGE_SHOW_PRODUCTS_OFF_FILTER}" />
								<button class="btn btn-primary person_account_menu_link w-100"
									role="button">показать все товары</button>
							</form>
							<form action="Controller">
								<input type="hidden" name="command"
									value="show_personal_account_main_page" />
								<button class="btn btn-primary person_account_menu_link w-100"
									role="button">показать заказы</button>
							</form>
						</div>
					</div>
				</c:if>
				<c:if test="${user == null}">
					<div class="col-12">
						<h3
							class="text-center d-flex flex-column justify-content-center align-items-center"
							style="min-height: 45.3vh">Ваше время сессии завершено.
							Перезайдите в учётную запись.</h3>
					</div>
				</c:if>
			</div>
		</div>
	</main>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/personal_account.js"></script>
</body>
</html>