package User.StudentController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.StudentService;
import Service.TeacherService;
import User.UserDTO.StudentDTO;
import User.UserDTO.TeacherDTO;

public class StudentAddController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		TeacherService teaService = TeacherService.getInstance();
		StudentService stuService = StudentService.getInstance();
		
		int num = 0; int grade = 0; int grd_num = 0;
		int gender = 0; String strGender = "";
		String name = ""; String tea_id = "";
		
		if(request.getParameter("grade_num") != "") {
			grade = Integer.parseInt(request.getParameter("grade_num"));
		}
		if(request.getParameter("class_num") != "") {
			grd_num = Integer.parseInt(request.getParameter("class_num"));
		}
		if(request.getParameter("stu_num") != "") {
			num = Integer.parseInt(request.getParameter("stu_num"));
		}
		if(request.getParameter("stu_gender") != "") {
			strGender = request.getParameter("stu_gender"); 
		}
		if(request.getParameter("stu_name") != "") {
			name = request.getParameter("stu_name");
		}
		
		if(strGender.equals("남자") || strGender.equals("남")){
			gender = 1;
		}
		else if(strGender.equals("여자") || strGender.equals("여") ) {
			gender = 2;
		}
		
		Calendar calendar = new GregorianCalendar();		//날짜 객체 생성을 위한 calendar
		Date date = new Date(calendar.getTimeInMillis()); //현재의 날짜 객체
		
		tea_id = (String) session.getAttribute("tea_id");
		TeacherDTO t = teaService.teacherInfo(tea_id);
		
		if(num != 0 && gender != 0 && name != "") {
			StudentDTO student = new StudentDTO(name, t.getSCID(), grade, grd_num, num, tea_id, gender, date);
			boolean stuRegist = stuService.studentRegist(student);
			
			if(stuRegist == true) {
				PrintWriter script =response.getWriter();
				script.println("<script>");
				script.println("alert('Student ADD Complete!!')");
				script.println("location.href='/PeerSys/page_tea/ClassManagement/manageMentClass.jsp'");
				script.println("</script>");
				script.close();
				return;
			}
			else if(stuRegist == false) {
				PrintWriter script =response.getWriter();
				script.println("<script>");
				script.println("alert('Student ADD Failed....')");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			}
		}
	}
}
