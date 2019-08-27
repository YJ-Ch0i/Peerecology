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
import Service.QuestionService;
import Service.StudentService;
import SurveyRelationDTO.SurveyAnswerDTO;
import User.UserDTO.StudentDTO;

public class PeerLoadController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<SurveyAnswerDTO> multiAnswerValue = QuestionService.getInstance().getMultiAnswerValueInQuestion(
																				CommonUtil.strToInt(request.getParameter("qid")),
																				CommonUtil.strToInt(request.getParameter("seq")),
																				request.getParameter("scid"),
																				CommonUtil.strToInt(request.getParameter("grade")),
																				CommonUtil.strToInt(request.getParameter("grdNum")));
		
		ArrayList<StudentDTO> attendList = StudentService.getInstance().studentListAttend3(
																			request.getParameter("scid"),
																			CommonUtil.strToInt(request.getParameter("grade")),
																			CommonUtil.strToInt(request.getParameter("grdNum")),
																			request.getParameter("year"));
		Gson gson = new Gson();
		List<List<String>> nodeEdgeArray = new ArrayList<>();	//노드와 엣지 2개의 결과를 전달할 List
		List<String> nodeArray = new ArrayList<>();
		//관리자용 또래지명 결과 ::: 학생ID or 재학생 번호
		for(StudentDTO dto : attendList) {
			JsonObject obj = new JsonObject();
			obj.addProperty("id", dto.getStu_id());
			obj.addProperty("label", Integer.toString(dto.getNum()));

			String json = gson.toJson(obj);
			nodeArray.add(json);
		}
		nodeEdgeArray.add(nodeArray);
		
		List<String> edgeArray = new ArrayList<>();
		//노드를 통해 엣지들을 잇는 json
		for(SurveyAnswerDTO dto : multiAnswerValue) {
			JsonObject obj = new JsonObject();
			obj.addProperty("from", dto.getStudentID());
			obj.addProperty("to", dto.getMultiAnswers());
			obj.addProperty("arrows", "to");
			
			String json = gson.toJson(obj);
			edgeArray.add(json);
		}
		nodeEdgeArray.add(edgeArray);
				
		PrintWriter pw = response.getWriter();
		pw.print(nodeEdgeArray);
		pw.flush();
		pw.close();
	}
}
