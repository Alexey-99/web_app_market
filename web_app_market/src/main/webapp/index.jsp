<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="by.koroza.zoo_market.web.command.name.command.CommandName"%>
<%@page import="by.koroza.zoo_market.web.command.name.servlet.ServletName"%>
<%@page import="by.koroza.zoo_market.web.command.name.parameter.ParameterName"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:forward
		page="/${ServletName.MAIN_SERVLET_CONTROLLER_NAME}?${ParameterName.PARAMETER_COMMAND}=${CommandName.COMMAND_SHOW_HOME_PAGE}" />
</body>
</html>