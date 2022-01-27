package School.SchoolDTO;

public class SchoolDTO {
//test
	private String scid;
	private String scname;
	private String scaddress;
	
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
	}
	public String getScname() {
		return scname;
	}
	public void setScname(String scname) {
		this.scname = scname;
	}
	public String getScaddress() {
		return scaddress;
	}
	public void setScaddress(String scaddress) {
		this.scaddress = scaddress;
	}
	
	public SchoolDTO() {}
	public SchoolDTO(String scid, String scname, String scaddress) {
		super();
		this.scid = scid;
		this.scname = scname;
		this.scaddress = scaddress;
	}
}
