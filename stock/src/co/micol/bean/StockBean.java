package co.micol.bean;

//재고관리

public class StockBean {

	private String Pro_Code;	//품목코드
	private int St_Code;		//창고코드
	private int St_Amount;		//재고수량
	
	public String getPro_Code() {
		return Pro_Code;
	}
	public void setPro_Code(String pro_Code) {
		Pro_Code = pro_Code;
	}
	public int getSt_Code() {
		return St_Code;
	}
	public void setSt_Code(int st_Code) {
		St_Code = st_Code;
	}
	public int getSt_Amount() {
		return St_Amount;
	}
	public void setSt_Amount(int st_Amount) {
		St_Amount = st_Amount;
	}
}
