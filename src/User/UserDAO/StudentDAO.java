package User.UserDAO;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import User.UserDTO.StudentDTO;
import User.UserDTO.StudentItem;
import Util.DBConn;

public class StudentDAO {

	private static StudentDAO dao = new StudentDAO();
	private StudentDAO() {
	}

	public static StudentDAO getInstance() {
		return dao;
	}
	
	
	public ArrayList<StudentItem> LoadStudent(String file_name, String upload_path) {
		ArrayList<StudentItem> resultlist = new ArrayList<StudentItem>();

		int stu_name_index = 0;
		int stu_number_index = 0;
		int stu_gender_index = 0;
		int before_class_index = 0;
		int before_number_index = 0;

		try {
			//InputStreamReader is = new InputStreamReader(new FileInputStream(upload_path + "\\" + file_name), "UTF-8");
			InputStreamReader is = new InputStreamReader(new FileInputStream(upload_path + "/" + file_name), "UTF-8");
			CSVReader reader = new CSVReader(is);
			List<String[]> list = reader.readAll();

			int i = 0;
			for (String[] str : list) {
				StudentItem entity = new StudentItem();

				if (i == 0) {
					int j = 0;
					for (String temp : str) {
						if (temp.equals("이름")) {
							stu_name_index = j;
						}
						if (temp.equals("번호")) {
							stu_number_index = j;
						}
						if (temp.equals("성별")) {
							stu_gender_index = j;
						}
						if (temp.equals("이전 반")) {
							before_class_index = j;
						}
						if (temp.equals("이전 번호")) {
							before_number_index = j;
						}
						j++;
					}
				} else {
					entity.setStu_name(str[stu_name_index]);
					entity.setStu_number(str[stu_number_index]);
					entity.setStu_gender(str[stu_gender_index]);
					entity.setBefore_class(str[before_class_index]);
					entity.setBefore_number(str[before_number_index]);
					resultlist.add(entity);
				}
				i++;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultlist;
	}
	
	public boolean studentRegist(StudentDTO dto) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO user_students(name, SCID, grade, class, num, TID, gender, lastChangeDate) VALUES (?,?,?,?,?,?,?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getScid());
			pstmt.setInt(3, dto.getGrade());
			pstmt.setInt(4, dto.getGrd_num());
			pstmt.setInt(5, dto.getNum());
			pstmt.setString(6, dto.getTea_id());
			pstmt.setInt(7, dto.getGender());
			pstmt.setDate(8, dto.getLastChangeDate());
			
			pstmt.executeUpdate();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return false;
	}
	
	public ArrayList<StudentDTO> studentList(String TID, String SCID) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_students WHERE TID=? AND SCID=? ORDER BY num";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, TID);
			pstmt.setString(2, SCID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new StudentDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getDate(9), rs.getBoolean(10)));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return list;
	}
}
