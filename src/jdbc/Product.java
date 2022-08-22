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

class Db1{
	private Connection conn;
	public Db1() {
		String url="jdbc:mariadb://localhost:3306/mydb";
		String user="root";
		String pw="1234qwer";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insert(Sp p) {
		String sql="insert into sp values(?,?);";
		try {
			PreparedStatement ptm=conn.prepareStatement(sql);
			ptm.setString(1, p.getSpname());
			ptm.setInt(2, p.getSpprice());
			ptm.executeUpdate();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void select() {
		String sql="select * from sp;";
		try {
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString(1)+" : "+rs.getInt(2));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(String spname, int spprice) {
		String sql="update sp set spprice=? where spname=?;";
		try {
			PreparedStatement ptm=conn.prepareStatement(sql);
			ptm.setInt(1, spprice);
			ptm.setString(2, spname);
			ptm.executeUpdate();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(String spname) {
		String sql="delete from sp where spname=?;";
		try {
			PreparedStatement ptm=conn.prepareStatement(sql);
			ptm.setString(1, spname);
			ptm.executeUpdate();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int select_f(String spname) {
		String sql="select spprice from sp where spname=?;";
		int price=0;
		try {
			PreparedStatement ptm=conn.prepareStatement(sql);
			ptm.setString(1, spname);
			ResultSet rs=ptm.executeQuery();
			if(rs.next()) {
				price=rs.getInt(1);
			}
			rs.close();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}
}
class Sp{

	private String spname;
	private int spprice;
	
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	
	public Sp() throws IOException {
		System.out.print("상품명 : ");
		spname=in.readLine();
		System.out.print("가격 : ");
		spprice=Integer.parseInt(in.readLine());
	}
	public String getSpname() {
		return spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public int getSpprice() {
		return spprice;
	}

	public void setSpprice(int spprice) {
		this.spprice = spprice;
	}

}
public class Product {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		Db1 db=new Db1();
		while(true) {
			System.out.print("1.상품등록 2.조회 3.수정 4.삭제 0.프로그램 종료 : ");
			int s=Integer.parseInt(in.readLine());
			if(s==1) {
				db.insert(new Sp());
			}
			if(s==2) {
				db.select();
			}
			if(s==3) {
				System.out.print("상품명 : ");
				String spname=in.readLine();
				System.out.print("가격 : ");
				int spprice=Integer.parseInt(in.readLine());
				db.update(spname, spprice);
			}
			if(s==4) {
				System.out.print("상품명 : ");
				String spname=in.readLine();
				db.delete(spname);
			}
			if(s==0) {
				break;
			}
		}
		System.out.println("프로그램을 종료합니다.");
	}
}
