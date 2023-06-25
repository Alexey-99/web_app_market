<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.PagePathName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ServletName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterName"%>
<fmt:setLocale value="${AttributeName.ATTRIBUTE_SESSION_LOCALE}"
	scope="session" />
<fmt:setBundle
	basename="${PagePathName.PAGE_CONTENT_PROPERTIES}${locale}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/items/header_top.css" />
<link rel="stylesheet"
	href="css/items/verification_personal_account.css" />
<title><fmt:message key="verification_email.title" /></title>
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
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_HOME_PAGE}" />
									<button class="close_btn border-0 bg-transparent" role="button">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
											width="25px" height="25px">
                        <path
												d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
									</button>
								</form>
								<h4 class="text-center">
									<fmt:message
										key="verification_email.massege.enter_verification_code" />
									<span> </span> "${user.getEmail()}"<span>)</span>
								</h4>
							</div>
							<div
								class="d-flex justify-content-center align-items-center flex-column verification_personal_account_body">
								<form class="w-100 verification_personal_account_form"
									action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
									<div
										class="form-floating mb-3 w-100 verification_personal_account_body">
										<input type="text"
											class="form-control text-center verification_personal_account_form_body_input"
											id="verification_email.lable.massege.enter_password"
											name="${InputName.VERIFICATION_PERSON_ACCOUNT_INPUT_CODE}"
											placeholder='<fmt:message key="verification_email.lable.massege.enter_password"/>' />
										<label
											class="text-lowercase verification_personal_account_form_body_label w-100 text-center"
											for="verification_email.lable.massege.enter_password">
											<fmt:message
												key="verification_email.lable.massege.enter_password" />
											...
										</label>
									</div>
									<div
										class="verification_personal_account_form_fotter d-flex justify-content-end">
										<div class="verification_personal_account_form_fotter_btn">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_VERIFICATION_PERSONAL_ACCOUNT}" />
											<button
												class="btn verification_personal_account_btn_submit text-uppercase"
												role="button">
												<fmt:message key="verification_email.ok" />
											</button>
										</div>
									</div>
								</form>
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SEND_ONE_MORE_TIME_VERIFICATION_CODE}" />
									<button
										class="btn verification_personal_account_btn_submit text-uppercase"
										role="button">
										<fmt:message
											key="verification_email.send_verification_code_one_more_tim" />
									</button>
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