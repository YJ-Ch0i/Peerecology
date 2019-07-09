package SurveyRelationDTO;

public class QuestionTypeDTO {

	private int q_typeID;
	private String descript;
	private int q_typeOfferSeq;
	
	public QuestionTypeDTO(int q_typeID, String descript) {
		this.q_typeID = q_typeID;
		this.descript = descript;
	}
	public QuestionTypeDTO(int q_typeID, String descript,int q_typeOfferSeq) {
		this.q_typeID = q_typeID;
		this.descript = descript;
		this.q_typeOfferSeq = q_typeOfferSeq;
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
	public int getQ_typeOfferSeq() {
		return q_typeOfferSeq;
	}
	public void setQ_typeOfferSeq(int q_typeOfferSeq) {
		this.q_typeOfferSeq = q_typeOfferSeq;
	}
	
	
	
}
