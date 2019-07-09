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
	
	public ArrayList<StudentItem> LoadStudent(String file_name, String upload_path) {
		return dao.LoadStudent(file_name, upload_path);
	}
	
	public boolean studentRegist(StudentDTO dto) {
		return dao.studentRegist(dto);
	}
	
	public ArrayList<StudentDTO> studentList(String TID, String SCID) {
		return dao.studentList(TID, SCID);
	}
	
	public boolean studentTransfer(StudentDTO dto) {
		return dao.studentTransfer(dto);
	}
	
	public int studentLogin(StudentDTO dto) {		
		return dao.studentLogin(dto);
	}
}
