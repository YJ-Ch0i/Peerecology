package User.TeacherController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.TeacherService;
import User.UserDTO.TeacherDTO;

public class TeacherRegistController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession(); 	 String t_name="";
		String t_mail = ""; String t_pass = ""; String t_rePass = "";
		
		if(request.getParameter("tea_id") !=""){
			t_mail = request.getParameter("tea_id");
		}
		if(request.getParameter("tea_pass") !=""){
			t_pass = request.getParameter("tea_pass");
		}
		if(request.getParameter("re-pass") !=""){
			t_rePass = request.getParameter("re-pass");
		}
		if(request.getParameter("tea_name") !=""){
			t_name = request.getParameter("tea_name");
		}

		Calendar currentCalendar = Calendar.getInstance();
		String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
		TeacherService service = TeacherService.getInstance();
		boolean isRegist = service.isUserRegist(t_mail);

		if(isRegist==true&&t_pass.equals(t_rePass)){
			
		TeacherDTO teacher = new TeacherDTO(t_mail, t_pass, t_name, false , strYear);
			
			int result = service.teacherRegister(teacher);
			if(result==1){
				session.setAttribute("tea_id", t_mail);
				response.sendRedirect("/PeerSys/emailSendAction.tc");
			}
		}
		else if(isRegist==false)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('This Email is Registed!!')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else if(!t_pass.equals(t_rePass)) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Please Password & rePassword Check!!!')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
	}
}
