<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ParameterName"%>
<%@page import="by.koroza.zoo_market.web.command.name.ServletName"%>
<%@page import="by.koroza.zoo_market.web.command.name.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.user.change.ChangeLoginCommand"%>
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
<link rel="stylesheet" href="css/items/main_header.css" />
<link rel="stylesheet" href="css/pages/personal_account.css" />
<title><fmt:message
		key="personal_account_person_infomation.changing_login_form.title" />
</title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- changing_login_input_exception_type_and_message = AttributeName.ATTRIBUTE_CHANGING_LOGIN_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
</head>
<body>
	<%@ include file="/jsp/items/header_block_header_top.jsp"%>

	<main class="mb-5">
		<div class="container">
			<div class="row">
				<c:if test="${user != null}">
					<div class="col-12">
						<div
							class="position-fixed justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 change_login_form">
							<div
								class="position-relative w-100 d-flex flex-column change_login_form_inner">
								<div class="login_password_form_top">
									<form class=""
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
										method="get">
										<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
											value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE}" />
										<button class="close_btn" role="button">
											<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
												width="25px" height="25px">
	<path
													d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" /></svg>
										</button>
									</form>
									<h2 class="form_title text-center mb-3">
										<fmt:message
											key="personal_account_person_infomation.changing_login_form.title" />
									</h2>
								</div>
								<c:if
									test="${changing_login_input_exception_type_and_message == null || changing_login_input_exception_type_and_message.isEmpty()}">
									<form class=""
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
										method="get">
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="personal_account_person_infomation.changing_login_form.login"
												required name="${InputName.CHANGING_LOGIN_INPUT_USER_LOGIN}"
												value="${user.getLogin()}"
												placeholder='<fmt:message key="personal_account_person_infomation.changing_login_form.login"/>' />
											<label class="text-lowercase"
												for="personal_account_person_infomation.changing_login_form.login">
												<fmt:message
													key="personal_account_person_infomation.changing_login_form.login" />
											</label>
										</div>
										<div
											class="login_password_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CHANGE_LOGIN}" />
											<button class="btn login_password_form_btn_submit"
												role="button">
												<fmt:message
													key="personal_account_person_infomation.changing_login_form.ok" />
											</button>
										</div>
									</form>
								</c:if>
								<c:if
									test="${changing_login_input_exception_type_and_message != null && !changing_login_input_exception_type_and_message.isEmpty()}">
									<form class=""
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
										method="get">
										<c:if
											test="${changing_login_input_exception_type_and_message.containsKey(ChangeLoginCommand.TYPY_INPUT_EXCEPTION_LOGIN)}">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-invalid"
													id="personal_account_person_infomation.changing_login_form.login"
													required
													name="${InputName.CHANGING_LOGIN_INPUT_USER_LOGIN}"
													placeholder='<fmt:message key="personal_account_person_infomation.changing_login_form.login"/>' />
												<div class="invalid-feedback">${changing_login_input_exception_type_and_message.get(ChangeLoginCommand.TYPY_INPUT_EXCEPTION_LOGIN)}</div>
												<label class="text-lowercase"
													for="personal_account_person_infomation.changing_login_form.login">
													<fmt:message
														key="personal_account_person_infomation.changing_login_form.login" />
												</label>
											</div>
										</c:if>
										<c:if
											test="${!changing_login_input_exception_type_and_message.containsKey(ChangeLoginCommand.TYPY_INPUT_EXCEPTION_LOGIN)}">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-valid"
													id="personal_account_person_infomation.changing_login_form.login"
													required
													name="${InputName.CHANGING_LOGIN_INPUT_USER_LOGIN}"
													value="${user.getLogin()}"
													placeholder='<fmt:message key="personal_account_person_infomation.changing_login_form.login"/>' />
												<div class="valid-feedback">
													<fmt:message
														key="personal_account_person_infomation.changing_login_form.valid_feed_back" />
												</div>
												<label class="text-lowercase"
													for="personal_account_person_infomation.changing_login_form.login">
													<fmt:message
														key="personal_account_person_infomation.changing_login_form.login" />
												</label>
											</div>
										</c:if>
										<div
											class="login_password_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CHANGE_LOGIN}" />
											<button class="btn login_password_form_btn_submit"
												role="button">
												<fmt:message
													key="personal_account_person_infomation.changing_login_form.ok" />
											</button>
										</div>
									</form>
								</c:if>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${user == null}">
					<div class="col-12">
						<h3
							class="text-center d-flex flex-column justify-content-center align-items-center"
							style="min-height: 45.3vh">
							<fmt:message
								key="personal_account_person_infomation.end_session.message" />
						</h3>
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