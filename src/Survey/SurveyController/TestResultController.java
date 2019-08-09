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
import SurveyRelationDTO.AllDescQuestionDTO;
import SurveyRelationDTO.QuestionTrandManagerDTO;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.QuestionTypeDTO;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import User.UserDTO.StudentDTO;
import User.UserDTO.StudentScore;
import view.viewDTO.SearchEndsurveyDTO;

public class TestResultController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// String surveyNo = request.getParameter("surveyNo").trim();
		// String ingNo = request.getParameter("ingSeq").trim();
		String scid = request.getParameter("sch_code");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String grade1 = request.getParameter("grade").trim();
		String grd_num1 = request.getParameter("grd_num").trim();
		String sch_name = request.getParameter("sch_name");

		// int survey_no = Integer.parseInt(surveyNo);
		// int ingSeq = Integer.parseInt(ingNo);
		int grade = Integer.parseInt(grade1);
		int grd_num = Integer.parseInt(grd_num1);
		Date start = Date.valueOf(startdate);
		Date end = Date.valueOf(enddate);

		StudentService stuService = StudentService.getInstance();
		ArrayList<StudentDTO> attendList = new ArrayList<>();
		// 재학생 리스트
		attendList = stuService.studentListAttend2(scid, grade, grd_num, year);

		SurveyService surService = SurveyService.getInstance();

		ArrayList<SearchEndsurveyDTO> endSurList = new ArrayList<>();
		// 종료된 설문 리스트
		endSurList = surService.searchEndSurveyToYear(scid, year);

		QuestionService queService = QuestionService.getInstance();
		ArrayList<QuestionTrandTypeDTO> trandList = new ArrayList<>();
		ArrayList<ArrayList<QuestionTrandTypeDTO>> totalTrandList = new ArrayList<>();
		//ArrayList<SurveyManagerDTO> svManagerList = new ArrayList<>();
		ArrayList<ArrayList<SurveyManagerDTO>> totalSvManagerList = new ArrayList<>();
		//ArrayList<AllDescQuestionDTO> questionDescList = new ArrayList<>();
		ArrayList<ArrayList<AllDescQuestionDTO>> totalQuestionDescList = new ArrayList<>();

		AnswerService ansService = AnswerService.getInstance();
		//ArrayList<SurveyAnswerDTO> answers = new ArrayList<>();
		ArrayList<ArrayList<SurveyAnswerDTO>> totalAnswers = new ArrayList<>();
		//ArrayList<QuestionTypeDTO> allType = new ArrayList<>();

		ArrayList<QuestionTrandManagerDTO> bigTrandList = new ArrayList<>();
		bigTrandList = queService.getBigTrandList(scid, start, end);

		ArrayList<ArrayList<QuestionTrandManagerDTO>> totalBigTrandList = new ArrayList<>();
		//ArrayList<QuestionTrandManagerDTO> sBigTrandList = new ArrayList<>();

		ArrayList<StudentScore> stu_scoreList = new ArrayList<>();

		for (int x = 0; x < endSurList.size(); x++) {
			ArrayList<SurveyManagerDTO> svManagerList = new ArrayList<>();
			ArrayList<AllDescQuestionDTO> questionDescList = new ArrayList<>();
			ArrayList<SurveyAnswerDTO> answers = new ArrayList<>();
			ArrayList<QuestionTypeDTO> allType = new ArrayList<>();
			//ArrayList<QuestionTrandManagerDTO> bigTrandList = new ArrayList<>();
			ArrayList<QuestionTrandManagerDTO> sBigTrandList = new ArrayList<>();
			
			allType = queService.showAllType();
			
			// 모든 설문의 척도분류 리스트
			sBigTrandList = queService.searchBigTrandList(endSurList.get(x).getSurveyNo(),endSurList.get(x).getIngSeq(), endSurList.get(x).getSCID(), endSurList.get(x).getStartDate(), endSurList.get(x).getEndDate());
			totalBigTrandList.add(sBigTrandList);

			// 해당 연도에 진행한 모든설문의 척도 리스트
			trandList = queService.searchTrandList(endSurList.get(x).getSurveyNo(), endSurList.get(x).getIngSeq(),endSurList.get(x).getSCID());
			totalTrandList.add(trandList);

			// 각 설문당 문항들의 리스트
			svManagerList = surService.showQuestionsToManager(endSurList.get(x).getSurveyNo());
			//totalSvManagerList.add(svManagerList);

			for (int j = 0; j < svManagerList.size(); j++) {
				questionDescList.add(queService.showQuestion(svManagerList.get(j).getQID()));
			}
			//totalQuestionDescList.add(questionDescList);

			answers = ansService.getAnswers(endSurList.get(x).getIngSeq());			
			//totalAnswers.add(answers);

			
			for (int l = 0; l < trandList.size(); l++) {
				for(int k=0; k<sBigTrandList.size(); k++) {
					if(trandList.get(l).getBigTrandID() == sBigTrandList.get(k).getBigTrandID()) {				
						for (int n = 0; n < attendList.size(); n++) {
							int total = 0;
							StudentScore students = new StudentScore();
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
																if (answers.get(r).getAnswerValue().equals(questionDescList.get(o).getQue_answer())) {
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
							
							students.setSid(attendList.get(n).getStu_id());
							students.setName(attendList.get(n).getName());
							students.setIngSeq(endSurList.get(x).getIngSeq());
							students.settType(trandList.get(l).getQ_trandType());
							students.setBigTrandType(trandList.get(l).getBigTrandID());
							students.setScore(total);
							
							System.out.println(attendList.get(n).getStu_id());
							System.out.println(attendList.get(n).getName());      
							System.out.println(endSurList.get(x).getIngSeq());    
							System.out.println(trandList.get(l).getQ_trandType());
							System.out.println(trandList.get(l).getBigTrandID());
							System.out.println(total);                            
							
							stu_scoreList.add(students);
						}
					}
				}
			}
		}

		/*
		 * System.out.println(totalSvManagerList.size());
		 * System.out.println(totalAnswers.size());
		 * 
		 * for(int i=0; i<totalAnswers.size(); i++) { for(int j=0;
		 * j<totalAnswers.get(i).size(); j++) { System.out.println("응답아이디 : " +
		 * totalAnswers.get(i).get(j).getAnswerID()); System.out.println("응답 : " +
		 * totalAnswers.get(i).get(j).getAnswerValue()); System.out.println("문항아이디 : " +
		 * totalAnswers.get(i).get(j).getQID()); System.out.println("응답 학생 : " +
		 * totalAnswers.get(i).get(j).getStudentID()); System.out.println("또래지명? : " +
		 * totalAnswers.get(i).get(j).isMultiAnswer()); } }
		 * 
		 * for(int i=0; i<totalSvManagerList.size(); i++) { for(int j=0;
		 * j<totalSvManagerList.get(i).size(); j++) { System.out.println("문항아이디 : " +
		 * totalSvManagerList.get(i).get(j).getQID()); System.out.println("문항시퀀스 : " +
		 * totalSvManagerList.get(i).get(j).getQseq()); System.out.println("매니저아이디 : " +
		 * totalSvManagerList.get(i).get(j).getSMID()); System.out.println("설문넘버 : " +
		 * totalSvManagerList.get(i).get(j).getSurveyNo()); } }
		 * System.out.println(totalSvManagerList.size());
		 * System.out.println(totalSvManagerList.get(0).size());
		 * System.out.println(totalSvManagerList.get(1).size()); System.out.println();
		 * System.out.println(questionDescList.size()); for(int i=0;
		 * i<questionDescList.size(); i++) {
		 * System.out.println(questionDescList.get(i).getQue_title()); }
		 * System.out.println();
		 * 
		 * for(int i=0; i<totalQuestionDescList.size(); i++) { for(int j=0;
		 * j<totalQuestionDescList.get(i).size(); j++) { System.out.println();
		 * System.out.println("문항아이디: " + totalQuestionDescList.get(i).get(j).getQID());
		 * System.out.println("문항 타이틀 : " +
		 * totalQuestionDescList.get(i).get(j).getQue_title());
		 * System.out.println("문항 척도 : " +
		 * totalQuestionDescList.get(i).get(j).getQue_trandTitle());
		 * System.out.println("문항 타입 : " +
		 * totalQuestionDescList.get(i).get(j).getQue_typeTitle());
		 * System.out.println(); } }
		 * 
		 * for(int i=0; i<totalBigTrandList.size(); i++) { for(int j=0;
		 * j<totalBigTrandList.get(i).size(); j++) { System.out.println("척도분류 아이디 : " +
		 * totalBigTrandList.get(i).get(j).getBigTrandID());
		 * System.out.println("척도분류 이름 : " +
		 * totalBigTrandList.get(i).get(j).getDescript());
		 * System.out.println("척도분류 설명 : " +
		 * totalBigTrandList.get(i).get(j).getExplan()); System.out.println("공개여부 : " +
		 * totalBigTrandList.get(i).get(j).getIsShowing()); } }
		 * 
		 * 
		 * for (AllDescQuestionDTO dto : questionDescList) {
		 * System.out.println("문항 아이디 : " + dto.getQID());
		 * System.out.println("문항 타이틀 : " + dto.getQue_title());
		 * System.out.println("문항 척도" + dto.getQue_trandTitle());
		 * System.out.println("정답 : " + dto.getQue_answer()); System.out.println("문항 타입"
		 * + dto.getQue_typeID()); } allType = queService.showAllType();
		 * 
		 * System.out.println(questionDescList.size());
		 */

		/*
		 * for(int i=0; i<endSurList.size(); i++) { for(int j=0;
		 * j<totalBigTrandList.size(); j++) { for(int k=0;
		 * k<totalBigTrandList.get(j).size(); k++) { for(int l=0;
		 * l<totalTrandList.size(); l++) { for(int m=0; m<totalTrandList.get(l).size();
		 * m++) { if(totalBigTrandList.get(j).get(k).getBigTrandID() ==
		 * totalTrandList.get(l).get(m).getBigTrandID()) { for(int n=0;
		 * n<attendList.size(); n++) { int total = 0; StudentScore students = new
		 * StudentScore(); for(int o=0; o<totalQuestionDescList.size(); o++) { for(int
		 * p=0; p<totalQuestionDescList.get(o).size(); p++) { for(int q=0;
		 * q<allType.size(); q++) {
		 * if(totalQuestionDescList.get(o).get(p).getQue_typeID() ==
		 * allType.get(q).getQ_typeID()) {
		 * if(totalQuestionDescList.get(o).get(p).getQue_trandTitle().equals(
		 * totalTrandList.get(l).get(m).getQ_trandDescipt())) { for(int r=0;
		 * r<totalAnswers.size(); r++) { for(int s=0; s<totalAnswers.get(r).size(); s++)
		 * {
		 * 
		 * if(totalQuestionDescList.get(o).get(p).getQID() ==
		 * totalAnswers.get(r).get(s).getQID()) { if(attendList.get(n).getStu_id() ==
		 * totalAnswers.get(r).get(s).getStudentID()) {
		 * if(totalAnswers.get(r).get(s).getIngSeq() == endSurList.get(i).getIngSeq()) {
		 * int score = 0; if(totalQuestionDescList.get(o).get(p).isQue_isReverseType()
		 * == false) { if(totalAnswers.get(r).get(s).getAnswerValue().equals("1")) score
		 * = 5; if(totalAnswers.get(r).get(s).getAnswerValue().equals("2")) score = 4;
		 * if(totalAnswers.get(r).get(s).getAnswerValue().equals("3")) score = 3;
		 * if(totalAnswers.get(r).get(s).getAnswerValue().equals("4")) score = 2;
		 * if(totalAnswers.get(r).get(s).getAnswerValue().equals("5")) score = 1;
		 * 
		 * total = score + total; } else if(allType.get(q).getDescript().equals("또래지명"))
		 * { continue; } else if(allType.get(q).getDescript().equals("주관식")) {
		 * if(totalAnswers.get(r).get(s).getAnswerValue().equals(totalQuestionDescList.
		 * get(o).get(p).getQue_answer())) { total = total + 1; } } else
		 * if(totalQuestionDescList.get(o).get(p).isQue_isReverseType() == true) { score
		 * = Integer.parseInt(totalAnswers.get(r).get(s).getAnswerValue().trim()); total
		 * = score + total; } } } } } } } } } } }
		 * students.setSid(attendList.get(n).getStu_id());
		 * students.setName(attendList.get(n).getName());
		 * students.setIngSeq(endSurList.get(i).getIngSeq());
		 * students.settType(totalTrandList.get(l).get(m).getQ_trandType());
		 * students.setBigTrandType(totalTrandList.get(l).get(m).getBigTrandID());
		 * students.setScore(total); stu_scoreList.add(students); } } } } } } }
		 */

		/*
		 * for (int m = 0; m < trandList.size(); m++) { for (int k = 0; k <
		 * attendList.size(); k++) { int total = 0; StudentScore students = new
		 * StudentScore(); for (int i = 0; i < questionDescList.size(); i++) { for (int
		 * l = 0; l < allType.size(); l++) { if (questionDescList.get(i).getQue_typeID()
		 * == allType.get(l).getQ_typeID()) { if
		 * (questionDescList.get(i).getQue_trandTitle().equals(trandList.get(m).
		 * getQ_trandDescipt())) { for (int j = 0; j < answers.size(); j++) { if
		 * (questionDescList.get(i).getQID() == answers.get(j).getQID()) { if
		 * (attendList.get(k).getStu_id() == answers.get(j).getStudentID()) {
		 * 
		 * int score = 0; if (questionDescList.get(i).isQue_isReverseType() == false) {
		 * 
		 * if (answers.get(j).getAnswerValue().equals("1")) score = 5; if
		 * (answers.get(j).getAnswerValue().equals("2")) score = 4; if
		 * (answers.get(j).getAnswerValue().equals("3")) score = 3; if
		 * (answers.get(j).getAnswerValue().equals("4")) score = 2; if
		 * (answers.get(j).getAnswerValue().equals("5")) score = 1;
		 * 
		 * total = score + total;
		 * 
		 * } else if(allType.get(l).getDescript().equals("또래지명")) { continue; } else
		 * if(allType.get(l).getDescript().equals("주관식")) { continue; } else if
		 * (questionDescList.get(i).isQue_isReverseType() == true) { score =
		 * Integer.parseInt(answers.get(j).getAnswerValue().trim()); total = score +
		 * total; }
		 * 
		 * } } } } } } } students.setSid(attendList.get(k).getStu_id());
		 * students.setName(attendList.get(k).getName()); //students.setIngSeq(ingSeq);
		 * students.settType(trandList.get(m).getQ_trandType());
		 * students.setScore(total); stu_scoreList.add(students); } }
		 */

		ArrayList<String> totalArray = new ArrayList<>();
		ArrayList<String> trandArray = new ArrayList<>();
		ArrayList<String> stuArray = new ArrayList<>();
		Gson gson = new Gson();
		JsonObject totalobject = new JsonObject();
		JsonObject trandobject = new JsonObject();
		JsonObject stuobject = new JsonObject();

		for (int i = 0; i < stu_scoreList.size(); i++) {
			totalobject.addProperty("sId", stu_scoreList.get(i).getSid());
			totalobject.addProperty("sName", stu_scoreList.get(i).getName());
			totalobject.addProperty("ingSeq", stu_scoreList.get(i).getIngSeq());
			totalobject.addProperty("trand", stu_scoreList.get(i).gettType());
			totalobject.addProperty("bigtrand", stu_scoreList.get(i).getBigTrandType());
			totalobject.addProperty("score", stu_scoreList.get(i).getScore());

			String json = gson.toJson(totalobject);
			totalArray.add(json);
		}

		for (QuestionTrandTypeDTO dto : trandList) {
			trandobject.addProperty("trandID", dto.getQ_trandType());
			trandobject.addProperty("trandDes", dto.getQ_trandDescipt());

			String json = gson.toJson(trandobject);
			trandArray.add(json);
		}

		for (StudentDTO dto : attendList) {
			stuobject.addProperty("sId", dto.getStu_id());
			stuobject.addProperty("name", dto.getName());
			stuobject.addProperty("scid", dto.getScid());
			stuobject.addProperty("grade", dto.getGrade());
			stuobject.addProperty("classes", dto.getGrd_num());
			stuobject.addProperty("numb", dto.getNum());

			String json = gson.toJson(stuobject);
			stuArray.add(json);
		}

		ArrayList<String> bigTrandArray = new ArrayList<>();
		JsonObject bigTrandObject = new JsonObject();
		for (QuestionTrandManagerDTO dto : bigTrandList) {
			bigTrandObject.addProperty("bigTID", dto.getBigTrandID());
			bigTrandObject.addProperty("desc", dto.getDescript());
			bigTrandObject.addProperty("explan", dto.getExplan());

			String json = gson.toJson(bigTrandObject);
			bigTrandArray.add(json);
		}
		
		ArrayList<String> endSurArray = new ArrayList<>();
		JsonObject endSurObject = new JsonObject();
		for (SearchEndsurveyDTO dto : endSurList) {
			endSurObject.addProperty("surNo", dto.getSurveyNo());
			endSurObject.addProperty("ingSeq", dto.getIngSeq());
			endSurObject.addProperty("endDate", dto.getEndDate().toString());
			endSurObject.addProperty("title", dto.getTitle());

			String json = gson.toJson(endSurObject);
			endSurArray.add(json);
		}
		
		ArrayList<QuestionTrandTypeDTO> allTrand = new ArrayList<>();
		ArrayList<QuestionTrandTypeDTO> allMixedTrand = new ArrayList<>();
		allTrand = queService.showAllTrand();
		for(int i=0; i<allTrand.size(); i++) {
			System.out.println("빅트아디" + allTrand.get(i).getBigTrandID());
			System.out.println("트아디" + allTrand.get(i).getQ_trandType());
			System.out.println("트설명" + allTrand.get(i).getQ_trandDescipt());
		}
		for(int i=0; i<bigTrandList.size(); i++) {
			System.out.println("빅트아디" + bigTrandList.get(i).getBigTrandID());
			System.out.println("빅트이름" + bigTrandList.get(i).getDescript());
			System.out.println("빅트설명" + bigTrandList.get(i).getExplan());
		}
		
		for(int j=0; j<allTrand.size(); j++) {
			for(int i=0; i<bigTrandList.size(); i++) {
				if(bigTrandList.get(i).getBigTrandID() == allTrand.get(j).getBigTrandID()) {
					QuestionTrandTypeDTO qttDTO = new QuestionTrandTypeDTO();
					qttDTO.setBigTrandID(bigTrandList.get(i).getBigTrandID());
					qttDTO.setQ_trandDescipt(allTrand.get(j).getQ_trandDescipt());
					qttDTO.setQ_trandType(allTrand.get(j).getQ_trandType());
					allMixedTrand.add(qttDTO);
				}
				else continue;
			}
		}
		
		for(int i=0; i<allMixedTrand.size(); i++) {
			System.out.println("빅트아디" + allMixedTrand.get(i).getBigTrandID());
			System.out.println("트아디" + allMixedTrand.get(i).getQ_trandType());
			System.out.println("트설명" + allMixedTrand.get(i).getQ_trandDescipt());
		}
		
		ArrayList<String> mixedTrand = new ArrayList<>();
		JsonObject mixTrandObject = new JsonObject();
		for (QuestionTrandTypeDTO dto : allMixedTrand) {
			mixTrandObject.addProperty("bigTrand", dto.getBigTrandID());
			mixTrandObject.addProperty("trandType", dto.getQ_trandType());
			mixTrandObject.addProperty("trandDesc", dto.getQ_trandDescipt());			

			String json = gson.toJson(mixTrandObject);
			mixedTrand.add(json);
		}

		// System.out.println(totalArray);
		// System.out.println(trandArray);
		// System.out.println(stuArray);

		// request.setAttribute("survey_no", survey_no);
		// request.setAttribute("ingSeq", ingSeq);
		request.setAttribute("scid", scid);
		request.setAttribute("startdate", start);
		request.setAttribute("enddate", end);
		request.setAttribute("title", title);
		request.setAttribute("stu_scoreList", stu_scoreList);
		request.setAttribute("totalArray", totalArray);
		request.setAttribute("trandArray", trandArray);
		request.setAttribute("stuArray", stuArray);
		request.setAttribute("sch_name", sch_name);
		request.setAttribute("bigTrandList", bigTrandList);
		request.setAttribute("bigTrandArray", bigTrandArray);
		request.setAttribute("endSurList", endSurList);
		request.setAttribute("endSurArray", endSurArray);
		request.setAttribute("allMixedTrand", allMixedTrand);
		request.setAttribute("mixedTrand", mixedTrand);

		// RequestDispatcher dc =
		// request.getRequestDispatcher("/page_tea/SurveyResult/resultSurvey.jsp");
		RequestDispatcher dc = request.getRequestDispatcher("/page_common/privateResult.jsp");
		dc.forward(request, response);

	}

}
