package co.micol.controller;

import java.sql.SQLException;
import java.util.Scanner;
import co.micol.bean.BuyBean;
import co.micol.bean.InBean;
import co.micol.bean.OutBean;
import co.micol.bean.ProductsBean;
import co.micol.bean.SellBean;
import co.micol.bean.StorageBean;
import co.micol.dao.BuyDao;
import co.micol.dao.InDao;
import co.micol.dao.OutDao;
import co.micol.dao.ProductsDao;
import co.micol.dao.SellDao;
import co.micol.dao.StorageDao;

public class Start {

	public Start() throws SQLException {

		Scanner sc = new Scanner(System.in);
		ProductsBean pb = new ProductsBean();
		StorageBean stb = new StorageBean();
		BuyBean bb = new BuyBean();
		SellBean sb = new SellBean();
		InBean ib = new InBean();
		OutBean ob = new OutBean();
		ProductsDao pro = new ProductsDao();
		StorageDao sto = new StorageDao();
		BuyDao buy = new BuyDao();
		SellDao sel = new SellDao();
		InDao in = new InDao();
		OutDao out = new OutDao();

		System.out.println("원하는 메뉴를 선택하세요.");
		System.out.println("=====================");
		System.out.println("1. 기초정보관리 2. 입출고정보 3. 재고정보 4. 종료");
		int n = Integer.parseInt(sc.next());

		if (n == 1) {
			System.out.println("[기초정보 관리] 원하는 메뉴를 선택하세요.");
			System.out.println("==================================");
			System.out.println("1. 품목정보 2. 창고정보 3. 구매업체관리 4. 판매업체관리");
			int x = Integer.parseInt(sc.next());
			if (x == 1) {
				System.out.println("[기초정보 관리 - 품목정보] 원하는 메뉴를 선택하세요.");
				System.out.println("===========================================");
				System.out.println("1. 등록 2. 수정 3. 삭제 4. 검색 5. 전체목록");
				int a = Integer.parseInt(sc.next());
				if (a == 1) {
					pro.InsertPro(pb);
				} else if (a == 2) {
					pro.EditPro(pb);
				} else if (a == 3) {
					pro.DeletePro();
				} else if (a == 4) {
					pro.SearchPro();
				} else if (a == 5) {
					pro.ViewPro();
				} else					
					System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			} else if (x == 2) {
				System.out.println("[기초정보 관리 - 창고정보] 원하는 메뉴를 선택하세요.");
				System.out.println("===========================================");
				System.out.println("1. 등록 2. 수정 3. 삭제 4. 검색 5. 전체목록");
				int b = Integer.parseInt(sc.next());
				if (b == 1) {
					sto.InsertStorage(stb);
				} else if (b == 2) {
					sto.EditStorage(stb);
				} else if (b == 3) {
					sto.DeleteStorage();
				} else if (b == 4) {
					sto.SearchStorage();
				} else if (b == 5) {
					sto.ViewStorage();
				} else
					System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			} else if (x == 3) {
				System.out.println("[기초정보 관리 - 구매업체관리] 원하는 메뉴를 선택하세요.");
				System.out.println("==============================================");
				System.out.println("1. 등록 2. 수정 3. 삭제 4. 검색 5. 전체목록");
				int c = Integer.parseInt(sc.next());
				if (c == 1) {
					buy.insertBuy();
				} else if (c == 2) {
					buy.editBuy();
				} else if (c == 3) {
					buy.deleteBuy();
				} else if (c == 4) {
					buy.searchBuy();
				} else if (c == 5) {
					buy.viewBuy();
				} else
					System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			} else if (x == 4) {
				System.out.println("[기초정보 관리 - 판매업체 관리] 원하는 메뉴를 선택하세요.");
				System.out.println("===============================================");
				System.out.println("1. 등록 2. 수정 3. 삭제 4. 검색 5. 전체목록");
				int d = Integer.parseInt(sc.next());
				if (d == 1) {
					sel.insertSell();
				} else if (d == 2) {
					sel.insertSell();
				} else if (d == 3) {
					sel.deleteSell();
				} else if (d == 4) {
					sel.searchSell();
				} else if (d == 5) {
					sel.viewSell();
				} else
					System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			} else
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
		} else if (n == 2) {
			System.out.println("[입출고 정보] 원하는 메뉴를 선택하세요.");
			System.out.println("================================");
			System.out.println("1. 입고정보 2. 출고정보 3. 입출고관리 4. 입출고조회");
			int y = Integer.parseInt(sc.next());
			if (y == 1) {
				System.out.println("[입출고 정보 - 입고정보] 원하는 메뉴를 선택하세요.");
				System.out.println("================================");
				System.out.println("1. 검색 2. 전체목록");
				int a = Integer.parseInt(sc.next());
				if (a == 1) { 
					in.searchIn();
				} else if (a == 2) {
					in.viewIn();
				} else 
					System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			} else if (y == 2) {
				System.out.println("[입출고 정보 - 출고정보] 원하는 메뉴를 선택하세요.");
				System.out.println("================================");
				System.out.println("1. 검색 2. 전체목록");
				int b = Integer.parseInt(sc.next());
				if (b == 1) {
					out.searchOut();
				} else if (b == 2) {
					out.viewOut();
				} else 
					System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			} else if (y == 3) {
//입출고내역 등록하는 프로시저 호출				
			} else if (y == 4) {
//입출고 조회하기				
			} else
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
		} else if (n == 3) {
			System.out.println("[재고 정보] 원하는 메뉴를 선택하세요.");
			System.out.println("==============================");
			System.out.println("1. 현재고 2. 품목별 재고현황 3. 창고별 재고 현황");
			int z = Integer.parseInt(sc.next());
//수정 필요
			if (z == 1) {
			} else if (z == 2) {
			} else if (z == 3) {
			} else
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");

		} else if (n == 4) {
			System.out.println("프로그램을 종료합니다.");
			System.exit(n);
		} else
			System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
	}

	public static void main(String[] args) throws SQLException {

		Start home = new Start();
	}
}
