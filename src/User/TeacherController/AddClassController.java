package User.TeacherController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.CommonUtil;
import Controller.Controller;
import Service.SchoolService;
import Service.StudentService;
import Service.TeacherService;
import User.UserDTO.StudentDTO;
import User.UserDTO.StudentItem;
import User.UserDTO.TeacherDTO;

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
	       sch_name = request.getParameter("sch_name");
	    }
	    if(request.getParameter("sch_code") != ""){
	       sch_code = request.getParameter("sch_code");
	    }
	    if(request.getParameter("grade_num") != ""){
		   grade = Integer.parseInt(request.getParameter("grade_num"));
	    }
	    if(request.getParameter("class_num") != ""){
		   grd_num = Integer.parseInt(request.getParameter("class_num"));
	    }
	    if(request.getParameter("sch_address") !=""){
	       sch_address = request.getParameter("sch_address");
	    }
//	    if(session.getAttribute("file_name") != "" || session.getAttribute("file_name") == null){
//	       file_name = session.getAttribute("file_name").toString();
//	    }
	    if(CommonUtil.isNotNullString((String)session.getAttribute("file_name"))) {
	    	file_name = session.getAttribute("file_name").toString();
	    }
	    else file_name="";	    
	    
	    if(CommonUtil.isNullString(file_name)){
	       //session.setAttribute("tea_id", tea_id);
	       PrintWriter script =response.getWriter();
	       script.println("<script>");
	       script.println("alert('CSV파일을 업로드 해 주세요.')");
	       script.println("history.back();");
	       script.println("</script>");
	       script.close();
	       return;
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
	    
	    Calendar calendar = new GregorianCalendar();		//날짜 객체 생성을 위한 calendar
		Date date = new Date(calendar.getTimeInMillis()); //현재의 날짜 객체
	    
	    if(tea_id != "" &&file_name!="" && sch_code != "" && grade != -1 && grd_num != -1){
			StudentService stuservice = StudentService.getInstance();
			SchoolService schService = SchoolService.getInstance();
			TeacherService teaService = TeacherService.getInstance();
			ArrayList<StudentItem> stuItem_list = new ArrayList<>();				
			
			if(!schService.isSchoolAlreadyRegister(sch_code))
			{
				schService.SchoolRegist(sch_address,sch_name,sch_code);
			}
		
			//학급 업데이트 시 기존 등록 학생 invisible 로직
			TeacherDTO teacher = teaService.teacherInfo(tea_id);
			ArrayList<StudentDTO> stuListExisted = stuservice.getStudentListAttend2020(teacher.getTID(), teacher.getSCID(), teacher.getGrade(), teacher.getClasses(), teacher.getLastChangeYear());

			if(!stuListExisted.isEmpty()) {
				for(StudentDTO dto : stuListExisted) {
					stuservice.studentTransfer2020(dto);
				}
			}
			//end
			
			boolean teaUpdate = teaService.teacherSchoolUpdate(sch_code, grade, grd_num, tea_id, strYear);

			stuItem_list = stuservice.LoadStudent(file_name, request.getParameter("uploadPath"));

			for(StudentItem stuItem : stuItem_list) {
				String classAndban = null;
				int gender = -1;
				if(stuItem.getBefore_class() != null || stuItem.getBefore_class() != "") {
				String[] splitBf_class = stuItem.getBefore_class().split("학년 ");
					if(splitBf_class.length>=2) 
					{
						String[] splitBf_class2 = splitBf_class[1].split("반");
						classAndban = splitBf_class[0] + "-"+splitBf_class2[0];
					}				
				}
				
				if(stuItem.stu_gender.equals("남자") || stuItem.stu_gender.equals("남")) {
					gender = 1;
				}
				else if(stuItem.stu_gender.equals("여자") || stuItem.stu_gender.equals("여")) {
					gender = 2;
				}
				
				StudentDTO students = new StudentDTO(stuItem.getStu_name(), sch_code, grade, grd_num, Integer.parseInt(stuItem.getStu_number()), tea_id, gender, date);
				stuservice.registStudent(students);
			}			
			
			try {
				if(teaUpdate == true) {					 
	               session.setAttribute("tea_id", tea_id);
	               PrintWriter script =response.getWriter();
	               script.println("<script>");
	               script.println("alert('Certified!!')");
	               script.println("location.href='page_tea/login.jsp';");
	               script.println("</script>");
	               script.close();
	               return;
				}
				else {
					PrintWriter script =response.getWriter();
					script.println("<script>");
					script.println("alert('Error...')");
					script.println("history.back();");
					script.println("</script>");
					script.close();
					return;
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
	    }
	}
}
				

