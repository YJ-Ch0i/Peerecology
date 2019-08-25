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
		String scid = request.getParameter("scid");
		int grade = CommonUtil.strToInt(request.getParameter("grade"));
		int gradeNo = CommonUtil.strToInt(request.getParameter("grd_num"));
		int studId = CommonUtil.strToInt(request.getParameter("stuid"));
		String registedYear = request.getParameter("year");
		
		List<StudentScoresDTO> stuScoreDtoList = SurveyService.getInstance().getStudentScores(
																				request.getParameter("scid"),
																				CommonUtil.strToInt(request.getParameter("grade")),
																				CommonUtil.strToInt(request.getParameter("grd_num")),
																				CommonUtil.strToInt(request.getParameter("stuid")),
																				request.getParameter("year"));
		Map<Integer, List<StudentScoresDTO>> stuScoreMap = new HashMap<>();
		List<Integer> seqIntArr = new ArrayList<>();
		for (StudentScoresDTO dto : stuScoreDtoList) {
			int seqInt = dto.getIngseq();
			if (seqIntArr.contains(seqInt)) {
				stuScoreMap.get(seqInt).add(dto);
			} else {
				seqIntArr.add(seqInt);
				List<StudentScoresDTO> tempList = new ArrayList<>();
				tempList.add(dto);
				stuScoreMap.put(seqInt, tempList);
			}
		}
		
		//TODO map -> json형태로 넘겨줘야됨.
		
		// jsonArray 넘김
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(
					SurveyService.getInstance().getStudentScores(
							request.getParameter("scid"),
							CommonUtil.strToInt(request.getParameter("grade")),
							CommonUtil.strToInt(request.getParameter("grd_num")),
							CommonUtil.strToInt(request.getParameter("stuid")),
							request.getParameter("year"))));
		pw.flush();
		pw.close();
	}
}
