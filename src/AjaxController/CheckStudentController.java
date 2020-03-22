package AjaxController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.CommonUtil;
import Controller.Controller;
import Service.StudentService;
import Service.TeacherService;
import User.UserDTO.StudentDTO;
import User.UserDTO.TeacherDTO;

public class CheckStudentController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int stuNum = CommonUtil.strToInt(request.getParameter("number"));

		TeacherDTO teacher = TeacherService.getInstance().teacherInfo(
				(String)request.getSession().getAttribute("tea_id"));
		
//		ArrayList<StudentDTO> attendList = StudentService.getInstance().studentListAttend3(
//																			teacher.getSCID(),
//																			teacher.getGrade(),
//																			teacher.getClasses(),
//																			teacher.getLastChangeYear());
		
		ArrayList<StudentDTO> attendList = StudentService.getInstance().getStudentListAttend2020(teacher.getTID(),
																							teacher.getSCID(),
																							teacher.getGrade(),
																							teacher.getClasses(),
																							teacher.getLastChangeYear());
	
		List<Integer> numList = new ArrayList<>();
		for(StudentDTO dto : attendList) {
			numList.add(dto.getNum());
		}
		
		System.out.println(numList.contains(stuNum));
		
		PrintWriter pw = response.getWriter();
		JsonObject obj = new JsonObject();
		if(numList.contains(stuNum)) {
			obj.addProperty("contains", "false");
			String json = new Gson().toJson(obj);
			pw.print(json);
		}
		else if(!numList.contains(stuNum)) {
			obj.addProperty("contains", "true");
			String json = new Gson().toJson(obj);
			pw.print(json);
		}
		pw.flush();
		pw.close();
	}
}

