package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CalendarService;
import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/calendar")
public class CalendarController extends HttpServlet {
	// CalendarService에 값을 넘겨주기 위해
	private CalendarService calendarService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* LoginFilter를 사용하여 주석처리
		// 로그인 되어있지 않은 상태에서 요청 처리 불가(calendar 페이지를 따로 쳐도 이동못함)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인되지 못한 상태
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		*/
		
		String currentYear = request.getParameter("currentYear");
		String currentMonth = request.getParameter("currentMonth");
		String option = request.getParameter("option");
		
		// 디버깅코드
		System.out.println("[Jihwan] currentYear 확인 -> " + currentYear + "년도");
		System.out.println("[Jihwan] currentMonth 확인 -> " + currentMonth + "월");
		System.out.println("[Jihwan] option 확인 -> " + option);
		
		// CalendarService 클래스 객체 생성
		calendarService = new CalendarService();
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		System.out.println("[Jihwan] memberId 확인 -> " + memberId);
		
		Map<String, Object> map = calendarService.getTargetCalendar(memberId, currentYear, currentMonth, option);
		
		// 모델
		request.setAttribute("targetYear", map.get("targetYear"));
		request.setAttribute("targetMonth", map.get("targetMonth"));
		request.setAttribute("endDay", map.get("endDay"));
		// 달력을 출력할때 앞/뒤 필요한 공백 <td>
		request.setAttribute("startBlank", map.get("startBlank"));
		request.setAttribute("endBlank", map.get("endBlank"));
		// 달력에 출력할 todo 모델 목록
		request.setAttribute("todoList", map.get("todoList"));
		
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}
}
