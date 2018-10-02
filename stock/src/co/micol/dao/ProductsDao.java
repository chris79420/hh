package co.micol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import co.micol.bean.ProductsBean;
import java.util.Scanner;

public class ProductsDao {
	Scanner sc = new Scanner(System.in);
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private int r;
	private String sql;

	public ProductsDao() {
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

	public void InsertPro(ProductsBean ipb) throws SQLException { // 상품 등록
		sql = "insert into products " + "values(?,concat('?','imount_seq.nextval'),?,?,?)";
		System.out.println("분류코드를 입력하세요.");
		ipb.setCat_code(sc.nextLine());
		System.out.println("상품명을 입력하세요");
		ipb.setPro_name(sc.nextLine());
		System.out.println("규격을 입력하세요");
		ipb.setPro_std(sc.nextLine());
		System.out.println("단위를 입력하세요");
		ipb.setPro_unit(sc.nextLine());

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ipb.getCat_code());
			psmt.setString(2, ipb.getCat_code());
			psmt.setString(3, ipb.getPro_name());
			psmt.setString(4, ipb.getPro_std());
			psmt.setString(5, ipb.getPro_unit());

			r = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ViewPro();
	}

	public int DeletePro() throws SQLException { // 상품 삭제
		sc = new Scanner(System.in);
		sql = "delete from products where pro_code = ?";
		ViewPro();
		System.out.println("========================");
		System.out.println("삭제할 제품의 상품코드를 입력하세요.");
		String d = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, d);
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ViewPro();
		return r;
	}

	public void EditPro(ProductsBean epb) throws SQLException { // 상품 수정
		sql = "update products " + "set cat_code = ?, pro_name = ?, pro_std = ?, pro_unit = ? " + "where pro_code = ?";

		ViewPro();
		System.out.println("======================");
		System.out.println("수정할 상품 코드를 입력하세요.");
		
		epb.setPro_code(sc.nextLine());

		try {

			//while (rs.next()) {
				psmt.setString(1, epb.getCat_code());
				psmt.setString(2, epb.getPro_name());
				psmt.setString(3, epb.getPro_std());
				psmt.setString(4, epb.getPro_unit());
				psmt.setString(5, epb.getPro_code());
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("===================");
		System.out.println("수정할 대상을 선택하세요. 1:분류코드 2:상품이름 3:상품규격 4:상품단위");
		int n = Integer.parseInt(sc.nextLine());

		switch (n) {
		case 1:
			System.out.println("수정할 분류코드를 입력하세요.");
			epb.setCat_code(sc.nextLine());
			break;
		case 2:
			System.out.println("수정할 상품 이름를 입력하세요.");
			epb.setPro_name(sc.nextLine());
			break;
		case 3:
			System.out.println("수정할 상품 규격을 입력하세요.");
			epb.setPro_std(sc.nextLine());
			break;
		case 4:
			System.out.println("수정할 상품 단위를 입력하세요.");
			epb.setPro_unit(sc.nextLine());
			break;
		}
		EditPro(epb); // 수정 실행
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, epb.getCat_code());
			psmt.setString(2, epb.getPro_name());
			psmt.setString(3, epb.getPro_std());
			psmt.setString(4, epb.getPro_unit());
			psmt.setString(5, epb.getPro_code());
			int r = psmt.executeUpdate();

			if (r == 0)
				System.out.println("수정에 실패했습니다'^'");
			else
				System.out.println("성공적으로 수정되었습니다:-)");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void SearchPro() { // 상품 세부조회
		sc = new Scanner(System.in);
		sql = "select * from products where pro_code = ?";
		System.out.println("조회할 제품의 상품코드를 입력하세요");
		String s = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			rs = psmt.executeQuery();

			if (rs.next()) {
				ProductsBean spb = new ProductsBean();
				spb.setCat_code(rs.getString("cat_code"));
				spb.setPro_code(rs.getString("pro_code"));
				spb.setPro_name(rs.getString("pro_name"));
				spb.setPro_std(rs.getString("pro_std"));
				spb.setPro_unit(rs.getString("pro_unit"));
			} else
				System.out.println("조회할 상품이 없습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ViewPro() throws SQLException { // 상품목록보기
		sql = "select * from products";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				do {
					ProductsBean vpb = new ProductsBean();
					vpb.setCat_code(rs.getString("cat_code"));
					vpb.setPro_code(rs.getString("pro_code"));
					vpb.setPro_name(rs.getString("pro_name"));
					vpb.setPro_std(rs.getString("pro_std"));
					vpb.setPro_unit(rs.getString("pro_unit"));
				} while (rs.next());
			} else
				System.out.println("제품이 존재하지 않습니다.");
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
