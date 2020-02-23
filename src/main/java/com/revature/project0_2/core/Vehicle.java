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
	public Integer year;
	public Double mileage;
	//public String condition; // make enum later
	//public transient ArrayList<String> bid;
	//public transient ArrayList<String >offer;
	//public transient String highestBid;
	//public transient String lowestOffer;
	public Double bid;
	public Double highestOffer;
	public String vin;
	public String highestBidderOrOwner;
	public Double monthlyPayment;
	public Double principle; // offer that was accepted
	public Integer paymentDuration; // in months
	public Boolean pended;
}
