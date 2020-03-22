package User.AdminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.CommonUtil;
import Controller.Controller;
import Service.TeacherService;
import User.UserDTO.TeacherDTO;

public class AdminClassSearchController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String sch_code = request.getParameter("sch_code");
		String sch_name = request.getParameter("sch_name");
		String adminSearching = request.getParameter("adminSearching");
		String sch_address = request.getParameter("sch_address");
		int grade = CommonUtil.strToInt(request.getParameter("grade"));
		String year = request.getParameter("year");
		String title = request.getParameter("title");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		
		String surNo = ""; String ingseq = ""; int surveyNo = 0; int ingSeq = 0;
		if(request.getParameter("surveyNo") != "" ||  !request.getParameter("surveyNo").equals("")
				|| request.getParameter("surveyNo") != null || !request.getParameter("surveyNo").equals(null)) {
			surNo = request.getParameter("surveyNo").trim();
			surveyNo = Integer.parseInt(surNo);
		}
		if(request.getParameter("ingseq") != "" || !request.getParameter("ingseq").equals("")
				|| request.getParameter("ingseq") != null || !request.getParameter("ingseq").equals(null)) {
			ingseq = request.getParameter("ingseq").trim();
			ingSeq = Integer.parseInt(ingseq);
		}
		
		System.out.println(surveyNo);
		System.out.println(ingSeq);
		
		boolean isElementalSchool = true;
		
		if(sch_name.contains("중학교")) isElementalSchool = false;
		
		//ClassService service = ClassService.getInstance();
		//ArrayList<String> list = service.ClassSearch(sch_code);
		//request.setAttribute("list", list);
		
		TeacherService teaService = TeacherService.getInstance();
		ArrayList<TeacherDTO> list = teaService.searchClassForAdmin(sch_code, grade);
		
		request.setAttribute("list", list);
		request.setAttribute("sch_code", sch_code);
		request.setAttribute("sch_name", sch_name);
        request.setAttribute("sch_address", sch_address);
		request.setAttribute("isElementalSchool", isElementalSchool);
		request.setAttribute("adminSearching", adminSearching);
		request.setAttribute("year", year);
		request.setAttribute("title", title);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		request.setAttribute("ingSeq", ingSeq);
		request.setAttribute("surveyNo", surveyNo);
		RequestDispatcher dc =  request.getRequestDispatcher("/page_common/searchClass.jsp");
		dc.forward(request, response);
	}

}
