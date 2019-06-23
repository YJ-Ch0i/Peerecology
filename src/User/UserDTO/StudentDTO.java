package User.UserDTO;

import java.sql.Timestamp;

public class StudentDTO {

	private int stu_id;
	private String name;
	private String scid;
	private int grade;
	private int grd_num;
	private int num;
	private String tea_id;
	private String gender;
	private Timestamp lastChangeDate;
	
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getGrd_num() {
		return grd_num;
	}
	public void setGrd_num(int grd_num) {
		this.grd_num = grd_num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTea_id() {
		return tea_id;
	}
	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Timestamp getLastChangeDate() {
		return lastChangeDate;
	}
	public void setLastChangeDate(Timestamp lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	
	public StudentDTO() {}
	public StudentDTO(int stu_id, String name, String scid, int grade, int grd_num, int num, String tea_id,
			String gender, Timestamp lastChangeDate) {
		super();
		this.stu_id = stu_id;
		this.name = name;
		this.scid = scid;
		this.grade = grade;
		this.grd_num = grd_num;
		this.num = num;
		this.tea_id = tea_id;
		this.gender = gender;
		this.lastChangeDate = lastChangeDate;
	}
}
