package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.SurveyService;

public class VersionAddController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String version_title = request.getParameter("version_title");
		if(version_title==null)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Input title Please!!')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else 
		{
			String[] QIDs = request.getParameterValues("questionId");
			if(QIDs == null)
			{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('Input question Please!!')");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			}
			else
			{
				SurveyService surveyService = SurveyService.getInstance();
				surveyService.versionTitleRegister(version_title);
				surveyService.version_QuestionRegister(QIDs);
				
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('Complete Register!!')");
				script.println("location.href='/PeerSys/page_adm/SurveyManagement/formSurvey.jsp';");
				script.println("</script>");
				script.close();
				return;
				
			}
		}
	}

}
