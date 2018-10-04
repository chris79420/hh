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

	public void insertout() {
		String sql = "Insert into out_t" + "values(seq.nextval,?,?,?,?,sysdate)";

		OutBean B = new OutBean();
		int tempAmount, tempPrice;
		System.out.println("Out_line를 입력하세요.");
		B.setOut_code(sc.nextInt());
		sc.nextLine();
		System.out.println("Out_amount를 입력하세요.");
		tempAmount = sc.nextInt();
		B.setOut_code(tempAmount);
		sc.nextLine();
		System.out.println("Out_pirce를 입력하세요.");
		tempPrice = sc.nextInt();
		B.setOut_code(tempPrice);
		sc.nextLine();
		B.setOut_sum((tempAmount * tempPrice));

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, B.getOut_code());
			pstmt.setInt(2, B.getOut_line());
			pstmt.setInt(3, B.getOut_amount());
			pstmt.setInt(4, B.getOut_sum());

			int n = pstmt.executeUpdate();

			if (n == 0)
				System.out.println("입력실패");
			else
				System.out.println("입력성공");
			//// 작성글보여주기루틴

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOut() {
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

	public void editOut(OutBean eb) {

		String sql;/*
					 * ="update out_t set ~~~~"+ "where b_id=?";
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

	public void searchOut() {
		String sql = "select * from out_t where out_code = ?";
		OutBean vb = new OutBean();
		int temp = sc.nextInt();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, temp);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {

					vb = new OutBean();
					vb.setOut_code(rs.getInt("out_code"));
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
					vb.setOut_code(rs.getInt("out_code"));
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
