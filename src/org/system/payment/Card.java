package org.system.payment;

import java.time.LocalDate;

public class Card {
	private String number;
	private CardType type;
	private LocalDate expiresOn;
	
	public Card() {
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
	
	public boolean isExpired() {
		return LocalDate.now().isAfter(expiresOn);
	}
	
	public String toString() {
		return type.name() + " " + getNumberMasked();
	}
}
