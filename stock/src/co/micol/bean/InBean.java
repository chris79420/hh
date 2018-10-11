package co.micol.bean;

public class InBean {
	private String in_code;
	private int in_line;
	private int in_b_code;
	private String in_pro_code; 
	private int in_amount;
	private int in_price;
	private int in_sum;
	private String in_date;
	
	public String getIn_code() {
		return in_code;
	}
	public void setIn_code(String in_code) {
		this.in_code = in_code;
	}
	public int getIn_line() {
		return in_line;
	}
	public void setIn_line(int in_line) {
		this.in_line = in_line;
	}
	
	public int getIn_b_code() {
		return in_b_code;
	}
	public void setIn_b_code(int in_b_code) {
		this.in_b_code = in_b_code;
	}
	public String getIn_pro_code() {
		return in_pro_code;
	}
	public void setIn_pro_code(String in_pro_code) {
		this.in_pro_code = in_pro_code;
	}
	public int getIn_amount() {
		return in_amount;
	}
	public void setIn_amount(int in_amount) {
		this.in_amount = in_amount;
	}
	public int getIn_price() {
		return in_price;
	}
	public void setIn_price(int in_price) {
		this.in_price = in_price;
	}
	public int getIn_sum() {
		return in_sum;
	}
	public void setIn_sum(int in_sum) {
		this.in_sum = in_sum;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String date) {
		this.in_date = date;
	}
	@Override
	public String toString() {

		return "InBean [in_code=" + in_code + ", in_line=" + in_line + ", in_b_code=" + in_b_code + ", in_pro_code="
				+ in_pro_code + ", in_amount=" + in_amount + ", in_price=" + in_price + ", in_sum=" + in_sum + ", in_date="
				+ in_date + "]";
	}

	
}