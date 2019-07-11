package Service;

import java.util.*;

import SurveyRelationDAO.SurveyDAO;
import SurveyRelationDTO.SurveyDTO;
import SurveyRelationDTO.SurveyManagerDTO;


public class SurveyService {
	private static SurveyService service = new SurveyService();
	private SurveyService() {}
	SurveyDAO dao =  SurveyDAO.getInstance();
	public static SurveyService getInstance() {
		return service;
	}
	
	public void version_QuestionRegister(String[] questions) {
		dao.version_QuestionRegister(questions);
	}
	public void versionTitleRegister(String version_title) {
		dao.versionTitleRegister(version_title);
	}
	public ArrayList<SurveyDTO> showAllSurveys(){
		return dao.showAllSurveys();
	}
	public ArrayList<SurveyManagerDTO> showQuestionsToManager(int surveyNo){
		return dao.showQuestionsToManager(surveyNo);
	}
	
}
