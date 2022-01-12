package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	// 컨트롤러 클래스가 서비스 클래스를 메소드마다 생성하기 보단 한 번에 지정
	private MemberService memberService;
	
	// 로그인 홈페이지
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이미 로그인 되었다면 요청 처리 불가(login 페이지를 따로 쳐도 이동못함)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 이미 로그인 되어있는 상태
			response.sendRedirect(request.getContextPath() + "/member/calendar");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	// 로그인 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("utf-8"); // doPost() 메소드 상단에(요청처리전) 무조건 있어야한다 - post방식으로 넘어오는 데이터들의 인코딩 셋팅
		
		// 이미 로그인 되었다면 요청 처리 불가(login 페이지를 따로 쳐도 이동못함) -> 이 로직도 필터로 사용 가능하다
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 이미 로그인 되어있는 상태
			response.sendRedirect(request.getContextPath() + "/member/calendar");
			return;
		}
		
		// login.jsp에서 입력값 가져옴
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println("[Jihwan] memberId 확인 -> " + memberId);
		System.out.println("[Jihwan] memberPw 확인 -> " + memberPw);
		
		// Member 클래스 객체 생성
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		
		// memberService 변수에 MemberService 객체 생성
		memberService = new MemberService(); 
		Member loginMember = memberService.login(paramMember);
		if(loginMember == null) { // 로그인 실패하면
			// 로그인 get 방식인 홈페이지로 돌아가게 한다
			System.out.println("로그인이 되어있지 않으므로 로그인하세요");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		// 일반 jsp에서는 session이 있지만, 여기에는 없기때문에 session을 가지고옴
		session.setAttribute("loginMember", loginMember);
		// 로그인이 됬다면 calendar 페이지로 이동한다
		response.sendRedirect(request.getContextPath() + "/member/calendar");
	}
}
