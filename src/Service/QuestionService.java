package Service;

import java.util.ArrayList;

import SurveyRelationDAO.QuestionDAO;
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
	public void queTypeRegister(String typeTitle){
		dao.queTypeRegister(typeTitle);
	}
	public int queTrandDelete(int trandNum) {
		return dao.queTrandDelete(trandNum);
	}
	public int queTypeDelete(int trandNum) {
		return dao.queTypeDelete(trandNum);
	}
}
