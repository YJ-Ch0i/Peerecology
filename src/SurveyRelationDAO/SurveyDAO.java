package SurveyRelationDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import SurveyRelationDTO.Stu_ans_ViewDTO;
import SurveyRelationDTO.StudentScoresDTO;
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
	public void versionTitleRegister(String version_title,String descript) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO survey(title,descript) VALUES (?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, version_title);
			pstmt.setString(2, descript);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
	}
	
	public int versionDelete(int SurveyNo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="UPDATE survey set isOpen=0 where surveyNo=?";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, SurveyNo);
			pstmt.executeUpdate();
			return isSuccess;
		}catch(Exception e) {
			isSuccess = -1;
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return isSuccess;
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
	public void goingVersionRegister(String[] SCIDs, int surveyNo, int grade, String startDate, String endDate) 
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO survey_ing(surveyNo,SCID,grade,startDate,endDate) VALUES (?,?,?,?,?)";
		for(int i=0; i<SCIDs.length; i++) 
		{
		try 
		{
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, surveyNo);
			pstmt.setString(2, SCIDs[i]);
			pstmt.setInt(3, grade);
			pstmt.setString(4, startDate);
			pstmt.setString(5, endDate);
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
	public ArrayList<SurveyGoingDTO> showAllGoingSurveys() throws ParseException
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		ResultSet rs = null;
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		String strThisDate = format1.format(System.currentTimeMillis());
		java.util.Date thisDate = new SimpleDateFormat("yyyy-MM-dd").parse(strThisDate);
		String SQL ="SELECT * FROM survey_ing AS surIng, school_info AS sch WHERE sch.SCID=surIng.SCID GROUP BY surIng.ingSeq ORDER BY DATE(surIng.endDate) ASC;";
		
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("surIng.endDate")); 
				int compareDate = thisDate.compareTo(date);
				if(compareDate<=0) 
				{
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("surIng.ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surIng.surveyNo"));
				surveyGoingDTO.setSCID_name(rs.getString("sch.name"));
				surveyGoingDTO.setSCID(rs.getString("surIng.SCID"));
				surveyGoingDTO.setGrade(rs.getInt("surIng.grade"));
				surveyGoingDTO.setStartDate(rs.getString("surIng.startDate"));
				surveyGoingDTO.setEndDate(rs.getString("surIng.endDate"));
				surveyGoingList.add(surveyGoingDTO);
				}
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
	public ArrayList<SurveyGoingDTO> showResultSurvey(String SCID_name) throws ParseException
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		ResultSet rs = null;
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		String strThisDate = format1.format(System.currentTimeMillis());
		java.util.Date thisDate = new SimpleDateFormat("yyyy-MM-dd").parse(strThisDate);
		
		String SQL ="SELECT * FROM survey_ing AS surIng, school_info AS sch where sch.name LIKE \"%\" '"+SCID_name+"' \"%\" "
				+ " AND sch.SCID=surIng.SCID GROUP BY surIng.ingSeq";
			try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("surIng.endDate")); 
				int compareDate = thisDate.compareTo(date);
				if(compareDate>0) 
				{
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("surIng.ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surIng.surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("surIng.SCID"));
				surveyGoingDTO.setSCID_name(rs.getString("sch.name"));
				surveyGoingDTO.setStartDate(rs.getString("surIng.startDate"));
				surveyGoingDTO.setEndDate(rs.getString("surIng.endDate"));
				surveyGoingList.add(surveyGoingDTO);
				}
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
	
	public ArrayList<SurveyGoingDTO> showUncalculatedSurvey(String SCID_name) throws ParseException
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		ResultSet rs = null;
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		String strThisDate = format1.format(System.currentTimeMillis());
		java.util.Date thisDate = new SimpleDateFormat("yyyy-MM-dd").parse(strThisDate);
		
		String SQL ="SELECT * FROM survey_ing AS surIng, school_info AS sch where sch.name LIKE \"%\" '"+SCID_name+"' \"%\" "
				+ " AND sch.SCID=surIng.SCID AND surIng.`isCalculated`=0  GROUP BY surIng.ingSeq";
			try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("surIng.endDate")); 
				int compareDate = thisDate.compareTo(date);
				if(compareDate>0) 
				{
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("surIng.ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surIng.surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("surIng.SCID"));
				surveyGoingDTO.setSCID_name(rs.getString("sch.name"));
				surveyGoingDTO.setStartDate(rs.getString("surIng.startDate"));
				surveyGoingDTO.setEndDate(rs.getString("surIng.endDate"));
				surveyGoingList.add(surveyGoingDTO);
				}
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
	
	public ArrayList<SurveyGoingDTO> showAllResultSurvey() throws ParseException
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		String strThisDate = format1.format (System.currentTimeMillis());
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strThisDate);
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_ing AS surIng, school_info AS sch WHERE surIng.SCID=sch.SCID GROUP BY surIng.ingSeq ORDER BY DATE(surIng.startDate) ASC;";
			try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				java.util.Date dbDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("surIng.endDate"));
				int compare = dbDate.compareTo(date);
				if(compare<0) 
				{
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("surIng.ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surIng.surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("surIng.SCID"));
				surveyGoingDTO.setSCID_name(rs.getString("sch.name"));
				surveyGoingDTO.setStartDate(rs.getString("surIng.startDate"));
				surveyGoingDTO.setEndDate(rs.getString("surIng.endDate"));
				surveyGoingList.add(surveyGoingDTO);
				}
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
	
	public ArrayList<SurveyGoingDTO> showCalculatedSurvey() throws ParseException
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		String strThisDate = format1.format (System.currentTimeMillis());
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strThisDate);
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_ing AS surIng, school_info AS sch WHERE surIng.SCID=sch.SCID AND surIng.`isCalculated`=1 GROUP BY surIng.ingSeq ORDER BY DATE(surIng.startDate) ASC;";
			try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				java.util.Date dbDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("surIng.endDate"));
				int compare = dbDate.compareTo(date);
				if(compare<0) 
				{
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("surIng.ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surIng.surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("surIng.SCID"));
				surveyGoingDTO.setSCID_name(rs.getString("sch.name"));
				surveyGoingDTO.setGrade(rs.getInt("surIng.grade"));
				surveyGoingDTO.setStartDate(rs.getString("surIng.startDate"));
				surveyGoingDTO.setEndDate(rs.getString("surIng.endDate"));
				surveyGoingList.add(surveyGoingDTO);
				}
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
	
	public ArrayList<SurveyGoingDTO> getCalculatedClassSurveyList(String scid, int grade, String year)
	{
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_ing AS surIng, school_info AS sch WHERE surIng.SCID=? AND surIng.grade=? AND YEAR(surIng.endDate)=? AND surIng.`isCalculated`=1 GROUP BY surIng.ingSeq ORDER BY DATE(surIng.startDate) ASC;";
			try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, scid);
			pstmt.setInt(2, grade);
			pstmt.setString(3, year);
            rs = pstmt.executeQuery();
			while(rs.next()) 
			{

				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("surIng.ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surIng.surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("surIng.SCID"));
				surveyGoingDTO.setSCID_name(rs.getString("sch.name"));
				surveyGoingDTO.setGrade(rs.getInt("surIng.grade"));
				surveyGoingDTO.setStartDate(rs.getString("surIng.startDate"));
				surveyGoingDTO.setEndDate(rs.getString("surIng.endDate"));
				surveyGoingList.add(surveyGoingDTO);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyGoingList;
	}
	
	public ArrayList<SurveyGoingDTO> showUncalculatedSurvey() throws ParseException
	{
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<SurveyGoingDTO> surveyGoingList = new ArrayList<SurveyGoingDTO>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		String strThisDate = format1.format (System.currentTimeMillis());
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strThisDate);
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_ing AS surIng, school_info AS sch WHERE surIng.SCID=sch.SCID AND surIng.`isCalculated`=0 GROUP BY surIng.ingSeq ORDER BY DATE(surIng.startDate) ASC;";
			try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				java.util.Date dbDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("surIng.endDate"));
				int compare = dbDate.compareTo(date);
				if(compare<0) 
				{
				SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
				surveyGoingDTO.setIngSeq(rs.getInt("surIng.ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surIng.surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("surIng.SCID"));
				surveyGoingDTO.setSCID_name(rs.getString("sch.name"));
				surveyGoingDTO.setGrade(rs.getInt("surIng.grade"));
				surveyGoingDTO.setStartDate(rs.getString("surIng.startDate"));
				surveyGoingDTO.setEndDate(rs.getString("surIng.endDate"));
				surveyGoingList.add(surveyGoingDTO);
				}
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
	
	public boolean calculateSurvey(StudentScoresDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO student_score(ingSeq, surveyNo, SCID, grade, grd_num, studentID, studentName, bigTrandID, trandID, score) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIngseq());
			pstmt.setInt(2, dto.getSurveyNo());
			pstmt.setString(3, dto.getSCID());
			pstmt.setInt(4, dto.getGrade());
			pstmt.setInt(5, dto.getGrd_num());
			pstmt.setInt(6, dto.getStu_id());
			pstmt.setString(7, dto.getsName());
			pstmt.setInt(8, dto.getBigTrandId());
			pstmt.setInt(9, dto.getTrandId());
			pstmt.setDouble(10, dto.getScore());
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
	
	public void calculateUpdate(int ingSeq, int survey_no) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String SQL ="UPDATE survey_ing set isCalculated=1 where ingSeq=? AND surveyNo=?";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, ingSeq);
			pstmt.setInt(2, survey_no);
			pstmt.executeUpdate();
		}catch(Exception e) {			
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
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
				surveyDTO.setDescript(rs.getString("descript"));
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
	public ArrayList<SurveyManagerDTO> pagingShowQuestionsToManager(int surveyNo, String pageNumber)
	{ 
		//SELECT * FROM survey_manager WHERE QSeq >= ABS((SELECT MAX(QSeq) FROM survey_manager WHERE surveyNo=2) - 20) AND
		 // QSeq < ABS((SELECT MAX(QSeq) FROM survey_manager WHERE surveyNo=2) - 10)  AND surveyNo=2 ;
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ArrayList<SurveyManagerDTO> surveyQuestions = new ArrayList<SurveyManagerDTO>();
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_manager where QSeq >= ? "
				+ " AND QSeq <= ?  AND surveyNo='"+surveyNo+"' ";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, ((Integer.parseInt(pageNumber)-1) * 10) +1); // 1-> 1 , 2 -> 11
			pstmt.setInt(2, Integer.parseInt(pageNumber) * 10); // 1 -> 10 , 2 -> 20
            rs = pstmt.executeQuery();
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
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyQuestions;
	}
	public ArrayList<SurveyManagerDTO> showQuestionsToManager(int surveyNo)
	{ 
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ArrayList<SurveyManagerDTO> surveyQuestions = new ArrayList<SurveyManagerDTO>();
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_manager where surveyNo='"+surveyNo+"' ";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			 rs = pstmt.executeQuery();
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
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return surveyQuestions;
	}
	public int getSurveyNo(int surIngNo)
	{ 
		Connection conn=null;	
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rs = null;
		String SQL ="SELECT surveyNo FROM survey_ing where ingSeq='"+surIngNo+"' ";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			 rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				return rs.getInt("surveyNo");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return count;
	}
	public int getQuestionCount(int surveyNo)
	{ 
		Connection conn=null;	
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_manager where surveyNo='"+surveyNo+"' ";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			 rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				count++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return count;
	}
	public boolean nextPage(int surveyNo, String pageNumber)
	{
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="SELECT * FROM survey_manager  where surveyNo='"+surveyNo+"' AND QSeq > ? ;" ;
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(pageNumber) * 10);
            rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return false;
	}
	public SurveyGoingDTO startSurvey(String SCID, String grade)
	{
		Connection conn=null;	
		Statement stmt = null;
		SurveyGoingDTO surveyGoingDTO = new SurveyGoingDTO();
		ResultSet rs = null;
		
		//String SQL ="SELECT * FROM survey_ing WHERE SCID='"+SCID+"' AND DATE(startDate) >= DATE_FORMAT(NOW(), '\"%\"\"Y-\"%\"m-\"%\"d') AND DATE(endDate) >= DATE_FORMAT(NOW(),'\"%\"Y-\"%\"m-\"%\"d')";
		String SQL = "SELECT * FROM survey_ing WHERE SCID= '" + SCID + "' AND grade='" + grade + "' AND DATE(NOW()) BETWEEN startDate AND endDate";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			if(rs.next()) 
			{
				surveyGoingDTO.setIngSeq(rs.getInt("ingSeq"));
				surveyGoingDTO.setSurveyNo(rs.getInt("surveyNo"));
				surveyGoingDTO.setSCID(rs.getString("SCID"));
				surveyGoingDTO.setGrade(rs.getInt("grade"));
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
	
	/**
	 * 학교 코드와 설문을 실시한 연도로 해당 연도의 기간이 종료된 설문들을 가져옴
	 * @param scid
	 * @param year
	 * @return
	 */
	public ArrayList<SearchEndsurveyDTO> searchEndSurveyToYear(String scid, String year) {
		
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SearchEndsurveyDTO> surveyList = new ArrayList<SearchEndsurveyDTO>();
		
		String sql = "SELECT * FROM search_endsurvey_view WHERE SCID=? AND YEAR(endDate)=?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scid);
			pstmt.setString(2, year);
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
	
	/**
	 * 학교 코드와 설문이 종료된 날짜로 해당 연도의 기간이 종료된 설문들과 해당 연도를 가져옴 
	 * @param scid
	 * @param year
	 * @return
	 */
	public SearchEndsurveyDTO searchEndSurveyToDate(String scid, String date) {
		
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SearchEndsurveyDTO dto = new SearchEndsurveyDTO();
		
		String sql = "SELECT YEAR(endDate), surveyNo, ingSeq, SCID, startDate, endDate, title FROM search_endsurvey_view WHERE SCID=? AND endDate=?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scid);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto.setYear(rs.getInt(1));
				dto.setSurveyNo(rs.getInt(2));
				dto.setIngSeq(rs.getInt(3));
				dto.setSCID(rs.getString(4));
				dto.setStartDate(rs.getDate(5));
				dto.setEndDate(rs.getDate(6));
				dto.setTitle(rs.getString(7));
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
		return dto;
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
	
	public ArrayList<StudentScoresDTO> getClassesScores(int ingSeq, int surveyNo, String SCID, int grade, int grd_num) {
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<StudentScoresDTO> scoresList = new ArrayList<>();
		
		String sql = "SELECT * FROM stu_scores WHERE ingSeq=? AND surveyNo=? AND SCID=? AND grade=? AND grd_num=?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ingSeq);
			pstmt.setInt(2, surveyNo);
			pstmt.setString(3, SCID);
			pstmt.setInt(4, grade);
			pstmt.setInt(5, grd_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				StudentScoresDTO dto = new StudentScoresDTO();
				dto.setStu_id(rs.getInt("studentID"));
				dto.setsName(rs.getString("studentName"));
				dto.setBigTrandId(rs.getInt("bigTrandID"));
				dto.setBigTrandDesc(rs.getString("bigDesc"));
				dto.setTrandId(rs.getInt("trandID"));
				dto.setTrandDesc(rs.getString("trDesc"));
				dto.setScore(rs.getDouble("score"));
				dto.setYear(rs.getString("year"));
				
				scoresList.add(dto);
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
		return scoresList;
	}
	
	public ArrayList<StudentScoresDTO> getClassesAllScores(String SCID, int grade, int grd_num, String year) {
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<StudentScoresDTO> scoresList = new ArrayList<>();
		
		String sql = "SELECT * FROM stu_scores WHERE SCID=? AND grade=? AND grd_num=? AND year=? ORDER BY trandID";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			pstmt.setInt(2, grade);
			pstmt.setInt(3, grd_num);
			pstmt.setString(4, year);	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				StudentScoresDTO dto = new StudentScoresDTO();
				dto.setIngseq(rs.getInt("ingSeq"));
				dto.setStu_id(rs.getInt("studentID"));
				dto.setsName(rs.getString("studentName"));
				dto.setBigTrandId(rs.getInt("bigTrandID"));
				dto.setBigTrandDesc(rs.getString("bigDesc"));
				dto.setTrandId(rs.getInt("trandID"));
				dto.setTrandDesc(rs.getString("trDesc"));
				dto.setScore(rs.getDouble("score"));
				dto.setYear(rs.getString("year"));
				
				scoresList.add(dto);
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
		return scoresList;
	}
	
	public ArrayList<StudentScoresDTO> getStudentScores(String SCID, int grade, int grd_num, int stuid, String year) {
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<StudentScoresDTO> scoresList = new ArrayList<>();
		
		String sql = "SELECT * FROM stu_scores WHERE SCID=? AND grade=? AND grd_num=? AND studentID=? AND year=? ORDER BY ingSeq, trandID";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SCID);
			pstmt.setInt(2, grade);
			pstmt.setInt(3, grd_num);
			pstmt.setInt(4, stuid);
			pstmt.setString(5, year);	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				StudentScoresDTO dto = new StudentScoresDTO();
				dto.setIngseq(rs.getInt("ingSeq"));
				dto.setSurveyNo(rs.getInt("surveyNo"));
				dto.setStu_id(rs.getInt("studentID"));
				dto.setsName(rs.getString("studentName"));
				dto.setBigTrandId(rs.getInt("bigTrandID"));
				dto.setBigTrandDesc(rs.getString("bigDesc"));
				dto.setTrandId(rs.getInt("trandID"));
				dto.setTrandDesc(rs.getString("trDesc"));
				dto.setScore(rs.getDouble("score"));
				dto.setYear(rs.getString("year"));
				
				scoresList.add(dto);
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
		return scoresList;
	}
}
