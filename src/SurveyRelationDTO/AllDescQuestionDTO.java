package SurveyRelationDTO;

import java.util.ArrayList;

public class AllDescQuestionDTO {
	private int QID;
	private String que_title;
	private String que_trandTitle;
	private int que_typeID;
	private String que_typeTitle;
	private int que_typeOfferSeq;
	private String que_answer;
	private boolean que_isReverseType;
	private boolean q_typeDirection;
	private ArrayList<QuestionOfferDTO> questionOffer;
	public int getQID() {
		return QID;
	}
	public void setQID(int qID) {
		QID = qID;
	}
	public String getQue_title() {
		return que_title;
	}
	public void setQue_title(String que_title) {
		this.que_title = que_title;
	}
	public String getQue_trandTitle() {
		return que_trandTitle;
	}
	public void setQue_trandTitle(String que_trandTitle) {
		this.que_trandTitle = que_trandTitle;
	}
	public String getQue_typeTitle() {
		return que_typeTitle;
	}
	public void setQue_typeTitle(String que_typeTitle) {
		this.que_typeTitle = que_typeTitle;
	}
	public boolean isQue_isReverseType() {
		return que_isReverseType;
	}
	public void setQue_isReverseType(boolean que_isReverseType) {
		this.que_isReverseType = que_isReverseType;
	}
	public ArrayList<QuestionOfferDTO> getQuestionOffer() {
		return questionOffer;
	}
	public void setQuestionOffer(ArrayList<QuestionOfferDTO> questionOffer) {
		this.questionOffer = questionOffer;
	}
	public int getQue_typeOfferSeq() {
		return que_typeOfferSeq;
	}
	public void setQue_typeOfferSeq(int que_typeOfferSeq) {
		this.que_typeOfferSeq = que_typeOfferSeq;
	}
	public int getQue_typeID() {
		return que_typeID;
	}
	public void setQue_typeID(int que_typeID) {
		this.que_typeID = que_typeID;
	}
	public boolean isQ_typeDirection() {
		return q_typeDirection;
	}
	public void setQ_typeDirection(boolean q_typeDirection) {
		this.q_typeDirection = q_typeDirection;
	}
	public String getQue_answer() {
		return que_answer;
	}
	public void setQue_answer(String que_answer) {
		this.que_answer = que_answer;
	}
}
