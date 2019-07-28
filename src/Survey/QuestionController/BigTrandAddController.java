package Survey.QuestionController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.QuestionService;

public class BigTrandAddController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bigTrandTitle = request.getParameter("bigTrandTitle");	
		QuestionService queService = QuestionService.getInstance();
		
		if(bigTrandTitle.equals("")||bigTrandTitle.equals(" ")||bigTrandTitle==null)
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
			int isSuccess = queService.trandManagerRegister(bigTrandTitle);
			if(isSuccess==-1)
			{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('척도 분류 이름이 같은 것이 이미 존재합니다.')");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			}
			else
			{
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

}
