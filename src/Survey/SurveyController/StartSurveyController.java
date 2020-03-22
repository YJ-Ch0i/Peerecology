package Survey.SurveyController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.CommonUtil;
import Controller.Controller;
import Service.AnswerService;
import Service.QuestionService;
import Service.SurveyService;
import SurveyRelationDTO.QuestionOfferDTO;
import SurveyRelationDTO.SurveyGoingDTO;
import SurveyRelationDTO.SurveyManagerDTO;

public class StartSurveyController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		String sch_code = (String)session.getAttribute("sch_code");
		String grade = (String) session.getAttribute("grade");
		AnswerService ansService = AnswerService.getInstance();
		SurveyService surService = SurveyService.getInstance();
//		String strStu_id =  (String)session.getAttribute("stu_id");
//		int stu_id = Integer.parseInt(strStu_id);
		int stu_id = CommonUtil.strToInt((String)session.getAttribute("stu_id"));
		SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO(); 
		surveyGoingDTO = surService.startSurvey(sch_code, grade);
		if(surveyGoingDTO.getIngSeq() == 0)
		{
			session.removeAttribute("stu_id");
			session.removeAttribute("stu_desc");
			session.removeAttribute("grd_num");
			session.removeAttribute("sch_code");
			session.removeAttribute("stu_num");
			session.removeAttribute("grade");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('진행 가능한 설문조사가 없습니다. 자동으로 로그아웃 되었습니다.');");
			script.println("location.href='/PeerSys/index.jsp'");
			script.println("</script>");
			script.close();
		}
		else
		{
			surveyGoingDTO = surService.findIngSurvey(surveyGoingDTO.getSurveyNo(), sch_code);
			int getQuestionCount = surService.getQuestionCount(surveyGoingDTO.getSurveyNo());
			int getAnswerCount = ansService.getAnswersCount(surveyGoingDTO.getIngSeq(), stu_id);

			System.out.println(getQuestionCount);
			System.out.println(getAnswerCount);
			if(getQuestionCount==getAnswerCount)
			{
				session.removeAttribute("stu_id");
				session.removeAttribute("stu_desc");
				session.removeAttribute("grd_num");
				session.removeAttribute("sch_code");
				session.removeAttribute("stu_num");
				session.removeAttribute("grade");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 설문조사를 완료했습니다. 자동으로 로그아웃 되었습니다.');");
				script.println("location.href='/PeerSys/index.jsp'");
				script.println("</script>");
				script.close();
			}
			// 설문조사를 하지 않은 경우
			else if(getAnswerCount==0) 
			{
				session.setAttribute("surveyNo", surveyGoingDTO.getSurveyNo());
				session.setAttribute("surveyIngNo", surveyGoingDTO.getIngSeq());
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href='/PeerSys/page_stu/SurveyPage.jsp'");
				script.println("</script>");
				script.close();
				return;
			}
			else if(getQuestionCount>getAnswerCount)
			{
				session.setAttribute("surveyNo", surveyGoingDTO.getSurveyNo());
				session.setAttribute("surveyIngNo", surveyGoingDTO.getIngSeq());
				int pageNumbering = getAnswerCount/10+1;
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('아직 다 하지 못한 설문조사를 진행하겠습니다.');");
				script.println("location.href='/PeerSys/page_stu/SurveyPage.jsp?pageNumber="+pageNumbering+"'");
				script.println("</script>");
				script.close();
				return;
			}
		}
	}

}
