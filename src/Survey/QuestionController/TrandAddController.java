package Survey.QuestionController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.QuestionService;

public class TrandAddController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String trand_title = request.getParameter("trand_title");
		String trandBigName = request.getParameter("trandBigName");
		String bigTrandExplan = request.getParameter("bigTrandExplan");
		String bigTrandTitle = null;
		QuestionService queService = QuestionService.getInstance();
		if(trand_title.equals("")||trand_title.equals(" "))
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Input title Please!!')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		// 직접 입력했을때
		if(trandBigName.equals("0"))
		{
			bigTrandTitle = request.getParameter("bigTrandTitle");
			if(bigTrandTitle.equals("")||bigTrandTitle.equals(" "))
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
				int isSuccess = queService.trandManagerRegister(bigTrandTitle, bigTrandExplan);
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
					queService.queTrandRegister(trand_title);
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
		else {
				queService.queTrandDifferentRegister(trand_title,Integer.parseInt(trandBigName));
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
