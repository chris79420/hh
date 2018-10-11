package co.micol.bean;

public class ProductsBean {

	private String cat_code;
	private String pro_code;
	private String pro_name;
	private String pro_std;
	private String pro_unit;
	private int pro_price;
	private int st_code;
	
	public String getCat_code() {
		return cat_code;
	}
	public void setCat_code(String cat_code) {
		this.cat_code = cat_code;
	}
	public String getPro_code() {
		return pro_code;
	}
	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_std() {
		return pro_std;
	}
	public void setPro_std(String pro_std) {
		this.pro_std = pro_std;
	}
	public String getPro_unit() {
		return pro_unit;
	}
	public void setPro_unit(String pro_unit) {
		this.pro_unit = pro_unit;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	public int getSt_code() {
		return st_code;
	}
	public void setSt_code(int st_code) {
		this.st_code = st_code;
	}
	
	@Override
	public String toString() {

		return "ProductsBean [cat_code=" + cat_code + ", pro_code=" + pro_code + ", pro_name=" + pro_name + ", pro_std="
				+ pro_std + ", pro_unit=" + pro_unit + ", pro_price=" + pro_price + ", st_code=" + st_code + "]";
	}
}
