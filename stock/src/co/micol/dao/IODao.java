package co.micol.dao;

// 상품별 입출고 조회
// 기간별 입출고 조회 어떻게 할지?

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import co.micol.bean.IOBean;

public class IODao {
	Scanner sc = new Scanner(System.in);
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private String sql;

	public IODao() {
		try {
			String user = "hr";
			String pw = "hr";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void InsertIO() {
		
	}
	
	public void EditIO() {
		
	}
	
	public void DeleteIO() {
		
	}
	
	public void SearchIOByCode() { // 상품별 입출고 세부조회
		sc = new Scanner(System.in);
		sql = "select * from inout_t where pro_code = ? order by pro_code;";
		System.out.println("조회할 입출고 내역의 번호를 입력하세요");
		String s = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			rs = psmt.executeQuery();

			if (rs.next()) {
				IOBean isbc = new IOBean();
				isbc.setIO_No(rs.getInt("io_no"));
				isbc.setPro_code(rs.getString("pro_code"));
				isbc.setIO_Date(rs.getString("io_date"));
				isbc.setIO_Amount(rs.getInt("io_amount"));
				isbc.setSt_Code(rs.getInt("st_code"));
				
			} else
				System.out.println("조회할 상품별 입출고 내역이 없습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void SearchIOByDate() { // 기간별 입출고 세부조회
		sc = new Scanner(System.in);
		sql = "select * from inout_t where io_date = ? order by io_date;";
		System.out.println("조회할 입출고 내역 날짜를 입력하세요. (MM-DD-YY)");
		String s = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			rs = psmt.executeQuery();

			if (rs.next()) {
				IOBean isbd = new IOBean();
				isbd.setIO_No(rs.getInt("io_no"));
				isbd.setPro_code(rs.getString("pro_code"));
				isbd.setIO_Date(rs.getString("io_date"));
				isbd.setIO_Amount(rs.getInt("io_amount"));
				isbd.setSt_Code(rs.getInt("st_code"));

			} else
				System.out.println("조회할 기간별 입출고 내역이 없습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ViewIO() throws SQLException { // 입출고 목록보기
		sql = "select * from inout_t";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				do {
					IOBean ivb = new IOBean();
					ivb.setIO_No(rs.getInt("io_no"));
					ivb.setPro_code(rs.getString("pro_code"));
					ivb.setIO_Date(rs.getString("io_date"));
					ivb.setIO_Amount(rs.getInt("io_amount"));
					ivb.setSt_Code(rs.getInt("st_code"));

				} while (rs.next());
			} else
				System.out.println("입출고 내역이 존재하지 않습니다.");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() throws SQLException {
		psmt.close();
		conn.close();
	}
}
