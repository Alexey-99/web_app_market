<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.input.InputName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterValue"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="${PagePathName.PAGE_CONTENT_PROPERTIES}" />
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
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
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
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
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
									<span> </span>"${user.getEmail()}"<span>)</span>
								</h4>
							</div>
							<div
								class="d-flex justify-content-center align-items-center flex-column verification_personal_account_body">
								<form class="w-100 verification_personal_account_form"
									action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<div
										class="form-floating mb-3 w-100 verification_personal_account_body">
										<input type="text"
											class="form-control text-center is-invalid verification_personal_account_form_body_input"
											id="verification_email.lable.massege.enter_password" required
											name="${InputName.CONFIRMATION_EMAIL_INPUT_CODE}"
											placeholder='<fmt:message key="verification_email.lable.massege.enter_password"/>' />
										<label
											class="text-lowercase verification_personal_account_form_body_label w-100 text-center"
											for="verification_email.lable.massege.enter_password">
											<fmt:message
												key="verification_email.lable.massege.enter_password" />
											...
										</label>
										<div class="invalid-feedback">
											<fmt:message
												key="verification_email.lable.invalid_feed_back.entered_incorrect_code" />
										</div>
									</div>
									<div
										class="verification_personal_account_form_fotter d-flex justify-content-end">
										<div class="verification_personal_account_form_fotter_btn">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CONFIRMATION_EMAIL}" />
											<button
												class="btn verification_personal_account_btn_submit text-uppercase"
												role="button">
												<fmt:message key="verification_email.ok" />
											</button>
										</div>
									</div>
								</form>
								<div class="mt-3">
									<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
										method="get">
										<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
											value="${CommandName.COMMAND_SEND_ONE_MORE_TIME_CONFIMATION_EMAIL_CODE}" />
										<button
											class="btn verification_personal_account_btn_submit text-uppercase"
											role="button">
											<fmt:message
												key="verification_email.send_verification_code_one_more_time" />
										</button>
									</form>
									<form class="mt-3"
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
										method="get">
										<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
											value="${CommandName.COMMAND_SHOW_CHANGE_EMAIL_FORM}" />
										<button class="btn verification_personal_account_btn_submit "
											role="button">
											<fmt:message key="verification_email.change_email" />
										</button>
									</form>
									<form class="mt-3"
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
										method="get">
										<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
											value="${CommandName.COMMAND_SIGN_OUT_PERSONAL_ACCOUNT}" />
										<button class="btn verification_personal_account_btn_submit "
											role="button">
											<fmt:message key="verification_email.exit" />
										</button>
									</form>
								</div>
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