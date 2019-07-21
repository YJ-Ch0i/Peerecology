package Survey.AnswerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.AnswerService;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyGoingDTO;

public class RegisterAnswerController implements Controller {
 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		String strStu_id = (String)session.getAttribute("stu_id");
		int stu_id = Integer.parseInt(strStu_id);
		SurveyGoingDTO surveyGoingDTO = (SurveyGoingDTO)session.getAttribute("surveyGoingDTO");
		String[] QIDs = request.getParameterValues("QID");
		int ingSeq = surveyGoingDTO.getIngSeq();
		AnswerService answerService = AnswerService.getInstance();
		
		for(int i=0; i<QIDs.length; i++)
		{
			String[] checkDDorae = request.getParameterValues("checkDDorae"+i);
			String checkAnswer = request.getParameter("checkAnswer"+i);
			SurveyAnswerDTO surveyAnswerDTO = new SurveyAnswerDTO();
			if(checkAnswer!=null) 
			{
			surveyAnswerDTO.setAnswerValue(checkAnswer);
			surveyAnswerDTO.setIngSeq(ingSeq);
			surveyAnswerDTO.setMultiAnswer(false);
			surveyAnswerDTO.setQID(Integer.parseInt(QIDs[i]));
			surveyAnswerDTO.setSutudentID(stu_id);
			answerService.surveyAnswerRegister(surveyAnswerDTO);
			}
			else
			{
			surveyAnswerDTO.setIngSeq(ingSeq);
			surveyAnswerDTO.setMultiAnswer(true);
			surveyAnswerDTO.setQID(Integer.parseInt(QIDs[i]));
			surveyAnswerDTO.setSutudentID(stu_id);
			answerService.surveyAnswerRegister(surveyAnswerDTO);
			answerService.multiAnswerRegister(checkDDorae);
			}
			
		}
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Success!')");
		script.println("location.href='/PeerSys/index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}

}
