package org.system.domain;

import java.time.LocalDateTime;

public abstract class Booking {
	protected String UUID;
	protected double total;
	protected BookingStatus status;
	protected LocalDateTime createdDateTime;
	protected Customer customer;
	
	public Booking() {
		this.UUID = "";
		this.total = 0d;
		this.status = BookingStatus.CREATED;
		this.createdDateTime = LocalDateTime.now();
		this.customer = null;
	}
	
	public String getUUID() {
		return UUID;
	}
	
	public void setUUID(String aUUID) {
		this.UUID = aUUID;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double aTotal) {
		this.total = aTotal;
	}
	
	public BookingStatus getStatus() {
		return status;
	}
	
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer aCustomer) {
		this.customer = aCustomer;
	}
	
	// Methods
	public boolean cancel() {
		return true;
	}
	
	public void markPaid(String paymentID) {
		
	}
	
	public void setBookingStatus() {
		
	}
	
	public String toString() {
		return "Booking ID: " + UUID + " Status: " + status + "\n\tTotal: " + total + " Created: " + createdDateTime;
	}
}
