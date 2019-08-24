package Service;

import java.util.ArrayList;

import User.UserDAO.*;
import User.UserDTO.*;

public class StudentService {
	private static StudentService service = new StudentService();
	private StudentService() {}
	StudentDAO dao =  StudentDAO.getInstance();
	public static StudentService getInstance() {
		return service;
	}
	
	public StudentDTO getStudent(String name, String TID, String SCID) {
		return dao.getStudent(name, TID, SCID);
	}
	public ArrayList<StudentDTO> findStudent(String name) {
		return dao.findStudent(name);
	}
	public ArrayList<StudentItem> LoadStudent(String file_name, String upload_path) {
		return dao.LoadStudent(file_name, upload_path);
	}
	
	public boolean studentRegist(StudentDTO dto) {
		return dao.studentRegist(dto);
	}
	
	public ArrayList<StudentDTO> studentList(String TID, String SCID) {
		return dao.studentList(TID, SCID);
	}
	public ArrayList<StudentDTO> showAllStudent() {
		return dao.showAllStudent();
	}
	
	public ArrayList<StudentDTO> getSchoolAttendList(String SCID, int grade, String year) {
		return dao.getSchoolAttendList(SCID, grade, year);
	}
	
	public ArrayList<StudentDTO> studentListInClass(String SCID, int grade, int grd_num) {
		return dao.studentListInClass(SCID, grade, grd_num);
	}
	public ArrayList<Integer> getStudentList(String SCID, int grade) {
		return dao.getStudentList(SCID, grade);
	}
	public ArrayList<StudentDTO> studentListAttend (String TID, String SCID, int grade, int grd_num) {
		return dao.studentListAttend(TID, SCID, grade, grd_num);
	}
	public ArrayList<StudentDTO> findStudentToGradeSCID(int SID, String SCID, int grade, int classes){
		return dao.findStudentToGradeSCID(SID,SCID, grade, classes);
	}
	
	public ArrayList<StudentDTO> studentListAttend2(String SCID, int grade, int grd_num, String year) {
		return dao.studentListAttend2(SCID, grade, grd_num, year);
	}
	
	public ArrayList<StudentDTO> studentListAttend3(String SCID, int grade, int grd_num, String year) {
		return dao.studentListAttend3(SCID, grade, grd_num, year);
	}
	
	public boolean studentTransfer(StudentDTO dto) {
		return dao.studentTransfer(dto);
	}
	
	public int studentLogin(StudentDTO dto) {		
		return dao.studentLogin(dto);
	}
	
}
