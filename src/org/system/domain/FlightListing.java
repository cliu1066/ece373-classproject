package org.system.domain;

import java.time.LocalDate;
import org.system.search.SearchCriteria;
import org.system.search.FlightSearchCriteria;

public class FlightListing extends Listing {
	private String destination;
	private LocalDate date;
	private String airline;
	
	public FlightListing() {
		super();
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String aDestination) {
		this.destination = aDestination;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate aDate) {
		this.date = aDate;
	}
	
	public String getAirline() {
		return airline;
	}
	
	public void setAirline(String aAirline) {
		this.airline = aAirline;
	}
	
	public boolean matches(SearchCriteria criteria) {
		return true;
	}
	
	public String toString() {
		return "Flight ID: " + UUID + " Airline: " + airline + " Destination: " + destination + "\n\tPrice: " + price + "Available (Y/N): " + (isAvailable ? "Y" : "N"); 
	}
}
