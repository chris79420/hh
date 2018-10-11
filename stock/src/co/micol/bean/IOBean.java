package co.micol.bean;

// 입출고관리

public class IOBean {
	private int IO_No;			//입출고번호(sequence)
	private String Pro_code;	//품목코드
	private String IO_Date;		//입출고 날짜
	private int IO_Amount;		//입출고 수량
	private int St_Code;		//창고 코드
	
	public int getIO_No() {
		return IO_No;
	}
	public void setIO_No(int iO_No) {
		IO_No = iO_No;
	}
	public String getPro_code() {
		return Pro_code;
	}
	public void setPro_code(String pro_code) {
		Pro_code = pro_code;
	}
	public String getIO_Date() {
		return IO_Date;
	}
	public void setIO_Date(String iO_Date) {
		IO_Date = iO_Date;
	}
	public int getIO_Amount() {
		return IO_Amount;
	}
	public void setIO_Amount(int iO_Amount) {
		IO_Amount = iO_Amount;
	}
	public int getSt_Code() {
		return St_Code;
	}
	public void setSt_Code(int st_Code) {
		St_Code = st_Code;
	}
	@Override
	public String toString() {
		return "IOBean [IO_No=" + IO_No + ", Pro_code" + Pro_code + ", IO_Date" + IO_Date + ", IO_Amount" + IO_Amount
				 + ", St_Code" + St_Code + "]";
	}
}
