package co.micol.bean;

public class SellBean {
	String s_code;
	String s_name;
	String s_addr;
	String s_tel;
	String s_rep;
	
	public String getS_code() {
		return s_code;
	}
	public void setS_code(String s_code) {
		this.s_code = s_code;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_addr() {
		return s_addr;
	}
	public void setS_addr(String s_addr) {
		this.s_addr = s_addr;
	}
	public String getS_tel() {
		return s_tel;
	}
	public void setS_tel(String s_tel) {
		this.s_tel = s_tel;
	}
	public String getS_rep() {
		return s_rep;
	}
	public void setS_rep(String s_rep) {
		this.s_rep = s_rep;
	}
	@Override
	public String toString() {
		return "SellBean [s_code=" + s_code + ", s_name=" + s_name + ", s_addr=" + s_addr + ", s_tel=" + s_tel
				+ ", s_rep=" + s_rep + "]";
	}
	
}
