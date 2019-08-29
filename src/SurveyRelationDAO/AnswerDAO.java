package SurveyRelationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SurveyRelationDTO.SurveyAnswerDTO;
import Util.DBConn;

public class AnswerDAO {
	private static AnswerDAO dao = new AnswerDAO();
	private AnswerDAO() {}
	
	public static AnswerDAO getInstance() {
		return dao;
	}
	public void surveyAnswerRegister(SurveyAnswerDTO answerDTO) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO survey_answer(QID,studentID,isMultiAnswer,answerValue,ingSeq) VALUES (?,?,?,?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, answerDTO.getQID());
			pstmt.setInt(2, answerDTO.getStudentID());
			pstmt.setBoolean(3, answerDTO.isMultiAnswer());
			pstmt.setString(4, answerDTO.getAnswerValue());
			pstmt.setInt(5, answerDTO.getIngSeq());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
	}
	public void multiAnswerRegister(String[] ddoraeAnswer) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO multi_answermanager(answerID,multiAnswer) VALUES ((SELECT MAX(answerID) FROM survey_answer),?)";
		for(int i=0; i<ddoraeAnswer.length; i++) {
			try {
				conn =DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, Integer.parseInt(ddoraeAnswer[i]));
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
				if(conn != null) try{conn.close();}catch(SQLException sqle){}
			}
		}
	}
	
	
	public ArrayList<SurveyAnswerDTO> getAnswers(int ingSeq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SurveyAnswerDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM survey_answer WHERE ingSeq=?";
		
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ingSeq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SurveyAnswerDTO dto = new SurveyAnswerDTO();
				dto.setAnswerID(rs.getInt(1));
				dto.setQID(rs.getInt(2));
				dto.setSutudentID(rs.getInt(3));
				dto.setMultiAnswer(rs.getBoolean(4));
				dto.setAnswerValue(rs.getString(5));
				dto.setIngSeq(rs.getInt(6));
				
				list.add(dto);
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
	
	/**
	 * 1. 학생이 설문에서 단 한개라도 응답했을 경우 설문 참여로 간주하여 설문에 참여하였음을 반환함
	 * 2. 학생이 설문 내에서 답한 갯수를 가져옴
	 * @param ingSeq
	 * @param studentID
	 * @return
	 */
	public int getAnswersCount(int ingSeq, int studentID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SurveyAnswerDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM survey_answer WHERE ingSeq=? and studentID=?";
		int count = 0;
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ingSeq);
			pstmt.setInt(2, studentID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return count;
	}
	
	
	/**
	 * 설문회차 내 학급에서 또래지명 척도분류에 대한 응답들을 가져옴
	 * @param qtid
	 * @param seq
	 * @param scid
	 * @param grade
	 * @param grdNum
	 * @return
	 */
	public List<SurveyAnswerDTO> getMultiAnswersInSeq(int qtid, int seq, String scid, int grade, int grdNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SurveyAnswerDTO> list = new ArrayList<>();

		String sql = "SELECT sa.`answerID`, sa.`studentID`, ma.`multiAnswer`, qtt.`bigTrandID` AS btid, q.`Ttype` AS trid, sa.`ingSeq`\r\n" + 
				"FROM survey_answer AS sa \r\n" + 
				"LEFT JOIN multi_answermanager AS ma \r\n" + 
				"ON sa.`answerID` = ma.`answerID`\r\n" + 
				"LEFT JOIN user_students AS st\r\n" + 
				"ON sa.`studentID` = st.`SID`\r\n" + 
				"LEFT JOIN question AS q\r\n" + 
				"ON sa.`QID`=q.`QID`\r\n" + 
				"LEFT JOIN q_trand_type AS qtt\r\n" + 
				"ON q.`Ttype` = qtt.`q_trandID`\r\n" + 
				"LEFT JOIN q_trand_manager AS qtm\r\n" + 
				"ON qtt.`bigTrandID` = qtm.`bigTrandID`\r\n" + 
				"WHERE qtt.`q_trandID`=? AND sa.`ingSeq`=? AND st.`SCID`=? AND st.`grade`=? AND st.`class`=?\r\n" + 
				"ORDER BY answerID, multiAnswer";	
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qtid);
			pstmt.setInt(2, seq);
			pstmt.setString(3, scid);
			pstmt.setInt(4, grade);
			pstmt.setInt(5, grdNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SurveyAnswerDTO dto = new SurveyAnswerDTO();
				dto.setAnswerID(rs.getInt(1));
				dto.setStudentID(rs.getInt(2));
				dto.setMultiAnswers(rs.getInt(3));
				dto.setBtid(rs.getInt(4));
				dto.setTrid(rs.getInt(5));
				dto.setIngSeq(rs.getInt(6));
				list.add(dto);
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
}
