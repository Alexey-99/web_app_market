<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.input.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page import="by.koroza.zoo_market.model.entity.status.UserRole"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.admin.change.user.status.ChangeUserStatusCommand"%>
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
		key="change_user_status.form_validated.title" /></title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- admin_page_change_user_status_input_exception_type_and_message = AttributeName.ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
</head>
<body>
	<%@ include file="/jsp/items/header_block_header_top.jsp"%>
	<main class="mb-5">
		<div class="container">
			<c:if test="${user != null}">
				<div class="row">
					<div class="col-12">
						<div
							class="position-fixed d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 change_user_status_form">
							<div
								class="position-relative w-100 d-flex flex-column change_user_status_form_inner">
								<div class="change_user_status_form_top">
									<div
										class="d-flex justify-content-center align-items-center mb-4 change_user_status_form_top">

										<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_ADMIN_PAGE }">
											<button class="close_btn" role="button">
												<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
													width="25px" height="25px">
                        <path
														d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
                      </svg>
											</button>
										</form>
										<h2 class="form_title text-center mb-3 text-lowercase">
											<fmt:message key="change_user_status.form_validated.title" />
										</h2>
									</div>
								</div>
								<c:if
									test="${admin_page_change_user_status_input_exception_type_and_message == null || admin_page_change_user_status_input_exception_type_and_message.isEmpty()}">
									<form class="change_user_status_form_body" method="post"
										action="${pageContext.request.contextPath}/${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="change_user_status.form_validated.input_lable.login"
												required
												name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_INPUT_LOGIN}"
												placeholder='<fmt:message key="change_user_status.form_validated.input_lable.login"/>' />
											<label class="text-lowercase"
												for="change_user_status.form_validated.input_lable.login">
												<fmt:message
													key="change_user_status.form_validated.input_lable.login" />
											</label>
										</div>
										<div class="mb-3">
											<select
												name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE}"
												class="form-select" required aria-label="select example">
												<option value="">
													<fmt:message
														key="change_user_status.form_validated.select.optional.select_role" />
												</option>
												<option value="${UserRole.USER.getIdRole()}">
													<fmt:message
														key="change_user_status.form_validated.select.optional.user" />
												</option>
												<option value="${UserRole.ADMIN.getIdRole()}">
													<fmt:message
														key="change_user_status.form_validated.select.optional.admin" />
												</option>
											</select>
										</div>
										<div
											class="change_user_status_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_ADMIN_PAGE_CHANGE_USER_STATUS}" />
											<button class="btn change_user_status_form_btn_submit"
												role="button">
												<fmt:message key="change_user_status.form_validated.ok" />
											</button>
										</div>
									</form>
								</c:if>
								<c:if
									test="${admin_page_change_user_status_input_exception_type_and_message != null && !admin_page_change_user_status_input_exception_type_and_message.isEmpty()}">
									<form class="change_user_status_form_body" method="post"
										action="${pageContext.request.contextPath}/${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<c:if
											test="${admin_page_change_user_status_input_exception_type_and_message.containsKey(ChangeUserStatusCommand.INPUT_EXCEPTION_TYPE_LOGIN) }">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-invalid"
													id="change_user_status.form_validated.input_lable.login"
													required
													name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_INPUT_LOGIN}"
													placeholder='<fmt:message key="change_user_status.form_validated.input_lable.login"/>' />
												<label class="text-lowercase"
													for="change_user_status.form_validated.input_lable.login">
													<fmt:message
														key="change_user_status.form_validated.input_lable.login" />
												</label>
												<div class="invalid-feedback">
													${admin_page_change_user_status_input_exception_type_and_message.get(ChangeUserStatusCommand.INPUT_EXCEPTION_TYPE_LOGIN)}
												</div>
											</div>
										</c:if>
										<c:if
											test="${!admin_page_change_user_status_input_exception_type_and_message.containsKey(ChangeUserStatusCommand.INPUT_EXCEPTION_TYPE_LOGIN) }">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-valid"
													id="change_user_status.form_validated.input_lable.login"
													required
													name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_INPUT_LOGIN}"
													placeholder='<fmt:message key="change_user_status.form_validated.input_lable.login"/>' />
												<label class="text-lowercase"
													for="change_user_status.form_validated.input_lable.login">
													<fmt:message
														key="change_user_status.form_validated.input_lable.login" />
												</label>
												<div class="valid-feedback">
													<fmt:message
														key="change_user_status.form_validated.positive_feed_back" />
												</div>
											</div>
										</c:if>
										<c:if
											test="${admin_page_change_user_status_input_exception_type_and_message.containsKey(ChangeUserStatusCommand.INPUT_EXCEPTION_TYPE_ROLE) }">
											<div class="mb-3">
												<select
													name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE}"
													class="form-select is-invalid" required
													aria-label="select example">
													<option value="">
														<fmt:message
															key="change_user_status.form_validated.select.optional.select_role" />
													</option>
													<option value="${UserRole.USER.getIdRole()}">
														<fmt:message
															key="change_user_status.form_validated.select.optional.user" />
													</option>
													<option value="${UserRole.ADMIN.getIdRole()}">
														<fmt:message
															key="change_user_status.form_validated.select.optional.admin" />
													</option>
												</select>
												<div class="invalid-feedback">
													${admin_page_change_user_status_input_exception_type_and_message.get(ChangeUserStatusCommand.INPUT_EXCEPTION_TYPE_ROLE)}
												</div>
											</div>
										</c:if>
										<c:if
											test="${!admin_page_change_user_status_input_exception_type_and_message.containsKey(ChangeUserStatusCommand.INPUT_EXCEPTION_TYPE_ROLE) }">
											<div class="mb-3">
												<select
													name="${InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE}"
													class="form-select " required aria-label="select example">
													<option value="">
														<fmt:message
															key="change_user_status.form_validated.select.optional.select_role" />
													</option>
													<option value="${UserRole.USER.getIdRole()}">
														<fmt:message
															key="change_user_status.form_validated.select.optional.user" />
													</option>
													<option value="${UserRole.ADMIN.getIdRole()}">
														<fmt:message
															key="change_user_status.form_validated.select.optional.admin" />
													</option>
												</select>
											</div>
										</c:if>
										<div
											class="change_user_status_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_ADMIN_PAGE_CHANGE_USER_STATUS}" />
											<button class="btn change_user_status_form_btn_submit"
												role="button">
												<fmt:message key="change_user_status.form_validated.ok" />
											</button>
										</div>
									</form>

								</c:if>
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
							<fmt:message
								key="change_user_status.form_validated.end_session.message" />
						</h3>
					</div>
				</div>
			</c:if>
		</div>
	</main>
</body>
</html>