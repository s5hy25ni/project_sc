package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Sell{
	private String name;
	private String spname;
	private int count;
	private int price;
	private int total;
	
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	
	public Sell() throws IOException {
		System.out.print("이름 : ");
		name=in.readLine();
		System.out.print("상품명 : ");
		spname=in.readLine();
		System.out.print("수량 : ");
		count=Integer.parseInt(in.readLine());
		Db1 db=new Db1();
		price=db.select_f(spname);
		total=count*price;
	}
	public void display() {
		System.out.println(name+"님 "+spname+" "+count+"개 가격은 "+total+"원 입니다.");
	}
}
public class ProductSell {
	public static void main(String[] args) throws IOException {
		new Sell().display();
	}
}
