package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class TodoService {
	private TodoDao todoDao;
	
	public int modifyTodo(Todo todo) {
		int updateRs = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://52.79.218.125:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			updateRs = todoDao.updateTodoList(conn, todo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateRs;
	}
	
	public int removeTodo(Todo todo) {
		int deletrRs = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://52.79.218.125:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			deletrRs = todoDao.deleteTodoList(conn, todo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deletrRs;
	}
	
	public int addTodo(Todo todo) {
		int insertRs = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://52.79.218.125:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			insertRs = todoDao.insertTodo(conn, todo);
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
	
	public List<Todo> getTodoListByDate(Todo todo) {
		List<Todo> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://52.79.218.125:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			list = todoDao.selectTodoListByDate(conn, todo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
