package org.system.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.system.search.SearchCriteria;
import org.system.search.FlightSearchCriteria;

/*
 * Class: FlightListing
 * The FlightListing class is a child of the abstract Listing class. It contains more
 * methods and fields specific to flight listings.
 */
public class FlightListing extends Listing {
	private String destination;
	private LocalDate date;
	private String airline;
	
	public FlightListing() {
		super();
	}
	
	public FlightListing(String aUUID, String aDestination, LocalDate aDate, double aPrice,
						String aAirline) {
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
	
	/*
	 * matches(criteria) - Determines if current object matches the search criteria
	 * @param criteria - SearchCriteria object with fields for search/match
	 * @return true if criteria matches current fields, false if not
	 */
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
		return "Flight ID: " + UUID + "\n\tAirline: " + airline + "\n\tDestination: " +
				destination + "\n\tPrice: " + price + "\n\tAvailable (Y/N): " +
				(isAvailable ? "Y" : "N"); 
	}
	
	public static ArrayList<FlightListing> parseFlightData(String filename) {
		File fileIn = new File(filename);
		Scanner scanner = null;
		ArrayList<FlightListing> flights = new ArrayList<FlightListing>();
		
		try {
			scanner = new Scanner(fileIn);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] splitLine = line.split(",");
				
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return flights;
	}
	
}
