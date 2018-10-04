package co.micol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import co.micol.bean.StorageBean;

public class StorageDao {
	Scanner sc = new Scanner(System.in);
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private int r;
	private String sql;

	public StorageDao() {
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

	public int InsertStorage(StorageBean istb) throws SQLException { // 창고 등록
		sql = "insert into storage " + "values(?,?,?)";
		System.out.println("창고 코드를 입력하세요.");
		istb.setSt_code(sc.nextInt());
		System.out.println("창고 이름을 입력하세요");
		istb.setSt_name(sc.nextLine());
		System.out.println("창고 설명을 입력하세요");
		istb.setSt_note(sc.nextLine());

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, istb.getSt_code());
			psmt.setString(2, istb.getSt_name());
			psmt.setString(3, istb.getSt_note());

			r = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ViewStorage();
		return r;
	}

	public void DeleteStorage() throws SQLException { // 창고 삭제
		sc = new Scanner(System.in);
		sql = "delete from storage where st_code = ?";
		ViewStorage();
		System.out.println("========================");
		System.out.println("삭제할 창고의 코드를 입력하세요.");
		String d = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, d);
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ViewStorage();
	}

	public void EditStorage(StorageBean stb) throws SQLException { // 창고 수정
		sql = "update storage " + "set st_name = ?, st_note = ? " + "where st_code = ?";
		ViewStorage();
		System.out.println("========================");
		System.out.println("수정할 창고 코드를 입력하세요.");
		
		stb.setSt_code(sc.nextInt());

		try {
				psmt.setString(1, stb.getSt_name());
				psmt.setString(2, stb.getSt_note());
				psmt.setInt(3, stb.getSt_code());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("===================");
		System.out.println("수정할 대상을 선택하세요. 1 : 창고이름 2 : 창고설명");
		int n = Integer.parseInt(sc.nextLine());

		switch (n) {
		case 1:
			System.out.println("수정할 창고이름을 입력하세요.");
			stb.setSt_name(sc.nextLine());
			break;
		case 2:
			System.out.println("수정할 창고 설명을 입력하세요.");
			stb.setSt_note(sc.nextLine());
			break;
		}
		EditStorage(stb); // 수정 실행
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, stb.getSt_name());
			psmt.setString(2, stb.getSt_note());
			psmt.setInt(3, stb.getSt_code());
			int r = psmt.executeUpdate();

			if (r == 0)
				System.out.println("수정에 실패했습니다'^'");
			else
				System.out.println("성공적으로 수정되었습니다:-)");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void SearchStorage() { // 창고 조회
		sc = new Scanner(System.in);
		sql = "select * from storage where st_code = ?";
		System.out.println("조회할 창고의 코드를 입력하세요");
		String s = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			rs = psmt.executeQuery();
			if (rs.next()) {
				StorageBean sstb = new StorageBean();
				sstb.setSt_code(rs.getInt("st_code"));
				sstb.setSt_name(rs.getString("st_name"));
				sstb.setSt_note(rs.getString("st_note"));
			} else
				System.out.println("조회할 창고가 없습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ViewStorage() throws SQLException { // 창고 목록
		sql = "select * from storage";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				do {
					StorageBean vstb = new StorageBean();
					vstb.setSt_code(rs.getInt("st_code"));
					vstb.setSt_name(rs.getString("st_name"));
					vstb.setSt_note(rs.getString("st_note"));

				} while (rs.next());
			} else
				System.out.println("창고가 존재하지 않습니다.");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();
	}

	public void close() throws SQLException {
		psmt.close();
		conn.close();
	}
}
