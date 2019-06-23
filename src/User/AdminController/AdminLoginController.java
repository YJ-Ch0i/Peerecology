package User.AdminController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;

public class AdminLoginController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 	ServletContext context =  request.getServletContext(); 
		String admin_id = context.getInitParameter("admin_id");	String admin_pass = context.getInitParameter("admin_password");
		String input_id = null;  String input_pass = null;
		if(request.getParameter("input_id") != null)
			input_id = request.getParameter("input_id");
		if(request.getParameter("input_pass") != null)
			input_pass = request.getParameter("input_pass");
		
		if(input_id == "" && input_pass == "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('�븘�씠�뵒 �샊�� 鍮꾨�踰덊샇瑜� �엯�젰�빐 二쇱꽭�슂.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		if(input_id == "" && input_pass != "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('�븘�씠�뵒瑜� �엯�젰 �빐 二쇱꽭�슂.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		if(input_id != "" && input_pass == "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('鍮꾨�踰덊샇瑜� �엯�젰�빐二쇱꽭�슂.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		
		if(!input_id.equals(admin_id) || !input_pass.equals(admin_pass))
		{
			if(input_id.equals(admin_id)) 
			{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('鍮꾨�踰덊샇媛� ���졇�뒿�땲�떎.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			}
		}
		else 
		{
			session.setAttribute("admin_id", admin_id);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href='/Survey/index.jsp';");
			script.println("</script>");
			script.close();
		}
	}

}
