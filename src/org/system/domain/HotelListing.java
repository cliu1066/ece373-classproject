package org.system.domain;

import org.system.search.SearchCriteria;
import org.system.search.HotelSearchCriteria;

public class HotelListing extends Listing {
	private String location;
	private double rating;
	private String name;
	
	public HotelListing() {
		super();
	}
	
	public HotelListing(String aUUID, String aLocation, double aRating, double aPrice, String aName) {
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
		return "Hotel ID: " + UUID + "\n\tName: " + name + "\n\tLocation: " + location + "\n\tRating: " + rating + "\n\tPrice/Night: " + price + "\n\tAvailable (Y/N): " + (isAvailable ? "Y" : "N");
	}
}
