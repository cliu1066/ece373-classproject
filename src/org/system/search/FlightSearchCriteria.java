package org.system.search;

import java.time.LocalDate;
import java.io.Serializable;

/*
 * Class: FlightSearchCriteria
 * The FlightSearchCriteria class is a child of SearchCriteria with additional
 * flight searching fields (notably destination and date).
 */
public class FlightSearchCriteria extends SearchCriteria implements Serializable {
	private String departure;
	private String destination;
	private LocalDate date;
	
	public FlightSearchCriteria() {
		super();
	}
	
	public String getDeparture() {
		return departure;
	}
	
	public void setDeparture(String aDeparture) {
		departure = aDeparture;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String aDestination) {
		destination = aDestination;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate aDate) {
		this.date = aDate;
	}
	
	public String toString() {
		return "Search Criteria:\n\t" + 
				"Destination: " + (departure != null ? departure : "N/A") + " " +
				(destination != null ? destination : "N/A") + 
				" Date: " + (date != null ? date : "N/A") + " Price: " + priceRange;
	}
}
