<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.input.InputName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.exception.TypeInputExeception"%>
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
	href="css/items/sign_in_and_registration_form.css" />
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- changing_email_input_exception_type_and_message = AttributeName.ATTRIBUTE_CHANGING_EMAIL_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
<title><fmt:message key="change_email_form.title" /></title>
</head>
<body>
	<header class="header pb-5" style="height: 100vh;">
		<div class="container">
			<div class="row header_top">
				<div class="col-12">
					<div
						class="position-fixed d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 sign_in_and_registration_form">
						<div
							class="position-relative w-100 d-flex flex-column sign_in_and_registration_form_inner">
							<div
								class="d-flex justify-content-center align-items-center mb-4 sign_in_and_registration_form_top_btns">
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
									<button class="close_btn" role="button">
										<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
											width="25px" height="25px">
	<path
												d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" /></svg>
									</button>
								</form>
								<div
									class="btn active sign_in_and_registration_form_top_btn sign_in_and_registration_form_top_btn_registration">
									<fmt:message key="change_email_form.title" />
								</div>
							</div>
							<div class="registration_form">
								<c:if
									test="${changing_email_input_exception_type_and_message == null || changing_email_input_exception_type_and_message.isEmpty()}">
									<form class="registration_form_body" method="post"
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<div class="form-floating mb-3">
											<input type="email" class="form-control"
												id="change_email_form.body.input_lable.email_address"
												required name="${InputName.REGISTRATION_INPUT_USER_EMAIL}"
												value='<c:if test="${user.getEmail() != null && !user.getEmail().isBlank()}">${user.getEmail()}</c:if>'
												placeholder='<fmt:message key="change_email_form.body.input_lable.email_address"/>'
												pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})" />
											<label class="text-lowercase registration_form_body_label"
												for="change_email_form.body.input_lable.email_address">
												<fmt:message
													key="change_email_form.body.input_lable.email_address" />
											</label>
										</div>
										<div
											class="registration_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CHANGE_EMAIL}" />
											<button class="btn registration_form_btn_submit"
												role="button">
												<fmt:message key="change_email_form.ok" />
											</button>
										</div>
									</form>
								</c:if>
								<c:if
									test="${changing_email_input_exception_type_and_message != null && !changing_email_input_exception_type_and_message.isEmpty()}">
									<form class="registration_form_body" method="post"
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<c:if
											test="${changing_email_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL)}">
											<div class="form-floating mb-3">
												<input type="email" class="form-control is-invalid"
													id="change_email_form.body.input_lable.email_address"
													name="${InputName.REGISTRATION_INPUT_USER_EMAIL}" required
													value='<c:if test="${user.getEmail() != null && !user.getEmail().isBlank()}">${user.getEmail()}</c:if>'
													placeholder='<fmt:message key="change_email_form.body.input_lable.email_address"/>'
													pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})" />
												<label class="text-lowercase registration_form_body_label"
													for="change_email_form.body.input_lable.email_address">
													<fmt:message
														key="change_email_form.body.input_lable.email_address" />
													<c:if
														test="${user.getEmail() == null || user.getEmail().isBlank()}">(<fmt:message
															key="change_email_form.description.not_entered" />)</c:if>
												</label>
												<div class="invalid-feedback">
													${changing_email_input_exception_type_and_message.get(TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL)}
												</div>
											</div>
										</c:if>
										<c:if
											test="${!changing_email_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL)}">
											<div class="form-floating mb-3">
												<input type="email" class="form-control is-valid"
													id="change_email_form.body.input_lable.email_address"
													name="${InputName.REGISTRATION_INPUT_USER_EMAIL}" required
													value='<c:if test="${user.getEmail() != null && !user.getEmail().isBlank()}">${user.getEmail()}</c:if>'
													placeholder='<fmt:message key="change_email_form.body.input_lable.email_address"/>'
													pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})" />
												<label class="text-lowercase registration_form_body_label"
													for="change_email_form.body.input_lable.email_address">
													<fmt:message
														key="change_email_form.body.input_lable.email_address" />
													<c:if
														test="${user.getEmail() == null || user.getEmail().isBlank()}">(<fmt:message
															key="change_email_form.description.not_entered" />)</c:if>
												</label>
												<div class="valid-feedback">
													<fmt:message key="change_email_form.valid_feed_back" />
												</div>
											</div>
										</c:if>
										<div
											class="registration_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CHANGE_EMAIL}" />
											<button class="btn registration_form_btn_submit"
												role="button">
												<fmt:message key="change_email_form.ok" />
											</button>
										</div>
									</form>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<%@ include file="/jsp/items/footer.jsp"%>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/header_top.js"></script>
	<script src="js/sign_in_and_registration_form.js"></script>
</body>
</html>