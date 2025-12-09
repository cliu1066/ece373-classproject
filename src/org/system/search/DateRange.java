package org.system.search;

import java.time.LocalDate;

/*
 * Class: DateRange
 * The DateRange class represents a range of LocalDate objects used for searching listings.
 */
public class DateRange {
	private LocalDate start;
	private LocalDate end;
	
	public DateRange() {
		this.start = null;
		this.end = null;
	}
	
	public LocalDate getStart() {
		return start;
	}
	
	public LocalDate getEnd() {
		return end;
	}
	
	public void setStart(LocalDate aStart) {
		start = aStart;
	}
	
	public void setEnd(LocalDate aEnd) {
		end = aEnd;
	}
	
	/*
	 * inRange(aDate) - Takes in a date and checks if in current date range
	 * @param aDate - LocalDate object to check if in range
	 * @return boolean true if in range
	 */
	public boolean inRange(LocalDate aDate) {
		return (start == null || !aDate.isBefore(start)) && 
				(end == null || !aDate.isAfter(end));
	}
	
	public String toString() {
		if (start == null && end == null) {
			return "No date range set";
		}
		if (start == null) {
			return "Before " + end;
		}
		if (end == null) {
			return "After " + start;
		}
		return start + " to " + end;
	}
}
