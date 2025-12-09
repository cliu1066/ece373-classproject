package org.system.people;

import java.io.Serializable;
import java.util.*;

import org.system.booking.*;
import org.system.listing.FlightListing;
import org.system.listing.HotelListing;

/*
 * Class: Admin
 * Child class of Person. Implements travel booking system admin related tasks.
 */
public class Admin extends Person implements Serializable {
	public Admin() {
		super();
	}
	
	public Admin(String aUUID, String aName) {
		super(aUUID, aName);
	}
	
	/*
	 * addFlight(listing, flights) - Adds a flight listing to a list of flights
	 * @param listing - FlightListing to be added
	 * @param flights - List<FlightListing> added to
	 * @return boolean true if added successfully
	 */
	public boolean addFlight(FlightListing listing, List<FlightListing> flights) {
		if (listing != null && !flights.contains(listing)) {
			return flights.add(listing);
		}
		return false;
	}
	
	/*
	 * updateFlight(listingID, updatedListing, flights) - Replaces the flight listing of the same listingID
	 * with the updatedListing
	 * @param listingID - String ID to match 
	 * @param updatedListing - new FlightListing object to replace the old one with
	 * @param flights - list of FlightListing objects to compare with
	 * @return boolean true if updated
	 */
	public boolean updateFlight(String listingID, FlightListing updatedListing, List<FlightListing> flights) {
		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getUUID().equals(listingID)) {
				flights.set(i, updatedListing);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * removeFlight(listingID, flights, bookings) - Removes the flight of same listingID from a list if
	 * the flight has not been paid for
	 * @param listingID - String ID to match
	 * @param flights - list of FlightListing objects to compare/search
	 * @param bookings - list of all Booking objects in the system
	 * @return boolean true if flight removed
	 */
	public boolean removeFlight(String listingID, List<FlightListing> flights, List<Booking> bookings) {
		for (Booking booking : bookings) {
			// check if booking is paid for (cannot remove)
			if (booking instanceof FlightBooking && booking.getStatus() == BookingStatus.PAID) {
				FlightBooking fb = (FlightBooking)booking;
				if (fb.getFlightID().equals(listingID)) {
					return false;
				}
			}
		}
		
		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getUUID().equals(listingID)) {
				flights.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * addHotel(listing, hotels) - Adds a hotel listing to a list of hotel
	 * @param listing - HotelListing to be added
	 * @param hotels - List<HotelListing> added to
	 * @return boolean true if added successfully
	 */
	public boolean addHotel(HotelListing listing, List<HotelListing> hotels) {
		if (listing != null && !hotels.contains(listing)) {
			return hotels.add(listing);
		}
		return false;
	}
	
	/*
	 * updateHotel(listingID, updatedListing, hotels) - Replaces the hotel listing of the same listingID
	 * with the updatedListing
	 * @param listingID - String ID to match 
	 * @param updatedListing - new HotelListing object to replace the old one with
	 * @param hotels - list of HotelListing objects to compare with
	 * @return boolean true if updated
	 */
	public boolean updateHotel(String listingID, HotelListing updatedListing, List<HotelListing> hotels) {
		for (int i = 0; i < hotels.size(); i++) {
			if (hotels.get(i).getUUID().equals(listingID)) {
				hotels.set(i, updatedListing);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * removeHotel(listingID, hotels, bookings) - Removes the hotel of same listingID from a list if
	 * the hotel has not been paid for
	 * @param listingID - String ID to match
	 * @param hotels - list of HotelListing objects to compare/search
	 * @param bookings - list of all Booking objects in the system
	 * @return boolean true if hotel removed
	 */
	public boolean removeHotel(String listingID, List<HotelListing> hotels, List<Booking> bookings) {
		for (Booking booking : bookings) {
			// check if booking is paid for (cannot remove)
			if (booking instanceof HotelBooking && booking.getStatus() == BookingStatus.PAID) {
				HotelBooking hb = (HotelBooking)booking;
				if (hb.getUUID().equals(listingID)) {
					return false;
				}
			}
		}
		
		for (int i = 0; i < hotels.size(); i++) {
			if (hotels.get(i).getUUID().equals(listingID)) {
				hotels.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return "Admin ID: " + UUID + " Name: " + name;
	}
}
