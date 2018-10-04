package co.micol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import co.micol.bean.InBean;
import co.micol.bean.OutBean;

public class InDao {

	static Connection conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	Scanner sc = new Scanner(System.in);

	public InDao() {
		try {
			String user = "hr";
			String pw = "hr";
			String url = "jdbc:oracle:thin:@192.168.0.71:1521:xe"; // xe : SID 값

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");
			viewIn();

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}

	public int purchase_Order_Num() {
		String seqNextVal = "select in_seq.nextval from dual";
		int in_code = 0;
		try {
			pstmt = conn.prepareStatement(seqNextVal);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				in_code = rs.getInt("in_seq.nextval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return in_code;
	}

	public void insertIn() {

		String sql; 
		/*
		 * to_char(sysdate,'yymm')||lpad(?,4,'0')) b_code number ,purchase_order
		 * VARCHAR2 ,pro_code VARCHAR2 ,amount NUMBER)
		 */

		// 구매번호는 고정
		int in_code = purchase_Order_Num();
		int tempAmount;
		int b_code;
		String pro_code;

		while (true) {
			System.out.println("구매업체 코드(b_code)를 입력하시오(입력을 그만하려면 0을 입력하시오)");
			b_code = sc.nextInt();
			sc.nextLine();
			
			if(b_code==0)
				break;
			
			System.out.println("입고 수량을 입력하세요.");
			tempAmount = sc.nextInt();
			sc.nextLine();
			System.out.println("상품코드를 입력하세요.");
			pro_code = sc.nextLine();
			
			sql= "exec create_purchase_info(?,to_char(sysdate,'yyyymmdd')||lpad(?,4,'0'),?,?)";

			try {				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, b_code);
				pstmt.setInt(2, in_code);
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
		String sql = "delete from In_t where in_code = ?";
		System.out.println("삭제할 구매번호를 입력");
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

		viewIn();
		InBean eb = new InBean();
		eb=searchIn();
		
		String sql;/*
					 * ="update In_t set ~~~~"+ "where b_id=?";
					 */
		try {
			pstmt = conn.prepareStatement(sql);
			/*
			 * pstmt.setString(1, eb.getB_code()); pstmt.setString(2,eb.getContent());
			 * pstmt.setInt(3, eb.getBid());
			 */
			int n = pstmt.executeUpdate();
			System.out.println(n + "건업뎃");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public InBean searchIn() {
		String sql = "select * from in_t where in_code = ? and in_line=?";
		InBean bb = new InBean();
		String tempcode = sc.nextLine();
		int templine = sc.nextInt();
		sc.nextLine();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tempcode);
			pstmt.setInt(2, templine);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				bb = new InBean();
				bb.setIn_code(rs.getString("in_code"));
				bb.setIn_line(rs.getInt("in_line"));
				bb.setIn_amount(rs.getInt("in_amount"));
				bb.setIn_price(rs.getInt("in_price"));
				bb.setIn_sum(rs.getInt("in_sum"));
				bb.setIn_date(rs.getString("in_date"));
				bb.setIn_b_code(rs.getInt("in_b_code"));
				bb.setIn_pro_code(rs.getString("in_pro_code"));
				// 출력구문만들기
				System.out.println(bb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bb;
	}

	public void InSearch() {

	}

	public ResultSet viewIn() {
		String sql = "select * from in_t";
		InBean vb = new InBean();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {

					vb = new InBean();
					vb.setIn_code(rs.getString("in_code"));
					vb.setIn_line(rs.getInt("in_line"));
					vb.setIn_amount(rs.getInt("in_amount"));
					vb.setIn_price(rs.getInt("in_price"));
					vb.setIn_sum(rs.getInt("in_sum"));
					vb.setIn_date(rs.getString("in_date"));
					vb.setIn_b_code(rs.getInt("in_b_code"));
					vb.setIn_pro_code(rs.getString("in_pro_code"));
					// 출력구문만들기
					System.out.println(vb);
				} while (rs.next());
			} else
				System.out.println("조회할 데이터가 없습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void close() throws SQLException {
		pstmt.close();
		conn.close();
	}
}