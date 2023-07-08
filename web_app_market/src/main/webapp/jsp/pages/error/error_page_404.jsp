<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.attribute.AttributeName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page
	import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="${PagePathName.PAGE_CONTENT_PROPERTIES}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/items/root.css" />
<link rel="stylesheet" href="css/items/success_payment.css" />
<title>404</title>
<!-- locale = AttributeName.ATTRIBUTE_SESSION_LOCALE -->
</head>
<body>
	<header class="success_payment"
		style="min-height: 100vh; background: var(--color2)">
		<div class="container">
			<div class="row">
				<div
					class="col-12 d-flex justify-content-center align-items-center flex-column"
					style="min-height: 100vh">
					<div
						class="position-fixed d-flex justify-content-center align-items-center top-0 end-0 right-0 w-100 h-100 success_payment_form">
						<div
							class="position-relative w-100 d-flex justify-content-center align-items-center flex-column success_payment_form_inner">
							<img
								src="${ServletName.SERVLET_SHOW_IMAGE_NAME}?${ParameterName.PARAMETER_IMAGE_FILE_PATH}=${ImagePath.LOGO_PNG_IMAGE_PATH}"
								alt="logo" />
							<h1 class="text-uppercase success_payment_form_title">
								Страница не найдена. перейдите на главную страницу.</h1>
							<div
								class="success_payment_form_fotter d-flex justify-content-end">
								<form action="${ServletName.MAIN_SERVLET_CONTROLLER_NAME}"
									method="get">
									<input type="hidden" name="${ParameterName.PARAMETER_COMMAND}"
										value="${CommandName.COMMAND_SHOW_HOME_PAGE}">
									<button class="nav-link menu_link text-uppercase" role="button">
										<h5>
											<fmt:message key="header_top.main_page" />
										</h5>
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
</body>
</html>