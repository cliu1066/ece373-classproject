package org.system.search;

/*
 * Class: SearchCriteria
 * The abstract class SearchCriteria contains the information used when wanting to search
 * and filter through listings (hotel or flight).
 */
public abstract class SearchCriteria {
	protected PriceRange priceRange;
	
	public SearchCriteria() {
		this.priceRange = new PriceRange();
	}
	
	public PriceRange getPriceRange() {
		return priceRange;
	}
	
	public void setPriceRange(PriceRange aPriceRange) {
		this.priceRange = aPriceRange;
	}
}
