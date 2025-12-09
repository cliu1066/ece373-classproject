package org.system.listing;

import org.system.search.SearchCriteria;

import java.io.Serializable; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.system.search.HotelSearchCriteria;

/*
 * Class: HotelListing
 * The HotelListing class is a child of the abstract Listing class. It contains more
 * methods and fields specific to hotel listings.
 */
public class HotelListing extends Listing implements Serializable {
	private String location;
	private double rating;
	private String name;
	
	public HotelListing() {
		super();
	}
	
	public HotelListing(String aUUID, String aLocation, double aRating, 
			double aPrice, String aName) {
		super(aUUID, aPrice);
		this.location = aLocation;
		this.rating = aRating;
		this.name = aName;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String aLocation) {
		this.location = aLocation;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double aRating) {
		this.rating = aRating;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String aName) {
		this.name = aName;
	}
	
	/*
	 * matches(criteria) - Determines if current object matches the search criteria
	 * @param criteria - SearchCriteria object with fields for search/match
	 * @return true if criteria matches current fields, false if not
	 */
	public boolean matches(SearchCriteria criteria) {
		if (!(criteria instanceof HotelSearchCriteria) || !isAvailable) {
			return false;
		}
		HotelSearchCriteria hsc = (HotelSearchCriteria)criteria;
		if (hsc.getLocation() != null && !location.equals(hsc.getLocation())) {
			return false;
		}
		if (hsc.getRating() != null && rating < hsc.getRating()) {
			return false;
		}
		if (hsc.getPriceRange() != null && !hsc.getPriceRange().inRange(price)) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "Hotel ID: " + UUID + " Name: " + name + " Location: " + location +
				" Rating: " + rating + " Price/Night: " + price + 
				" Available (Y/N): " + (isAvailable ? "Y" : "N");
	}
	
	public static ArrayList<HotelListing> parseHotelData(String filename) {
		File fileIn = new File(filename);
		Scanner scanner = null;
		ArrayList<HotelListing> hotels = new ArrayList<HotelListing>();
		
		try {
			scanner = new Scanner(fileIn);
			scanner.nextLine();
			int i = 5;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] splitLine = line.split(",");
				
				String uuid = "HL" + ((i < 10) ? "0" : "0") + String.valueOf(i);
				String location = splitLine[0];
				String name = splitLine[1];
				double rating = Double.valueOf(splitLine[2]);
				double price = Double.valueOf(splitLine[3]);
				
				HotelListing hl = new HotelListing(uuid, location, rating, price, name);
				hotels.add(hl);
				i++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return hotels;
	}
	
}
