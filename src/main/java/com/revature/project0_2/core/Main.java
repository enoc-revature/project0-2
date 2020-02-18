package com.revature.project0_2.core;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.revature.project0_2.dao.SerializationDAO;

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
