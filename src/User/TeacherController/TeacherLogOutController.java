package User.TeacherController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;

public class TeacherLogOutController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		
		if(session.getAttribute("tea_id")==null) 
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Already Logout');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}else {
			session.removeAttribute("tea_id");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Logout Complete.');");
			script.println("location.href='/PeerSys/teacherIndex.jsp';");
			script.println("</script>");
			script.close();
		}
		
	}

}
