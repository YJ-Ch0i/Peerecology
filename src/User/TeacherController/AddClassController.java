package User.TeacherController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.StudentService;

public class AddClassController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();    
		String tea_id = "";
	      String stu_id="";
	      String stu_name="";	   
	      int grade = -1;
	      int grd_num = -1;
	      String student_count = "";
	      String stu_birth = "";
	      String sch_name = "";
	      String sch_code = "";
	      String sch_address = "";
	      String file_name = "";
	      int index=-1;
	      
	      Calendar currentCalendar = Calendar.getInstance();
	      String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));   //Calendar를 통한 현재시간의 연도 저장
	      
	      if(session.getAttribute("tea_id") != ""){
	         tea_id = (String) session.getAttribute("tea_id");
	      }	    
	      if(request.getParameter("sch_name") != ""){
	         sch_name = (String) request.getParameter("sch_name");
	      }
	      if(request.getParameter("sch_code") != ""){
	         sch_code = (String) request.getParameter("sch_code");
	      }
	      if(request.getParameter("grade_num") != ""){
		     grade = Integer.parseInt(request.getParameter("grade_num"));
	      }
	      if(request.getParameter("class_num") != ""){
		     grd_num = Integer.parseInt(request.getParameter("class_num"));
	      }
	      if(request.getParameter("sch_address") !=""){
	         sch_address = (String) request.getParameter("sch_address");
	      }
	      if(session.getAttribute("file_name") != ""){
	         file_name = session.getAttribute("file_name").toString();
	      }
	      
	      if(sch_code == ""){
	         //session.setAttribute("tea_id", tea_id);
	         PrintWriter script =response.getWriter();
	         script.println("<script>");
	         script.println("alert('학교를 찾아주세요.')");
	         script.println("history.back();");
	         script.println("</script>");
	         script.close();
	         return;
	      }
	      if(grade == -1){
	         //session.setAttribute("tea_id", tea_id);
	         PrintWriter script =response.getWriter();
	         script.println("<script>");
	         script.println("alert('학년을 입력 해 주세요.')");
	         script.println("history.back();");
	         script.println("</script>");
	         script.close();
	         return;
	      }
	      if(grd_num == -1){
	         //session.setAttribute("tea_id", tea_id);
	         PrintWriter script =response.getWriter();
	         script.println("<script>");
	         script.println("alert('반을 입력 해 주세요.')");
	         script.println("history.back();");
	         script.println("</script>");
	         script.close();
	         return;
	      }
	      
	      if(tea_id != "" &&file_name!="" && sch_code != "" && grade != -1 && grd_num != -1){
	    	  StudentService stuservice = StudentService.getInstance();
	    	  
	      }
	}
}
