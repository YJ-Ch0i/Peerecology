package Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.*;

import SurveyRelationDAO.SurveyDAO;
import SurveyRelationDTO.Stu_ans_ViewDTO;
import SurveyRelationDTO.SurveyDTO;
import SurveyRelationDTO.SurveyGoingDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import view.viewDTO.SearchEndsurveyDTO;


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
	public void versionTitleRegister(String version_title,String descript) {
		dao.versionTitleRegister(version_title, descript);
	}
	public int versionDelete(int SurveyNo) {
		return dao.versionDelete(SurveyNo);
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
	public ArrayList<SurveyGoingDTO> showAllGoingSurveys() throws ParseException{
		return dao.showAllGoingSurveys();
	}
	public ArrayList<SurveyGoingDTO> showAllResultSurvey() throws ParseException{
		return dao.showAllResultSurvey();
	}
	public ArrayList<SurveyGoingDTO> showResultSurvey(String SCID_name) throws ParseException{
		return dao.showResultSurvey(SCID_name);
	}
	public SurveyGoingDTO startSurvey(String SCID){
		return dao.startSurvey(SCID);
	}
	public SurveyGoingDTO findIngSurvey(int surveyNo, String SCID){
		return dao.findIngSurvey(surveyNo, SCID);
	}
	public SurveyDTO showSearchSurveyToSurveyNo(int surveyNo){
		return dao.showSearchSurveyToSurveyNo(surveyNo);
	}
	
	public ArrayList<SurveyGoingDTO> searchSurveyToTeacher(int surveyNo, String scid) {
		return dao.searchSurveyToTeacher(surveyNo, scid);
	}
	
	public ArrayList<SearchEndsurveyDTO> searchEndSurvey(String SCID) {
		return dao.searchEndSurvey(SCID);
	}
	
	public ArrayList<Stu_ans_ViewDTO> searchAnswer(int surNo, int ingSeq, String scid, Date start, Date end) {
		return dao.searchAnswer(surNo, ingSeq, scid, start, end);
	}
}
