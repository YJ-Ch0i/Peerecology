package User.TeacherController;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.TeacherService;
import Util.SHA256;

public class EmailCheckController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String code = null;
		if(request.getParameter("code") != null)
		{
			code = request.getParameter("code");
		}
		
		String tea_id = null;
		if(session.getAttribute("tea_id") != null)
		{
			tea_id = (String) session.getAttribute("tea_id");
		}

		if(tea_id == null)	
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Login Plz');");
			script.println("location.href = 'index.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
		
		
		TeacherService teaService = TeacherService.getInstance();
		
		String u_email = tea_id;
		boolean isRight = (new SHA256().getSHA256(u_email).equals(code)) ? true : false;
		if(isRight == true)
		{
			teaService.setEmailCheck(tea_id);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Certified!!!');");
			script.println("location.href = 'index.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
		else
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Not right Code!!');");
			script.println("location.href = 'index.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
	}
}
