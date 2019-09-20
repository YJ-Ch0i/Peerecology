package Util;

import java.sql.*;
public class DBConn 
{
	public static Connection getConnection() throws SQLException
	{
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//로컬환경
//			String url = "jdbc:mysql://localhost/sulmun?useSSL=false";
//			conn = DriverManager.getConnection(url, "root", "cs1234");
			
			//카페24 서버환경 
//			String url = "jdbc:mysql://peerecology.cafe24.com:3306/peerecology";
			String url = "jdbc:mysql://localhost:3306/peerecology";
			conn = DriverManager.getConnection(url, "peerecology", "vygotsky2018/");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
