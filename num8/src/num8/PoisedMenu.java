package num8;
//Importing necessary classes for use in the main program.
import java.sql.*;
import java.text.ParseException; 



public class PoisedMenu extends PoisedInputChecks {  // Class declaration.
	public static void main(String[] args) throws ParseException {  // Main method declaration.		
		// Initializing a 'Projects' object to call methods from the Projects class.
		Projects projObj = new Projects();
		inputs inpt = new inputs();
		finalizeProj finalize = new finalizeProj();
		viewOverdue over = new viewOverdue();
		// Welcome message displayed to user.
		System.out.println("Welcome to the Poised Management System \n");
		
		// A while loop is used to repeatedly return the user to the main menu after each choice made,
		// until they select number 8, to exit the loop and log out of the program.
		while(true) {
			
			// Poised menu display with user options.
			System.out.println("\nPlease select an actio according to the number: "
				+ "\t\n1 --- View Existing Projects"
				+ "\t\n2 --- Add a New Project"
				+ "\t\n3 --- Update Existing Project Info"
				+ "\t\n4 --- Finalize a Project"
				+ "\t\n5 --- View Incomplete Projects"
				+ "\t\n6 --- View Overdue Projects"
				+ "\t\n7 --- Find a Project"
				+ "\t\n8 --- Exit program");
		
			// The user's choice is checked and saved in an integer variable.
			// The 'intCheck()' method is defined and explained below the main program method. 
			int userChoice = intCheck("selected"); 
			
			// A try-catch block is used to connect to the MySQL server and access the PoisePMS database.
			try {
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/poisepms",
	                    "Ikenna",
	                    "Ikenna08$"
                 );
			
			// Statement object created.
			Statement statement = connection.createStatement();
			
			//The actions related to user choice are now acted on. 
			//If the user selects '1', they are able to view all the projects listed in the database.
			
			if (userChoice == 1) {
				projObj.printAllFromTable(statement);
			
			// If the user selects '2', they are prompted for new project information.
			// Their inputs are checked and stored in associated variables for use.
		 
			} else if (userChoice == 2) {		
				inpt.addProject(statement);
			
			// If the user selects '3' then they are allowed to edit project details on a chosen project. 
			 // They are prompted to enter a project number and then shown a short sub-menu with two edit choices.
	
			} else if (userChoice == 3) {	
				projObj.editProject(statement);
				
			// If the user selects option 4 from the main menu, they are prompted for a project number to finalize it.
			//the number helps locate the desired project
			 
			 
			} else if (userChoice == 4) {
				finalize.finalizeProject(statement);
				
			//If the user selects option '5', they are able to view all incomplete projects in the database.
			 // An incomplete project is not finalized and has no completion date added, therefore the program locates incomplete project info 
			 
			} else if (userChoice == 5) {			
				projObj.viewIncomplete(statement);
				
			//If the user selects '6' from the main menu they are able to view all overdue projects, if any are listed. 
			//A boolean proj_Check variable is set to determine whether overdue projects are present to be displayed.
			
			} else if (userChoice == 6) {		
				over.viewOverdue(statement);
		
		// If the user selects '7' from the main menu, they are given the option to locate a project by number or name.	
		} else if (userChoice == 7) {	
			projObj.findProject(statement);
			
		// The last option in the main menu, number '8', allows the user to log out of the system.
		} else if (userChoice == 8) {
			System.out.println("Thanks for using our services. Have a great day ;)");
			break;
			
		}		
		// Catch created for SQL exception.	
		} catch (SQLException e) {  
         e.printStackTrace();
         
     }
	}
}	
}