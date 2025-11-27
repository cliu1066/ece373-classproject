package org.system.search;

public class HotelSearchCriteria extends SearchCriteria {
	private String location;
	private Integer rating;
	
	public HotelSearchCriteria() {
		super();
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String aLocation) {
		this.location = aLocation;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer aRating) {
		this.rating = aRating;
	}
	
	public String toString() {
		return "Search Criteria:\n\t" + 
				"Location: " + (location != null ? location : "N/A") +
				"Rating: " + (rating != null ? rating : "N/A") + " Price: " + priceRange;
	}
}
