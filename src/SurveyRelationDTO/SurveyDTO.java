package SurveyRelationDTO;

import java.util.Date;

public class SurveyDTO {
	private int surveyNo;
	private String title;
	private Date startDate; 
	private Date endDate; 
	private boolean isOpen;
	
	public SurveyDTO(){	
	}
	public int getSurveyNo() {
		return surveyNo;
	}
	public void setSurveyNo(int surveyNo) {
		this.surveyNo = surveyNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
}
