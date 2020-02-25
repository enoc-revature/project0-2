/*
 * CHECK LIST:
 * Remove employee account creation. Have employees set up ahead of time
 * Find out how to route file input into test units.
 * Log what user was in session when the error occured or when the method was called.
 * 		String input = "Honda\nCivic\n1999\n"; // Vehicle make,model,year for example
 *		InputStream in = new ByteArrayInputStream(input.getBytes());
 *		System.setIn(in); // This part preloads the string input 
 *					  	  // into the input stream buffer before the method to test is called.
 * Number car selection and any selection.
 * Add some input validation
 * Ask for fields even though the inputs are optional.
 * Use java.text.* classes to format.
 * Maximize console during presentation and increase size.
 * Create surrogate keys.
 * Use unique function in sql statement to test duplicate accounts or vehicles.
 * Explain your program, what it is, why you're there, how it helps ppl.
 * Find a way to eliminate dead air. Faster inputs by making your output easier to suss out.
 * 
 * Start with a high level explanation every time even if everyone knows it
 * THEN dive into details or process
 * Give more engaging demos
 * Tell a story, create a narrative
 * Rehearse inputs. Repeat 
 * Don't have to follow all branch conditions
 * Always have one happy path
 * Then, demo another path if you have time or it's relevant.
 * Try to do at least one happy path.
 */


package com.revature.project0_2.core;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Main {
	private static Logger log = Logger.getRootLogger();
	static Account acct;
	static boolean isEmployee;
	static boolean exitProgram; //At almost any point, the user can exit the program.
	public static void main(String[] args) {
		Menus menus = new Menus();
		boolean madeAccount = false;
		String input = "";
		char accountType;
		while(!exitProgram) {
			madeAccount = false;
			Scanner s = new Scanner(System.in);
			System.out.print("Are you an Employee(E), Customer(C), Not a Member(N), or Quit(Q): ");
			input = s.nextLine();
			if(input.length()==0)
				continue;
			accountType = input.toUpperCase().charAt(0);

			switch(accountType) {
			case 'E' : acct = menus.employeeLogin();
						break;
			case 'C' : acct = menus.customerLogin();
						break;
			case 'N'  : menus.createAccount();
						madeAccount=true;
						acct = null;
						break;
			default  : exitProgram=true;
			}
			//exitProgram = true; // Avoid infinite loop atm
			
			if(madeAccount)
				continue;
			
			if(!exitProgram) {
				if(acct == null) {
					System.out.println("Incorrect login or password.\n\n");
				} else if(acct instanceof Employee){
					menus.employeeMenu();
				} else if(acct instanceof Customer){
					menus.customerMenu((Customer) acct);
				}
			}
			//acct = null;
		}
		System.out.println("Have a nice day!");
		log.debug("Program terminated soundly.");
	}

}
