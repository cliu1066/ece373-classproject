package org.system.domain;

import org.system.people.*;

import java.util.List;
import java.util.ArrayList;

/*
 * Class: BookingSystem
 * The BookingSystem class is the top level class connecting the whole travel booking
 * system. It contains methods to access the different fields/objects of the system
 * and print them.
 */
public class BookingSystem {
	private List<FlightListing> flights;
	private List<HotelListing> hotels;
	private List<Booking> bookings;
	private List<Customer> customers;
	private List<Admin> admins;
	
	public BookingSystem() {
		flights = new ArrayList<>();
		hotels = new ArrayList<>();
		bookings = new ArrayList<>();
		customers = new ArrayList<>();
		admins = new ArrayList<>();
	}
	
	public List<FlightListing> getFlights() {
		return flights;
	}
	
	public List<HotelListing> getHotels() {
		return hotels;
	}
	
	public List<Booking> getBookings() {
		return bookings;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public List<Admin> getAdmins() {
		return admins;
	}
	
	public void printFlights() {
		for (FlightListing flight : flights) {
			System.out.println(flight);
		}
	}
	
	public void printHotels() {
		for (HotelListing hotel : hotels) {
			System.out.println(hotel);
		}
	}
	
	public void printBookings() {
		for (Booking booking : bookings) {
			System.out.println(booking);
		}
	}
	
	public void printCustomers() {
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}
	
	public void printAdmins() {
		for (Admin admin : admins) {
			System.out.println(admin);
		}
	}
	
	/*
	 * getFlightByID(aUUID) - Get FlightListing object from a UUID
	 * @param aUUID - String ID to search flight listings
	 * @return FlightListing object matching aUUID or null if no match
	 */
	public FlightListing getFlightByID(String aUUID) {
		for (FlightListing flight : flights) {
			if (flight.getUUID().equals(aUUID)) {
				return flight;
			}
		}
		return null;
	}
	
	/*
	 * getHotelByID(aUUID) - Get HotelListing object from a UUID
	 * @param aUUID - String ID to search hotel listings
	 * @return HotelListing object matching aUUID or null if no match
	 */
	public HotelListing getHotelByID(String aUUID) {
		for (HotelListing hotel : hotels) {
			if (hotel.getUUID().equals(aUUID)) {
				return hotel;
			}
		}
		return null;
	}
	
	public boolean addAdmin(Admin aAdmin) {
		return admins.add(aAdmin);
	}
	
	public boolean removeAdmin(Admin aAdmin) {
		return admins.remove(aAdmin);
	}
	
	public boolean addCustomer(Customer aCustomer) {
		return customers.add(aCustomer);
	}
	
	public boolean removeCustomer(Customer aCustomer) {
		return customers.remove(aCustomer);
	}

	public boolean addBooking(Booking aBooking) {
		return bookings.add(aBooking);
	}

	// Additional methods to add/remove flights, hotels, bookings can be added similarly
	// Added by Barack M. A.
	public boolean addFlight(FlightListing flight) {
		return flights.add(flight);
	}

	public boolean removeFlight(FlightListing flight) {
		return flights.remove(flight);
	}

	public boolean addHotel(HotelListing hotel) {
		return hotels.add(hotel);
	}

	public boolean removeHotel(HotelListing hotel) {
		return hotels.remove(hotel);
	}

	public Customer getCustomerByID(String aUUID) {
		for (Customer customer : customers) {
			if (customer.getUUID().equals(aUUID)) { 
				return customer;
			}
		}
		return null;
	}

	public Customer getCustomerByName(String name) {
		for (Customer customer : customers) {
			if (customer.getName().equalsIgnoreCase(name)) {  
				return customer;
			}
		}
		return null;
	}

	public Booking getBookingByID(String aUUID) {
		for (Booking booking : bookings) {
			if (booking.getUUID().equals(aUUID)) {
				return booking;
			}
		}
		return null;
	}

}
