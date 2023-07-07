<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>
		Request from ${pageContext.errorData.requestURI} is failed <br />
		Servlet name: ${pageContext.errorData.servletName} <br /> Status
		code: ${pageContext.errorData.statusCode} <br /> Exception:
		${pageContext.exception} <br /> Message from exception:
		${pageContext.exception.message} <br /> Stack trace: <br />
		<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
			${trace}<br />
		</c:forEach>
	</h2>
</body>
</html>