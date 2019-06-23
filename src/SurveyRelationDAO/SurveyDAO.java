package SurveyRelationDAO;

public class SurveyDAO {
	
	private static SurveyDAO dao = new SurveyDAO();
	private SurveyDAO() {}
	
	public static SurveyDAO getInstance() {
		return dao;
	}
	
}
