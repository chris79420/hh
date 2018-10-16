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
		sql = "insert into products " + "values(?,imount_seq.nextval,?,?,?,?,?)";
		System.out.println("분류코드를 입력하세요.");
		ipb.setCat_code(sc.nextLine());
		System.out.println("상품명을 입력하세요");
		ipb.setPro_name(sc.nextLine());
		System.out.println("규격을 입력하세요");
		ipb.setPro_std(sc.nextLine());
		System.out.println("단위를 입력하세요");
		ipb.setPro_unit(sc.nextLine());
		System.out.println("단가를 입력하세요");
		ipb.setPro_price(sc.nextInt());
		System.out.println("창고코드를 입력하세요");
		ipb.setSt_code(sc.nextInt());

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ipb.getCat_code());
			psmt.setString(2, ipb.getPro_name());
			psmt.setString(3, ipb.getPro_std());
			psmt.setString(4, ipb.getPro_unit());
			psmt.setInt(5, ipb.getPro_price());
			psmt.setInt(6, ipb.getSt_code());

			int r = psmt.executeUpdate();
			if (r == 0)
				System.out.println("등록에 실패했습니다.");
			else
				System.out.println(r + "건을 성공적으로 등록했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int DeletePro() throws SQLException { // 상품 삭제
		ViewPro();
		sql = "delete from products where pro_code =?";
		System.out.println("=============================");
		System.out.println("삭제할 제품의 상품코드를 입력하세요.");
		String dpb = sc.nextLine();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dpb);
			int r = psmt.executeUpdate();
			if (r == 0)
				System.out.println("삭제에 실패했습니다.");
			else
				System.out.println(r + "건을 성공적으로 삭제했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public void EditPro(ProductsBean epb) throws SQLException { // 상품 수정
		ViewPro();
		String sql = "";
		int editInt = 0;
		String editStr = "";
		String inp;
		System.out.println("수정할 대상을 고르세요. 1:분류코드 2:상품코드 3:이름 4: 단위 5:규격 6: 단가 7:창고코드");
		int choice = sc.nextInt();
		sc.nextLine();
		if (choice == 6 || choice == 7) {
			System.out.println("수정할 값을 입력하세요.");
			editInt = sc.nextInt();
			sc.nextLine();
			System.out.println("수정할 상품의 코드를 입력하세요.");
			inp = sc.nextLine();
		} else {
			System.out.println("수정할 값을 입력하세요.");
			editStr = sc.nextLine();
			System.out.println("수정할 상품의 코드를 입력하세요.");			
			inp = sc.nextLine();
		}
		switch (choice) {
		case 1:
			sql = "update products set cat_code = ? where pro_code =?";
			break;
		case 2:
			sql = "update products set pro_code = ? where pro_code = "+inp;
			break;
		case 3:
			sql = "update products set pro_name = ? where pro_code = "+inp;
			break;
		case 4:
			sql = "update products set pro_std = ? where pro_code = "+inp;
			break;
		case 5:
			sql = "update products set pro_unit = ? where pro_code = "+inp;
			break;
		case 6:
			sql = "update products set pro_price = ? where pro_code = "+inp;
			break;
		case 7:
			sql = "update products set st_code = ? where pro_code = "+inp;
			break;
		}
		try {
			psmt = conn.prepareStatement(sql);
			if (choice == 6 || choice == 7) {
				psmt.setInt(1, editInt);
			} else {
				psmt.setString(1, editStr); 
				psmt.setString(2, inp);
			}
			System.out.println(editStr + inp +"/"+ sql);
			
			int r = psmt.executeUpdate();
			if (r == 0)
				System.out.println("수정에 실패했습니다.");
			else
				System.out.println(r + "건을 성공적으로 수정했습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void SearchPro() { // 상품 세부조회
		ProductsBean spb = new ProductsBean();
		sql = "select * from products where pro_code =?";
		System.out.println("조회할 제품의 상품코드를 입력하세요");
		spb.setPro_code(sc.nextLine());

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, spb.getPro_code());
			rs = psmt.executeQuery();

			if (rs.next()) {
				spb.setCat_code(rs.getString("cat_code"));
				spb.setPro_code(rs.getString("pro_code"));
				spb.setPro_name(rs.getString("pro_name"));
				spb.setPro_std(rs.getString("pro_std"));
				spb.setPro_unit(rs.getString("pro_unit"));
				spb.setPro_price(rs.getInt("pro_price"));
				spb.setSt_code(rs.getInt("st_code"));
			} else
				System.out.println("조회할 상품이 없습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ViewPro() throws SQLException { // 상품목록보기
		ProductsBean vpb = new ProductsBean();
		sql = "select * from products";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				do {
					vpb.setCat_code(rs.getString("cat_code"));
					vpb.setPro_code(rs.getString("pro_code"));
					vpb.setPro_name(rs.getString("pro_name"));
					vpb.setPro_std(rs.getString("pro_std"));
					vpb.setPro_unit(rs.getString("pro_unit"));
					vpb.setPro_price(rs.getInt("pro_price"));
					vpb.setSt_code(rs.getInt("st_code"));
					System.out.println(vpb);
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
