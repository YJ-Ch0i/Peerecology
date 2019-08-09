package SurveyRelationDTO;

public class QuestionTrandManagerDTO {
	private int bigTrandID;
	private String descript;
	private int isShowing;
	private String explan;
	
	public QuestionTrandManagerDTO() {}
	public QuestionTrandManagerDTO(int bigTrandID, String descript) {
		this.bigTrandID = bigTrandID;
		this.descript = descript;
	}
	public QuestionTrandManagerDTO(int bigTrandID, String descript, int isShowing, String explan) {
		super();
		this.bigTrandID = bigTrandID;
		this.descript = descript;
		this.isShowing = isShowing;
		this.explan = explan;
	}
	
	public int getBigTrandID() {
		return bigTrandID;
	}
	public void setBigTrandID(int bigTrandID) {
		this.bigTrandID = bigTrandID;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getIsShowing() {
		return isShowing;
	}
	public void setIsShowing(int isShowing) {
		this.isShowing = isShowing;
	}
	public String getExplan() {
		return explan;
	}
	public void setExplan(String explan) {
		this.explan = explan;
	}
	
}
