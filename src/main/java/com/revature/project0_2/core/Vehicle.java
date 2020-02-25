package com.revature.project0_2.core;

import java.io.Serializable;
import java.util.ArrayList;

// POJO
public class Vehicle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String make; //req
	public String model; //req
	public Integer year = 0;
	public Double mileage = 0.0;
	//public String condition; // make enum later
	//public transient ArrayList<String> bid;
	//public transient ArrayList<String >offer;
	//public transient String highestBid;
	//public transient String lowestOffer;
	public Double bid; //req
	public Double highestOffer=0.0;
	public String vin; //req
	public String highestBidderOrOwner;
	public Double monthlyPayment = 0.0;
	public Double principle = 0.0; // offer that was accepted
	public Integer paymentDuration = 60; // in months
	public Boolean pended = false;
}
