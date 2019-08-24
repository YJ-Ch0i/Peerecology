package AjaxController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Controller.Controller;
import Service.QuestionService;
import Service.SurveyService;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;

public class StudentLoadController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String stuid = request.getParameter("stuid").trim();
		String scid = request.getParameter("scid");	
		String grd = request.getParameter("grade").trim();	
		String grdn = request.getParameter("grd_num").trim();	
		String year = request.getParameter("year");
		
		int stid = Integer.parseInt(stuid);
		int grade = Integer.parseInt(grd);
		int grd_num = Integer.parseInt(grdn);
		
		System.out.println(stuid);
		System.out.println(scid);
		System.out.println(grd);
		System.out.println(grdn);
		System.out.println(year);
		
		JsonArray trandJson = new JsonArray();
		
		SurveyService surService = SurveyService.getInstance();
		ArrayList<StudentScoresDTO> getStudentScores = new ArrayList<>();
		getStudentScores = surService.getStudentScores(scid, grade, grd_num, stid, year);
		
		
		//척도분류에 따른 척도의 jsonArray 생성
		for(int i=0; i<getStudentScores.size(); i++) {
			JsonObject obj = new JsonObject();
			obj.addProperty("stuid", getStudentScores.get(i).getStu_id());
			obj.addProperty("name", getStudentScores.get(i).getsName());
			obj.addProperty("ingSeq", getStudentScores.get(i).getIngseq());
			obj.addProperty("surNo", getStudentScores.get(i).getSurveyNo());
			obj.addProperty("btid", getStudentScores.get(i).getBigTrandId());
			obj.addProperty("btdesc", getStudentScores.get(i).getBigTrandDesc());
			obj.addProperty("trid", getStudentScores.get(i).getTrandId());
			obj.addProperty("trdesc", getStudentScores.get(i).getTrandDesc());
			obj.addProperty("score", getStudentScores.get(i).getScore());
			
			trandJson.add(obj);
		}
		
		// jsonArray 넘김
		PrintWriter pw = response.getWriter();
		pw.print(trandJson.toString());
		pw.flush();
		pw.close();
	}
}
