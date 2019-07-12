package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.SurveyService;

public class GoingVersionController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] strHiddenSchs = request.getParameterValues("schools");
		String strCheckSurveyNo = request.getParameter("checkSurvey");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		// 체크하지 않았을 경우.
		if(strCheckSurveyNo==null)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Check Survey!!')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else if(startDate == "" || endDate == "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Set startDate and endDate!!')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else 
		{
			int checkSurveyNo = Integer.parseInt(strCheckSurveyNo);
			
			SurveyService surService = SurveyService.getInstance();
			surService.goingVersionRegister(strHiddenSchs, checkSurveyNo, startDate, endDate);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Complete Register!!')");
			script.println("parent.document.location.href='/PeerSys/page_adm/SurveyStarting/goingSurvey.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
	}

}
