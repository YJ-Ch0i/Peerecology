package SurveyRelationDTO;

import java.sql.Date;

public class Stu_ans_ViewDTO {

	private int QID;
	private int stu_id;
	private int stu_num;
	private String stu_name;
	private boolean isMulti;
	private int answerV;
	private int surveyNo;
	private int ingSeq;
	private String SCID;
	private Date startDate;
	private Date endDate;
	private int QType;
	private String qtDes;
	private int tType;
	private String qttDes;
	private boolean isReverse;
	private int totalScore;
	
	public int getQID() {
		return QID;
	}
	public void setQID(int qID) {
		QID = qID;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public int getStu_num() {
		return stu_num;
	}
	public void setStu_num(int stu_num) {
		this.stu_num = stu_num;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public boolean isMulti() {
		return isMulti;
	}
	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}
	public int getAnswerV() {
		return answerV;
	}
	public void setAnswerV(int answerV) {
		this.answerV = answerV;
	}
	public int getSurveyNo() {
		return surveyNo;
	}
	public void setSurveyNo(int surveyNo) {
		this.surveyNo = surveyNo;
	}
	public int getIngSeq() {
		return ingSeq;
	}
	public void setIngSeq(int ingSeq) {
		this.ingSeq = ingSeq;
	}
	public String getSCID() {
		return SCID;
	}
	public void setSCID(String sCID) {
		SCID = sCID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getQType() {
		return QType;
	}
	public void setQType(int qType) {
		QType = qType;
	}
	public String getQtDes() {
		return qtDes;
	}
	public void setQtDes(String qtDes) {
		this.qtDes = qtDes;
	}
	public int gettType() {
		return tType;
	}
	public void settType(int tType) {
		this.tType = tType;
	}
	public String getQttDes() {
		return qttDes;
	}
	public void setQttDes(String qttDes) {
		this.qttDes = qttDes;
	}
	public boolean isReverse() {
		return isReverse;
	}
	public void setReverse(boolean isReverse) {
		this.isReverse = isReverse;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	public Stu_ans_ViewDTO() {}
	public Stu_ans_ViewDTO(int qID, int stu_id, int answerV, int ingSeq, String sCID, Date startDate, Date endDate,
			int qType, String qtDes, int tType, String qttDes, boolean isReverse) {
		super();
		QID = qID;
		this.stu_id = stu_id;
		this.answerV = answerV;
		this.ingSeq = ingSeq;
		SCID = sCID;
		this.startDate = startDate;
		this.endDate = endDate;
		QType = qType;
		this.qtDes = qtDes;
		this.tType = tType;
		this.qttDes = qttDes;
		this.isReverse = isReverse;
	}
	
	public Stu_ans_ViewDTO(int qID, int stu_id, int stu_num, String stu_name, int answerV, int surveyNo, int ingSeq,
			String sCID, Date startDate, Date endDate, int qType, String qtDes, int tType, String qttDes,
			boolean isReverse) {
		super();
		QID = qID;
		this.stu_id = stu_id;
		this.stu_num = stu_num;
		this.stu_name = stu_name;
		this.answerV = answerV;
		this.surveyNo = surveyNo;
		this.ingSeq = ingSeq;
		SCID = sCID;
		this.startDate = startDate;
		this.endDate = endDate;
		QType = qType;
		this.qtDes = qtDes;
		this.tType = tType;
		this.qttDes = qttDes;
		this.isReverse = isReverse;
	}
	
	public Stu_ans_ViewDTO(int qID, int stu_id, String stu_name, int answerV, int ingSeq, String sCID, Date endDate,
			String qtDes, String qttDes, int totalScore) {
		super();
		QID = qID;
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.answerV = answerV;
		this.ingSeq = ingSeq;
		SCID = sCID;
		this.endDate = endDate;
		this.qtDes = qtDes;
		this.qttDes = qttDes;
		this.totalScore = totalScore;
	}
	public Stu_ans_ViewDTO(int qID, int stu_id, String stu_name, int answerV, int ingSeq, String sCID, Date endDate,
			int qType, String qtDes, int tType, String qttDes, int totalScore) {
		super();
		QID = qID;
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.answerV = answerV;
		this.ingSeq = ingSeq;
		SCID = sCID;
		this.endDate = endDate;
		QType = qType;
		this.qtDes = qtDes;
		this.tType = tType;
		this.qttDes = qttDes;
		this.totalScore = totalScore;
	}
	public Stu_ans_ViewDTO(int qID, int stu_id, int stu_num, String stu_name, boolean isMulti, int answerV,
			int surveyNo, int ingSeq, String sCID, Date startDate, Date endDate, int qType, String qtDes, int tType,
			String qttDes, boolean isReverse, int totalScore) {
		super();
		QID = qID;
		this.stu_id = stu_id;
		this.stu_num = stu_num;
		this.stu_name = stu_name;
		this.isMulti = isMulti;
		this.answerV = answerV;
		this.surveyNo = surveyNo;
		this.ingSeq = ingSeq;
		SCID = sCID;
		this.startDate = startDate;
		this.endDate = endDate;
		QType = qType;
		this.qtDes = qtDes;
		this.tType = tType;
		this.qttDes = qttDes;
		this.isReverse = isReverse;
		this.totalScore = totalScore;
	}
	
	
}
