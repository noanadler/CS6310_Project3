package arms;

import java.sql.*;
import javax.swing.*;


public class sqliteConnection {

	Connection conn = null;
	
	public static Connection dbConnector()
	{
		try{
			Class.forName("org.sqlite.JDBC");	
			
			// Connection path can be changed to 'src/armsDB.sqlite' or 'armsDB.sqlite' depending on location of database
			
			//Connection conn = DriverManager.getConnection("jdbc:sqlite:src/armsDB.sqlite");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:armsDB.sqlite");
			
			
			//JOptionPane.showMessageDialog(null, "Database Connection Successful");
			return conn;			
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}		
		
	}
	
	
	
	
	
	
	
	
	
	
}
