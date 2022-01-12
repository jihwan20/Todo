package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/addTodo")
public class AddTodoController extends HttpServlet {
	private TodoService todoService;
	
	// todo(할일)를 추가하는 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// todoList.jsp에서 값 가져옴
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		
		todoService = new TodoService();
		
		Todo todo = new Todo();
		todo.setMemberId(memberId);
		todo.setTodoDate(todoDate);
		todo.setTodoContent(todoContent);
		
		// addTodo() 메소드가 성공한지, 실패한지 확인하기 위한 변수
		int check = todoService.addTodo(todo);
		
		// 문자열 가져오기(y = 0~3번째 자리 숫자), (m = 5번째방부터 시작 5~6 숫자), (d = 8번째방부터 시작 8~9 숫자)
		String y = todoDate.substring(0,4);
		String m = todoDate.substring(5,7);
		String d = todoDate.substring(8,10);
		System.out.println(y);
		System.out.println(m);
		System.out.println(d);
		
		if(check == 1) { // 입력 성공
			System.out.println("추가하였습니다!");
			// todoList 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/todoList?y="+y+"&m="+m+"&d="+d);
			return;
		} else { // 입력 실패
			System.out.println("실패하였습니다!");
			// todoList 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/todoList?y="+y+"&m="+m+"&d="+d);
			return;
		}
	}
}
