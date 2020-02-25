package com.revature.project0_2.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
//import com.revature.jdbc.SqlDao;


public class DealershipSystemWithSql extends DealershipSystem{
	private static Logger log = Logger.getRootLogger();
	private static final String[] credentials =
		{
				"jdbc:postgresql://localhost:5432/postgres", // End-point
				"postgres",									 // Username
				"postgres"									 // Password
		};
	
	// CREATE
	public static void createEmployee(Employee e) {
		log.trace("createEmployee(Employee)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			if(!recordExists(e.userId, 'E')) { // If the vehicle is not a duplicate entry
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO employees_proj_0 " +
						"(firstName,lastName,address,email,userid,password) " +
						"VALUES(?,?,?,?,?,?)");
				ps.setString(1, e.firstName);
				ps.setString(2, e.lastName);
				ps.setString(3, e.address);
				ps.setString(4, e.email);
				ps.setString(5, e.userId);
				ps.setString(6, e.password);
				ps.executeUpdate();
			} else {
				System.out.println("Duplicate Employee Entry!");
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}

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

	public static boolean recordExists(String id, Character ch) {
		log.trace("recordExists(String,Character)");
		Boolean duplicate = false;
		String query = "";
		switch(ch) {
		case 'E': query = "SELECT * FROM employees_proj_0 WHERE userid=?";
					break;
		case 'C': query = "SELECT * FROM customers_proj_0 WHERE userid=?";
					break;
		case 'V': query = "SELECT * FROM vehicles_proj_0 WHERE vin=?";
					break;
		}
		log.debug("Query String: " + query);
		log.debug("ID: " + id);
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			log.debug("recordExists postgres connection");
			PreparedStatement psCheckUnique = conn.prepareStatement(query);
			psCheckUnique.setString(1, id);
			log.debug("psCheckUnique");
			ResultSet rsCheckUnique = psCheckUnique.executeQuery();
			log.debug("rsCheckUnique");
			duplicate = rsCheckUnique.next();
			log.debug("duplicate=rsChechUnique assigns " + duplicate);
			duplicate = rsCheckUnique.next();
			if(duplicate) {
				log.debug("Duplicate exists");
				System.out.println("Duplicate exists");
			}
		} catch(SQLException err) {
			err.printStackTrace();
		}
		return duplicate;
	}

	public static void createCustomer(Customer c) {
		log.trace("createCustomer(Customer)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			if(!recordExists(c.userId, 'C')) {
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO customers_proj_0 " +
						"(firstName,lastName,address,email,creditcard,userid,password) " +
						"VALUES(?,?,?,?,?,?,?)");
				ps.setString(1, c.firstName);
				ps.setString(2, c.lastName);
				ps.setString(3, c.address);
				ps.setString(4, c.email);
				ps.setString(5, c.creditCard);
				ps.setString(6, c.userId);
				ps.setString(7, c.password);
				ps.executeUpdate();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createVehicle(Vehicle v) {
		log.trace("createVehicle(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			if(!recordExists(v.vin, 'V')) {
				//log.debug("createVehicle before ps creation");
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO vehicles_proj_0 " +
						"(make,model,year,mileage,vin,bid,highestOffer,highestBidderOrOwner,monthlyPayment,principle,paymentDuration,pended) " +
						"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				//log.debug("createVehicle after ps creation");
				ps.setString(1, v.make); //log.debug("make assigned");
				ps.setString(2, v.model); //log.debug("model assigned");
				ps.setInt(3, v.year); //log.debug("year assigned");
				ps.setDouble(4, v.mileage); //log.debug("mileage assigned");
				ps.setString(5, v.vin); //log.debug("vin assigned");
				ps.setDouble(6, v.bid); //log.debug("bid assigned");
				ps.setDouble(7, v.highestOffer); //log.debug("highestOffer assigned");
				ps.setString(8, v.highestBidderOrOwner); //log.debug("highestBidderOrOwner assigned");
				ps.setDouble(9, v.monthlyPayment); //log.debug("monthlyPayment assigned");
				ps.setDouble(10, v.principle); //log.debug("principle assigned");
				ps.setInt(11, v.paymentDuration); //log.debug("paymentDuration assigned");
				ps.setBoolean(12, v.pended); //log.debug("pended assigned");
				//log.debug("createVehicle ps assigned");
				ps.executeUpdate();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
	
	// READ
	public static Employee getEmployee(String id) {
		log.trace("DealershipSystemWithSql.getEmployee(String id)");
		Employee e = null;
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"SELECT * FROM employees_proj_0 " +
					"WHERE userid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				e = new Employee();
				e.firstName = rs.getString("firstName");
				e.lastName = rs.getString("lastName");
				e.address = rs.getString("address");
				e.email = rs.getString("email");
				e.userId = rs.getString("userid");
				e.password = rs.getString("password");
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
		log.debug("EmployeeID: = " + e.userId);
		return e;
	}

	public static Customer getCustomer(String id) {
		log.trace("DealershipSystemWithSql.getCustomer(String id)");
		Customer c = null;
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"SELECT * FROM customers_proj_0 " +
					"WHERE userid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Customer();
				c.firstName = rs.getString("firstName");
				c.lastName = rs.getString("lastName");
				c.address = rs.getString("address");
				c.userId = rs.getString("userid");
				c.password = rs.getString("password");
				c.email = rs.getString("email");
				c.creditCard = rs.getString("creditCard");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static Vehicle getVehicle(String vin) {
		log.trace("DealershipSystemWithSql.getVehicle(String)");
		Vehicle v = null;
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"SELECT * FROM vehicles_proj_0 " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, vin);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				v = new Vehicle();
				v.make = rs.getString("make");
				v.model = rs.getString("model");
				v.year = rs.getInt("year");
				v.mileage = rs.getDouble("mileage");
				v.vin = rs.getString("vin");
				v.bid = rs.getDouble("bid");
				v.highestOffer = rs.getDouble("highestOffer");
				v.highestBidderOrOwner = rs.getString("highestBidderOrOwner");
				v.monthlyPayment = rs.getDouble("monthlyPayment");
				v.principle = rs.getDouble("principle");
				v.paymentDuration = rs.getInt("paymentDuration");
				v.pended = rs.getBoolean("pended");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	public static ArrayList<Vehicle> getVehicles() {
		log.trace("DealershipSystemWithSql.getVehicles()");
		Vehicle v = null;
		ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query = "SELECT * FROM vehicles_proj_0 " +
							"ORDER BY bid";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				v = new Vehicle();
				v.make = rs.getString("make");
				v.model = rs.getString("model");
				v.year = rs.getInt("year");
				v.mileage = rs.getDouble("mileage");
				v.vin = rs.getString("vin");
				v.bid = rs.getDouble("bid");
				v.highestOffer = rs.getDouble("highestOffer");
				v.highestBidderOrOwner = rs.getString("highestBidderOrOwner");
				v.monthlyPayment = rs.getDouble("monthlyPayment");
				v.principle = rs.getDouble("principle");
				v.paymentDuration = rs.getInt("paymentDuration");
				v.pended = rs.getBoolean("pended");
				vList.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vList;
	}
	
	// UPDATE
	public static void updateOffer(Vehicle v) {
		log.trace("updateOffer(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"UPDATE vehicles_proj_0 " +
					"SET highestOffer=?,highestBidderOrOwner=? " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1,v.highestOffer); // gets updated in calling function
			ps.setString(2,v.highestBidderOrOwner); // gets updated in calling function
			ps.setString(3,v.vin);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateVehicleSold(Vehicle v) {
		log.trace("updateVehicleSold(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"UPDATE vehicles_proj_0 " +
					"SET principle=?,highestOffer=?,highestBidderOrOwner=?,monthlyPayment=?,pended=? " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1,v.principle); //set new principle
			ps.setDouble(2,v.principle); //set highestOffer
			ps.setString(3,v.highestBidderOrOwner);
			ps.setDouble(4,v.monthlyPayment);
			ps.setBoolean(5,v.pended);
			ps.setString(6,v.vin);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DELETE
	public static void removeEmployee(Employee e) {
		log.trace("removeVehicle(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM employees_proj_0 WHERE userid=?");
			ps.setString(1, e.userId);
			ps.executeUpdate();
			
		} catch (SQLException err) {
			err.printStackTrace();
		}	
	}

	public static void removeCustomer(Customer c) {
		log.trace("removeVehicle(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM customers_proj_0 WHERE userid=?");
			ps.setString(1, c.userId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public static void removeVehicle(Vehicle v) {
		log.trace("removeVehicle(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM vehicles_proj_0 WHERE vin=?");
			ps.setString(1, v.vin);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public static void removeVehicle(String id) {
		log.trace("removeVehicle(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM vehicles_proj_0 WHERE vin=?");
			ps.setString(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public static Double calculatePayments(Double principle) {
		log.trace("calculatePayments(Double, Integer)");
		double d = 60.0; // duration in months
		double t = 60.0/12.0; // duration in years
		double p = principle;
		double totalInterest = DealershipSystem.INTEREST*t;
		double totalWithInterest = p*(1+totalInterest);
		return totalWithInterest/d;
	}
}
