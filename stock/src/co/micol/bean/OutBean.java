package co.micol.bean;

public class OutBean {
	private String out_code;
	private int out_line;
	private int out_s_code;
	private String out_pro_code; 
	private int out_amount;
	private int out_price;
	private int out_sum;
	private String out_date;
	
	
	public String getOut_code() {
		return out_code;
	}
	public void setOut_code(String out_code) {
		this.out_code = out_code;
	}
	public int getOut_line() {
		return out_line;
	}
	public void setOut_line(int out_loute) {
		this.out_line = out_loute;
	}
	
	public int getOut_s_code() {
		return out_s_code;
	}
	public void setOut_s_code(int out_s_code) {
		this.out_s_code = out_s_code;
	}
	public String getOut_pro_code() {
		return out_pro_code;
	}
	public void setOut_pro_code(String out_pro_code) {
		this.out_pro_code = out_pro_code;
	}
	public int getOut_amount() {
		return out_amount;
	}
	public void setOut_amount(int out_amount) {
		this.out_amount = out_amount;
	}
	public int getOut_price() {
		return out_price;
	}
	public void setOut_price(int out_price) {
		this.out_price = out_price;
	}
	public int getOut_sum() {
		return out_sum;
	}
	public void setOut_sum(int out_sum) {
		this.out_sum = out_sum;
	}
	public String getOut_date() {
		return out_date;
	}
	public void setOut_date(String date) {
		this.out_date = date;
	}
	@Override
	public String toString() {
		return "OutBean [out_code=" + out_code + ", out_line=" + out_line + ", out_s_code=" + out_s_code
				+ ", out_pro_code=" + out_pro_code + ", out_amount=" + out_amount + ", out_price=" + out_price
				+ ", out_sum=" + out_sum + ", out_date=" + out_date + "]";
	
}