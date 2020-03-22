package Survey.AnswerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.AnswerService;
import Service.SurveyService;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyGoingDTO;

public class RegisterAnswerController implements Controller {
 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 	
		SurveyService surService = SurveyService.getInstance();
		int surveyNo = (Integer) session.getAttribute("surveyNo");
		String strStu_id = (String)session.getAttribute("stu_id");
		int stu_id = Integer.parseInt(strStu_id);
		int ingSeq = (Integer)session.getAttribute("surveyIngNo");
		String[] QIDs = request.getParameterValues("QID");
		AnswerService answerService = AnswerService.getInstance();
		String strPageNumbering = request.getParameter("pageNumbering");
		int pageNumbering = Integer.parseInt(strPageNumbering); // 1페이지 일때는 10문항 2페이지 일때는 20문항.
		
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
			if(checkDDorae!=null) 
			{
			answerService.multiAnswerRegister(checkDDorae);
			}
			}
			
		}
		
		
		strPageNumbering = request.getParameter("pageNumbering");
		pageNumbering = Integer.parseInt(strPageNumbering);
		
		if(surService.nextPage(surveyNo, strPageNumbering)) 
		{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='/PeerSys/page_stu/SurveyPage.jsp?pageNumber="+(pageNumbering+1) +"'");
		script.println("</script>");
		script.close();
		return;
		}
		else 
		{
			session.removeAttribute("stu_id");
			session.removeAttribute("stu_desc");
			session.removeAttribute("grd_num");
			session.removeAttribute("sch_code");
			session.removeAttribute("stu_num");
			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('수고하셨습니다.');");
			script.println("location.href='/PeerSys/page_stu/complete.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
	}
}
