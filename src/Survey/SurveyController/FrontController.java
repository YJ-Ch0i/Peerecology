package Survey.SurveyController;

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
		list.put("/versionRegister.sv", new VersionAddController());
		list.put("/searchSurveyList.sv", new SearchSurveyListController());
		list.put("/goingSurvey.sv", new GoingVersionController());
		list.put("/startSurvey.sv", new StartSurveyController());
		list.put("/resultCalculate.sv", new ResultCalculateController());
		list.put("/resultTeacher.sv", new PersonalResultController());
		list.put("/versionDelete.sv", new VersionDeleteController());
		list.put("/testResult.sv", new TestResultController());
		list.put("/viewResult.sv", new ResultViewController());
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
