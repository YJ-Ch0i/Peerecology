package Service;

import java.sql.Date;
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
	
	public ArrayList<QuestionTrandTypeDTO> searchTrandList(int surNo, int ingSeq, String scid, Date start, Date end) {
		return dao.searchTrandList(surNo, ingSeq, scid, start, end);
	}
	public QuestionTrandTypeDTO searchTrand(int trandID) {
		return dao.searchTrand(trandID);
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
	public ArrayList<QuestionTypeDTO> showAllType(int qtype) {
		return dao.showAllType(qtype);
	}
	public void queTrandRegister(String trandTitle){
		dao.queTrandRegister(trandTitle);
	}
	public void queTypeRegister(String typeTitle, int offerSeq, boolean q_typeDirection){
		dao.queTypeRegister(typeTitle, offerSeq,q_typeDirection);
	}
	public void questionRegister(QuestionDTO questionDTO) {
		dao.questionRegister(questionDTO);
	}
	public void questionDifferentRegister(QuestionDTO questionDTO) {
		dao.questionDifferentRegister(questionDTO);
	}
	public int questionDelete(int QID) {
		return dao.questionDelete(QID);
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
	public AllDescQuestionDTO showQuestion(int QID) {
		return dao.showQuestion(QID);
	}
}
