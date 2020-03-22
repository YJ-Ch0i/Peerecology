package Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.*;

import SurveyRelationDAO.SurveyDAO;
import SurveyRelationDTO.Stu_ans_ViewDTO;
import SurveyRelationDTO.StudentScoresDTO;
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
	public ArrayList<SurveyManagerDTO> pagingShowQuestionsToManager(int surveyNo, String pageNumber){
		return dao.pagingShowQuestionsToManager(surveyNo, pageNumber);
	}
	public ArrayList<SurveyManagerDTO> showQuestionsToManager(int surveyNo){
		return dao.showQuestionsToManager(surveyNo);
	}
	public int getSurveyNo(int surIngNo){
		return dao.getSurveyNo(surIngNo);
	}
	public int getQuestionCount(int surveyNo) {
		return dao.getQuestionCount(surveyNo);
	}
	public boolean nextPage(int surveyNo, String pageNumber){
		return dao.nextPage(surveyNo, pageNumber);
	}
	public ArrayList<SurveyDTO> showSearchSurveys(String version_title){
		return dao.showSearchSurveys(version_title);
	}
	public void goingVersionRegister(String[] SCIDs, int surveyNo, int grade, String startDate, String endDate) {
		dao.goingVersionRegister(SCIDs, surveyNo, grade, startDate, endDate);
	}
	public ArrayList<SurveyGoingDTO> showAllGoingSurveys() throws ParseException{
		return dao.showAllGoingSurveys();
	}
	public ArrayList<SurveyGoingDTO> showAllResultSurvey() throws ParseException{
		return dao.showAllResultSurvey();
	}
	public ArrayList<SurveyGoingDTO> showUncalculatedSurvey() throws ParseException{
		return dao.showUncalculatedSurvey();
	}
	public ArrayList<SurveyGoingDTO> showUncalculatedSurvey(String SCID_name) throws ParseException{
		return dao.showUncalculatedSurvey(SCID_name);
	}
	
	public ArrayList<SurveyGoingDTO> showCalculatedSurvey() throws ParseException{
		return dao.showCalculatedSurvey();
	}
	
	public boolean calculateSurvey(StudentScoresDTO dto) {
		return dao.calculateSurvey(dto);
	}
	public void calculateUpdate(int ingSeq, int survey_no) {
		dao.calculateUpdate(ingSeq, survey_no);
	}
	
	public ArrayList<SurveyGoingDTO> showResultSurvey(String SCID_name) throws ParseException{
		return dao.showResultSurvey(SCID_name);
	}
	public SurveyGoingDTO startSurvey(String SCID, String grade){
		return dao.startSurvey(SCID, grade);
	}
	public SurveyGoingDTO findIngSurvey(int surveyNo, String SCID){
		return dao.findIngSurvey(surveyNo, SCID);
	}
	
	public int showSearchSurveyToIngseq(int ingseq) {
		return dao.showSearchSurveyToIngseq(ingseq);
	}
	
	public SurveyDTO showSearchSurveyToSurveyNo(int surveyNo){
		return dao.showSearchSurveyToSurveyNo(surveyNo);
	}
	
	public ArrayList<SurveyGoingDTO> searchSurveyToTeacher(int surveyNo, String scid) {
		return dao.searchSurveyToTeacher(surveyNo, scid);
	}
	
	public ArrayList<SearchEndsurveyDTO> searchEndSurvey(String SCID, int grade) {
		return dao.searchEndSurvey(SCID, grade);
	}
	
	public ArrayList<SearchEndsurveyDTO> searchEndSurveyToYear(String scid, String year) {
		return dao.searchEndSurveyToYear(scid, year);
	}
	
	public SearchEndsurveyDTO searchEndSurveyToDate(String scid, String date) {
		return dao.searchEndSurveyToDate(scid, date);
	}
	
	public ArrayList<Stu_ans_ViewDTO> searchAnswer(int surNo, int ingSeq, String scid, Date start, Date end) {
		return dao.searchAnswer(surNo, ingSeq, scid, start, end);
	}
	
	public ArrayList<StudentScoresDTO> getClassesScores(int ingSeq, int surveyNo, String SCID, int grade, int grd_num) {
		return dao.getClassesScores(ingSeq, surveyNo, SCID, grade, grd_num);
	}
	
	public ArrayList<StudentScoresDTO> getClassesAllScores(String SCID, int grade, int grd_num, String year) {
		return dao.getClassesAllScores(SCID, grade, grd_num, year);
	}
	
	public ArrayList<StudentScoresDTO> getStudentScoresInAllSeq(String SCID, int surNo, int grade, int grd_num, int btid, String year) {
		return dao.getStudentScoresInAllSeq(SCID, surNo, grade, grd_num, btid, year);
	}
	
	public ArrayList<SurveyGoingDTO> getCalculatedClassSurveyList(String scid, int grade, String year){
		return dao.getCalculatedClassSurveyList(scid, grade, year);
	}
	
	public ArrayList<SurveyGoingDTO> getCalculatedSurveyListInClass(String scid, int grade, int surNo, String year){
		return dao.getCalculatedSurveyListInClass(scid, grade, surNo, year);
	}
	
	public ArrayList<StudentScoresDTO> getStudentScores(String SCID, int grade, int grd_num, int stuid, String year) {
		return dao.getStudentScores(SCID, grade, grd_num, stuid, year);
	}
	public ArrayList<StudentScoresDTO> getStudentScoresInSeq(String SCID, int grade, int grd_num, int stuid, String year, int seq) {
		return dao.getStudentScoresInSeq(SCID, grade, grd_num, stuid, year, seq);
	}
	public boolean deleteSurveyGoing(int ingSeq) {
		return dao.deleteSurveyGoing(ingSeq);
	}
}
