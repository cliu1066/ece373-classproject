package org.system.test;

import org.system.domain.*;
import org.system.payment.*;
import org.system.people.*;
import org.system.search.*;

import java.time.LocalDate;
import java.util.List;

public class Driver1 {
	public static void main(String[] args) {
		// create booking system
		BookingSystem bs = new BookingSystem();
		
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
		
		// test customer search
		// search for flights in phoenix
		System.out.println("Search for flights to Phoenix");
		FlightSearchCriteria search1 = new FlightSearchCriteria();
		search1.setDestination("Phoenix, USA");
		List<FlightListing> flights1 = c1.searchFlights(search1, bs.getFlights());
		for (FlightListing flight : flights1) {
			System.out.println(flight);
		}
		
		// search for flights in price range
		System.out.println("\nSearch for flights with prices $700-$1300");
		FlightSearchCriteria search2 = new FlightSearchCriteria();
		search2.setPriceRange(new PriceRange(700d, 1300d));
		List<FlightListing> flights2 = c1.searchFlights(search2, bs.getFlights());
		for (FlightListing flight : flights2) {
			System.out.println(flight);
		}
		
		// search for hotels in seoul
		System.out.println("\nSearch for hotels in Seoul");
		HotelSearchCriteria search3 = new HotelSearchCriteria();
		search3.setLocation("Seoul, South Korea");
		List<HotelListing> hotels1 = c2.searchHotels(search3, bs.getHotels());
		for (HotelListing hotel : hotels1) {
			System.out.println(hotel);
		}
		
		// search for hotels with rating 4.6+
		System.out.println("\nSearch for hotels with rating 4.6+");
		HotelSearchCriteria search4 = new HotelSearchCriteria();
		search4.setRating(4.6);
		List<HotelListing> hotels2 = c2.searchHotels(search4, bs.getHotels());
		for (HotelListing hotel : hotels2) {
			System.out.println(hotel);
		}
		
		// test bookings
		// flight bookings
		System.out.println("\nTest flight bookings");
		System.out.println("Customer 1 books flight listing 1");
		FlightBooking fb1 = c1.bookFlight(fl1, bs);
		System.out.println(fb1);
		
		System.out.println("\nCustomer 2 books flight listing 2");
		FlightBooking fb2 = c2.bookFlight(fl2, bs);
		System.out.println(fb2);
		
		System.out.println("\nCustomer 2 books flight listing 3");
		FlightBooking fb3 = c2.bookFlight(fl3, bs);
		System.out.println(fb3);
		
		// hotel bookings
		System.out.println("\nCustomer 1 books hotel listing 1");
		HotelBooking hb1 = c1.bookHotel(hl1, 2, bs);
		System.out.println(hb1);
		
		System.out.println("\nCustomer 2 books hotel listing 2");
		HotelBooking hb2 = c2.bookHotel(hl2, 3, bs);
		System.out.println(hb2);
		
		// test payments
		System.out.println("\nTest payments");
		System.out.println("Customer 1 pays for flight booking 1");
		Payment p1 = c1.pay(fb1, card1);
		System.out.println("Flight payment status: " + p1.getPaymentStatus());
		System.out.println("Flight booking status: " + fb1.getStatus());
		
		System.out.println("\nCustomer 1 pays for hotel booking 1");
		Payment p2 = c1.pay(hb1, card1);
		System.out.println("Hotel payment status: " + p2.getPaymentStatus());
		System.out.println("Hotel booking status: " + hb1.getStatus());
		
		System.out.println("\nCustomer 2 pays for flight booking 2");
		Payment p3 = c2.pay(fb2, card2);
		System.out.println("Flight payment status: " + p3.getPaymentStatus());
		System.out.println("Flight booking status: " + fb2.getStatus());
		
		Payment p4 = c2.pay(hb2, card2);
		System.out.println("\nCustomer 2 pays for hotel booking 2");
		System.out.println("Hotel payment status: " + p4.getPaymentStatus());
		System.out.println("Hotel booking status: " + hb2.getStatus());
		
		// test expired card
		System.out.println("\nCustomer 2 pays for flight booking 3 with invalid card");
		Payment p5 = c2.pay(fb3, card3);
		System.out.println("Expired card payment status: " + p5.getPaymentStatus());
		System.out.println("Expired card booking status: " + fb3.getStatus());
		
		// test cancel booking
		System.out.println("\nCancel customer 1 flight booking (unsuccessful because paid)");
		c1.cancelBooking(fb1.getUUID());
		System.out.println("Booking status: " + fb1.getStatus());
		
		System.out.println("\nCancel customer 2 hotel booking (successful)");
		c2.cancelBooking(hb2.getUUID());
		System.out.println("Booking status: " + hb2.getStatus());
		
		// test admin remove flight
		// paid booking (cannot remove)
		System.out.println("\nRemove FL001 (with paid booking)");
		boolean removeStatus = a1.removeFlight("FL001", bs.getFlights(), bs.getBookings());
		System.out.println("Removal status: " + (removeStatus ? "Successful" : "Unsuccessful"));
		
		// no conflicts (successful remove)
		System.out.println("Remove FL004 (no conflict)");
		removeStatus = a2.removeFlight("FL004", bs.getFlights(), bs.getBookings());
		System.out.println("Removal status: " + (removeStatus ? "Successful" : "Unsuccessful"));
		
		// test booking history
		System.out.println("\nCustomer 1 booking history:");
		c1.printBookingHistory();
		
		System.out.println("\nCustomer 2 booking history:");
		c2.printBookingHistory();
		
		// test update listing
		System.out.println("\nTest update flight listing");
		FlightListing ufl1 = new FlightListing();
		ufl1.setUUID("FL001");
		ufl1.setDestination("Seattle, USA");
		ufl1.setDate(LocalDate.now().plusDays(4));
		ufl1.setPrice(50);
		ufl1.setAirline("American Airlines");
		ufl1.setAvailable(true);
		a1.updateFlight("FL001", ufl1, bs.getFlights());
		System.out.println("FL001 updated: \n\t" + ufl1);
		
		// print all flights
		System.out.println("\nPrint all flights:");
		bs.printFlights();
		
		// print all hotels
		System.out.println("\nPrint all hotels:");
		bs.printHotels();
		
		// print all bookings
		System.out.println("\nPrint all bookings:");
		bs.printBookings();
	}
}
