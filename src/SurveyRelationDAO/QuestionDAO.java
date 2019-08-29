package SurveyRelationDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import SurveyRelationDTO.AllDescQuestionDTO;
import SurveyRelationDTO.QuestionDTO;
import SurveyRelationDTO.QuestionOfferDTO;
import SurveyRelationDTO.QuestionTrandManagerDTO;
import SurveyRelationDTO.QuestionTrandTypeDTO;
import SurveyRelationDTO.QuestionTypeDTO;
import SurveyRelationDTO.SurveyAnswerDTO;
import Util.DBConn;

public class QuestionDAO {
	
	private static QuestionDAO dao = new QuestionDAO();
	private QuestionDAO() {}
	
	public static QuestionDAO getInstance() {
		return dao;
	}
	public ArrayList<QuestionTrandManagerDTO> showAllBigTrands()
	{
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QuestionTrandManagerDTO> trandList = new ArrayList<QuestionTrandManagerDTO>();
		
		String sql = "SELECT * from q_trand_manager where isShowing=True";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) 
			{
				trandList.add(new QuestionTrandManagerDTO(rs.getInt(1), rs.getString(2)));
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
		return trandList;
	}
	public ArrayList<QuestionTrandTypeDTO> searchTrandList(int surNo, int ingSeq, String scid) {
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QuestionTrandTypeDTO> trandList = new ArrayList<QuestionTrandTypeDTO>();
		
		String sql = "SELECT DISTINCT Ttype, bigTrandID, qttdes FROM student_answer WHERE surveyNo=? AND ingSeq=? AND SCID=? ORDER BY Ttype";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, surNo);
			pstmt.setInt(2, ingSeq);
			pstmt.setString(3, scid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				trandList.add(new QuestionTrandTypeDTO(rs.getInt(1), rs.getInt(2), rs.getString(3)));
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
		return trandList;
	}
	
