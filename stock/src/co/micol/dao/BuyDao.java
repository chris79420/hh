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
		String sql ="Insert into buy_ent(b_code,b_name,b_addr,b_tel,b_rep)"
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
			////작성글보여주기루틴
				
		} catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("입력 값을 다시 확인");
		}
	}
	
	
	public void deleteBuy() {
		String sql="delete from buy_ent where b_code = ?";
		System.out.println("삭제할 업체코드를 입력");
		String tmp = sc.nextLine();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tmp);
			int res=pstmt.executeUpdate();
			if(res>0)
				System.out.println("삭제하였습니다.");
			else
				System.out.println("삭제할데이터가 없습니다. 입력한 코드를 확인해주세요");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void editBuy(BuyBean eb) {
		
		
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
	
	public BuyBean searchBuy() {
		String sql="select * from buy_ent where b_code = ? or b_name=?";
		BuyBean bb = new BuyBean();
		System.out.println("찾기를 원하는 b_code나 b_name을 입력하세요");
		String temp = sc.nextLine();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, temp);
			pstmt.setString(2, temp);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				bb = new BuyBean();
				bb.setB_code(rs.getString("b_code"));
				bb.setB_name(rs.getString("b_name"));
				bb.setB_addr(rs.getString("b_addr"));
				bb.setB_tel(rs.getString("b_tel"));
				bb.setB_rep(rs.getString("b_rep"));
				//출력구문만들기
				System.out.println(bb);
			}
		}
		catch(SQLException e){
			e.printStackTrace();

		}
		return bb;
		
	}
	public void BuySearch() {
		
	}
	public ResultSet viewBuy() {
		String sql = "select * from buy_ent";
		BuyBean vb = new BuyBean();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				do {

					vb = new BuyBean();
					vb.setB_code(rs.getString("b_code"));
					vb.setB_name(rs.getString("b_name"));
					vb.setB_addr(rs.getString("b_addr"));
					vb.setB_tel(rs.getString("b_tel"));
					vb.setB_rep(rs.getString("b_rep"));
					// 출력구문만들기
					System.out.println(vb);
				} while (rs.next());
			} else
				System.out.println("조회할 데이터가 없습니다.");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	public void close() throws SQLException {
		pstmt.close();
		conn.close();
	}
}
