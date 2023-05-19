<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script type="text/javascript">
		localStorage.setItem('user', '${param.user}'); //Where param user is your request parameter from previous jsp.
	</script>

	<script type="text/javascript">
		localStorage.getItem('user');
	</script>
</body>
</html>