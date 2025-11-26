package org.system.domain;

import org.system.search.SearchCriteria;
import org.system.search.HotelSearchCriteria;

public class HotelListing extends Listing {
	private String location;
	private int rating;
	private String name;
	
	public HotelListing() {
		super();
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String aLocation) {
		this.location = aLocation;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int aRating) {
		this.rating = aRating;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String aName) {
		this.name = aName;
	}
	
	public boolean matches(SearchCriteria criteria) {
		return true;
	}
	
	public String toString() {
		return "Hotel ID: " + UUID + " Name: " + name + "Location: " + location + "\n\tRating: " + rating + " Price/Night: " + price + " Available (Y/N): " + (isAvailable ? "Y" : "N");
	}
}
