package Service;

import java.util.ArrayList;

import User.UserDAO.StudentDAO;
import User.UserDTO.StudentItem;

public class StudentService {
	private static StudentService service = new StudentService();
	private StudentService() {}
	StudentDAO dao =  StudentDAO.getInstance();
	public static StudentService getInstance() {
		return service;
	}
	
	public ArrayList<StudentItem> LoadStudent(String file_name, String upload_path) {
		return dao.LoadStudent(file_name, upload_path);
	}
}
