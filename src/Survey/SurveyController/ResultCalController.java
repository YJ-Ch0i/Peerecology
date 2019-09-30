package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.CommonUtil;
import Common.Constant;
import Controller.Controller;
import Service.AnswerService;
import Service.QuestionService;
import Service.StudentService;
import Service.SurveyService;
import SurveyRelationDTO.AllDescQuestionDTO;
import SurveyRelationDTO.QuestionTrandManagerDTO;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.QuestionTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import User.UserDTO.StudentDTO;

public class ResultCalController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		/**
		 * surNo --> 설문 고유ID
		 * ingSeq --> 설문회차 고유ID
		 * scid --> 학교 고유ID
		 * startdate --> 설문회차 시작일
		 * enddate --> 설문회차 종료일
		 * year --> 선택한 학급의 등록연도
		 * grade --> 선택한 학년
		 */
		int surNo = CommonUtil.strToInt(request.getParameter("surveyNo"));
		int ingSeq = CommonUtil.strToInt(request.getParameter("surNo"));
		String scid = request.getParameter("sch_code");
		Date startdate = Date.valueOf(request.getParameter("startdate"));
		Date enddate = Date.valueOf(request.getParameter("enddate"));		
		String year = request.getParameter("year");		
		int grade = CommonUtil.strToInt(request.getParameter("grade"));
		
		
		
		
	}
}
