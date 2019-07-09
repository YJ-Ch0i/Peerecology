package User.StudentController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;

public class StudentLogoutController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(); 
		
		if(session.getAttribute("stu_id")==null) 
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그아웃 되어있는 상태입니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}else {
			session.removeAttribute("stu_id");
			session.removeAttribute("stu_desc");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그아웃 되었습니다.');");
			script.println("location.href='/PeerSys/index.jsp';");
			script.println("</script>");
			script.close();
		}		
	}
}
