package org.system.test;

import java.time.LocalDate;
import java.util.ArrayList;

import org.system.booking.*;
import org.system.listing.FlightListing;
import org.system.listing.HotelListing;
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
		Customer c3 = new Customer();
		
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
		
		c3.setName("Candice");
		c3.setUUID("C003");
		c3.setEmail("candice@email.com");
		bs.addCustomer(c3);
		
		// create flight listings
		FlightListing fl1 = new FlightListing();
		FlightListing fl2 = new FlightListing();
		FlightListing fl3 = new FlightListing();
		FlightListing fl4 = new FlightListing();
		
		// create hotel listings
		HotelListing hl1 = new HotelListing();
		HotelListing hl2 = new HotelListing();
		HotelListing hl3 = new HotelListing();
		HotelListing hl4 = new HotelListing();
		
		// set flight attributes
		fl1.setUUID("FL001");
		fl1.setDestination("Phoenix, USA");
		fl1.setDate(LocalDate.now().plusDays(5));
		fl1.setPrice(500);
		fl1.setAirline("Delta Airlines");
		fl1.setAvailable(true);
		
		fl2.setUUID("FL002");
		fl2.setDestination("Seoul, South Korea");
		fl2.setDate(LocalDate.now().plusDays(30));
		fl2.setPrice(1000);
		fl2.setAirline("Korean Air");
		fl2.setAvailable(true);
		
		fl3.setUUID("FL003");
		fl3.setDestination("Los Angeles, USA");
		fl3.setDate(LocalDate.now().plusDays(10));
		fl3.setPrice(100);
		fl3.setAirline("American Airlines");
		fl3.setAvailable(true);
		
		fl4.setUUID("FL004");
		fl4.setDestination("Tokyo, Japan");
		fl4.setDate(LocalDate.now().plusDays(20));
		fl4.setPrice(1200);
		fl4.setAirline("Japan Airlines");
		fl4.setAvailable(true);
		
		// set hotel attributes
		hl1.setUUID("HL001");
		hl1.setName("Sanctuary Camelback Mountain");
		hl1.setLocation("Paradise Valley, AZ, USA");
		hl1.setPrice(540);
		hl1.setRating(4.7);
		hl1.setAvailable(true);
		
		hl2.setUUID("HL002");
		hl2.setName("The Shilla Seoul");
		hl2.setLocation("Seoul, South Korea");
		hl2.setPrice(270);
		hl2.setRating(4.6);
		hl2.setAvailable(true);
		
		hl3.setUUID("HL003");
		hl3.setName("The Beverly Hills Hotel");
		hl3.setLocation("Beverly Hills, CA, USA");
		hl3.setPrice(1700);
		hl3.setRating(4.7);
		hl3.setAvailable(true);
		
		hl4.setUUID("HL004");
		hl4.setName("Hoshinoya Tokyo");
		hl4.setLocation("Tokyo, Japan");
		hl4.setPrice(1500);
		hl4.setRating(4.5);
		hl4.setAvailable(true);
		
		// admin add listings
		a1.addFlight(fl1, bs.getFlights());
		a1.addFlight(fl2, bs.getFlights());
		a2.addFlight(fl3, bs.getFlights());
		a2.addFlight(fl4, bs.getFlights());
		
		a1.addHotel(hl1, bs.getHotels());
		a1.addHotel(hl2, bs.getHotels());
		a2.addHotel(hl3, bs.getHotels());
		a2.addHotel(hl4, bs.getHotels());
		
		
		// get flight listings from csv
		ArrayList<FlightListing> flights = FlightListing.parseFlightData("flights.csv");
		for (FlightListing fl : flights) {
			a1.addFlight(fl, bs.getFlights());
		}
		
		// get hotel listing from csv
		ArrayList<HotelListing> hotels = HotelListing.parseHotelData("hotels.csv");
		for (HotelListing hl : hotels) {
			a2.addHotel(hl, bs.getHotels());
		}
		
		// instantiate GUI
		newGUI = new BookingSystemGUI(bs);
	}
}
