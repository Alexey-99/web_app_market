<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.input.InputName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.exception.TypeInputExeception"%>
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
<!-- changing_person_information_input_exception_type_and_message = AttributeName.ATTRIBUTE_CHANGING_PERSON_INFOMATION_INPUT_EXCEPTION_TYPE_AND_MASSAGE -->
</head>
<body>
	<%@ include file="/jsp/items/header_block_header_top.jsp"%>

	<main class="mb-5">
		<div class="container">
			<div class="row">
				<c:if test="${user != null}">

					<div class="col-12">
						<div
							class="position-fixed justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 general_infomation_form">
							<div
								class="position-relative w-100 d-flex flex-column general_infomation_form_inner">
								<div class="general_infomation_form_top">
									<form class="" action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
											value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE}" />
										<button class="close_btn" role="button">
											<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
												width="25px" height="25px">
	<path
													d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" /></svg>
										</button>
									</form>
									<h2 class="form_title text-center mb-3">Изменение общей
										информации</h2>
								</div>
								<c:if
									test="${changing_person_information_input_exception_type_and_message.isEmpty()}">
									<form class=" " action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="floatingInputName"
												name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_NAME}"
												value="${user.getName()}" placeholder="Robert" /> <label
												class="text-lowercase" for="floatingInputName">Имя</label>
										</div>
										<div class="form-floating mb-3">
											<input type="text" class="form-control"
												id="floatingInputSurname"
												name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_SURNAME}"
												value="${user.getSurname()}" placeholder="Downey" /> <label
												class="text-lowercase" for="floatingInputSurname">Фамилия</label>
										</div>
										<div class="form-floating mb-3">
											<input type="email" class="form-control"
												id="floatingInputEmail" placeholder="name@example.com"
												pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})"
												name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_EMAIL}"
												value="${user.getEmail()}" /> <label class="text-lowercase"
												for="floatingInputEmail">Адрес электронной почты</label>
										</div>
										<div
											class="general_infomation_form_fotter d-flex justify-content-end">
											<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CHANGING_PERSON_INFORMATION}" />
											<button class="btn general_infomation_form_btn_submit"
												role="button">готово</button>
										</div>
									</form>
								</c:if>
								<c:if
									test="${!changing_person_information_input_exception_type_and_message.isEmpty()}">
									<form class=" " action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}">
										<c:if
											test="${changing_person_information_input_exception_type_and_message.containsKey(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_NAME)}">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-invalid"
													id="floatingInputName"
													name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_NAME}"
													value="${user.getName()}" placeholder="Robert" />
												<div class="invalid-feedback">${changing_person_information_input_exception_type_and_message.get(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_NAME)}</div>
												<label class="text-lowercase" for="floatingInputName">Имя</label>
											</div>
										</c:if>
										<c:if
											test="${!changing_person_information_input_exception_type_and_message.containsKey(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_NAME)}">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-valid"
													id="floatingInputName"
													name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_NAME}"
													value="${user.getName()}" placeholder="Robert" />
												<div class="valid-feedback">Все хорошо!</div>
												<label class="text-lowercase" for="floatingInputName">Имя</label>
											</div>
										</c:if>

										<c:if
											test="${changing_person_information_input_exception_type_and_message.containsKey(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_SURNAME)}">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-invalid"
													id="floatingInputSurname"
													name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_SURNAME}"
													value="${user.getSurname()}" placeholder="Downey" />
												<div class="invalid-feedback">${changing_person_information_input_exception_type_and_message.get(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_SURNAME)}</div>
												<label class="text-lowercase" for="floatingInputSurname">Фамилия</label>
											</div>
										</c:if>
										<c:if
											test="${!changing_person_information_input_exception_type_and_message.containsKey(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_SURNAME)}">
											<div class="form-floating mb-3">
												<input type="text" class="form-control is-valid"
													id="floatingInputSurname"
													name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_SURNAME}"
													value="${user.getSurname()}" placeholder="Downey" />
												<div class="valid-feedback">Все хорошо!</div>
												<label class="text-lowercase" for="floatingInputSurname">Фамилия</label>
											</div>
										</c:if>
										<c:if
											test="${changing_person_information_input_exception_type_and_message.containsKey(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_EMAIL)}">
											<div class="form-floating mb-3">
												<input type="email" class="form-control is-invalid"
													id="floatingInputEmail" placeholder="name@example.com"
													pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})"
													name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_EMAIL}"
													value="${user.getEmail()}" />
												<div class="invalid-feedback">${changing_person_information_input_exception_type_and_message.get(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_EMAIL)}</div>
												<label class="text-lowercase" for="floatingInputEmail">Адрес
													электронной почты</label>
											</div>
										</c:if>
										<c:if
											test="${!changing_person_information_input_exception_type_and_message.containsKey(ChangePersonInformationCommand.TYPY_INPUT_EXCEPTION_EMAIL)}">
											<div class="form-floating mb-3">
												<input type="email" class="form-control is-valid"
													id="floatingInputEmail" placeholder="name@example.com"
													pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})"
													name="${InputName.CHANGING_PERSON_INFORMATION_FORM_INPUT_USER_EMAIL}"
													value="${user.getEmail()}" />
												<div class="valid-feedback">Все хорошо!</div>
												<label class="text-lowercase" for="floatingInputEmail">Адрес
													электронной почты</label>
											</div>
										</c:if>
										<div
											class="general_infomation_form_fotter d-flex justify-content-end">
											<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
												value="${CommandName.COMMAND_CHANGING_PERSON_INFORMATION}" />
											<button class="btn general_infomation_form_btn_submit"
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