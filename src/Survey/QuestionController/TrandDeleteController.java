package Survey.QuestionController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.QuestionService;

public class TrandDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
		String[] deleteTrandValues = request.getParameterValues("deleteTrandValues");
		
		QuestionService queSerivce = QuestionService.getInstance();
		for(int i=0; i<deleteTrandValues.length; i++)
		{
			
			String[] deleteSplitValues = deleteTrandValues[i].split("&Split");
			int deleteReuslt = queSerivce.queTrandDelete(Integer.parseInt(deleteSplitValues[0]));
			if(deleteReuslt==-1)
			{
				PrintWriter script = response.getWriter();
				script.println("<script charset=\'euc-kr\'>");
				script.println("alert('설문문항 중에 척도가 "+deleteSplitValues[1]+"에 포함 되어있습니다.')");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
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
