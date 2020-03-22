package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.CommonUtil;
import Common.Constant;
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
		
		Date start = Date.valueOf(startdate);
		Date end = Date.valueOf(enddate);

		StudentService stuService = StudentService.getInstance();
		ArrayList<StudentDTO> attendList = new ArrayList<>();
		// 설문에 해당하는 학교의 재학생 리스트
		attendList = stuService.getSchoolAttendList(scid, grd, year);
		for(StudentDTO dto : attendList) {
			System.out.println("이름 ::: " + dto.getName());
			System.out.println("연도 ::: " + dto.getYear());
			System.out.println("학년 ::: " + dto.getGrd_num());
			System.out.println("반 ::: " + dto.getNum());
		}

		SurveyService surService = SurveyService.getInstance();
		QuestionService queService = QuestionService.getInstance();
		
		ArrayList<SurveyManagerDTO> svManagerList = new ArrayList<>();
		svManagerList = surService.showQuestionsToManager(survey_no);
		ArrayList<AllDescQuestionDTO> questionDescList = new ArrayList<>();
		for (int j = 0; j < svManagerList.size(); j++) {
			questionDescList.add(queService.showQuestion(svManagerList.get(j).getQID()));
		}
		
		for(AllDescQuestionDTO dto : questionDescList) {
			System.out.println("제목 ::: " + dto.getQue_title());
			System.out.println("성향 ::: " + dto.getQue_trandTitle());
			System.out.println("타입 ::: " + dto.getQue_typeTitle());
		}
		
		//척도리스트		
		ArrayList<QuestionTrandTypeDTO> trandList = queService.searchTrandList(survey_no, ingSeq, scid);
		
		for(QuestionTrandTypeDTO dto : trandList) {
			System.out.println("btid ::: " + dto.getBigTrandID());
			System.out.println("설명 ::: " + dto.getQ_trandDescipt());
			System.out.println("trid ::: " + dto.getQ_trandType());
		}

		AnswerService ansService = AnswerService.getInstance();
		ArrayList<SurveyAnswerDTO> answers = new ArrayList<>();
		answers = ansService.getAnswers(ingSeq);
		//ArrayList<SurveyAnswerDTO> answers = new ArrayList<>();
		ArrayList<QuestionTypeDTO> allType = new ArrayList<>();
		allType = queService.showAllType();

		ArrayList<QuestionTrandManagerDTO> bigTrandList = new ArrayList<>();
		bigTrandList = queService.getBigTrandList(scid, start, end);
		
		//TODO
		List<StudentScoresDTO> list = new ArrayList<>();
		
		
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
														if (questionDescList.get(o).isQue_isReverseType() == false
																&& allType.get(q).getQ_typeID() != Constant.SHORTANSWER
																&& allType.get(q).getQ_typeID() != Constant.PEERTYPEID
																&& CommonUtil.isNullString(questionDescList.get(o).getQue_answer())) {	//객관식 역산
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
														} else if (allType.get(q).getDescript().equals("또래지명") || 
																allType.get(q).getQ_typeID() == Constant.PEERTYPEID) {	//또래지명
															continue;
														} else if (allType.get(q).getDescript().equals("주관식") || 
																allType.get(q).getQ_typeID() == Constant.SHORTANSWER) {	//주관식
//															if (answers.get(r).getAnswerValue().contains(questionDescList.get(o).getQue_answer()) ||
															if (answers.get(r).getAnswerValue().equals(questionDescList.get(o).getQue_answer()) &&
																	CommonUtil.isNotNullString(questionDescList.get(o).getQue_answer())) {
																total = total + 1;
															}
															else continue;
														} else if (questionDescList.get(o).isQue_isReverseType() == true
																&& allType.get(q).getQ_typeID() != Constant.SHORTANSWER
																&& allType.get(q).getQ_typeID() != Constant.PEERTYPEID
																&& CommonUtil.isNullString(questionDescList.get(o).getQue_answer())) { //객관식 정산
																														
															score = CommonUtil.strToInt(answers.get(r).getAnswerValue());
															total = score + total;
														}
														//TODO  객관식 정답있을때
														else if(allType.get(q).getQ_typeID() != Constant.SHORTANSWER
																&& CommonUtil.isNotNullString(questionDescList.get(o).getQue_answer())) {
															if (answers.get(r).getAnswerValue().contains(questionDescList.get(o).getQue_answer()) ||
																	answers.get(r).getAnswerValue().equals(questionDescList.get(o).getQue_answer())) {
																																
																total = total + 1;																
															}															
															else continue;
														}
														else continue;
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
						int queCount = queService.countOfTrandQuestion(survey_no, trandList.get(l).getQ_trandType());						
						double finScore = 0;
						if(queCount != 0) {
							finScore = (double)total/queCount;							
						}
						else if(queCount == 0) {
							continue;
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
						students.setScore(finScore);				      
						list.add(students);
						
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
