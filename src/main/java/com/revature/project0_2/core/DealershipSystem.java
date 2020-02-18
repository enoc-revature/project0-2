package com.revature.project0_2.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.project0_2.dao.SerializationDAO;

public  class DealershipSystem<T> {
	// DealershipSystem is simply a collection of methods.
	private static Logger log = Logger.getRootLogger();
	public final static String DIRECTORYNAME = "src\\main\\resources\\DAOFiles\\";
	public final static double INTEREST = 0.05;

	public static String calculatePayments(String principle, String duration){
		log.debug("DealershipSystem.calculatePayments(String principle, String duration)");
		double p = Double.parseDouble(principle);
		double d = Double.parseDouble(duration);
		double totalInterest = d*DealershipSystem.INTEREST;
		double totalWithInterest = p*totalInterest;
		return Double.toString(totalWithInterest/d);
	}
	
	public static boolean checkEmployeeCredentials(String id, String pw) {
		log.debug("DealershipSystem.checkEmployeeCredentials(String id, String pw");
		//SerializationDAO dao = new SerializationDAO();
		//Employee emp = (Employee) dao.readSerial(id,'E');
		Employee emp = getEmployee(id);
		if(emp == null) {
			return false;
		}
		log.debug("pw input: "+pw);
		log.debug("pw file: " + emp.password);
		return pw.equals(emp.password);
	}

	public static boolean checkCustomerCredentials(String id, String pw) {
		log.debug("DealershipSystem.checkCustomerCredentials(String id, String pw)");
		Customer cus = getCustomer(id);
		if(cus == null) {
			return false;
		}
		log.debug("pw input: "+pw);
		log.debug("pw file: " + cus.password);
		return pw.equals(cus.password);
	}
	
	public static void save(Object o) {
		log.debug("DealershipSystem.save(Object o)");
		SerializationDAO dao = new SerializationDAO();
		dao.writeSerial(o);
	}

	public static Employee getEmployee(String id) {
		log.debug("DealershipSystem.getEmployer(String id)");
		SerializationDAO dao = new SerializationDAO();
		Employee emp = (Employee) dao.readSerial(id, 'E');
		if(emp != null) {
			log.debug("readSerial() returned Employee.");
			return emp;
		} else {
			log.debug("readSerial() returned null.");
			return null;
		}
	}

	public static Customer getCustomer(String id) {
		log.debug("DealershipSystem.getCustomer(String id)");
		SerializationDAO dao = new SerializationDAO();
		Customer cus = (Customer) dao.readSerial(id, 'C');
		if(cus != null) {
			log.debug("readSerial() returned Customer.");
			return cus;
		} else {
			log.debug("readSerial() returned null.");
			return null;
		}
	}

	public static Vehicle getVehicle(String id) {
		log.debug("DealershipSystem.getVehicle(String id)");
		SerializationDAO dao = new SerializationDAO();
		Vehicle veh = (Vehicle) dao.readSerial(id, 'V');
		if(veh != null) {
			log.debug("readSerial() returned Vehicle.");
			return veh;
		} else {
			log.debug("readSerial() returned null.");
			return null;
		}
	}

	public static ArrayList<Vehicle> getVehicles(String[] fileNames) {
	//public static ArrayList<Object> get(String[] idList) {
		log.debug("DealershipSystem.getVehicles(String[] fileNames)");
		ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
		/*
		ArrayList<Object> oList = new ArrayList<Object>();
		ArrayList<Employee> eList = new ArrayList<Employee>();
		ArrayList<Customer> cList = new ArrayList<Customer>();
		*/
		SerializationDAO dao = new SerializationDAO();
		

		/*
		if(idList[0].substring(1,1) == "E") {
			for(String id : idList) 
				eList.add((Employee) dao.readSerial(id));
			return eList;
		} else if(idList[0].substring(1,1) == "C"){
			for(String id : idList) 
				cList.add((Customer) dao.readSerial(id));
			return cList;
		} else if(idList[0].substring(1,1) == "V") {
			for(String id : idList) 
				vList.add((Vehicle) dao.readSerial(id));
			return vList;
		}
		*/
		String[] idList = new String[fileNames.length];
		for(int i=0; i<fileNames.length; i++) {
			idList[i] = fileNames[i].substring(1);
			idList[i] = idList[i].replace(".dat","");
		}
		log.debug("Before vList.add");
		System.out.println(idList[0]);
		for(String id : idList) 
			vList.add((Vehicle) dao.readSerial(id, 'V'));
		log.debug("END");
		return vList;
	}
	
	public static boolean rejectPended(Vehicle v) {
		return v.pended;
	}
}
