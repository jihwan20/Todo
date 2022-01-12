<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ // 현재 문서가 준비되면 매개변수로 넣은 콜백 함수를 실행하라는 의미
		$('#loginBtn').click(function(){
			if($('#memberId').val() == '') {
				alert('memberId를 입력하세요');
				return;
			}
			if($('#memberPw').val() == '') {
				alert('memberPw를 입력하세요');
				return;
			}
			
			$('#loginForm').submit();
		});
		// 로그인 엔터키 이벤트
		$('#loginForm').keypress(function(event){
			if(event.which == 13) {
				$('#loginBtn').click();
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div style="margin-top: 100px; border-radius:15px; width: 500px; height: 400px;" class="container float-center text-center  bg-light">
		<br>
		<h1>Todo</h1>
		<form id="loginForm" method="post" action="${pageContext.request.contextPath}/login">
			<table style="margin-top: 60px;" class="table table-bordered">
				<tr>
					<th>아이디</th>
					<td><input class="btn btn-light" type="text" id="memberId" name="memberId" value="test"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input class="btn btn-light" type="password" id="memberPw" name="memberPw" value="1234"></td>
				</tr>
			</table>
			<br>
			<button style="width: 450px;" class="btn btn-outline-dark" type="button" id="loginBtn" onclick="button()">로그인</button>
			
		</form>
	</div>
	<br>
	<div style="text-align: center;">
		<a style="color: black;" href="${pageContext.request.contextPath}/addMember">회원가입</a>
	</div>
</body>
</html>