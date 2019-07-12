package User.StudentController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.StudentService;
import User.UserDTO.StudentDTO;

public class SearchStudentController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String sch_code = request.getParameter("sch_code");
		String grd = request.getParameter("grade").trim();
		String num = request.getParameter("grd_num").trim();
		String sch_name = request.getParameter("sch_name");
		String sch_address = request.getParameter("sch_address");
		String tea_id = request.getParameter("tea_id");
		int grade = Integer.parseInt(grd);
		int grd_num = Integer.parseInt(num);
		
		StudentService service = StudentService.getInstance();
		ArrayList<StudentDTO> list = service.studentListAttend(tea_id, sch_code, grade, grd_num);
		
		request.setAttribute("list", list);
		request.setAttribute("sch_code", sch_code);
		request.setAttribute("grade", grade);
		request.setAttribute("grd_num", grd_num);
		request.setAttribute("sch_name", sch_name);
		request.setAttribute("sch_address", sch_address);
		
		RequestDispatcher dc =  request.getRequestDispatcher("/page_common/searchStudent.jsp");
		dc.forward(request, response);
	}

}
