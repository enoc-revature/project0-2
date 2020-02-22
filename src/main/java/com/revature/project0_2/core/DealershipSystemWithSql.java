package com.revature.project0_2.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.revature.jdbc.SqlDao;


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
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO employee " +
					"(firstName,lastName,address,id,password) " +
					"VALUES(?,?,?,?,?)");
			ps.setString(1, e.firstName);
			ps.setString(2, e.lastName);
			ps.setString(3, e.address);
			ps.setString(4, e.id);
			ps.setString(5, e.password);
			ps.executeUpdate();
		} catch (SQLException err) {
			err.printStackTrace();
		}

	}

	public static void createCustomer(Customer c) {
		log.trace("createCustomer(Customer)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO customers " +
					"(firstName,lastName,address,id,password) " +
					"VALUES(?,?,?,?,?)");
			ps.setString(1, c.firstName);
			ps.setString(2, c.lastName);
			ps.setString(3, c.address);
			ps.setString(4, c.id);
			ps.setString(5, c.password);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createVehicle(Vehicle v) {
		log.trace("createVehicle(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO vehicles " +
					"(make,model,year,mileage,mileage,condition,vin,bid) " +
					"VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, v.make);
			ps.setString(2, v.model);
			ps.setString(3, v.year);
			ps.setString(4, v.mileage);
			ps.setString(5, v.condition);
			ps.setString(6, v.vin);
			ps.setString(7, v.bid);
			ps.executeUpdate();
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
	
	// READ
	public static Employee getEmployee(String id) {
		log.debug("DealershipSystemWithSql.getEmployer(String id)");
		Employee e = new Employee();
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"SELECT * FROM employees " +
					"WHERE employeeid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			e.firstName = rs.getString("firstName");
			e.lastName = rs.getString("lastName");
			e.address = rs.getString("address");
			e.id = rs.getString("employeeid");
			e.password = rs.getString("password");
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return e;
	}

	public static Customer getCustomer(String id) {
		log.debug("DealershipSystemWithSql.getCustomer(String id)");
		Customer c = new Customer();
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			String query =
					"SELECT * FROM employees " +
					"WHERE employeeid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			c.firstName = rs.getString("firstName");
			c.lastName = rs.getString("lastName");
			c.address = rs.getString("address");
			c.id = rs.getString("customerid");
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
					"SELECT * FROM vehicles " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, vin);
			ResultSet rs = ps.executeQuery();
			v.make = rs.getString("make");
			v.model = rs.getString("model");
			v.year = rs.getString("year");
			v.highestOffer = rs.getString("highestOffer");
			v.mileage = rs.getString("mileage");
			v.condition = rs.getString("condition");
			v.bid = rs.getString("bid");
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
			String query = "SELECT * FROM vehicles";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				v.make = rs.getString("vin");
				v.model = rs.getString("model");
				v.year = rs.getString("year");
				v.highestOffer = rs.getString("highestOffer");
				v.mileage = rs.getString("mileage");
				v.condition = rs.getString("condition");
				v.bid = rs.getString("bid");
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
					"INSERT INTO vehicles (highestOffer,highestBidderOrOwner) " +
					"VALUES (?,?) " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,v.highestOffer);
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
					"INSERT INTO vehicles " +
					"(price,highestOffer,highestBidderOrOwner,monthlyPayment) " +
					"VALUES (?,?,?,?,?) " +
					"WHERE vin = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,v.highestOffer); //set new principle
			ps.setString(2,null); //kill highestOffer, it's not relevant
			ps.setString(3,v.principle);
			ps.setString(4,v.principle);
			ps.setString(5,v.monthlyPayment);
			ps.setString(6,v.highestBidderOrOwner);
			ps.setString(7,v.vin);
			ps.setBoolean(8,true);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DELETE
	public static void removeVehicle(Vehicle v) {
		log.trace("removeVehicle(Vehicle)");
		try (Connection conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2])) {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM vehicle WHERE vin=?");
			ps.setString(1, v.vin);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
