package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Todo;

// member 테이블에서 memberId에 해당되는 행을 를 삭제시 todo테이블의 memberId의 행도 삭제되어야 한다
public class TodoDao {
	
	public List<Todo> selectTodoListByMonth(Connection conn, Todo todo) throws SQLException {
		// 디버깅 코드
		System.out.println("[Jihwan] memberId 확인 -> " + todo.getMemberId());
		System.out.println("[Jihwan] todoDate(년도,월) 확인 -> " + todo.getTodoDate());
		
		// 쿼리 생성
		String sql = TodoQuery.SELECT_TODO_LIST_BY_MONTH;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		
		List<Todo> list = new ArrayList<Todo>();
		
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent5"));
			
			list.add(t);
		}
		
		// 기록 종료
		rs.close();
		stmt.close();
		
		return list;
	}
	
	// 일정 데이터를 수정하는 메소드
	public int updateTodoList(Connection conn, Todo todo) throws SQLException {
		// 디버깅 코드
		System.out.println("[Jihwan] todoContent 확인 -> " + todo.getTodoContent());
		System.out.println("[Jihwan] todoNo 확인 -> " + todo.getTodoNo());
		System.out.println("[Jihwan] MemberId 확인 -> " + todo.getMemberId());
		
		// 쿼리 생성
		String sql = TodoQuery.UPDATE_TODO_LIST;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getTodoContent());
		stmt.setInt(2, todo.getTodoNo());
		stmt.setString(3, todo.getMemberId());
		
		// 쿼리 실행
		int updateRs = stmt.executeUpdate();
		
		if(updateRs == 1) {
			System.out.println("수정 성공!");
		} else {
			System.out.println("수정 실패!");
		}
		
		// 기록 종료
		stmt.close();
		
		return updateRs;
	}
	
	// 일정 데이터를 삭제하는 메소드
	public int deleteTodoList(Connection conn, Todo todo) throws SQLException {
		// 디버깅 코드
		System.out.println("[Jihwan] todoNo 확인 -> " + todo.getTodoNo());
		System.out.println("[Jihwan] memberId 확인 -> " + todo.getMemberId());
		
		// 쿼리 생성
		String sql = TodoQuery.DELETE_TODO_LIST;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, todo.getTodoNo());
		stmt.setString(2, todo.getMemberId());
		
		// 쿼리 실행
		int deleteRs = stmt.executeUpdate();
		
		if(deleteRs == 1) {
			System.out.println("삭제 성공!");
		} else {
			System.out.println("삭제 실패!");
		}
		
		// 기록 종료
		stmt.close();
		
		return deleteRs;
	}
	
	// 일정 데이터를 추가하는 메소드
	public int insertTodo(Connection conn, Todo todo) throws SQLException {
		// 디버깅 코드
		System.out.println("[Jihwan] memberId 확인 -> " + todo.getMemberId());
		System.out.println("[Jihwan] todoDate 확인 -> " + todo.getTodoDate());
		System.out.println("[Jihwan] todoContent 확인 -> " + todo.getTodoContent());
		
		// 쿼리 생성
		String sql = TodoQuery.INSERT_TODO_LIST;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		stmt.setString(3, todo.getTodoContent());
		
		// 쿼리 실행
		int insertRs = stmt.executeUpdate();
		
		if(insertRs == 1) {
			System.out.println("입력 성공!");
		} else {
			System.out.println("입력 실패!");
		}
		
		// 기록 종료
		stmt.close();
		
		return insertRs;
	}
	
	// 일정에 대한 목록(데이터)을 출력하는 메소드
	public List<Todo> selectTodoListByDate(Connection conn, Todo todo) throws SQLException {
		// 디버깅 코드
		System.out.println("[Jihwan] memberId 확인 -> " + todo.getMemberId());
		System.out.println("[Jihwan] todoDate 확인 -> " + todo.getTodoDate());
		
		// 쿼리 생성
		String sql = TodoQuery.SELECT_TODO_LIST_BY_DATE;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		
		
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		
		List<Todo> list = new ArrayList<Todo>();
		
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoNo(rs.getInt("todoNo"));
			t.setMemberId(rs.getString("memberId"));
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent"));
			t.setCreateDate(rs.getString("createDate"));
			t.setUpdateDate(rs.getString("updateDate"));
			
			list.add(t);
		}
		
		// 기록 종료
		rs.close();
		stmt.close();
		
		return list;
	}
	
	// 회원탈퇴를 하게되면 todo 테이블의 탈퇴한 데이터도 삭제되는 메소드
	public void deleteTodo(Connection conn, String memberId) throws SQLException {
		// 디버깅 코드
		System.out.println("[Jihwan] memberId 확인 -> " + memberId);
		
		// 쿼리 생성
		String sql = TodoQuery.DELETE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		
		// 쿼리 실행
		stmt.executeUpdate();
		
		// 기록 종료
		stmt.close();
	}
}
