package org.system.domain;

import org.system.people.Customer;

public class HotelBooking extends Booking {
	private String hotelID;
	private int nights;
	
	public HotelBooking() {
		super();
		this.nights = 0;
	}
	
	public HotelBooking(String aBookingID, double aTotal, String aHotelID, int aNights, Customer aCustomer) {
		super(aBookingID, aTotal, aCustomer);
		this.nights = aNights;
		this.hotelID = aHotelID;
	}
	
	public String getHotelID() {
		return hotelID;
	}
	
	public void setHotelID(String aHotelID) {
		this.hotelID = aHotelID;
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
