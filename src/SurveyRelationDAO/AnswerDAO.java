package SurveyRelationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
