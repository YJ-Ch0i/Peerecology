package AjaxController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Common.CommonUtil;
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
		
		//TODO
		List<StudentScoresDTO> scoresList = new ArrayList<>();
		for(StudentDTO stuDto : attendList) {			
			double devidedScore = 0.0;
			for (int seqKey : seqMap.keySet()) {
				int isParticipation = AnswerService.getInstance().getAnswersCount(seqKey, stuDto.getStu_id());
				for(int trKey : seqMap.get(seqKey).keySet()){
					double pickedScore = 0.0;
					for(int i=0; i<seqMap.get(seqKey).get(trKey).size(); i++) {
						if(stuDto.getStu_id() == seqMap.get(seqKey).get(trKey).get(i).getMultiAnswers()) {
							pickedScore++;
						}
						else continue;
					}
					
					if(isParticipation == 0) devidedScore = pickedScore / (attendList.size());					
					else devidedScore = pickedScore / (attendList.size()-1);
					
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
	
	public static List<StudentScoresDTO> calculatePeerScoreRadar(int stid, String scid, int grade, int grdNum, String year) {
		
		//또래지명 척도에 관한 모든 점수 리스트
		List<StudentScoresDTO> list = calculatePeerScore(Common.Constant.PEERID, scid, grade, grdNum, year);
		//학생한명의 척도별 점수
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
				stuScore.setScore(dto.getScore());
				stuScore.setYear(year);
				stuScoresList.add(stuScore);
			}
			else continue;
		}
		return stuScoresList;
	}
}