	public ArrayList<QuestionTrandManagerDTO> searchBigTrandList(int surNo, int ingSeq, String scid, Date start, Date end) {
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QuestionTrandManagerDTO> bigTrandList = new ArrayList<QuestionTrandManagerDTO>();
		
		String sql = "SELECT bigTrandID, descript, isShowing, explan FROM q_trand_manager WHERE bigTrandID IN(SELECT bigTrandID FROM student_answer WHERE surveyNo=? AND ingSeq=? AND scid=? AND startDate=? AND endDate=? GROUP BY bigTrandID)";
		
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
				bigTrandList.add(new QuestionTrandManagerDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
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
		return bigTrandList;
	}
	
	public ArrayList<QuestionTrandManagerDTO> getBigTrandList(String scid, Date start, Date end) {
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QuestionTrandManagerDTO> bigTrandList = new ArrayList<QuestionTrandManagerDTO>();
		
		String sql = "SELECT bigTrandID, descript, isShowing, explan FROM q_trand_manager WHERE bigTrandID IN(SELECT bigTrandID FROM student_answer WHERE scid=? AND startDate=? AND endDate=? GROUP BY bigTrandID)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scid);
			pstmt.setDate(2, start);
			pstmt.setDate(3, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bigTrandList.add(new QuestionTrandManagerDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
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
		return bigTrandList;
	}
	
	public QuestionTrandTypeDTO searchTrand(int trandID) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		QuestionTrandTypeDTO queTrand = null;
		ResultSet rs = null;
		String SQL ="Select * from q_trand_type WHERE q_trandID=?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, trandID);
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				queTrand = new QuestionTrandTypeDTO();
				queTrand.setQ_trandType(rs.getInt(1));
				queTrand.setQ_trandDescipt(rs.getString(2));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return queTrand;
	}
	
	public ArrayList<QuestionTrandTypeDTO> getTrandToBigTID(int bigTid) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		QuestionTrandTypeDTO queTrand = null;
		ResultSet rs = null;
		ArrayList<QuestionTrandTypeDTO> list = new ArrayList<>();
		
		String SQL ="Select * from q_trand_type WHERE bigTrandID=?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bigTid);
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				queTrand = new QuestionTrandTypeDTO();
				queTrand.setQ_trandType(rs.getInt(1));
				queTrand.setBigTrandID(rs.getInt(2));
				queTrand.setQ_trandDescipt(rs.getString(3));
				
				list.add(queTrand);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return list;
	}
	
	public ArrayList<QuestionTrandTypeDTO> showAllTrand() {
		Connection conn=null;
		Statement stmt = null;
		ArrayList<QuestionTrandTypeDTO> queTrands = new ArrayList<QuestionTrandTypeDTO>();
		ResultSet rs = null;
		String SQL ="Select * from q_trand_type";
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				QuestionTrandTypeDTO questionDTO = new QuestionTrandTypeDTO(rs.getInt("q_trandID"), rs.getInt("bigTrandID"), rs.getString("descript"));
				queTrands.add(questionDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return queTrands;
	}
	public ArrayList<QuestionTypeDTO> showAllType() {
		Connection conn=null;
		Statement stmt = null;
		ArrayList<QuestionTypeDTO> queTypes = new ArrayList<QuestionTypeDTO>();
		ResultSet rs = null;
		String SQL ="Select * from q_type";
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				QuestionTypeDTO questionDTO = new QuestionTypeDTO(rs.getInt("q_typeID"),rs.getString("descript"));
				queTypes.add(questionDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return queTypes;
	}
	
	public QuestionTypeDTO showAllType(int qtype) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		QuestionTypeDTO questionDTO =  new QuestionTypeDTO();
		ResultSet rs = null;
		String SQL ="Select * from search_qtype WHERE QType=?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, qtype);
			rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				 questionDTO = new QuestionTypeDTO(rs.getInt("QType"),rs.getString("descript"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return questionDTO;
	}
	
	
	public ArrayList<QuestionDTO> showAllQuestion() {
		Connection conn=null;
		Statement stmt = null;
		ArrayList<QuestionDTO> questions = new ArrayList<QuestionDTO>();
		ResultSet rs = null;
		String SQL ="Select * from question where isShowing=1";
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				QuestionDTO questionDTO = new QuestionDTO(rs.getInt("QID"),rs.getString("title"),rs.getInt("Qtype"),rs.getInt("Ttype"),rs.getBoolean("isReverseType"));
				questions.add(questionDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return questions;
	}
	public void queTypeRegister(String typeTitle, int offerSeq, boolean q_typeDirection) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO q_type(descript,q_typeOfferSeq,q_typeDirection) VALUES (?,?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, typeTitle);
			pstmt.setInt(2, offerSeq);
			pstmt.setBoolean(3, q_typeDirection);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
	}
	public int trandManagerRegister(String bigTrandTitle, String bigTrandExplan) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="INSERT INTO q_trand_manager(descript, explan) VALUES (?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bigTrandTitle);
			pstmt.setString(2, bigTrandExplan);
			pstmt.executeUpdate();
		}catch(Exception e) {
			isSuccess = -1;
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return isSuccess;
	}
	
	public int queTrandRegister(String trandTitle) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="INSERT INTO q_trand_type(bigTrandID,descript) VALUES ((SELECT MAX(bigTrandID) FROM q_trand_manager),?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, trandTitle);
			pstmt.executeUpdate();
		}catch(Exception e) {
			isSuccess = -1;
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return isSuccess;
	}
	public int queTrandDifferentRegister(String trandTitle,int bigTrandID) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="INSERT INTO q_trand_type(bigTrandID,descript) VALUES (?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bigTrandID);
			pstmt.setString(2, trandTitle);
			pstmt.executeUpdate();
		}catch(Exception e) {
			isSuccess = -1;
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return isSuccess;
	}
	public int queBigTrandDelete(int trandNum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="UPDATE q_trand_manager SET isShowing=FALSE where bigTrandID=?";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, trandNum);
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
	public int queTrandDelete(int trandNum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="DELETE FROM q_trand_type where q_trandID=?";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, trandNum);
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
	public int queTypeDelete(int typeNum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="DELETE FROM q_type where q_typeID=?";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, typeNum);
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
	public int queTypeOfferDelete(int typeNum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="DELETE FROM question_offer where q_typeID=?";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, typeNum);
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
	public void questionRegister(QuestionDTO questionDTO) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO question(title, QType, Ttype,isReverseType,que_answer) VALUES (?,?,?,?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, questionDTO.getTitle());
			pstmt.setInt(2, questionDTO.getQType());
			pstmt.setInt(3, questionDTO.getTtype());
			pstmt.setBoolean(4, questionDTO.isReverseType());
			pstmt.setString(5, questionDTO.getQue_answer());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
	}
	public int questionDelete(int QID) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int isSuccess = 0;
		String SQL ="UPDATE question set isShowing=0 where QID=?";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QID);
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
	public void questionDifferentRegister(QuestionDTO questionDTO) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO question(title, QType, Ttype,isReverseType,que_answer) VALUES (?,(SELECT MAX(q_typeID) FROM q_type),?,?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, questionDTO.getTitle());
			pstmt.setInt(2, questionDTO.getTtype());
			pstmt.setBoolean(3, questionDTO.isReverseType());
			pstmt.setString(4, questionDTO.getQue_answer());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
	}
	public void questionOfferRegister(int QoferSeq, String offers) {
		Connection conn=null;	
		PreparedStatement pstmt=null;
		String[] offerSplit = offers.split(",");
		for(int i=0; i<offerSplit.length; i++)
		{
		String SQL ="INSERT INTO question_offer(q_typeID, QOfferSeq, title) VALUES ((SELECT MAX(q_typeID) FROM q_type),?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, i+1);
			pstmt.setString(2, offerSplit[i]);
			
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
	public AllDescQuestionDTO showQuestion(int QID) {
		Connection conn=null;	
		Statement stmt = null;
		AllDescQuestionDTO questionDTO = new AllDescQuestionDTO();
		ResultSet rs = null;
		String SQL ="SELECT question.title,question.que_answer,qtt.descript,qt.descript,qt.q_typeOfferSeq,qt.q_typeID ,question.isReverseType,qt.q_typeDirection"
				+ " FROM question,q_trand_type AS qtt,q_type AS qt "
				+ " WHERE qtt.q_trandID = (SELECT Ttype from question where QID='"+QID+"') "
				+ " AND question.QID = (SELECT QID from question where QID='"+QID+"') "
				+ " AND qt.q_typeID = (SELECT QType from question where QID='"+QID+"') ";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			if(rs.next()) 
			{
				questionDTO.setQID(QID);
				questionDTO.setQue_title(rs.getString("question.title"));
				questionDTO.setQue_answer(rs.getString("question.que_answer"));
				questionDTO.setQue_trandTitle(rs.getString("qtt.descript"));
				questionDTO.setQue_typeTitle(rs.getString("qt.descript"));
				questionDTO.setQue_typeID(rs.getInt("qt.q_typeID"));
				questionDTO.setQue_typeOfferSeq(rs.getInt("qt.q_typeOfferSeq"));
				questionDTO.setQue_isReverseType(rs.getBoolean("question.isReverseType"));
				questionDTO.setQ_typeDirection(rs.getBoolean("qt.q_typeDirection"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return questionDTO;
	}
	public ArrayList<QuestionOfferDTO> showQuestionOffer(int q_typeID) {
		Connection conn=null;	
		Statement stmt = null;
		ArrayList<QuestionOfferDTO> questionOfferList = new ArrayList<QuestionOfferDTO>();
		ResultSet rs = null;
		String SQL ="SELECT * FROM question_offer WHERE q_typeID='"+q_typeID+"'";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			while(rs.next()) 
			{
				QuestionOfferDTO questionOfferDTO = new QuestionOfferDTO();
				questionOfferDTO.setQ_typeID(rs.getInt("q_typeID"));
				questionOfferDTO.setqOfferSeq(rs.getInt("QOfferSeq"));
				questionOfferDTO.setTitle(rs.getString("title"));
				questionOfferList.add(questionOfferDTO);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null) try{stmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return questionOfferList;
	}
	
	public int countOfTrandQuestion(int surNo, int tId) {
		Connection conn=null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String SQL ="SELECT COUNT(sm.`QID`) FROM survey_manager AS sm, question AS q, q_trand_type AS qtt WHERE sm.surveyNo=? AND q.`Ttype`=qtt.`q_trandID` AND q.`QID`=sm.`QID` AND q.`Ttype`=?";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, surNo);
			pstmt.setInt(2,  tId);
            rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return result;
	}
	
	/**
	 * 설문 회차에서 조사한 또래지명 문항들 List를 반환
	 * @author yeong
	 * @param ingSeq 설문회차
	 * @return
	 */
	public List<QuestionDTO> getPeerQuestionListInSeq(int surNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QuestionDTO> list = new ArrayList<>();
		
		String sql = "SELECT q.`QID`, q.`title`, q.`Ttype` FROM question AS q LEFT JOIN survey_manager AS sm ON q.`QID`=sm.`QID` WHERE q.`QType`=1 AND sm.`surveyNo`=?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, surNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setQID(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setTtype(rs.getInt(3));
				list.add(dto);
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
	 * 설문 회차에서 문항에 대해 해당 학교 학년 반의 재학생이 응답한 정보를 List로 반환
	 * @param qId 문항의 ID
	 * @param ingSeq 설문 회차
	 * @param scid 학교코드
	 * @param grade 학년
	 * @param grdNum 반
	 * @return
	 */
	public List<SurveyAnswerDTO> getMultiAnswerValueInQuestion(int qId, int ingSeq, String scid, int grade, int grdNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SurveyAnswerDTO> list = new ArrayList<>();
		
		String sql = "SELECT sa.`answerID`, sa.`studentID`, ma.`multiAnswer`\r\n" + 
				"FROM survey_answer AS sa \r\n" + 
				"LEFT JOIN multi_answermanager AS ma \r\n" + 
				"ON sa.`answerID` = ma.`answerID`\r\n" + 
				"LEFT JOIN user_students AS st\r\n" + 
				"ON sa.`studentID` = st.`SID`\r\n" + 
				"WHERE sa.QID=? AND sa.`ingSeq`=? AND st.`SCID`=? AND st.`grade`=? AND st.`class`=? \r\n" + 
				"ORDER BY studentID, multiAnswer";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			pstmt.setInt(2, ingSeq);
			pstmt.setString(3, scid);
			pstmt.setInt(4, grade);
			pstmt.setInt(5, grdNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SurveyAnswerDTO dto = new SurveyAnswerDTO();
				dto.setAnswerID(rs.getInt(1));
				dto.setStudentID(rs.getInt(2));
				dto.setMultiAnswers(rs.getInt(3));
				list.add(dto);
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

