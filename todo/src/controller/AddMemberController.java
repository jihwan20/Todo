package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.Member;

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {
	private MemberService memberService;
	
	// 회원가입 폼 페이지
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
	}

	// 회원가입 폼 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// addMember.jsp에서 값 가져옴
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// 디버깅 코드
		System.out.println("[Jihwan] memberId 확인 -> " + memberId);
		System.out.println("[Jihwan] memberPw 확인 -> " + memberPw);
		
		// member변수에다 입력 받은 값 세팅
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// MemberService 클래스 객체 생성
		memberService = new MemberService();
		
		// addMember() 메소드가 성공했는지 실패했는지 여부를 알기위한 변수
		int check = memberService.addMember(member);
		
		if(check == 1) { // 회원가입 성공
			System.out.println(memberId + "님 회원가입 성공하였습니다");
			// 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else { // 회원가입 실패
			System.out.println(memberId + "님 회원가입 실패하였습니다");
			// 회원가입 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/addMember");
			return;
		}
	}
}
