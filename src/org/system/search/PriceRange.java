package org.system.search;

/*
 * Class: PriceRange
 * The PriceRange class represents a range of prices used for searching listings.
 */
public class PriceRange {
	private Double min;
	private Double max;
	
	public PriceRange() {
		this.min = null;
		this.max = null;
	}
	
	public PriceRange(Double aMin, Double aMax) {
		this.min = aMin;
		this.max = aMax;
	}
	
	public Double getMin() {
		return min;
	}
	
	public Double getMax() {
		return max;
	}
	
	public void setMin(Double aMin) {
		this.min = aMin;
	}
	
	public void setMax(Double aMax) {
		this.max = aMax;
	}
	
	/*
	 * inRange(aPrice) - Checks if a price is within the price range
	 * @param aPrice - double price value to check
	 * @return boolean true if price in range
	 */
	public boolean inRange(double aPrice) {
		return (min == null || aPrice >= min) && (max == null || aPrice <= max);
	}
	
	public String toString() {
		if (min == null && max == null) {
			return "No price range set";
		}
		if (min == null) {
			return "Max: $" + max;
		}
		if (max == null) {
			return "Min: $" + min;
		}
		return "$" + min + " to $" + max;
	}
}
