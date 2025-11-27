package org.system.people;

import java.util.*;

import org.system.domain.Booking;
import org.system.domain.BookingStatus;
import org.system.domain.FlightBooking;
import org.system.domain.FlightListing;
import org.system.domain.HotelBooking;
import org.system.domain.HotelListing;

public class Admin extends Person {
	public Admin() {
		super();
	}
	
	public Admin(String aUUID, String aName) {
		super(aUUID, aName);
	}
	
	public boolean addFlight(FlightListing listing, List<FlightListing> flights) {
		if (listing != null && !flights.contains(listing)) {
			return flights.add(listing);
		}
		return false;
	}
	
	public boolean updateFlight(String listingID, FlightListing updatedListing, List<FlightListing> flights) {
		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getUUID().equals(listingID)) {
				flights.set(i, updatedListing);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeFlight(String listingID, List<FlightListing> flights, List<Booking> bookings) {
		for (Booking booking : bookings) {
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
	
	public boolean addHotel(HotelListing listing, List<HotelListing> hotels) {
		if (listing != null && !hotels.contains(listing)) {
			return hotels.add(listing);
		}
		return false;
	}
	
	public boolean updateHotel(String listingID, HotelListing updatedListing, List<HotelListing> hotels) {
		for (int i = 0; i < hotels.size(); i++) {
			if (hotels.get(i).getUUID().equals(listingID)) {
				hotels.set(i, updatedListing);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeHotel(String listingID, List<HotelListing> hotels, List<Booking> bookings) {
		for (Booking booking : bookings) {
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
