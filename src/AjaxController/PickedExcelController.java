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
import SurveyRelationDTO.StudentScoresDTO;
import SurveyRelationDTO.SurveyAnswerDTO;
import SurveyRelationDTO.SurveyGoingDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import User.UserDTO.StudentDTO;

public class PickedExcelController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		//해당 설문에서의 모든문항 리스트
		ArrayList<SurveyManagerDTO> svmList = SurveyService.getInstance().showQuestionsToManager(surNo);
		ArrayList<AllDescQuestionDTO> questionDescList = new ArrayList<>();
		for(int i=0; i<svmList.size(); i++) {
			questionDescList.add(QuestionService.getInstance().showQuestion(svmList.get(i).getQID()));
		}
		
		Map<Integer, AllDescQuestionDTO> queMap = new HashMap<>();
		for(AllDescQuestionDTO dto : questionDescList) {
			if(dto.getQue_typeID() == Constant.PEERTYPEID) {
				queMap.put(dto.getQID(), dto);
			}
			else continue;
		}
		
		//워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		for(int queKey : queMap.keySet()) {
			//워크시트 생성
			XSSFSheet sheet = workbook.createSheet(queMap.get(queKey).getQID() + "");
			//행 생성
			XSSFRow row = sheet.createRow(0);
			//행에 대한 셀 생성
			XSSFCell cell;
			
			//문항별 또래지명 데이터를 가져옴
			List<SurveyAnswerDTO> multiAnswers = AnswerService.getInstance().getMultiAnswersInQuestion(
																				seq, scid, grade, grdNum, queKey);
			
			Map<Integer, SurveyAnswerDTO> ansMap = new HashMap<>();
//			for(SurveyAnswerDTO dto : multiAnswers) {
//				ansMap.put(dto.getAnswerID(), dto);
//			}
			for(int i=0; i<multiAnswers.size(); i++) {
				ansMap.put(multiAnswers.get(i).getAnswerID(), multiAnswers.get(i));
			}
			
			//문항별 개인 또래지명 점수
			List<StudentScoresDTO> privateScores = PeerScoreCalculate.calculateQuestionsPeerScore(seq, multiAnswers, attendList);
			
			//1행2열에 문항의 제목을 넣어줌
			cell = row.createCell(1);
			cell.setCellValue(queMap.get(queKey).getQue_title());
			
			//2행 개행
			row = sheet.createRow(1);			
			for(int i=0; i<attendList.size(); i++) {
				cell = row.createCell(0);	
				cell.setCellValue("id");	//2행 1열은 id로 고정
				cell = row.createCell(i+1);
				cell.setCellValue(attendList.get(i).getName()); //2행의 id 이외 모든 데이터를 학생이름으로 출력
			}
			
			for(int i=0; i<attendList.size(); i++) {
				row = sheet.createRow(i+2);
				cell = row.createCell(0);
				cell.setCellValue(attendList.get(i).getName()); //1열의 모든 행 데이터를 학생이름으로 출력
				
				List<Integer> pickedList = new ArrayList<>();
				for(SurveyAnswerDTO dto : multiAnswers) {
					if(dto.getStudentID() == attendList.get(i).getStu_id()) {
						pickedList.add(dto.getMultiAnswers());	//학생이 문항에서 선택한 multiAnswer를 새로운 List에 저장
					}
				}
				
				for(int j=0; j<attendList.size(); j++) {	//열의 반복을 위한 for문
					cell = row.createCell(j+1);
					cell.setCellValue(0);					//행 데이터를 0으로 초기화함
					for(int k=0; k<multiAnswers.size(); k++) {
						if(attendList.get(i).getStu_id() == multiAnswers.get(k).getStudentID()	//multiAnswer의 응답자가 현재 행에서 탐색중인 학생일때
								&& pickedList.contains(multiAnswers.get(k).getMultiAnswers())	//해당 학생이 응답한 데이터들중에 돌고있는 multiAnswer가 있을 때
								&& multiAnswers.get(k).getMultiAnswers() == attendList.get(j).getStu_id()){	//multiAnswer와 학생 열데이터와 같다면
							cell = row.createCell(j+1);
							cell.setCellValue(1);
						}
					}
				}
			}			
			
			row = sheet.createRow(attendList.size() + 3);
			cell = row.createCell(0);
			cell.setCellValue("Score");
			for(int j=0; j<attendList.size(); j++) {				
				for(int k=0; k<privateScores.size(); k++) {
					if(privateScores.get(k).getStu_id() == attendList.get(j).getStu_id()) {
						cell = row.createCell(j+1);
						cell.setCellValue(Double.parseDouble(String.format("%.3f", privateScores.get(k).getScore())));
					}
					else continue;
				}
			}
		}
		
		//TODO test FileStream

		String sFileName = school.getName() + "_" + grade + "-" + grdNum + "_" + "PeerData_" + survey.getEndDate() + ".xlsx";                                     		
		
		String path = "C:\\PeerLab\\" + school.getName() + "\\" + survey.getGrade() + "학년\\" + survey.getEndDate();
		CommonUtil.createFolder(path);
		
		JsonObject obj = new JsonObject();
		PrintWriter pw = response.getWriter();

		File file = new File(path + "\\" + sFileName);			
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
                
                obj.addProperty("result", "다운로드 성공! C드라이브의 PeerLab 폴더에서 학교를 확인 해 주세요.");
        		pw.print(obj);
        		pw.flush();
        		pw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
}
