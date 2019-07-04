package School.SchoolController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import School.SchoolDTO.SchoolDTO;
import Service.SchoolService;

public class SchoolSearch implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String schoolNm = request.getParameter("input_schoolNm");
		String adminSearching = request.getParameter("adminSearching");
		if(schoolNm.isEmpty()) 
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('학교명을 입력해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		else
		{
			SchoolService service = SchoolService.getInstance();
			ArrayList<SchoolDTO> school = service.SearchSchoolAddress(schoolNm);
			
			if(school.isEmpty())
			{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('등록되지 않는 학교입니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			}
			else 
			{
				request.setAttribute("school", school);
				request.setAttribute("adminSearching", adminSearching);
				
				RequestDispatcher view = request.getRequestDispatcher("/page_common/searchSchool.jsp");
				view.forward(request, response);
			}
		}
	}
}
