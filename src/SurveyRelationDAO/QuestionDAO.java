package SurveyRelationDAO;

public class QuestionDAO {
	
	private static QuestionDAO dao = new QuestionDAO();
	private QuestionDAO() {}
	
	public static QuestionDAO getInstance() {
		return dao;
	}
	
}
