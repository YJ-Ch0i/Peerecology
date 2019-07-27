package User.TeacherController;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;


public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		list = new HashMap<String, Controller>();
		list.put("/teacherRegister.tc", new TeacherRegistController());
		list.put("/emailSendAction.tc", new EmailSendController());
		list.put("/teacherLogin.tc", new TeacherLoginController());
		list.put("/teacherLogout.tc", new TeacherLogOutController());
		list.put("/emailCheckAction.tc", new EmailCheckController());
		list.put("/classAdd.tc", new AddClassController());
		}
		
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String url = request.getRequestURI();
	    String contextPath = request.getContextPath();
	    String path = url.substring(contextPath.length());
		 
	    Controller subController = list.get(path);
	    subController.execute(request, response);
	}
}