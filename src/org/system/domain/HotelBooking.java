package org.system.domain;

public class HotelBooking extends Booking {
	private int nights;
	
	public HotelBooking() {
		super();
		this.nights = 0;
	}
	
	public int getNights() {
		return nights;
	}
	
	public void setNights(int aNights) {
		this.nights = aNights;
	}
	
	public String toString() {
		return "Hotel Booking ID: " + UUID + " Guest: " + customer.getName() + " Nights: " + nights;
	}
}
