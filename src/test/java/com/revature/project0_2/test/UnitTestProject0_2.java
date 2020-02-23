package com.revature.project0_2.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.revature.project0_2.core.*;
import com.revature.project0_2.core.DealershipSystemWithSql;

public class UnitTestProject0_2 {
	private static Logger log = Logger.getRootLogger();

	// Use these objects throughout unit tests
	Employee emp = new Employee();
	Customer cus = new Customer();
	Vehicle veh = new Vehicle();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// Classes with good inputs to be modified per test
		// These values will reset before each test.
		emp.firstName = "TEST_USER_FNAME";
		emp.lastName = "TEST_USER_LNAME";
		emp.address = "TEST_USER_ADDRESS";
		emp.email = "TEST_USER_@email.com";
		emp.userId = "TEST_USERID";
		emp.password = "TEST_USERPASSWORD";
		
		cus.firstName = "TEST_CUST_FNAME";
		cus.lastName = "TEST_CUST_LNAME";
		cus.address = "TEST_CUST_ADDRESS";
		cus.email = "TEST_CUST_@email.com";
		cus.userId = "TEST_CUSTID";
		cus.password = "TEST_CUSTPASSWORD";
		cus.creditCard = "9999" + "9999" + "9999" + "9999";
		
		veh.make = "TEST_MAKE";
		veh.model = "TEST_MODEL";
		veh.year = 9999;
		veh.mileage = 9999.9;
		veh.vin = "TEST_VIN9999";
		veh.bid = 9999.99;
		veh.highestOffer = 100.0;
		veh.highestBidderOrOwner = null;
		veh.monthlyPayment = 0.0;
		veh.principle = 0.0; // offer that was accepted
		veh.paymentDuration = 60; // in months
		veh.pended = false;
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * DealershipSystemWithSql - Employees SQL Operations
	 */
	@Test
	public void createEmployee_getEmployeeDuplicate() {
		String[] s = new String[6];
		String[] sReturned = new String[6];
		DealershipSystemWithSql.createEmployee(emp);
		DealershipSystemWithSql.createEmployee(emp);
		Employee empReturned = DealershipSystemWithSql.getEmployee(emp.userId);
		
		s[0] = emp.firstName;
		s[1] = emp.lastName;
		s[2] = emp.address;
		s[3] = emp.email;
		s[4] = emp.userId;
		s[5] = emp.password;

		sReturned[0] = empReturned.firstName;
		sReturned[1] = empReturned.lastName;
		sReturned[2] = empReturned.address;
		sReturned[3] = empReturned.email;
		sReturned[4] = empReturned.userId;
		sReturned[5] = empReturned.password;
		assertArrayEquals(s,sReturned);
	}

	@Test
	public void testRecordDoesExistsEmployee() {
		DealershipSystemWithSql.createEmployee(emp);
		assertEquals(true, DealershipSystemWithSql.recordExists(emp.userId, 'E'));
	}

	@Test
	public void createEmployee_getEmployee() {
		DealershipSystemWithSql.removeEmployee(emp);
		DealershipSystemWithSql.createEmployee(emp);
		Employee empReturned = DealershipSystemWithSql.getEmployee(emp.userId);
		String[] s = new String[6];
		String[] sReturned = new String[6];

		s[0] = emp.firstName;
		s[1] = emp.lastName;
		s[2] = emp.address;
		s[3] = emp.email;
		s[4] = emp.userId;
		s[5] = emp.password;

		sReturned[0] = empReturned.firstName;
		sReturned[1] = empReturned.lastName;
		sReturned[2] = empReturned.address;
		sReturned[3] = empReturned.email;
		sReturned[4] = empReturned.userId;
		sReturned[5] = empReturned.password;

		assertArrayEquals(s,sReturned);
	}

	@Test
	public void testRecordDoesNotExistsEmployee() {
		DealershipSystemWithSql.removeEmployee(emp);
		assertEquals(false, DealershipSystemWithSql.recordExists(emp.userId, 'E'));
	}
	
	/*
	 * DealershipSystemWithSql - Customers SQL Operations
	 */
	@Test
	public void createCustomer_getCustomerDuplicate() {
		String[] s = new String[7];
		String[] sReturned = new String[7];
		DealershipSystemWithSql.createCustomer(cus);
		DealershipSystemWithSql.createCustomer(cus);
		Customer cusReturned = DealershipSystemWithSql.getCustomer(cus.userId);
		
		s[0] = cus.firstName;
		s[1] = cus.lastName;
		s[2] = cus.address;
		s[3] = cus.email;
		s[4] = cus.userId;
		s[5] = cus.password;
		s[6] = cus.creditCard;

		sReturned[0] = cusReturned.firstName;
		sReturned[1] = cusReturned.lastName;
		sReturned[2] = cusReturned.address;
		sReturned[3] = cusReturned.email;
		sReturned[4] = cusReturned.userId;
		sReturned[5] = cusReturned.password;
		sReturned[6] = cusReturned.creditCard;
		assertArrayEquals(s,sReturned);
	}

	@Test
	public void testRecordDoesExistsCustomer() {
		DealershipSystemWithSql.createCustomer(cus);
		assertEquals(true, DealershipSystemWithSql.recordExists(cus.userId, 'C'));
	}

