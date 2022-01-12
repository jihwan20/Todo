package commons;

import java.sql.Connection;
import java.sql.DriverManager;

// DBUtil을 service가 호출해가지고 service가 dao 호출할 때 service가 만든 Connection을 제공해준다 -> 이렇게 한 이유 : 트랜잭션 처리 때문에
public class DBUtil {
	public static Connection getConnection(String url, String user, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
