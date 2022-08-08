<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="default/myNavbar.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextRoot}/css/mySideBarCSS.css">
<script src="${contextRoot}/js/mySideBarJS.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9bf7222a42.js" ></script>

<title>iMedical長照醫療網後台管理</title>
</head>
<body style="background-image: url('${contextRoot}/img/backstage/bg.jfif');">


	<div class="container-all">
		<!-- 左邊 -->
	<div class="container-left">
		<!-- 導覽列 -->
		<nav id="sidebar">
			<!-- 展往左邊/縮起來 按鈕 -->
			<button type="button" id="collapse" class="collapse-btn">
				<i class="fa-solid fa-align-left"></i>
			</button>
		
		
		<!-- List 列表 -->
		<ul class="list-unstyled">
			  <p>後台管理</p>
			<li>
				<a href="${contextRoot}/Backstage">首頁<i class="fa-solid fa-house"></i> </a>
			</li>
			<li>
				<a href="#sublist" data-bs-toggle="collapse" id="dropdown">管理<i class="fa-solid fa-list-check"></i></a>
				<!-- 子連結列表 -->
				<ul id="sublist" class="list-unstyled collapse">
					<li>
						<a href="${contextRoot}/Backstage/getallEmployee">管理員工</a>
					</li>
					<li>
						<a href="${contextRoot}/Backstage/getAllMember">管理會員</a>
					</li>
					<li>
						<a href="${contextRoot}/Backstage/getAllRealAssistiveDevice">管理輔具</a>
					</li>
					<li>
						<a href="${contextRoot}/Backstage/getAllAssistiveDeviceApplicationForm">管理輔具表單</a>
					</li>
					<li>
						<a href="${contextRoot}/Backstage/getAllAgency">管理機構</a>
					</li>
					<li>
						<a href="${contextRoot}/Backstage/getAllCommodities">管理商品</a>
					</li>
					<li>
						<a href="${contextRoot}/Backstage/searchAllNews">管理公告</a>
					</li>

				</ul>
			</li>
			
			<li>
				<a href="#sublist2" data-bs-toggle="collapse" id="dropdown">表單<i class="fa-brands fa-wpforms"></i></a>
				<ul id="sublist2" class="list-unstyled collapse">
					<li>
						<a class="dropdown-item" href="${contextRoot}/Backstage/goAddEmployee">新進員工表單</a>
						<a class="dropdown-item" href="${contextRoot}/Backstage/goAddMember">新進會員表單</a>
						<a class="dropdown-item" href="${contextRoot}/Backstage/goAddRealAssistiveDevice">輔具表單</a>
						<a class="dropdown-item" href="${contextRoot}/Backstage/goAddAgency">機構表單</a>
						<a class="dropdown-item" href="${contextRoot}/Backstage/goAddCommodities">商品表單</a>
						<a class="dropdown-item" href="${contextRoot}/Backstage/addNewsMessage">公告表單</a>
					</li>
					
					
					
				</ul>
			</li>
			<li>
				<a href="${contextRoot}/Backstage/getAllEditLog">編輯紀錄<i class="fa-brands fa-wpforms"></i></a>
			</li>
			
		</ul>


		</nav>
	</div>





	<div class="container-right" style="overflow: auto;">
		<div class="container-fluid">
			<div style="width: 100%; text-align: center;">
				<h2><i class="fa-solid fa-house"></i>後台管理首頁</h2>
			</div>
			
		<hr>
			
			
			<div id="carouselExampleIndicators" class="carousel slide" style="margin: 20px 500px 100px 500px;"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="${contextRoot}/img/backstage/slide1.jfif" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="${contextRoot}/img/backstage/slide2.jfif" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="${contextRoot}/img/backstage/slide3.jfif" class="d-block w-100" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-target="#carouselExampleIndicators" data-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-target="#carouselExampleIndicators" data-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
		</button>
	</div>
	
	
	
	
	
	
	
		<div class="content-wrapper" style="min-height: 621px;">
		<div class="container">

			<section class="content-header">

				<ol class="breadcrumb">
					<li><a href="${contextRoot}/"><i class="fa fa-dashboard">點擊連結前台首頁Home</i>
					</a></li>

					<li class="active"></li>
				</ol>
			</section>

			<section class="content">
				<div class="callout callout-info"
					style="background-color: rgb(240, 207, 41); border-radius: 30px; padding: 30px 30px 30px 30px; margin-bottom: 35px;">
					<h4>歡迎來到iMedical後台管理頁面!</h4>
					
					<p>輕鬆管理企業會員、員工、前端網頁...等資料</p>
					<p style="color: red; font-size: large;"> <b>使用結束請務必登出!</b> </p>
				</div>
				<!-- <div class="callout callout-danger" style="background-color: threedhighlight;">
					<h4>在此輸入訊息</h4>
					<p>在此輸入訊息</p>
				</div>
				<div class="box box-default" style="background-color: threedhighlight;">
					<div class="box-header with-border">
						<h3 class="box-title">在此輸入訊息</h3>
					</div>
					<div class="box-body">在此輸入訊息</div>

				</div> -->

			</section>

		</div>

	</div>

	<footer class="main-footer"
		style="margin-top: 100px; background-color: lightgray;">
		<div class="container" style="width: 100%; text-align: center;">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.01
			</div>
			<strong>Copyright © 2022-2025 <a href="">iMedical</a>.
			</strong> All rights reserved.
		</div>

	</footer>
	
	
	
	
		
		
			
		</div>
	</div>
	</div>
	








	

</body>
</html>