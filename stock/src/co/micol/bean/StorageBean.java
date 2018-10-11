package co.micol.bean;

public class StorageBean {
	private int st_code;
	private String st_name;
	private String st_note;

	public int getSt_code() {
		return st_code;
	}

	public void setSt_code(int st_code) {
		this.st_code = st_code;
	}

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getSt_note() {
		return st_note;
	}

	public void setSt_note(String st_note) {
		this.st_note = st_note;
	}

	@Override
	public String toString() {

		return "StorageBean [st_code=" + st_code + ", st_name=" + st_name + ", st_note=" + st_note + "]";
	}

}
