<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<script src="${contextRoot}/js/jquery-3.6.0.min.js"></script>
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
<script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/9bf7222a42.js" crossorigin="anonymous"></script>


</head>
<body>


	<nav class="navbar navbar-expand-lg navbar-light bg-light ">
		<a class="navbar-brand" href="${contextRoot}/Backstage"> <img alt="" src="${contextRoot}/img/icon.png" width="30" height="30"> iMedical長照醫療後台管理</a>






		

		<div style="margin-left: auto; margin-right: 30px;">
		
				<div class="navbar" id="navbarNav" >
<%-- 						<img alt="尚未上傳照片" style="width: 40px; height: 40px; margin-right: 10px; font-size: xx-small;" src="${contextRoot}/Backstage/downloadImage/${loginSession[0].id}"><img/> --%>
					<ul class=navbar-nav>
						<ins style="vertical-align:bottom; margin-right: 20px;">您好! 管理者: <b style="color: red; font: bolder;"><sec:authentication property="name"/></b> </ins> 
<%-- 						<li>登入帳號(name): <span ><sec:authentication property="name"/> </span></li> --%>
<%-- 						<li>登入帳號(principal.username): <span ><sec:authentication property="principal.username"/> </span></li> --%>
<%-- 						<li>憑證(credentials): <span ><sec:authentication property="credentials"/> </span></li> --%>
<%-- 						<li>權限與角色(authorities): <span ><sec:authentication property="authorities"/> </span></li> --%>
<%-- 						<li>客戶端地址(remoteAddress): <span ><sec:authentication property="details.remoteAddress"/> </span></li> --%>
<%-- 						<li>Session ID(details.sessionId): <span ><sec:authentication property="details.sessionId"/> </span></li> --%>
					  <li class="nav-item">
					  	<form method="post" name="logout" action="${contextRoot}/Backstage/logout">
					  		<sec:csrfInput/>
					  		 <button type="submit" class="btn btn-outline-dark " onclick="return confirm('確認登出嗎?')">登出</button>
					  	</form>
					  </li>
					</ul>
					
					
				</div>
	

		</div>
	</nav>
		








</body>
</html>