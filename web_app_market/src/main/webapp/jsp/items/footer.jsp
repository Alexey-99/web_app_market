<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@page import="by.koroza.zoo_market.web.command.name.path.PagePathName"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="${PagePathName.PAGE_CONTENT_PROPERTIES}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/items/home_page/footer.css" />
<title><fmt:message key="footer.title" /></title>
</head>
<body>
	<footer class="pt-5 pb-5">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<h5 class="text-center">
						<fmt:message key="footer.message" />
					</h5>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>