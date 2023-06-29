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
<title>personal_account_person_infomation.jsp</title>
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
							class="position-fixed justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 login_password_form">
							<div
								class="position-relative w-100 d-flex flex-column login_password_form_inner">
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
									<h2 class="form_title text-center mb-3">Изменение пароля</h2>
								</div>
								<c:if
									test="${changing_login_input_exception_type_and_message.isEmpty()}">
									<form class=""
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
										method="get">
										<div
											class="form-floating mb-3 d-flex justify-content-center align-items-center">
											<input type="password"
												class="form-control login_password_form_input_password"
												id="floatingInputPassword"
												name="${InputName.CHANGING_LOGIN_AND_PASSWORD_INPUT_USER_PASSWORD}"
												value="${user.getPassword()}" placeholder="123456" /> <label
												class="text-lowercase" for="floatingInputPassword">Пароль</label>
											<div class="btn login_password_form_password_btn"
												onclick="showPasswordInLoginPasswordFormInput()">
												<svg class="login_password_form_open_eye"
													xmlns="http://www.w3.org/2000/svg" height="25"
													viewBox="0 96 960 960" width="25">
                        <path
														d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                      </svg>
												<svg class="login_password_form_close_eye d-none"
													xmlns="http://www.w3.org/2000/svg" height="25"
													viewBox="0 96 960 960" width="25">
                        <path
														d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                      </svg>
											</div>
										</div>
										<div
											class="login_password_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CHANGE_PASSWORD}" />
											<button class="btn login_password_form_btn_submit"
												role="button">готово</button>
										</div>
									</form>
								</c:if>
								<c:if
									test="${!changing_login_input_exception_type_and_message.isEmpty()}">
									<form class=""
										action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
										method="get">
										<c:if
											test="${changing_login_and_password_input_exception_type_and_message.containsKey(ChangeLoginAndPasswordCommand.TYPY_INPUT_EXCEPTION_PASSWORD)}">
											<div
												class="form-floating mb-3 d-flex justify-content-center align-items-center">
												<input type="password"
													class="form-control login_password_form_input_password is-invalid"
													id="floatingInputPassword"
													name="${InputName.CHANGING_LOGIN_AND_PASSWORD_INPUT_USER_PASSWORD}"
													value="${user.getPassword()}" placeholder="123456" />
												<div class="invalid-feedback">${changing_login_and_password_input_exception_type_and_message.get(ChangeLoginAndPasswordCommand.TYPY_INPUT_EXCEPTION_PASSWORD)}</div>
												<label class="text-lowercase" for="floatingInputPassword">Пароль</label>
												<div class="btn login_password_form_password_btn"
													onclick="showPasswordInLoginPasswordFormInput()">
													<svg class="login_password_form_open_eye"
														xmlns="http://www.w3.org/2000/svg" height="25"
														viewBox="0 96 960 960" width="25">
                        <path
															d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                      </svg>
													<svg class="login_password_form_close_eye d-none"
														xmlns="http://www.w3.org/2000/svg" height="25"
														viewBox="0 96 960 960" width="25">
                        <path
															d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                      </svg>
												</div>
											</div>
										</c:if>
										<c:if
											test="${!changing_login_and_password_input_exception_type_and_message.containsKey(ChangeLoginAndPasswordCommand.TYPY_INPUT_EXCEPTION_PASSWORD)}">
											<div
												class="form-floating mb-3 d-flex justify-content-center align-items-center">
												<input type="password"
													class="form-control login_password_form_input_password"
													id="floatingInputPassword"
													name="${InputName.CHANGING_LOGIN_AND_PASSWORD_INPUT_USER_PASSWORD}"
													value="${user.getPassword()}" placeholder="123456" />
												<div class="valid-feedback">Все хорошо!</div>
												<label class="text-lowercase" for="floatingInputPassword">Пароль</label>
												<div class="btn login_password_form_password_btn"
													onclick="showPasswordInLoginPasswordFormInput()">
													<svg class="login_password_form_open_eye"
														xmlns="http://www.w3.org/2000/svg" height="25"
														viewBox="0 96 960 960" width="25">
                        <path
															d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                      </svg>
													<svg class="login_password_form_close_eye d-none"
														xmlns="http://www.w3.org/2000/svg" height="25"
														viewBox="0 96 960 960" width="25">
                        <path
															d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                      </svg>
												</div>
											</div>
										</c:if>
										<div
											class="login_password_form_fotter d-flex justify-content-end">
											<input type="hidden"
												name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CHANGE_PASSWORD}" />
											<button class="btn login_password_form_btn_submit"
												role="button">готово</button>
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