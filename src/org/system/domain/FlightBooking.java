package org.system.domain;

public class FlightBooking extends Booking {
	public FlightBooking() {
		super();
	}
	
	public String toString() {
		return "Flight Booking ID: " + UUID + " Passenger: " + customer.getName();
	}
}
