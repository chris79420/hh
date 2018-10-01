package co.micol.bean;

public class InBean {
	private int in_code;
	private int in_line;
	private int in_amount;
	private int in_price;
	private int in_sum;
	private String date;
	
	public int getIn_code() {
		return in_code;
	}
	public void setIn_code(int in_code) {
		this.in_code = in_code;
	}
	public int getIn_line() {
		return in_line;
	}
	public void setIn_line(int in_line) {
		this.in_line = in_line;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "InBean [in_code=" + in_code + ", in_line=" + in_line + ", in_amount=" + in_amount + ", in_price="
				+ in_price + ", in_sum=" + in_sum + ", date=" + date + "]";
	}
	
}