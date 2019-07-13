package SurveyRelationDTO;

public class QuestionDTO {
	private int QID;
	private String title;
	private int QType;
	private int Ttype;
	private boolean isReverseType;
	private String que_answer;
	public QuestionDTO(int QID, String title, int QType, int Ttype, boolean isReverseType) {
		this.QID = QID;
		this.title = title;
		this.QType = QType;
		this.Ttype = Ttype;
		this.isReverseType = isReverseType;
	}
	public QuestionDTO(int QID, String title, int QType, int Ttype, boolean isReverseType, String que_answer) {
		this.QID = QID;
		this.title = title;
		this.QType = QType;
		this.Ttype = Ttype;
		this.isReverseType = isReverseType;
		this.que_answer = que_answer;
	}

	public QuestionDTO(String title, int QType, int Ttype, boolean isReverseType, String que_answer) {
		
		this.title = title;
		this.QType = QType;
		this.Ttype = Ttype;
		this.isReverseType = isReverseType;
		this.que_answer = que_answer;
	}
	public QuestionDTO(String title, int QType, int Ttype, boolean isReverseType) {
		
		this.title = title;
		this.QType = QType;
		this.Ttype = Ttype;
		this.isReverseType = isReverseType;
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
	public String getQue_answer() {
		return que_answer;
	}
	public void setQue_answer(String que_answer) {
		this.que_answer = que_answer;
	}
}
