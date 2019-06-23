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
			script.println("alert('아이디 혹은 비밀번호를 입력해 주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		if(tea_id == "" && pass != "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디를 입력 해 주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		if(tea_id != "" && pass == "")
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호를 입력해주세요.');");
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
			script.println("alert('로그인 성공.');");
			script.println("location.href = 'page_tea/login.jsp';");
			script.println("</script>");
			script.close();
			return;
		}
		else if(result == 0)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 다릅니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else if(result == -1)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디 입니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		else if(result == -2)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		
	}

}
