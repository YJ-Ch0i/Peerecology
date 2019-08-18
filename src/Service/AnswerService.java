package Service;

import java.util.ArrayList;

import SurveyRelationDAO.AnswerDAO;
import SurveyRelationDTO.SurveyAnswerDTO;

public class AnswerService {
	private static AnswerService service = new AnswerService();
	private AnswerService() {}
	AnswerDAO dao =  AnswerDAO.getInstance();
	public static AnswerService getInstance() {
		return service;
	}
	public void surveyAnswerRegister(SurveyAnswerDTO answerDTO) {
		dao.surveyAnswerRegister(answerDTO);
	}
	public void multiAnswerRegister(String[] ddoraeAnswer) {
		dao.multiAnswerRegister(ddoraeAnswer);
	}
	
	public ArrayList<SurveyAnswerDTO> getAnswers(int ingSeq) {
		return dao.getAnswers(ingSeq);
	}
	public int getAnswersCount(int ingSeq, int studentID) {
		return dao.getAnswersCount(ingSeq, studentID);
	}
}
