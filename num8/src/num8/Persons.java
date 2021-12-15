package num8;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Persons {

	public void displayCustomer(Statement statement, int proj_num) throws SQLException {
		
		ResultSet rSet = statement.executeQuery("select * FROM projects WHERE ProjectNumber = " + proj_num
				+ " AND Title = 'Customer';");
		
		while (rSet.next()) { 
			System.out.println( 
					"\nTitle: "+ rSet.getString("Title")
					 +"\nCustomer Name: " + rSet.getString("name")
					+ "\nContact Number: " + rSet.getInt("number")
					+ "\nPhysical Address: " + rSet.getString("address")
					+ "\nEmail Address: " + rSet.getString("email")
					);
		}
	}
}