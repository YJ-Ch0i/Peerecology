package Service;

import java.util.ArrayList;

import SurveyRelationDAO.QuestionDAO;
import SurveyRelationDTO.QuestionTrandTypeDTO;


public class QuestionService {
	private static QuestionService service = new QuestionService();
	private QuestionService() {}
	QuestionDAO dao =  QuestionDAO.getInstance();
	public static QuestionService getInstance() {
		return service;
	}
	
	public ArrayList<QuestionTrandTypeDTO> showAllTrand() {
	return dao.showAllTrand();
	}
	public void queTrandRegister(String trandTitle){
		dao.queTrandRegister(trandTitle);
	}
	public int queTrandDelete(int trandNum) {
		return dao.queTrandDelete(trandNum);
	}
}
