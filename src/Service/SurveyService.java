package Service;

import java.util.*;

import SurveyRelationDAO.SurveyDAO;
import SurveyRelationDTO.SurveyDTO;
import SurveyRelationDTO.SurveyGoingDTO;
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
	public ArrayList<SurveyDTO> showSearchSurveys(String version_title){
		return dao.showSearchSurveys(version_title);
	}
	public void goingVersionRegister(String[] SCIDs, int surveyNo, String startDate, String endDate) {
		dao.goingVersionRegister(SCIDs, surveyNo, startDate, endDate);
	}
	public ArrayList<SurveyGoingDTO> showAllGoingSurveys(){
		return dao.showAllGoingSurveys();
	}
	public SurveyGoingDTO startSurvey(String SCID){
		return dao.startSurvey(SCID);
	}
}
