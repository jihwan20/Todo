package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBListener implements ServletContextListener {

	// 톰캣이 켜짐과 동시에 드라이브를 로딩하겠다
    public void contextInitialized(ServletContextEvent sce)  { 
         try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("org.mariadb.jdbc.Driver 등록 성공!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
	
}
