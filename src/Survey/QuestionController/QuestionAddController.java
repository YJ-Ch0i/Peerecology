package Survey.QuestionController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Controller;
import Service.QuestionService;
import SurveyRelationDTO.QuestionDTO;

public class QuestionAddController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strQue_trandTypeID = request.getParameter("que_trandTypeID");
		String strQue_typeID = request.getParameter("que_typeID");
		String que_title = request.getParameter("que_title");
		String strIsReverseType = request.getParameter("isReverseType");
		String strQ_typeDirection = request.getParameter("q_typeDirection");
		boolean q_typeDirection = true;
		if(strQ_typeDirection.equals("2")) q_typeDirection = false;
		int que_trandTypeID = Integer.parseInt(strQue_trandTypeID);
		int que_typeID = Integer.parseInt(strQue_typeID);
		boolean isReverseType = true;
		if(strIsReverseType.equals("0")) {isReverseType = false;}
		
		QuestionDTO questionDTO = new QuestionDTO(que_title,que_typeID,que_trandTypeID,isReverseType);
		QuestionService queService = QuestionService.getInstance();

		
		if(que_typeID!=-1) 
		{
			queService.questionRegister(questionDTO);
		}
		else 
		{
			String type_title = request.getParameter("type_title");
			String strOfferSeq = request.getParameter("offerSeq");
			int offerSeq = Integer.parseInt(strOfferSeq);
			
			queService.queTypeRegister(type_title, offerSeq,q_typeDirection);
			
			if(offerSeq!=0)
			{
				String titles = "";
				for(int i=0; i<offerSeq; i++)
				{
					titles += request.getParameter("type_OfferTitle"+(i+1))+",";
				}
				queService.questionOfferRegister(offerSeq,titles);
			}
			queService.questionDifferentRegister(questionDTO);
		}
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('Success!')");
		script.println("parent.document.location.reload();");
		script.println("</script>");
		script.close();
		return;
	}

}
