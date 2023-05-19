<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.AttributeName"%>
<%@page import="by.koroza.zoo_market.web.command.name.InputName"%>
<%@page import="by.koroza.zoo_market.web.command.name.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.impl.RegistrationUserCommand"%>

<fmt:setLocale value="${AttributeName.ATTRIBUTE_SESSION_LOCALE}"
	scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/items/header_top.css" />
<link rel="stylesheet"
	href="css/items/sign_in_and_registration_form.css" />
<title>header_top.jsp</title>
<!-- user = AttributeName.ATTRIBUTE_USER -->
<!-- is_hiving_registrated_user = AttributeName.ATTRIBUTE_IS_HAVING_RESERVED_USER -->
</head>
<body>

	<div class="col-xl-2 col-lg-12 logo">
		<form action="Controller">
			<input type="hidden" name="command"
				value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
			<button class="logo_link" role="button">
				<img class="img img-fluid" src="img/logo.svg" alt="logo" />
			</button>
		</form>
	</div>
	<div class="col-xl-8 col-lg-6 menu_xl">
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
					aria-controls="navbarNavDropdown" aria-expanded="true"
					aria-label="Переключатель навигации">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-center"
					id="navbarNavDropdown">
					<ul class="navbar-nav">
						<li class="nav-item">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
								<button class="nav-link menu_link text-uppercase" role="button">
									<h5>
										<fmt:message key="header_top.main_page" />
									</h5>
								</button>
							</form>
						</li>
						<li class="nav-item">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
								<button class="nav-link text-uppercase menu_link" role="button">
									<h5>
										<fmt:message key="header_top.promotions" />
									</h5>
								</button>
							</form>
						</li>
						<li class="nav-item menu_item">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
								<button class="nav-link text-uppercase menu_link" role="button">
									<h5>
										<fmt:message key="header_top.news" />
									</h5>
								</button>
							</form>
						</li>

						<c:if
							test="${is_hiving_registrated_user == true && user.getRole().getIdRole() >= 1}">
							<c:if test="${user.getRole().getIdRole() == 1}">
								<li class="nav-item">
									<form action="Controller">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_SHOW_VERIFICATION_PERSONAL_ACCOUNT_FORN}">
										<button class="nav-link text-uppercase menu_link"
											role="button">
											<h5>
												<fmt:message key="header_top.email_verification" />
											</h5>
										</button>
									</form>
								</li>
							</c:if>
							<c:if test="${user.getRole().getIdRole() > 1}">
								<li class="nav-item">
									<form action="Controller">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_SHOW_BACKET_PAGE}" /> <input
											class="productsIdXl" type="hidden"
											name="${AttributeName.ATTRIBUTE_SAVED_PRODUCTS_ID_IN_JSP_PAGE }" />
										<button class="nav-link text-uppercase menu_link"
											role="button" onclick="getProducts('.productsIdXl')">
											<h5>
												<fmt:message key="header_top.basket" />
											</h5>
										</button>
									</form>
								</li>
								<li class="nav-item">
									<form action="Controller">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE}">
										<button class="nav-link text-uppercase menu_link"
											role="button">
											<h5>
												<fmt:message key="header_top.personal_account" />
											</h5>
										</button>
									</form>
								</li>
							</c:if>
						</c:if>

						<c:if
							test="${is_hiving_registrated_user == false || user.getRole().getIdRole() < 1}">
							<li class="nav-item">
								<button class="nav-link text-uppercase menu_link" role="button"
									onclick="showSignInAndRegistrationForm()">
									<h5>
										<fmt:message key="header_top.sign_in_or_registration" />
									</h5>
								</button>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div
		class="col-xl-2 col-lg-6 justify-content-center flex-row flags_icons_btns_xl">
		<form class="me-3" action="Controller">
			<input type="hidden" name="command"
				value="${CommandName.COMMAND_SET_ENGLISH_LOCALE}" />
			<button class="border-0 bg-transparent"
				style="width: 60px; height: 40px">
				<svg xmlns="http://www.w3.org/2000/svg" id="flag-icons-gb"
					viewBox="0 0 640 480">
                  <path fill="#012169" d="M0 0h640v480H0z" />
                  <path fill="#FFF"
						d="m75 0 244 181L562 0h78v62L400 241l240 178v61h-80L320 301 81 480H0v-60l239-178L0 64V0h75z" />
                  <path fill="#C8102E"
						d="m424 281 216 159v40L369 281h55zm-184 20 6 35L54 480H0l240-179zM640 0v3L391 191l2-44L590 0h50zM0 0l239 176h-60L0 42V0z" />
                  <path fill="#FFF"
						d="M241 0v480h160V0H241zM0 160v160h640V160H0z" />
                  <path fill="#C8102E"
						d="M0 193v96h640v-96H0zM273 0v480h96V0h-96z" />
                </svg>
			</button>
		</form>
		<form class="" action="Controller">
			<input type="hidden" name="command"
				value="${CommandName.COMMAND_SET_RUSSIAN_LOCALE}" />
			<button class="border-0 bg-transparent"
				style="width: 60px; height: 40px">
				<svg xmlns="http://www.w3.org/2000/svg" id="flag-icons-ru"
					viewBox="0 0 640 480">
                  <g fill-rule="evenodd" stroke-width="1pt">
                    <path fill="#fff" d="M0 0h640v480H0z" />
                    <path fill="#0039a6" d="M0 160h640v320H0z" />
                    <path fill="#d52b1e" d="M0 320h640v160H0z" />
                  </g>
                </svg>
			</button>
		</form>
	</div>
	<div class="col-lg-12 menu_flags_icons_lg">
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<button type="button" data-bs-toggle="collapse"
					data-bs-target="#navbarNavDropdown" class="navbar-toggler"
					aria-controls="navbarNavDropdown" aria-expanded="true"
					aria-label="Переключатель навигации">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-center"
					id="navbarNavDropdown">
					<ul class="navbar-nav">
						<li class="nav-item">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
								<button class="nav-link menu_link" role="button">
									<h5>
										<fmt:message key="header_top.main_page" />
									</h5>
								</button>
							</form>
						</li>
						<li class="nav-item">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
								<button class="nav-link menu_link" role="button">
									<h5>
										<fmt:message key="header_top.promotions" />
									</h5>
								</button>
							</form>
						</li>
						<li class="nav-item menu_item">
							<form action="Controller">
								<input type="hidden" name="command"
									value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
								<button class="nav-link menu_link" role="button">
									<h5>
										<fmt:message key="header_top.news" />
									</h5>
								</button>
							</form>
						</li>
						<c:if
							test="${is_hiving_registrated_user == true && user.getRole().getIdRole() >= 1}">
							<c:if test="${user.getRole().getIdRole() == 1}">
								<li class="nav-item">
									<form action="Controller">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_SHOW_VERIFICATION_PERSONAL_ACCOUNT_FORN}">
										<button class="nav-link menu_link" role="button">
											<h5>
												<fmt:message key="header_top.email_verification" />
											</h5>
										</button>
									</form>
								</li>
							</c:if>
							<c:if test="${user.getRole().getIdRole() > 1}">
								<li class="nav-item">
									<form action="Controller">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_SHOW_BACKET_PAGE}" /> <input
											class="productsId" type="hidden"
											name="${AttributeName.ATTRIBUTE_SAVED_PRODUCTS_ID_IN_JSP_PAGE }" />
										<button class="nav-link menu_link" role="button"
											onclick="getProducts('.productsIdLg')">
											<h5>
												<fmt:message key="header_top.basket" />
											</h5>
										</button>
									</form>
								</li>
								<li class="nav-item">
									<form action="Controller">
										<input type="hidden" name="command"
											value="${CommandName.COMMAND_SHOW_PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE}">
										<button class="nav-link menu_link" role="button">
											<h5>
												<fmt:message key="header_top.personal_account" />
											</h5>
										</button>
									</form>
								</li>
							</c:if>
						</c:if>

						<c:if
							test="${is_hiving_registrated_user == false || user.getRole().getIdRole() < 1}">
							<li class="nav-item">
								<button class="nav-link menu_link" role="button"
									onclick="showSignInAndRegistrationForm()">
									<h5>
										<fmt:message key="header_top.sign_in_or_registration" />
									</h5>
								</button>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
		<div class="col-lg-2 flags_icons_btns_lg">
			<form class="me-3" action="Controller">
				<input type="hidden" name="command"
					value="${CommandName.COMMAND_SET_ENGLISH_LOCALE}" />
				<button class="border-0 bg-transparent"
					style="width: 60px; height: 40px">
					<svg xmlns="http://www.w3.org/2000/svg" id="flag-icons-gb"
						viewBox="0 0 640 480">
                    <path fill="#012169" d="M0 0h640v480H0z" />
                    <path fill="#FFF"
							d="m75 0 244 181L562 0h78v62L400 241l240 178v61h-80L320 301 81 480H0v-60l239-178L0 64V0h75z" />
                    <path fill="#C8102E"
							d="m424 281 216 159v40L369 281h55zm-184 20 6 35L54 480H0l240-179zM640 0v3L391 191l2-44L590 0h50zM0 0l239 176h-60L0 42V0z" />
                    <path fill="#FFF"
							d="M241 0v480h160V0H241zM0 160v160h640V160H0z" />
                    <path fill="#C8102E"
							d="M0 193v96h640v-96H0zM273 0v480h96V0h-96z" />
                  </svg>
				</button>
			</form>
			<form class="" action="Controller">
				<input type="hidden" name="command"
					value="${CommandName.COMMAND_SET_RUSSIAN_LOCALE}" />
				<button class="border-0 bg-transparent"
					style="width: 60px; height: 40px">
					<svg xmlns="http://www.w3.org/2000/svg" id="flag-icons-ru"
						viewBox="0 0 640 480">
                    <g fill-rule="evenodd" stroke-width="1pt">
                      <path fill="#fff" d="M0 0h640v480H0z" />
                      <path fill="#0039a6" d="M0 160h640v320H0z" />
                      <path fill="#d52b1e" d="M0 320h640v160H0z" />
                    </g>
                  </svg>
				</button>
			</form>
		</div>
	</div>
	<div class="col-12">
		<div
			class="position-fixed d-flex d-none justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 sign_in_and_registration_form">
			<div
				class="position-relative w-100 d-flex flex-column sign_in_and_registration_form_inner">
				<div
					class="d-flex justify-content-center align-items-center mb-4 sign_in_and_registration_form_top_btns">
					<div class="close_btn" onclick="closeSignInAndRegistrationForm()">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
							width="25px" height="25px">
	<path
								d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" /></svg>
					</div>
					<button
						class="btn text-uppercase active sign_in_and_registration_form_top_btn sign_in_and_registration_form_top_btn_sign_in"
						role="button" onclick="openSignInForm()">Войти</button>
					<button
						class="btn  text-uppercase sign_in_and_registration_form_top_btn sign_in_and_registration_form_top_btn_registration"
						role="button" onclick="openRegistrationForm()">
						Регистрация</button>
				</div>

				<div class="sign_in_form">
					<div class="sign_in_form_top">
						<h2 class="form_title text-center mb-3 text-lowercase">логин
							и пароль</h2>
					</div>
					<form class="sign_in_form_body" action="Controller">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="floatingInputLogin"
								name="${InputName.SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_LOGIN}"
								placeholder="Robert99" /> <label class="text-lowercase"
								for="floatingInputLogin">Логин</label>
						</div>
						<div
							class="form-floating mb-3 d-flex justify-content-center align-items-center">
							<input type="password"
								class="form-control sign_in_form_input_password"
								id="floatingInputPassword"
								name="${InputName.SIGN_IN_PERSONAL_ACCOUNT_INPUT_USER_PASSWORD}"
								placeholder="123456" /> <label class="text-lowercase"
								for="floatingInputPassword">Пароль</label>
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
						</div>
						<div class="sign_in_form_fotter d-flex justify-content-end">
							<div class="btn sign_in_form_btn_cancel"
								onclick="closeSignInAndRegistrationForm()">отмена</div>
							<input type="hidden" name="command"
								value="${CommandName.COMMAND_SIGN_IN_PERSON_ACCOUNT}" />
							<button class="btn sign_in_form_btn_submit" role="button">
								готово</button>
						</div>
					</form>
				</div>

				<div class="registration_form d-none">
					<div class="sign_in_form_top">
						<h2 class="form_title text-center mb-3 text-lowercase">
							регистрация</h2>
					</div>
					<form class="registration_form_body" action="Controller">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="floatingInputName"
								name="${InputName.REGISTRATION_INPUT_USER_NAME}"
								placeholder="Robert"
								value='<c:if test="${user.getName() != null && !user.getName().isBlank()}">${user.getName()}</c:if>' />
							<label class="text-lowercase registration_form_body_label"
								for="floatingInputName">имя <span>*</span>
							</label>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control "
								id="floatingInputSurname"
								name="${InputName.REGISTRATION_INPUT_USER_SURNAME}"
								value='<c:if test="${user.getSurname() != null && !user.getSurname().isBlank()}">${user.getSurname()}</c:if>'
								placeholder="Robert" /> <label
								class="text-lowercase registration_form_body_label"
								for="floatingInputSurname">Фамилия<span>*</span>
							</label>
						</div>
						<div class="form-floating mb-3">
							<input type="email" class="form-control" id="floatingInputEmail"
								name="${InputName.REGISTRATION_INPUT_USER_EMAIL}"
								value='<c:if test="${user.getEmail() != null && !user.getEmail().isBlank()}">${user.getEmail()}</c:if>'
								placeholder="name@example.com"
								pattern="([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,}).([A-z]{2,8})" /> <label
								class="text-lowercase registration_form_body_label"
								for="floatingInputEmail">Адрес электронной почты </label>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="floatingInputLogin"
								name="${InputName.REGISTRATION_INPUT_USER_LOGIN}"
								value='<c:if test="${user.getLogin() != null && !user.getLogin().isBlank()}">${user.getLogin()}</c:if>'
								placeholder="Robert" /> <label
								class="text-lowercase registration_form_body_label"
								for="floatingInputLogin">Логин </label>
						</div>
						<div
							class="password_form d-flex juctify-content-between align-items-center">
							<div class="form-floating mb-3 w-100">
								<input type="password"
									class="form-control registration_form_input_password"
									id="floatingInputPassword"
									name="${InputName.REGISTRATION_INPUT_USER_PASSWORD}"
									value='<c:if test="${user.getPassword() != null && !user.getPassword().isBlank()}">${user.getPassword()}</c:if>'
									placeholder="123456" /> <label
									class="text-lowercase registration_form_body_label"
									for="floatingInputPassword">Пароль</label>
							</div>
							<div class="btn registration_form_password_btn mb-5 "
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
						</div>
						<div class="form_description">
							<h5>
								<span>*</span> - поле не обязательное для заполнения
							</h5>
						</div>

						<div class="registration_form_fotter d-flex justify-content-end">
							<div class="btn registration_form_btn_cancel"
								onclick="closeSignInAndRegistrationForm()">отмена</div>
							<input type="hidden" name="command"
								value="${CommandName.COMMAND_REGISTRATION_USER}" />
							<button class="btn registration_form_btn_submit" role="button">готово</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="js/bootstrap/bootstrap.bundle.js"></script>
	<script src="js/header_top.js"></script>
	<script src="js/min_base.js"></script>
	<script src="js/sign_in_and_registration_form.js"></script>


</body>
</html>