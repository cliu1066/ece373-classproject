package org.system.domain;

import org.system.search.SearchCriteria;

/*
 * Class: Listing
 * The Listing class is an abstract class for flight/hotel listings. This class contains
 * constructors and getters/setters for the fields.
 */
public abstract class Listing {
	protected String UUID;
	protected double price;
	protected boolean isAvailable;
	
	public Listing() {
		this.isAvailable = true;
	}
	
	public Listing(String aUUID, double aPrice) {
		this.UUID = aUUID;
		this.price = aPrice;
		this.isAvailable = true;
	}
	
	public String getUUID() {
		return UUID;
	}
	
	public void setUUID(String aUUID) {
		this.UUID = aUUID;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double aPrice) {
		this.price = aPrice;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void setAvailable(boolean available) {
		this.isAvailable = available;
	}
	
	public abstract boolean matches(SearchCriteria criteria);
}
