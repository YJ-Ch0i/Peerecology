package Survey.SurveyController;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Controller.Controller;
import Service.QuestionService;
import Service.StudentService;
import Service.SurveyService;
import SurveyRelationDTO.QuestionTrandManagerDTO;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;
import SurveyRelationDTO.SurveyGoingDTO;
import User.UserDTO.StudentDTO;

public class ResultViewController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String scid = request.getParameter("sch_code");
		String grade1 = request.getParameter("grade").trim();
		String grd_num1 = request.getParameter("grd_num").trim();
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String sch_name = request.getParameter("sch_name");
		String ingseq = request.getParameter("ingSeq").trim();
		String survey_no = request.getParameter("surveyNo").trim();
		
		int ingSeq = Integer.parseInt(ingseq);
		int surveyNo = Integer.parseInt(survey_no);
		int grade = Integer.parseInt(grade1);
		int grdNum = Integer.parseInt(grd_num1);
		Date start = Date.valueOf(startdate);
		Date end = Date.valueOf(enddate);
		
		SurveyService surService = SurveyService.getInstance();
		QuestionService queService = QuestionService.getInstance();
		StudentService stuService = StudentService.getInstance();
		
		ArrayList<StudentDTO> attendList = new ArrayList<>();
		// 재학생 리스트
		attendList = stuService.studentListAttend3(scid, grade, grdNum, year);
		
		ArrayList<StudentScoresDTO> scoresList = new ArrayList<>();
		//scoresList = surService.getClassesScores(ingSeq, surveyNo, scid, grade, grd_num);
		scoresList = surService.getClassesAllScores(scid, grade, grdNum, year);
		
		ArrayList<SurveyGoingDTO> calculatedSurveyList = new ArrayList<>();
		try {
			calculatedSurveyList = surService.getCalculatedClassSurveyList(grade, year);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ArrayList<QuestionTrandTypeDTO> trandList = new ArrayList<>();
		ArrayList<QuestionTrandManagerDTO> bigTrandList = new ArrayList<>();
		trandList = queService.searchTrandList(surveyNo, ingSeq, scid);
		bigTrandList = queService.searchBigTrandList(surveyNo, ingSeq, scid, start, end);
		
		Gson gson = new Gson();
		
		//해당 학년에서 설문 종료 후 계산이 완료된 설문 Json
		ArrayList<String> endSurJson = new ArrayList<>();
		JsonObject ensSurObject = new JsonObject();
		for(int i=0; i<calculatedSurveyList.size(); i++) {
			ensSurObject.addProperty("surIngSeq", calculatedSurveyList.get(i).getIngSeq());
			
			String json = gson.toJson(ensSurObject);
			endSurJson.add(json);
		}
		
		//설문에 사용된 척도들 Json
		ArrayList<String> trandJson = new ArrayList<>();
		JsonObject trandObject = new JsonObject();
		for(int i=0; i<trandList.size(); i++) {
			trandObject.addProperty("trandID", trandList.get(i).getQ_trandType());
			trandObject.addProperty("bigID", trandList.get(i).getBigTrandID());			
			trandObject.addProperty("trandDesc", trandList.get(i).getQ_trandDescipt());	
			
			String json = gson.toJson(trandObject);
			trandJson.add(json);
		}
		
		//설문에 사용된 척도분류 Json
		ArrayList<String> bigTrandJson = new ArrayList<>();
		JsonObject bigTrandObject = new JsonObject();
		for(int i=0; i<bigTrandList.size(); i++) {
			bigTrandObject.addProperty("btID", bigTrandList.get(i).getBigTrandID());
			bigTrandObject.addProperty("btDesc", bigTrandList.get(i).getDescript());			
			bigTrandObject.addProperty("explan", bigTrandList.get(i).getExplan());	
			
			String json = gson.toJson(bigTrandObject);
			bigTrandJson.add(json);
		}
				
		//모든 재학생점수들 json
		ArrayList<String> scoresJson = new ArrayList<>();
		JsonObject scoresObject = new JsonObject();
		for(int i=0; i<scoresList.size(); i++) {
			scoresObject.addProperty("surIngSeq", scoresList.get(i).getIngseq());
			scoresObject.addProperty("sID", scoresList.get(i).getStu_id());
			scoresObject.addProperty("sName", scoresList.get(i).getsName());
			scoresObject.addProperty("btID", scoresList.get(i).getBigTrandId());
			scoresObject.addProperty("btDesc", scoresList.get(i).getBigTrandDesc());
			scoresObject.addProperty("trID", scoresList.get(i).getTrandId());
			scoresObject.addProperty("trDesc", scoresList.get(i).getTrandDesc());
			scoresObject.addProperty("score", scoresList.get(i).getScore());
			scoresObject.addProperty("year", scoresList.get(i).getYear());
			
			String json = gson.toJson(scoresObject);
			scoresJson.add(json);
		}
		
		//재학생 json
		ArrayList<String> stuJson = new ArrayList<>();
		JsonObject stuObject = new JsonObject();
		for (StudentDTO dto : attendList) {
			stuObject.addProperty("sId", dto.getStu_id());
			stuObject.addProperty("name", dto.getName());
			stuObject.addProperty("scid", dto.getScid());
			stuObject.addProperty("grade", dto.getGrade());
			stuObject.addProperty("classes", dto.getGrd_num());
			stuObject.addProperty("numb", dto.getNum());

			String json = gson.toJson(stuObject);
			stuJson.add(json);
		}
		
		
		ArrayList<QuestionTrandTypeDTO> allMixedTrand = new ArrayList<>();
		ArrayList<QuestionTrandTypeDTO> allTrand = new ArrayList<>();
		allTrand = queService.showAllTrand();
		for(int j=0; j<allTrand.size(); j++) {
			for(int i=0; i<bigTrandList.size(); i++) {
				if(bigTrandList.get(i).getBigTrandID() == allTrand.get(j).getBigTrandID()) {
					QuestionTrandTypeDTO qttDTO = new QuestionTrandTypeDTO();
					qttDTO.setBigTrandID(bigTrandList.get(i).getBigTrandID());
					qttDTO.setQ_trandDescipt(allTrand.get(j).getQ_trandDescipt());
					qttDTO.setQ_trandType(allTrand.get(j).getQ_trandType());
					allMixedTrand.add(qttDTO);
				}
				else continue;
			}
		}
		ArrayList<String> mixedTrand = new ArrayList<>();
		JsonObject mixTrandObject = new JsonObject();
		for (QuestionTrandTypeDTO dto : allMixedTrand) {
			mixTrandObject.addProperty("btID", dto.getBigTrandID());
			mixTrandObject.addProperty("trID", dto.getQ_trandType());
			mixTrandObject.addProperty("trDesc", dto.getQ_trandDescipt());			

			String json = gson.toJson(mixTrandObject);
			mixedTrand.add(json);
		}
		
		request.setAttribute("scid", scid);
		request.setAttribute("ingSeq", ingSeq);
		request.setAttribute("surNo", surveyNo);
		request.setAttribute("startdate", start);
		request.setAttribute("enddate", end);
		request.setAttribute("title", title);
		request.setAttribute("sch_name", sch_name);
		request.setAttribute("grade", grade);
		request.setAttribute("grdNum", grdNum);
		request.setAttribute("year", year);
		request.setAttribute("attendList", attendList);
		request.setAttribute("trandJson", trandJson);
		request.setAttribute("bigTrandJson", bigTrandJson);
		request.setAttribute("scoresJson", scoresJson);
		request.setAttribute("stuJson", stuJson);
		request.setAttribute("bigTrandList", bigTrandList);
		request.setAttribute("mixedTrand", mixedTrand);
		request.setAttribute("endSurJson", endSurJson);
		
		RequestDispatcher dc = request.getRequestDispatcher("/page_common/classResult.jsp");
		dc.forward(request, response);
	}
}
