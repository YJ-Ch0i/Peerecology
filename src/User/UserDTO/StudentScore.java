package User.UserDTO;

public class StudentScore {

	private int sid;
	private String name;
	private int ingSeq;
	private int qid;
	private int qType;
	private int tType;
	private int bigTrandType;
	private String answer;
	private int score = 0;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIngSeq() {
		return ingSeq;
	}
	public void setIngSeq(int ingSeq) {
		this.ingSeq = ingSeq;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getqType() {
		return qType;
	}
	public void setqType(int qType) {
		this.qType = qType;
	}
	public int gettType() {
		return tType;
	}
	public void settType(int tType) {
		this.tType = tType;
	}
	public int getBigTrandType() {
		return bigTrandType;
	}
	public void setBigTrandType(int bigTrandType) {
		this.bigTrandType = bigTrandType;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public StudentScore() {}
	public StudentScore(int sid, String name, int ingSeq, int qType, int tType, int score) {
		super();
		this.sid = sid;
		this.name = name;
		this.ingSeq = ingSeq;
		this.qType = qType;
		this.tType = tType;
		this.score = score;
	}
	public StudentScore(int sid, String name, int ingSeq, int qid, int qType, int tType, int score) {
		super();
		this.sid = sid;
		this.name = name;
		this.ingSeq = ingSeq;
		this.qid = qid;
		this.qType = qType;
		this.tType = tType;
		this.score = score;
	}
	public StudentScore(int sid, String name, int ingSeq, int qid, int qType, int tType, String answer, int score) {
		super();
		this.sid = sid;
		this.name = name;
		this.ingSeq = ingSeq;
		this.qid = qid;
		this.qType = qType;
		this.tType = tType;
		this.answer = answer;
		this.score = score;
	}
	public StudentScore(int sid, String name, int ingSeq, int qid, int qType, int tType, int bigTrandType,
			String answer, int score) {
		super();
		this.sid = sid;
		this.name = name;
		this.ingSeq = ingSeq;
		this.qid = qid;
		this.qType = qType;
		this.tType = tType;
		this.bigTrandType = bigTrandType;
		this.answer = answer;
		this.score = score;
	}
}
