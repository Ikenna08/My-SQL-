package num8;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class finalizeProj extends PoisedInputChecks {
	public void finalizeProject(Statement statement) throws SQLException {		
		// The user is prompted to enter a project number to finalize.
		System.out.println("Please enter the project number to be finalized: ");
		int proj_num = intCheck("project number");		
		// Selecting the totalFee and totalPaid columns from the table.
		ResultSet results2 = statement.executeQuery("SELECT totalFee, totalPaid FROM projects WHERE projectNumber = " + proj_num);
		float totalFee = 0;
		float totalPaid = 0;		
		// Iterating through the columns and storing the two float numbers into corresponding variables.
		while (results2.next()) {
			totalFee = results2.getFloat("totalFee"); 
			totalPaid = results2.getFloat("totalPaid");
			
		}
		// If the project has been paid in full, the amount paid will equal the total fee for the project.
		// This means no invoice needs to be generated.
		if (totalFee == totalPaid) {
			System.out.println("No invoice to be generated as the project has been completely paid.");			
			// using the date the project was paid for in full as the completion date
			LocalDate today = LocalDate.now();			
			statement.executeUpdate("UPDATE projects SET CompDate = " + "'" + today + "'" + " WHERE projectNumber = " + proj_num);			
			// The project is then marked as completed by writing 'Yes' to the complete column in the table.
			statement.executeUpdate("UPDATE projects SET completed = 'Yes' WHERE projectNumber = " + proj_num);			
			System.out.println("Project completed. ");
			printAllFromTable(statement);
		
		} else if (totalFee != totalPaid) {
			System.out.println("There is still an outstanding amount to be paid for this project. View your invoice below: \n");					
			// the outstanding fee will be displayed
			System.out.println("\nAmount Outstanding: R" + (totalFee - totalPaid)+"\n how much would you like to add to the project: ");	
			float numberInput =  floatCheck("completion date");			
			System.out.println();
			totalPaid = totalPaid + numberInput;
			statement.executeUpdate("UPDATE projects SET totalPaid = " + "'" + totalPaid + "'" + " WHERE projectNumber = " + proj_num);			
			// A successful message is displayed and the user is able to view the updated project list.
			System.out.println("Your project has been successfully completed. View projects below: ");
			printAllFromTable(statement);		
		}
	}
	
	public void printAllFromTable(Statement statement) throws SQLException{   
		// Selecting all information (i.e. all rows) from the projects table.
		ResultSet rSet = statement.executeQuery("SELECT * FROM projects");
		// Iterating through info in each column to display to the user.
		while (rSet.next()) {
			System.out.println(
	            "Project Number: \t" + rSet.getInt("projectNumber")
	            + "\nProject Name: \t" + rSet.getString("projectName") 
	            + "\nBuilding Type: \t" + rSet.getString("buildingType")        
	            + "\nPhysical buildingAddress: " + rSet.getString("buildingAddress") 
	            + "\nERF Number: \t" + rSet.getString("erfNumber") 
	            + "\nTotal Fee: \tR" + rSet.getFloat("totalFee") 
	            + "\nAmount Paid: \t" + rSet.getFloat("totalPaid")  
	            + "\ndeadline: \t" + rSet.getString("deadline") 
	            + "\ncompleted: \t" + rSet.getString("completed") 
	            + "\ncustomer: " + rSet.getString("customer") 
	            + "\narchitect: " + rSet.getString("architect") 
	            + "\nstructuralEngineer: " + rSet.getString("structuralEngineer") 
	            + "\nprojectManager: " + rSet.getString("projectManager")  
	            + "\nCompletion Date: " + rSet.getString("CompDate") 
	            + "\n"
	        );
		}	
	}
}
