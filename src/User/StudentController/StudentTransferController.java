package User.StudentController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.StudentService;
import Service.TeacherService;
import User.UserDTO.StudentDTO;
import User.UserDTO.TeacherDTO;

public class StudentTransferController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession();
		TeacherService teaService = TeacherService.getInstance();
		StudentService stuService = StudentService.getInstance();

		String stu_name = "";
		String tea_id = "";
		
		if(request.getParameter("stu_name") != "") {
			stu_name = request.getParameter("stu_name");
		}
		if(session.getAttribute("tea_id") != ""){
			tea_id = (String) session.getAttribute("tea_id");
		}
		
		TeacherDTO teacher = teaService.teacherInfo(tea_id);
		StudentDTO student = stuService.getStudent(stu_name, tea_id, teacher.getSCID());
		boolean stuTransf = stuService.studentTransfer(student);
		
		if(stuTransf == true) {
			PrintWriter script =response.getWriter();
			script.println("<script>");
			script.println("alert('Student Transfer Complete!!')");
			script.println("location.href='/PeerSys/page_tea/ClassManagement/manageMentClass.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
		else if(stuTransf == false) {
			PrintWriter script =response.getWriter();
			script.println("<script>");
			script.println("alert('Transfer Failed....')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
	}

}
