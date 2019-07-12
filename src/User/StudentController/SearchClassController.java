package User.StudentController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.TeacherService;
import User.UserDTO.TeacherDTO;


public class SearchClassController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String sch_code = request.getParameter("sch_code");
		String sch_name = request.getParameter("sch_name");
		String adminSearching = request.getParameter("adminSearching");
		String sch_address = request.getParameter("sch_address");
		
		boolean isElementalSchool = true;
		
		if(sch_name.contains("중학교")) isElementalSchool = false;
		
		//ClassService service = ClassService.getInstance();
		//ArrayList<String> list = service.ClassSearch(sch_code);
		//request.setAttribute("list", list);
		
		TeacherService teaService = TeacherService.getInstance();
		ArrayList<TeacherDTO> list = teaService.searchGradeUseTeacher(sch_code);
		
		request.setAttribute("list", list);
		request.setAttribute("sch_code", sch_code);
		request.setAttribute("sch_name", sch_name);
        request.setAttribute("sch_address", sch_address);
		request.setAttribute("isElementalSchool", isElementalSchool);
		request.setAttribute("adminSearching", adminSearching);
		RequestDispatcher dc =  request.getRequestDispatcher("/page_common/searchClass.jsp");
		dc.forward(request, response);
	}
}
