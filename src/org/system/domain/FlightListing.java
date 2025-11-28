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
	
	public FlightListing(String aUUID, String aDestination, LocalDate aDate, double aPrice, String aAirline) {
		super(aUUID, aPrice);
		this.destination = aDestination;
		this.date = aDate;
		this.airline = aAirline;
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
		if (!(criteria instanceof FlightSearchCriteria) || !isAvailable) {
			return false;
		}
		FlightSearchCriteria fsc = (FlightSearchCriteria)criteria;
		if (fsc.getDestination() != null && !destination.equals(fsc.getDestination())) {
			return false;
		}
		if (fsc.getDate() != null && !date.equals(fsc.getDate())) {
			return false;
		}
		if (fsc.getPriceRange() != null && !fsc.getPriceRange().inRange(price)) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "Flight ID: " + UUID + "\n\tAirline: " + airline + "\n\tDestination: " + destination + "\n\tPrice: " + price + "\n\tAvailable (Y/N): " + (isAvailable ? "Y" : "N"); 
	}
}
