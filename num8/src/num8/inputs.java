package num8;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException; 


public class inputs extends PoisedInputChecks {
	public void addProject(Statement statement) throws SQLException {	
		String title = "customer";
		System.out.println("\nPlease add the customer name: "); 
		String customerName = stringCheck("customer name");
		System.out.println("\nPlease add the customer surname: "); 
		String customerSur = stringCheck("customer surname");
		System.out.println("\nPlease add the customer number: "); 
		int customerNumber = intCheck("customer number");
		System.out.println("\nPlease add the customer email: "); 
		String customerEmail = stringCheck("customer email");
		System.out.println("\nPlease add the customer address: "); 
		String customerAddress = stringCheck("customer address");
		String fullCusName = customerName + customerSur;
		
		String aTitle = "architect";
		System.out.println("\nPlease add the architect name: "); 
		String architectName = stringCheck("architect name");
		System.out.println("\nPlease add the architect surname: "); 
		String architectSur = stringCheck("architect surname");
		System.out.println("\nPlease add the architect number: "); 
		int architectNumber = intCheck("architect number");
		System.out.println("\nPlease add the architect email: "); 
		String architectEmail = stringCheck("architect email");
		System.out.println("\nPlease add the architect address: "); 
		String architectAddress = stringCheck("architect address");
		String fullArcName = architectName + architectSur;
		
		String pmTitle = "project Manager";
		System.out.println("\nPlease add the ProjectManager name: "); 
		String ProjectManagerName = stringCheck("ProjectManager name");
		System.out.println("\nPlease add the ProjectManager surname: "); 
		String ProjectManagerSur = stringCheck("ProjectManager surname");
		System.out.println("\nPlease add the ProjectManager number: "); 
		int ProjectManagerNumber = intCheck("ProjectManager number");
		System.out.println("\nPlease add the ProjectManager email: "); 
		String ProjectManagerEmail = stringCheck("ProjectManager email");
		System.out.println("\nPlease add the ProjectManager address: "); 
		String ProjectManagerAddress = stringCheck("ProjectManager address");
		String fullPMName = ProjectManagerName + ProjectManagerSur;
		
		String seTitle = "Structural engineer";
		System.out.println("\nPlease add the structuralEngineer name: "); 
		String structuralEngineerName = stringCheck("structuralEngineer name");
		System.out.println("\nPlease add the structuralEngineer surname: "); 
		String structuralEngineerSur = stringCheck("structuralEngineer surname");
		System.out.println("\nPlease add the structuralEngineer number: "); 
		int structuralEngineerNumber = intCheck("structuralEngineer number");
		System.out.println("\nPlease add the structuralEngineer email: "); 
		String structuralEngineerEmail = stringCheck("structuralEngineer email");
		System.out.println("\nPlease add the structuralEngineer address: "); 
		String structuralEngineerAddress = stringCheck("structuralEngineer address");
		String fullSEName = structuralEngineerName + structuralEngineerSur;
		
		System.out.println("\nPlease add a new project number: "); 
		int proj_num = intCheck("new project number");				
		System.out.println("\nPlease add a building type: ");
		String build_type = stringCheck("building type");	
		System.out.println("\nPlease add a new project name or enter 'ac' to automatically create one: ");
		String proj_name = stringCheck("new project name");
		if (proj_name.equalsIgnoreCase("AC")) {
			proj_name = build_type + " " + customerName ;
		} 
		else {
			proj_name = proj_name;
		}
		System.out.println("\nPlease add a buildingAddress for the project: ");
		String buildingAddress = stringCheck("project buildingAddress");		
		System.out.println("\nPlease add an ERF number: ");
		String erfNumber = stringCheck("ERF number");		
		System.out.println("\nPlease add a total fee for the project: ");
		float totalFee = floatCheck("total fee");		
		System.out.println("\nPlease add the current amount paid for the project: ");
		float totalPaid = floatCheck("amount paid");		
		System.out.println("Please add a deadline for the project (e.g. 3-Dec-2020): ");
		String deadline = stringCheck("deadline");			
		
		String finalize = "false";
		String comp_date = "None";	
		statement.executeUpdate(
                "INSERT INTO projects VALUES (" + proj_num + ", " + "'" + proj_name + "'" + ", " + "'" + build_type + "'" + ", " + "'" + buildingAddress 
                + "'" + ", " + "'" + erfNumber + "'" + ", " + totalFee + ", " + totalPaid + ", " + "'" + deadline + "'" + ", " +  "'"+ architectName + "'" +", " + "'"+ customerName + "'" +", " + "'"+ProjectManagerName + "'" +", " + "'"+structuralEngineerName+"'"+", "+"'" +
                		finalize + "'" + ", " +"'"+ comp_date+"'"+");"
            );
		 
		statement.executeUpdate(
                "INSERT INTO person values("+"'" +title +"'" + ", " + "'"+ fullCusName+"'" + ", " + customerNumber +", "+ "'"+customerEmail+"'"+", "+ "'"+customerAddress+"'"+");");
		
		statement.executeUpdate(
                "INSERT INTO person values("+"'" +aTitle +"'" + ", " + "'"+ fullArcName+"'" + ", " + architectNumber +", "+ "'"+architectEmail+"'"+", "+ "'"+architectAddress+"'"+");");
		
		statement.executeUpdate(
                "INSERT INTO person values("+"'" +pmTitle +"'" + ", " + "'"+ fullPMName+"'" + ", " + ProjectManagerNumber +", "+ "'"+ProjectManagerEmail+"'"+", "+ "'"+ProjectManagerAddress+"'"+");");

		statement.executeUpdate(
                "INSERT INTO person values("+"'" +seTitle +"'" + ", " + "'"+fullSEName+"'" + ", " + structuralEngineerNumber +", "+ "'"+structuralEngineerEmail+"'"+", "+ "'"+structuralEngineerAddress+"'"+");");
		// A successful message is displayed and the user can then view the updated project list.
		System.out.println("\nYour new project was successfully added. View updated projects below: \n"); 
		printAllFromTable(statement); 
		
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
	}}}
