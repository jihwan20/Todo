package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import dao.MemberDao;
import dao.TodoDao;
import vo.Member;

public class MemberService {
	// MemberService가 어떤 행위를 하기위해서 사용하는 memberDao, TodoDao는 매번 동일해야 하니 이렇게 하나로 만들어준다
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	public int addMember(Member member) {
		// 디버깅 코드
		System.out.println("[Jihwan] memberId 확인 -> " + member.getMemberId());
		System.out.println("[Jihwan] memberPw 확인 -> " + member.getMemberPw());
		
		int insertRs = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://52.79.218.125:3306/todo", "root", "java1004");
			memberDao = new MemberDao();
			insertRs = memberDao.insertMember(conn, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertRs;
	}
	
	public boolean removeMember(String memberId, String memberPw) {
		boolean result = false;
		// 디버깅 코드
		System.out.println("[Jihwan] memberId 확인 -> " + memberId);
		System.out.println("[Jihwan] memberPw 확인 -> " + memberPw);
		Connection conn  = DBUtil.getConnection("jdbc:mariadb://52.79.218.125:3306/todo", "root", "java1004");
		try {
			conn.setAutoCommit(false);
			memberDao = new MemberDao();
			todoDao = new TodoDao();
			
			// todoDao deleteTodo() 메소드 먼저 실행
			todoDao.deleteTodo(conn, memberId);
			// 비빌번호가 틀려서 삭제가 안될경우 강제로 예외를 발생(throw)시켜 catch절로 이동해서 롤백되도록
			if(memberDao.deleteMember(conn, memberId, memberPw) != 1) {
				throw new Exception();
			}
			
			conn.commit(); // try 절이 정상적으로 실행되면 finally 절으로
			result = true;
		} catch (Exception e) { // try 절에서 하나라도 실패하면 catch절로 가서 conn은 롤백되고 false를 리턴
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Member login(Member member) {
		Member loginMember = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://52.79.218.125:3306/todo", "root", "java1004");
			memberDao = new MemberDao();
			loginMember = memberDao.login(conn, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("[Jihwan] conn이 종료되었습니다");
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
}
