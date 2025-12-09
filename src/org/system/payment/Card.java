package org.system.payment;

import java.time.LocalDate;

/*
 * Class: Card
 * The Card class represents the current method of payment and contains mostly
 * getters/setters for credit card related things.
 */
public class Card {
	private String number;
	private CardType type;
	private LocalDate expiresOn;
	
	public Card() {
		number = "";
		type = null;
		expiresOn = null;
	}
	
	public Card(String aNumber, CardType aType, LocalDate aDate) {
		this.number = aNumber;
		this.type = aType;
		this.expiresOn = aDate;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String aNumber) {
		number = aNumber;
	}
	
	/*
	 * getNumberMasked() - Masks a full credit card number with asterisks
	 * @return String of 12 asterisks plus the last 4 digits of a card
	 */
	public String getNumberMasked() {
		return "************" + number.substring(number.length() - 4);
	}
	
	public CardType getType() {
		return type;
	}
	
	public void setType(CardType aType) {
		this.type = aType;
	}
	
	public LocalDate getExpireDate() {
		return expiresOn;
	}
	
	/*
	 * isExpired() - Checks if card expiration date is past
	 * @return boolean true if expired
	 */
	public boolean isExpired() {
		return LocalDate.now().isAfter(expiresOn);
	}
	
	public String toString() {
		return type.name() + " " + getNumberMasked();
	}
}
