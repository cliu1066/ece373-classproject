package org.system.domain;

import java.time.LocalDateTime;

import org.system.people.Customer;

public abstract class Booking {
	protected String UUID;
	protected double total;
	protected BookingStatus status;
	protected LocalDateTime createdDateTime;
	protected Customer customer;
	
	public Booking() {
		this.status = BookingStatus.CREATED;
		this.createdDateTime = LocalDateTime.now();
	}
	
	public Booking(String aUUID, double aTotal, Customer aCustomer) {
		this.UUID = aUUID;
		this.total = aTotal;
		this.status = BookingStatus.CREATED;
		this.createdDateTime = LocalDateTime.now();
		this.customer = aCustomer;
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
		if (status == BookingStatus.CREATED || status == BookingStatus.PENDING_PAYMENT) {
			status = BookingStatus.CANCELED;
			return true;
		}
		return false;
	}
	
	public void markPaid(String paymentID) {
		status = BookingStatus.PAID;
	}
	
	public void setPendingPayment() {
		if (status == BookingStatus.CREATED) {
			status = BookingStatus.PENDING_PAYMENT;
		}
	}
	
	public String toString() {
		return "Booking ID: " + UUID + " Status: " + status + "\n\tTotal: " + total + " Created: " + createdDateTime;
	}
}
