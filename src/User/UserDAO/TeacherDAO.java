package User.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

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
		
		String sql = "select TID from user_teachers where TID = ?";
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
	
	public void teacherSchoolUpdate() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE user_teachers SET mailcheck = true WHERE TID = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {			
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}			
		}
		
	}
}
