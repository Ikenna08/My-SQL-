package num8;
import java.util.Scanner;

public class PoisedInputChecks {  // Main class declaration.
	
	// this block of code basically works to help determine if the user entered the float and not another type 
	public static float floatCheck(String type) {	
		while(true) {  // While loop repeatedly re-prompts for input until correct.
			Scanner floatInput = new Scanner(System.in);
			String number = floatInput.nextLine();
			try {
				float output = Float.parseFloat(number);  // Attempting to parse the input to a double (i.e. checking for correct input).
				return output;		
			} catch (NumberFormatException ex) {
				System.out.println(" Please try again. Please enter the " + type + ": \n");		
			}
		}
	}
	
	// this block of code basically works to help determine if the user entered the string and not another type 
	public static String stringCheck(String type) {		
		while(true) {  // While loop repeatedly re-prompts for input until correct.
			Scanner stringInput = new Scanner(System.in);
			String input = stringInput.nextLine();			
			if ((input == null) || (input.length() > 50)) {
				System.out.println(" Please try again. Please enter the " + type + ": \n");	
			} else {
				return input;
			}
		}
	}
	
	// this block of code basically works to help determine if the user entered the integers and not another type 
		public static int intCheck(String type) {  			
			while(true) {  // While loop repeatedly re-prompts for input until correct.
				Scanner numInput = new Scanner(System.in);  
				String number = numInput.nextLine();				
				try {
					int output = Integer.parseInt(number);  // Attempting to parse the input to an integer (i.e. checking for correct input).
					return output;				
				} catch (NumberFormatException ex) {
					System.out.println(" Please try again. Please enter the " + type + ": \n");		
				}
			}
		}
	}
//recieved help from discord and family member
//https://www.webucator.com/article/how-to-check-object-type-in-java/