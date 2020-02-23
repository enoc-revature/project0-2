package com.revature.project0_2.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.revature.project0_2.core.*;
import com.revature.project0_2.core.DealershipSystemWithSql;

public class UnitTestProject0_2 {
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
		emp.firstName = "John";
		emp.lastName = "Smith";
		emp.address = "123 Ave.";
		emp.email = "employee@email.com";
		emp.userId = "userid";
		emp.password = "password";
		
		cus.firstName = "John";
		cus.lastName = "Smith";
		cus.address = "123 Ave.";
		cus.email = "customer@email.com";
		cus.userId = "userid";
		cus.password = "password";
		cus.creditCard = 0;
		
		veh.make = "Toyota";
		veh.model = "Yaris";
		veh.year = 2007;
		veh.mileage = 100000.0;
		veh.vin = "JBC203840";
		veh.bid = 2000.00;
		veh.highestOffer = 0.0;
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
	 * DealershipSystemWithSql
	 */
	@Test
	public void createEmployee_getEmployeeDuplicate() {
		String[] s = new String[5];
		String[] sReturned = new String[5];
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
		sReturned[6] = empReturned.password;
		assertArrayEquals(s,sReturned);
	}

	@Test
	public void testRecordDoesExists() {
		assertEquals(DealershipSystemWithSql.recordExists(emp.userId, 'E'), true);
	}

	@Test
	public void createEmployee_getEmployee() {
		DealershipSystemWithSql.removeEmployee(emp);
		DealershipSystemWithSql.createEmployee(emp);
		Employee empReturned = DealershipSystemWithSql.getEmployee(emp.userId);
		String[] s = new String[6];
		String[] sReturned = new String[5];

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
		sReturned[6] = empReturned.password;

		assertArrayEquals(s,sReturned);
	}

	@Test
	public void testRecordDoesNotExists() {
		DealershipSystemWithSql.removeEmployee(emp);
		assertEquals(DealershipSystemWithSql.recordExists(emp.userId, 'E'), false);
	}
}
