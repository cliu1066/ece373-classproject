package org.system.people;

import org.system.search.FlightSearchCriteria;
import org.system.search.HotelSearchCriteria;
import org.system.domain.Booking;
import org.system.domain.FlightBooking;
import org.system.domain.FlightListing;
import org.system.domain.HotelBooking;
import org.system.domain.HotelListing;
import org.system.domain.BookingSystem;
import org.system.payment.Card;
import org.system.payment.Payment;
import org.system.payment.PaymentStatus;

import java.util.List;
import java.util.ArrayList;

public class Customer extends Person {
	private String email;
	private List<Booking> bookingHistory;
	
	public Customer() {
		super();
		this.bookingHistory = new ArrayList<>();
	}
	
	public Customer(String aUUID, String aName, String aEmail) {
		super(aUUID, aName);
		this.email = aEmail;
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
	
	public List<FlightListing> searchFlights(FlightSearchCriteria criteria, 
												List<FlightListing> flights) {
		List<FlightListing> matches = new ArrayList<>();
		for (FlightListing flight : flights) {
			if (flight.matches(criteria)) {
				matches.add(flight);
			}
		}
		return matches;
	}
	
	public List<HotelListing> searchHotels(HotelSearchCriteria criteria,
												List<HotelListing> hotels) {
		List<HotelListing> matches = new ArrayList<>();
		for (HotelListing hotel : hotels) {
			if (hotel.matches(criteria)) {
				matches.add(hotel);
			}
		}
		return matches;
	}
	
	public FlightBooking bookFlight(FlightListing listing, BookingSystem bookingSystem) {
		String bookingID = "FB" + System.currentTimeMillis();
		FlightBooking booking = new FlightBooking(bookingID, listing.getPrice(), listing.getUUID(), this);
		booking.setPendingPayment();
		bookingHistory.add(booking);
		bookingSystem.addBooking(booking);
		return booking;
	}
	
	public HotelBooking bookHotel(HotelListing listing, int nights, BookingSystem bookingSystem) {
		String bookingID = "HB" + System.currentTimeMillis();
		double total = listing.getPrice() * nights;
		HotelBooking booking = new HotelBooking(bookingID, total, listing.getUUID(), nights, this);
		booking.setPendingPayment();
		bookingHistory.add(booking);
		bookingSystem.addBooking(booking);
		return booking;
	}
	
	public Payment pay(Booking booking, Card card) {
		String paymentID = "P" + System.currentTimeMillis();
		Payment payment = new Payment(paymentID, booking.getUUID(), booking.getTotal());
		
		PaymentStatus result = payment.process(card);
		if (result == PaymentStatus.APPROVED) {
			booking.markPaid(paymentID);
		}
		else {
			System.out.println("Payment failed. Please try again.");
		}
		return payment;
	}
	
	public List<Booking> getBookingHistory() {
		return bookingHistory;
	}
	
	public void printBookingHistory() {
		for (Booking booking : bookingHistory) {
			System.out.println(booking);
		}
	}
	
	public void addBooking(Booking booking) {
		bookingHistory.add(booking);
	}
	
	public boolean cancelBooking(String bookingID) {
		for (Booking booking : bookingHistory) {
			if (booking.getUUID().equals(bookingID)) {
				if (booking.cancel()) {
					return bookingHistory.remove(booking);
				}
			}
		}
		return false;
	}
	
	public String toString() {
		return "Customer ID: " + UUID + " Name: " + name + "\n\tEmail: " + email + 
				"\n\tNumber of Bookings: " + bookingHistory.size();
	}
}
