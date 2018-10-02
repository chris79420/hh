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
		String sql ="Insert into sell_ent(s_code,s_name,s_addr,s_tel,s_rep)"
				+ "values(?,?,?,?,?)";
		SellBean B = new SellBean();
		System.out.println("s_code");
		B.setS_code(sc.nextLine());
		System.out.println("s_name");
		B.setS_name(sc.nextLine());
		System.out.println("s_addr");
		B.setS_addr(sc.nextLine());
		System.out.println("s_tel");
		B.setS_tel(sc.nextLine());
		System.out.println("s_rep");
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
				System.out.println("입력실패");
			else
				System.out.println("입력성공");
			////작성글보여주기루틴
				
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void deleteSell() {
		String sql="delete from sell_ent where s_code = ?";

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
		sc.close();
	}
	
	
	public void editSell(SellBean eb) {
		
		
		String sql;/*="update buy_ent set ~~~~"+
					"where b_id=?";
		*/
		try {
			pstmt = conn.prepareStatement(sql);
			/*pstmt.setString(1, eb.getB_code());
			pstmt.setString(2,eb.getContent());
			pstmt.setInt(3, eb.getBid());
			*/
			int n = pstmt.executeUpdate();
			System.out.println(n+"건업뎃");
		}
		catch(SQLException e){
			e.printStackTrace();	
		}
	}
	
	public void searchSell(String temp) {
		String sql="select * from buy_ent where b_code = ? or b_name=?";
		SellBean sb = new SellBean();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, temp);
			pstmt.setString(2, temp);
			
			rs=pstmt.executeQuery();
			if (rs.next()) {
				do {

					sb = new SellBean();
					sb.setS_code(rs.getString("s_code"));
					sb.setS_name(rs.getString("s_name"));
					sb.setS_addr(rs.getString("s_addr"));
					sb.setS_tel(rs.getString("s_tel"));
					sb.setS_rep(rs.getString("s_rep"));
					// 출력구문만들기
					System.out.println(sb);
				} while (rs.next());
			} else
				System.out.println("조회할 데이터가 없습니다.");
		}
		catch(SQLException e){
			e.printStackTrace();

		}
		//return sb;
		
	}
	
	public void viewSell() {
		String sql = "select * from buy_ent";
		SellBean sb = new SellBean();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				do {

					sb = new SellBean();
					sb.setS_code(rs.getString("s_code"));
					sb.setS_name(rs.getString("s_name"));
					sb.setS_addr(rs.getString("s_addr"));
					sb.setS_tel(rs.getString("s_tel"));
					sb.setS_rep(rs.getString("s_rep"));
					// 출력구문만들기
					System.out.println(sb);
				} while (rs.next());
			} else
				System.out.println("조회할 데이터가 없습니다.");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		//return rs;
	}
	public void close() throws SQLException {
		pstmt.close();
		conn.close();
	}
}
