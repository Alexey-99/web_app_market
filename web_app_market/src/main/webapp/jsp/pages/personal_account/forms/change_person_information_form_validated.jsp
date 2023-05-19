<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.change.ChangePersonInformationCommand"%>
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
					<div class="col-md-2 col-sm-3 col-xs-4">
						<div class="btn-group person_account_menu_links">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE}" />
								<button
									class="btn btn-primary w-100 active person_account_menu_link btn_first"
									role="button" aria-current="page">Профиль</button>
							</form>
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_HISTORY_ORDERS_PAGE}" />
								<button class="btn btn-primary person_account_menu_link w-100"
									role="button">Заказы</button>
							</form>
						</div>
					</div>

					<div class="col-md-10 col-sm-9 col-xs-8 person_part">
						<div class="row">
							<div class="col-12 mb-5">
								<h1 class="person_part_title">Профиль</h1>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<div
									class="row row-cols-1 row-cols-sm-1 row-cols-lg-2 row-cols-xxl-3 g-4 personal_infomation">
									<div class="general_infomation">
										<h4 class="d-flex align-items-center mb-4 part_title">
											Общая информация
											<button
												class="btn pb-0 pt-0 change_infomation_btn general_infomation_btn"
												onclick="showGeneralInfomationForm()">
												<svg class="general_infomation_svg" width="26" height="26"
													viewBox="0 0 26 26" fill="none"
													xmlns="http://www.w3.org/2000/svg">
                          <path
														d="M12.069 15.8408L16.0545 8.93759C16.3307 8.4593 16.1668 7.84771 15.6885 7.57156L13.2371 6.15625C12.7588 5.88011 12.1472 6.04398 11.8711 6.52228L7.88551 13.4255"
														stroke="#9E9E9E" stroke-width="2" stroke-linecap="round"
														class="general_infomation_svg_path" />
                          <path
														d="M7.88938 13.4212L7.90151 16.5027C7.90453 17.269 8.73295 17.7473 9.39809 17.3668L12.0728 15.8365"
														stroke="#9E9E9E" stroke-width="2" stroke-linecap="round"
														class="general_infomation_svg_path" />
                          <path d="M7.88672 20.3438L18.0452 20.3438"
														stroke="#9E9E9E" stroke-width="2" stroke-linecap="round"
														class="general_infomation_svg_path" />
                        </svg>
											</button>
										</h4>
										<span>имя</span>
										<h5>${user.getName()}</h5>
										<span>фамилия</span>
										<h5>${user.getSurname()}</h5>
										<span>e-mail</span>
										<h5>${user.getEmail()}</h5>
									</div>
									<div class="personal_discont">
										<h4 class="d-flex align-items-center mb-4 part_title">
											Персональная скидка</h4>
										<span>размер</span>
										<h5 class="d-flex align-items-center">
											<c:if test="${user.getDiscont() == 0}">0</c:if>
											<c:if test="${user.getDiscont() > 0}">${user.getDiscont()}</c:if>
											<span class="span_procent ms-1"> <svg
													xmlns="http://www.w3.org/2000/svg" height="24"
													viewBox="0 96 960 960" width="24">
                          <path
														d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z" />
                        </svg>
											</span>
										</h5>
									</div>
									<div class="login_password">
										<h4 class="d-flex align-items-center mb-4 part_title">
											Логин и пароль
											<button
												class="btn pb-0 pt-0 change_infomation_btn login_password_btn"
												onclick="showLoginPasswordForm()">
												<svg class="login_password_svg" width="26" height="26"
													viewBox="0 0 26 26" fill="none"
													xmlns="http://www.w3.org/2000/svg">
                          <path
														d="M12.069 15.8408L16.0545 8.93759C16.3307 8.4593 16.1668 7.84771 15.6885 7.57156L13.2371 6.15625C12.7588 5.88011 12.1472 6.04398 11.8711 6.52228L7.88551 13.4255"
														stroke="#9E9E9E" stroke-width="2" stroke-linecap="round"
														class="login_password_svg_path" />
                          <path
														d="M7.88938 13.4212L7.90151 16.5027C7.90453 17.269 8.73295 17.7473 9.39809 17.3668L12.0728 15.8365"
														stroke="#9E9E9E" stroke-width="2" stroke-linecap="round"
														class="login_password_svg_path" />
                          <path d="M7.88672 20.3438L18.0452 20.3438"
														stroke="#9E9E9E" stroke-width="2" stroke-linecap="round"
														class="login_password_svg_path" />
                        </svg>
											</button>
										</h4>
										<span>логин</span>
										<h5>${user.getLogin()}</h5>
										<span>пароль</span>
										<div class="d-flex flex-row align-items-center">
											<h5 class="text_password hide_password mb-1">${user.getPassword()}</h5>
											<button class="btn password_btn" onclick="showPassword()">
												<svg class="open_eye" xmlns="http://www.w3.org/2000/svg"
													height="25" viewBox="0 96 960 960" width="25">
                          <path
														d="M480.118 726Q551 726 600.5 676.382q49.5-49.617 49.5-120.5Q650 485 600.382 435.5q-49.617-49.5-120.5-49.5Q409 386 359.5 435.618q-49.5 49.617-49.5 120.5Q310 627 359.618 676.5q49.617 49.5 120.5 49.5Zm-.353-58Q433 668 400.5 635.265q-32.5-32.736-32.5-79.5Q368 509 400.735 476.5q32.736-32.5 79.5-32.5Q527 444 559.5 476.735q32.5 32.736 32.5 79.5Q592 603 559.265 635.5q-32.736 32.5-79.5 32.5ZM480 856q-146 0-264-83T40 556q58-134 176-217t264-83q146 0 264 83t176 217q-58 134-176 217t-264 83Zm0-300Zm-.169 240Q601 796 702.5 730.5 804 665 857 556q-53-109-154.331-174.5-101.332-65.5-222.5-65.5Q359 316 257.5 381.5 156 447 102 556q54 109 155.331 174.5 101.332 65.5 222.5 65.5Z" />
                        </svg>
												<svg class="close_eye d-none"
													xmlns="http://www.w3.org/2000/svg" height="25"
													viewBox="0 96 960 960" width="25">
                          <path
														d="m629 637-44-44q26-71-27-118t-115-24l-44-44q17-11 38-16t43-5q71 0 120.5 49.5T650 556q0 22-5.5 43.5T629 637Zm129 129-40-40q49-36 85.5-80.5T857 556q-50-111-150-175.5T490 316q-42 0-86 8t-69 19l-46-47q35-16 89.5-28T485 256q143 0 261.5 81.5T920 556q-26 64-67 117t-95 93Zm58 226L648 827q-35 14-79 21.5t-89 7.5q-146 0-265-81.5T40 556q20-52 55.5-101.5T182 360L56 234l42-43 757 757-39 44ZM223 402q-37 27-71.5 71T102 556q51 111 153.5 175.5T488 796q33 0 65-4t48-12l-64-64q-11 5-27 7.5t-30 2.5q-70 0-120-49t-50-121q0-15 2.5-30t7.5-27l-97-97Zm305 142Zm-116 58Z" />
                        </svg>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12">
						<div
							class="position-fixed justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 general_infomation_form">
							<div
								class="position-relative w-100 d-flex flex-column general_infomation_form_inner">
								<div class="general_infomation_form_top">
									<form class=" " action="Controller">
										<input type="hidden" name="command"
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
									<form class=" " action="Controller">
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
											<input type="hidden" name="command"
												value="${CommandName.COMMAND_CHANGING_PERSON_INFORMATION}" />
											<button class="btn general_infomation_form_btn_submit"
												role="button">готово</button>
										</div>
									</form>
								</c:if>
								<c:if
									test="${!changing_person_information_input_exception_type_and_message.isEmpty()}">
									<form class=" " action="Controller">
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
											<input type="hidden" name="command"
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