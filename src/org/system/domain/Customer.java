package org.system.domain;

import org.system.search.FlightSearchCriteria;
import org.system.search.HotelSearchCriteria;
import org.system.payment.Card;
import org.system.payment.Payment;
import org.system.payment.PaymentStatus;

import java.util.*;

public class Customer extends Person {
	private String email;
	private List<Booking> bookingHistory;
	
	public Customer() {
		super();
		this.email = "";
		this.bookingHistory = new ArrayList<>();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String aEmail) {
		if (aEmail != null && aEmail.contains("@")) {
			this.email = aEmail;
		}
	}
	
	public List<FlightListing> searchFlights(FlightSearchCriteria criteria, List<FlightListing> allFlights) {
		return allFlights;
	}
	
	public List<HotelListing> searchHotels(HotelSearchCriteria criteria, List<HotelListing> allHotels) {
		return allHotels;
	}
	
	public FlightBooking confirmFlightBooking(FlightListing listing, String passenger) {
		FlightBooking booking = new FlightBooking();
		return booking;
	}
	
	public HotelBooking confirmHotelBooking(HotelListing listing, String guest, int nights) {
		HotelBooking booking = new HotelBooking();
		return booking;
	}
	
	public Payment pay(Booking booking, Card card) {
		Payment payment = new Payment();
		return payment;
	}
	
	public List<Booking> getBookingHistory() {
		return bookingHistory;
	}
	
	public void addBooking(Booking booking) {
		bookingHistory.add(booking);
	}
	
	public String toString() {
		return "Customer ID: " + UUID + " Name: " + name + "\n\tEmail: " + email + "\n\tNumber of Bookings: " + bookingHistory.size();
	}
}
