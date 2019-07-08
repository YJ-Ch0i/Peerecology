package Survey.QuestionController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.QuestionService;

public class TypeAddController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type_title = request.getParameter("type_title");
		if(type_title=="")
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
			QuestionService queService = QuestionService.getInstance();
			queService.queTypeRegister(type_title);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Success!')");
			script.println("parent.document.location.reload();");
			script.println("</script>");
			script.close();
			return;
		}
	}

}
