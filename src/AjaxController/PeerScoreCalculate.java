package AjaxController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Common.CommonUtil;
import Common.Constant;
import Service.AnswerService;
import Service.QuestionService;
import Service.StudentService;
import Service.SurveyService;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyGoingDTO;
import User.UserDTO.StudentDTO;

/**
 * 또래지명 점수 계산 클래스
 * @class PeerScoreCalculate.java
 * @author yeong
 */
public class PeerScoreCalculate {

	/**
	 * 해당연도 모든 척도별 개인 또래지명 점수계산
	 * @param btid
	 * @param scid
	 * @param grade
	 * @param grdNum
	 * @param year
	 * @return
	 */
	public static List<StudentScoresDTO> calculatePeerScore(int btid, String scid, int grade, int grdNum, String year) {
		
		//해당년도에 완료된 설문조사 목록
		List<SurveyGoingDTO> calculatedSurveyList = SurveyService.getInstance().getCalculatedClassSurveyList(scid, grade, year);
		//선택한 척도분류에 해당하는 척도들 리스트
		List<QuestionTrandTypeDTO> tList = QuestionService.getInstance().getTrandToBigTID(btid);
		//재학생 리스트
		List<StudentDTO> attendList = StudentService.getInstance().studentListAttend3(scid, grade, grdNum, year);
		
		//List<List<SurveyAnswerDTO>> multiAnswerListInSeq = new ArrayList<>();
		Map<Integer, Map<Integer, List<SurveyAnswerDTO>>> seqMap = new HashMap<>();
		
		
		for(SurveyGoingDTO seqDto : calculatedSurveyList) {
			Map<Integer, List<SurveyAnswerDTO>> trMap = new HashMap<>();
			for(QuestionTrandTypeDTO trDto : tList) {				
				//또래지명척도와 설문회차에 해당하는 또래지명 응답들의 리스트
				List<SurveyAnswerDTO> multiAnswerList = AnswerService.getInstance().getMultiAnswersInSeq(
																						trDto.getQ_trandType(), 
																						seqDto.getIngSeq(), 
																						scid, grade, grdNum);
				trMap.put(trDto.getQ_trandType(), multiAnswerList);
			}
			seqMap.put(seqDto.getIngSeq(), trMap);			
		}		
		
		List<StudentScoresDTO> scoresList = new ArrayList<>();
		for(StudentDTO stuDto : attendList) {	
			double devidedPickedScoreQuestion = 0.0;
			double devidedScore = 0.0;
			for (int seqKey : seqMap.keySet()) {
				int surveyNo = SurveyService.getInstance().showSearchSurveyToIngseq(seqKey);
				int isParticipation = AnswerService.getInstance().getAnswersCount(seqKey, stuDto.getStu_id());
				for(int trKey : seqMap.get(seqKey).keySet()){
					double pickedScore = 0.0;
					for(int i=0; i<seqMap.get(seqKey).get(trKey).size(); i++) {
						if(stuDto.getStu_id() == seqMap.get(seqKey).get(trKey).get(i).getMultiAnswers()) {
							pickedScore = pickedScore+1;
						}
						else continue;
					}
					int queCount = QuestionService.getInstance().countOfTrandQuestion(surveyNo, trKey);
					devidedPickedScoreQuestion = pickedScore / queCount;
					if(isParticipation == 0) devidedScore = devidedPickedScoreQuestion / (attendList.size());					
					else devidedScore = devidedPickedScoreQuestion / (attendList.size()-1);
					
					StudentScoresDTO stuScore = new StudentScoresDTO();
					stuScore.setIngseq(seqKey);					
					stuScore.setTrandId(trKey);
					stuScore.setStu_id(stuDto.getStu_id());
					stuScore.setsName(stuDto.getName());
					stuScore.setScore(devidedScore);
					stuScore.setSCID(scid);
					stuScore.setGrade(grade);
					stuScore.setGrd_num(grdNum);
					scoresList.add(stuScore);					
				}
			}
		}
		return scoresList;
	}
	
