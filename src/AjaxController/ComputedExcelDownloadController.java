package AjaxController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Common.CommonUtil;
import Common.Constant;
import Controller.Controller;
import Service.QuestionService;
import Service.StudentService;
import Service.SurveyService;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.StudentScoresDTO;
import User.UserDTO.StudentDTO;

public class ComputedExcelDownloadController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//재학생 리스트
		List<StudentDTO> attendList = StudentService.getInstance().studentListAttend3(
																			request.getParameter("scid"),
																			CommonUtil.strToInt(request.getParameter("grade")),
																			CommonUtil.strToInt(request.getParameter("grdNum")),
																			request.getParameter("year"));
		
		//해당 설문에서 척도 리스트
		List<QuestionTrandTypeDTO> trandList = QuestionService.getInstance().searchTrandList(
																				CommonUtil.strToInt(request.getParameter("surNo")), 
																				CommonUtil.strToInt(request.getParameter("seq")), 
																				request.getParameter("scid"));
		
		List<StudentScoresDTO> stuScoreDtoList = SurveyService.getInstance().getClassesAllScores(
																				request.getParameter("scid"),
																				CommonUtil.strToInt(request.getParameter("grade")),
																				CommonUtil.strToInt(request.getParameter("grdNum")),																				
																				request.getParameter("year"));

		List<StudentScoresDTO> peerScorelist = PeerScoreCalculate.calculatePeerScore(
																			Constant.PEERID,
																			request.getParameter("scid"),
																			CommonUtil.strToInt(request.getParameter("grade")),
																			CommonUtil.strToInt(request.getParameter("grdNum")),
																			request.getParameter("year"));
		
		
		System.out.println(peerScorelist);
		System.out.println(stuScoreDtoList);
		for(StudentScoresDTO dto : stuScoreDtoList) {
			System.out.println(dto.getStu_id());
			System.out.println(dto.getsName());
			System.out.println(dto.getTrandId());
			System.out.println(dto.getTrandDesc());
			System.out.println(dto.getScore());
			System.out.println();
		}
		
		for(StudentScoresDTO dto : peerScorelist) {
			System.out.println(dto.getStu_id());
			System.out.println(dto.getsName());
			System.out.println(dto.getTrandId());
			System.out.println(dto.getScore());
			System.out.println();
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
		for(int i=0; i<trandList.size(); i++) {
			cell = row.createCell(i+1);
			cell.setCellValue(trandList.get(i).getQ_trandType() + trandList.get(i).getQ_trandDescipt()); //척도아이디
		}
	
		
		for(int i=0; i<attendList.size(); i++) {
			row = sheet.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellValue(attendList.get(i).getName()); //학생이름
			//cell.setCellValue(attendList.get(i).getStu_id()); //학생아이디
			for(int l=0; l<trandList.size(); l++) {
				if(trandList.get(l).getBigTrandID() == Constant.PEERID) {
					for(int k=0; k<peerScorelist.size(); k++) {
						if(trandList.get(l).getQ_trandType() == peerScorelist.get(k).getTrandId() 
								&& attendList.get(i).getStu_id() == peerScorelist.get(k).getStu_id()) {
							cell = row.createCell(l+1);
							cell.setCellValue(peerScorelist.get(k).getScore()); 
						}
						else continue;
					}
				}
				else if(trandList.get(l).getBigTrandID() != Constant.PEERID) {
					for(int k=0; k<stuScoreDtoList.size(); k++) {
						if(trandList.get(l).getQ_trandType() == stuScoreDtoList.get(k).getTrandId() 
								&& attendList.get(i).getStu_id() == stuScoreDtoList.get(k).getStu_id()) {
							cell = row.createCell(l+1);
							cell.setCellValue(stuScoreDtoList.get(k).getScore()); 
						}
						else continue;
					}
				}
			}				
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
        PrintWriter pw = response.getWriter();
		pw.print("성공");
		pw.flush();
		pw.close();
	}
}
