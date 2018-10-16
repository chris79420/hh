package co.micol.dao;

//수정, 조회 해결하기
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
		sc.nextLine(); // 엔터키 날려주는 것!
		System.out.println("창고 이름을 입력하세요.");
		istb.setSt_name(sc.nextLine());
		System.out.println("창고 설명을 입력하세요.");
		istb.setSt_note(sc.nextLine());

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, istb.getSt_code());
			psmt.setString(2, istb.getSt_name());
			psmt.setString(3, istb.getSt_note());

			int r = psmt.executeUpdate();
			if (r == 0)
				System.out.println("등록에 실패했습니다.");
			else
				System.out.println(r + "건을 성공적으로 등록했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ViewStorage();
		return r;
	}

	public void DeleteStorage() throws SQLException { // 창고 삭제
		ViewStorage();
		String sql = "delete from storage where st_code = ?";
		System.out.println("==========================");
		System.out.println("삭제할 창고의 코드를 입력하세요.");
		String ds = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ds);
			int r = psmt.executeUpdate();
			if (r == 0)
				System.out.println("삭제에 실패했습니다.");
			else
				System.out.println(r + "건을 성공적으로 삭제했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void EditStorage(StorageBean stb) throws SQLException { // 창고 수정
		ViewStorage();
		String sql = "";

		System.out.println("========================");
		System.out.println("수정할 대상을 고르세요. 1:창고코드 2:창고이름 3:창고설명");
		int choice = sc.nextInt();
		String editStr = sc.nextLine();
		switch (choice) {
		case 1:
			sql = "update storage set st_code = ? where st_code = ?";
			break;
		case 2:
			sql = "update storage set st_name = ? where st_code = ?";
			break;
		case 3:
			sql = "update storage set st_note = ? where st_code = ?";
			break;
		}

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, editStr);
			psmt.setString(2, stb.getSt_name());
			int r = psmt.executeUpdate();
			if (r == 0)
				System.out.println("수정에 실패했습니다.");
			else
				System.out.println(r + "건을 성공적으로 수정했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void SearchStorage() { // 창고 조회
		StorageBean sstb = new StorageBean();
		sql = "select * from storage where st_code =?";
		System.out.println("조회할 창고의 코드를 입력하세요.");
		String s = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			rs = psmt.executeQuery();
			if (rs.next()) {
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
		StorageBean vstb = new StorageBean();
		sql = "select * from storage";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

	if (rs.next()) {
				do {
					vstb.setSt_code(rs.getInt("st_code"));
					vstb.setSt_name(rs.getString("st_name"));
					vstb.setSt_note(rs.getString("st_note"));
					System.out.println(vstb);
				} while (rs.next());
			} else
				System.out.println("창고가 존재하지 않습니다.");
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
