package User.TeacherController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.TeacherService;

public class TeacherLoginController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 	
		String tea_id = null; 	String pass = null;
		
		if(request.getParameter("tea_id") != null)
			tea_id = request.getParameter("tea_id");
		if(request.getParameter("pass") != null)
			pass = request.getParameter("pass");
		
		if(tea_id == "" && pass == "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('All Enter Blank!.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		if(tea_id == "" && pass != "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Enter ID!.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		if(tea_id != "" && pass == "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Enter Password.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		
		TeacherService service = TeacherService.getInstance();
		int result = service.TeacherLogin(tea_id, pass);
		
		if(result == 1)
		{
			session.setAttribute("tea_id", tea_id);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Login Complete!');");
			script.println("location.href = 'page_tea/login.jsp';");
			script.println("</script>");
			script.close();
			return;
		}
		else if(result == 0)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Check Password!');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else if(result == -1)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('No Exist ID');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		
	}

}
