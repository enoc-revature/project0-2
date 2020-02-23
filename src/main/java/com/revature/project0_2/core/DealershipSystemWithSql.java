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

	public static boolean recordExists(String id, Character ch) {
		log.trace("recordExists(String,Character)");
		Boolean duplicate = false;
		String query = "";
		switch(ch) {
		case 'E': query = "SELECT count(*) FROM employees_proj_0 WHERE userid=?";
					break;
		case 'C': query = "SELECT count(*) FROM customers_proj_0 WHERE userid=?";
					break;
		case 'V': query = "SELECT count(*) FROM vehicles_proj_0 WHERE vin=?";
					break;
		}
		log.debug("Query String: " + query);
		log.debug("UserID: " + id);
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement psCheckUnique = conn.prepareStatement(query);
			psCheckUnique.setString(1, id);
			ResultSet rsCheckUnique = psCheckUnique.executeQuery();
			if(rsCheckUnique.next())
				duplicate = true;
		} catch(SQLException err) {
			err.printStackTrace();
		}
		return duplicate;
	}

	public static void createCustomer(Customer c) {
		log.trace("createCustomer(Customer)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO customers_proj_0 " +
					"(firstName,lastName,address,email,creditcard,userid,password) " +
					"VALUES(?,?,?,?,?)");
			ps.setString(1, c.firstName);
			ps.setString(2, c.lastName);
			ps.setString(3, c.address);
			ps.setString(4, c.email);
			ps.setInt(5, c.creditCard);
			ps.setString(6, c.userId);
			ps.setString(7, c.password);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createVehicle(Vehicle v) {
		log.trace("createVehicle(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO vehicles_proj_0 " +
					"(make,model,year,mileage,mileage,condition,vin,bid) " +
					"VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, v.make);
			ps.setString(2, v.model);
			ps.setInt(3, v.year);
			ps.setDouble(4, v.mileage);
			//ps.setString(5, v.condition);
			ps.setString(5, v.vin);
			ps.setDouble(6, v.bid);
			ps.executeUpdate();
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
	
	// READ
	public static Employee getEmployee(String id) {
		log.trace("DealershipSystemWithSql.getEmployee(String id)");
		Employee e = new Employee();
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"SELECT * FROM employees_proj_0 " +
					"WHERE userid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				e.firstName = rs.getString("firstName");
				e.lastName = rs.getString("lastName");
				e.address = rs.getString("address");
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
		Customer c = new Customer();
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"SELECT * FROM employees_proj_0 " +
					"WHERE employeeid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			c.firstName = rs.getString("firstName");
			c.lastName = rs.getString("lastName");
			c.address = rs.getString("address");
			c.userId = rs.getString("userid");
			c.password = rs.getString("password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static Vehicle getVehicle(String vin) {
		log.trace("DealershipSystemWithSql.getVehicles(String)");
		Vehicle v = new Vehicle();
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"SELECT * FROM vehicles_proj_0 " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, vin);
			ResultSet rs = ps.executeQuery();
			v.make = rs.getString("make");
			v.model = rs.getString("model");
			v.year = rs.getInt("year");
			v.highestOffer = rs.getDouble("highestOffer");
			v.mileage = rs.getDouble("mileage");
			//v.condition = rs.getString("condition");
			v.bid = rs.getDouble("bid");
			v.vin = rs.getString("vin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	public static ArrayList<Vehicle> getVehicles() {
		log.trace("DealershipSystemWithSql.getVehicles()");
		Vehicle v = new Vehicle();
		ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query = "SELECT * FROM vehicles_proj_0";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				v.make = rs.getString("vin");
				v.model = rs.getString("model");
				v.year = rs.getInt("year");
				v.highestOffer = rs.getDouble("highestOffer");
				v.mileage = rs.getDouble("mileage");
				//v.condition = rs.getString("condition");
				v.bid = rs.getDouble("bid");
				v.vin = rs.getString("vin");
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
					"INSERT INTO vehicles_proj_0 (highestOffer,highestBidderOrOwner) " +
					"VALUES (?,?) " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1,v.highestOffer);
			ps.setString(2,v.highestBidderOrOwner);
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
					"INSERT INTO vehicles_proj_0 " +
					"(principle,highestOffer,highestBidderOrOwner,monthlyPayment) " +
					"VALUES (?,?,?,?,?) " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1,v.highestOffer); //set new principle
			//ps.setDouble(2,null); //kill highestOffer, it's not relevant
			//ps.setDouble(3,v.principle);
			ps.setDouble(2,v.highestOffer);
			ps.setString(3,v.highestBidderOrOwner);
			ps.setDouble(4,v.monthlyPayment);
			ps.setBoolean(5,true);
			ps.setString(6,v.vin);
			ps.execute();
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

	public static Double calculatePayments(Double principle, Integer paymentDuration) {
		log.trace("calculatePayments(Double, Integer)");
		double p = principle;
		double d = paymentDuration;
		double totalInterest = d*DealershipSystem.INTEREST;
		double totalWithInterest = p*(1+totalInterest);
		return totalWithInterest/d;
	}
}
