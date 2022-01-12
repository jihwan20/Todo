package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

// CalendarController.java와 calendar.jsp의 중간매개체 (메소드를 넘겨줌)
public class CalendarService {
	private TodoDao todoDao;
	
	public Map<String, Object> getTargetCalendar(String memberId, String currentYear, String currentMonth, String option) { // option : pre, next
		// 1. 달력 소스 코드
		Map<String, Object> map = new HashMap<>();
		// 오늘 날짜를 보여주기 위해(오늘 날짜의 년도와 월을 가진다)
		Calendar c = Calendar.getInstance();
		
		if(currentYear != null && currentMonth != null) { // 매개변수의 값인 cuurentYear값과 currentMonth값이 들어오면
			int y = 0;
			int m = 0;
			y = Integer.parseInt(currentYear); // 숫자로 재정의
			// 알고리즘 구현에서는 1월 ~ 12월
			m = Integer.parseInt(currentMonth); // 숫자로 재정의
			if(option != null && option.equals("pre")) {
				m = m - 1; // m값이 0이될 때 이슈발생 
				if(m == 0) { // 이슈 해결
					m = 12;
					y = y - 1; // y--, y-=1
				}
			} else if(option != null && option.equals("next")) {
				m = m + 1; // m값이 13이될 때 이슈발생
				if(m == 13) { // 이슈 해결
					m = 1;
					y = y + 1; // y++, y+=1
				}
			}
			c.set(Calendar.YEAR, y); // 매개변수의 값인 년도로 바꾸어준다
			c.set(Calendar.MONTH, m-1); // 매개변수의 값인 월로 바꾸어준다(월은 자바 api에선 0~11)
		}
		
		c.set(Calendar.DATE, 1); // c 객체가 오늘의 정보인데 -> 같은 달 1일로 변경
		
		// 달력에 필요한 데이터모델(view에다 넘겨줌)
		int targetYear = c.get(Calendar.YEAR); // 오늘 년도
		int targetMonth = c.get(Calendar.MONTH) + 1; // 오늘 월, +1을 한 이유 : 월은 1월 부터 12월 이지만 프로그램에선 0부터 11이므로 
		int endDay = c.getActualMaximum(Calendar.DATE); // 오늘 월의 마지막 일자
		
		// 달력 앞, 뒤 공백의 개수
		int startBlank = 0; // 현재 타겟이 되는 달의 1일의 요일 -> 일요일이면 0, 월요일 1... 토요일 6이 필요
		startBlank = c.get(Calendar.DAY_OF_WEEK) -1;
		int endBlank = 0; // 전체의 <td>의 개수 = startBlank + endDay + endBlank ------ (startBlank + endDay) / 7 했을 때 나누어 떨어지면 endBlank는 0
		endBlank = 7 - (startBlank + endDay) % 7;
		if(endBlank == 7) {
			endBlank = 0;
		}
		
		map.put("targetYear", targetYear);
		map.put("targetMonth", targetMonth);
		map.put("endDay", endDay);
		map.put("startBlank", startBlank);
		map.put("endBlank", endBlank);
		
		System.out.println("[Jihwan] map 확인 -> " + map);
		
		// 2. 달력에 추가할 모델(todo) 알고리즘 코드
		List<Todo> todoList = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://52.79.218.125:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			Todo todo = new Todo(); // memberId, todoDate(년,월)가 필요, memberId는 매개변수로 입력받고, todoDate는 위에서 구해놓은 targetYear, targetMonth를 이용해서 구함
			todo.setMemberId(memberId);
			
			/*
			String t = ""; // t는 공백 변수 (targetMonth가 1,2,3...9일때 두자리 수를 만들기 위한 변수)
			if(targetMonth < 10) { // targetMonth가 10보다 작으면
				t="0";
			}
			*/
			
			String strYear ="" + targetYear;
			String strMonth ="" + targetMonth;
			if(targetMonth < 10) { // targetMonth가 10보다 작으면
				strMonth = "0" + targetMonth;
			}
			todo.setTodoDate(strYear+"-"+strMonth);
			System.out.println("[Jihwan] todo 확인 -> " +todo);
			
			todoList = todoDao.selectTodoListByMonth(conn, todo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		map.put("todoList", todoList);
		
		return map;
	}
}
