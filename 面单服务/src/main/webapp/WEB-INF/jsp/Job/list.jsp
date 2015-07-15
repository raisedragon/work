<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/commons/base.jsp" %>
</head>

<body>
	<display:table name="jobs" pagesize="20" partialList="true" size="${page.size}">
		<display:column property="name"></display:column>
		<display:column property="clazz"></display:column>
		<display:column property="description"></display:column>
	</display:table>
</body>
</html>