	@Test
	public void createCustomer_getCustomer() {
		DealershipSystemWithSql.removeCustomer(cus);
		DealershipSystemWithSql.createCustomer(cus);
		Customer cusReturned = DealershipSystemWithSql.getCustomer(cus.userId);
		String[] s = new String[6];
		String[] sReturned = new String[6];

		s[0] = cus.firstName;
		s[1] = cus.lastName;
		s[2] = cus.address;
		s[3] = cus.email;
		s[4] = cus.userId;
		s[5] = cus.password;

		sReturned[0] = cusReturned.firstName;
		sReturned[1] = cusReturned.lastName;
		sReturned[2] = cusReturned.address;
		sReturned[3] = cusReturned.email;
		sReturned[4] = cusReturned.userId;
		sReturned[5] = cusReturned.password;

		assertArrayEquals(s,sReturned);
	}

	@Test
	public void testRecordDoesNotExistsCustomer() {
		DealershipSystemWithSql.removeCustomer(cus);
		assertEquals(false, DealershipSystemWithSql.recordExists(cus.userId, 'C'));
	}
	
	/*
	 * DealershipSystemWithSql - Vehicles SQL Operations
	 */
	@Test
	public void createVehicle_getVehicleDuplicate() {
		String[] s = new String[12];
		String[] sReturned = new String[12];
		DealershipSystemWithSql.createVehicle(veh);
		DealershipSystemWithSql.createVehicle(veh);
		Vehicle vehReturned = DealershipSystemWithSql.getVehicle(veh.vin);
		
		s[0] = veh.make;
		s[1] = veh.model;
		s[2] = Integer.toString(veh.year);
		s[3] = Double.toString(veh.mileage);
		s[4] = veh.vin;
		s[5] = Double.toString(veh.bid);
		s[6] = Double.toString(veh.highestOffer);
		s[7] = veh.highestBidderOrOwner;
		s[8] = Double.toString(veh.monthlyPayment);
		s[9] = Double.toString(veh.principle);
		s[10] = Integer.toString(veh.paymentDuration);
		s[11] = Boolean.toString(veh.pended);

		sReturned[0] = vehReturned.make;
		sReturned[1] = vehReturned.model;
		sReturned[2] = Integer.toString(vehReturned.year);
		sReturned[3] = Double.toString(vehReturned.mileage);
		sReturned[4] = vehReturned.vin;
		sReturned[5] = Double.toString(vehReturned.bid);
		sReturned[6] = Double.toString(vehReturned.highestOffer);
		sReturned[7] = vehReturned.highestBidderOrOwner;
		sReturned[8] = Double.toString(vehReturned.monthlyPayment);
		sReturned[9] = Double.toString(vehReturned.principle);
		sReturned[10] = Integer.toString(vehReturned.paymentDuration);
		sReturned[11] = Boolean.toString(vehReturned.pended);
		assertArrayEquals(s,sReturned);
	}

	@Test
	public void testRecordDoesExistsVehicle() {
		DealershipSystemWithSql.createVehicle(veh);
		assertEquals(true, DealershipSystemWithSql.recordExists(veh.vin, 'V'));
	}

	@Test
	public void createVehicle_getVehicle() {
		DealershipSystemWithSql.removeVehicle(veh);
		DealershipSystemWithSql.createVehicle(veh);
		Vehicle vehReturned = DealershipSystemWithSql.getVehicle(veh.vin);
		String[] s = new String[12];
		String[] sReturned = new String[12];

		s[0] = veh.make;
		s[1] = veh.model;
		s[2] = Integer.toString(veh.year);
		s[3] = Double.toString(veh.mileage);
		s[4] = veh.vin;
		s[5] = Double.toString(veh.bid);
		s[6] = Double.toString(veh.highestOffer);
		s[7] = veh.highestBidderOrOwner;
		s[8] = Double.toString(veh.monthlyPayment);
		s[9] = Double.toString(veh.principle);
		s[10] = Integer.toString(veh.paymentDuration);
		s[11] = Boolean.toString(veh.pended);

		sReturned[0] = vehReturned.make;
		sReturned[1] = vehReturned.model;
		sReturned[2] = Integer.toString(vehReturned.year);
		sReturned[3] = Double.toString(vehReturned.mileage);
		sReturned[4] = vehReturned.vin;
		sReturned[5] = Double.toString(vehReturned.bid);
		sReturned[6] = Double.toString(vehReturned.highestOffer);
		sReturned[7] = vehReturned.highestBidderOrOwner;
		sReturned[8] = Double.toString(vehReturned.monthlyPayment);
		sReturned[9] = Double.toString(vehReturned.principle);
		sReturned[10] = Integer.toString(vehReturned.paymentDuration);
		sReturned[11] = Boolean.toString(vehReturned.pended);

		assertArrayEquals(s,sReturned);
	}

	@Test
	public void testRecordDoesNotExistsVehicle() {
		DealershipSystemWithSql.removeVehicle(veh);
		assertEquals(false, DealershipSystemWithSql.recordExists(veh.vin, 'V'));
	}
}
