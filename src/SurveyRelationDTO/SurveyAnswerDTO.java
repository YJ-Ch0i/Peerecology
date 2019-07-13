package SurveyRelationDTO;

public class SurveyAnswerDTO {
	private int answerID;
	private int QID;
	private int studentID;
	private boolean isMultiAnswer;
	private String answerValue;
	private int ingSeq;
	public int getAnswerID() {
		return answerID;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setSutudentID(int studentID) {
		this.studentID = studentID;
	}
	public boolean isMultiAnswer() {
		return isMultiAnswer;
	}
	public void setMultiAnswer(boolean isMultiAnswer) {
		this.isMultiAnswer = isMultiAnswer;
	}
	public String getAnswerValue() {
		return answerValue;
	}
	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}
	public int getQID() {
		return QID;
	}
	public void setQID(int qID) {
		QID = qID;
	}
	public int getIngSeq() {
		return ingSeq;
	}
	public void setIngSeq(int ingSeq) {
		this.ingSeq = ingSeq;
	}
}
