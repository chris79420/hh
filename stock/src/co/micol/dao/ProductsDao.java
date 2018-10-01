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
	
	public void InsertPro(ProductsBean b) throws SQLException { //상품 등록
		sql = "insert into products "
				+ "values(?,concat('?','imount_seq.nextval'),?,?,?)";
		System.out.println("분류코드를 입력하세요.");
		b.setCat_code(sc.nextLine());
		System.out.println("상품명을 입력하세요");
		b.setPro_name(sc.nextLine());
		System.out.println("규격을 입력하세요");
		b.setPro_std(sc.nextLine());
		System.out.println("단위를 입력하세요");
		b.setPro_unit(sc.nextLine());	
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, b.getCat_code());  
			psmt.setString(2, b.getCat_code()); 
			psmt.setString(3, b.getPro_name());
			psmt.setString(4, b.getPro_std());
			psmt.setString(5, b.getPro_unit());
			
			r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ViewPro();
	}
	
	public int DeletePro() throws SQLException { //상품 삭제
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
	
	public void EditPro(ProductsBean b) { 	//상품 수정
		sql = "update products "
				+ "set cat_code = ?, pro_name = ?, pro_std = ?, pro_unit = ? "
				+ "where pro_code = ?";

		ViewPro();
		System.out.println("======================");
		System.out.println("수정할 상품 코드를 입력하세요.");
		rs = SearchPro(Integer.parseInt(sc.nextLine()));

		try {

			while (rs.next()) {
				psmt.setString(1, b.getCat_code());
				psmt.setString(2, b.getPro_name());
				psmt.setString(3, b.getPro_std());
				psmt.setString(4, b.getPro_unit());
				psmt.setString(5, b.getPro_code());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("===================");
		System.out.println("수정할 대상을 선택하세요.");
		int n = Integer.parseInt(sc.nextLine());
		EditSelect(n);
		EditPro(b);	//수정 실행
	}
	
	private void EditSelect(int n) {
		switch(n) {
		case 1 : //제목수정
			System.out.println("수정할 제목을 입력하세요.");
			bean.setTitle(sc.nextLine());
			break;
		case 2 : //내용수정
			System.out.println("수정할 내용을 입력하세요.");
			bean.setContent(sc.nextLine());
			break;
		}
	
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, b.getCat_code());
			psmt.setString(2, b.getPro_name());
			psmt.setString(3, b.getPro_std());
			psmt.setString(4, b.getPro_unit());
			psmt.setString(5, b.getPro_code());
			int n = psmt.executeUpdate();

			if (n == 0)
				System.out.println("수정에 실패했습니다'^'");
			else
				System.out.println("성공적으로 수정되었습니다:-)");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void SearchPro() { 	//상품 세부조회
		sc = new Scanner(System.in);
		sql = "select * from products where pro_code = ?";  	
		System.out.println("조회할 제품의 상품코드를 입력하세요");
		String s = sc.nextLine();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
			ProductsBean b = new ProductsBean();
			b.setCat_code(rs.getString("cat_code"));
			b.setPro_code(rs.getString("pro_code"));
			b.setPro_name(rs.getString("pro_name"));
			b.setPro_std(rs.getString("pro_std"));
			b.setPro_unit(rs.getString("pro_unit"));
			}
			else System.out.println("조회할 상품이 없습니다."); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void ViewPro() throws SQLException {  //상품목록보기
		sql = "select * from products";

		if(rs.next()) {
			do {
				ProductsBean b = new ProductsBean();
				b.setCat_code(rs.getString("cat_code"));
				b.setPro_code(rs.getString("pro_code"));
				b.setPro_name(rs.getString("pro_name"));
				b.setPro_std(rs.getString("pro_std"));
				b.setPro_unit(rs.getString("pro_unit"));
			} while(rs.next());
		} else System.out.println("해당 제품이 존재하지 않습니다.");
		rs.close();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
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

