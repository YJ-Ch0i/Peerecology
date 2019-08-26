package User.UserDAO;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.opencsv.CSVReader;

import User.UserDTO.*;
import Util.*;

public class StudentDAO {

	private static StudentDAO dao = new StudentDAO();
	private StudentDAO() {
	}

	public static StudentDAO getInstance() {
		return dao;
	}
	
	public StudentDTO getStudent(String name, String TID, String SCID) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		StudentDTO dto = new StudentDTO();

		String sql = "SELECT * FROM user_students WHERE name=? AND TID=? AND SCID=?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, TID);
			pstmt.setString(3, SCID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setStu_id(rs.getInt(1));
				dto.setName(rs.getString(2));;
				dto.setScid(rs.getString(3));
				dto.setGrade(rs.getInt(4));
				dto.setGrd_num(rs.getInt(5));
				dto.setNum(rs.getInt(6));
				dto.setTea_id(TID);
				dto.setTransfer(rs.getBoolean("isTransfer"));
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
		return dto;
	}
	public ArrayList<StudentDTO> findStudent(String name) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		ArrayList<StudentDTO> stuList = new ArrayList<StudentDTO>();

		String sql = "SELECT * FROM user_students AS stu, school_info AS sch WHERE stu.name LIKE \"%\" ? \"%\" AND stu.SCID=sch.SCID ";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StudentDTO stuDTO = new StudentDTO();
				stuDTO.setStu_id(rs.getInt("stu.SID"));
				stuDTO.setName(rs.getString("stu.name"));;
				stuDTO.setScid(rs.getString("sch.name"));
				stuDTO.setGrade(rs.getInt("stu.grade"));
				stuDTO.setGrd_num(rs.getInt("stu.class"));
				stuDTO.setNum(rs.getInt("stu.num"));
				stuDTO.setGender(rs.getInt("stu.gender"));
				stuList.add(stuDTO);
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
		return stuList;
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
	public ArrayList<StudentDTO> findStudentToGradeSCID(int SID ,String SCID, int grade, int classes){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_students WHERE SID NOT IN (?) AND isTransfer=1 AND SCID=? AND grade=? AND class=? ORDER BY num";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, SID);
			pstmt.setString(2, SCID);
			pstmt.setInt(3, grade);
			pstmt.setInt(4, classes);
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
	
	public ArrayList<StudentDTO> studentListInClass(String SCID, int grade, int grd_num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_students WHERE SCID=? AND grade=? AND class=? ORDER BY num";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			pstmt.setInt(2, grade);
			pstmt.setInt(3, grd_num);
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
	
	public ArrayList<StudentDTO> studentListAttend(String TID, String SCID, int grade, int grd_num, String year) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_students WHERE TID=? AND SCID=? AND grade=? AND class=? AND isTransfer=1 AND YEAR(lastChangeDate)=? ORDER BY num";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, TID);
			pstmt.setString(2, SCID);
			pstmt.setInt(3, grade);
			pstmt.setInt(4, grd_num);
			pstmt.setString(5, year);
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
	
	public ArrayList<StudentDTO> getStudentAttendList(String TID, String SCID, int grade, int grd_num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_students WHERE TID=? AND SCID=? AND grade=? AND class=? AND isTransfer=1 ORDER BY num";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, TID);
			pstmt.setString(2, SCID);
			pstmt.setInt(3, grade);
			pstmt.setInt(4, grd_num);
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
	
	public ArrayList<Integer> getStudentList(String SCID, int grade) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		ArrayList<Integer> list = new ArrayList<Integer>();

		String sql = "SELECT DISTINCT class FROM user_students WHERE SCID=? AND grade=? AND DATE_FORMAT(lastChangeDate, \'%Y\') = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			pstmt.setInt(2, grade);
			pstmt.setInt(3, year);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				list.add(rs.getInt("class")); 
				System.out.println("qqq");
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
	public ArrayList<StudentDTO> getSchoolAttendList(String SCID, int grade, String year) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_students WHERE SCID=? AND grade=? AND YEAR(lastChangeDate)=? AND isTransfer=1 ORDER BY grade, class, num";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			pstmt.setInt(2, grade);
			pstmt.setString(3, year);
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
	
	public ArrayList<StudentDTO> studentListAttend2(String SCID, int grade, int grd_num, String year) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_students WHERE SCID=? and grade=? and class=? and YEAR(lastChangeDate)=? AND isTransfer=1 ORDER BY num";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			pstmt.setInt(2, grade);
			pstmt.setInt(3, grd_num);
			pstmt.setString(4, year);
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
	
	public ArrayList<StudentDTO> studentListAttend3(String SCID, int grade, int grd_num, String year) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		String sql = "SELECT SID, NAME, SCID, grade, class, num, TID, gender, YEAR(lastChangeDate), isTransfer FROM user_students WHERE SCID=? and grade=? and class=? and YEAR(lastChangeDate)=? AND isTransfer=1 ORDER BY num";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			pstmt.setInt(2, grade);
			pstmt.setInt(3, grd_num);
			pstmt.setString(4, year);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new StudentDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getBoolean(10)));
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
	
	public boolean studentTransfer(StudentDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String sql = "UPDATE user_students SET isTransfer=0 WHERE name=? AND SCID=? AND TID=?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getScid());
			pstmt.setString(3, dto.getTea_id());
			pstmt.executeUpdate();
			return true;			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {			
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return false;
	}
	
	public int studentLogin(StudentDTO dto) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT num FROM user_students WHERE TID=? AND SCID=? AND name=?";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getInt(1) == dto.getNum())
					return 1;
				else
					return 0;
			}
			return -1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return -2;
	}
	public ArrayList<StudentDTO> showAllStudent() {
		Connection conn=null;
		Statement stmt = null;
		ArrayList<StudentDTO> stuList = new ArrayList<StudentDTO>();
		ResultSet rs = null;
		String SQL ="Select * from user_students AS stu, school_info AS sch where stu.SCID=sch.SCID GROUP BY stu.SID;";
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				StudentDTO stuDTO = new StudentDTO();
				stuDTO.setStu_id(rs.getInt("stu.SID"));
				stuDTO.setName(rs.getString("stu.name"));
				stuDTO.setScid(rs.getString("sch.name"));
				stuDTO.setGrade(rs.getInt("stu.grade"));
				stuDTO.setGrd_num(rs.getInt("stu.class"));
				stuDTO.setNum(rs.getInt("stu.num"));
				stuDTO.setGender(rs.getInt("stu.gender"));
				stuList.add(stuDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return stuList;
	}
}
