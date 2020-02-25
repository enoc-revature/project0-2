package com.revature.project0_2.core;
import java.text.NumberFormat;
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
		System.out.print("Customer ID: ");
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
			System.out.println("\nWhat would you like to do? ");
			System.out.print("A) Add a vehicle\nB) Remove a vehicle\nC) View vehicles in the lot.\n");
			System.out.print("D) Choose offers\nE) View Payments and Payments\nQ) Logoff\n");
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
			System.out.println("\nWhat would you like to do? ");
			System.out.print("A) View vehicles in the lot.\nB) Make an Offer\n");
			System.out.print("C) View your vehicles and payments\nQ) Logoff\n");
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
		System.out.print("\nWould you like to create a customer account(y/n): ");
		String input = s.nextLine();
		if(input.length()==0)
			return;
		char accountType = input.toUpperCase().charAt(0);
		
		switch(accountType) {
		case 'Y': createCustomer();
					break;
		default: Main.exitProgram=true;
		}
		
	}
	
	public void createCustomer() {
		Customer cus = new Customer();
		
		// Get info from user
		System.out.println("\nPlease enter the following information (*) indicates a required field");
		System.out.print("First Name: ");
		cus.firstName = s.nextLine();
		System.out.print("Last Name: ");
		cus.lastName = s.nextLine();
		System.out.print("Address: ");
		cus.address = s.nextLine();
		System.out.print("Email(*): ");
		cus.email = s.nextLine();
		System.out.print("Credit Card Number(*): ");
		cus.creditCard = s.nextLine();
		System.out.print("UserID(*): ");
		cus.userId = s.nextLine();
		System.out.print("Password(*): ");
		cus.password = s.nextLine();
		
		// Save info
		DealershipSystemWithSql.createCustomer(cus);
		
	}
	public void addVehicle() {
		String temp = null;
		Vehicle v = new Vehicle();
		
		// Get info from user
		System.out.print("Make(*): ");
		v.make = s.nextLine();
		
		System.out.print("Model(*): ");
		v.model = s.nextLine();
		
		System.out.print("Year: ");
		temp = s.nextLine();
		v.year = Integer.parseInt(temp);
		
		System.out.print("Mileage: ");
		temp = s.nextLine();
		v.mileage = Double.parseDouble(temp);
		
		System.out.print("VIN(*): ");
		v.vin = s.nextLine();
		
		System.out.print("Initial Price(*): ");
		temp = s.nextLine();
		v.bid = Double.parseDouble(temp);
		
		log.debug("Make: "+v.make);
		log.debug("Model: "+v.model);
		log.debug("Year: "+v.year);
		log.debug("Mileage: "+v.mileage);
		log.debug("VIN: "+v.vin);
		log.debug("Initial Price: "+v.bid);

		// Save vehicle
		DealershipSystemWithSql.createVehicle(v);
	}

	public void  removeVehicle() {
		log.debug("removeOffers()");

		// List Vehicles
		Vehicle v = new Vehicle();
		String vin;
		Double price;
		boolean leaveMenu = false;
		String input = "";
		char option;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		int i = 1;
		int carNum = 0;
		while(!leaveMenu) {
			Scanner s = new Scanner(System.in);
			log.debug("Still in while loop.");
			System.out.println("   Make\t\tModel\t\tPrice\tOffer\t\tVIN\tPended");
			ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles();
			i=1;
			for(Vehicle vIter : vList) {
				System.out.printf("%s) %s\t%s\t%s\t%s\t%s\t%s%n",
						i, vIter.make, vIter.model, nf.format(vIter.bid), nf.format(vIter.highestOffer), vIter.vin, vIter.pended?"Yes":"No");
				i++;
			}
			System.out.print("Would you like to remove a vehicle(y/n): ");
			input = s.nextLine();
			if(input.length()==0) {
				continue;
			}
			option = input.toUpperCase().charAt(0);
			if(option == 'Y') {
				System.out.print("Enter the number next to the vehicle: ");
				//System.out.print("Choose the number next to the vehicle: ");
				//String id = s.nextLine();
				//vin = s.nextLine();
				//log.debug("vin="+vin);
				carNum = s.nextInt();
				log.debug("carNum="+carNum);
				
				//v = DealershipSystemWithSql.getVehicle(vin);
				v = DealershipSystemWithSql.getVehicle(vList.get(carNum-1).vin);
					
				log.debug("After getVehicle()");
				if(v==null) {
					System.out.println("Vehicle does not exist.");
					continue;
				} else {
					DealershipSystemWithSql.removeVehicle(v);
				}
			} else {
				leaveMenu = true;
			}	
		}
	}
		
	public void  viewVehicles() {
		// Display Vehicles
		log.trace("viewVehicles()");
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles();

		// List Vehicles
		System.out.println("   Make\t\tModel\tInitial Price\tOffer\t\tSettled Price\tVIN\t\tPended");
		int i=1;
		for(Vehicle v : vList) {
			if(v.pended) 
				System.out.printf("%s) %s\t%s\t%s\t\t%s\t%s\t%s\t%s%n",
						i, v.make, v.model, "-", "-\t", nf.format(v.principle), v.vin+"\t", v.pended?"Yes":"No");
			else 
				System.out.printf("%s) %s\t%s\t%s\t%s\t%s\t%s\t%s%n",
						i, v.make, v.model, nf.format(v.bid), nf.format(v.highestOffer), "-\t", v.vin+"\t", v.pended?"Yes":"No");
			i++;
		}
	}

	public void  viewAllPayments() {
		// Display Vehicles
		log.trace("viewAllPayments()");
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		Customer c = null;
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles();

		// List Vehicles
		System.out.println("   Make\t\tModel\tVIN\temail\t\tPrinciple\tPayments");
		int i=1;
		for(Vehicle v : vList) {
			c = DealershipSystemWithSql.getCustomer(v.highestBidderOrOwner);
			if(v.pended) {
				System.out.printf("%s) %s\t%s\t%s\t%s\t%s\t%s%n",
						i, v.make, v.model, v.vin, c.email, nf.format(v.principle), nf.format(v.monthlyPayment));
			}
			i++;
		}
	}

	public void  viewOffers() {
		log.debug("viewOffers()");
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles();
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		// List Vehicles
		System.out.println("Make\tModel\tPrice\tOffer\tVIN\tOwner\tPayments");
		for(Vehicle v : vList)
			if(!v.pended)
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s%n",
						v.make, v.model, nf.format(v.bid), nf.format(v.highestOffer), v.vin, v.highestBidderOrOwner);
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
				System.out.print("Enter the number next to the vehicle: ");
				vin = s.nextLine();
				System.out.print("Enter your offer: $");
				price = s.nextDouble();
				
				v = DealershipSystemWithSql.getVehicle(vin);
				if(v.highestOffer < price) {
					v.highestOffer = price;
					DealershipSystemWithSql.updateVehicleSold(v);
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
		ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles();
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		// List Vehicles
		System.out.println("Make\tModel\tVIN\tPrinciple\tPayments");
		for(Vehicle v : vList) {
			if(v.pended && c.userId.contentEquals(v.highestBidderOrOwner))
				System.out.printf("%s\t%s\t%s\t%s\t%s%n",
						v.make, v.model, v.vin, nf.format(v.principle), nf.format(v.monthlyPayment));
		}
	}

	public void chooseOffer() { // Employee
		// Display Vehicles
		log.debug("chooseOffer()");

		// List Vehicles
		Vehicle v = new Vehicle();
		String vin;
		Double price;
		boolean leaveMenu = false;
		String input = "";
		char option;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		int i = 1;
		int offset=0;
		int carNum = 0;
		while(!leaveMenu) {
			Scanner s = new Scanner(System.in);
			log.debug("Still in while loop.");
			System.out.println("   Make\t\tModel\tPrice\t\tOffer\t\tVIN");
			ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles();
			i=1;
			offset=0;
			for(Vehicle vIter : vList) {
				if(!vIter.pended) {
					System.out.printf("%s) %s\t%s\t%s\t%s\t%s%n",
							i, vIter.make, vIter.model, nf.format(vIter.bid), nf.format(vIter.highestOffer), vIter.vin);
					i++;
				} else {
					offset++;
				}
			}
			System.out.print("Would you like to accept an offer(y/n): ");
			input = s.nextLine();
			if(input.length()==0) {
				continue;
			}
			option = input.toUpperCase().charAt(0);
			if(option == 'Y') {
				System.out.print("Enter the number next to the vehicle: ");
				//vin = s.nextLine();
				//log.debug("vin="+vin);
				carNum = s.nextInt();
				log.debug("carNum="+carNum);
				log.debug("offset="+offset);
				log.debug("carNum-offset="+(carNum+offset));
				
				//v = DealershipSystemWithSql.getVehicle(vin);
				log.debug("Model: " + vList.get(carNum+offset-1).model);
				v = DealershipSystemWithSql.getVehicle(vList.get(carNum+offset-1).vin);
					
				log.debug("After getVehicle()");
				if(v==null) {
					System.out.println("Vehicle does not exist.");
					continue;
				} else {
					v.principle = v.highestOffer;
					v.bid = null;
					v.highestOffer = null;
					v.pended = true;
					v.paymentDuration = 60;
					v.monthlyPayment = DealershipSystemWithSql.calculatePayments(v.principle);
					DealershipSystemWithSql.updateVehicleSold(v);
				}
			} else {
				leaveMenu = true;
			}	
		}
	}

	
	public void makeOffer(Customer c) { // Customer Menu
		// Display Vehicles
		log.trace("makeOffer(Customer)");

		// List Vehicles
		Vehicle v = new Vehicle();
		String vin;
		Double price;
		boolean leaveMenu = false;
		String input = "";
		char option;
		int i = 1;
		int carNum = 1;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		while(!leaveMenu) {
			Scanner s = new Scanner(System.in);
			log.debug("Still in while loop.");
			System.out.println("   Make\t\tModel\tYear\tPrice\t\tOffer\t\tVIN\tPended");
			ArrayList<Vehicle> vList = DealershipSystemWithSql.getVehicles();
			i=1;
			for(Vehicle vIter : vList) {
				/*
				if(v.pended) 
					System.out.printf("%s) %s\t%s\t%s\t\t%s\t%s\t%s\t%s%n",
							i, v.make, v.model, "-", "-", nf.format(v.principle), v.vin+"\t", v.pended?"Yes":"No");
				else				 
					System.out.printf("%s) %s\t%s\t%s\t%s\t%s\t%s\t%s%n",
							i, v.make, v.model, nf.format(v.bid), nf.format(v.highestOffer), "-\t", v.vin+"\t", v.pended?"Yes":"No");
				*/
				if(!vIter.pended) {
					System.out.printf("%s) %s\t%s\t%s\t%s\t%s\t%s\t%s%n",
							i, vIter.make, vIter.model, vIter.year, nf.format(vIter.bid), nf.format(vIter.highestOffer), vIter.vin, vIter.pended?"Yes":"No");
				} else {
					System.out.printf("%s) %s\t%s\t%s\t%s\t%s\t%s\t%s%n",
							i, vIter.make, vIter.model, vIter.year, "-\t", "-\t", vIter.vin, vIter.pended?"Yes":"No");
				}
				i++;
			}
			System.out.print("Would you like to make an offer(y/n)? ");
			input = s.nextLine();
			if(input.length()==0) {
				continue;
			}
			option = input.toUpperCase().charAt(0);
			if(option == 'Y') {
				System.out.print("Enter the number next to vehicle: ");
				//vin = s.nextLine();
				carNum = Integer.parseInt(s.nextLine());
				log.debug("carNume="+carNum);
				System.out.print("Enter your offer: $");
				price = s.nextDouble();
				
				//v = DealershipSystemWithSql.getVehicle(vin);
				v = DealershipSystemWithSql.getVehicle(vList.get(carNum-1).vin);
				log.debug("After getVehicle()");
				if(v==null) {
					System.out.println("Vehicle does not exist.");
					continue;
				}
				if(DealershipSystemWithSql.rejectPended(v)) {
					System.out.println("Cannot make offer on a vehicle that has been sold.");
				} else if(v.highestOffer == null) {
					v.highestOffer = price;
					v.highestBidderOrOwner = c.userId;
					//DealershipSystemWithSql.save(v);
					DealershipSystemWithSql.updateOffer(v);
					System.out.println("Offer successful!");
				} else if(v.highestOffer < price) {
					v.highestOffer = price;
					v.highestBidderOrOwner = c.userId;
					//DealershipSystemWithSql.save(v);
					DealershipSystemWithSql.updateOffer(v);
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
