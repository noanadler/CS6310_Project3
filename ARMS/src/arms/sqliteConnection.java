package arms;

import java.sql.*;
import javax.swing.*;


public class sqliteConnection {

	Connection conn = null;
	
	public static Connection dbConnector()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ML Miller\\workspace\\CS6310_Project3\\ARMS\\src\\armsDB.sqlite");
			//JOptionPane.showMessageDialog(null, "Database Connection Successful");
			return conn;			
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}		
		
	}
	
	
	
	
	
	
	
	
	
	
}
