package Service;

import User.UserDAO.TeacherDAO;
import User.UserDTO.TeacherDTO;

public class TeacherService {

	private static TeacherService service = new TeacherService();
	private TeacherService() {}
	TeacherDAO dao =  TeacherDAO.getInstance();
	public static TeacherService getInstance() {
		return service;
	}
	
	public boolean isUserRegist(String TID) {
		return dao.isUserRegist(TID);
	}
	
	public int teacherRegister(TeacherDTO t) {
		return dao.teacherRegister(t);
	}
	
	public boolean getEamilChecked(String TID) {
		return dao.getEamilChecked(TID);
	}
	
	public boolean setEmailCheck(String TID) {
		return dao.setEmailCheck(TID);
	}

	public int TeacherLogin(String tea_id, String pass) {
		return dao.teacherLogin(tea_id,pass);
	}
	public TeacherDTO teacherInfo(String tea_id) {
		return dao.teacherInfo(tea_id);
	}
	public boolean teacherSchoolUpdate(String scid, int grade, int grd_num) {
		return dao.teacherSchoolUpdate(scid, grade, grd_num);
	}
}
