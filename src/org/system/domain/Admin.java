package org.system.domain;

import java.util.*;

public class Admin extends Person {
	public Admin() {
		super();
	}
	
	public boolean addFlight(FlightListing listing, List<FlightListing> flights) {
		return true;
	}
	
	public boolean updateFlight(String flightID, FlightListing updatedListing, List<FlightListing> flights) {
		return true;
	}
	
	public boolean removeFlight(String flightID, List<FlightListing> flights) {
		return true;
	}
	
	public boolean addHotel(HotelListing listing, List<HotelListing> hotels) {
		return true;
	}
	
	public boolean updateHotel(String hotelID, HotelListing updatedListing, List<HotelListing> hotels) {
		return true;
	}
	
	public boolean removeHotel(String hotelID, List<HotelListing> hotels) {
		return true;
	}
	
	public String toString() {
		return "Admin ID: " + UUID + " Name: " + name;
	}
}
