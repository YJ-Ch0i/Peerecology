package User.StudentController;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;

public class FrontController  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		list = new HashMap<String, Controller>();
		list.put("/stuLogin.st", new StudentLoginController());
		list.put("/StudentTransfer.st", new StudentTransferController());
		list.put("/AddStudent.st", new StudentAddController());		
		list.put("/SearchGrade.st", new SearchGradeController());
		}
		
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setCharacterEncoding("euc-kr");  request.setCharacterEncoding("UTF-8");
		  String url = request.getRequestURI();
		  String contextPath = request.getContextPath();
		  String path = url.substring(contextPath.length());
		 
		  Controller subController = list.get(path);
		  subController.execute(request, response);
	}
}
