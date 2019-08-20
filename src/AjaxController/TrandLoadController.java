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
import SurveyRelationDTO.QuestionTrandTypeDTO;

public class TrandLoadController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String btid = request.getParameter("btid").trim();		
		int bigT = Integer.parseInt(btid);
		
		QuestionService queService = QuestionService.getInstance();		
		ArrayList<QuestionTrandTypeDTO> tList = new ArrayList<>();
		tList = queService.getTrandToBigTID(bigT);
		
		JsonArray trandJson = new JsonArray();
		
		//척도분류에 따른 척도의 jsonArray 생성
		for(int i=0; i<tList.size(); i++) {
			JsonObject obj = new JsonObject();
			obj.addProperty("trandID", tList.get(i).getQ_trandType());
			obj.addProperty("trandDesc", tList.get(i).getQ_trandDescipt());
			
			trandJson.add(obj);
		}
		
		// jsonArray 넘김
		PrintWriter pw = response.getWriter();
		pw.print(trandJson.toString());
		pw.flush();
		pw.close();
	}
}
