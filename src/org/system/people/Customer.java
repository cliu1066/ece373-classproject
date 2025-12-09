package org.system.people;

import org.system.search.*;
import org.system.booking.*;
import org.system.listing.FlightListing;
import org.system.listing.HotelListing;
import org.system.payment.*;

import java.util.List;
import java.util.ArrayList;

/*
 * Class: Customer
 * Child class of Person. Implements travel booking actions from a customer's perspective.
 */
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
	
	/*
	 * searchFlights(criteria, flights) - Returns a list of flights matching
	 * the given search criteria
	 * @param criteria - FlightSearchCriteria object to match
	 * @param flights - list of FlightListing objects to search through
	 * @return list of FlightListing objects that match the criteria
	 */
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
	
	/*
	 * searchHotels(criteria, hotels) - Returns a list of hotels matching
	 * the given search criteria
	 * @param criteria - HotelSearchCriteria object to match
	 * @param hotels - list of HotelListing objects to search through
	 * @return list of HotelListing objects that match the criteria
	 */
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
	
	/*
	 * bookFlight(listing, bookingSystem) - Creates a new flight booking
	 * @param listing - FlightListing object to book
	 * @param bookingSystem - BookingSystem to register the booking with
	 * @return FlightBooking object with pending payment status
	 */
	public FlightBooking bookFlight(FlightListing listing, BookingSystem bookingSystem) {
		String bookingID = "FB" + System.currentTimeMillis();
		FlightBooking booking = new FlightBooking(bookingID, listing.getPrice(), listing.getUUID(), this);
		booking.setPendingPayment();
		bookingHistory.add(booking);
		bookingSystem.addBooking(booking);
		return booking;
	}
	
	/*
	 * bookHotel(listing, nights, bookingSystem) - Creates a new hotel booking
	 * @param listing - HotelListing object to book
	 * @param nights - integer number of nights to book
	 * @param bookingSystem - BookingSystem to register the booking with
	 * @return HotelBooking object with pending payment status
	 */
	public HotelBooking bookHotel(HotelListing listing, int nights, BookingSystem bookingSystem) {
		String bookingID = "HB" + System.currentTimeMillis();
		double total = listing.getPrice() * nights;
		HotelBooking booking = new HotelBooking(bookingID, total, listing.getUUID(), nights, this);
		booking.setPendingPayment();
		bookingHistory.add(booking);
		bookingSystem.addBooking(booking);
		return booking;
	}
	
	/*
	 * pay(booking, card) - Processes payment for a booking using the provided card
	 * @param booking - Booking object to pay for
	 * @param card - Card object to use for payment
	 */
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
	
	/*
	 * cancelBooking(bookingID) - Cancels a booking and removes it from history
	 * @param bookingID - String ID to match/search for a booking
	 * @return true if booking successfully cancelled and removed
	 */
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
