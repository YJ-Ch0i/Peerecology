package User.StudentController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.StudentService;
import User.UserDTO.StudentDTO;

public class StudentLoginController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String stu_id = request.getParameter("stu_id");
		String grade = request.getParameter("grade");
		String grd_num = request.getParameter("grd_num");
		String sch_name = request.getParameter("sch_name");
		String stu_name = request.getParameter("stu_name");
		String sch_code = request.getParameter("sch_code");
		String stu_num = request.getParameter("stu_num");
		
		StudentService stuService = StudentService.getInstance();
		ArrayList<StudentDTO> OtherStuList = new ArrayList<StudentDTO>();
		OtherStuList = stuService.findStudentToGradeSCID(Integer.parseInt(stu_id),sch_code, Integer.parseInt(grade), Integer.parseInt(grd_num));
		
		session.setAttribute("stu_id", stu_id);
		session.setAttribute("OtherStuList", OtherStuList);
		session.setAttribute("stu_desc", sch_name +" "+grade + "학년 " + grd_num + "반 " + stu_name);
		session.setAttribute("sch_code", sch_code);
		session.setAttribute("grade", grade);
		session.setAttribute("stu_num", stu_num);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인 되었습니다.');");
		script.println("location.href='/PeerSys/index.jsp';");
		script.println("</script>");
		script.close();
	}
}
