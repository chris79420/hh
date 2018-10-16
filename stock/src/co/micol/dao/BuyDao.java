package co.micol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import co.micol.bean.BuyBean;

public class BuyDao {
	
	static Connection conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	Scanner sc = new Scanner(System.in);
	
	public BuyDao() {
		try {
			String user = "hr";
			String pw = "hr";
			String url = "jdbc:oracle:thin:@192.168.0.71:1521:xe"; // xe : SID 값

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");
			viewBuy();

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}
	
	
	public void insertBuy() {
		String sql ="Insert into buy_ent "
				+ "values(?,?,?,?,?)";

		BuyBean B = new BuyBean();
		System.out.println("b_code를 입력하세요.");
		B.setB_code(sc.nextLine());
		System.out.println("b_name를 입력하세요.");
		B.setB_name(sc.nextLine());
		System.out.println("b_addr를 입력하세요.");
		B.setB_addr(sc.nextLine());
		System.out.println("b_tel를 입력하세요.");
		B.setB_tel(sc.nextLine());
		System.out.println("b_rep를 입력하세요.");
		B.setB_rep(sc.nextLine());		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, B.getB_code());
			pstmt.setString(2, B.getB_name());
			pstmt.setString(3, B.getB_addr());
			pstmt.setString(4, B.getB_tel());
			pstmt.setString(5, B.getB_rep());
			
			int n = pstmt.executeUpdate();
			
			if(n==0)
				System.out.println("입력실패");
			else
				System.out.println("입력성공");
			viewBuy();				
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("입력 값을 다시 확인");
		}
	}
	
	public void deleteBuy() {
		String sql="delete from buy_ent where b_code = ?";
		System.out.println("삭제할 업체코드를 입력");
		String tmp = sc.nextLine();
		sc.nextLine();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tmp);
			pstmt.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editBuy() {
		
		viewBuy();
		BuyBean eb= new BuyBean();
		eb=searchBuy();
		String sql="";
		
		System.out.println("수정하려는 것을 고르시오(b_code:1 "+
		"업체명 b_name:2 " + 
		"주소 b_addr:3 " + 
		"연락처 b_tel:4" + 
		"대표자명 b_rep:5");
		
		int choice=sc.nextInt();
		sc.nextLine();
		System.out.println("수정하려는 값을 입력하시오");
		String editStr=sc.nextLine();
		switch(choice){
		case 1:
			sql="update set b_code=? where b_code=?";
			break;
		case 2:
			sql="update set b_name=? where b_code=?";
			break;
		case 3:
			sql="update set b_addr=? where b_code=?";
			break;
		case 4:
			sql="update set b_tel=? where b_code=?";
			break;
		case 5:
			sql="update set b_rep=? where b_code=?";
			break;
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, editStr);
			pstmt.setString(2, eb.getB_code());
			int n = pstmt.executeUpdate();
			System.out.println(n+"건업뎃");
		}
		catch(SQLException e){
			e.printStackTrace();	
		}
	}
	
	public BuyBean searchBuy() {
		String sql="select * from buy_ent where b_code = ?";
		BuyBean bb = new BuyBean();
		System.out.println("b_code를 입력하세요");
		String temp = sc.nextLine();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, temp);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bb = new BuyBean();
				bb.setB_code(rs.getString("b_code"));
				bb.setB_name(rs.getString("b_name"));
				bb.setB_addr(rs.getString("b_addr"));
				bb.setB_tel(rs.getString("b_tel"));
				bb.setB_rep(rs.getString("b_rep"));
				//출력구문만들기
				System.out.println(bb);
			} else {
				System.out.println("입력 값을 확인하세요");
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return bb;
	}

	public ResultSet viewBuy() {
		String sql = "select * from buy_ent";
		BuyBean vb = new BuyBean();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				do {
					vb.setB_code(rs.getString("b_code"));
					vb.setB_name(rs.getString("b_name"));
					vb.setB_addr(rs.getString("b_addr"));
					vb.setB_tel(rs.getString("b_tel"));
					vb.setB_rep(rs.getString("b_rep"));
					System.out.println(vb);
				} while (rs.next());
			} else
				System.out.println("조회할 데이터가 없습니다.");
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	public void close() throws SQLException {
		pstmt.close();
		conn.close();
	}
}