package num8;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Projects extends PoisedInputChecks {

	public void editProject(Statement statement) throws SQLException {	
		// The user is prompted to enter a project number to edit.
		System.out.println("Please enter the number of the project you wish to update: \n");
		int proj = intCheck("project number");	
		System.out.println("Please enter 'dd' for due date or 'pd' to edit amount paid till date: ");	
		String editChoice = stringCheck("edit choice");		
		
		if (editChoice == "dd") {
			System.out.println("Please enter a new project deadline: ");
			String new_date = stringCheck("new project deadline"); 			
			statement.executeUpdate(
                    "UPDATE projects SET deadline = '" + new_date + "'" + " WHERE projectNumber = " + proj
                );			
			// A successful message is displayed to the user and then they are able to view the list of updated projects.
			System.out.println("Project update successful. ");
			printAllFromTable(statement);	
		// If the user selects option 2, they are prompted to enter a new amount paid.
		
		} else if (editChoice == "pd") {
			System.out.println("Please enter a new total amount paid: ");
			float new_amount = floatCheck("new amount paid");
			
			statement.executeUpdate(
                    "UPDATE projects SET totalPaid = " + new_amount + " WHERE projectNumber = " + proj
                );			
			// A successful message is displayed to the user and then they are able to view the list of updated projects.
			System.out.println("Your project info has been successfully updated. View projects below: ");
			printAllFromTable(statement);			
		}
	}
	
	public void viewIncomplete(Statement statement) throws SQLException {
		System.out.println("\nPlease view all incomplete projects below: \n");	
		ResultSet rSet = statement.executeQuery("SELECT * FROM projects WHERE completed = 'false'");	
		// All incomplete projects are displayed using a table iterator.
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
		                + "\nCompletion Date: " + rSet.getString("CompDate")  
		                + "\n"
					);
		}
	}
	 // This method allows users to find project objects from the projects table in the external 'PoisePMS' 

	public void findProject(Statement statement) throws SQLException {
		
		System.out.println("Would you like to search for your project by 1.) project number or 2.) project name? \nPlease select either 1 or 2.");
		int search_Choice = intCheck("Number search option");
		
		/* If they choose option 1, they are prompted to enter the project number.
		 * Once the number has been entered, the program selects all info related to that project to display to the user.
		 */
		if (search_Choice == 1) {
			System.out.println("\nPlease enter the number of the project you wish to locate: ");
			int proj_num = intCheck("project number");
			
			System.out.println("\nPlease view your project details below: \n");
			
			ResultSet results6 = statement.executeQuery("SELECT * from projects WHERE projectNumber = " + proj_num);
			
			// Iterating through project info by column of the project selected by the user.
			while (results6.next()) {
	    		System.out.println(
	    				 "Project Number: \t" + results6.getInt("projectNumber")
	    	                + "\nProject Name: \t" + results6.getString("projectName") 
	    	                + "\nBuilding Type: \t" + results6.getString("buildingType")        
	    	                + "\nPhysical buildingAddress: " + results6.getString("buildingAddress") 
	    	                + "\nERF Number: \t" + results6.getString("erfNumber") 
	    	                + "\nTotal Fee: \tR" + results6.getFloat("totalFee") 
	    	                + "\nAmount Paid: \t" + results6.getFloat("totalPaid")  
	    	                + "\ndeadline: \t" + results6.getString("deadline") 
	    	                + "\ncompleted: \t" + results6.getString("completed") 
	    	                //+ "\nCompletion Date: " + results6.getString("Completion_Date") 
	    	                + "\n"
	            );
			}
		} else if (search_Choice == 2) {
			System.out.println("\nPlease enter the name of the project you wish to locate: ");
			String proj_name = stringCheck("project name");
			System.out.println("\nPlease view your project details below: \n");			
			ResultSet results7 = statement.executeQuery("SELECT * from projects WHERE projectName = '" + proj_name + "'");		
			// Iterating through project info by column of the project selected by the user.
			while (results7.next()) {
	    		System.out.println(
	    				 "Project Number: \t" + results7.getInt("projectNumber")
	    	                + "\nProject Name: \t" + results7.getString("projectName") 
	    	                + "\nBuilding Type: \t" + results7.getString("buildingType")        
	    	                + "\nPhysical buildingAddress: " + results7.getString("buildingAddress") 
	    	                + "\nERF Number: \t" + results7.getString("erfNumber") 
	    	                + "\nTotal Fee: \tR" + results7.getFloat("totalFee") 
	    	                + "\nAmount Paid: \t" + results7.getFloat("totalPaid")  
	    	                + "\ndeadline: \t" + results7.getString("deadline") 
	    	                + "\ncompleted: \t" + results7.getString("completed") 
	    	                //+ "\nCompletion Date: " + results7.getString("Completion_Date") 
	    	                + "\n"
	            );
			}
		}
	}
	public void printAllFromTable(Statement statement) throws SQLException{
        
		// Selecting all information (i.e. all rows) from the projects table.
    	ResultSet results = statement.executeQuery("SELECT * FROM projects");
    	
    	// Iterating through info in each column to display to the user.
    	while (results.next()) {
    		System.out.println(
                "Project Number: \t" + results.getInt("projectNumber")
                + "\nProject Name: \t" + results.getString("projectName") 
                + "\nBuilding Type: \t" + results.getString("buildingType")        
                + "\nPhysical buildingAddress: " + results.getString("buildingAddress") 
                + "\nERF Number: \t" + results.getString("erfNumber") 
                + "\nTotal Fee: \tR" + results.getFloat("totalFee") 
                + "\nAmount Paid: \t" + results.getFloat("totalPaid")  
                + "\ndeadline: \t" + results.getString("deadline") 
                + "\ncompleted: \t" + results.getString("completed") 
                + "\ncustomer: " + results.getString("customer") 
                + "\narchitect: " + results.getString("architect") 
                + "\nstructuralEngineer: " + results.getString("structuralEngineer") 
                + "\nprojectManager: " + results.getString("projectManager")                
                + "\n"
            );
    }
}
}