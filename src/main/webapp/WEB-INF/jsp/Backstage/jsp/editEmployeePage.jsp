<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="default/myNavbar.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>iMedical長照醫療網編輯員工資料頁面</title>


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
				<h2><i class="fa-solid fa-pen-to-square"></i>編輯員工</h2>
			</div>
			
		<hr>



			<form:form action="editEmployee" modelAttribute="someEmployee"
				enctype="multipart/form-data" method="post">

				<form:input type="hidden" path="id"
					value="<%=request.getParameter(\"id\")%>" />
				<br>
				<div class="form-group">
					<label for="employeePhoto">編輯員工照片:</label> 
					<br>
					<img alt="圖片無法顯示" id="thumbnail" style="width: 150px;"
						src="${contextRoot}/Backstage/downloadImage/${empId}" /> 
					<br>
					<input
						type="file" name="employeePic" value="${someEmployee.get().employeePhoto }"  class="form-control"
						aria-describedby="employeePhotoHelp" />
						
				
			
				</div>

				<div class="form-group">
					<label for="account">編輯員工帳號:</label>
					<form:input type="text" path="employeeAccount"
						class="form-control" id="account" 
						aria-describedby="accountHelp" />
				</div>
				<%-- 		<form:errors path="name" cssClass="error" /> --%>
				<!-- 		<span id="name.errors" class="error">此帳號已有人使用</span> -->




				<div class="form-group">
					<label for="password">編輯員工密碼:</label>
					<form:input type="password" path="employeePassword"
						class="form-control" id="password"
						aria-describedby="passwordHelp" />
				</div>
				<div class="form-group">
					<label for="name">編輯員工姓名:</label>
					<form:input type="text" path="employeeName" class="form-control"
						id="name" aria-describedby="nameHelp" placeholder="ex: 王曉明" />
				</div>
				<div class="form-group">
					<label for="phone">編輯員工電話號碼:</label>
					<form:input type="number" path="employeePhone"
						class="form-control" id="phone" aria-describedby="phoneHelp" />
				</div>

				<div class="form-group">
					<label for="email">編輯員工電子郵件:</label>
					<form:input type="email" path="employeeEmail"
						class="form-control" id="email" aria-describedby="emailHelp"
						placeholder="xxxxx@gmail.com" />
				</div>

				<div class="form-group">
					<label for="address">編輯員工地址:</label>
					<form:input type="text" path="employeeAddress"
						class="form-control" id="address"
						aria-describedby="addressHelp" />
				</div>
				<div class="form-group">
					<label for="birthday">編輯員工生日:</label>
					<form:input type="date" path="employeeBirthday"
						class="form-control" id="birthday"
						aria-describedby="birthdayHelp" placeholder="ex: 1998/08/08" />
				</div>

				<div class="form-group">
					<label for="level">編輯員工職等:</label>
					<form:select path="employeeRole" required="required">
						<form:option value="">選擇職等</form:option>
						<form:option value="normal,ROLE_normal">一般員工</form:option>
						<form:option value="admin,normal,ROLE_admin">主管</form:option>
					</form:select>

				</div>

				<div class="form-group">
					<label for="salary">編輯員工薪資:</label>
					<form:input type="number" path="employeeSalary"
						class="form-control" id="salary" aria-describedby="salaryHelp" />
				</div>

				<div class="form-group">
					<label for="onboard">編輯員工到職日期:</label>
					<form:input type="date" path="employeeOnboard"
						class="form-control" id="onboard"
						aria-describedby="onboardHelp" placeholder="ex: 2020/03/04" />
				</div>
				<div style="text-align: center; margin-top: 50px;">
					<form:button type="submit" class="btn btn-primary">送出</form:button>
				</div>
			</form:form>
	



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