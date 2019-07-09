package SurveyRelationDTO;

public class QuestionOfferDTO {
	private int q_typeID;
	private int qOfferSeq;
	private String title;
	public QuestionOfferDTO(int q_typeID, int qOfferSeq, String title) {
		this.q_typeID = q_typeID;
		this.qOfferSeq = qOfferSeq;
		this.title = title;
	}
	public QuestionOfferDTO() {
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getqOfferSeq() {
		return qOfferSeq;
	}
	public void setqOfferSeq(int qOfferSeq) {
		this.qOfferSeq = qOfferSeq;
	}
	public int getQ_typeID() {
		return q_typeID;
	}
	public void setQ_typeID(int q_typeID) {
		this.q_typeID = q_typeID;
	}
	
	
}
