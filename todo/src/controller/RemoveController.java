package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;

@WebServlet("/member/remove")
public class RemoveController extends HttpServlet {
	// 컨트롤러 클래스가 서비스 클래스를 메소드마다 생성하기 보단 한 번에 지정
	private MemberService memberService;
	
	// 회원탈퇴 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/remove.jsp").forward(request, response);
	}

	// 회원탈퇴 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// remove.jsp에서 값 가져옴
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println("[Jihwan] memberId 확인 -> " + memberId);
		System.out.println("[Jihwan] memberPw 확인 -> " + memberPw);
		
		memberService = new MemberService();
		
		// removeMember() 메소드가 성공한지, 실패한지 확인하기 위한 변수
		boolean check = memberService.removeMember(memberId, memberPw);
		
		if(check == false) { // 회원탈퇴 실패
			System.out.println(memberId + "님 회원탈퇴 실패하였습니다");
			// 캘린더 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/calendar");
			return;
		} else { // 회원탈퇴 성공
			System.out.println(memberId + "님 회원탈퇴 성공하였습니다");
			// logout 서비스를 이용하여 기존 세션에 대한 정보를 제거하고 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/logout");
			return;
		}
	}
}

