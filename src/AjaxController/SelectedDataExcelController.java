package AjaxController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.CommonUtil;
import Common.Constant;
import Controller.Controller;
import School.SchoolDTO.SchoolDTO;
import Service.AnswerService;
import Service.QuestionService;
import Service.SchoolService;
import Service.StudentService;
import Service.SurveyService;
import SurveyRelationDAO.SurveyDAO;
import SurveyRelationDTO.AllDescQuestionDTO;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyGoingDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import User.UserDTO.StudentDTO;

public class SelectedDataExcelController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String scid = request.getParameter("scid");
		int grade = CommonUtil.strToInt(request.getParameter("grade"));
		int grdNum = CommonUtil.strToInt(request.getParameter("grdNum"));
		String year = request.getParameter("year");
		int surNo = CommonUtil.strToInt(request.getParameter("surNo"));
		int seq = CommonUtil.strToInt(request.getParameter("seq"));
		
		//학교정보
		SchoolDTO school = SchoolService.getInstance().getSchoolToSCID(scid);
		//설문회차정보
		SurveyGoingDTO survey = SurveyDAO.getInstance().getSeq(seq);
				
		//재학생 리스트
		List<StudentDTO> attendList = StudentService.getInstance().studentListAttend3(scid, grade, grdNum, year);
		
		//해당 설문에서 문항 리스트
		ArrayList<SurveyManagerDTO> svmList = SurveyService.getInstance().showQuestionsToManager(surNo);
		ArrayList<AllDescQuestionDTO> questionDescList = new ArrayList<>();
		for(int i=0; i<svmList.size(); i++) {
			questionDescList.add(QuestionService.getInstance().showQuestion(svmList.get(i).getQID()));
		}		
		
		//응답들을 찾아 HashMap에 저장
		ArrayList<SurveyAnswerDTO> answers = AnswerService.getInstance().getAnswers(seq);
		Map<Integer, SurveyAnswerDTO> answersMap = new HashMap<>();
		for(SurveyAnswerDTO dto : answers) {
			answersMap.put(dto.getAnswerID(), dto);
		}
		
		//워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		//워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
		//행 생성
		XSSFRow row = sheet.createRow(0);
		//행에 대한 셀 생성
		XSSFCell cell;
		
		//1행1열은 id로 고정
		cell = row.createCell(0);
		cell.setCellValue("id");
		
		//1행 2열부터 척도의 id를 입력
		for(int i=0; i<questionDescList.size(); i++) {
			cell = row.createCell(i+1);
			//cell.setCellValue(questionDescList.get(i).getQue_title()); //문항의 제목
			cell.setCellValue(questionDescList.get(i).getQue_title()); //문항의 제목
		}
		
		//학생별로 선택한 데이터들을 입력
		for(int i=0; i<attendList.size(); i++) {
			row = sheet.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellValue(attendList.get(i).getName()); //학생이름
			
			for(int l=0; l<questionDescList.size(); l++) {				
				if(questionDescList.get(l).getQue_typeID() != Constant.PEERTYPEID) {	//문항타입 != 또래지명
					for(int answerKey : answersMap.keySet()) {
						if(answersMap.get(answerKey).getQID() == questionDescList.get(l).getQID()
								&& attendList.get(i).getStu_id() == answersMap.get(answerKey).getStudentID()) {
							if(CommonUtil.isNullString(questionDescList.get(l).getQue_answer())) {															
								cell = row.createCell(l+1);
								cell.setCellValue(answersMap.get(answerKey).getAnswerValue().toString());
							}
							else if(CommonUtil.isNotNullString(questionDescList.get(l).getQue_answer())) {
								if(CommonUtil.isEqualOrContainsString(questionDescList.get(l).getQue_answer(), answersMap.get(answerKey).getAnswerValue())) {
									cell = row.createCell(l+1);
									cell.setCellValue("1" + " // " + answersMap.get(answerKey).getAnswerValue());
								}
								else if(CommonUtil.isNotEqualOrContainsString(questionDescList.get(l).getQue_answer(), answersMap.get(answerKey).getAnswerValue())) {																		
									cell = row.createCell(l+1);
									cell.setCellValue("0" + " // " + answersMap.get(answerKey).getAnswerValue());
								}
							}							
						}						
					}
				}
				else if(questionDescList.get(l).getQue_typeID() == Constant.PEERTYPEID) {
					cell = row.createCell(l+1);						
					cell.setCellValue("PeerData");
				}
				else continue;
			}
		}
		
		
		for(int l=0; l<questionDescList.size(); l++) {	
			sheet.autoSizeColumn(l);			
		}
					
		
		//TODO test FileStream

		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String sFileName = school.getName() + "_" + grade + "-" + grdNum + "_" + "SelectedData_" + survey.getEndDate() + ".xlsx";                                     		
		
		//로컬용
//		String path = "C:\\PeerLab\\" + school.getName() + "\\" + survey.getGrade() + "학년\\" + survey.getEndDate();
		
		//서버용
		String path = "PeerLab/" + school.getName() + "/" + survey.getGrade() + "학년/" + survey.getEndDate();
		String realPath = uploadPath + path;
		
		CommonUtil.createFolder(realPath);
		
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		PrintWriter pw = response.getWriter();

		File file = new File(realPath + "/" + sFileName);			
        FileOutputStream fos = null;	        
        
        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook!=null) workbook.close();
                if(fos!=null) fos.close();	
                
                obj.addProperty("filePath", "/PeerSys/upload/" + path + "/" + sFileName);
	            String json = gson.toJson(obj);
	            
	    		pw.print(json);
        		pw.flush();
        		pw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
}
