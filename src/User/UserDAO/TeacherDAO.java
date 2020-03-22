package User.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import User.UserDTO.TeacherDTO;
import Util.DBConn;

public class TeacherDAO {
	
	private static TeacherDAO dao = new TeacherDAO();
	private TeacherDAO() {}
	
	public static TeacherDAO getInstance() {
		return dao;
	}

	/**
	 * 유저 아이디 중복 체크
	 * 유저가 이미 가입되어 있다면 false를 반환
	 * @param uId
	 * @return
	 */
	public boolean isUserRegist(String TID) {
		//유저 아이디 중복체크
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT TID FROM user_teachers WHERE TID = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, TID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(TID)) return false;				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
		}
		return true;
	}
	
	/**
	 * 회원가입
	 * @param t
	 * @return
	 */
	public int teacherRegister(TeacherDTO t) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO user_teachers(TID, pwd, name, mailcheck, lastChangeYear) VALUES (?,?,?,?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, t.getTID());
			pstmt.setString(2, t.getPwd());
			pstmt.setString(3, t.getName());
			pstmt.setBoolean(4, t.isMailcheck());
			pstmt.setString(5, t.getLastChangeYear());
			
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return -1;
	}
	
	/**
	 * 관리자용 교사 계정 생성
	 * @param t
	 * @return
	 */
	public int registTeacherAdmin(TeacherDTO t) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO user_teachers(TID, pwd, name, mailcheck, lastChangeYear) VALUES (?,?,?,?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, t.getTID());
			pstmt.setString(2, t.getPwd());
			pstmt.setString(3, t.getName());
			pstmt.setBoolean(4, t.isMailcheck());
			pstmt.setString(5, t.getLastChangeYear());
			
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return -1;
	}
	
	/**
	 * 이메일 인증 여부
	 * @param u_id
	 * @return
	 */
	public boolean getEamilChecked(String TID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT mailcheck FROM user_teachers WHERE TID = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, TID);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getBoolean(1);
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
		return false;
	}
	
	/**
	 * 이메일 인증하면 바꿔줌
	 * @param u_id
	 * @param pl_id
	 * @return
	 */
	public boolean setEmailCheck(String TID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE user_teachers SET mailcheck = true WHERE TID = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, TID);
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

	public int teacherLogin(String tea_id, String pass) {
		String sql = "SELECT pwd FROM user_teachers WHERE TID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tea_id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				if(rs.getString(1).equals(pass)) return 1; 
				else return 0;	
			}
			return -1; 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally 
		{
			try
			{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return -2; 
	}

	//변경된 선생 데이터
	public TeacherDTO teacherInfo(String tea_id) {
		String sql = "SELECT * FROM user_teachers WHERE TID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeacherDTO teacherDTO = new TeacherDTO();
		try
		{
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tea_id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				teacherDTO.setTID(rs.getString(1));
				teacherDTO.setPwd(rs.getString(2));
				teacherDTO.setName(rs.getString(3));
				teacherDTO.setSCID(rs.getString(4));
				teacherDTO.setGrade(rs.getInt(5));
				teacherDTO.setClasses(rs.getInt(6));
				teacherDTO.setMailcheck(rs.getBoolean(7));
				teacherDTO.setLastChangeYear(rs.getString(8));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally 
		{
			try
			{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return teacherDTO;
	}
	
	/**
	 * 2019 선생데이터
	 * @param tea_id
	 * @return
	 */
	public TeacherDTO teacherInfo2019(String tea_id) {
		String sql = "SELECT * FROM teachers_2019 WHERE TID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeacherDTO teacherDTO = new TeacherDTO();
		try
		{
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tea_id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				teacherDTO.setTID(rs.getString(1));
				teacherDTO.setPwd(rs.getString(2));
				teacherDTO.setName(rs.getString(3));
				teacherDTO.setSCID(rs.getString(4));
				teacherDTO.setGrade(rs.getInt(5));
				teacherDTO.setClasses(rs.getInt(6));
				teacherDTO.setMailcheck(rs.getBoolean(7));
				teacherDTO.setLastChangeYear(rs.getString(8));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally 
		{
			try
			{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return teacherDTO;
	}
	
	
	
	public boolean teacherSchoolUpdate(String scid, int grade, int grd_num, String tea_id, String year) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE user_teachers SET SCID=?, grade=?, class=?, lastChangeYear=? WHERE TID = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scid);
			pstmt.setInt(2, grade);
			pstmt.setInt(3, grd_num);
			pstmt.setString(4, year);
			pstmt.setString(5, tea_id);
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
	
	/**
	 * 학교선택 후 학년 반 선택을 위한 검색
	 * @param scid
	 */
	public ArrayList<TeacherDTO> searchGradeUseTeacher(String scid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TeacherDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_teachers WHERE SCID=? ORDER BY grade, class";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new TeacherDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7), rs.getString(8)));
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
	
	/**
	 * 학교선택 후 학년 반 선택을 위한 검색 - 관리자
	 * @param scid
	 */
	public ArrayList<TeacherDTO> searchClassForAdmin(String scid, int grade) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TeacherDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM user_teachers WHERE SCID=? AND grade=? ORDER BY grade, class";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scid);
			pstmt.setInt(2, grade);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new TeacherDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7), rs.getString(8)));
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
