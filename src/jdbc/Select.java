package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
	public static void main(String[] args) {
		
		String url="jdbc:mariadb://localhost:3306/project_sc";
		String user="root";
		String pw="1234qwer";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// System.out.println("drive ok");
			
			Connection conn=DriverManager.getConnection(url, user, pw);
			// System.out.println("connection ok : "+conn);
			
			Statement stm=conn.createStatement();
			String sql="select * from sj;";
			ResultSet rs=stm.executeQuery(sql);
			
			int total=0;
			float avg=0.0f;
			
			while(rs.next()) {
				total=rs.getInt(2)+rs.getInt(3)+rs.getInt(4);
				avg=(float)total/3;
				System.out.println(rs.getString(1)+":"+total+":"+avg);
			}
			
			rs.close();
			stm.close();
			conn.close();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
