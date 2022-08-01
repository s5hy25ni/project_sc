package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Db{
	private Connection conn;
	public Db() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url="jdbc:mariadb://localhost:3306/project_sc";
			String user="root";
			String password="1234qwer";
			conn=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(WhitePages p) {
		String sql="insert into whitePages values(?,?);";
		try {
			PreparedStatement pmt=conn.prepareStatement(sql);
			pmt.setString(1, p.getName());
			pmt.setString(2, p.getNumber());
			pmt.executeUpdate(); // db 반영
			pmt.close();
//			conn.close(); // 쓸 거 같으면 냅둠
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void select() {
		try {
			Statement stm=conn.createStatement();
			String sql="select * from whitePages;";
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString(1)+": "+rs.getString(2));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

class WhitePages{
	private String name;
	private String number;
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	public WhitePages() throws IOException {
		System.out.print("이름: ");
		name=in.readLine();
		System.out.print("전화번호: ");
		number=in.readLine();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}

public class WPProject {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		Db db=new Db();
		while(true) {
			System.out.print("1.등록 2.출력 0.종료 : ");
			int s=Integer.parseInt(in.readLine());
			if(s==1) {
				db.insert(new WhitePages());
			}
			if (s==2) {
				db.select();
			}
			if(s==0) {
				break;
			}
		}
		System.out.println("프로그램을 종료합니다.");
	}

}
