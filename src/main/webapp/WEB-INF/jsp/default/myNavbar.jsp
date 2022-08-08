<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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



<header class="h_block" id="top">
	<div class="container-fluid m-auto" id="h_div1">
		<nav
			class="navbar navbar-expand-lg navbar-light d-flex justify-content-between p-0">
			<div id="h_title">
				<a
					class="navbar-brand d-flex justify-content-start align-items-center m-auto"
					style="flex: 1" href="${contextRoot}/"> iMedical </a>
			</div>
			<!-- 					<button class="navbar-toggler" type="button" data-toggle="collapse" -->
			<!-- 						data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" -->
			<!-- 						aria-expanded="false" aria-label="Toggle navigation"> -->
			<!-- 						<span class="navbar-toggler-icon"></span> -->
			<!-- 					</button> -->
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<div id="h_items" style="flex: 1;">
					<div class="container-fluid d-flex justify-content-end p-0"
						style="max-width: 1280px;">
						<div class="topLink-box">
							<ul class="nav">

								<!-- 									登出 -->
								<sec:authorize access="authenticated" var="authenticated" />
								<c:choose>
									<c:when test="${authenticated}">
										<form method="post" action="${contextRoot}/Backstage/logout">
											<sec:csrfInput />
											<button type="submit" class="btn btn-outline-dark" onclick="return confirm('確認登出嗎?')">登出</button>
										</form>
									</c:when>
								</c:choose>
								<!-- 									/登出 -->

								<li class="nav-item">
									<!-- 											//判斷有無登錄後台 --> <sec:authorize
										access="authenticated" var="authenticated" /> <c:choose>
										<c:when test="${authenticated}">
											<a class="nav-link" href="${contextRoot}/Backstage/"> <span>您好
													<span style="color: red;"> <sec:authentication
															property="name" />
												</span>
											</span> <span class="fas fa-tools"></span> 回到後台
											</a>
										</c:when>
										<c:otherwise>

											<a href="#" type="button" data-bs-toggle="modal"
												data-bs-target="#loginModal">
												<span
												class="fas fa-tools"></span>
												後台管理
												
											</a>





										</c:otherwise>
									</c:choose>








								</li>
								<li class="nav-item">
									<!-- 											//判斷有無登錄會員 --> <sec:authorize
										access="authenticated" var="authenticated" /> <c:choose>
										<c:when test="${authenticated}">
											<%-- 													<a class="nav-link" href="${contextRoot}/FrontMember/memberCentre"> --%>
											<!-- 														<span class="fas fa-user-cog"></span> -->
											<!-- 														歡迎, -->
											<%-- 														<sec:authentication property="name" /> --%>
											<!-- 													</a> -->
										</c:when>
										<c:otherwise>
											<a class="nav-link" href="${contextRoot}/frontMemberLogin">
												<span class="fas fa-user-cog"></span> 會員中心
											</a>
											<li class="nav-item"><a class="nav-link"
												href="${contextRoot}/ShoppingCart"> <span
													class="fas fa-shopping-cart"></span> 購物車
											</a></li>
										</c:otherwise>
									</c:choose> <!-- 											// -->
								</li>

							</ul>
						</div>
					</div>
					<!-- navbar block -->
					<div class="container-fluid p-0" style="max-width: 1280px;"
						id="my-navbar">
						<nav class="navbar navbar-expand-lg" style="width: 50%">
							<div class="d-flex navbar-nav nav-fill w-100">
								<a class="nav-item nav-link" href="${contextRoot}/aboutimedical">關於我們</a>
								<a class="nav-item nav-link" href="${contextRoot}/agencyData">找機構</a>
								<a class="nav-item nav-link"
									href="${contextRoot}/assistiveDevice">找輔具</a> <a
									class="nav-item nav-link" href="${contextRoot}/subsidy">長照2.0</a>
								<a class="nav-item nav-link" href="${contextRoot}/gosubsidy">長照補助</a>
								<a class="nav-item nav-link" href="${contextRoot}/messageNavbar">留言板</a>
								<a class="nav-item nav-link" href="${contextRoot}/Commodities">商店</a>
							</div>
						</nav>
						<form id="SiteSearchForm" class="form-inline " method="post">
							<input type="search" class="form-control" placeholder="search">
							<button class="btn btn-outline-secondary" type="button"
								id="h_searchBtn">搜尋</button>
						</form>
					</div>
				</div>
			</div>
		</nav>
	</div>
</header>






<body>
	<div class="modal fade" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!--Header  -->
				<div class="modal-header">
					<h3 ><i class="fa-solid fa-user-gear"></i>後台管理登入</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!--Body  -->
				<div class="modal-body">
					<!--登入表單  -->
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
									<c:if test="${param.error2}">
										<c:out value="您已登出，請重新登入" />
									</c:if>
								</p>
								<button class="btn btn-info" type="submit">登入</button>
							</div>


					</form>


				</div>

				<!--Footer  -->
				<div class="modal-footer">
					<div class="signup"></div>

				</div>



			</div>
		</div>
	</div>


</body>