package org.system.search;

import java.time.LocalDate;

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
