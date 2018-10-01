package co.micol.bean;

public class BuyBean {
	String b_code;
	String b_name;
	String b_addr;
	String b_tel;
	String b_rep;
	
	public String getB_code() {
		return b_code;
	}
	public void setB_code(String b_code) {
		this.b_code = b_code;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_addr() {
		return b_addr;
	}
	public void setB_addr(String b_addr) {
		this.b_addr = b_addr;
	}
	public String getB_tel() {
		return b_tel;
	}
	public void setB_tel(String b_tel) {
		this.b_tel = b_tel;
	}
	public String getB_rep() {
		return b_rep;
	}
	public void setB_rep(String b_rep) {
		this.b_rep = b_rep;
	}
	@Override
	public String toString() {
		return "BuyBean [b_code=" + b_code + ", b_name=" + b_name + ", b_addr=" + b_addr + ", b_tel=" + b_tel
				+ ", b_rep=" + b_rep + "]";
	}

}
