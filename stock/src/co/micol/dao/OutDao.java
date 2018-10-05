package co.micol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import co.micol.bean.InBean;
import co.micol.bean.OutBean;

public class OutDao {

	static Connection conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	Scanner sc = new Scanner(System.in);

	public OutDao() {
		try {
			String user = "hr";
			String pw = "hr";
			String url = "jdbc:oracle:thin:@192.168.0.71:1521:xe"; // xe : SID 값

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}
	public int sell_Order_Num() {
		String seqNextVal = "select out_seq.nextval from dual";
		int out_code = 0;
		try {
			pstmt = conn.prepareStatement(seqNextVal);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				out_code = rs.getInt("in_seq.nextval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out_code;
	}
	
	public void insertOut() {
		
		String sql; 
		/*
		 * to_char(sysdate,'yymm')||lpad(?,4,'0')) s_code number 
		 * ,purchase_order VARCHAR2 
		 * ,pro_code VARCHAR2 ,amount NUMBER)
		 */
		
		int out_code = sell_Order_Num();
		int tempAmount;
		int s_code;
		String pro_code;

		while (true) {
			System.out.println("판매업체 코드(s_code)를 입력하시오(입력을 그만하려면 0을 입력하시오)");
			s_code = sc.nextInt();
			sc.nextLine();
			
			if(s_code==0)
				break;
			
			System.out.println("출고 수량을 입력하세요.");
			tempAmount = sc.nextInt();
			sc.nextLine();
			System.out.println("상품코드를 입력하세요.");
			pro_code = sc.nextLine();
			sql= "exec create_sell_info(?,to_char(sysdate,'yyyymmdd')||lpad(?,4,'0'),?,?)";

			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, s_code);
				pstmt.setInt(2, out_code);
				pstmt.setString(3, pro_code);
				pstmt.setInt(4, tempAmount);
				int n = pstmt.executeUpdate();

				if (n == 0)
					System.out.println("입력실패");
				else
					System.out.println("입력성공");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void deleteIn() {
		String sql = "delete from out_t where out_code = ?";

		viewOut();
		System.out.println("삭제할 판매번호를 입력");
		int temp = sc.nextInt();
		sc.nextLine();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, temp);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editIn() {

		viewOut();
		OutBean eb = new OutBean();
		eb=searchOut();
		
		System.out.println("수정을 원하는 column의 번호를 입력하시오"+
		"Out_code(1) Out_line(2) Out_amount(3) Out_price(4) Out_date(5)"+
				"Out_b_code(6) Out_pro_code(7)");
		int choice = sc.nextInt();
		sc.nextLine();
		String sql=new String();
		System.out.println("수정하려는 값을 입력하시오");
		String editStr=sc.nextLine();
		switch(choice) {
		case 1:
			sql="update Out_t set in_code=?"+ "where Out_code=?";
			break;
		case 2:
			sql="update Out_t set in_line=?"+ "where Out_code=?";
			break;
		case 3:
			sql="update Out_t set in_amount=?"+ "where Out_code=?";
			break;
		case 4:
			sql="update Out_t set in_price=?"+ "where Out_code=?";
			break;
		case 5:
			sql="update Out_t set in_date=?"+ "where Out_code=?";
			break;
		case 6:
			sql="update Out_t set in_b_code=?"+ "where Out_code=?";
			break;
		case 7:
			sql="update Out_t set in_pro_code=?"+ "where Out_code=?";
			break;
		}
					 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, editStr); 
			pstmt.setString(2,eb.getOut_code());
			
			int n = pstmt.executeUpdate();
			System.out.println(n + "건업뎃");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public OutBean searchOut() {
		String sql = "select * from out_t where out_code = ?";
		OutBean vb = new OutBean();
		int temp = sc.nextInt();
		sc.nextLine();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, temp);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {

					vb = new OutBean();
					vb.setOut_code(rs.getString("out_code"));
					vb.setOut_line(rs.getInt("out_line"));
					vb.setOut_amount(rs.getInt("out_amount"));
					vb.setOut_price(rs.getInt("out_price"));
					vb.setOut_sum(rs.getInt("out_sum"));
					vb.setOut_date(rs.getString("out_date"));
					vb.setOut_s_code(rs.getInt("out_s_code"));
					vb.setOut_pro_code(rs.getString("out_pro_code"));
					// 출력구문만들기
					System.out.println(vb);
				} while (rs.next());
			} else
				System.out.println("조회할 데이터가 없습니다.");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return vb;
	}

	public void viewOut() {
		String sql = "select * from out_t";
		OutBean vb = new OutBean();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {

					vb = new OutBean();
					vb.setOut_code(rs.getString("out_code"));
					vb.setOut_line(rs.getInt("out_line"));
					vb.setOut_amount(rs.getInt("out_amount"));
					vb.setOut_price(rs.getInt("out_price"));
					vb.setOut_sum(rs.getInt("out_sum"));
					vb.setOut_date(rs.getString("out_date"));
					// 출력구문만들기
					System.out.println(vb);
				} while (rs.next());
			} else
				System.out.println("조회할 데이터가 없습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return rs;
	}

	public void close() throws SQLException {
		pstmt.close();
		conn.close();
	}
}
