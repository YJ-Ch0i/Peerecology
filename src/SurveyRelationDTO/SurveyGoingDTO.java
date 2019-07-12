package SurveyRelationDTO;

public class SurveyGoingDTO {
	private int ingSeq;
	private String SCID;
	private int surveyNo;
	private String startDate;
	private String endDate;
	public SurveyGoingDTO() {
		
	}
	public SurveyGoingDTO(String SCID,int surveyNo,String startDate,String endDate) {
		this.SCID = SCID;
		this.surveyNo = surveyNo;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public int getSurveyNo() {
		return surveyNo;
	}
	public void setSurveyNo(int surveyNo) {
		this.surveyNo = surveyNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
