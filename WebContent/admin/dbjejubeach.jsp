<%@page import="com.JejuTravel.dao.BeachDAO"%>
<%@page import="com.JejuTravel.vo.BeachVO"%>
<%@page import="com.JejuTravel.dao.CafeDAO"%>
<%@page import="com.JejuTravel.vo.CafeVO"%>
<%@page import="com.JejuTravel.dao.HotelDAO"%>
<%@page import="com.JejuTravel.vo.HotelVO"%>
<%@page import="com.JejuTravel.vo.FoodVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.JejuTravel.dao.FoodDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	// 사용자 리스트를 담은 리스트를 불러 불러 오자
	// AdminServlet에 저장 시킨 getList를 받아 올수 있다.
	ArrayList<BeachVO> getList = BeachDAO.getBeach();
	// 	for (UserVO vo : getList) { 
	// 		System.out.println("idx: " + vo.getU_idx());
	// 		System.out.println("이름: " + vo.getName());
	// 		System.out.println(vo.getEmail());
	// 		System.out.println(vo.getPhone());
	// 		System.out.println(vo.getPw());
	// 		System.out.println("=======================");
	// 	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>관리자 페이지</title>
</head>
<body>
	<%-- 	<%=vo.getU_idx() %> --%>
	<div class="container" style="margin-left: 50px;">
		<h1>관리자 페이지</h1>
 		<br> <a href="<%=path%>/logout.JejuTravel">로그아웃</a>
 		<br><br>
		<button type="button" class="btn btn-warning" onclick="getUserInfo()">수정</button>
		<button type="button" class="btn btn-danger" onclick="delInfo()">삭제</button>
		<table class="table table-hover" style="width: 1400px;">
			<thead>
				<tr>
					<th>선택 </th>
					<th>번호</th>
					<th>해수욕장</th>
					<th>그림 사이트 주소</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (BeachVO vo : getList) {
				%>
				<tr>
					<td><input type="radio" name="opt" value=<%=vo.getU_idx()%>>
					</td>
					<td><%=vo.getU_idx()%></td>
					<td><%=vo.getName()%></td>
					<td width="40%"><%=vo.getAddress()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

		<!-- Modal 시작 -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">회원 정보 변경</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="phone">그림 사이트 주소</label> <input type="text"
								class="form-control" id="bHours">
						</div>
						<div class="form-group">
							<label for="email">해수욕장</label> <input type="text"
								class="form-control" id="name">
						</div>
					</div>
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning"
						onclick="updateInfo()" data-dismiss="modal">저장</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
	
				</div>
			</div>

		</div>
	<!--   모달 끝 -->

</body>
<script>
	function getUserInfo() {
		var u_idx = $('input[name=opt]:checked').val();
		// ajax 사용
		var path = getContextPath();
		$.ajax({
			type : 'POST',
			url : path + "/getbeachinfo.JejuTravel",
			data : {
				"u_idx" : u_idx
			},
			success : function(data) {
				console.log(data);
				var test = data.split('/');
				console.log(test);
				if ($.trim(test[0]) == 'OK') {
					console.log('정보 불러 오기 성공');
					//             	$("#email").val(data);
					$("#name").val(test[2]);
					$("#address").val(test[3]); // 모달에서 id 값이 name 인 곳에 값 설정
					$("#myModal").modal('show');
					//             	alert("정보 불러 '오기' 성공");
				} else {
					console.log('서버 에러');
				}
			},
		}); //end ajax
	}
	function updateInfo() {
		var u_idx = $('input[name=opt]:checked').val();
		var name = $("#name").val(); // 모달에서 id 값이 name 인 곳에 값 설정
		var address = $("#address").val();
		//  	alert(u_idx + " 번호를 수정 해야함");
		//  	return ;
		// ajax 사용
		var path = getContextPath();
		$.ajax({
			type : 'POST',
			url : path + "/beachupdateinfo.JejuTravel",
			data : {
				"u_idx" : u_idx,
				"name" : name,
				"address" : address
			},
			success : function(data) {
				console.log(data);
				if ($.trim(data) == 'OK') {
					console.log('수정 완료');
					alert("수정이 완료 되었습니다.");
					// 페이지 새로 고침
					location.reload();
				} else {
					console.log('수정 서버 에러');
				}
			},
		}); //end ajax 
	}
	function delInfo() {
		var u_idx = $('input[name=opt]:checked').val();
		alert(u_idx + " 정보를 삭제 하시겠습니까? ");
		// ajax 사용
		var path = getContextPath();
		$.ajax({
			type : 'POST',
			url : path + '/delbeachinfo.JejuTravel',
			data : {
				"u_idx" : u_idx
			},
			success : function(data) {
				console.log(data);
				if ($.trim(data) == 'OK') {
					console.log('수정이나 삭제완료');
					alert("삭제 되었습니다.");
					location.reload(); // 페이지 새로 고침
				} else {
					console.log('서버 에러');
				}
			},
		}); //end ajax 
	}
	// 함수 이름 변경하고
	// id 부분에 삭제나 수정 해야할 idx 번호를 넣고
	// ajax 쪽 url 에 수정이나 삭제 할 주소를 넣어서
	// 해당하는 주소로 가면 컨트롤러쪽으로 보내고
	// 그 컨트롤러에서 UserDAO 로 연결 해서 
	// 삭제나 수정 쿼리를 날려서 처리
	function getContextPath() {
		var hostIndex = location.href.indexOf(location.host)
				+ location.host.length;
		return location.href.substring(hostIndex, location.href.indexOf('/',
				hostIndex + 1));
	}
	// 전달 매개변수는 idx(사용자 번호)
	// url 접속 정보(서블릿으로 보내는)
	function sendData(u_idx, suburl) {
		var path = getContextPath();
		$.ajax({
			type : 'POST',
			url : path + suburl,
			data : {
				"u_idx" : u_idx
			},
			success : function(data) {
				console.log(data);
				if ($.trim(data) == 'OK') {
					console.log('수정이나 삭제완료');
				} else {
					console.log('서버 에러');
				}
			},
		// ajax가 비동기로 동작 하기 때문에 데이터를 동기화 시키기 위해
		// return 값 체크 때문에 
		//async:false    
		}); //end ajax 
		//return isID;
	}
</script>
</html>