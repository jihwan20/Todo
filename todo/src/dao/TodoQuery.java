package dao;

public class TodoQuery {
	public static final String DELETE_TODO; // 회원 탈퇴
	public static final String SELECT_TODO_LIST_BY_DATE; // 캘린더 일정 표현
	public static final String INSERT_TODO_LIST; // 일정 추가
	public static final String DELETE_TODO_LIST; // 일적 삭제
	public static final String UPDATE_TODO_LIST; // 일정 수정
	public static final String SELECT_TODO_LIST_BY_MONTH; // 캘린더 요일마다 content 내용이 간략히 보일 수 있게 
	
	static {
		DELETE_TODO = "DELETE FROM todo WHERE member_id = ?";
		SELECT_TODO_LIST_BY_DATE = "SELECT todo_no todoNo, member_id memberId, todo_date todoDate, todo_content todoContent, create_date createDate, update_date updateDate FROM todo WHERE member_id = ? AND todo_date = ?";
		INSERT_TODO_LIST = "INSERT INTO todo(member_id, todo_date, todo_content, create_date, update_date) VALUES(?,?,?,NOW(),NOW())";
		DELETE_TODO_LIST = "DELETE FROM todo WHERE todo_no = ? AND member_id = ?";
		UPDATE_TODO_LIST = "UPDATE todo SET todo_content=?, update_date=NOW() WHERE todo_no=? AND member_id=?";
		SELECT_TODO_LIST_BY_MONTH = "SELECT todo_date todoDate, SUBSTR(todo_content,1,5) todoContent5 FROM todo WHERE member_id = ? AND SUBSTR(todo_date,1,7) = ? ORDER BY todo_date ASC";
	}
}
