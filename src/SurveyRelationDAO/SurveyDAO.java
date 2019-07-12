package SurveyRelationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import SurveyRelationDTO.*;
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
	public void goingVersionRegister(String[] SCIDs, int surveyNo, String startDate, String endDate) 
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO survey_ing(surveyNo,SCID,startDate,endDate) VALUES (?,?,?,?)";
		for(int i=0; i<SCIDs.length; i++) 
		{
		try 
		{
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, surveyNo);
			pstmt.setString(2, SCIDs[i]);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
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
	public ArrayList<SurveyGoingDTO> showAllGoingSurveys()
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		ResultSet rs = null;
		
		String SQL ="(SELECT surveyNo FROM survey_ing WHERE DATE(startDate) >= DATE_FORMAT(NOW(), '\"%\"\"Y-\"%\"m-\"%\"d')AND DATE(endDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d'))";
		String SQL1 ="(SELECT SCID FROM survey_ing WHERE DATE(startDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d') AND DATE(endDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d'))";
		String SQL2 ="SELECT surIng.*,sch.name,sur.title from survey_ing AS surIng, school_info AS sch, survey AS sur "
				+ "where sur.surveyNo="+SQL+" AND sch.SCID="+SQL1+" AND "
				+ "DATE(surIng.startDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d') AND DATE(surIng.endDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d')";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL2);
			while(rs.next()) 
			{
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("surIng.ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surIng.surveyNo"));
				surveyGoingDTO.setSurvey_title(rs.getString("sur.title"));
				surveyGoingDTO.setSCID_name(rs.getString("sch.name"));
				surveyGoingDTO.setSCID(rs.getString("surIng.SCID"));
				surveyGoingDTO.setStartDate(rs.getString("surIng.startDate"));
				surveyGoingDTO.setEndDate(rs.getString("surIng.endDate"));
				surveyGoingList.add(surveyGoingDTO);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyGoingList;
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
			while(rs.next()) 
			{
				SurveyDTO surveyDTO = new SurveyDTO();
				surveyDTO.setSurveyNo(rs.getInt("surveyNo"));
				surveyDTO.setTitle(rs.getString("title"));
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
	public ArrayList<SurveyDTO> showSearchSurveys(String version_title)
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyDTO> surveyList = new ArrayList<SurveyDTO>();
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey where title Like \"%\" '"+version_title+"' \"%\" ; ";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				SurveyDTO surveyDTO = new SurveyDTO();
				surveyDTO.setSurveyNo(rs.getInt("surveyNo"));
				surveyDTO.setTitle(rs.getString("title"));
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
	public SurveyGoingDTO startSurvey(String SCID)
	{
		Connection conn=null;	
		Statement stmt = null;
		SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
		ResultSet rs = null;
		
		String SQL ="SELECT * FROM survey_ing WHERE SCID='"+SCID+"' AND DATE(startDate) >= DATE_FORMAT(NOW(), '\"%\"\"Y-\"%\"m-\"%\"d') AND DATE(endDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d')";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			if(rs.next()) 
			{
				surveyGoingDTO.setIngSeq(rs.getInt("ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("SCID"));
				surveyGoingDTO.setStartDate(rs.getString("startDate"));
				surveyGoingDTO.setEndDate(rs.getString("endDate"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyGoingDTO;
	}
}
