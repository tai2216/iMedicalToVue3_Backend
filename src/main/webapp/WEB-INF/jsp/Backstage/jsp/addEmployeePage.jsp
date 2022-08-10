<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="default/myNavbar.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tags" %>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
<!-- Bootstrap 連結 -->
<meta charset="UTF-8">

<title>新進員工</title>
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
				<h2><i class="fa-solid fa-pen-to-square"></i>新進員工表單</h2>
			</div>
			
		<hr>
			<div>
					<img style="width: 150px;"  id="thumbnail" alt="尚未上傳大頭照" src=""/>
			</div> 
			
		<form:form action="addEmployee" modelAttribute="employeeBean"  enctype="multipart/form-data"
			method="post"  onsubmit="return checkFlag()">
			
			<form:input type="hidden" path="id" />
			<br>
				<label for="fileImage">上傳員工大頭照* <i class="fa-solid fa-camera"></i></label>
			<div class="form-group" >
				<input type="file" name="employeePic" id="fileImage" required="required" />
<!-- 				<input type="file" name="employeePhoto"   accept=".jpg, .png" -->
<!-- 					class="form-control-sm" id="fileImage" aria-describedby="fileImageHelp" required="required" /> -->
					
			</div>
			<hr>
				<label for="account">帳號* <i class="fas fa-user-tie" aria-hidden="true"></i></label>
			<div class="form-group">
				<form:input type="text" path="employeeAccount" 
					name="account" onchange="flag=0" 
					class="form-control" id="account" aria-describedby="accountHelp" required="required" />
				<sec:csrfInput />
				<input name="csrfToken" value="${_csrf.token}" type="hidden">
				<input class="btn-sm btn-primary" type="button" value="檢查帳號" onclick="check()">
				<div id="validateMessage"></div>
			</div>
	<%-- 		<form:errors path="name" cssClass="error" /> --%>
	<!-- 		<span id="name.errors" class="error">此帳號已有人使用</span> -->
			<hr>
				<label for="password">密碼* <i class="fa-solid fa-lock"></i></label>
			<div class="form-group">
				<form:input type="password" path="employeePassword" 
					class="form-control" id="password" aria-describedby="passwordHelp" required="required"/>
			</div>
			<hr>
				<label for="name">姓名*<i class="fas fa-user-tie" aria-hidden="true"></i></label>
			<div class="form-group">
				<form:input type="text" path="employeeName" class="form-control"
					id="name" aria-describedby="nameHelp" placeholder="ex: 王曉明" required="required" />
				
			</div>
			

			
			<hr>
				<label for="phone">手機號碼* <i class="fa-solid fa-mobile-screen-button"></i></label>
			<div class="form-group">
				<form:input type="number" path="employeePhone" class="form-control" minlength="10" maxlength="10"
					id="phone" aria-describedby="phoneHelp" hint="最少10位" placeholder="ex:0968111259" required="required"/>
			</div>
			<hr>
				<label for="email">電子郵件* <i class="fa-solid fa-envelope"></i></label>
			<div class="form-group">
				<form:input type="email" path="employeeEmail" class="form-control"
					id="email" aria-describedby="emailHelp" placeholder="xxxxx@gmail.com" required="required"/>
			</div>		
			<hr>
				<label for="address">地址* <i class="fa-solid fa-location-dot"></i></label>
			<div class="form-group">
				<form:input type="text" path="employeeAddress" class="form-control"
					id="address" aria-describedby="addressHelp" required="required"/>
			</div>
			<hr>
				<label for="birthday">生日* <i class="fa-solid fa-cake-candles"></i></label>
			<div class="form-group">
				<form:input type="date" path="employeeBirthday" class="form-control"
					id="birthday" aria-describedby="birthdayHelp" placeholder="ex: 1998/08/08" required="required" />
			</div>		
			<hr>
				<label for="level">職等*</label>
			<div class="form-group">
				<form:select path="employeeRole" required="required">
					<form:option value="">選擇職等</form:option>
					<form:option value="normal,ROLE_normal">一般員工</form:option>
					<form:option value="admin,normal,ROLE_admin">主管</form:option>
				</form:select>

			</div>		
			<hr>
				<label for="salary">薪資* <i class="fa-solid fa-sack-dollar"></i></label>
			<div class="form-group">
				<form:input type="number" path="employeeSalary" class="form-control" min="30000" max="100000"
					id="salary" aria-describedby="salaryHelp" required="required"/>
			</div>		
			<hr>
				<label for="onboard">到職日期* <i class="fa-solid fa-calendar"></i></label>
			<div class="form-group">
				<form:input type="date" path="employeeOnboard"  class="form-control" 
					id="onboard" aria-describedby="onboardHelp" placeholder="ex: 2020/03/04" required="required"/>
			</div>			
					
			<div style="text-align: center; margin-top: 50px;">
				<form:button type="submit" class="btn-lg btn-primary">送出</form:button>
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
	
	
	
	
	var flag=0; //0=false 驗證未通過 如果帳號可用則將flag設為1
	
	function check(){
		//抓到id叫做account的html元素命名為變數account
		let account = $('#account').val();
		//抓到input元素裡面name叫做csrfToken的元素裡面屬性的value命名為變數token
		var token =$('input[name="csrfToken"]').attr('value');
		//console.log(token);
		
		//ajax較完整寫法
		$.ajax({
			url:"${contextRooot}/iMedical/Backstage/checkAccount",
			data: {account},
			method: "POST",
			//傳入csrf的token以便通過spring security控管
			headers: {
				"X-CSRF-Token":token
			},
			success: function(re){
				flag = (re == "可使用此帳號") ? 1 : 0;
				if(flag){
					$('#validateMessage').html('<div style="color: green;"><b>此帳號可以使用</b></div>');
				}else{
					$('#validateMessage').html('<div style="color: red;"><b>此帳號無法使用，請輸入其他帳號</b></div>');
				}
			}
		});
//		原本寫法 但因為無法傳入csrf因此改寫為上述
// 		$.post("${contextRooot}/iMedical/Backstage/checkAccount",{account},function(re){

// 			flag = (re == "可使用此帳號") ? 1 : 0;
// 			console.log(flag);
			
// 			if(flag){
// 				$('#validateMessage').html('<div style="color: green;"><b>此帳號可以使用</b></div>');
// 			}else{
// 				$('#validateMessage').html('<div style="color: red;"><b>此帳號無法使用，請輸入其他帳號</b></div>');
// 			}
// 		});






		
	}
	
	//若flag=0 則在表單送出時會return false阻止提交並印出提示
	function checkFlag(){
		if (!flag){
			$('#validateMessage').html('<div style="color: red;"><b>此帳號無法使用</b></div>');
			return false;
		}
	}
	

	
	
	
	
	</script>


</body>
</html>