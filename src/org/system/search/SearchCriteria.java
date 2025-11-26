package org.system.search;

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
