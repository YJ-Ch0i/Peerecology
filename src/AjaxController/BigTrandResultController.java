package AjaxController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.CommonUtil;
import Common.Constant;
import Controller.Controller;
import Service.QuestionService;
import Service.SurveyService;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;
import SurveyRelationDTO.SurveyGoingDTO;

public class BigTrandResultController implements Controller {
	
	Gson gson = new Gson();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int btid = CommonUtil.strToInt(request.getParameter("btid"));
		int seq = CommonUtil.strToInt(request.getParameter("seq"));
		String scid = request.getParameter("scid");
		int grade = CommonUtil.strToInt(request.getParameter("grade"));
		int grdNum = CommonUtil.strToInt(request.getParameter("grdNum"));
		String year = request.getParameter("year");
		int surNo = CommonUtil.strToInt(request.getParameter("surNo"));
		PrintWriter pw = response.getWriter();		
		
		//선택한 회차의 설문지랑 같은 설문을 사용한 설문회차를 모두 가져옴
		ArrayList<SurveyGoingDTO> calculatedSurveyList = SurveyService.getInstance().getCalculatedSurveyListInClass(scid, grade, surNo, year);
		
		//설문 내 척도분류별 척도리스트
		List<String> trandJson = getTrandListInSurvey(surNo, seq, scid, btid);
		
		String endSurJson = gson.toJson(calculatedSurveyList);
		List<List<String>> trandScoreList = new ArrayList<>();
		trandScoreList.add(trandJson);
		
		if(CommonUtil.isNotNullString(scid) && grade != 0 && grdNum != 0 && seq != 0 && surNo != 0 && CommonUtil.isNotNullString(year)) {
			//모두 빈값이 아닐 때
			List<String> scoresArray = new ArrayList<>();
			if(btid == Constant.PEERID) {	//또래지명일 떄
				
				List<StudentScoresDTO> list = PeerScoreCalculate.calculatePeerScore(btid, scid, grade, grdNum, year, surNo);
				
				for(StudentScoresDTO dto : list) {
					JsonObject obj = new JsonObject();
					obj.addProperty("sID", dto.getStu_id());
					obj.addProperty("sName", dto.getsName());
					obj.addProperty("surIngSeq", dto.getIngseq());
					obj.addProperty("trID", dto.getTrandId());
					obj.addProperty("score", dto.getScore());
					
					String json = gson.toJson(obj);
					scoresArray.add(json);
				}
				trandScoreList.add(scoresArray);
				pw.print(trandScoreList);
			}
			else {	//또래지명이 아닐 때
				//학교 학년 반에 해당하는 확인할 설문이 사용된 회차 및 점수를 가져옴
				ArrayList<StudentScoresDTO> scoreList = SurveyService.getInstance().getStudentScoresInAllSeq(scid,
																											surNo,
																											grade,
																											grdNum,
																											btid,
																											year);

				for(StudentScoresDTO dto : scoreList) {
					JsonObject obj = new JsonObject();
					obj.addProperty("sID", dto.getStu_id());
					obj.addProperty("sName", dto.getsName());
					obj.addProperty("surIngSeq", dto.getIngseq());
					obj.addProperty("btId", dto.getBigTrandId());
					obj.addProperty("trID", dto.getTrandId());
					obj.addProperty("score", dto.getScore());
					
					String json = gson.toJson(obj);
					scoresArray.add(json);
				}
				trandScoreList.add(scoresArray);
				pw.print(trandScoreList);
			}
		}
		pw.flush();
		pw.close();
	}
	
	/**
	 * 설문 내 척도분류별 척도 리스트 반환 메소드
	 * @param btid
	 * @return 
	 */
	public List<String> getTrandListInSurvey(int surNo, int seq, String scid, int btid) {
				
		ArrayList<QuestionTrandTypeDTO> tList = new ArrayList<>();		
		tList = QuestionService.getInstance().getTrandListToBigT(surNo, scid, btid);	
		
		List<String> trandJson = new ArrayList<>();;
		
		//척도분류에 따른 척도의 jsonArray 생성
		for(int i=0; i<tList.size(); i++) {
			JsonObject obj = new JsonObject();
			obj.addProperty("trandID", tList.get(i).getQ_trandType());
			obj.addProperty("trandDesc", tList.get(i).getQ_trandDescipt());
			
			String json = gson.toJson(obj);
			trandJson.add(json);
		}
		
		return trandJson;
	}
}
