package School.SchoolDTO;

public class SchoolDTO {

	private String SCID;
	private String name;
	private String address;
	
	
	public SchoolDTO() {}
	public SchoolDTO(String SCID, String name, String address) {
		this.SCID = SCID;
		this.name = name;
		this.address = address;
	}
	public String getSCID() {
		return SCID;
	}
	public void setSCID(String sCID) {
		SCID = sCID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
