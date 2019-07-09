package Service;

import java.util.ArrayList;

import School.SchoolDAO.*;
import School.SchoolDTO.*;

public class SchoolService {
	private static SchoolService service = new SchoolService();
	private SchoolService() {}
	SchoolDAO dao =  SchoolDAO.getInstance();
	public static SchoolService getInstance() {
		return service;
	}
	
	public ArrayList<SchoolDTO> SearchSchoolAddress(String sch_name) {
		return dao.SearchSchoolAddress(sch_name);
	}
	
	public boolean isSchoolAlreadyRegister(String sch_code) {
		return dao.isSchoolAlreadyRegister(sch_code);
	}
	
	public int SchoolRegist(String sch_address, String sch_name, String sch_code) {
		return dao.SchoolRegist(sch_address, sch_name, sch_code);
	}
	
	public ArrayList<SchoolDTO> school_List() {
		return dao.school_List();
	}
	
	public SchoolDTO getSchoolToSCID(String SCID) {
		return dao.getSchoolToSCID(SCID);
	}
}
