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
<title><fmt:message key="sign_in_and_registartion_form.title" /></title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- registration_input_exception_type_and_message = AttributeName.ATTRIBUTE_REGISTRATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
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

								<button
									class="btn sign_in_and_registration_form_top_btn sign_in_and_registration_form_top_btn_sign_in"
									role="button" onclick="openSignInForm()">
									<fmt:message
										key="header_top.sign_in_and_registartion_form.sign_in_and_registartion_form_top.sign_in" />
								</button>
								<button
									class="btn active sign_in_and_registration_form_top_btn sign_in_and_registration_form_top_btn_registration"
									role="button" onclick="openRegistrationForm()">
									<fmt:message
										key="header_top.sign_in_and_registartion_form.sign_in_and_registartion_form_top.registration" />
								</button>
							</div>

							<div class="sign_in_form d-none">
								<div class="sign_in_form_top">
									<h2 class="form_title text-center mb-3 text-lowercase">
										<fmt:message
											key="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_top.form_title.login_and_password" />
									</h2>
								</div>
								<form class="sign_in_form_body" method="post"
									action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
									<div class="form-floating mb-3">
										<input type="text" class="form-control" required
											id="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_body.input_lable.login"
											name="${InputName.SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_LOGIN}"
											placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_body.input_lable.login"/>'
											pattern="(\w|[А-Яа-я]){1,255}" /> <label
											class="text-lowercase"
											for="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_body.input_lable.login">
											<fmt:message
												key="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_body.input_lable.login" />
										</label>
									</div>
									<div class="input-group mb-3">
										<div class="form-floating ">
											<input type="password"
												class="form-control sign_in_form_input_password" required
												id="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_body.input_lable.password"
												name="${InputName.SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_PASSWORD}"
												placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_body.input_lable.password"/>'
												pattern="(\w|[А-Яа-я]){5,255}" /> <label
												class="text-lowercase"
												for="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_body.input_lable.password">
												<fmt:message
													key="header_top.sign_in_and_registartion_form.sign_in_form.sign_in_form_body.input_lable.password" />
											</label>

										</div>
										<span class="input-group-text">
											<div class="btn sign_in_form_password_btn"
												onclick="showPasswordSignInFormInput()">
												<svg class="sign_in_form_open_eye"
													xmlns="http://www.w3.org/2000/svg" height="25"
													viewBox="0 96 960 960" width="25">
                          <path
														d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                        </svg>
												<svg class="sign_in_form_close_eye d-none"
													xmlns="http://www.w3.org/2000/svg" height="25"
													viewBox="0 96 960 960" width="25">
                          <path
														d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                        </svg>
											</div>
										</span>
									</div>
									<div class="sign_in_form_fotter d-flex justify-content-end">
										<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
											value="${CommandName.COMMAND_SIGN_IN_PERSON_ACCOUNT}" />
										<button class="btn sign_in_form_btn_submit" role="button">
											<fmt:message
												key="header_top.sign_in_and_registartion_form.registartion_form.footer.button.ok" />
										</button>
									</div>
								</form>
							</div>

							<div class="registration_form">
								<div class="registration_form_top">
									<h2 class="form_title text-center mb-3 text-lowercase">
										<fmt:message
											key="header_top.sign_in_and_registartion_form.registartion_form.title.registration" />
									</h2>
								</div>
								<c:if
									test="${registration_input_exception_type_and_message == null || registration_input_exception_type_and_message.isEmpty()}">
									<form class="registration_form_body" method="post"
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<div class="form-floating mb-3">
											<input type="email" class="form-control"
												id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address"
												required name="${InputName.REGISTRATION_INPUT_USER_EMAIL}"
												value='<c:if test="${user.getEmail() != null && !user.getEmail().isBlank()}">${user.getEmail()}</c:if>'
												placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address"/>'
												pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})" />
											<label class="text-lowercase registration_form_body_label"
												for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address"><fmt:message
													key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address" />
											</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login"
												required name="${InputName.REGISTRATION_INPUT_USER_LOGIN}"
												value='<c:if test="${user.getLogin() != null && !user.getLogin().isBlank()}">${user.getLogin()}</c:if>'
												placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login"/>'
												pattern="(\w|[А-Яа-я]){1,255}" /> <label
												class="text-lowercase registration_form_body_label"
												for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login">
												<fmt:message
													key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login" />
											</label>
										</div>
										<div class="input-group mb-3">
											<div class="form-floating">
												<input type="password"
													class="form-control registration_form_input_password"
													id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password"
													required
													name="${InputName.REGISTRATION_INPUT_USER_PASSWORD}"
													value='<c:if test="${user.getPassword() != null && !user.getPassword().isBlank()}">${user.getPassword()}</c:if>'
													placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password"/>'
													pattern="(\w|[А-Яа-я]){5,255}" /> <label
													class="text-lowercase registration_form_body_label"
													for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password">
													<fmt:message
														key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password" />
												</label>

											</div>
											<span class="input-group-text mb-3">
												<div class="btn registration_form_password_btn mt-0 "
													onclick="showPasswordRegistrationFormInput()">
													<svg class="registration_form_open_eye"
														xmlns="http://www.w3.org/2000/svg" height="25"
														viewBox="0 96 960 960" width="25">
                          <path
															d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                        </svg>
													<svg class="registration_form_close_eye d-none"
														xmlns="http://www.w3.org/2000/svg" height="25"
														viewBox="0 96 960 960" width="25">
                          <path
															d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                        </svg>
												</div>
											</span>
										</div>
										<div class="form_description">
											<h5>
												<span>* </span>
												<fmt:message
													key="header_top.sign_in_and_registartion_form.registartion_form.body.form_description.field_is_not_reguired_for_enter_information" />
											</h5>
										</div>
										<div
											class="registration_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_REGISTRATION_USER}" />
											<button class="btn registration_form_btn_submit"
												role="button">
												<fmt:message
													key="header_top.sign_in_and_registartion_form.registartion_form.footer.button.ok" />
											</button>
										</div>
									</form>
								</c:if>
								<c:if
									test="${registration_input_exception_type_and_message != null && !registration_input_exception_type_and_message.isEmpty()}">
									<form class="registration_form_body" method="post"
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<c:if
											test="${registration_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL)}">
											<div class="form-floating mb-3">
												<input type="email" class="form-control is-invalid"
													id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address"
													name="${InputName.REGISTRATION_INPUT_USER_EMAIL}" required
													value='<c:if test="${user.getEmail() != null && !user.getEmail().isBlank()}">${user.getEmail()}</c:if>'
													placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address"/>'
													pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})" />
												<label class="text-lowercase registration_form_body_label"
													for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address">
													<fmt:message
														key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address" />
													<c:if
														test="${user.getEmail() == null || user.getEmail().isBlank()}">(<fmt:message
															key="sign_in_and_registartion_form.description.not_entered" />)</c:if>
												</label>
												<div class="invalid-feedback">
													${registration_input_exception_type_and_message.get(TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL)}
												</div>
											</div>
										</c:if>
										<c:if
											test="${!registration_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_EMAIL)}">
											<div class="form-floating mb-3">
												<input type="email" class="form-control is-valid"
													id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address"
													name="${InputName.REGISTRATION_INPUT_USER_EMAIL}" required
													value='<c:if test="${user.getEmail() != null && !user.getEmail().isBlank()}">${user.getEmail()}</c:if>'
													placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address"/>'
													pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})" />
												<label class="text-lowercase registration_form_body_label"
													for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address">
													<fmt:message
														key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.email_address" />
													<c:if
														test="${user.getEmail() == null || user.getEmail().isBlank()}">(<fmt:message
															key="sign_in_and_registartion_form.description.not_entered" />)</c:if>
												</label>
												<div class="valid-feedback">
													<fmt:message
														key="sign_in_and_registartion_form.valid_feed_back" />
												</div>
											</div>
										</c:if>
										<c:if
											test="${registration_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_LOGIN)}">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-invalid"
													id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login"
													name="${InputName.REGISTRATION_INPUT_USER_LOGIN}" required
													value='<c:if test="${user.getLogin() != null && !user.getLogin().isBlank()}">${user.getLogin()}</c:if>'
													placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login"/>'
													pattern="(\w|[А-Яа-я]){1,255}" /> <label
													class="text-lowercase registration_form_body_label"
													for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login"><fmt:message
														key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login" />
													<c:if
														test="${user.getLogin() == null || user.getLogin().isBlank()}">(<fmt:message
															key="sign_in_and_registartion_form.description.not_entered" />)</c:if>
												</label>
												<div class="invalid-feedback">
													${registration_input_exception_type_and_message.get(TypeInputExeception.TYPY_INPUT_EXCEPTION_LOGIN)}
												</div>
											</div>
										</c:if>
										<c:if
											test="${!registration_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_LOGIN)}">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-valid"
													id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login"
													name="${InputName.REGISTRATION_INPUT_USER_LOGIN}" required
													value='<c:if test="${user.getLogin() != null && !user.getLogin().isBlank()}">${user.getLogin()}</c:if>'
													placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login"/>'
													pattern="(\w|[А-Яа-я]){1,255}" /> <label
													class="text-lowercase registration_form_body_label"
													for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login"><fmt:message
														key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.login" />
													<c:if
														test="${user.getLogin() == null || user.getLogin().isBlank()}">(<fmt:message
															key="sign_in_and_registartion_form.description.not_entered" />)</c:if>
												</label>
												<div class="valid-feedback">
													<fmt:message
														key="sign_in_and_registartion_form.valid_feed_back" />
												</div>
											</div>
										</c:if>
										<c:if
											test="${registration_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_PASSWORD)}">
											<div class="password_form">
												<div class="input-group mb-3">
													<div class="form-floating">
														<input type="password"
															class="form-control registration_form_input_password is-invalid"
															required
															id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password"
															name="${InputName.REGISTRATION_INPUT_USER_PASSWORD}"
															value='<c:if test="${user.getPassword() != null && !user.getPassword().isBlank()}">${user.getPassword()}</c:if>'
															placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password"/>'
															pattern="(\w|[А-Яа-я]){5,255}" /> <label
															class="text-lowercase registration_form_body_label"
															for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password">
															<fmt:message
																key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password" />
															<c:if
																test="${user.getPassword() == null || user.getPassword().isBlank()}">(<fmt:message
																	key="sign_in_and_registartion_form.description.not_entered" />)</c:if>
														</label>
														<div class="invalid-feedback">
															${registration_input_exception_type_and_message.get(TypeInputExeception.TYPY_INPUT_EXCEPTION_PASSWORD)}
														</div>
													</div>
													<span class="input-group-text mb-5">
														<div class="btn registration_form_password_btn"
															onclick="showPasswordRegistrationFormInput()">
															<svg class="registration_form_open_eye"
																xmlns="http://www.w3.org/2000/svg" height="25"
																viewBox="0 96 960 960" width="25">
                          <path
																	d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                        </svg>
															<svg class="registration_form_close_eye d-none"
																xmlns="http://www.w3.org/2000/svg" height="25"
																viewBox="0 96 960 960" width="25">
                          <path
																	d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                        </svg>
														</div>
													</span>
												</div>
											</div>
										</c:if>
										<c:if
											test="${!registration_input_exception_type_and_message.containsKey(TypeInputExeception.TYPY_INPUT_EXCEPTION_PASSWORD)}">
											<div class="password_form">
												<div class="input-group mb-3">
													<div class="form-floating">
														<input type="password"
															class="form-control registration_form_input_password is-valid"
															required
															id="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password"
															name="${InputName.REGISTRATION_INPUT_USER_PASSWORD}"
															value='<c:if test="${user.getPassword() != null && !user.getPassword().isBlank()}">${user.getPassword()}</c:if>'
															placeholder='<fmt:message key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password"/>'
															pattern="(\w|[А-Яа-я]){5,255}" /> <label
															class="text-lowercase registration_form_body_label"
															for="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password">
															<fmt:message
																key="header_top.sign_in_and_registartion_form.registartion_form.body.input_lable.password" />
															<c:if
																test="${user.getPassword() == null || user.getPassword().isBlank()}">(<fmt:message
																	key="sign_in_and_registartion_form.description.not_entered" />)</c:if>
														</label>
														<div class="valid-feedback">
															<fmt:message
																key="sign_in_and_registartion_form.valid_feed_back" />
														</div>
													</div>
													<span class="input-group-text mb-3">
														<div class="btn registration_form_password_btn"
															onclick="showPasswordRegistrationFormInput()">
															<svg class="registration_form_open_eye"
																xmlns="http://www.w3.org/2000/svg" height="25"
																viewBox="0 96 960 960" width="25">
                          <path
																	d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                        </svg>
															<svg class="registration_form_close_eye d-none"
																xmlns="http://www.w3.org/2000/svg" height="25"
																viewBox="0 96 960 960" width="25">
                          <path
																	d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                        </svg>
														</div>
													</span>
												</div>
											</div>
										</c:if>
										<div class="form_description">
											<h5>
												<span>* </span>
												<fmt:message
													key="header_top.sign_in_and_registartion_form.registartion_form.body.form_description.field_is_not_reguired_for_enter_information" />
											</h5>
										</div>
										<div
											class="registration_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_REGISTRATION_USER}" />
											<button class="btn registration_form_btn_submit"
												role="button">
												<fmt:message
													key="header_top.sign_in_and_registartion_form.registartion_form.footer.button.ok" />
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
	<script src="js/header_top.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/sign_in_and_registration_form.js"></script>
</body>
</html>