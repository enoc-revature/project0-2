package com.revature.project0_2.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Menus {
	private static Logger log = Logger.getRootLogger();
	Scanner s = new Scanner(System.in);

	public Employee employeeLogin() {
		System.out.print("Employee ID: ");
		String id = s.nextLine();
		System.out.print("Password: ");
		String pw = s.nextLine();
		if(DealershipSystemWithSql.checkEmployeeCredentials(id, pw)) {
			return DealershipSystemWithSql.getEmployee(id);
		} else {
			return null;
		}

	}

	public Customer customerLogin() {
		System.out.print("Customer CC: ");
		String id = s.nextLine();
		System.out.print("Password: ");
		String pw = s.nextLine();
		if(DealershipSystemWithSql.checkCustomerCredentials(id, pw)) {
			return DealershipSystemWithSql.getCustomer(id);
		} else {
			return null;
		}

		
	}

	public void employeeMenu() {
		boolean leaveMenu = false;
		char option;
		while(!leaveMenu) {
			System.out.print("A) Add a vehicle\nB) Remove a vehicle\nC) View vehicles in the lot.\n");
			System.out.print("D) Choose offers\nE) View Payments and Payments\nQ) Logoff\n");
			System.out.print("What would you like to do? ");
			String input = s.nextLine();
			if(input.length()==0)
				continue;
			option = input.toUpperCase().charAt(0);
			switch(option) {
			case 'A' : addVehicle();
						break;
			case 'B' : removeVehicle();
						break;
			case 'C' : viewVehicles();
						break;
			case 'D' : chooseOffer();
						break;
			case 'E' : viewAllPayments();
						break;
			default : leaveMenu=true;
			}
		}
	}
	
	public void customerMenu(Customer c) {
		boolean leaveMenu = false;
		char option;
		while(!leaveMenu) {
			System.out.print("A) View vehicles in the lot.\nB) Make an Offer\n");
			System.out.print("C) View your vehicles and payments\nQ) Logoff\n");
			System.out.println("What would you like to do? ");
			String input = s.nextLine();
			if(input.length()==0)
				continue;
			option = input.toUpperCase().charAt(0);

			switch(option) {
			case 'A' : viewVehicles();
						break;
			case 'B' : makeOffer(c);
						break;
			case 'C' : viewCustomerVehicles(c);
						break;
			default : leaveMenu=true;
			}
		}	
	}

	public void createAccount() {
		System.out.print("Would you like to create an employee(E) or customer(C) account: ");
		String input = s.nextLine();
		if(input.length()==0)
			return;
		char accountType = input.toUpperCase().charAt(0);
		
		switch(accountType) {
		case 'E': newEmployee();
					break;
		case 'C': createCustomer();
					break;
		default: Main.exitProgram=true;
		}
		
	}
	
	public void newEmployee() {
		Employee emp = new Employee();

		// Get info from user
		System.out.print("First Name: ");
		emp.firstName = s.nextLine();
		System.out.print("Last Name: ");
		emp.lastName = s.nextLine();
		System.out.print("Address: ");
		emp.address = s.nextLine();
		System.out.print("Employee ID: ");
		emp.id = s.nextLine();
		System.out.print("Password: ");
		emp.password = s.nextLine();
		
		// Save info
		DealershipSystemWithSql.createEmployee(emp);
	}
	
	public void createCustomer() {
		Customer cus = new Customer();
		
		// Get info from user
		System.out.print("First Name: ");
		cus.firstName = s.nextLine();
		System.out.print("Last Name: ");
		cus.lastName = s.nextLine();
		System.out.print("Address: ");
		cus.address = s.nextLine();
		System.out.print("Credit Card Number: ");
		cus.id = s.nextLine();
		System.out.print("Password: ");
		cus.password = s.nextLine();
		
		// Save info
		DealershipSystemWithSql.save(cus);
		
	}
	public void addVehicle() {
		Vehicle v = new Vehicle();
		
		// Get info from user
		System.out.print("Make: ");
		v.make = s.nextLine();
		
		System.out.print("Model: ");
		v.model = s.nextLine();
		
		System.out.print("Year: ");
		v.year = s.nextLine();
		
		System.out.print("Mileage: ");
		v.mileage = s.nextLine();
		
		System.out.print("Condition: ");
		v.condition = s.nextLine();
		
		System.out.print("VIN: ");
		v.vin = s.nextLine();
		
		System.out.print("Bid Price: ");
		v.bid = s.nextLine();

		// Save vehicle
		// Get vehicle list to make sure there 
		// 	are no duplicates. VIN is Primary Key
		//DealershipSystemWithSql.save(v);
		DealershipSystemWithSql.save(v);
	}

	public void  removeVehicle() {
		System.out.print("VIN of vehicle to remove: ");
		String id = s.nextLine();
		//File file = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		File file = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\_" + id + ".dat");
		if(file.exists()) {
			try {
				file.delete();
			} catch(SecurityException e) {
				log.error("Unable to access file due to file access permissions.");
				e.printStackTrace();
			} 
		} else {
			System.out.println("Vehicle is already not in the system.");
		}
	}
		
	public void  viewVehicles() {
		// Display Vehicles
		log.debug("viewVehicles()");
		File folder = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles(folder.list());
		log.debug("folder.list()[0]=" + folder.list()[0]);
		//String[] fileNames = file.list();
		//vList = DealershipSystemWithSql.getVehicle(fileNames);

		// List Vehicles
		System.out.println("Make\tModel\tBid\tOffer\tPrice\tVIN\tPended");
		for(Vehicle v : vList) {
			if(v.pended) 
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s%n",
						v.model, v.model, "-", "-", "$"+v.principle, v.vin, v.pended);
			else
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s%n",
						v.model, v.model, "$"+v.bid, "$"+v.highestOffer, "-", v.vin, v.pended);
		}
	}

	public void  viewAllPayments() {
		// Display Vehicles
		log.debug("viewVehicles()");
		File folder = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles(folder.list());
		log.debug("folder.list()[0]=" + folder.list()[0]);
		//String[] fileNames = file.list();
		//vList = DealershipSystemWithSql.getVehicle(fileNames);

		// List Vehicles
		System.out.println("Make\tModel\tVIN\tOwner\tPrinciple\tPayments");
		for(Vehicle v : vList) {
			if(v.pended) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\t\t%s%n",
						v.model, v.model, v.vin, v.highestBidderOrOwner, "$"+v.principle, "$"+v.monthlyPayment);
			}
		}
	}
	/*	
	public void  viewOffers() {
		// Display Vehicles
		log.debug("viewVehicles()");
		File folder = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles(folder.list());
		log.debug("folder.list()[0]=" + folder.list()[0]);
		//String[] fileNames = file.list();
		//vList = DealershipSystemWithSql.getVehicle(fileNames);

		// List Vehicles
		System.out.println("Make\tModel\tBid\tOffer\tVIN\tOwner\tPayments");
		for(Vehicle v : vList) {
			if(!v.pended) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s%n",
						v.model, v.model, v.bid, v.highestOffer, v.vin, v.highestBidderOrOwner);
			}
		}
	}
	*/
	/*
	public void  viewPayments() {
		// Display Vehicles
		log.debug("viewPayments()");
		File folder = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles(folder.list());
		log.debug("folder.list()[0]=" + folder.list()[0]);
		//String[] fileNames = file.list();
		//vList = DealershipSystemWithSql.getVehicle(fileNames);

		// List Vehicles
		System.out.println("Make\tModel\tPayment");
		for(Vehicle v : vList)
			System.out.printf("%s\t%s\t%s\t%s\t%n", v.model, v.model, v.monthlyPayment);
	}
	*/

	public void  viewOffers() {
		log.debug("viewOffers()");
		File folder = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles(folder.list());
		log.debug("folder.list()[0]=" + folder.list()[0]);
		//String[] fileNames = file.list();
		//vList = DealershipSystemWithSql.getVehicle(fileNames);

		// List Vehicles
		System.out.println("Make\tModel\tBid\tOffer\tVIN\tOwner\tPayments");
		for(Vehicle v : vList)
			if(!v.pended)
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s%n",
						v.model, v.model, "$"+v.bid, "$"+v.highestOffer, v.vin, v.highestBidderOrOwner);
		Vehicle v = new Vehicle();
		String vin;
		Double price;
		boolean leaveMenu = false;
		Scanner s = new Scanner(System.in);
		String input = "";
		char option;
		while(!leaveMenu) {
			System.out.print("Would you like to accept an offer(y/n): ");
			input = s.nextLine();
			if(input.length()==0)
				continue;
			option = input.toUpperCase().charAt(0);

			if(option == 'Y') {
				System.out.print("Enter the vehicle VIN: ");
				vin = s.nextLine();
				System.out.print("Enter your offer: $");
				price = s.nextDouble();
				
				v = DealershipSystemWithSql.getVehicle(vin);
				if(Double.parseDouble(v.highestOffer) < price) {
					v.highestOffer = price.toString();
					DealershipSystemWithSql.save(v);
					System.out.println("Offer successful!");
				} else {
					System.out.println("Offer is too low.");
				}
			} else {
				leaveMenu = true;
			}
		}
	}
		

	public void  viewCustomerVehicles(Customer c) { // Customer Menu
		// Display Vehicles
		log.debug("viewCustomerVehicles()");
		File folder = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles(folder.list());
		log.debug("folder.list()[0]=" + folder.list()[0]);
		//String[] fileNames = file.list();
		//vList = DealershipSystemWithSql.getVehicle(fileNames);

		// List Vehicles
		System.out.println("Make\tModel\tVIN\tPrinciple\tPayments");
		for(Vehicle v : vList) {
			if(v.pended && c.id.contentEquals(v.highestBidderOrOwner))
				System.out.printf("%s\t%s\t%s\t%s\t\t%s%n",
						v.make, v.model, v.vin, "$"+v.principle, "$"+v.monthlyPayment);
		}
	}

	public void chooseOffer() { // Employee
		// Display Vehicles
		log.debug("chooseOffers()");
		File folder = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		//String[] fileNames = file.list();
		//vList = DealershipSystemWithSql.getVehicle(fileNames);

		// List Vehicles
		Vehicle v = new Vehicle();
		String vin;
		Double price;
		boolean leaveMenu = false;
		String input = "";
		char option;
		while(!leaveMenu) {
			Scanner s = new Scanner(System.in);
			log.debug("Still in while loop.");
			System.out.println("Make\tModel\tBid\tOffer\tVIN");
			ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles(folder.list());
			log.debug("folder.list()[0]=" + folder.list()[0]);
			for(Vehicle vIter : vList) {
				if(!vIter.pended) {
					System.out.printf("%s\t%s\t%s\t%s\t%s%n",
							vIter.model, vIter.model, "$"+vIter.bid, "$"+vIter.highestOffer, vIter.vin);;
				}
			}
			System.out.print("Would you like to accept an offer(y/n): ");
			input = s.nextLine();
			if(input.length()==0) {
				continue;
			}
			option = input.toUpperCase().charAt(0);
			if(option == 'Y') {
				System.out.print("Enter the vehicle VIN: ");
				vin = s.nextLine();
				log.debug("vin="+vin);
				
				v = DealershipSystemWithSql.getVehicle(vin);
					
				log.debug("After getVehicle()");
				if(v==null) {
					System.out.println("Vehicle does not exist.");
					continue;
				} else {
					v.principle = v.highestOffer;
					v.bid = null;
					v.highestOffer = null;
					v.pended = true;
					v.paymentDuration = "60";
					v.monthlyPayment = DealershipSystemWithSql.calculatePayments(v.principle, v.paymentDuration);
					DealershipSystemWithSql.save(v);
				}
			} else {
				leaveMenu = true;
			}	
		}
	}

	
	public void makeOffer(Customer c) { // Customer Menu
		/*
		viewVehicles();
		Vehicle v = new Vehicle();
		String vin;
		Double price;
		boolean leaveMenu = false;
		while(!leaveMenu) {
			System.out.print("Would you like to accept an offer(y/n): ");
			char option = s.nextLine().toUpperCase().charAt(0);
			if(option == 'Y') {
				System.out.print("Enter the vehicle VIN: ");
				vin = s.nextLine();
				System.out.print("Enter your offer: $");
				price = s.nextDouble();
			
				v = DealershipSystemWithSql.getVehicle(vin);
				if(DealershipSystemWithSql.rejectPended(v)) {
					System.out.println("Cannot make offer on a vehicle that has been sold.");
				} else if(Double.parseDouble(v.highestOffer) < price) {
					v.highestOffer = price.toString();
					DealershipSystemWithSql.save(v);
					System.out.println("Offer successful!");
				} else {
					System.out.println("Offer is too low.");
				}
			} else {
				leaveMenu = true;
			}
		}	
		*/
		// Display Vehicles
		log.debug("makeOffers()");
		File folder = new File(DealershipSystemWithSql.DIRECTORYNAME + "vehicles\\");
		//String[] fileNames = file.list();
		//vList = DealershipSystemWithSql.getVehicle(fileNames);

		// List Vehicles
		Vehicle v = new Vehicle();
		String vin;
		Double price;
		boolean leaveMenu = false;
		String input = "";
		char option;
		while(!leaveMenu) {
			Scanner s = new Scanner(System.in);
			log.debug("Still in while loop.");
			System.out.println("Make\tModel\tYear\tBid\tOffer\tVIN");
			ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles(folder.list());
			log.debug("folder.list()[0]=" + folder.list()[0]);
			for(Vehicle vIter : vList) {
				if(!vIter.pended) {
					System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s%n",
							vIter.model, vIter.model, vIter.year, "$"+vIter.bid, "$"+vIter.highestOffer, vIter.vin, vIter.pended);;
				} else {
					System.out.printf("%s\t%s\t%s\t%s\t%s\t%s%n",
							vIter.model, vIter.model, vIter.year, "-", "-", vIter.vin, vIter.pended);;
				}
			}
			System.out.print("Would you like to make an offer(y/n)? ");
			input = s.nextLine();
			if(input.length()==0) {
				continue;
			}
			option = input.toUpperCase().charAt(0);
			if(option == 'Y') {
				System.out.print("Enter the vehicle VIN: ");
				vin = s.nextLine();
				log.debug("vin="+vin);
				System.out.print("Enter your offer: $");
				price = s.nextDouble();
				
				v = DealershipSystemWithSql.getVehicle(vin);
				log.debug("After getVehicle()");
				if(v==null) {
					System.out.println("Vehicle does not exist.");
					continue;
				}
				if(DealershipSystemWithSql.rejectPended(v)) {
					System.out.println("Cannot make offer on a vehicle that has been sold.");
				} else if(v.highestOffer.isEmpty()) {
					v.highestOffer = price.toString();
					v.highestBidderOrOwner = c.id;
					DealershipSystemWithSql.save(v);
					System.out.println("Offer successful!");
				} else if(Double.parseDouble(v.highestOffer) < price) {
					v.highestOffer = price.toString();
					v.highestBidderOrOwner = c.id;
					DealershipSystemWithSql.save(v);
					System.out.println("Offer successful!");
				} else {
					System.out.println("Offer is too low.");
				}	
				
			} else {
				leaveMenu = true;
			}	
		}
	}
}
