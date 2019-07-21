package Survey.QuestionController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.QuestionService;

public class QuestionDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] deleteQuestion = request.getParameterValues("deleteQuestion");
		
		if(deleteQuestion == null)
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
			for(int i=0; i<deleteQuestion.length; i++)
			{
				
				int deleteReuslt = queService.questionDelete(Integer.parseInt(deleteQuestion[i]));
				if(deleteReuslt==-1)
				{
			String deleteQuestionTitle = request.getParameter("deleteQuestion_title"+Integer.parseInt(deleteQuestion[i]));
			PrintWriter script = response.getWriter();
			script.println("<script charset=\'euc-kr\'>");
			script.println("alert('설문중에 문항 "+deleteQuestionTitle+"가 포함 되어있습니다.')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
				}
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
