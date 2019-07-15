package view.viewDTO;

import java.sql.Date;

public class SearchEndsurveyDTO {

	private int surveyNo;
	private int ingSeq;
	private String SCID;
	private Date startDate;
	private Date endDate;
	private String title;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public SearchEndsurveyDTO() {}
	public SearchEndsurveyDTO(int surveyNo, String sCID, Date startDate, Date endDate, String title) {
		super();
		this.surveyNo = surveyNo;
		SCID = sCID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
	}
	
	public SearchEndsurveyDTO(int surveyNo, int ingSeq, String sCID, Date startDate, Date endDate, String title) {
		super();
		this.surveyNo = surveyNo;
		this.ingSeq = ingSeq;
		SCID = sCID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
	}
}
