<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ // 현재 문서가 준비되면 매개변수로 넣은 콜백 함수를 실행하라는 의미
		$('#deleteBtn').click(function(){
			if($('#memberPw').val() == '') {
				alert('memberPw를 입력하세요');
				return;
			}
			
			$('#deleteForm').submit();
		});
	});
</script>
</head>
<body>
	<div class="div">
		<h1>회원탈퇴</h1>
		<h5 class="h5">회원탈퇴를 위한 비밀번호를 입력해주세요</h5><br>
		<form id="deleteForm" method="post" action="${pageContext.request.contextPath}/member/remove">
			<div>	
			   	<input type ="hidden" name="memberId" value="${loginMember.memberId}">
				비밀번호 : <input class="btn btn-light" type="password" id="memberPw" name="memberPw">
			</div>
			<button class="btn btn-outline-dark" type="button" id="deleteBtn" onclick="button()">회원탈퇴</button>
			<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/calendar">뒤로가기</a>
		</form>
	</div>
</body>
</html>