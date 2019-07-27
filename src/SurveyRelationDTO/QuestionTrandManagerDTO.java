package SurveyRelationDTO;

public class QuestionTrandManagerDTO {
	private int bigTrandID;
	private String descript;
	
	public QuestionTrandManagerDTO(int bigTrandID, String descript) {
		this.bigTrandID = bigTrandID;
		this.descript = descript;
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
	
}
