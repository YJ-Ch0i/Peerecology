package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.QuestionService;
import Service.SurveyService;
import SurveyRelationDTO.QuestionOfferDTO;
import SurveyRelationDTO.SurveyGoingDTO;
import SurveyRelationDTO.SurveyManagerDTO;

public class StartSurveyController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		String sch_code = (String)session.getAttribute("sch_code");
		
		SurveyService surService = SurveyService.getInstance();
		SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO(); 
		surveyGoingDTO = surService.startSurvey(sch_code);
		if(surveyGoingDTO==null)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('진행 가능한 설문조사가 없습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		else
		{
			ArrayList<SurveyManagerDTO> questionsDTO = new ArrayList<SurveyManagerDTO>();
			questionsDTO = surService.showQuestionsToManager(surveyGoingDTO.getSurveyNo());
			surveyGoingDTO = surService.findIngSurvey(surveyGoingDTO.getSurveyNo(), sch_code);
			request.setAttribute("questionsDTO", questionsDTO);
			session.setAttribute("surveyGoingDTO", surveyGoingDTO);
			RequestDispatcher dc = request.getRequestDispatcher("/page_stu/SurveyPage.jsp");
			dc.forward(request, response);
		}
	}

}
