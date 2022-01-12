<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- foreach문을 사용하기 위해 설정 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div>
		<h5 class="text-left">
			${loginMember.memberId}님 반갑습니다.
		<a class="btn btn-outline-light bg-dark text-white btn-sm" href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
		<a class="btn btn-outline-light bg-dark text-white btn-sm" href="${pageContext.request.contextPath}/member/remove">회원탈퇴</a>
		</h5>
	</div>

	<div>
		<!-- 달력 + todo -->
		<h3 class="container text-center">
			<span>
				<a class="btn btn-outline-dark btn-lg" href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=pre">◀</a>
			</span>
			${targetYear}년 ${targetMonth}월
			<span>
				<a class="btn btn-outline-dark btn-lg" href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=next">▶</a>
			</span>
		</h3>

		<h3 class="text-center">총 일정 수 : ${todoList.size()}개</h3>
		<table class="container table table-secondary text-center" style="margin-left: auto; margin-right: auto; border-radius: 15px;">
			<tr class="bg-dark text-white">
				<td style="color: red">일</td>
				<td>월</td>
				<td>화</td>
				<td>수</td>
				<td>목</td>
				<td>금</td>
				<td style="color: blue">토</td>
			</tr>
			<tr>
				<!-- JSTL for문 -->
				<c:forEach var="i" begin="1" end="${startBlank + endDay + endBlank}"
					step="1">
					<!-- {startBlank + endDay + endBlank} : 전체 <td>의 개수 -->
					<c:if test="${i-startBlank >= 1 && i-startBlank <= endDay}">
						<td style="width: 35px; height: 120px;">
						<a style="font-weight: bold; color: black" href="${pageContext.request.contextPath}/member/todoList?y=${targetYear}&m=${targetMonth}&d=${i-startBlank}">${i-startBlank}</a>
							<div>
								<!-- 날짜별 일정 -->
								<c:forEach var="todo" items="${todoList}">
									<c:if test="${(i-startBlank) == todo.todoDate.substring(9)}">
										<!-- todo.todoDate.substring(8)는 2021-10-22이면 22를 의미 -->
										<div>${todo.todoContent}</div>
									</c:if>
								</c:forEach>
							</div></td>
					</c:if>
					<c:if test="${i-startBlank < 1 || i-startBlank > endDay}">
						<td>&nbsp;</td>
						<!-- &nbsp; : 한칸띄움 -->
					</c:if>

					<c:if test="${i%7 == 0 }">
			</tr>
			<tr>
				</c:if>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>