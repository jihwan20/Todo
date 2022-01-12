<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- foreach문을 사용하기 위해 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 상세보기 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#addBtn').click(function(){
			if($('#addContent').val() == '') {
				alert('내용을 입력하세요');
				return;
			}
			
			$('#addForm').submit();
		});
		
		// 삭제
		$('#deleteTodoBtn').click(function(){
			var result = confirm('일정을 삭제하겠습니까?');
			if(result) {
				location.replace('${pageContext.request.contextPath}/member/removeTodo?todoNo=${t.todoNo}&memberId=${t.memberId}&todoDate=${t.todoDate}');
			} else {
				
			}
		});
	});
</script>
</head>
<body>
<div style="margin-top: 100px; border-radius:15px;" class="container float-center bg-light">
	<div class="container">
		<br>
		<h1 class="text-center">${todoDate} 일정</h1>
		<table class="table text-center">
			<tr>
				<td style="width: 30%;">일정날짜</td>
				<td style="width: 40%;">일정내용</td>
				<td style="width: 15%;">수정</td>
				<td style="width: 15%;">삭제</td>
			</tr>
			<c:forEach var="t" items="${todoList}">
				<tr>
					<td>${todoDate}</td> <!-- 날짜는 todoList에 있지만 위에도 있으니 위에꺼 사용 -->
					<td>${t.todoContent}</td>
					<td><a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/modifyTodo?todoNo=${t.todoNo}&memberId=${t.memberId}&todoDate=${t.todoDate}&todoContent=${t.todoContent}">수정</a></td>
					<td><a id="deleteTodoBtn" class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/removeTodo?todoNo=${t.todoNo}&memberId=${t.memberId}&todoDate=${t.todoDate}">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
		<form id="addForm" method="post" action="${pageContext.request.contextPath}/member/addTodo">
		<!-- AddTodoController.doPost() 생성 -->
			<input type="hidden" name="memberId" value="${memberId}">
			<table class="table" style="margin: auto;">
				<tr>
					<th>일정 날짜</th>
					<td><input style="border: none;" class="text-left btn btn-light" type="text" name="todoDate" value="${todoDate}" readonly="readonly"></td>
				</tr>
				<tr>
					<th>일정 내용</th>
					<td><textarea style="resize: none;" class="text-left btn btn-light" id="addContent" rows="3" cols="120" name="todoContent"></textarea></td>
				</tr>
				<br>
			<tr>
			<td colspan="2">
			<div class="float-right">
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/calendar">달력보기</a>
				<button style="text-align: right;" class="btn btn-outline-dark" type="button" id="addBtn" onclick="button()">추가하기</button>
			</div>
			</td>
			</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>