<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../default/myNavbar.jsp"></jsp:include>
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
<meta charset="UTF-8">
<title>iMedical長照醫療網編輯紀錄頁面</title>
<link href="${contextRoot}/css/backStage.css" rel="stylesheet"
	type="text/css" />


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
				<h2><i class="fa-solid fa-pen-to-square"></i>管理員工</h2>
			</div>
			
		<hr>


		
		
		
		<div
		style="width: 100%; text-align: center; margin-top: 20px; margin-bottom: 30px;">
		<form action="../Backstage/">
			<input name="id" type="search" required="required"
				style="border-radius: 10px; width: 50%;" placeholder="">
			<input type="submit" value="查詢" 
				style="border-style: groove; border-radius: 5px;">

		</form>
	</div>




	<div
		style="border: 2px groove; width:100%; background-color:highlighttext; border-radius: 2em; padding: 30px 30px 30px 30px; text-align: center;">

		<h1>查看所有編輯紀錄</h1>




		<table class="table table-hover" style="background-color: white; border-width: 2px;">
			<tr
				style="margin: 30px 30px 30px 30px; background-color: silver;">
				<th>編輯人姓名</th>
				<th>編輯人動作</th>
				<th>變更資料</th>
				<th>變更資料之編號</th>
				<th>編輯時間</th>
				<th></th>
			</tr>



			<c:forEach var="list" items="${page.content}">
				<tr>
					<td>${list.employeeName}</td>
					<td>${list.employeeAction}</td>
					<td>${list.tableName}</td>
					<td>${list.updatedTableId}</td>
					<td>
					<fmt:formatDate value="${list.editTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td><a
						href="${contextRoot}/Backstage/deleteEditLog?id=${list.id}"
						onclick="return confirm('確認刪除嗎?')">刪除</a></td>
				</tr>

			</c:forEach>






		</table>

		<div class="row justify-content-center" style="text-align: center;">
			<div class="col-9">
			<
			
				<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
					<c:choose>
						<c:when test="${page.number!=pageNumber-1 }">
							<a href="${contextRoot}/Backstage/getAllEditLog?p=${pageNumber}">
								<c:out value="${pageNumber}"></c:out>
							</a>
						</c:when>
						<c:otherwise>
							<c:out value="${pageNumber}"></c:out>
						</c:otherwise>


					</c:choose>
					<c:if test="${pageNumber != page.totalPages}">|</c:if>

				</c:forEach>
				 > 
				
			</div>
		</div>
	</div>





	<footer class="main-footer">
		<div class="container">
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