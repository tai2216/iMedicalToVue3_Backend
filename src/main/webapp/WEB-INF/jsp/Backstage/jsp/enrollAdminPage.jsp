<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tags" %>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>測試註冊管理者頁面</title>
</head>
<body>


	<form:form action="enroll" modelAttribute="adminBean" method="post">
		<form:input type="hidden" path="id"/>
		account: <form:input path="adminAccount"/>
		<br/>
		password: <form:input path="adminPassword"/>
		<form:input type="hidden" path="adminRole" value="admin,normal,ROLE_manager"/>
		<br/>
		<button>submit</button>
	</form:form>

	

</body>
</html>