package SurveyRelationDTO;

public class QuestionTrandTypeDTO {
	private int q_trandType;
	private String q_trandDescipt;
	
	public QuestionTrandTypeDTO() {}
	public QuestionTrandTypeDTO(int q_trandType, String q_trandDescipt) {
		this.setQ_trandType(q_trandType);
		this.setQ_trandDescipt(q_trandDescipt);
	}

	public int getQ_trandType() {
		return q_trandType;
	}

	public void setQ_trandType(int q_trandType) {
		this.q_trandType = q_trandType;
	}

	public String getQ_trandDescipt() {
		return q_trandDescipt;
	}

	public void setQ_trandDescipt(String q_trandDescipt) {
		this.q_trandDescipt = q_trandDescipt;
	}
	
	
}
