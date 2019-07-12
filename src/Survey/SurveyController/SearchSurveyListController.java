package Survey.SurveyController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.SurveyService;

public class SearchSurveyListController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String stu_id = request.getParameter("stu_id");
		String sch_name = request.getParameter("sch_name");
		String stu_grade = request.getParameter("stu_grade");
		String stu_grdnum = request.getParameter("stu_grdnum");
		String stu_num = request.getParameter("stu_num");
		String stu_name  = request.getParameter("stu_name"); 
		
		SurveyService surveyService = SurveyService.getInstance();
		
		//ArrayList<SurveyDTO> svList = surveyService.SurveySearchAll(class_id);
		request.setAttribute("stu_id", stu_id);
		//request.setAttribute("svList", svList);
		RequestDispatcher dc = request.getRequestDispatcher("/page_teacher/SurveyResult/findSurveyId.jsp");
		dc.forward(request, response);
		
	}

}
