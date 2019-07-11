package SurveyRelationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import SurveyRelationDTO.QuestionDTO;
import SurveyRelationDTO.SurveyDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import Util.DBConn;

public class SurveyDAO {
	
	private static SurveyDAO dao = new SurveyDAO();
	private SurveyDAO() {}
	
	public static SurveyDAO getInstance() {
		return dao;
	}
	public void versionTitleRegister(String version_title) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO survey(title) VALUES (?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, version_title);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
	}
	public void version_QuestionRegister(String[] questions) 
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO survey_manager(surveyNo,Qseq,QID) VALUES ((SELECT MAX(surveyNo) FROM survey),?,?)";
		for(int i=0; i<questions.length; i++) 
		{
		try 
		{
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, i+1);
			pstmt.setString(2, questions[i]);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		}
	}
	public ArrayList<SurveyDTO> showAllSurveys()
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyDTO> surveyList = new ArrayList<SurveyDTO>();
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey where isOpen=True;";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			if(rs.next()) 
			{
				SurveyDTO surveyDTO = new SurveyDTO();
				surveyDTO.setSurveyNo(rs.getInt("surveyNo"));
				surveyDTO.setTitle(rs.getString("title"));
				surveyDTO.setStartDate(rs.getDate("startDate"));
				surveyDTO.setEndDate(rs.getDate("endDate"));
				surveyDTO.setOpen(rs.getBoolean("isOpen"));
				surveyList.add(surveyDTO);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyList;
	}
	public ArrayList<SurveyManagerDTO> showQuestionsToManager(int surveyNo)
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyManagerDTO> surveyQuestions = new ArrayList<SurveyManagerDTO>();
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_manager where surveyNo='"+surveyNo+"';";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				SurveyManagerDTO surveyManagerDTO = new SurveyManagerDTO();
				surveyManagerDTO.setSMID(rs.getInt("SMID"));
				surveyManagerDTO.setSurveyNo(surveyNo);
				surveyManagerDTO.setQseq(rs.getInt("QSeq"));
				surveyManagerDTO.setQID(rs.getInt("QID"));
				surveyQuestions.add(surveyManagerDTO);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyQuestions;
	}
}
