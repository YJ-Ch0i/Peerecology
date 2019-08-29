package AjaxController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonObject;

import Common.CommonUtil;
import Common.Constant;
import Controller.Controller;
import Service.QuestionService;
import Service.StudentService;
import Service.SurveyService;
import SurveyRelationDTO.AllDescQuestionDTO;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import User.UserDTO.StudentDTO;

public class ComputedExcelDownloadController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String scid = request.getParameter("scid");
		int grade = CommonUtil.strToInt(request.getParameter("grade"));
		int grdNum = CommonUtil.strToInt(request.getParameter("grdNum"));
		String year = request.getParameter("year");
		int surNo = CommonUtil.strToInt(request.getParameter("surNo"));
		int seq = CommonUtil.strToInt(request.getParameter("seq"));
		
		//재학생 리스트
		List<StudentDTO> attendList = StudentService.getInstance().studentListAttend3(scid, grade, grdNum, year);
		
		//해당 설문에서 척도 리스트
		List<QuestionTrandTypeDTO> trandList = QuestionService.getInstance().searchTrandList(surNo, seq, scid);
		
//		List<StudentScoresDTO> stuScoreDtoList = SurveyService.getInstance().getClassesAllScores(scid, grade, grdNum, year);																				
//		List<StudentScoresDTO> peerScorelist = PeerScoreCalculate.calculatePeerScore(Constant.PEERID, scid, grade, grdNum, year);																			
		
		
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
		for(int i=0; i<trandList.size(); i++) {
			cell = row.createCell(i+1);
			cell.setCellValue(trandList.get(i).getQ_trandType() + trandList.get(i).getQ_trandDescipt()); //척도아이디
		}
	
		List<StudentScoresDTO> stuPrivateScoreList = new ArrayList<>();
		List<StudentScoresDTO> stuPrivatePeerScoreList = new ArrayList<>();
		
		for(int i=0; i<attendList.size(); i++) {
			row = sheet.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellValue(attendList.get(i).getName()); //학생이름
			//cell.setCellValue(attendList.get(i).getStu_id()); //학생아이디
			stuPrivateScoreList = SurveyService.getInstance().getStudentScores(scid, grade, grdNum, attendList.get(i).getStu_id(), year);
			stuPrivatePeerScoreList = PeerScoreCalculate.calculatePeerScoreRadar(attendList.get(i).getStu_id(), scid, grade, grdNum, year);
			for(int l=0; l<trandList.size(); l++) {
				if(trandList.get(l).getBigTrandID() == Constant.PEERID) {
					for(int k=0; k<stuPrivatePeerScoreList.size(); k++) {
						if(trandList.get(l).getQ_trandType() == stuPrivatePeerScoreList.get(k).getTrandId() 
								&& attendList.get(i).getStu_id() == stuPrivatePeerScoreList.get(k).getStu_id()) {
							cell = row.createCell(l+1);
							cell.setCellValue(Double.parseDouble(String.format("%.3f", stuPrivatePeerScoreList.get(k).getScore()))); 
						}
						else continue;
						//Double.parseDouble(String.format("%.2f",per2)

					}
				}
				else if(trandList.get(l).getBigTrandID() != Constant.PEERID) {
					for(int k=0; k<stuPrivateScoreList.size(); k++) {
						if(trandList.get(l).getQ_trandType() == stuPrivateScoreList.get(k).getTrandId() 
								&& attendList.get(i).getStu_id() == stuPrivateScoreList.get(k).getStu_id()) {
							cell = row.createCell(l+1);
							cell.setCellValue(stuPrivateScoreList.get(k).getScore()); 
						}
						else continue;
					}
				}
			}				
		}
		
		//워크시트 생성
		XSSFSheet sheet2 = workbook.createSheet();
		//행 생성
		XSSFRow row2 = sheet2.createRow(0);
		//행에 대한 셀 생성
		XSSFCell cell2;
		
		QuestionService queService = QuestionService.getInstance();
		SurveyService surService = SurveyService.getInstance();
		ArrayList<SurveyManagerDTO> svManagerList = new ArrayList<>();
		
		svManagerList = surService.showQuestionsToManager(surNo);
		ArrayList<AllDescQuestionDTO> questionDescList = new ArrayList<>();
		for (int j = 0; j < svManagerList.size(); j++) {
			questionDescList.add(queService.showQuestion(svManagerList.get(j).getQID()));
		} //회차에 해당하는 설문의 문항들을 가져옴
		
		//1행1열은 id로 고정		
		cell2 = row2.createCell(0);
		cell2.setCellValue("학년");
		cell2 = row2.createCell(1);
		cell2.setCellValue("반");
		cell2 = row2.createCell(2);
		cell2.setCellValue("번호");
		cell2 = row2.createCell(3);
		cell2.setCellValue("ID");
		
		//1행 2열부터 문항의 id를 입력. 현재는 문항의 이름
		for(int i=0; i<questionDescList.size(); i++) {
			cell2 = row2.createCell(i+4);
			cell2.setCellValue(questionDescList.get(i).getQue_title()); //문항이름
			if(queService.showAllType(questionDescList.get(i).getQue_typeID()).getQ_typeID() == Constant.PEERTYPEID){
				//sheet2.addMergedRegion(new CellRangeAddress(0, 0, i+4, i+4+attendList.size()));
				//cell2 = row2.createCell(i+4+attendList.size());
				//cell2.setCellValue(questionDescList.get(i).getQue_title());
			}
			else continue;
			
			
			
			//sheet.addMergedRegion();
		}
		
		File file = new File("C:\\Users\\yeong\\Downloads\\testWrite.xlsx");
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
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        JsonObject obj = new JsonObject();
        obj.addProperty("result", "다운로드 성공! 다운로드 폴더를 확인 해 주세요.");
        PrintWriter pw = response.getWriter();
		pw.print(obj);
		pw.flush();
		pw.close();
	}
}
