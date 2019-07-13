package SurveyRelationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import SurveyRelationDTO.*;
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
		for(int i=0; i<ddoraeAnswer.length; i++)
		{
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
}
