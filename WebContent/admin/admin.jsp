
<%@page import="java.util.ArrayList"%>
<%@page import="com.JejuTravel.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	ArrayList<UserVO> getList = (ArrayList<UserVO>)request.getAttribute("userlist");  
	//ArrayList<UserVO> 에 저장되어 있는 값들을 들고온다.
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/2-col-portfolio.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>관리라는게 되는건지</title>
</head>
<body>

	<!-- #Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">DB관리창</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">

						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Food</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="<%=path%>/dbfood.JejuTravel">Food 넣기</a> <a
									class="dropdown-item" href="<%=path%>/modifyfood.JejuTravel">Food 수정</a> 
									</div>
									</div>
									
							</li>
					<li class="nav-item">
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Cafe&Bar</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="<%=path%>/dbcafe.JejuTravel">Cafe&Bar 넣기</a> <a
									class="dropdown-item" href="<%=path%>/modifycafe.JejuTravel">Cafe&Bar 수정</a> 
									</div>
									</div>
									</li>
							<li class="nav-item">
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">HotelFood</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="<%=path%>/dbhotelfood.JejuTravel">hotelFood 넣기</a> <a
									class="dropdown-item" href="<%=path%>/modifyhotelfood.JejuTravel">hotelFood 수정</a> 
									</div></div></li>
								<li class="nav-item">
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">Beach</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="<%=path%>/dbbeach.JejuTravel">Beach 넣기</a> <a
									class="dropdown-item" href="<%=path%>/modifybeach.JejuTravel">Beach 수정</a> 
									</div></div></li>
							<li class="nav-item">
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">ExtremeSports</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="<%=path%>/dbextremesports.JejuTravel">ExtremeSports 넣기</a> <a
									class="dropdown-item" href="<%=path%>/modifyextremesports.JejuTravel">ExtremeSports 수정</a>
									</div></div>
								</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<h2>회원정보 변경 Table</h2>
		<p>일단 연습</p>
		<table class="table table-condensed">
			<thead>
				<tr>
					<th>u_idx</th>
					<th>name</th>
					<th>email</th>
					<th>username</th>
					<th>pw</th>
					<th>grade</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				</tr>
				<%for (UserVO vo : getList) { %>
				<tr>
					<td><%=vo.getU_idx()%></td>
					<td><%=vo.getName()%></td>
					<td><%=vo.getEmail()%></td>
					<td><%=vo.getUsername()%></td>
					<td><%=vo.getPw()%></td>
					<td><%=vo.getGrade()%> 
					<select name="selectBox" id="selectBox-<%=vo.getU_idx()%>" style="height: 30px; width: 40px;">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
					</select>
						<button type="button" class="btn btn-success"
							onclick="updateInfo('<%=vo.getU_idx()%>')" >수정</button>
						<button type="button" class="btn btn-danger"
							onclick="deleteInfo()">삭제</button></td>
				</tr>
				<% } %>

			</tbody>
		</table>
	</div>
	<script>
function updateInfo(U_idx) {
	
	var path = '/' + location.pathname.split('/')[1];
	/* var grade =$("select[name='selectBox']"); */
	var grade = document.getElementById("selectBox-"+U_idx);
	var gradeValue = grade.options[grade.selectedIndex].text;
	/* var U_idx = $("#u_idx").val(); */
	
    $.ajax({
        type: 'POST',
        url: path + "/updateinfo.JejuTravel",
        data: {
        	"u_idx" : U_idx,
            "grade" : gradeValue
        },
        success: function(data){
        	console.log(data);
            if($.trim(data) == 'OK'){
            	console.log('수정');
            	alert("수정이 완료 되었습니다.");
            	location.reload();
            } else { 
            	console.log('서버 에러');
            }
        }, 
    });    //end ajax 
}
function deleteInfo() {
	var U_idx = $("#u_idx").val();
	var path = '/' + location.pathname.split('/')[1];
	alert(U_idx + "   정보를 삭제 하시겠습니까? ");
	console.log("이거나오냐?");
	// ajax 사용
    $.ajax({
        type: 'POST',
        url: path + '/delinfo.JejuTravel',
        data: {
            "u_idx" : U_idx,
        },
        success: function(data){
        	console.log(data);
            if($.trim(data) == 'OK'){
            	console.log('삭제완료');
            	alert("삭제 되었습니다.");
            	location.reload();   // 페이지 새로 고침
            } else { 
            	console.log('서버 에러');
            }
        }, 
    });    //end ajax 
}
</script>
</body>
</html>