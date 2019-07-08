package SurveyRelationDTO;

public class QuestionTypeDTO {

	private int q_typeID;
	private String descript;
	
	
	public QuestionTypeDTO(int q_typeID, String descript) {
		this.q_typeID = q_typeID;
		this.descript = descript;
	}
	public int getQ_typeID() {
		return q_typeID;
	}
	public void setQ_typeID(int q_typeID) {
		this.q_typeID = q_typeID;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	
	
}
