<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="default/myNavbar.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextRoot}/css/mySideBarCSS.css">
<script src="${contextRoot}/js/mySideBarJS.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9bf7222a42.js" ></script>
<meta charset="BIG5">
<title>iMedical長照醫療網管理機構頁面</title>
</head>
<body>

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
				<h2><i class="fa-solid fa-pen-to-square"></i>管理機構</h2>
			</div>
			
		<hr>

		
		
		
		<div  style="width: 100%; ">
			<h1 style="text-align: center;">管理所有機構</h1>

		<c:forEach var="list" items="${page.content}">


			<%-- <h3>${list.agencyName}</h3> --%>
			<%-- <i class="fa fa-map-marker icon-darker fa-lg fa-fw">${list.agencyAddress} &nbsp</i> --%>
			<%-- <div class="div.left"><img alt="圖片無法顯示" src="${contextRoot}/Backstage/downloadImageAgency/${list.id}"> </div> --%>
			<%-- <div class="div.right"><p>${list.workingHours}</p> </div> --%>
			<!-- <hr> -->

			<div class="container"  style="width: 100%;" >
			
				<div class="card mb-3" >
				<div class="row ">
				
				<div class="col-md-5">
					<img style="width: 500px;" src="${contextRoot}/Backstage/downloadImageAgency/${list.id}"
						class="card-img-left" alt="機構圖片">

				<ul class="list-group list-group-flush">
					<li class="list-group-item">機構類型: ${list.agencyType}</li>
					<li class="list-group-item">房型: ${list.roomType}</li>
					<li class="list-group-item">機構地址: ${list.agencyAddress}</li>
					<li class="list-group-item">床數: ${list.bedNumber}</li>
					<li class="list-group-item">營業時間: ${list.workingHours}</li>
					<li class="list-group-item">機構電話: ${list.agencyPhone}</li>
				</ul>
					
				</div>


			
			<div class="col-md-7">
					<h5 class="card-header">機構名稱:</h5>
					<div class="card-body">
						<h5 class="card-title">${list.agencyName }</h5>

					</div>
			
				<h5 class="card-header">政府補助:</h5>
				<div class="card-body">
					<span>${list.governmentSubsidy}</span>
				</div>
			<div class="card">
				<h5 class="card-header">服務項目</h5>
				<div class="card-body">
					<p class="card-text">服務對象: ${list.serveTarget}</p>
					<p class="card-text">醫療服務: ${list.medicalService}</p>
					<p class="card-text">專業照護: ${list.professionalCare}</p>
					<p class="card-text">特色服務: ${list.featureService}</p>
				</div>
			</div>
			<p>
				<a href="${contextRoot}/Backstage/goEditAgency?id=${list.id}"
						>編輯此機構</a>
			</p>
			
			<p>
				<a href="${contextRoot}/Backstage/deleteAgency?id=${list.id}"
						onclick="return confirm('確認刪除嗎?')">刪除此機構</a>
			</p>
			</div>
			
			</div>
				</div>
				</div>
			
			
			<hr>
		</c:forEach>
		
		
		


		<div class="row justify-content-center" style="text-align: center;">
			<div class="col-9">
				<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
					<c:choose>
						<c:when test="${page.number!=pageNumber-1 }">
							<a href="${contextRoot}/Backstage/getAllAgency?p=${pageNumber}">
								<c:out value="${pageNumber}"></c:out>
							</a>
						</c:when>
						<c:otherwise>
							<c:out value="${pageNumber}"></c:out>
						</c:otherwise>


					</c:choose>
					<c:if test="${pageNumber != page.totalPages}"></c:if>

				</c:forEach>
			</div>
		</div>

	</div>

	

	<footer class="main-footer" style="margin-top: 100px;">
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