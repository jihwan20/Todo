package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout") // /member로 시작 -> 로그인 되어있지 않은 상태에서 요청 처리 불가
public class LogoutController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* LoginFilter를 사용하여 주석처리
		// 로그인 되어있지 않은 상태에서 요청 처리 불가(logout 페이지를 따로 쳐도 이동못함)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인되지 못한 상태
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		*/
		
		// 로그인한 세션의 값을 모두 사라지게 함
		request.getSession().invalidate();
		// 로그인 페이지로 이동
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
}
