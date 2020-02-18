package com.revature.project0_2.core;

import java.io.Serializable;
import java.util.ArrayList;

// POJO
public class Vehicle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String make;
	public String model;
	public String year;
	public String mileage;
	public String condition; // make enum later
	//public transient ArrayList<String> bid;
	//public transient ArrayList<String >offer;
	//public transient String highestBid;
	//public transient String lowestOffer;
	public String bid = "";
	public String highestOffer = "";
	public String vin;
	public String highestBidderOrOwner = "Dealership";
	public String monthlyPayment;
	public String principle = ""; // offer that was accepted
	public String paymentDuration; // in months
	public boolean pended;
}
