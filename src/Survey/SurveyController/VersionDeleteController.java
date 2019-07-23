package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.SurveyService;

public class VersionDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String[] deleteVersionValues = request.getParameterValues("deleteVersionValues");
		
		if(deleteVersionValues == null)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('check deleting version!!')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else 
		{
			SurveyService surService = SurveyService.getInstance();
			for(int i=0; i<deleteVersionValues.length; i++)
			{
				
				surService.versionDelete(Integer.parseInt(deleteVersionValues[i]));
			}
			
			
		}
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Success Delete! Press F5')");
		script.println("history.back();");
		script.println("</script>");
		script.close();
	}

}
