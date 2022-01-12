<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 수정 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#updateBtn').click(function(){
			if($('#todoContent').val() == '') {
				alert('수정할 일정을 입력하세요');
				return;
			}
			
			$('#updateForm').submit();
		});
	});
</script>
</head>
<body>
	<div style="margin-top: 100px; border-radius:15px;" class="container bg-light">
		<br>
		<form id="updateForm" method="post" action="${pageContext.request.contextPath}/member/modifyTodo">
		<input type="hidden" name="todoNo" value="${todoNo}">
		<input type="hidden" name="memberId" value="${memberId}">
		<table class="table" style="margin: auto;">
			<tr>
				<th>일정 날짜</th>
				<td><input class="btn btn-light" type="text" name="todoDate" value="${todoDate}" readonly="readonly"></td> 
			</tr>
			<tr>
				<th>일정 내용</th>
				<td><textarea style="resize: none;" class="text-left btn btn-light" id="todoContent" rows="3" cols="120" name="todoContent">${todoContent }</textarea></td> 
			</tr>
			<tr>
				<td colspan="2">
					<div class="float-right">
						<button class="btn btn-outline-dark" type="button" id="updateBtn" onclick="button()">수정하기</button>
						<input class="btn btn-outline-dark" type="button" value="뒤로가기" onclick="history.back();">
					</div>
				</td>
			</tr>
		</table>
		
		</form>
	</div>
</body>
</html>