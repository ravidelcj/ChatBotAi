package Database;

import java.sql.*;
public class Database {
    Connection con = null;
    Statement statement = null;
    ResultSet rs = null;
    int index;
	public void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(" jdbc:mysql://localhost:3306/bot","root","root");
			statement = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in init database: " + e);
		}
		
	}
	
	public int addAnswer(String answer){
		try {
			statement.executeQuery("Insert into answers(answer) values('" + answer + "')");
			rs = statement.executeQuery("Select COUNT(*) from answers");
			index = rs.getInt(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in add answer: " + e);
		}
		return index;
	}
	
	public void updateAnswer(int index, String answer){
		try{
			statement.executeUpdate("Update answers SET answer='" + answer + "' where id=" + index);
		}catch(Exception e){
			System.out.println("Exception in update answer: " + e);
		}
	}
}
