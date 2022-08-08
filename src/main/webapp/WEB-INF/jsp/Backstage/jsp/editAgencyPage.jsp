<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="default/myNavbar.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tags" %>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextRoot}/css/mySideBarCSS.css">
<script src="${contextRoot}/js/mySideBarJS.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9bf7222a42.js" ></script>
<script src="${contextRoot}/js/jquery-3.6.0.min.js"></script>

<meta charset="UTF-8">

<link href="${contextRoot}/css/backStage.css" rel="stylesheet" type="text/css" />


<title>iMedical長照醫療網編輯機構頁面</title>
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
			<div style="width: 100%; text-align: center;">
				<h2><i class="fa-solid fa-pen-to-square"></i>編輯機構資料</h2>
			</div>
			<div class="container"  style="width: 100%;" >
			
			<form:form action="editAgency" modelAttribute="someAgency"  enctype="multipart/form-data"
			method="post">
			
				<form:input type="hidden" path="id" value="<%=request.getParameter(\"id\")%>"/>
				
				<div class="card mb-3" >
				<div class="row ">
				
				<div class="col-md-5">
					<img style="width: 500px;" src="${contextRoot}/Backstage/downloadImageAgency/${agencyId}"
						class="card-img" alt="機構圖片">
						<hr>
						<input type="file" name="agencyPic" class="form-control" id="fileImage" aria-describedby="agencyPicHelp"  required="required" />

				<ul class="list-group list-group-flush">
					<li class="list-group-item">機構類型: 
						<form:input type="text" path="agencyType" class="form-control" id="agencyType" aria-describedby="agencyTypeHelp" required="required"/>
					</li>
					<li class="list-group-item">房型: 
						<form:input type="text" path="roomType" class="form-control" id="roomType" aria-describedby="roomTypeHelp"  required="required" />
					</li>
					<li class="list-group-item">機構地址: 
						<form:input type="text" path="agencyAddress" class="form-control" id="agencyAddress" aria-describedby="agencyAddressHelp"  required="required"/>
					</li>
					<li class="list-group-item">床數: 
						<form:input type="number" path="bedNumber" class="form-control" id="bedNumber" aria-describedby="bedNumberHelp" required="required"/>
					</li>
					<li class="list-group-item">營業時間: 
						<form:input type="text" path="workingHours" class="form-control" id="workingHours" aria-describedby="workingHoursHelp" required="required"/>
					</li>
					<li class="list-group-item">機構電話:
						<form:input type="text" path="agencyPhone" class="form-control" minlength="9" maxlength="10" id="phone" aria-describedby="phoneHelp" required="required"/>
					</li>
				</ul>
					
				</div>


			
			<div class="col-md-7">
					<h5 class="card-header">機構名稱:</h5>
					<div class="card-body">
						<h5 class="card-title">
						<form:input type="text" path="agencyName"  lang="en" class="form-control" id="agencyName" aria-describedby="agencyNameHelp" required="required" />
						</h5>

					</div>
			
				<h5 class="card-header">政府補助:</h5>
				<div class="card-body">
					<span>
						<form:textarea rows="8" type="text" path="governmentSubsidy"  class="form-control" id="governmentSubsidy" aria-describedby="governmentSubsidyHelp"  required="required"/>
					</span>
				</div>
			<div class="card">
				<h5 class="card-header">服務項目</h5>
				<div class="card-body">
					<p class="card-text">服務對象: 
						<form:textarea type="text" path="serveTarget" class="form-control" id="serveTarget" aria-describedby="serveTargetHelp" required="required"/>
					</p>
					<p class="card-text">醫療服務: 
						<form:textarea type="" path="medicalService"  class="form-control" id="medicalService" aria-describedby="medicalServiceHelp"  required="required"/>
					</p>
					<p class="card-text">專業照護: 
						<form:textarea type="text" path="professionalCare"  class="form-control" id="professionalCare" aria-describedby="professionalCareHelp" required="required"/>
					</p>
					<p class="card-text">特色服務: 
						<form:textarea type="text" path="featureService"  class="form-control" id="featureService" aria-describedby="featureServiceHelp"  required="required"/>
					</p>
				</div>
			</div>
			</div>
			</div>
				<div style="text-align: center;">
					<form:button type="submit" class="btn btn-primary">送出</form:button>
				</div>
			
			
			</div>
			</form:form>
			

			</div>
			
		</div>
		</div>

			


		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	





		
		
		
		

	
		







	
	
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		$('#fileImage').change(function(){
			showImageThumbnail(this);
		});
		
		
	});
	
	function showImageThumbnail(fileInput){
		file = fileInput.files[0];
		reader = new FileReader();
		
		reader.onload = function(e){
			$('#thumbnail').attr('src', e.target.result);
		};
		
		reader.readAsDataURL(file);
		
	}
	
	
	
	</script>


</body>
</html>