	/**
	 * 문항별 개인의 또래지명 점수
	 * @param seq
	 * @param multiAnswers
	 * @param attendList
	 * @return
	 */
	public static List<StudentScoresDTO> calculateQuestionsPeerScore(int seq, List<SurveyAnswerDTO> multiAnswers, List<StudentDTO> attendList) {			
		
		List<StudentScoresDTO> scoresList = new ArrayList<>();
		for(StudentDTO stuDto : attendList) {				
			double devidedScore = 0.0;
			double pickedScore = 0.0;
						
			int isParticipation = AnswerService.getInstance().getAnswersCount(seq, stuDto.getStu_id());
			for(SurveyAnswerDTO ansKey : multiAnswers){
				if(stuDto.getStu_id() == ansKey.getMultiAnswers()) {
					pickedScore = pickedScore+1;
				}
				else continue;
			}
				
			if(isParticipation == 0) devidedScore = pickedScore / (attendList.size());					
			else devidedScore = pickedScore / (attendList.size()-1);
			
			StudentScoresDTO stuScore = new StudentScoresDTO();
			stuScore.setIngseq(seq);									
			stuScore.setStu_id(stuDto.getStu_id());
			stuScore.setsName(stuDto.getName());
			stuScore.setScore(devidedScore);
			stuScore.setSCID(stuDto.getScid());
			stuScore.setGrade(stuDto.getGrade());
			stuScore.setGrd_num(stuDto.getGrd_num());
			scoresList.add(stuScore);
		}
		return scoresList;
	}
	
	
	/**
	 * 개인 또래지명 점수계산 메소드
	 * @param btid
	 * @param scid
	 * @param grade
	 * @param grdNum
	 * @param year
	 * @param seq
	 */
	public static List<StudentScoresDTO> calculatePrivatePeerScore(int btid, String scid, int grade, int grdNum, String year, int seq) {
			
		//선택한 척도분류에 해당하는 척도들 리스트
		List<QuestionTrandTypeDTO> tList = QuestionService.getInstance().getTrandToBigTID(btid);
		//재학생 리스트
		List<StudentDTO> attendList = StudentService.getInstance().studentListAttend3(scid, grade, grdNum, year);
		
		//List<List<SurveyAnswerDTO>> multiAnswerListInSeq = new ArrayList<>();
		Map<Integer, Map<Integer, List<SurveyAnswerDTO>>> seqMap = new HashMap<>();
		
		Map<Integer, List<SurveyAnswerDTO>> trMap = new HashMap<>();
		for(QuestionTrandTypeDTO trDto : tList) {				
			//또래지명척도와 설문회차에 해당하는 또래지명 응답들의 리스트
			List<SurveyAnswerDTO> multiAnswerList = AnswerService.getInstance().getMultiAnswersInSeq(
																					trDto.getQ_trandType(), 
																					seq, scid, grade, grdNum);
			trMap.put(trDto.getQ_trandType(), multiAnswerList);
		}
		seqMap.put(seq, trMap);			
				
		List<StudentScoresDTO> scoresList = new ArrayList<>();
		for(StudentDTO stuDto : attendList) {	
			double devidedPickedScoreQuestion = 0.0;
			double devidedScore = 0.0;
			for (int seqKey : seqMap.keySet()) {
				//회차에서 사용한 설문조사
				int surveyNo = SurveyService.getInstance().showSearchSurveyToIngseq(seqKey);
				//학생의 설문회차에 대한 참여 여부
				int isParticipation = AnswerService.getInstance().getAnswersCount(seqKey, stuDto.getStu_id());
				
				//설문회차에 해당하는 map을 토대로 조사한 척도를를 가져옴
				for(int trKey : seqMap.get(seqKey).keySet()){
					double pickedScore = 0.0;
					for(int i=0; i<seqMap.get(seqKey).get(trKey).size(); i++) {
						if(stuDto.getStu_id() == seqMap.get(seqKey).get(trKey).get(i).getMultiAnswers()) {
							pickedScore = pickedScore+1;
						}
						else continue;
					}
					
					//또래지명에 해당하는 척도를 가져온다.
					int queCount = QuestionService.getInstance().countOfTrandQuestion(surveyNo, trKey);
					devidedPickedScoreQuestion = pickedScore / queCount;
					if(isParticipation == 0) devidedScore = devidedPickedScoreQuestion / (attendList.size());					
					else devidedScore = devidedPickedScoreQuestion / (attendList.size()-1);
					
					StudentScoresDTO stuScore = new StudentScoresDTO();
					stuScore.setIngseq(seqKey);					
					stuScore.setTrandId(trKey);
					stuScore.setStu_id(stuDto.getStu_id());
					stuScore.setsName(stuDto.getName());
					stuScore.setScore(devidedScore);
					stuScore.setSCID(scid);
					stuScore.setGrade(grade);
					stuScore.setGrd_num(grdNum);
					scoresList.add(stuScore);					
				}
			}
		}
		return scoresList;
	}
	
	/**
	 * 엑셀 파싱을 위한 척도별 또래지명 점수 리스트
	 * @param stid
	 * @param scid
	 * @param grade
	 * @param grdNum
	 * @param year
	 * @param seq
	 * @return
	 */
	public static List<StudentScoresDTO> calculatePeerScoreExcel(int stid, String scid, int grade, int grdNum, String year, int seq) {
		
		//또래지명 척도에 관한 해당회차 점수 리스트
		List<StudentScoresDTO> list = calculatePrivatePeerScore(Common.Constant.PEERID, scid, grade, grdNum, year, seq);
		//학생한명의 척도별 점수
		List<StudentScoresDTO> stuScoresList = savePeerScore(list, stid, scid, grade, grdNum, year);

		return stuScoresList;
	}
	
	/**
	 * 레이더차트를 위한 척도별 또래지명점수 리스트
	 * @param stid
	 * @param scid
	 * @param grade
	 * @param grdNum
	 * @param year
	 * @return
	 */
	public static List<StudentScoresDTO> calculatePeerScoreRadar(int stid, String scid, int grade, int grdNum, String year) {
		
		//또래지명 척도에 관한 모든 점수 리스트
		List<StudentScoresDTO> list = calculatePeerScore(Common.Constant.PEERID, scid, grade, grdNum, year);
		//학생한명의 척도별 점수
		List<StudentScoresDTO> stuScoresList = savePeerScore(list, stid, scid, grade, grdNum, year);

		return stuScoresList;
	}
	
	protected static List<StudentScoresDTO> savePeerScore(List<StudentScoresDTO> list, int stid, String scid, int grade, int grdNum, String year) {
		
		List<StudentScoresDTO> stuScoresList = new ArrayList<>();
		
		for(StudentScoresDTO dto : list) {
			if(dto.getStu_id() == stid) {
				StudentScoresDTO stuScore = new StudentScoresDTO();
				stuScore.setIngseq(dto.getIngseq());
				stuScore.setBigTrandId(Common.Constant.PEERID);
				stuScore.setTrandId(dto.getTrandId());
				stuScore.setSCID(scid);
				stuScore.setGrade(grade);
				stuScore.setGrd_num(grdNum);
				stuScore.setStu_id(dto.getStu_id());
				stuScore.setsName(dto.getsName());
				
				if(Double.isNaN(dto.getScore())) stuScore.setScore(0);
				else stuScore.setScore(dto.getScore());
				
				stuScore.setYear(year);
				stuScoresList.add(stuScore);
			}
			else continue;
		}
		return stuScoresList;
	}
}
