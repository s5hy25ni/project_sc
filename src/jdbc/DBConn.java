package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {

	public static void main(String[] args) {

		try { 
			
			String url="jdbc:mariadb://localhost:3306/javaproject01";
			String user="root";
			String pw="1234qwer";
			
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
			
			Connection conn=DriverManager.getConnection(url, user, pw);
			System.out.println("DB 연결 성공 : "+conn);
			
			Statement stm=conn.createStatement();
			
			String sql="insert into abc values('kim', 30);";
			stm.executeUpdate(sql);
			
			stm.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
