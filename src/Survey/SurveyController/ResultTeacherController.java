package Survey.SurveyController;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Controller.Controller;
import Service.AnswerService;
import Service.QuestionService;
import Service.StudentService;
import Service.SurveyService;
import Service.TeacherService;
import SurveyRelationDTO.AllDescQuestionDTO;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.QuestionTypeDTO;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import User.UserDTO.StudentDTO;
import User.UserDTO.StudentScore;
import User.UserDTO.TeacherDTO;

public class ResultTeacherController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		String surveyNo = request.getParameter("surveyNo").trim();
		String ingNo = request.getParameter("ingSeq").trim();
		String scid = request.getParameter("scid");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String title = request.getParameter("title");
		String tea_id = (String) session.getAttribute("tea_id");

		int survey_no = Integer.parseInt(surveyNo);
		int ingSeq = Integer.parseInt(ingNo);
		Date start = Date.valueOf(startdate);
		Date end = Date.valueOf(enddate);

		TeacherService teaService = TeacherService.getInstance();
		TeacherDTO teacher = teaService.teacherInfo(tea_id);

		StudentService stuService = StudentService.getInstance();
		ArrayList<StudentDTO> attendList = new ArrayList<>();
		attendList = stuService.studentListAttend(tea_id, teacher.getSCID(), teacher.getGrade(), teacher.getClasses());

		SurveyService surService = SurveyService.getInstance();

		QuestionService queService = QuestionService.getInstance();
		ArrayList<QuestionTrandTypeDTO> trandList = new ArrayList<>();
		trandList = queService.searchTrandList(survey_no, ingSeq, scid, start, end);

		ArrayList<SurveyManagerDTO> svManagerList = new ArrayList<>();
		svManagerList = surService.showQuestionsToManager(survey_no);
		ArrayList<AllDescQuestionDTO> questionDescList = new ArrayList<>();

		AnswerService ansService = AnswerService.getInstance();
		ArrayList<SurveyAnswerDTO> answers = new ArrayList<>();
		answers = ansService.getAnswers(ingSeq);

		ArrayList<QuestionTypeDTO> allType = new ArrayList<>();

		for (SurveyManagerDTO svmDTO : svManagerList) {
			questionDescList.add(queService.showQuestion(svmDTO.getQID()));
		}
		allType = queService.showAllType();

		ArrayList<StudentScore> stu_scoreList = new ArrayList<>();		
		for (int m = 0; m < trandList.size(); m++) {
			for (int k = 0; k < attendList.size(); k++) {
				int total = 0;				
				StudentScore students = new StudentScore();
				for (int i = 0; i < questionDescList.size(); i++) {
					for (int l = 0; l < allType.size(); l++) {
						if (questionDescList.get(i).getQue_typeID() == allType.get(l).getQ_typeID()) {
							if (questionDescList.get(i).getQue_trandTitle().equals(trandList.get(m).getQ_trandDescipt())) {
								for (int j = 0; j < answers.size(); j++) {
									if (questionDescList.get(i).getQID() == answers.get(j).getQID()) {
										if (attendList.get(k).getStu_id() == answers.get(j).getStudentID()) {
											
											int score = 0;
											if (questionDescList.get(i).isQue_isReverseType() == false) {

												if (answers.get(j).getAnswerValue().equals("1"))
													score = 5;
												if (answers.get(j).getAnswerValue().equals("2"))
													score = 4;
												if (answers.get(j).getAnswerValue().equals("3"))
													score = 3;
												if (answers.get(j).getAnswerValue().equals("4"))
													score = 2;
												if (answers.get(j).getAnswerValue().equals("5"))
													score = 1;

												total = score + total;

											} else if (questionDescList.get(i).isQue_isReverseType() == true) {
												score = Integer.parseInt(answers.get(j).getAnswerValue().trim());
												total = score + total;
											}											 
										}
									}																
								}
							}
						}						
					}
				}
				students.setSid(attendList.get(k).getStu_id());            
				students.setName(attendList.get(k).getName());             
				students.setIngSeq(ingSeq);                                
				students.settType(trandList.get(m).getQ_trandType());            
				students.setScore(total);                                  
				stu_scoreList.add(students);        				
			}
		}
		
		/*ArrayList<StudentScore> dividedScoreList = new ArrayList<>();
		
		for(int x=0; x<stu_scoreList.size(); x++) {
			for(int y=0; y<trandList.size(); y++) {
				if(trandList.get(y).getQ_trandType() == stu_scoreList.get(x).gettType()) {
					System.out.println(trandList.get(y).getQ_trandType());
					System.out.println(trandList.get(y).getQ_trandDescipt());					
					System.out.println(stu_scoreList.get(x).getName());
					System.out.println(stu_scoreList.get(x).getScore());
				}
			}
		}*/
		
		ArrayList<String> totalArray = new ArrayList<>();
		ArrayList<String> trandArray = new ArrayList<>();
		ArrayList<String> stuArray = new ArrayList<>();
		Gson gson = new Gson();
		JsonObject totalobject = new JsonObject();
		JsonObject trandobject = new JsonObject();
		JsonObject stuobject = new JsonObject();
		
		for(int i=0; i<stu_scoreList.size(); i++) {
			totalobject.addProperty("sId", stu_scoreList.get(i).getSid());
			totalobject.addProperty("sName" , stu_scoreList.get(i).getName());
			totalobject.addProperty("ingSeq" , stu_scoreList.get(i).getIngSeq());
			totalobject.addProperty("trand" , stu_scoreList.get(i).gettType());
			totalobject.addProperty("score" , stu_scoreList.get(i).getScore());		
			
			String json = gson.toJson(totalobject);
			totalArray.add(json);
		}		
		
		for(QuestionTrandTypeDTO dto : trandList) {
			trandobject.addProperty("trandID", dto.getQ_trandType());
			trandobject.addProperty("trandDes", dto.getQ_trandDescipt());
			
			String json = gson.toJson(trandobject);
			trandArray.add(json);
		}
		
		for(StudentDTO dto : attendList) {
			stuobject.addProperty("sId", dto.getStu_id());
			stuobject.addProperty("name", dto.getName());
			stuobject.addProperty("scid", dto.getScid());
			stuobject.addProperty("grade", dto.getGrade());
			stuobject.addProperty("classes", dto.getGrd_num());
			stuobject.addProperty("numb", dto.getNum());
			
			String json = gson.toJson(stuobject);
			stuArray.add(json);
		}
		
		//System.out.println(totalArray);
		//System.out.println(trandArray);
		//System.out.println(stuArray);

		request.setAttribute("survey_no", survey_no);
		request.setAttribute("ingSeq", ingSeq);
		request.setAttribute("scid", scid);
		request.setAttribute("startdate", start);
		request.setAttribute("enddate", end);
		request.setAttribute("title", title);
		request.setAttribute("stu_scoreList", stu_scoreList);
		request.setAttribute("totalArray", totalArray);
		request.setAttribute("trandArray", trandArray);
		request.setAttribute("stuArray", stuArray);

		RequestDispatcher dc = request.getRequestDispatcher("/page_tea/SurveyResult/resultSurvey.jsp");
		dc.forward(request, response);
	}
}
