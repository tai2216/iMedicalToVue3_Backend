<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../default/myNavbar.jsp"></jsp:include>
<html>
<head>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<meta charset="UTF-8">
<title>機構介紹</title>

<script src="${contextRoot}/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${contextRoot}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.css">
<!-- my javascript -->
<link rel="stylesheet" href="${contextRoot}/css/my.index.css" />
<!-- my javascript -->
<link rel="stylesheet" href="${contextRoot}/css/myAgency.css" />

</head>

<body>





	<!-- 左側選單-------------------------------------------------------------------------------- -->
	<br>
	<div class="cotainer">
		<div class="row position-absolute top-100% start-100%  bottom-50% ">
			<div class="list-group">
				<a href="${contextRoot}/Agency/viewAgency"><button type="button"
						class="list-group-item list-group-item-action active"
						aria-current="true">機構介紹</button></a> 
						<a
					href="${contextRoot }/Agency/selectAgencyData"><button
						type="button" class="list-group-item list-group-item-action">搜尋機構</button></a>
				<%-- 			    <a href="${contextRoot }/Agency/allAgencyData"><button type="button" class="list-group-item list-group-item-action">機構列表</button></a> --%>
			</div>
		</div>
	</div>

	<c:forEach var="list" items="${agencyDatas.content}">

		<!-- 右側內容 ---------------------------------------------------------------------->
		<br>
		<div style="margin: 30px auto 70px auto;" class="cotainer">
			<div class="row justify-content-center ">
				<h1>機構介紹</h1>
			</div>
		</div>

		<!-- BootStap 方法1 圖左文右 -->
		<div class="cotainer-right"></div>
			<div class="container  translate-right">
				<div class="card border-dark mb-3" style="max-width: 1500px;">
					<div class="row g-0">

						<div class="col-md-6">
							<div class="card-body" style="">
								<h4 class="agencyImgWord">機構圖片</h4>


								<div class="card">
									<img class="agencyImg"
										style="width: 500px;" alt="尚未上傳圖片"
										src="${contextRoot}/Backstage/downloadImageAgency/${list.id}">

									<div class="card">
										<h5 class="card-header">服務對象: </h5>
										<div class="card-body">
											<h5 class="card-title">項目</h5>
											<p class="card-text">${list.serveTarget}</p>
										</div>
									</div>
									

									<div class="card">
										<h5 class="card-header">醫療服務: </h5>
										<div class="card-body">
											<h5 class="card-title">項目</h5>
											<p class="card-text">${list.medicalService}</p>
										</div>
									</div>
				
									<div class="card">
										<h5 class="card-header">特色服務: </h5>
										<div class="card-body">
											<h5 class="card-title">項目</h5>
											<p class="card-text">${list.featureService}</p>
										</div>
									</div>
									


								</div>
							</div>
						</div>

						<div class="col-md-6" style="border-width: 2px;">
							<div class="card-body">
								<h3 class="card-Vatitle">機構資料</h3>

								<div class="card-text">
								
									<div>
										<h4>機構名稱:</h4>
										<span>${list.agencyName}</span>
									</div>
									<div>
										<h4>機構類型:</h4>
										<span>${list.agencyType}</span>
									</div>
									<div>
										<h4>機構電話:</h4>
										<span>${list.agencyPhone }</span>
									</div>
									<div>
										<h4>機構地址:</h4>
										<span>${list.agencyAddress }</span>
									</div>
									<div>
										<h4>服務時間:</h4>
										<span>${list.workingHours }</span>
									</div>

									<table>
										<tr>
											<th>房型:</th>
											<th>床數:</th>
										</tr>
										<tr>
											<td>${list.roomType}</td>
											<td>${list.bedNumber}</td>
										</tr>
									</table>

									<div class="card">
										<h5 class="card-header">政府補助對象: </h5>
										<div class="card-body">
											<h5 class="card-title">項目</h5>
											<p class="card-text">${list.governmentSubsidy}</p>
										</div>
									</div>


									<div class="card">
										<h5 class="card-header">專業照護: </h5>
										<div class="card-body">
											<h5 class="card-title">項目</h5>
											<p class="card-text">${list.professionalCare}</p>
										</div>
									</div>


								</div>
							</div>
						</div>

					</div>
				</div>
			</div>




	</c:forEach>



	<script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>

</body>
</html>