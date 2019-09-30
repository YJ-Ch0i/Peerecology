package AjaxController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Common.CommonUtil;
import Controller.Controller;
import Service.SurveyService;
import SurveyRelationDTO.StudentScoresDTO;

/**
 * 학생별 점수 조회 컨트롤러
 * 
 * @class StudentLoadController.java
 * @author yeong
 * @since 2019-08-24
 */
public class StudentLoadController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<StudentScoresDTO> stuScoreDtoList = SurveyService.getInstance().getStudentScores(
																				request.getParameter("scid"),
																				CommonUtil.strToInt(request.getParameter("grade")),
																				CommonUtil.strToInt(request.getParameter("grd_num")),
																				CommonUtil.strToInt(request.getParameter("stuid")),
																				request.getParameter("year"));
		
		List<StudentScoresDTO> list = PeerScoreCalculate.calculatePeerScoreRadar(CommonUtil.strToInt(request.getParameter("stuid")),
																						request.getParameter("scid"),
																						CommonUtil.strToInt(request.getParameter("grade")),
																						CommonUtil.strToInt(request.getParameter("grd_num")),
																						request.getParameter("year"),
																						CommonUtil.strToInt(request.getParameter("surNo")));
		
				
		Gson gson = new Gson();
		//두개의 JsonArray를 받을 List
		List<String> personalScore = new ArrayList<>(); 
		
		personalScore.add(gson.toJson(list));	//0번은 또래지명점수
		personalScore.add(gson.toJson(stuScoreDtoList));	//1번은 전체점수
		
		// jsonArray 넘김
		PrintWriter pw = response.getWriter();
		pw.print(personalScore);
		pw.flush();
		pw.close();
	}
}
