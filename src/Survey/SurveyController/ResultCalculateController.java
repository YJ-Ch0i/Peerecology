package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.AnswerService;
import Service.QuestionService;
import Service.StudentService;
import Service.SurveyService;
import SurveyRelationDTO.AllDescQuestionDTO;
import SurveyRelationDTO.QuestionTrandManagerDTO;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.QuestionTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import User.UserDTO.StudentDTO;

public class ResultCalculateController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String surveyNo = request.getParameter("surveyNo").trim();
		String ingNo = request.getParameter("ingSeq").trim();
		String scid = request.getParameter("sch_code");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String sch_name = request.getParameter("sch_name");
		String grade = request.getParameter("grade").trim();

		int survey_no = Integer.parseInt(surveyNo);
		int ingSeq = Integer.parseInt(ingNo);
		int grd = Integer.parseInt(grade);
		
		System.out.println("설문번호 : " + survey_no);
		System.out.println("설문 진행번호 : "+ingSeq);
		Date start = Date.valueOf(startdate);
		Date end = Date.valueOf(enddate);

		StudentService stuService = StudentService.getInstance();
		ArrayList<StudentDTO> attendList = new ArrayList<>();
		// 설문에 해당하는 학교의 재학생 리스트
		attendList = stuService.getSchoolAttendList(scid, grd, year);

		SurveyService surService = SurveyService.getInstance();
		QuestionService queService = QuestionService.getInstance();
		
		ArrayList<SurveyManagerDTO> svManagerList = new ArrayList<>();
		svManagerList = surService.showQuestionsToManager(survey_no);
		ArrayList<AllDescQuestionDTO> questionDescList = new ArrayList<>();
		for (int j = 0; j < svManagerList.size(); j++) {
			questionDescList.add(queService.showQuestion(svManagerList.get(j).getQID()));
		}
		
		ArrayList<QuestionTrandTypeDTO> trandList = new ArrayList<>();
		trandList = queService.searchTrandList(survey_no, ingSeq, scid);

		AnswerService ansService = AnswerService.getInstance();
		ArrayList<SurveyAnswerDTO> answers = new ArrayList<>();
		answers = ansService.getAnswers(ingSeq);
		//ArrayList<SurveyAnswerDTO> answers = new ArrayList<>();
		ArrayList<QuestionTypeDTO> allType = new ArrayList<>();
		allType = queService.showAllType();

		ArrayList<QuestionTrandManagerDTO> bigTrandList = new ArrayList<>();
		bigTrandList = queService.getBigTrandList(scid, start, end);
		
		ArrayList<StudentScoresDTO> stu_scoreList = new ArrayList<>();
		
		for (int l = 0; l < trandList.size(); l++) {
			for(int k=0; k<bigTrandList.size(); k++) {
				if(trandList.get(l).getBigTrandID() == bigTrandList.get(k).getBigTrandID()) {				
					for (int n = 0; n < attendList.size(); n++) {
						int total = 0;
						StudentScoresDTO students = new StudentScoresDTO();
						for (int o = 0; o < questionDescList.size(); o++) {
							for (int q = 0; q < allType.size(); q++) {						
								if (questionDescList.get(o).getQue_typeID() == allType.get(q).getQ_typeID()) {
									if (questionDescList.get(o).getQue_trandTitle().equals(trandList.get(l).getQ_trandDescipt())) {
										for (int r = 0; r < answers.size(); r++) {
											if (questionDescList.get(o).getQID() == answers.get(r).getQID()) {													
												if (attendList.get(n).getStu_id() == answers.get(r).getStudentID()) {
														int score = 0;
														if (questionDescList.get(o).isQue_isReverseType() == false) {
															if (answers.get(r).getAnswerValue().equals("1"))
																score = 5;
															if (answers.get(r).getAnswerValue().equals("2"))
																score = 4;
															if (answers.get(r).getAnswerValue().equals("3"))
																score = 3;
															if (answers.get(r).getAnswerValue().equals("4"))
																score = 2;
															if (answers.get(r).getAnswerValue().equals("5"))
																score = 1;

															total = score + total;
														} else if (allType.get(q).getDescript().equals("또래지명")) {
															continue;
														} else if (allType.get(q).getDescript().equals("주관식")) {
															if (answers.get(r).getAnswerValue().contains(questionDescList.get(o).getQue_answer()) ||
																	answers.get(r).getAnswerValue().equals(questionDescList.get(o).getQue_answer())) {
																total = total + 1;
															}
														} else if (questionDescList.get(o).isQue_isReverseType() == true) {
															score = Integer.parseInt(answers.get(r).getAnswerValue().trim());
															total = score + total;
														}
													}
													else continue;
												}
												else continue;
											}
										}
									}
									else continue;
								}
							}
						
						students.setStu_id(attendList.get(n).getStu_id());
						students.setsName(attendList.get(n).getName());
						students.setIngseq(ingSeq);
						students.setSurveyNo(survey_no);
						students.setTrandId(trandList.get(l).getQ_trandType());
						students.setBigTrandId(trandList.get(l).getBigTrandID());
						students.setSCID(scid);
						students.setGrade(attendList.get(n).getGrade());
						students.setGrd_num(attendList.get(n).getGrd_num());
						students.setScore(total);				          
						
						boolean isRegist = surService.calculateSurvey(students);
						
						if(isRegist == true) {
							continue;
						}
						else if(isRegist == false) {
							break;
						}
					}
				}
			}
		}
		
		surService.calculateUpdate(ingSeq, survey_no);

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Calculation Complete')");
		script.println("location.href='/PeerSys/page_adm/SurveyResult/resultSchool.jsp';");
		script.println("</script>");
		script.close();
		return;
		

		// RequestDispatcher dc = request.getRequestDispatcher("/page_tea/SurveyResult/resultSurvey.jsp");
		//RequestDispatcher dc = request.getRequestDispatcher("/page_common/privateResult.jsp");
		//dc.forward(request, response);

	}

}
