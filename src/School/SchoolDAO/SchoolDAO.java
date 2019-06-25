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
}
