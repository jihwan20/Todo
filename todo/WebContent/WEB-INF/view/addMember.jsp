<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#addBtn').click(function(){
			if($('#memberId').val() == '') {
				alert('memberId를 입력하세요');
				return;
			}
			if($('#memberPw').val() == '') {
				alert('memberPw를 입력하세요');
				return;
			}
			
			$('#addForm').submit();
		});
	});
</script>
</head>
<body>
	<div style="margin-top: 100px; border-radius:15px; width: 500px; height: 400px;" class="container float-center text-center  bg-light">
	<br>
		<h1>회원가입</h1>
		<form id="addForm" method="post" action="${pageContext.request.contextPath}/addMember">
			<table style="margin-top: 60px;" class="table table-bordered">
				<tr>
					<th>아이디</th>
					<td><input class="btn btn-light" type="text" id="memberId" name="memberId"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input class="btn btn-light" type="password" id="memberPw" name="memberPw"></td>
				</tr>
			</table>
			<br>
			<div class="text-center">
				<button class="btn btn-outline-dark" type="button" id="addBtn" onclick="button()">가입하기</button>
				<button class="btn btn-outline-dark" type="reset">초기화</button>
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/login">뒤로가기</a>
			</div>
		</form>
	</div>
</body>
</html>