package co.micol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import co.micol.bean.BuyBean;
import co.micol.bean.SellBean;

public class SellDao {
	
	static Connection conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	Scanner sc = new Scanner(System.in);
	
	public SellDao() {
		try {
			String user = "hr";
			String pw = "hr";
			String url = "jdbc:oracle:thin:@192.168.0.71:1521:xe"; // xe : SID 값

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");
			viewSell();

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}
	
	
	public void insertSell() {
		String sql ="Insert into sell_ent "
				+ "values(?,?,?,?,?)";
		SellBean B = new SellBean();
		System.out.println("판매업체 코드를 입력하세요.");
		B.setS_code(sc.nextLine());
		System.out.println("판매업체 이름을 입력하세요.");
		B.setS_name(sc.nextLine());
		System.out.println("판매업체 주소를 입력하세요.");
		B.setS_addr(sc.nextLine());
		System.out.println("판매업체 전화번호를 입력하세요.");
		B.setS_tel(sc.nextLine());
		System.out.println("판매업체 대표자명을 입력하세요.");
		B.setS_rep(sc.nextLine());		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, B.getS_code());
			pstmt.setString(2, B.getS_name());
			pstmt.setString(3, B.getS_addr());
			pstmt.setString(4, B.getS_tel());
			pstmt.setString(5, B.getS_rep());
			
			int n = pstmt.executeUpdate();
			if(n==0)
				System.out.println("입력에 실패했습니다.");
			else
				System.out.println(n+"건을 성공적으로 입력했습니다.");
				viewSell();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteSell() {
		viewSell();
		String sql="delete from sell_ent where s_code = ?";
		System.out.println("============================");
		System.out.println("삭제할 판매업체 코드를 입력하세요.");
		String tmp = sc.nextLine();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tmp);
			pstmt.executeUpdate();
			int r = pstmt.executeUpdate();
			if (r == 0)
				System.out.println("삭제에 실패했습니다.");
			else
				System.out.println(r + "건을 성공적으로 삭제했습니다.");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		sc.close();
	}
	
	public void editSell() {	
		viewSell();
		SellBean eb= new SellBean();
		eb=searchSell();
		String sql="";
		
		System.out.println("수정하려는 것을 고르시오(s_code:1 "+
		"업체명 s_name:2 " + 
		"주소 s_addr:3 " + 
		"연락처 s_tel:4" + 
		"대표자명 s_rep:5");
		
		int choice=sc.nextInt();
		sc.nextLine();
		System.out.println("수정하려는 값을 입력하시오");
		String editStr=sc.nextLine();
		switch(choice){
		case 1:
			sql="update set s_code=? where s_code=?";
			break;
		case 2:
			sql="update set s_name=? where s_code=?";
			break;
		case 3:
			sql="update set s_addr=? where s_code=?";
			break;
		case 4:
			sql="update set s_tel=? where s_code=?";
			break;
		case 5:
			sql="update set s_rep=? where s_code=?";
			break;
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, editStr);
			pstmt.setString(2, eb.getS_code());
			int n = pstmt.executeUpdate();
			System.out.println(n+"건 업뎃");
		} catch(SQLException e) {
			e.printStackTrace();	
		}
	}
	
	public SellBean searchSell() {
		String sql="select * from sell_ent where s_code = ?";
		SellBean sb = new SellBean();
		System.out.println("s_code를 입력하시오");
		String temp=sc.nextLine();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, temp);
			
			rs=pstmt.executeQuery();
			if (rs.next()) {
									sb = new SellBean();
					sb.setS_code(rs.getString("s_code"));
					sb.setS_name(rs.getString("s_name"));
					sb.setS_addr(rs.getString("s_addr"));
					sb.setS_tel(rs.getString("s_tel"));
					sb.setS_rep(rs.getString("s_rep"));
					// 출력구문만들기
					System.out.println(sb);
				} 
			else
				System.out.println("조회할 데이터가 없습니다.");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return sb;
		
	}
	
	public void viewSell() {
		SellBean sb = new SellBean();
		String sql = "select * from sell_ent";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				do {
					sb.setS_code(rs.getString("s_code"));
					sb.setS_name(rs.getString("s_name"));
					sb.setS_addr(rs.getString("s_addr"));
					sb.setS_tel(rs.getString("s_tel"));
					sb.setS_rep(rs.getString("s_rep"));
					System.out.println(sb);
				} while (rs.next());
			} else
				System.out.println("조회할 데이터가 없습니다.");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void close() throws SQLException {
		pstmt.close();
		conn.close();
	}	
}
