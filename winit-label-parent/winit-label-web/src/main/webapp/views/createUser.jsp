<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Organization Management System</title>
</head>
<body>
	<spring:form>
		<table>
			<tr>
				<td>Fist Name:</td>
				<td>
					<input name="firstName"/>
				</td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td>
					<input name="lastName"/>
				</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>
					<input name="email"/>
				</td>
			</tr>
		</table>
	</spring:form>
</body>
</html>