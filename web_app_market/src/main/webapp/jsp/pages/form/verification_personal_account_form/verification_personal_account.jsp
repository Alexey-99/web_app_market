<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.RegistrationUserCommand"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/items/header_top.css" />
<link rel="stylesheet"
	href="css/items/verification_personal_account.css" />
<title>verification_registration_information.jsp</title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
</head>
<body>
	<header class="header pb-5" style="height: 100vh;">
		<div class="container">
			<div class="row header_top">
				<div class="col-12">
					<div
						class="position-fixed d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 verification_personal_account">
						<div
							class="position-relative w-100 d-flex flex-column verification_personal_account_inner">
							<div
								class="d-flex justify-content-center align-items-center mb-4 verification_personal_account_top">
								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_SHOW_HOME_PAGE}" />
									<button class="close_btn border-0 bg-transparent" role="button">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
											width="25px" height="25px">
                        <path
												d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
									</button>
								</form>
								<h4 class="text-center">Введите код отправленный на email
									"${user.getEmail()}"</h4>
							</div>
							<div
								class="d-flex justify-content-center align-items-center flex-column verification_personal_account_body">
								<form class="w-100 verification_personal_account_form"
									action="Controller">
									<div
										class="form-floating mb-3 w-100 verification_personal_account_body">
										<input type="text"
											class="form-control text-center verification_personal_account_form_body_input"
											id="floatingInputCode"
											name="${InputName.VERIFICATION_PERSON_ACCOUNT_INPUT_CODE}"
											placeholder="name@example.com" /> <label
											class="text-lowercase verification_personal_account_form_body_label w-100 text-center"
											for="floatingInputCode">Введите пароль ...</label>
									</div>
									<div
										class="verification_personal_account_form_fotter d-flex justify-content-end">
										<div class="verification_personal_account_form_fotter_btn">
											<input type="hidden" name="command"
												value="${CommandName.COMMAND_VERIFICATION_PERSONAL_ACCOUNT}" />
											<button
												class="btn verification_personal_account_btn_submit text-uppercase"
												role="button">ок</button>
										</div>
									</div>
								</form>
								<form action="Controller">
									<input type="hidden" name="command"
										value="${CommandName.COMMAND_SEND_ONE_MORE_TIME_VERIFICATION_CODE}" />
									<button
										class="btn verification_personal_account_btn_submit text-uppercase"
										role="button">отправить ещё раз</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap/bootstrap.bundle.js"></script>
</body>
</html>