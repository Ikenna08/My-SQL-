package num8;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class viewOverdue {
public void viewOverdue(Statement statement) throws SQLException, ParseException {
		
		boolean proj_Check = false;
		String[] info;
		int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		String[] monthsofYear = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		int monthNum = 0;
		
		// Overdue projects will be incomplete, therefore only the deadline date info from columns of incomplete projects are located.
		ResultSet results4 = statement.executeQuery("SELECT deadline FROM projects WHERE completed = 'false'");
		
		// Iterating through the deadline dates in the incomplete projects to check if they are overdue.
		while (results4.next()) {
			String date_info = results4.getString("deadline");
			info = date_info.split("-");
			int day = Integer.parseInt(info[0]);
			String monthInfo = info[1];
			String month = (monthInfo.substring(0,2));
			int year = Integer.parseInt(info[2]);
			for (int index = 0; index < monthsofYear.length ; index++) {	
				if (month.equalsIgnoreCase(monthsofYear[index])) {
					monthNum = months[index];
				}
			}
			// Getting the current date and storing it as a string.
			LocalDate today = LocalDate.now();	
			String current = "" + java.time.LocalDate.now();  
			
			// Creating a new simple date format object.
			SimpleDateFormat dateObj = new SimpleDateFormat("yyyy-MM-dd");
			
			// Dates d1 and d2 are then created by parsing string info from 'current' date 
			Date dC = dateObj.parse(current);		
			Date dA = dateObj.parse(day + "-" + monthNum + "-" + year);
			
			// If the current date has passed the deadline for the project, it is overdue.
			// The proj_Check is set to 'true' and all of the columns for that project are selected and displayed.
			if (dC.compareTo(dA) < 0) {
				proj_Check = true;
				
				System.out.println("\nPlease view all overdue projects below: \n");
				ResultSet rSet = statement.executeQuery("SELECT * from projects WHERE deadline = '" + date_info + "'");
				
				// Iterating and displaying all info related to the overdue project.
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
			// However, if there are no overdue projects, proj_Check is set to 'false'.
			} else {
				proj_Check = false;
			}
	// If proj_Check is set at false after the projects are all checked, an appropriate message is displayed to the user.		
	} if (proj_Check == false) {
		System.out.println("There are no overdue projects listed on the system.");
	}
	}
}
