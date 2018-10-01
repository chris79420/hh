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
	
	public int InsertStorage(StorageBean b) throws SQLException { //창고 등록
		sql = "insert into storage "
				+ "values(?,?,?)";
		System.out.println("창고 코드를 입력하세요.");
		b.setSt_code(sc.nextInt());
		System.out.println("창고 이름을 입력하세요");
		b.setSt_name(sc.nextLine());
		System.out.println("창고 설명을 입력하세요");
		b.setSt_note(sc.nextLine());
		
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, b.getSt_code());  
			psmt.setString(2, b.getSt_name()); 
			psmt.setString(3, b.getSt_note());
			
			r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ViewStorage();
		return r;
	}
	
	public void DeleteStorage() throws SQLException { //창고 삭제
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
	
	public void EditStorage() { //창고 수정
		
	}
	
	public void SearchStorage() {	 //창고 조회
		sc = new Scanner(System.in);
		sql = "select * from storage where st_code = ?";  	
		System.out.println("조회할 창고의 코드를 입력하세요");
		String s = sc.nextLine();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				StorageBean b = new StorageBean();
				b.setSt_code(rs.getInt("st_code"));
				b.setSt_name(rs.getString("st_name"));
				b.setSt_note(rs.getString("st_note"));
			}
			else System.out.println("조회할 창고가 없습니다."); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void ViewStorage() throws SQLException {		//창고 목록
		sql = "select * from storage";

		if(rs.next()) {
			do {
				StorageBean b = new StorageBean();
				b.setSt_code(rs.getInt("st_code"));
				b.setSt_name(rs.getString("st_name"));
				b.setSt_note(rs.getString("st_note"));
				
			} while(rs.next());
		} else System.out.println("해당 창고가 존재하지 않습니다.");
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
