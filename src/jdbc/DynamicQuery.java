package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DynamicQuery {
	public static void main(String[] args) throws IOException {
		String url="jdbc:mariadb://localhost:3306/project_sc";
		String user="root";
		String pw="1234qwer";
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));	
		System.out.print("이름 : ");
		String name=in.readLine();
		System.out.print("전화번호 : ");
		String no=in.readLine();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("driver ok");
			Connection conn=DriverManager.getConnection(url, user, pw);
			System.out.println("login ok");
			String sql="insert into phone values(?,?);";
			
			PreparedStatement ptm=conn.prepareStatement(sql);
			ptm.setString(1, name);
			ptm.setString(2, no);
			ptm.executeUpdate();
			ptm.close();
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
