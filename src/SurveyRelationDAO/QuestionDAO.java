package SurveyRelationDAO;

import java.sql.*;
import java.util.ArrayList;

import SurveyRelationDTO.*;
import Util.*;

public class QuestionDAO {
	
	private static QuestionDAO dao = new QuestionDAO();
	private QuestionDAO() {}
	
	public static QuestionDAO getInstance() {
		return dao;
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
				QuestionTrandTypeDTO questionDTO = new QuestionTrandTypeDTO(rs.getInt("q_trandID"), rs.getString("descript"));
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
	public ArrayList<QuestionDTO> showAllQuestion() {
		Connection conn=null;
		Statement stmt = null;
		ArrayList<QuestionDTO> questions = new ArrayList<QuestionDTO>();
		ResultSet rs = null;
		String SQL ="Select * from question";
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
	public void queTypeRegister(String typeTitle, int offerSeq) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO q_type(descript,q_typeOfferSeq) VALUES (?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, typeTitle);
			pstmt.setInt(2, offerSeq);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
	}
	
	public void queTrandRegister(String trandTitle) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO q_trand_type(descript) VALUES (?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, trandTitle);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
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
		
		String SQL ="INSERT INTO question(title, QType, Ttype,isReverseType) VALUES (?,?,?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, questionDTO.getTitle());
			pstmt.setInt(2, questionDTO.getQType());
			pstmt.setInt(3, questionDTO.getTtype());
			pstmt.setBoolean(4, questionDTO.isReverseType());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
	}
	public void questionDifferentRegister(QuestionDTO questionDTO) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String SQL ="INSERT INTO question(title, QType, Ttype,isReverseType) VALUES (?,(SELECT MAX(q_typeID) FROM q_type),?,?)";
		try {
			conn =DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, questionDTO.getTitle());
			pstmt.setInt(2, questionDTO.getTtype());
			pstmt.setBoolean(3, questionDTO.isReverseType());
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
	public QuestionDTO showQuestion(int QID) {
		Connection conn=null;	
		Statement stmt = null;
		QuestionDTO questionDTO = new QuestionDTO();
		ResultSet rs = null;
		String SQL ="SELECT * FROM question WHERE QID='"+QID+"'";
		try {
			conn =DBConn.getConnection();
			stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
			if(rs.next()) 
			{
				questionDTO.setQID(rs.getInt("QID"));
				questionDTO.setTitle(rs.getString("title"));
				questionDTO.setQType(rs.getInt("QType"));
				questionDTO.setTtype(rs.getInt("Ttype"));
				questionDTO.setReverseType(rs.getBoolean("isReverseType"));
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
	
}

