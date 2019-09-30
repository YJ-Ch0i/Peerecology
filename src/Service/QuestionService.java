package Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import SurveyRelationDAO.*;
import SurveyRelationDTO.*;


public class QuestionService {
	private static QuestionService service = new QuestionService();
	private QuestionService() {}
	QuestionDAO dao =  QuestionDAO.getInstance();
	public static QuestionService getInstance() {
		return service;
	}
	public ArrayList<QuestionTrandManagerDTO> showAllBigTrands(){
		return dao.showAllBigTrands();
	}
	public ArrayList<QuestionTrandTypeDTO> searchTrandList(int surveyNo, int ingSeq, String scid) {
		return dao.searchTrandList(surveyNo, ingSeq, scid);
	}
	
	public ArrayList<QuestionTrandTypeDTO> getTrandListToBigT(int surNo, String scid, int btid) {
		return dao.getTrandListToBigT(surNo, scid, btid);
	}
	
	public QuestionTrandTypeDTO searchTrand(int trandID) {
		return dao.searchTrand(trandID);
	}
	public ArrayList<QuestionTrandManagerDTO> searchBigTrandList(int surNo, int ingSeq, String scid, Date start, Date end) {
		return dao.searchBigTrandList(surNo, ingSeq, scid, start, end);
	}
	public ArrayList<QuestionTrandManagerDTO> getBigTrandList(String scid, Date start, Date end) {
		return dao.getBigTrandList(scid, start, end);
	}
	public ArrayList<QuestionTrandTypeDTO> getTrandToBigTID(int bigTid) {
		return dao.getTrandToBigTID(bigTid);
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
	public QuestionTypeDTO showAllType(int qtype) {
		return dao.showAllType(qtype);
	}
	public int trandManagerRegister(String bigTrandTitle, String bigTrandExplan) {
		return dao.trandManagerRegister(bigTrandTitle, bigTrandExplan);
	}
	public int queTrandRegister(String trandTitle){
		return dao.queTrandRegister(trandTitle);
	}
	public int queTrandDifferentRegister(String trandTitle,int bigTrandID) {
		return dao.queTrandDifferentRegister(trandTitle, bigTrandID);
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

	public int queBigTrandDelete(int trandNum) {
		return dao.queBigTrandDelete(trandNum);
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
	public int countOfTrandQuestion(int surNo, int tId) {
		return dao.countOfTrandQuestion(surNo, tId);
	}
	
	public List<QuestionDTO> getPeerQuestionListInSeq(int surNo) {
		return dao.getPeerQuestionListInSeq(surNo);
	}
	
	public List<SurveyAnswerDTO> getMultiAnswerValueInQuestion(int qId, int ingSeq, String scid, int grade, int grdNum) {
		return dao.getMultiAnswerValueInQuestion(qId, ingSeq, scid, grade, grdNum);
	}
}
