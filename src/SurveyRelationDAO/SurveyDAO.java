package SurveyRelationDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import SurveyRelationDTO.Stu_ans_ViewDTO;
import SurveyRelationDTO.SurveyDTO;
import SurveyRelationDTO.SurveyGoingDTO;
import SurveyRelationDTO.SurveyManagerDTO;
import Util.DBConn;
import view.viewDTO.SearchEndsurveyDTO;

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
	public SurveyGoingDTO findIngSurvey(int surveyNo, String SCID)
	{
		Connection conn=null;	
		Statement stmt = null;
		SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
		ResultSet rs = null;
		
		/*String SQL2 ="SELECT * from survey_ing "
				+ " where surveyNo='"+surveyNo+"' AND SCID='"+SCID+"'"
				+ " AND DATE(startDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d') AND DATE(endDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d')";*/
		
		String SQL2 = "SELECT * FROM survey_ing WHERE surveyNo=" + surveyNo + " AND SCID= '" + SCID + "' AND DATE(NOW()) BETWEEN startDate AND endDate";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL2);
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
	public ArrayList<SurveyGoingDTO> showAllGoingSurveys()
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		ResultSet rs = null;
		
		String SQL ="SELECT * FROM survey_ing WHERE DATE(startDate) >= DATE_FORMAT(NOW(), '\"%\"\"Y-\"%\"m-\"%\"d')AND DATE(endDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d')";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("SCID"));
				surveyGoingDTO.setStartDate(rs.getString("startDate"));
				surveyGoingDTO.setEndDate(rs.getString("endDate"));
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
	public SurveyDTO showSearchSurveyToSurveyNo(int surveyNo)
	{
		Connection conn=null;	
		Statement stmt = null;
		SurveyDTO surveyDTO = new SurveyDTO();
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey where surveyNo='"+surveyNo+"'; ";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				surveyDTO.setSurveyNo(rs.getInt("surveyNo"));
				surveyDTO.setTitle(rs.getString("title"));
				surveyDTO.setOpen(rs.getBoolean("isOpen"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyDTO;
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
		
		//String SQL ="SELECT * FROM survey_ing WHERE SCID='"+SCID+"' AND DATE(startDate) >= DATE_FORMAT(NOW(), '\"%\"\"Y-\"%\"m-\"%\"d') AND DATE(endDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d')";
		String SQL = "SELECT * FROM survey_ing WHERE SCID= '" + SCID + "' AND DATE(NOW()) BETWEEN startDate AND endDate";
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
	
	public ArrayList<SurveyGoingDTO> searchSurveyToTeacher(int surveyNo, String scid) {
		
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SurveyGoingDTO> surveyList = new ArrayList<SurveyGoingDTO>();
		
		String sql = "SELECT * FROM survey_ing WHERE surveyNo=? AND SCID=? AND DATE(NOW()) >= endDate";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, surveyNo);
			pstmt.setString(2, scid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("SCID"));
				surveyGoingDTO.setStartDate(rs.getString("startDate"));
				surveyGoingDTO.setEndDate(rs.getString("endDate"));
				surveyList.add(surveyGoingDTO);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyList;
	}
	

	/**
	 * 학교 코드를 이용하여 해당 학교에서 기간이 끝난 설문조사 목록을 가져옴
	 * @param SCID
	 * @return
	 */
	public ArrayList<SearchEndsurveyDTO> searchEndSurvey(String SCID) {
		
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SearchEndsurveyDTO> surveyList = new ArrayList<SearchEndsurveyDTO>();
		
		String sql = "SELECT * FROM search_endsurvey_view WHERE SCID=?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SearchEndsurveyDTO dto = new SearchEndsurveyDTO();
				dto.setSurveyNo(rs.getInt(1));
				dto.setIngSeq(rs.getInt(2));
				dto.setSCID(rs.getString(3));
				dto.setStartDate(rs.getDate(4));
				dto.setEndDate(rs.getDate(5));
				dto.setTitle(rs.getString(6));
				surveyList.add(dto);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyList;
	}
	
	public ArrayList<Stu_ans_ViewDTO> searchAnswer(int surNo, int ingSeq, String scid, Date start, Date end) {
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Stu_ans_ViewDTO> answerList = new ArrayList<Stu_ans_ViewDTO>();
		
		String sql = "SELECT * FROM student_answer WHERE surveyNo=? AND ingSeq=? AND scid=? AND startDate=? AND endDate=? ORDER BY num, QID";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, surNo);
			pstmt.setInt(2, ingSeq);
			pstmt.setString(3, scid);
			pstmt.setDate(4, start);
			pstmt.setDate(5, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Stu_ans_ViewDTO dto = new Stu_ans_ViewDTO();
				dto.setQID(rs.getInt(1));
				dto.setStu_id(rs.getInt(2));
				dto.setStu_num(rs.getInt(3));
				dto.setStu_name(rs.getString(4));
				dto.setAnswerV(rs.getInt(5));
				dto.setSurveyNo(rs.getInt(6));
				dto.setIngSeq(rs.getInt(7));
				dto.setSCID(rs.getString(8));
				dto.setStartDate(rs.getDate(9));
				dto.setEndDate(rs.getDate(10));
				dto.setQType(rs.getInt(11));
				dto.setQtDes(rs.getString(12));
				dto.settType(rs.getInt(13));
				dto.setQttDes(rs.getString(14));
				dto.setReverse(rs.getBoolean(15));
				
				answerList.add(dto);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return answerList;
	}
}
