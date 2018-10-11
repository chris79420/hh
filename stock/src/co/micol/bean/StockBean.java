package co.micol.bean;

//재고관리

public class StockBean {

	private String pro_code; // 품목코드
	private int st_code; // 창고코드
	private int st_amount; // 재고수량

	public String getPro_code() {
		return pro_code;
	}

	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}

	public int getSt_code() {
		return st_code;
	}

	public void setSt_code(int st_code) {
		this.st_code = st_code;
	}

	public int getSt_amount() {
		return st_amount;
	}

	public void setSt_amount(int st_amount) {
		this.st_amount = st_amount;
	}

	@Override
	public String toString() {

		return "StockBean [pro_code=" + pro_code + ", st_code=" + st_code + ", st_amount=" + st_amount + "]";
	}
}
