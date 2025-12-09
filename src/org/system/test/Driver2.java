package org.system.test;

import java.time.LocalDate;

import org.system.domain.*;
import org.system.payment.*;
import org.system.people.*;
import org.system.ui.*;

public class Driver2 {
	
	public static void main(String[] args) {
		BookingSystem bs = new BookingSystem();
		BookingSystemGUI newGUI;
		
		// create customers
		Customer c1 = new Customer();
		Customer c2 = new Customer();
		
		// create admins
		Admin a1 = new Admin();
		Admin a2 = new Admin();
		
		// create payment cards
		Card card1 = new Card("1111111111111111", CardType.VISA, LocalDate.now().plusYears(2));
		Card card2 = new Card("1234123412341234", CardType.MASTERCARD, LocalDate.now().plusYears(3));
		Card card3 = new Card("1234567812345678", CardType.VISA, LocalDate.now().minusMonths(3));
		
		// set admin attributes
		a1.setName("Bob");
		a1.setUUID("A001");
		bs.addAdmin(a1);
		
		a2.setName("Jill");
		a2.setUUID("A002");
		bs.addAdmin(a2);
		
		// set customer attributes
		c1.setName("Andrew");
		c1.setUUID("C001");
		c1.setEmail("andrew@email.com");
		bs.addCustomer(c1);
		
		c2.setName("Barack");
		c2.setUUID("C002");
		c2.setEmail("barack@email.com");
		bs.addCustomer(c2);
		
		// instantiate GUI
		newGUI = new BookingSystemGUI(bs);
	}
}
