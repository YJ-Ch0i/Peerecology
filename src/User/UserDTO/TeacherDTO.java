package User.UserDTO;

import java.sql.Timestamp;

public class TeacherDTO {

	private String TID;
	private String pwd;
	private String name;
	private String SCID;
	private int classes;
	private int grade;
	private boolean mailcheck;
	private String lastChangeYear;
	
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSCID() {
		return SCID;
	}
	public void setSCID(String sCID) {
		SCID = sCID;
	}
	public boolean isMailcheck() {
		return mailcheck;
	}
	public void setMailcheck(boolean mailcheck) {
		this.mailcheck = mailcheck;
	}
	public String getLastChangeYear() {
		return lastChangeYear;
	}
	public void setLastChangeYear(String lastChangeYear) {
		this.lastChangeYear = lastChangeYear;
	}
	
	public TeacherDTO() {}
	
	/**
	 * 전체 생성자
	 * @param tID
	 * @param pwd
	 * @param name
	 * @param sCID
	 * @param classes
	 * @param grade
	 * @param mailcheck
	 * @param lastChangeDate
	 */
	public TeacherDTO(String tID, String pwd, String name, String sCID,int grade, int classes,boolean mailcheck, String lastChangeYear) {
		super();
		TID = tID;
		this.pwd = pwd;
		this.name = name;
		SCID = sCID;
		this.grade = grade;
		this.classes = classes;
		this.mailcheck = mailcheck;
		this.lastChangeYear = lastChangeYear;
	}
	
	/**
	 * 회원가입용 생성자
	 * @param tID
	 * @param pwd
	 * @param name
	 * @param mailcheck
	 * @param lastChangeDate
	 */
	public TeacherDTO(String tID, String pwd, String name, boolean mailcheck, String lastChangeYear) {
		super();
		TID = tID;
		this.pwd = pwd;
		this.name = name;
		this.mailcheck = mailcheck;
		this.lastChangeYear = lastChangeYear;
	}
	public int getClasses() {
		return classes;
	}
	public void setClasses(int classes) {
		this.classes = classes;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
