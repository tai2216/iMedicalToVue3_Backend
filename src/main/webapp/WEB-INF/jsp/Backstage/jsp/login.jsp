<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!-- 響應式網站 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Boostrap 導入程式 -->
<link rel="stylesheet" href="${contextRoot}/css/myAdminLogin.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script>
<script src="${contextRoot}/js/jquery-3.6.0.min.js"></script>

<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${contextRoot}/js/bootstrap.bundle.min.js " rel="stylesheet" />
<script src="https://kit.fontawesome.com/9bf7222a42.js" ></script>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>iMedical長照醫療網後台登入頁面</title>

</head>
<body>


	

	
	
	
	
	
	
	<div class="modal fade" id="loginModal" data-bs-backdrop="static" data-bs-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Header -->
				<div class="modal-header" >
					<h3><i class="fa-solid fa-user-gear"></i>後台管理登入</h3>
					
				<br>
				</div>

				<!-- Body -->
				<div class="modal-body">
					<!-- 登入表單 -->
					<form method="post" action="${contextRoot}/Backstage/login">
						<div class="form-group">
							<label for="username" ><i class="fas fa-user-tie" aria-hidden="true"></i><b>帳號:</b></label> 
							<hr> 
							
							<input class="account form-control"
								type="text" placeholder="Administrator's Account" name="username"
								required="required"> 
						</div>
							
							<label
								for="password"><i class="fa-solid fa-lock"></i><b>密碼:</b></label> 
								<hr> 
							<input class="password form-control"
								type="password" placeholder="Administrator's Password"
								name="password" required="required">

							<sec:csrfInput />
							<span style="font-size: large; line-height: 45px;"> 
							<input
								type="checkbox" name="remember-me" >記住我
							</span>
							<div style="width: 100%; text-align: center;">
								<p style="color: red; font: bold;">
									<c:if test="${param.error}">
										<c:out value="帳號密碼輸入錯誤，請重新輸入" />
									</c:if>
									<c:if test="${param.logout}">
										<c:out value="您已登出，請重新登入" />
									</c:if>
								</p>
								<button class="btn btn-info" type="submit">登入</button>
							</div>


					</form>


				</div>

				<!-- Footer -->
				<div class="modal-footer">
					<div class="signup">
						<a href="${contextRoot}/">回首頁</a>
					</div>

				</div>



			</div>
		</div>
	</div>
	
	
	
	



	<script >
	
		$(document).ready(function(){
			$("#loginModal").modal('show');
		});
		


	</script>































</body>
</html>