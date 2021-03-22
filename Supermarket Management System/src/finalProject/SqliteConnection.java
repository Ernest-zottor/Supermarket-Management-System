package finalProject;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection {
	Connection conn =null;

	public static Connection dbConnector() {
		try {

			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Joe\\eclipse-workspace\\FinalPro\\database\\SupermarketDb.sqlite");
			//JOptionPane.showMessageDialog(null, "Connection Successful");

			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
