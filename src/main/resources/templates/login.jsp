<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<script src="${contextRoot}/js/jquery-3.6.0.min.js"></script>

<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${contextRoot}/js/bootstrap.bundle.min.js " rel="stylesheet" />
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>iMedical長照醫療網後台登入頁面</title>

</head>
<body>

	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">
				<img alt="icon" src="${contextRoot}/img/icon.png" width="90"
					height="90" />iMedical後臺管理頁面登入
			</h1>
			<p class="lead"></p>
		</div>
	</div>

	<div style="width: 100%; margin-top: 50px; align-content: center;">



	<div class="card" 
		style="width: 400px; height:400px; margin: 150px auto 10px auto; border-color: black; border-radius: 6px; border-width: 2px;">
		<div class="card-header"> <h2 style="text-align: center;"><b>登入</b></h2> </div>
		<div class="card-body text-dark">
			<p class="card-text">
			<form action="adminFormLogin" method="post">
				<div class="main-box large-padding size-600x450 middle-align">
					
					<label for="username" style=""><b>帳號:</b></label> 
					<br>
					<input style="width: 100%; height:40px; border-radius: 6px; text-align: center;"
						type="text" placeholder="Enter User's Account" name="username"
						required="required"> 
						<br /> 
						<br /> 
					<label for="password"><b>密碼:</b></label>
					<br>
					<input style="width: 100%; height:40px; border-radius: 6px; text-align: center;"
						type="password" placeholder="Enter User's Password" name="password"
						required="required">
						
					<input type="text" value="${_csrf.token}" name="_csrf" />	
						
					<p style="color: red">
						<b>${loginError}</b>
					</p>
					<div style="width: 100%; text-align: center;">
										
						<button class="btn-primary" type="submit" 
						style="width:25%; height:40px; text-align: center; border-radius: 6px;">登入</button>
						<p style="color: red">
							<b>${logoutMessage}</b>
						</p>
						<a href="${contextRoot}">回首頁</a>
					</div>
					

				</div>
			</form>



		</div>
	</div>

	</div>



	<footer class="main-footer"
		style="margin: 150px 0px 0px 0px; background-color: lightgray;">
		<div class="container" style="width: 100%; text-align: center;">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.01
			</div>
			<strong>Copyright © 2022-2025 <a href="">iMedical</a>.
			</strong> All rights reserved.
		</div>

	</footer>



































</body>
</html>