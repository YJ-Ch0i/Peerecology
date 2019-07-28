package Survey.QuestionController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.QuestionService;

public class BigTrandDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String[] deleteTrandBigValues = request.getParameterValues("deleteTrandBigValues");
		if(deleteTrandBigValues == null)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('check deleting question!!')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else 
		{
			QuestionService queService = QuestionService.getInstance();
			for(int i=0; i<deleteTrandBigValues.length; i++)
			{
				queService.questionDelete(Integer.parseInt(deleteTrandBigValues[i]));
			}
		}
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Success Delete!')");
		script.println("parent.document.location.reload();");
		script.println("</script>");
		script.close();
	}

}
