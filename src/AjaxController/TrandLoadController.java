package AjaxController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Common.CommonUtil;
import Controller.Controller;
import Service.QuestionService;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;

public class TrandLoadController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		int btid = CommonUtil.strToInt(request.getParameter("btid"));
		int seq = CommonUtil.strToInt(request.getParameter("seq"));
		String scid = request.getParameter("scid");
		int grade = CommonUtil.strToInt(request.getParameter("grade"));
		int grdNum = CommonUtil.strToInt(request.getParameter("grdNum"));
		String year = request.getParameter("year");
		
		
		
		QuestionService queService = QuestionService.getInstance();
		ArrayList<QuestionTrandTypeDTO> tList = new ArrayList<>();		
		tList = queService.getTrandToBigTID(btid);
		Gson gson = new Gson();
		
		List<String> trandJson = new ArrayList<>();;
		
		//척도분류에 따른 척도의 jsonArray 생성
		for(int i=0; i<tList.size(); i++) {
			JsonObject obj = new JsonObject();
			obj.addProperty("trandID", tList.get(i).getQ_trandType());
			obj.addProperty("trandDesc", tList.get(i).getQ_trandDescipt());
			
			String json = gson.toJson(obj);
			trandJson.add(json);
		}
		
		List<List<String>> trandScoreList = new ArrayList<>();
		trandScoreList.add(trandJson);
		
		PrintWriter pw = response.getWriter();
		if(btid == Common.Constant.PEERID){	//또래지명일 때
			
			List<String> scoresArray = new ArrayList<>();
			List<StudentScoresDTO> list = PeerScoreCalculate.calculatePeerScore(btid, scid, grade, grdNum, year);
			
			for(StudentScoresDTO dto : list) {
				JsonObject obj = new JsonObject();
				obj.addProperty("sID", dto.getStu_id());
				obj.addProperty("sName", dto.getsName());
				obj.addProperty("surIngSeq", dto.getIngseq());
				obj.addProperty("trID", dto.getTrandId());
				obj.addProperty("score", dto.getScore());
				
				String json = gson.toJson(obj);
				scoresArray.add(json);
			}
			
			trandScoreList.add(scoresArray);
			System.out.println(trandScoreList);
			pw.print(trandScoreList);	
		}
		else {
			System.out.println(trandScoreList);
			pw.print(trandScoreList);
			
		}
		
		pw.flush();
		pw.close();
	}
}
