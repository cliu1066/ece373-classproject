package org.system.search;

public class HotelSearchCriteria extends SearchCriteria {
	private String location;
	private Double rating;
	
	public HotelSearchCriteria() {
		super();
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String aLocation) {
		this.location = aLocation;
	}
	
	public Double getRating() {
		return rating;
	}
	
	public void setRating(Double aRating) {
		this.rating = aRating;
	}
	
	public String toString() {
		return "Search Criteria:\n\t" + 
				"Location: " + (location != null ? location : "N/A") +
				"Rating: " + (rating != null ? rating : "N/A") + " Price: " + priceRange;
	}
}
