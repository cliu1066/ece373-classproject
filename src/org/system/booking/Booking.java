package org.system.booking;

import java.time.LocalDateTime;

import org.system.people.Customer;

/*
 * Class: Booking
 * The Booking class is an abstract class for flight/hotel booking child classes. This class
 * mostly contains getters/setters and methods to mark/change booking statuses.
 */
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
	
	/*
	 * cancel() - Set booking status to CANCELED if current status is CREATED or PENDING_PAYMENT.
	 * @return true if status set to CANCELED, false otherwise
	 */
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
	
	/*
	 * setPendingPayment() - Set payment status to PENDING_PAYMENT if current status is CREATED.
	 */
	public void setPendingPayment() {
		if (status == BookingStatus.CREATED) {
			status = BookingStatus.PENDING_PAYMENT;
		}
	}
	
	public String toString() {
		return "Booking ID: " + UUID + " Status: " + status + "\n\tTotal: " +
				total + " Created: " + createdDateTime;
	}
}
