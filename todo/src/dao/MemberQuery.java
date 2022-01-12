package dao;

public class MemberQuery {
	public static final String LOGIN; // 로그인
	public static final String DELETE_MEMBER; // 회원탈퇴
	public static final String INSERT_MEMBER; // 회원가입
	
	static {
		LOGIN = "SELECT member_id memberId, member_pw memberPw FROM member WHERE member_id = ? AND member_pw = ?";	
		DELETE_MEMBER = "DELETE FROM member WHERE member_id = ? AND member_pw = ?";
		INSERT_MEMBER = "INSERT INTO member(member_id, member_pw, create_date, update_date) VALUES(?,?,NOW(),NOW())";
	}
}
