package org.system.payment;

import java.time.LocalDateTime;

public class Payment {
	private String UUID;
	private String bookingID;
	private double amount;
	private PaymentStatus status;
	private LocalDateTime timestamp;
	
	public Payment() {
		this.status = PaymentStatus.INITIATED;
		this.timestamp = LocalDateTime.now();
	}
	
	public Payment(String aUUID, String aBookingID, double aAmount) {
		this.UUID = aUUID;
		this.bookingID = aBookingID;
		this.amount = aAmount;
		this.status = PaymentStatus.INITIATED;
		this.timestamp = LocalDateTime.now();
	}
	
	public String getUUID() {
		return UUID;
	}
	
	public void setUUID(String aUUID) {
		this.UUID = aUUID;
	}
	
	public String getBookingID() {
		return bookingID;
	}
	
	public void setBookingID(String aBookingID) {
		this.bookingID = aBookingID;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double aAmount) {
		this.amount = aAmount;
	}
	
	public PaymentStatus getPaymentStatus() {
		return status;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public boolean validateCard(Card card) {
		if (card == null) {
			return false;
		}
		if (card.getType() != CardType.VISA || card.getType() != CardType.MASTERCARD) {
			return false;
		}
		return !card.isExpired();
	}
	
	public PaymentStatus process(Card card) {
		if (!validateCard(card)) {
			status = PaymentStatus.DECLINED;
			System.out.println("Invalid card. Please try again.");
			return PaymentStatus.DECLINED;
		}
		status = PaymentStatus.APPROVED;
		return PaymentStatus.APPROVED;
	}
	
	public String toString() {
		return "Payment ID: " + UUID + " Amount: " + amount + " Status: " + status.name();
	}
}
