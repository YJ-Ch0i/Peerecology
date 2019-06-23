package SurveyRelationDTO;

public class QuestionDTO {
	private int QID;
	private String title;
	private int QType;
	private int Ttype;
	private int QOfferSeq;
	private String offerTitle;
	
	public int getQID() {
		return QID;
	}
	public void setQID(int qID) {
		QID = qID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQType() {
		return QType;
	}
	public void setQType(int qType) {
		QType = qType;
	}
	public int getTtype() {
		return Ttype;
	}
	public void setTtype(int ttype) {
		Ttype = ttype;
	}
	public int getQOfferSeq() {
		return QOfferSeq;
	}
	public void setQOfferSeq(int qOfferSeq) {
		QOfferSeq = qOfferSeq;
	}
	public String getOfferTitle() {
		return offerTitle;
	}
	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}
}
