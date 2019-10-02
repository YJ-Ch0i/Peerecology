package User.AdminController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.CommonUtil;
import Controller.Controller;
import Service.TeacherService;
import User.UserDAO.TeacherDAO;
import User.UserDTO.TeacherDTO;

/**
 * 관리자 전용 교사 계정 등록
 * @author yeong
 *
 */
public class AdminTeacherRegistController implements Controller {

	TeacherService teaService = TeacherService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String teaId = request.getParameter("tea_id");
		String teaName = request.getParameter("tea_name");
		String pass = request.getParameter("tea_pass");
		String rePass = request.getParameter("re-pass");
		
		Calendar currentCalendar = Calendar.getInstance();
		String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
		
		switch(checkAccount(teaId, teaName, pass, rePass)) {
		
		case 1:{
			TeacherDTO teacher = new TeacherDTO(teaId, pass, teaName, true, strYear);
			int regist = TeacherDAO.getInstance().registTeacherAdmin(teacher);
			if(regist == 1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('교사 계정을 등록하였습니다.')");
				script.println("location.href='/PeerSys/page_adm/login.jsp'");
				script.println("</script>");
				script.close();
				return;
			}
			else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('오류가 발생하였습니다.')");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			}
			
		}
		case 0:{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('두 암호가 다릅니다. 재확인하여 주십시오.')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		case -1:{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 가입된 계정입니다.')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		}
	}
	
	/**
	 * 관리자 교사 계정 유효성 검사
	 * @param id
	 * @param name
	 * @param pass
	 * @param rePass
	 * @return 1 : 가입 가능한 계정이고 비밀번호 확인이 완료 됨
	 * @return 0 : 가입 가능한 계정이나 입력한 두 비밀번호가 다름
	 * @return -1 : 이미 가입된 계정
	 */
	protected int checkAccount(String id, String name, String pass, String rePass) {
		
		boolean isRegist = teaService.isUserRegist(id);
		
		if(isRegist) { //가입되지 않은 아이디라면
			if(CommonUtil.isEqualString(pass, rePass)) {
				return 1;
			}
			else return 0;
		}
		else return -1;
		
	}
}
