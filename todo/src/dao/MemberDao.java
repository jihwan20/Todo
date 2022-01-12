package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

// 딱 쿼리만 만드는 곳
public class MemberDao {
	
	// (3) 회원가입 메소드
	public int insertMember(Connection conn, Member member) throws SQLException {
		// 디버깅코드
		System.out.println("[Jihwan] memberId, memberPw 확인 -> " + member.toString());
		
		// 쿼리 생성
		String sql = MemberQuery.INSERT_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		
		// 쿼리 실행
		int insertCheck = stmt.executeUpdate();
		
		if(insertCheck == 1) {
			System.out.println("회원가입 성공!");
		} else {
			System.out.println("회원가입 실패!");
		}
		
		// 기록 종료
		stmt.close();
		
		return insertCheck;
	}
	
	// (2) 회원탈퇴 메소드
	public int deleteMember(Connection conn, String memberId, String memberPw) throws SQLException {
		// 디버깅 코드
		System.out.println("[Jihwan] memberId 확인 -> " + memberId);
		System.out.println("[Jihwan] memberPw 확인 -> " + memberPw);
		
		// 쿼리 생성
		String sql = MemberQuery.DELETE_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		stmt.setString(2, memberPw);
		
		// 쿼리 실행
		int deleteCheck = stmt.executeUpdate();
		
		if(deleteCheck == 1) {
			System.out.println("회원탈퇴 성공!");
		} else {
			System.out.println("회원탈퇴 실패!");
		}
		
		// 기록 종료
		stmt.close();
		
		return deleteCheck;
	}
	
	// (1) 로그인 메소드
	public Member login(Connection conn, Member paramMember) throws SQLException {
		// 디버깅 코드
		System.out.println("[Jihwan] paramMember 확인 -> " + paramMember.toString());
		
		// 쿼리 생성
		String sql = MemberQuery.LOGIN;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, paramMember.getMemberId());
		stmt.setString(2, paramMember.getMemberPw());
		
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		
		// Member 클래스 객체 변수 생성
		Member loginMember = null;
		
		if(rs.next()) {
			// 변수에 Member 클래스 객체 생성
			loginMember = new Member();
			loginMember.setMemberId(rs.getString("memberId"));
			loginMember.setMemberPw(rs.getString("memberPw"));
		}
		
		// 기록 종료
		rs.close();
		stmt.close();
		
		return loginMember;
	}
}
