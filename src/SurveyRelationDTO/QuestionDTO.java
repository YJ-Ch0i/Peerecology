package SurveyRelationDTO;

public class QuestionDTO {
	private int QID;
	private String title;
	private int QType;
	private int Ttype;
	private boolean isReverseType;
	
	public QuestionDTO(int QID, String title, int QType, int Ttype, boolean isReverseType) {
		this.QID = QID;
		this.title = title;
		this.QType = QType;
		this.Ttype = Ttype;
		this.setReverseType(isReverseType);
	}
	public QuestionDTO(String title, int QType, int Ttype, boolean isReverseType) {
		
		this.title = title;
		this.QType = QType;
		this.Ttype = Ttype;
		this.setReverseType(isReverseType);
	}
	public QuestionDTO() 
	{
	}
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
	public boolean isReverseType() {
		return isReverseType;
	}
	public void setReverseType(boolean isReverseType) {
		this.isReverseType = isReverseType;
	}
}
