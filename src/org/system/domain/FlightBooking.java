package org.system.domain;

import org.system.people.Customer;

/*
 * Class: FlightBooking
 * The FlightBooking class is a child of the Booking parent class. It contains getters/setters
 * and a toString() method specific to booking flights.
 */
public class FlightBooking extends Booking {
	private String flightID;
	
	public FlightBooking() {
		super();
	}
	
	public FlightBooking(String aUUID, double aTotal, String aFlightID, Customer aCustomer) {
		super(aUUID, aTotal, aCustomer);
		this.flightID = aFlightID;
	}
	
	public String getFlightID() {
		return flightID;
	}
	
	public void setFlightID(String aFlightID) {
		this.flightID = aFlightID;
	}
	
	public String toString() {
		return "Flight Booking ID: " + UUID + " Passenger: " + customer.getName();
	}
}
