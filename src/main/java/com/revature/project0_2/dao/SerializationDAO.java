package com.revature.project0_2.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.project0_2.core.Customer;
import com.revature.project0_2.core.DealershipSystem;
import com.revature.project0_2.core.Employee;
import com.revature.project0_2.core.Vehicle;

public class SerializationDAO<T> {
	private static Logger log = Logger.getRootLogger();
	//private final String directoryName = "src\\main\\resources\\DAOFiles\\";

	public void writeSerial(T t) {
		log.debug("writeSerial() called");
		String fileName = "";

		Employee employee = null;
		Employee e2 = new Employee();
	
		Customer customer = null;
		Customer c2 = new Customer();

		Vehicle vehicle = null;
		Vehicle v2 = new Vehicle();
		String locationName = "";

		if(t instanceof Employee) {
			employee = (Employee) t;
			e2 = employee;
			fileName =  "_" + e2.userId + ".dat";
			locationName = DealershipSystem.DIRECTORYNAME + "employees\\" + fileName;
		} else if(t instanceof Customer) {
			customer = (Customer) t;
			c2 = customer;
			fileName = "_" + c2.creditCard + ".dat";
			locationName = DealershipSystem.DIRECTORYNAME + "customers\\" + fileName;
		} else if(t instanceof Vehicle) {
			vehicle = (Vehicle) t;
			v2 = vehicle;
			fileName = "_" + v2.vin + ".dat";
			locationName = DealershipSystem.DIRECTORYNAME + "vehicles\\" + fileName;
		} else {
			System.out.println("ERROR: ILLEGAL CLASS. TERMINATING PROGRAM.");
			System.exit(-101);
		}
		log.info("writeSerial called");

		//String locationName = DealershipSystem.DIRECTORYNAME + fileName + "s\\" + fileName + ".dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		log.info("Variables initialized");
		try {
			fos = new FileOutputStream(locationName);
			oos = new ObjectOutputStream(fos); // This does all the heavy-lifting of serialization
			if(t instanceof Employee) {
				oos.writeObject(e2);
			} else if(t instanceof Customer) {
				oos.writeObject(c2);
			} else if(t instanceof Vehicle) {
				oos.writeObject(v2);
			}
			log.debug("file steams opened and written successfully");
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException occurred");
			e.printStackTrace();
		} catch(IOException e) {
			log.error("IOException for opening stream and writing occurred");
			e.printStackTrace();
		} finally {
			log.debug("Finished File Output");
			try {
				fos.close();
				oos.close();
				log.debug("Finished closing file-out resources.");
			} catch(IOException e) {
				log.error("IOException up fos.close() occurred");
				e.printStackTrace();
			}
		}	
		log.info("writeSerial FINISHED");
	}
	
	public T readSerial(String id, char type) {
		Employee employee = null;
		Customer customer = null;
		Vehicle vehicle = null;

		/*
		if(id!="employee" && id!="customer" && id!="vehicle") {
		}
		*/
		log.debug("In readSer, ID="+id);
		char pathOption = type;
		String locationName = "";
		//locationName = DealershipSystem.DIRECTORYNAME + id + "s\\" + id + ".dat";
		switch(pathOption) {
		case 'E' : locationName = DealershipSystem.DIRECTORYNAME + "employees\\_" + id + ".dat";;
					break;
		case 'C' : locationName = DealershipSystem.DIRECTORYNAME + "customers\\_" + id + ".dat";;
					break;
		case 'V' : locationName = DealershipSystem.DIRECTORYNAME + "vehicles\\_" + id + ".dat";;
					break;
		default: System.out.println("ERROR: ILLEGAL CLASS. TERMINATING PROGRAM.");
				 System.exit(-102);
		}

		//String locationName = DealershipSystem.DIRECTORYNAME + id + "s\\" + id + ".dat";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object o = new Object();
		T t;
		log.debug("locationName=" + locationName);
		File file = new File(locationName);
		if(!file.exists())
			return null;
		try {
			log.debug("Started try 1");
			fis = new FileInputStream(locationName);
			ois = new ObjectInputStream(fis); // This does all the heavy-lifting of serialization
			/*
			if(id == "employee") {
				employee = (Employee) ois.readObject();
			} else if(id == "customer") {
				customer = (Customer) ois.readObject();
			} else if(id == "vehicle") {
				vehicle = (Vehicle) ois.readObject();
			}
			*/
			if(pathOption == 'E') {
				employee = (Employee) ois.readObject();
			} else if(pathOption == 'C') {
				customer = (Customer) ois.readObject();
			} else if(pathOption == 'V') {
				vehicle = (Vehicle) ois.readObject();
			}
			log.debug("Ended try 1");
		} catch (FileNotFoundException e) {
			log.debug("FILE NOT FOUND.");
			e.printStackTrace();
			return null;
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				fis.close();
				log.debug("Finished closing file-out resources.");
			} catch(IOException e) {
				log.error("IOException up fos.close() occurred");
				e.printStackTrace();
			}
		}

		log.debug("Inferred ID="+id);
		if(type == 'E') {
			return (T) employee;
		} else if(type == 'C') {
			return (T) customer;
		} else if(type == 'V') {
			return (T) vehicle;
		} else {
			System.out.println("ERROR: INCORRECT CLASS TYPE ON FILE. TERMINATING PROGRAM");
			System.exit(-103);
		}
		log.debug("readSerial() reached end");
		return null;
	}

	/*
	public boolean checkCredentials(String id, String pw) 
	*/
}
