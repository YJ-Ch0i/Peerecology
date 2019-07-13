package Service;

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
}
