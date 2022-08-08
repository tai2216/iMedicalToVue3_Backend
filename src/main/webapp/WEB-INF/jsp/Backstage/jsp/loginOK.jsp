<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<jsp:include page="default/myNavbar.jsp"></jsp:include>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="2,url=${contextRoot}/Backstage">
<script src="${contextRoot}/js/jquery-3.6.0.min.js"></script>
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
<script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">

<title>登入成功</title>
</head>
<body>
<div style="width: 100%; text-align: center;">

	<img style="width: 25%;" alt="轉圈圈" src="${contextRoot}/img/轉圈圈2.gif">

<br>
<h3>登入成功! 您好 <b style="color: red; font: bolder;"><sec:authentication property="name"/></b> !... 將在2秒後自動跳轉至後台首頁</h3>
</div>






</body>
</html>