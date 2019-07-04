package School.SchoolDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import School.SchoolDTO.SchoolDTO;
import Util.DBConn;

public class SchoolDAO {
	private static SchoolDAO dao = new SchoolDAO();
	private SchoolDAO() {}
	
	public static SchoolDAO getInstance() {
		return dao;
	}
	
	/**
	 * 학교 이름을 이용하여 학교정보 반환
	 * @param sch_name
	 * @return
	 */
	public ArrayList<SchoolDTO> SearchSchoolAddress(String sch_name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SchoolDTO> school = new ArrayList<>();
		
		String SQL = "SELECT * FROM school_info WHERE name Like ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sch_name + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				school.add(new SchoolDTO(rs.getString(1),rs.getString(2),rs.getString(3)));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
		}
		return school;
	}
	
	/**
	 * 학교 코드를 이용하여 DB에 등록 되어있는지 확인
	 * @param sch_code
	 * @return
	 */
	public boolean isSchoolAlreadyRegister(String sch_code) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM school_info WHERE SCID='"+sch_code+"'";
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			if(rs.next()) {
				return true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return false;
	}
	
	/**
	 * DB에 학교 등록
	 * @param sch_address
	 * @param sch_name
	 * @param sch_code
	 * @return
	 */
	public int SchoolRegist(String sch_address, String sch_name, String sch_code) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String SQL = "INSERT INTO school_info VALUES(?, ?, ?)";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sch_code);
			pstmt.setString(2, sch_name);
			pstmt.setString(3, sch_address);
			return pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return -1;
	}
	
	/**
	 * 저장된 학교 전부 검색
	 * @return
	 */
	public ArrayList<SchoolDTO> school_List() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SchoolDTO> list = new ArrayList<SchoolDTO>();
		 String SQL = "SELECT * FROM school_info";
         try {
           conn = DBConn.getConnection();
           pstmt = conn.prepareStatement(SQL);
           rs = pstmt.executeQuery();
           
           while(rs.next()) {
              list.add(new SchoolDTO(rs.getString("sch_address"),rs.getString("sch_name"),rs.getString("sch_code")));
           }
         }catch(Exception ex) {
			ex.printStackTrace();
		 }finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
         return list;
	}
	
	/**
	 * SCID로 검색하여 학교객체 반환
	 * @param SCID
	 * @return
	 */
	public SchoolDTO getSchoolToSCID(String SCID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SchoolDTO dto = new SchoolDTO();
		
		String sql = "SELECT * FROM school_info WHERE SCID=?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setScid(rs.getString(1));
				dto.setScname(rs.getString(2));
				dto.setScaddress(rs.getString(3));
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(rs != null) try {rs.close();} catch(SQLException ex) {ex.printStackTrace();}
			if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {ex.printStackTrace();}
			if(conn != null) try {conn.close();} catch(SQLException ex) {ex.printStackTrace();}
		}
		return dto;
	}
}
