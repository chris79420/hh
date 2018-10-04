package co.micol.dao;

// 상품별 입출고 조회
// 기간별 입출고 조회 어떻게 할지?

import java.sql.Connection;
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
	
//	public void SearchIO() { // 입출고 세부조회
//		sc = new Scanner(System.in);
//		sql = "select * from inout_t where io_no= ?";
//		System.out.println("조회할 입출고 내역의 번호를 입력하세요");
//		String s = sc.nextLine();
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, s);
//			rs = psmt.executeQuery();
//
//			if (rs.next()) {
//				IOBean isb = new IOBean();
//				isb.setIO_No(rs.getInt("io_no"));
//				isb.setPro_code(rs.getString("pro_code"));
//				isb.setIO_Date(rs.getString("io_date"));
//				isb.setIO_Amount(rs.getInt("io_amount"));
//				isb.setSt_Code(rs.getInt("st_code"));
//				
//			} else
//				System.out.println("조회할 입출고 내역이 없습니다.");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

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
