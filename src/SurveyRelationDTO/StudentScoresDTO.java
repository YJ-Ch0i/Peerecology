package SurveyRelationDTO;

public class StudentScoresDTO {
	
	private int ingseq;
	private int surveyNo;
	private String SCID;
	private int grade;
	private int grd_num;
	private int stu_id;
	private String sName;
	private int bigTrandId;
	private String bigTrandDesc;
	private String trandDesc;
	private int trandId;
	private double score;
	private String year;
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBigTrandDesc() {
		return bigTrandDesc;
	}
	public void setBigTrandDesc(String bigTrandDesc) {
		this.bigTrandDesc = bigTrandDesc;
	}
	public String getTrandDesc() {
		return trandDesc;
	}
	public void setTrandDesc(String trandDesc) {
		this.trandDesc = trandDesc;
	}
	public int getIngseq() {
		return ingseq;
	}
	public void setIngseq(int ingseq) {
		this.ingseq = ingseq;
	}
	public int getSurveyNo() {
		return surveyNo;
	}
	public void setSurveyNo(int surveyNo) {
		this.surveyNo = surveyNo;
	}
	public String getSCID() {
		return SCID;
	}
	public void setSCID(String sCID) {
		SCID = sCID;
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
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int getBigTrandId() {
		return bigTrandId;
	}
	public void setBigTrandId(int bigTrandId) {
		this.bigTrandId = bigTrandId;
	}
	public int getTrandId() {
		return trandId;
	}
	public void setTrandId(int trandId) {
		this.trandId = trandId;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	public StudentScoresDTO() {}
	public StudentScoresDTO(int ingseq, int surveyNo, String sCID, int grade, int grd_num, int stu_id, int bigTrandId,
			int trandId, double score) {
		super();
		this.ingseq = ingseq;
		this.surveyNo = surveyNo;
		SCID = sCID;
		this.grade = grade;
		this.grd_num = grd_num;
		this.stu_id = stu_id;
		this.bigTrandId = bigTrandId;
		this.trandId = trandId;
		this.score = score;
	}
}
