package Service;

import java.util.ArrayList;

import SurveyRelationDAO.*;
import SurveyRelationDTO.*;


public class QuestionService {
	private static QuestionService service = new QuestionService();
	private QuestionService() {}
	QuestionDAO dao =  QuestionDAO.getInstance();
	public static QuestionService getInstance() {
		return service;
	}
	public ArrayList<QuestionDTO> showAllQuestion() {
		return dao.showAllQuestion();
		}
	public ArrayList<QuestionTrandTypeDTO> showAllTrand() {
	return dao.showAllTrand();
	}
	public ArrayList<QuestionTypeDTO> showAllType() {
		return dao.showAllType();
		}
	public void queTrandRegister(String trandTitle){
		dao.queTrandRegister(trandTitle);
	}
	public void queTypeRegister(String typeTitle, int offerSeq){
		dao.queTypeRegister(typeTitle, offerSeq);
	}
	public void questionRegister(QuestionDTO questionDTO) {
		dao.questionRegister(questionDTO);
	}
	public void questionDifferentRegister(QuestionDTO questionDTO) {
		dao.questionDifferentRegister(questionDTO);
	}
	public int queTrandDelete(int trandNum) {
		return dao.queTrandDelete(trandNum);
	}
	public int queTypeDelete(int trandNum) {
		return dao.queTypeDelete(trandNum);
	}
	public int queTypeOfferDelete(int typeNum){
		return dao.queTypeOfferDelete(typeNum);
	}
	public void questionOfferRegister(int QoferSeq, String offers) {
		dao.questionOfferRegister(QoferSeq, offers);
	}
	public ArrayList<QuestionOfferDTO> showQuestionOffer(int q_typeID) {
		return dao.showQuestionOffer(q_typeID);
	}
	public QuestionDTO showQuestion(int QID) {
		return dao.showQuestion(QID);
	}
}
